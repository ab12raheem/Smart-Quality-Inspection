package com.example.demo.repositries;

import com.example.demo.model.Card;
import com.example.demo.model.CardProducts;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CardProductsRepo extends JpaRepository<CardProducts,Integer> {
    List<CardProducts> findAllByCard(Card card);

    Optional<CardProducts> findByProduct(Product product);

    Optional<CardProducts> findByProductAndCard(Product product, Card card);
}
