package com.example.demo.repositries;

import com.example.demo.model.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateRepo extends JpaRepository<Update,Integer> {
    @Query(
            value = "SELECT * FROM update ORDER BY id DESC LIMIT 5",
            nativeQuery = true
    )
    List<Update> getUpdate();
}
