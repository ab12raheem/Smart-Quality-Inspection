package com.example.demo.repositries;

import com.example.demo.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialRepo extends JpaRepository<Financial,Integer> {

    Optional<Financial> findByMonth(Date valueOf);

    @Query(
            value = "SELECT * FROM financial ORDER BY financial.month LIMIT 10 ",
            nativeQuery = true
    )
    List<Financial> getLastTenRecords();
}
