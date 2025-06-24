package com.example.demo.features.ingredients.queries;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infraestructure.IngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Configuration
public class IngredientQuery {
    public record Response(UUID id,String name,Double cost) {
    }
    @RestController
    public class Controller{
        private final IngredientRepository repository;
        public Controller(final IngredientRepository repository){
            this.repository = repository;
        }
        @GetMapping("/ingredients")
        public ResponseEntity<?> hadlder(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "1") int size
        ) {
            var result = repository
                .findByCriteria(name, PageRequest.of(page, size))
                .stream().map(i->new Response(i.getId(), i.getName(), i.getCost()))
                .toList();
            return ResponseEntity.ok().body(result);
        }
        
    }
}
