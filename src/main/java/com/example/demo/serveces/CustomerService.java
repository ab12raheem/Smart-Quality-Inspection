package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.CustomerRepo;
import com.example.demo.repositries.OrderRepo;
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
    @Autowired
    public CustomerService(CustomerRepo customerRepo, UserRepo userRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
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

    public void addCustomer(Customer customer, Integer userId) {
        Optional<User> user = userRepo.findById(userId);

        if (!user.isPresent()) {
            throw new IllegalStateException("user not found");

        }
        if (user.get().getRole() != 0) {
            throw new IllegalStateException("user has been used before");
        }
        User user1 = user.get();
        user1.setRole(2);
        userRepo.save(user1);
        customer.setUser(user1);
        customerRepo.save(customer);
    }

    public void deleteById(Integer id) {
        Optional<Customer> customer=customerRepo.findById(id);


        if(!customer.isPresent()){
            throw new IllegalStateException("customer not found ");
        }
        Optional<User>user=userRepo.findById(customer.get().getUser().getId());
        if(!user.isPresent()){
            throw new IllegalStateException("user not found ");
        }
        customerRepo.deleteById(id);
        userRepo.delete(user.get());
    }

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
    public void updateCustomer(Integer id, String address, String phoneNumber,
                               String postalCode, String cardId, String creditCard) {
        Optional<Customer>customer=customerRepo.findById(id);
        if(!customer.isPresent()){
            throw new IllegalStateException("customer not found");
        }
        if(address!=null &&
                address.length()>0&&
                !Objects.equals(customer.get().getAddress(),address)){
            customer.get().setAddress(address);
        }
        if(phoneNumber!=null &&
                phoneNumber.length()>0&&
                !Objects.equals(customer.get().getPhoneNumber(),phoneNumber)){
            customer.get().setPhoneNumber(phoneNumber);
        }
        if(cardId!=null &&
                cardId.length()>0&&
                !Objects.equals(customer.get().getCardID(),cardId)){
            customer.get().setCardID(cardId);
        }
        if(creditCard!=null &&
                creditCard.length()>0&&
                !Objects.equals(customer.get().getCreditCard(),creditCard)){
            customer.get().setCreditCard(creditCard);
        }
        if(postalCode!=null &&
                postalCode.length()>0&&
                !Objects.equals(customer.get().getPostalCode(),postalCode)){
            customer.get().setPostalCode(postalCode);
        }

    }

    public List<Order> getOrdersInProgress(Integer id) {
        Customer customer=getById(id);
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
    public List<Order> getOrdersDone(Integer id) {
        Customer customer=getById(id);
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
    public List<Order> getOrders(Integer id) {
        Customer customer=getById(id);
        List<Order>orders=orderRepo.findAllByCustomer(customer);
        return orders;
    }
    }

