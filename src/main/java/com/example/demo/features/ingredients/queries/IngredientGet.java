package com.example.demo.features.ingredients.queries;


import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.Get;
import com.example.demo.domain.Ingredient;

@Configuration
public class IngredientGet {
    public record Response(UUID id,String name,Double cost){

    }
    @RestController
    public class Controller{
        private final Get<Ingredient,UUID> repository;
        public Controller(final Get<Ingredient,UUID> repository){
            this.repository = repository;
        }
        @GetMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id){            
            
            var ingredient = repository.get(id);

            var response = new Response(
                ingredient.getId(),
                ingredient.getName(), 
                ingredient.getCost());

            return ResponseEntity.ok(response);
            
        }

    }
}
