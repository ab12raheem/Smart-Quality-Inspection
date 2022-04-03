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
    @GetMapping("getOrdersInProgress/{userName}")
    public List<Order> getOrdersInProgress(@PathVariable String userName){
        return customerService.getOrdersInProgress(userName);
    }
    @GetMapping("getOrderDone/{userName}")
    public List<Order> getOrdersDone(@PathVariable String userName){
        return customerService.getOrdersDone(userName);
    }
    @GetMapping("getAllOrders/{userName}")
    public List<Order> getOrders(@PathVariable String userName){
        return customerService.getOrders(userName);
    }

    @PostMapping("addCustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);

    }
    /*@DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        customerService.deleteById(id);
    }*/
    @DeleteMapping("deleteByUserName/{userName}")
    public void deleteById(@PathVariable String userName){
        customerService.deleteByUserName(userName);
    }
    @PutMapping("updateByUserName/{userName}")
    public void updateCustomer(
            @PathVariable  String userName ,
            @RequestParam (required = false) String address,
            @RequestParam (required = false) String postalCode,
            @RequestParam (required = false) String phoneNumber,
            @RequestParam (required = false) String  creditCard,
            @RequestParam (required = false) String cardId){
                customerService.updateCustomer(userName,address,phoneNumber,postalCode,cardId,creditCard);

    }


}
