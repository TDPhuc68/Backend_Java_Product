package com.Project.product.repository;

import com.Project.product.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReponsitory  extends JpaRepository<Customer,Long> {
}
