package com.Project.product.service;

import com.Project.product.entity.OrderDetails;
import com.Project.product.repository.OrderDetailReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailReponsitory orderDetailReponsitory;
    public List<OrderDetails> findAll(){return orderDetailReponsitory.findAll();}
    public OrderDetails createOrderDetail(OrderDetails orderDetails) {return orderDetailReponsitory.save(orderDetails);}
    public  void deletedOrderDetail(Long id){orderDetailReponsitory.deleteById(id);}
    public OrderDetails updateOrderDetails(Long id,OrderDetails orderDetails){
        OrderDetails or =orderDetailReponsitory.findById(id).get();
        or.setPrice(orderDetails.getPrice());
        or.setQuantity(orderDetails.getQuantity());
        return orderDetailReponsitory.save(or);
    }
}
