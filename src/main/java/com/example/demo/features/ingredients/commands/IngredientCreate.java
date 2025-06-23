package com.example.demo.features.ingredients.commands;

import java.util.UUID;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import com.example.demo.infraestructure.IngredientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class IngredientCreate {
    public record  Request(String name,Double cost) {
    }
    public record  Response(UUID id,String name,Double cost) {
    }
    @RestController
    public class Controller{
        private final IngredientRepository repository;
        public Controller(final IngredientRepository repository){
            this.repository = repository;
        }
        @PostMapping("/ingredients")      
        public ResponseEntity<?> handler(@RequestBody Request request){
            var ingredient = Ingredient.create(
                    UUID.randomUUID(), request.name(), request.cost());
            
            repository.save(ingredient);

            var response =new Response(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCost()
            );
            return ResponseEntity.status(201).body(response);

        }
    }
}
