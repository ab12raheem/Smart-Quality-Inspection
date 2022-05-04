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
import java.util.*;

@Service
public class CardService {
    private final CardRepo cardRepo;
    private final CardProductsRepo cardProductsRepo;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderProductsRepo orderProductsRepo;
    private final ProductService productService;
    private final FinancialService financialService;
    @Autowired
    public CardService(CardRepo cardRepo, CardProductsRepo cardProductsRepo, CustomerService customerService, OrderService orderService, OrderProductsRepo orderProductsRepo, ProductService productService, FinancialService financialService) {
        this.cardRepo = cardRepo;
        this.cardProductsRepo = cardProductsRepo;
        this.customerService = customerService;
        this.orderService = orderService;
        this.orderProductsRepo = orderProductsRepo;
        this.productService = productService;
        this.financialService = financialService;
    }

    public Card getCard(String userName) {
        Customer customer=customerService.getByUserName(userName);
        Optional<Card> card=cardRepo.findByCustomer(customer);
        if(!card.isPresent()){
            throw new IllegalStateException("not found");

        }
        return card.get();


    }

    public List<Product> getProducts(String userName) {
        Card card = getCard(userName);
        List<CardProducts> cardProductsSet= cardProductsRepo.findAllByCard(card);
        List <Product> products=new ArrayList<>();
        for(CardProducts cardProducts : cardProductsSet){
            if(cardProducts.getActivate()==true) {
                products.add(cardProducts.getProduct());
            }

        }
        return products;

    }

    public void addCard(String userName, Card card) {
        Customer customer=customerService.getByUserName(userName);
        Optional<Card> card1=cardRepo.findByCustomer(customer);
        if(card1.isPresent()){
            throw new IllegalStateException("Card existBefore");
        }
        card.setCustomer(customer);
        cardRepo.save(card);
    }

    public void addProduct(String userName, CardProducts cardProducts, Integer productId) {
        Card card=getCard(userName);
        Product product=productService.getById(productId);

        Optional<CardProducts> cardProducts1=cardProductsRepo.findByProduct(cardProducts.getProduct());
        if(cardProducts1.isPresent()){
            if(cardProducts1.get().getActivate()==false) {
                cardProducts1.get().setActivate(true);
                cardProductsRepo.save(cardProducts1.get());
                return;
            }else
                throw new IllegalStateException("product have been Added before");
        }
        cardProducts.setActivate(true);
        cardProducts.setCard(card);
        cardProducts.setProduct(product);
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
            if(cardProducts1.getActivate()==true) {
                OrderProducts orderProducts = new OrderProducts(cardProducts1.getCount(),
                        order, cardProducts1.getProduct());
                cardProductsRepo.delete(cardProducts1);
                orderProductsRepo.save(orderProducts);
            }
        }
        financialService.updateFinancial();
    }

    @Transactional
    public void deleteProduct(String userName, Integer productId) {
        Card card=getCard(userName);
        Set<CardProducts> cardProducts=card.getCardProducts();

        for(CardProducts cardProducts1:cardProducts){
            if(cardProducts1.getProduct().getId()==productId)
            cardProducts1.setActivate(false);

        }

    }
    @Transactional
    public void updateProduct(String userName, Integer count,Integer productId) {
        Card card=getCard(userName);
        Product product = productService.getById(productId);
        Optional<CardProducts>cardProducts=cardProductsRepo.findByProductAndCard(product,card);
        if(!cardProducts.isPresent()||cardProducts.get().getActivate()==false){
            throw new IllegalStateException("no such product");
        }
        if(count!=null){
            cardProducts.get().setCount(count);
        }
    }
}
