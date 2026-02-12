package com.onlineshopping.product.service;

import com.onlineshopping.product.model.Men;
import com.onlineshopping.product.repository.MenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenService {

    @Autowired
    private MenRepository menRepository;

    public List<Men> getAllMen() {
        return menRepository.findAll();
    }

    public Optional<Men> getMenById(Long id) {
        return menRepository.findById(id);
    }

    public Men saveMen(Men men) {
        return menRepository.save(men);
    }

    public void deleteMen(Long id) {
        menRepository.deleteById(id);
    }
}
