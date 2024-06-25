package com.Project.product.service;

import com.Project.product.entity.Product;
import com.Project.product.repository.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductReponsitory productReponsitory;
    public List<Product> getAllProduct(){return productReponsitory.findAll();}
    public Product createProduct(Product product){return productReponsitory.save(product);}
    public void deletedProduct(Long id){ productReponsitory.deleteById(id);}
public Product updateProduct(Long id,Product product){
        Product p =productReponsitory.findById(id).get();
        p.setName(product.getName());
        p.setBrand(product.getBrand());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());
        return productReponsitory.save(p);
    }
}
