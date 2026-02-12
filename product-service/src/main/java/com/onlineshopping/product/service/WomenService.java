package com.onlineshopping.product.service;

import com.onlineshopping.product.model.Women;
import com.onlineshopping.product.repository.WomenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WomenService {

    @Autowired
    private WomenRepository womenRepository;

    public List<Women> getAllWomen() {
        return womenRepository.findAll();
    }

    public Optional<Women> getWomenById(Long id) {
        return womenRepository.findById(id);
    }

    public Women saveWomen(Women women) {
        return womenRepository.save(women);
    }

    public void deleteWomen(Long id) {
        womenRepository.deleteById(id);
    }
}
