package com.example.demo.controllers;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.serveces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('Admin')")
    public List<Customer> getAllCustomers(){
        return customerService.geAllCustomers();
    }
    @GetMapping("byId/{id}")
    @PreAuthorize("hasRole('Admin')")
    public Customer getById(@PathVariable Integer id){
        return customerService.getById(id);
    }
    @GetMapping("byAddress/{address}")
    @PreAuthorize("hasRole('Admin')")
    public List<Customer> getByAddress(@PathVariable String address){
        return customerService.getByAddress(address);
    }
    @GetMapping("byUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public Customer getByUserName(@PathVariable String userName){
        return customerService.getByUserName(userName);
    }
    @GetMapping("getOrdersInProgress/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public List<Order> getOrdersInProgress(@PathVariable String userName){
        return customerService.getOrdersInProgress(userName);
    }
    @GetMapping("getOrderDone/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
    public List<Order> getOrdersDone(@PathVariable String userName){
        return customerService.getOrdersDone(userName);
    }
    @GetMapping("getAllOrders/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
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
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable String userName){
        customerService.deleteByUserName(userName);
    }
    @PutMapping("updateByUserName/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Customer')")
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
