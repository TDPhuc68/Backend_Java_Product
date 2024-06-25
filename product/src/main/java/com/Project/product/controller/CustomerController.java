package com.Project.product.controller;

import com.Project.product.entity.Customer;
import com.Project.product.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    static final Logger log =
            LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;
    @PostMapping("/create-Customer")
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        try {
            log.info("Customer ID: " + customer.getId() + "adding...");
           Customer createCustomer = customerService.createCustomer(customer);
            log.info("Customer ID:" + createCustomer.getId() + "was added successfully");
            return ResponseEntity.ok().body(createCustomer);
        } catch (Exception e) {
            log.error("Error in createCustomer() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/admin/get-all-customer")
    public List<Customer> getAllCustomer(){
        try {
            return customerService.findAll();
        } catch (Exception e) {
            log.error("Error in findAll() method: " + e.getMessage());
            return null;
        }
    }
    @PutMapping("/update-customer/{id}")

    public ResponseEntity updateCustomer(@PathVariable("id")Long customerId,
                                                   @RequestBody Customer customer){
        try {
            Customer updateCustomer = customerService.updateCustomer(customerId,customer);
            log.info("Customer ID:" + updateCustomer.getId() + "was updated successfully");
            return  ResponseEntity.ok().body(updateCustomer);
        } catch (Exception e) {
            log.error("Error in updateCustomer() method :" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/admin/deleted-customer/{id}")
    public ResponseEntity deletedCustomer(@PathVariable("id")Long customerId){
        try {
            customerService.deletedCustomer(customerId);
            String message = "Customer with ID: " + customerId + " was deleted successfully";
            log.info(message);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            log.error("Error in deleteCustomer() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
