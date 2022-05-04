package com.example.demo.controllers;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;
import com.example.demo.serveces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
@CrossOrigin(origins = "http://localhost:3000")

public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('Admin')")
    public List<Order> getAllOrders(){
        return orderService.getAll();
    }
    @GetMapping("byId/{id}")
    @PreAuthorize("hasRole('Admin')")
    public Order getById(@PathVariable Integer id){
        return orderService.getById(id);
    }
    @GetMapping("OrdersInProgress")
    @PreAuthorize("hasRole('Admin')")
    public List<Order> getOrdersInProgress(){
        return orderService.getOrdersInProgress();
    }
    @GetMapping("OrdersDone")
    @PreAuthorize("hasRole('Admin')")
    public List<Order> getOrdersDone(){
        return orderService.getOrdersDone();
    }
    @GetMapping("getOrderProducts/{orderId}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public List<OrderProducts> getOrderProducts(@PathVariable Integer orderId){
        return orderService.getOrdersProducts(orderId);
    }
    @GetMapping("getCard/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public Order getCart(@PathVariable String userName){
        return orderService.getCard(userName);
    }

    @PostMapping("addOrder/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public void addOrder(@PathVariable String userName,
                         @RequestBody Order order){
        orderService.addOrder(userName, order);
    }
    @PostMapping("addProductToOrder/{productId}/{orderId}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public void addProductToOrder(@PathVariable Integer productId,
                                  @PathVariable Integer orderId,
                                  @RequestParam Integer count){
        orderService.addProductToOrder(productId,orderId,count);
    }
    @DeleteMapping("deleteOrder/{orderId}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public void deleteById(@PathVariable  Integer orderId){
        orderService.deleteById(orderId);
    }
    @PutMapping("updateOrder/{orderId}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public void updateOrder(@PathVariable Integer orderId,
                            @RequestParam (required = false) Date orderDate,
                            @RequestParam(required = false) Date orderDone
                            ){
        orderService.updateOrder(orderId,orderDate,orderDone);
    }
    @PutMapping("setActivate/{id}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public void setActivate(@PathVariable Integer id){
        orderService.setActivate(id);

    }






}
