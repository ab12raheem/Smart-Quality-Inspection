package com.example.demo.repositries;

import com.example.demo.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {
    @Query(
            value = "SELECT * FROM public.notification ORDER BY id DESC LIMIT 5",
            nativeQuery = true
    )
    List<Notification> getLast();
}
