package com.onlineshopping.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "men_subcategory")
public class MenSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "men_id")
    private Men men;

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

    public Men getMen() {
        return men;
    }

    public void setMen(Men men) {
        this.men = men;
    }
}
