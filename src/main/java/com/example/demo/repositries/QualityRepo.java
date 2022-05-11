package com.example.demo.repositries;

import com.example.demo.model.Product;
import com.example.demo.model.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRepo extends JpaRepository<Quality,Integer> {

    @Query(
            value="SELECT COUNT(num_defect) FROM quality",
            nativeQuery = true
    )
     Integer findCountOfDefect();

    @Query(
            value="SELECT COUNT(num_ok) FROM quality",
            nativeQuery = true
    )
    Integer findCountOfOk();
    @Modifying
    @Query
            (
                    value = "UPDATE quality SET activate=false  ",
                    nativeQuery = true
            )
    void setAllActivateFalse();


    Quality getByActivate(Boolean x);
}
