package com.Project.product.repository;

import com.Project.product.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetails,Long> {
}
