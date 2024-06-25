package com.Project.product.repository;

import com.Project.product.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersReponsitory extends JpaRepository<Orders,Long> {
}
