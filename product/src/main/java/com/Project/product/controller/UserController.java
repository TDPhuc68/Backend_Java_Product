package com.Project.product.controller;

import com.Project.product.config.JwtTokenUtil;
import com.Project.product.entity.Token;
import com.Project.product.entity.User;
import com.Project.product.service.JwtUserDetailsService;
import com.Project.product.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    static final Logger log =
            LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity createUser(@RequestBody User user) {
        User addUser = new User();
        try {
            addUser.setUsername(user.getUsername());
            addUser.setPassword(passwordEncoder().encode(user.getPassword()));
            addUser.setEmail(user.getEmail());
            addUser.setRole(user.getRole());

            userService.createUser(addUser);
            log.info("User ID: " + addUser.getUser_id() + " was created successfully");
            return ResponseEntity.ok().body(addUser);
        } catch (Exception e) {
            log.error("Error creating user: " + e.getMessage());
            return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
        }
    }
    @ApiOperation(value = "Xem danh sách User", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy")
    })
    @GetMapping("/get-all-user")
    public ModelAndView getAllUsers() {
        try {
            ModelAndView modelAndView = new ModelAndView("listuser");
            modelAndView.addObject("users",userService.findAll());
            return modelAndView;
        }catch (Exception e) {
            log.error("Error in getAllUser : " + e.getMessage());
            return null;
        }
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long userId,
                                           @RequestBody User user) {
        try {
            User updateUser = userService.updateUser(userId, user);
            log.error("User ID: " + user.getUser_id() + " was updated successfully");
            return ResponseEntity.ok().body(updateUser);
        } catch (Exception e) {
            log.error("Error editing user: " + e.getMessage());
            return ResponseEntity.status(500).body("Error editing user: " + e.getMessage());
        }
    }
    @DeleteMapping("/admin/deleted-user/{id}")
    public ResponseEntity deletedUser(@PathVariable(name = "id") Long userId) {
        try {
            userService.deletedUser(userId);
            String message = " User with ID : " + userId + " was deleted successfully";

            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            log.error("Error deleting user:" + e.getMessage());
            return ResponseEntity.status(500).body("Error deteting user : " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            Token token = new com.Project.product.entity.Token(jwtTokenUtil.generateToken(userDetails));
            log.info("User with email: " + user.getEmail() + " was logged in successfully");
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            log.error("Error in login: " + e.getMessage());
            return ResponseEntity.status(500).body("Error in login: " + e.getMessage());
        }
    }
}

