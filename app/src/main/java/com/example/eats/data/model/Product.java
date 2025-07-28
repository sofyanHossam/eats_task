package com.example.eats.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private double price;

    private String category;

    private String url_image;

    private String url_restaurant_logo;

    // Constructor
    public Product(@NonNull String name, double price, String category, String url_image, String url_restaurant_logo) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.url_image = url_image;
        this.url_restaurant_logo = url_restaurant_logo;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }


    public String getUrl_restaurant_logo() {
        return url_restaurant_logo;
    }

    public void setUrl_restaurant_logo(String url_restaurant_logo) {
        this.url_restaurant_logo = url_restaurant_logo;
    }
}
