package com.Project.product.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;
    @Column(name="product_name")
    private String name;
    @Column(name="product_price")
    private Float price;
    @Column(name="product_image")
    private String image;
    @Column(name ="product_description")
    private String description;
    @Column(name="product_brand")
    private String brand;
    @Column(name="product_quantity")
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategory_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "product"})
    private ProductCategory productCategory;
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "product"})
    private List<OrderDetails> orderDetails;


}
