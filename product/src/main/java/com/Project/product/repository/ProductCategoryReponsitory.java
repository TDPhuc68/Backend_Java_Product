package com.Project.product.repository;

import com.Project.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryReponsitory extends JpaRepository<ProductCategory,Long> {
}
