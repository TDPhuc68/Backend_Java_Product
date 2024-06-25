package com.Project.product.service;

import com.Project.product.entity.Orders;
import com.Project.product.repository.OrdersReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersService {
    @Autowired
    private OrdersReponsitory ordersReponsitory;
    public List<Orders> findAll(){return ordersReponsitory.findAll();}
    public Orders createOrders(Orders orders) {return ordersReponsitory.save(orders);}
    public  void deletedOrders(Long id){ordersReponsitory.deleteById(id);}

    public Orders updateOrders(Long id,Orders orders){
        Orders o =ordersReponsitory.findById(id).get();
        o.setDate(orders.getDate());
        o.setPrice(orders.getPrice());
        return ordersReponsitory.save(o);
    }
}
