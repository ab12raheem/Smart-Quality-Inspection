package com.example.demo.controllers;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;
import com.example.demo.serveces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAll();
    }
    @GetMapping("byId/{id}")
    public Order getById(@PathVariable Integer id){
        return orderService.getById(id);
    }
    @GetMapping("OrdersInProgress")
    public List<Order> getOrdersInProgress(){
        return orderService.getOrdersInProgress();
    }
    @GetMapping("OrdersDone")
    public List<Order> getOrdersDone(){
        return orderService.getOrdersDone();
    }
    @GetMapping("getOrderProducts/{orderId}")
    public List<OrderProducts> getOrderProducts(@PathVariable Integer orderId){
        return orderService.getOrdersProducts(orderId);
    }
    @GetMapping("getCard/{customerId}")
    public Order getCart(@PathVariable Integer customerId){
        return orderService.getCard(customerId);
    }

    @PostMapping("addOrder/{customerId}")
    public void addOrder(@PathVariable Integer customerId,
                         @RequestBody Order order){
        orderService.addOrder(customerId, order);
    }
    @PostMapping("addProductToOrder/{productId}/{orderId}")
    public void addProductToOrder(@PathVariable Integer productId,
                                  @PathVariable Integer orderId,
                                  @RequestParam Integer count){
        orderService.addProductToOrder(productId,orderId,count);
    }
    @DeleteMapping("deleteOrder/{orderId}")
    public void deleteById(@PathVariable  Integer orderId){
        orderService.deleteById(orderId);
    }
    @PutMapping("updateOrder/{orderId}")
    public void updateOrder(@PathVariable Integer orderId,
                            @RequestParam (required = false) Date orderDate,
                            @RequestParam(required = false) Date orderDone
                            ){
        orderService.updateOrder(orderId,orderDate,orderDone);
    }
    @PutMapping("setActivate/{id}")
    public void setActivate(@PathVariable Integer id){
        orderService.setActivate(id);

    }






}
