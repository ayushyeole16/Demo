package com.onlineshopping.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Productdto {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;

    @JsonProperty("sub_category")
    private String subCategory;

    private String img;

    // Default constructor
    public Productdto() {}

    // Parameterized constructor
    public Productdto(String name, String description, Double price, Integer stock, String category, String subCategory, String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.subCategory = subCategory;
        this.img = img;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

