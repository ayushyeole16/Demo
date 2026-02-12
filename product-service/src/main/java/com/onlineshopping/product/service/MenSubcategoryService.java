package com.onlineshopping.product.service;

import com.onlineshopping.product.model.MenSubcategory;
import com.onlineshopping.product.repository.MenSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenSubcategoryService {

    @Autowired
    private MenSubcategoryRepository menSubcategoryRepository;

    public List<MenSubcategory> getAllMenSubcategories() {
        return menSubcategoryRepository.findAll();
    }

    public Optional<MenSubcategory> getMenSubcategoryById(Long id) {
        return menSubcategoryRepository.findById(id);
    }

    public MenSubcategory saveMenSubcategory(MenSubcategory menSubcategory) {
        return menSubcategoryRepository.save(menSubcategory);
    }

    public void deleteMenSubcategory(Long id) {
        menSubcategoryRepository.deleteById(id);
    }
}
