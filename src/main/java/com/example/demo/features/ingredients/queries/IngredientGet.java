package com.example.demo.features.ingredients.queries;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import com.example.demo.infraestructure.IngredientRepository;

@Configuration
public class IngredientGet {
    public record Response(UUID id,String name,Double cost){

    }
    @RestController
    public class Controller{
        private final IngredientRepository repository;
        public Controller(final IngredientRepository repository){
            this.repository = repository;
        }
        @GetMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id){
            Optional<Ingredient> ingredient = repository.findById(id); 
            if(ingredient.isPresent()){
                var entity = ingredient.get();
                var response = new Response(entity.getId(),entity.getName(), entity.getCost());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(404).body(null);
        }

    }
}
