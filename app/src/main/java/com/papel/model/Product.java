package com.papel.model;

public class Product {

    private Integer id;
    private String name="";
    private String description="";
    private Double price=0.0;
    private Integer quantity=0;

    public Product(String name, String description,Double price, Integer quantity){
        this.name=name;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
    }
    public Product(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
