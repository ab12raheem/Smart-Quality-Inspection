package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.CustomerRepo;
import com.example.demo.repositries.OrderRepo;
import com.example.demo.repositries.RoleRepo;
import com.example.demo.repositries.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final RoleRepo roleRepo;
    private final UserService userService;
    @Autowired
    public CustomerService(CustomerRepo customerRepo, UserRepo userRepo, OrderRepo orderRepo, RoleRepo roleRepo, UserService userService) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.roleRepo = roleRepo;
        this.userService = userService;
    }

    public List<Customer> geAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getById(Integer id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (!customer.isPresent()) {
            throw new IllegalStateException("customer not found ");
        }
        return customer.get();
    }

    public List<Customer> getByAddress(String address) {
        if (address == null) {
            throw new IllegalStateException("provide an Address");
        }
        return customerRepo.findAllByAddress(address);
    }

    public Customer getByUserName(String userName) {
        Optional<User> user = userRepo.getByUserName(userName);
        if (!user.isPresent()) {
            throw new IllegalStateException("Customer not found");
        }
        Optional<Customer> customer = customerRepo.getByUser(user.get());
        if (!customer.isPresent()) {
            throw new IllegalStateException("customer not found");
        }
        return customer.get();
    }

    public void addCustomer(Customer customer) {
        Optional<User> user = userRepo.getByUserName(customer.getUser().getUserName());
        Optional<User>user1=userRepo.getByEmail(customer.getUser().getEmail());
        Role role=roleRepo.getByRoleName("Customer");
        if (user.isPresent()) {
            throw new IllegalStateException("userName used before");

        }
        if (user1.isPresent()) {
            throw new IllegalStateException("Email used before");

        }

        customer.getUser().setRole(role);
        userService.addUser(customer.getUser());
        customer.setUser(customer.getUser());
        customerRepo.save(customer);
    }

    /*public void deleteById(String userName) {
        Optional <User> user=userRepo.getByUserName(userName);
        if(!user.isPresent()){
            throw new IllegalStateException("user not found");
        }
        Optional<Customer> customer=customerRepo.findByUser(user.get());


        if(!customer.isPresent()){
            throw new IllegalStateException("customer not found ");
        }

        customerRepo.delete(customer.get());
        userRepo.delete(user.get());
    }
*/
    public void deleteByUserName(String userName) {
        Optional<User>user=userRepo.getByUserName(userName);
        if(!user.isPresent()){
            throw new IllegalStateException("customer not found");
        }
        Optional<Customer>customer=customerRepo.getByUser(user.get());
        if(!customer.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        customerRepo.delete(customer.get());
        userRepo.delete(user.get());
    }
    @Transactional
    public void updateCustomer(String userName, String address, String phoneNumber,
                               String postalCode, String cardId, String creditCard) {

        Customer customer=getByUserName(userName);

        if(address!=null &&
                address.length()>0&&
                !Objects.equals(customer.getAddress(),address)){
            customer.setAddress(address);
        }
        if(phoneNumber!=null &&
                phoneNumber.length()>0&&
                !Objects.equals(customer.getPhoneNumber(),phoneNumber)){
            customer.setPhoneNumber(phoneNumber);
        }
        if(cardId!=null &&
                cardId.length()>0&&
                !Objects.equals(customer.getCardID(),cardId)){
            customer.setCardID(cardId);
        }
        if(creditCard!=null &&
                creditCard.length()>0&&
                !Objects.equals(customer.getCreditCard(),creditCard)){
            customer.setCreditCard(creditCard);
        }
        if(postalCode!=null &&
                postalCode.length()>0&&
                !Objects.equals(customer.getPostalCode(),postalCode)){
            customer.setPostalCode(postalCode);
        }

    }

    public List<Order> getOrdersInProgress(String userName) {
        Customer customer=getByUserName(userName);
        List<Order>orders=orderRepo.findAllByCustomer(customer);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        for(Order order : orders){
            if(order.getOrderDone().toLocalDate().isAfter(date.toLocalDate())){
                orders.remove(order);
            }

        }
        return orders;
    }
    public List<Order> getOrdersDone(String userName) {
        Customer customer=getByUserName(userName);
        List<Order>orders=orderRepo.findAllByCustomer(customer);
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        for(Order order : orders){
            if(order.getOrderDone().toLocalDate().isBefore(date.toLocalDate())){
                orders.remove(order);
            }

        }
        return orders;
    }
    public List<Order> getOrders(String userName) {
        Customer customer=getByUserName(userName);
        List<Order>orders=orderRepo.findAllByCustomer(customer);
        return orders;
    }
    }

