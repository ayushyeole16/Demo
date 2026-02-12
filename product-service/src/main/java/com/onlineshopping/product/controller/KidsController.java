package com.onlineshopping.product.controller;

import com.onlineshopping.product.model.Kids;
import com.onlineshopping.product.service.KidsService;
import com.onlineshopping.product.dto.Productdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kids")
public class KidsController {

    @Autowired
    private KidsService kidsService;

    @GetMapping
    public List<Kids> getAllKids() {
        return kidsService.getAllKids();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kids> getKidsById(@PathVariable Long id) {
        Optional<Kids> kids = kidsService.getKidsById(id);
        return kids.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kids createKids(@RequestBody Productdto productdto) {
        Kids kids = new Kids();
        kids.setName(productdto.getName());
        kids.setDescription(productdto.getDescription());
        kids.setPrice(BigDecimal.valueOf(productdto.getPrice()));
        kids.setStock(productdto.getStock());
        kids.setCategory(productdto.getCategory());
        kids.setSubCategory(productdto.getSubCategory());
        kids.setImg(productdto.getImg());
        return kidsService.saveKids(kids);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kids> updateKids(@PathVariable Long id, @RequestBody Kids kidsDetails) {
        Optional<Kids> kidsOptional = kidsService.getKidsById(id);
        if (!kidsOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Kids kids = kidsOptional.get();
        kids.setName(kidsDetails.getName());
        kids.setDescription(kidsDetails.getDescription());
        kids.setPrice(kidsDetails.getPrice());
        kids.setStock(kidsDetails.getStock());
        kids.setCategory(kidsDetails.getCategory());
        kids.setSubCategory(kidsDetails.getSubCategory());
        kids.setImg(kidsDetails.getImg());
        Kids updatedKids = kidsService.saveKids(kids);
        return ResponseEntity.ok(updatedKids);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKids(@PathVariable Long id) {
        kidsService.deleteKids(id);
        return ResponseEntity.noContent().build();
    }
}
