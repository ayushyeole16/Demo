package com.onlineshopping.product.service;

import com.onlineshopping.product.model.WomenSubcategory;
import com.onlineshopping.product.repository.WomenSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WomenSubcategoryService {

    @Autowired
    private WomenSubcategoryRepository womenSubcategoryRepository;

    public List<WomenSubcategory> getAllWomenSubcategories() {
        return womenSubcategoryRepository.findAll();
    }

    public Optional<WomenSubcategory> getWomenSubcategoryById(Long id) {
        return womenSubcategoryRepository.findById(id);
    }

    public WomenSubcategory saveWomenSubcategory(WomenSubcategory womenSubcategory) {
        return womenSubcategoryRepository.save(womenSubcategory);
    }

    public void deleteWomenSubcategory(Long id) {
        womenSubcategoryRepository.deleteById(id);
    }
}
