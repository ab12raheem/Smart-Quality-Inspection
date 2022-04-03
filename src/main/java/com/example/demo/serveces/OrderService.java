package com.example.demo.serveces;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;
import com.example.demo.repositries.CustomerRepo;
import com.example.demo.repositries.OrderProductsRepo;
import com.example.demo.repositries.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    private final OrderProductsRepo orderProductsRepo;
    private final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private final ProductService productService;
    @Autowired
    public OrderService(OrderRepo orderRepo, OrderProductsRepo orderProductsRepo, CustomerService customerService, CustomerRepo customerRepo, ProductService productService) {
        this.orderRepo = orderRepo;
        this.orderProductsRepo = orderProductsRepo;
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.productService = productService;
    }

    public List<Order> getAll() {

        return orderRepo.findAll();
    }

    public Order getById(Integer id) {
        Optional<Order>order=orderRepo.findById(id);
        if(!order.isPresent()){
            throw new IllegalStateException("order not Found");
        }
        return order.get();
    }


    public List<Order> getOrdersInProgress() {
        Boolean x=true;
        List<Order>orders=orderRepo.findAllByActivate(x);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        for(Order order : orders){
            if(order.getOrderDone().toLocalDate().isBefore(date.toLocalDate())){
                orders.remove(order);
            }

        }
        return orders;
    }
    public List<Order> getOrdersDone() {
        Boolean x=true;
        List<Order>orders=orderRepo.findAllByActivate(x);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        for(Order order : orders){
            if(order.getOrderDone().toLocalDate().isAfter(date.toLocalDate())){
                orders.remove(order);
            }

        }
        return orders;
    }

    public void addOrder(String userName, Order order) {

        Customer customer=customerService.getByUserName(userName);
        order.setCustomer(customer);
        customerRepo.save(customer);
        orderRepo.save(order);

    }

    public void addProductToOrder(Integer productId, Integer orderId, Integer count) {
        Product product=productService.getById(productId);
        Order order=getById(orderId);
        if(order.getActivate()){
            throw new IllegalStateException("already has activate ");
        }
        OrderProducts orderProducts1=new OrderProducts(count,order,product);
        orderProductsRepo.save(orderProducts1);

    }

    public List<OrderProducts> getOrdersProducts(Integer orderId) {
        Order order=getById(orderId);
        return orderProductsRepo.findAllByOrder(order);
    }

    public void deleteById(Integer orderId) {
        Order order=getById(orderId);
        orderProductsRepo.deleteAllByOrder(order);
        orderRepo.delete(order);
    }

    public Order getCard(String userName) {
        Customer customer=customerService.getByUserName(userName);
        Optional<Order>order=orderRepo.getCard(customer.getId());
        if(!order.isPresent()){
            throw new IllegalStateException("there is no card");

        }
        return order.get();
    }
    @Transactional
    public void updateOrder(Integer orderId, Date orderDate, Date orderDone) {
        Order order=getById(orderId);
        if(orderDate!=null ){
            order.setOrderDate(orderDate);
        }
        if(orderDone!=null ){
            order.setOrderDone(orderDone);
        }

    }
    @Transactional
    public void setActivate(Integer id) {
        Order order=getById(id);
        if(order.getActivate()){
            order.setActivate(false);
        }else order.setActivate(true);
    }
}
