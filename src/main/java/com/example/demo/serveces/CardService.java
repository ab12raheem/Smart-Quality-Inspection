package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.CardProductsRepo;
import com.example.demo.repositries.CardRepo;
import com.example.demo.repositries.OrderProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class CardService {
    private final CardRepo cardRepo;
    private final CardProductsRepo cardProductsRepo;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderProductsRepo orderProductsRepo;
    private final ProductService productService;
    @Autowired
    public CardService(CardRepo cardRepo, CardProductsRepo cardProductsRepo, CustomerService customerService, OrderService orderService, OrderProductsRepo orderProductsRepo, ProductService productService) {
        this.cardRepo = cardRepo;
        this.cardProductsRepo = cardProductsRepo;
        this.customerService = customerService;
        this.orderService = orderService;
        this.orderProductsRepo = orderProductsRepo;
        this.productService = productService;
    }

    public Card getCard(String userName) {
        Customer customer=customerService.getByUserName(userName);
        Optional<Card> card=cardRepo.findByCustomer(customer);
        if(!card.isPresent()){
            throw new IllegalStateException("not found");

        }
        return card.get();


    }

    public CardProducts getProducts(String userName) {
        Card card = getCard(userName);
        return cardProductsRepo.findAllByCard(card);
    }

    public void addCard(String userName, Card card) {
        Customer customer=customerService.getByUserName(userName);
        Optional<Card> card1=cardRepo.findByCustomer(customer);
        if(card1.isPresent()){
            throw new IllegalStateException("Card existBefore");
        }
        card1.get().setCustomer(customer);
        cardRepo.save(card1.get());
    }

    public void addProduct(String userName, CardProducts cardProducts) {
        Card card=getCard(userName);
        Optional<CardProducts> cardProducts1=cardProductsRepo.findByProduct(cardProducts.getProduct());
        if(cardProducts1.isPresent()){
           throw new IllegalStateException("product have been Added before");

        }
        cardProducts.setCard(card);
        cardProductsRepo.save(cardProducts);
    }
    @Transactional
    public void activate(String userName) {
        Card card=getCard(userName);
        Set<CardProducts> cardProducts=card.getCardProducts();
        if(cardProducts.isEmpty()){
            throw new IllegalStateException("no products");

        }
        Date orderDate=Date.valueOf(LocalDate.now());
        Date orderDone= Date.valueOf(LocalDate.now().plusDays(12));
        Order order = new Order(orderDate,orderDone ,true);
        orderService.addOrder(userName,order);

        for(CardProducts cardProducts1:cardProducts){
            OrderProducts orderProducts=new OrderProducts(cardProducts1.getCount(),
                    order, cardProducts1.getProduct());
            cardProductsRepo.delete(cardProducts1);
            orderProductsRepo.save(orderProducts);
        }
    }
    @Transactional
    public void deleteProduct(String userName, Integer productId) {
        Card card=getCard(userName);
        Product product = productService.getById(productId);
        Optional<CardProducts>cardProducts=cardProductsRepo.findByProductAndCard(product,card);
        if(!cardProducts.isPresent()){
            throw new IllegalStateException("no such product");
        }
        cardProductsRepo.delete(cardProducts.get());
    }
    @Transactional
    public void updateProduct(String userName, Integer count,Integer productId) {
        Card card=getCard(userName);
        Product product = productService.getById(productId);
        Optional<CardProducts>cardProducts=cardProductsRepo.findByProductAndCard(product,card);
        if(!cardProducts.isPresent()){
            throw new IllegalStateException("no such product");
        }
        if(count!=null){
            cardProducts.get().setCount(count);
        }
    }
}
