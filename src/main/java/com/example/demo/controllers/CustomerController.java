package com.example.demo.controllers;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.serveces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.geAllCustomers();
    }
    @GetMapping("byId/{id}")
    public Customer getById(@PathVariable Integer id){
        return customerService.getById(id);
    }
    @GetMapping("byAddress/{address}")
    public List<Customer> getByAddress(@PathVariable String address){
        return customerService.getByAddress(address);
    }
    @GetMapping("byUserName/{userName}")
    public Customer getByUserName(@PathVariable String userName){
        return customerService.getByUserName(userName);
    }
    @GetMapping("getOrdersInProgress/{id}")
    public List<Order> getOrdersInProgress(@PathVariable Integer id){
        return customerService.getOrdersInProgress(id);
    }
    @GetMapping("getOrderDone/{id}")
    public List<Order> getOrdersDone(@PathVariable Integer id){
        return customerService.getOrdersDone(id);
    }
    @GetMapping("getAllOrders/{id}")
    public List<Order> getOrders(@PathVariable Integer id){
        return customerService.getOrders(id);
    }

    @PostMapping("addCustomer/{userId}")
    public void addCustomer(@PathVariable Integer userId,
                            @RequestBody Customer customer){
        customerService.addCustomer(customer,userId);

    }
    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        customerService.deleteById(id);
    }
    @DeleteMapping("deleteByUserName/{userName}")
    public void deleteById(@PathVariable String userName){
        customerService.deleteByUserName(userName);
    }
    @PutMapping("updateById/{id}")
    public void updateCustomer(
            @PathVariable  Integer id ,
            @RequestParam (required = false) String address,
            @RequestParam (required = false) String postalCode,
            @RequestParam (required = false) String phoneNumber,
            @RequestParam (required = false) String  creditCard,
            @RequestParam (required = false) String cardId){
                customerService.updateCustomer(id,address,phoneNumber,postalCode,cardId,creditCard);

    }


}
