package com.onlineshopping.product.service;

import com.onlineshopping.product.model.Kids;
import com.onlineshopping.product.repository.KidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KidsService {

    @Autowired
    private KidsRepository kidsRepository;

    public List<Kids> getAllKids() {
        return kidsRepository.findAll();
    }

    public Optional<Kids> getKidsById(Long id) {
        return kidsRepository.findById(id);
    }

    public Kids saveKids(Kids kids) {
        return kidsRepository.save(kids);
    }

    public void deleteKids(Long id) {
        kidsRepository.deleteById(id);
    }
}
