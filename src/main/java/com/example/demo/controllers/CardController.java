package com.example.demo.controllers;

import com.example.demo.model.Card;
import com.example.demo.model.CardProducts;
import com.example.demo.model.Product;
import com.example.demo.serveces.CardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/card")
@CrossOrigin(origins = "http://localhost:3000")

public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    @GetMapping("getCard/{userName}")
    public Card getCard(@PathVariable String userName){
        return cardService.getCard(userName);
    }
    @GetMapping("getProducts/{userName}")
    @PreAuthorize("hasRole('Customer')")
    public List<Product> getProducts(@PathVariable String userName){
        return cardService.getProducts(userName);
    }
    @PostMapping("addCard/{userName}")
    public void addCard(@PathVariable String userName,
                        @RequestBody  Card card){
        cardService.addCard(userName,card);
    }
    @PostMapping("addProduct/{userName}/{productId}")
    public void addProduct(@PathVariable String userName,
                           @PathVariable Integer productId,
                           @RequestBody CardProducts cardProducts){
        cardService.addProduct(userName,cardProducts,productId);
    }
    @PutMapping("activate/{userName}")
    @PreAuthorize("hasRole('Customer')")
    public void activateCard(@PathVariable String userName){
        cardService.activate(userName);
    }
    @DeleteMapping("deleteProduct/{userName}/{productId}")
    @PreAuthorize("hasRole('Customer')")
    public void deleteProduct(@PathVariable String  userName,
                              @PathVariable Integer productId){
        cardService.deleteProduct(userName,productId);
    }
    @PutMapping("updateProduct/{userName}/{productId}")
    @PreAuthorize("hasRole('Customer')")
    public void updateProduct(@PathVariable String userName,
                              @PathVariable Integer productId,
                              @RequestParam Integer count){
        cardService.updateProduct(userName,count,productId);
    }


}
