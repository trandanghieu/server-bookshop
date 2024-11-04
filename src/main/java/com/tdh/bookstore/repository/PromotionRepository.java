package com.tdh.bookstore.repository;

import com.tdh.bookstore.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByPromotionNameContaining(String name);
}
