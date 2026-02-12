package com.onlineshopping.product.config;
 
public class ProductConfig {
 
    private String configName;
 
    // Constructors
    public ProductConfig() {}
 
    public ProductConfig(String configName) {
        this.configName = configName;
    }
 
    // Getters and Setters
    public String getConfigName() {
        return configName;
    }
 
    public void setConfigName(String configName) {
        this.configName = configName;
    }
}