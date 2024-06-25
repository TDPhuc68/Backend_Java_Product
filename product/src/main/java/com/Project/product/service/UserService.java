package com.Project.product.service;

import com.Project.product.entity.User;
import com.Project.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){return userRepository.findAll();}
    public User createUser(User user) {return userRepository.save(user);}
    public  void deletedUser(Long id){userRepository.deleteById(id);}
    public User updateUser(Long id,User user){
        User usertoEdit = userRepository.findById(id).get();
        usertoEdit.setUsername(user.getUsername());
        usertoEdit.setPassword(user.getPassword());
        usertoEdit.setEmail(user.getEmail());
        usertoEdit.setRole(user.getRole());
        return userRepository.save(usertoEdit);
    }
     public User findByEmail(String email){
        User u = userRepository.findUserByEmail(email);
        return u;
    }


}
