package com.example.eats.view.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.eats.data.model.Product;
import com.example.eats.data.repo.ProductRepoImpl;
import com.example.eats.domain.ProductRepo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepo productRepo;
    private final MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepo = new ProductRepoImpl(application.getApplicationContext());
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return productRepo.getAllProducts();
    }

    public void insertProduct(Product product) {
        executorService.execute(() -> {
            productRepo.insertProduct(product);
        });
    }

}

