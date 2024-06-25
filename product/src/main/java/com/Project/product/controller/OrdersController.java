package com.Project.product.controller;

import com.Project.product.entity.Orders;
import com.Project.product.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {
    static final Logger log =
            LoggerFactory.getLogger(OrdersController.class);
    @Autowired
     private OrdersService ordersService;
    @PostMapping("/create-orders")
    public ResponseEntity createOrders(@RequestBody Orders orders) {
        try{
            Orders createaOrder = ordersService.createOrders(orders);
            log.info(" order Id:"+ createaOrder.getId() + "was create succesfully");
            return ResponseEntity.ok().body(createaOrder);
        }catch (Exception e){
            log.error("Error in createOrder() method:"+e.getMessage());
            return null;
        }

    }
    @GetMapping("/get-all-orders")
    public List<Orders> getAllOrders(){
        try{
            return ordersService.findAll();
        }catch (Exception e){
            log.error("Error in fillAll() method "+ e.getMessage() );
            return null;
        }

    }
    @PutMapping("/update-orders/{id}")
    public ResponseEntity updateOrders(@PathVariable(name = "id") Long ordersId,
                                               @RequestBody Orders orders){
        try {
            Orders updateOrder = ordersService.updateOrders(ordersId, orders);
            log.info("Order ID:" + updateOrder.getId() + "was updated successfully");
            return  ResponseEntity.ok().body(updateOrder);
        } catch (Exception e) {
            log.error("Error in updateOrder() method :" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deleted-orders/{id}")
    public ResponseEntity deletedOrders(@PathVariable("id")Long ordersId){
        try {
            ordersService.deletedOrders(ordersId);
            String message = "Order with ID: " + ordersId + " was deleted successfully";
            log.info(message);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            log.error("Error in deleteOrder() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
