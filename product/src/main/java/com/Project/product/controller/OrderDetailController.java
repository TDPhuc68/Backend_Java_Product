package com.Project.product.controller;

import com.Project.product.entity.OrderDetails;
import com.Project.product.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderDetailController {
    static final Logger log =
            LoggerFactory.getLogger(OrderDetailController.class);

    @Autowired
    OrderDetailService orderDetailService;
    @PostMapping("/create-OrderDetail")
    public ResponseEntity createOrderDetails(@RequestBody OrderDetails orderDetails) {
        try {
            log.info("OrderDetail ID :" + orderDetails.getId() + "adding...");
            OrderDetails createOrderDetail = orderDetailService.createOrderDetail(orderDetails);
            log.info("OrderDetail ID :" + createOrderDetail.getId() + "was added successfully");
            return ResponseEntity.ok().body(createOrderDetail);
        } catch (Exception e) {
            log.error("Error in createOrderDetail() method :" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/get-all-orderDetail")
    public List<OrderDetails> getAllOrderDetails(){
        try{
            return orderDetailService.findAll();
        }catch (Exception e){
log.error("Error in findAll() method "+e.getMessage());
return null;
        }

    }
    @PutMapping("/update-orderDetail/{id}")
    public ResponseEntity updateOrderDetails(@PathVariable("id") Long orderDetailId,
                                                           @RequestBody OrderDetails orderDetails) {
        try {
            OrderDetails updateOrderDetail = orderDetailService.updateOrderDetails(orderDetailId, orderDetails);
            log.info("OrderDetail ID :" + updateOrderDetail.getId() + "was updated successfully");
            return ResponseEntity.ok().body(updateOrderDetail);
        } catch (Exception e) {
            log.error("Error in updateOrderDetail() method :" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleted-orderDetail/{id}")
    public ResponseEntity deletedOrderDetail(@PathVariable("id")Long orderDetailId){
        try{
            orderDetailService.deletedOrderDetail(orderDetailId);
            String message ="OrderDetail with ID: " +orderDetailId +"was deleted successfully";
            log.info(message);
            return ResponseEntity.ok().body(message);
        }catch (Exception e){
            log.error("Error in deletedOrderDetail() method" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
