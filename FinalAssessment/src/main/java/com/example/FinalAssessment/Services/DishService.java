package com.example.FinalAssessment.Services;

import com.example.FinalAssessment.Entities.Dish;
import com.example.FinalAssessment.Repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Optional<Dish> updateDish(Dish dish) {
        Optional<Dish> optionalDish = dishRepository.findById(dish.getId());
        if (optionalDish.isPresent()) {
            optionalDish.get().setName(dish.getName());
            optionalDish.get().setPrice(dish.getPrice());
            optionalDish.get().setIngredients(dish.getIngredients());
            optionalDish.get().setTag(dish.getTag());
            optionalDish.get().setPrepTime(dish.getPrepTime());
            optionalDish.get().setImageUrl(dish.getImageUrl());
            dishRepository.save(optionalDish.get());
        }
        return optionalDish;
    }

    public Optional<Dish> deleteDish(Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            dishRepository.delete(optionalDish.get());
        }
        return optionalDish;
    }
}
