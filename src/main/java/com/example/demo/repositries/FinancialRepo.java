package com.example.demo.repositries;

import com.example.demo.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface FinancialRepo extends JpaRepository<Financial,Integer> {

    Optional<Financial> findByMonth(Date valueOf);
}
