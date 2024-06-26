package com.example.FinalAssessment.Repositories;

import com.example.FinalAssessment.Entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    public Optional<Dish> findByName(String name);
}
