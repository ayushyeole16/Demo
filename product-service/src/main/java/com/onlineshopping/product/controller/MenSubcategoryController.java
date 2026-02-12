package com.onlineshopping.product.controller;

import com.onlineshopping.product.model.MenSubcategory;
import com.onlineshopping.product.service.MenSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/men-subcategories")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MenSubcategoryController {

    @Autowired
    private MenSubcategoryService menSubcategoryService;

    @GetMapping
    public List<MenSubcategory> getAllMenSubcategories() {
        return menSubcategoryService.getAllMenSubcategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenSubcategory> getMenSubcategoryById(@PathVariable Long id) {
        Optional<MenSubcategory> menSubcategory = menSubcategoryService.getMenSubcategoryById(id);
        return menSubcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenSubcategory createMenSubcategory(@RequestBody MenSubcategory menSubcategory) {
        return menSubcategoryService.saveMenSubcategory(menSubcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenSubcategory> updateMenSubcategory(@PathVariable Long id, @RequestBody MenSubcategory menSubcategoryDetails) {
        Optional<MenSubcategory> menSubcategoryOptional = menSubcategoryService.getMenSubcategoryById(id);
        if (!menSubcategoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MenSubcategory menSubcategory = menSubcategoryOptional.get();
        menSubcategory.setName(menSubcategoryDetails.getName());
        menSubcategory.setMen(menSubcategoryDetails.getMen());
        MenSubcategory updatedMenSubcategory = menSubcategoryService.saveMenSubcategory(menSubcategory);
        return ResponseEntity.ok(updatedMenSubcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenSubcategory(@PathVariable Long id) {
        menSubcategoryService.deleteMenSubcategory(id);
        return ResponseEntity.noContent().build();
    }
}
