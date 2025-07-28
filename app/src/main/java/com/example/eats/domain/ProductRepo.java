package com.example.eats.domain;


import androidx.lifecycle.LiveData;

import com.example.eats.data.model.Product;

import java.util.List;

public interface ProductRepo {
    void insertProduct(Product product);
    LiveData<List<Product>> getAllProducts();
}
