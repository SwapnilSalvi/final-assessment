package com.example.FinalAssessment.Controllers;

import com.example.FinalAssessment.Entities.Dish;
import com.example.FinalAssessment.Services.DishService;
import com.example.FinalAssessment.Utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/dish")
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        return ResponseHandler.generateResponse("Found", HttpStatus.OK, dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDish(@PathVariable Long id) {
        return ResponseHandler.generateResponse("Found", HttpStatus.OK, dishService.getDishById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDish(@RequestBody Dish dish) {
        Dish addedDish = dishService.addDish(dish);
        return ResponseHandler.generateResponse("Added", HttpStatus.CREATED, addedDish);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDish(@RequestBody Dish dish) {
        Optional<Dish> optionalDish = dishService.updateDish(dish);
        if (optionalDish.isPresent()) {
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, optionalDish.get());
        }else{
            return ResponseHandler.generateResponse("Dish with given id does not exist", HttpStatus.CREATED, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDish(@PathVariable Long id) {
        Optional<Dish> optionalDish = dishService.deleteDish(id);
        if (optionalDish.isPresent()) {
            return ResponseHandler.generateResponse("Deleted", HttpStatus.OK, optionalDish.get());
        }else {
            return ResponseHandler.generateResponse("Dish with given id does not exist", HttpStatus.CREATED, null);
        }
    }
}
