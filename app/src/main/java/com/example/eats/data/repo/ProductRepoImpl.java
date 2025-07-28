package com.example.eats.data.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.eats.data.local.AppDatabase;
import com.example.eats.data.local.ProductDao;
import com.example.eats.data.model.Product;
import com.example.eats.domain.ProductRepo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepoImpl implements ProductRepo {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public ProductRepoImpl(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        productDao = db.productDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void insertProduct(Product product) {
        executorService.execute(() -> productDao.insertProduct(product));
    }

    @Override
    public LiveData<List<Product>> getAllProducts() {
        // Room does not allow DB queries on main thread by default.
        // For simplicity, this blocks the thread; consider LiveData or async in real apps.
        return productDao.getAllProducts();
    }
}

