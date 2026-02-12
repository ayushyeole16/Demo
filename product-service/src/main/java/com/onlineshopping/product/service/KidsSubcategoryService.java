package com.onlineshopping.product.service;

import com.onlineshopping.product.model.KidsSubcategory;
import com.onlineshopping.product.repository.KidsSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KidsSubcategoryService {

    @Autowired
    private KidsSubcategoryRepository kidsSubcategoryRepository;

    public List<KidsSubcategory> getAllKidsSubcategories() {
        return kidsSubcategoryRepository.findAll();
    }

    public Optional<KidsSubcategory> getKidsSubcategoryById(Long id) {
        return kidsSubcategoryRepository.findById(id);
    }

    public KidsSubcategory saveKidsSubcategory(KidsSubcategory kidsSubcategory) {
        return kidsSubcategoryRepository.save(kidsSubcategory);
    }

    public void deleteKidsSubcategory(Long id) {
        kidsSubcategoryRepository.deleteById(id);
    }
}
