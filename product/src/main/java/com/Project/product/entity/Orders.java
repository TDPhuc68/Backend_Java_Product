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
@Table(name="orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="orders_id")
    private Long id;
    @Column(name="orders_date")
    private String date;
    @Column(name="orders_total_price")
    private float price;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "order"})
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "order"})
    private List<OrderDetails> orderDetails;

}
