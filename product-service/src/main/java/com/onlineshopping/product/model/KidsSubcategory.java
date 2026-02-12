package com.onlineshopping.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kids_subcategory")
public class KidsSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kids_id")
    private Kids kids;

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

    public Kids getKids() {
        return kids;
    }

    public void setKids(Kids kids) {
        this.kids = kids;
    }
}
