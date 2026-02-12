
package com.onlineshopping.product.controller;

import com.onlineshopping.product.model.KidsSubcategory;
import com.onlineshopping.product.service.KidsSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kids-subcategories")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class KidsSubcategoryController {

    @Autowired
    private KidsSubcategoryService kidsSubcategoryService;

    @GetMapping
    public List<KidsSubcategory> getAllKidsSubcategories() {
        return kidsSubcategoryService.getAllKidsSubcategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KidsSubcategory> getKidsSubcategoryById(@PathVariable Long id) {
        Optional<KidsSubcategory> kidsSubcategory = kidsSubcategoryService.getKidsSubcategoryById(id);
        return kidsSubcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public KidsSubcategory createKidsSubcategory(@RequestBody KidsSubcategory kidsSubcategory) {
        return kidsSubcategoryService.saveKidsSubcategory(kidsSubcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KidsSubcategory> updateKidsSubcategory(@PathVariable Long id, @RequestBody KidsSubcategory kidsSubcategoryDetails) {
        Optional<KidsSubcategory> kidsSubcategoryOptional = kidsSubcategoryService.getKidsSubcategoryById(id);
        if (!kidsSubcategoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        KidsSubcategory kidsSubcategory = kidsSubcategoryOptional.get();
        kidsSubcategory.setName(kidsSubcategoryDetails.getName());
        kidsSubcategory.setKids(kidsSubcategoryDetails.getKids());
        KidsSubcategory updatedKidsSubcategory = kidsSubcategoryService.saveKidsSubcategory(kidsSubcategory);
        return ResponseEntity.ok(updatedKidsSubcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKidsSubcategory(@PathVariable Long id) {
        kidsSubcategoryService.deleteKidsSubcategory(id);
        return ResponseEntity.noContent().build();
    }
}
