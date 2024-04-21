package com.springboot.everefing.repository;

import com.springboot.everefing.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

   List<Order> findByUsername(String username);

   @Transactional
   void deleteByUsername(String username);
}
