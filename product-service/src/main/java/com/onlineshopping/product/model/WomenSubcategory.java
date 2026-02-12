package com.onlineshopping.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "women_subcategory")
public class WomenSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "women_id")
    private Women women;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Women getWomen() {
        return women;
    }

    public void setWomen(Women women) {
        this.women = women;
    }
}
