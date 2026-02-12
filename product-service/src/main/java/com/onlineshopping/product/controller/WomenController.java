package com.onlineshopping.product.controller;

import com.onlineshopping.product.model.Women;
import com.onlineshopping.product.service.WomenService;
import com.onlineshopping.product.dto.Productdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/women")
public class WomenController {

    @Autowired
    private WomenService womenService;

    @GetMapping
    public List<Women> getAllWomen() {
        return womenService.getAllWomen();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Women> getWomenById(@PathVariable Long id) {
        Optional<Women> women = womenService.getWomenById(id);
        return women.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Women createWomen(@RequestBody Productdto productdto) {
        Women women = new Women();
        women.setName(productdto.getName());
        women.setDescription(productdto.getDescription());
        women.setPrice(BigDecimal.valueOf(productdto.getPrice()));
        women.setStock(productdto.getStock());
        women.setCategory(productdto.getCategory());
        women.setSubCategory(productdto.getSubCategory());
        women.setImg(productdto.getImg());
        return womenService.saveWomen(women);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Women> updateWomen(@PathVariable Long id, @RequestBody Women womenDetails) {
        Optional<Women> womenOptional = womenService.getWomenById(id);
        if (!womenOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Women women = womenOptional.get();
        women.setName(womenDetails.getName());
        women.setDescription(womenDetails.getDescription());
        women.setPrice(womenDetails.getPrice());
        women.setStock(womenDetails.getStock());
        women.setCategory(womenDetails.getCategory());
        women.setSubCategory(womenDetails.getSubCategory());
        women.setImg(womenDetails.getImg());
        Women updatedWomen = womenService.saveWomen(women);
        return ResponseEntity.ok(updatedWomen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWomen(@PathVariable Long id) {
        womenService.deleteWomen(id);
        return ResponseEntity.noContent().build();
    }
}
