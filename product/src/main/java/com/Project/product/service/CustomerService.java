package com.Project.product.service;

import com.Project.product.entity.Customer;
import com.Project.product.repository.CustomerReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerReponsitory customerReponsitory;

    public List<Customer> findAll(){return customerReponsitory.findAll();}
    public Customer createCustomer(Customer customer) {return customerReponsitory.save(customer);}
    public  void deletedCustomer(Long id){customerReponsitory.deleteById(id);}
    public Customer updateCustomer(Long id,Customer customer){
        Customer c=customerReponsitory.findById(id).get();
        c.setCustomer_name(customer.getCustomer_name());
        c.setCustomer_address((customer.getCustomer_address()));
        c.setCustomer_phone_number(customer.getCustomer_phone_number());
        return customerReponsitory.save(c);
    }
}
