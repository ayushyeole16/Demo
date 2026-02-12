package com.onlineshopping.product.controller;

import com.onlineshopping.product.model.Men;
import com.onlineshopping.product.service.MenService;
import com.onlineshopping.product.dto.Productdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/men")
public class MenController {

    @Autowired
    private MenService menService;

    @GetMapping
    public List<Men> getAllMen() {
        return menService.getAllMen();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Men> getMenById(@PathVariable Long id) {
        Optional<Men> men = menService.getMenById(id);
        return men.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Men createMen(@RequestBody Productdto productdto) {
        Men men = new Men();
        men.setName(productdto.getName());
        men.setDescription(productdto.getDescription());
        men.setPrice(BigDecimal.valueOf(productdto.getPrice()));
        men.setStock(productdto.getStock());
        men.setCategory(productdto.getCategory());
        men.setSubCategory(productdto.getSubCategory());
        men.setImg(productdto.getImg());
        return menService.saveMen(men);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Men> updateMen(@PathVariable Long id, @RequestBody Men menDetails) {
        Optional<Men> menOptional = menService.getMenById(id);
        if (!menOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Men men = menOptional.get();
        men.setName(menDetails.getName());
        men.setDescription(menDetails.getDescription());
        men.setPrice(menDetails.getPrice());
        men.setStock(menDetails.getStock());
        men.setCategory(menDetails.getCategory());
        men.setSubCategory(menDetails.getSubCategory());
        men.setImg(menDetails.getImg());
        Men updatedMen = menService.saveMen(men);
        return ResponseEntity.ok(updatedMen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMen(@PathVariable Long id) {
        menService.deleteMen(id);
        return ResponseEntity.noContent().build();
    }
}
