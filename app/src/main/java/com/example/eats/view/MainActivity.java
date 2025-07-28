package com.example.eats.view;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eats.R;
import com.example.eats.data.model.Product;
import com.example.eats.view.adapter.ProductAdapter;
import com.example.eats.view.viewmodel.ProductViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Views
    private MaterialToolbar toolbar;
    private LinearLayout filterContainer;
    private RecyclerView recyclerView;

    // Adapters & ViewModels
    private ProductAdapter adapter;
    private ProductViewModel viewModel;

    // Filter chips
    private final List<Chip> chipList = new ArrayList<>();
    private Chip selectedChip = null;
    private final String[] filters = {
            "أجبان", "أجبان قابلة للدهن", "مستورد", "أفضل العروض"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSystemUI();
        initViews();
        setupToolbar();
        setupFilters();
        setupRecyclerView();
        setupViewModel();

        addDummyProductsIfFirstTime(); // إدخال الداتا الوهمية أول مرة فقط
    }

    /**
     * Adjust status bar appearance.
     */
    private void setupSystemUI() {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primaryColor));

        // Icons should be dark if status bar background is light
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * Initialize views from layout.
     */
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        filterContainer = findViewById(R.id.filterContainer);
        recyclerView = findViewById(R.id.productRecyclerView);
    }

    /**
     * Set toolbar click behavior.
     */
    private void setupToolbar() {
        toolbar.setOnClickListener(v ->
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnCart).setOnClickListener(v ->
                Toast.makeText(this, "عرض السلة", Toast.LENGTH_SHORT).show());

        findViewById(R.id.cartPrice).setOnClickListener(v ->
                Toast.makeText(this, "price : 23.85", Toast.LENGTH_SHORT).show());
    }

    /**
     * Setup filter chips dynamically.
     */
    private void setupFilters() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String filter : filters) {
            Chip chip = (Chip) inflater.inflate(R.layout.filter_chip, filterContainer, false);
            chip.setText(filter);
            chip.setChecked(false);
            chip.setChipIconVisible(false);

            chip.setOnClickListener(v -> onFilterChipClicked(chip));

            filterContainer.addView(chip);
            chipList.add(chip);
        }
    }

    /**
     * Handle chip selection logic.
     */
    private void onFilterChipClicked(Chip chip) {
        if (chip == selectedChip) {
            chip.setChecked(false);
            chip.setChipIconVisible(false);
            selectedChip = null;
        } else {
            if (selectedChip != null) {
                selectedChip.setChecked(false);
                selectedChip.setChipIconVisible(false);
            }
            chip.setChecked(true);
            chip.setChipIconVisible(true);
            selectedChip = chip;
        }
    }

    /**
     * Configure RecyclerView with adapter and layout manager.
     */
    private void setupRecyclerView() {
        adapter = new ProductAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Initialize ViewModel and observe LiveData.
     */
    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        viewModel.getProductsLiveData().observe(this, products -> {
            adapter.setProducts(products);
        });
    }

    /**
     * Add dummy products to database only the first time app is launched.
     */
    private void addDummyProductsIfFirstTime() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean isInserted = prefs.getBoolean("dummy_data_inserted", false);

        if (isInserted) return;

        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(new Product("Pizza Margherita", 18.99, "Pizza",
                "https://t3.ftcdn.net/jpg/06/27/23/56/360_F_627235669_iz0O2leKYRzjxAKdFP7odpp9eCOZREtN.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTok7rpa2oCOiSqd-CnOdsAOOe5lFh-rISM9w&s"));

        dummyProducts.add(new Product("Cheeseburger", 26.49, "Burger",
                "https://static.vecteezy.com/system/resources/previews/022/911/694/non_2x/cute-cartoon-burger-icon-free-png.png",
                "https://static.vecteezy.com/system/resources/thumbnails/019/909/468/small/burger-king-transparent-burger-king-free-free-png.png"));

        dummyProducts.add(new Product("Sushi Combo", 12.99, "Sushi",
                "https://static.vecteezy.com/system/resources/previews/022/911/694/non_2x/cute-cartoon-burger-icon-free-png.png",
                "https://static.vecteezy.com/system/resources/thumbnails/019/909/468/small/burger-king-transparent-burger-king-free-free-png.png"));

        dummyProducts.add(new Product("Tacos", 59.99, "Mexican",
                "https://t3.ftcdn.net/jpg/06/27/23/56/360_F_627235669_iz0O2leKYRzjxAKdFP7odpp9eCOZREtN.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTok7rpa2oCOiSqd-CnOdsAOOe5lFh-rISM9w&s"));

        dummyProducts.add(new Product("Fried Chicken", 78.49, "Chicken",
                "https://static.vecteezy.com/system/resources/previews/022/911/694/non_2x/cute-cartoon-burger-icon-free-png.png",
                "https://static.vecteezy.com/system/resources/thumbnails/019/909/468/small/burger-king-transparent-burger-king-free-free-png.png"));

        dummyProducts.add(new Product("Pasta Carbonara", 99.49, "Pasta",
                "https://t3.ftcdn.net/jpg/06/27/23/56/360_F_627235669_iz0O2leKYRzjxAKdFP7odpp9eCOZREtN.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTok7rpa2oCOiSqd-CnOdsAOOe5lFh-rISM9w&s"));

        for (Product product : dummyProducts) {
            viewModel.insertProduct(product);
        }

        prefs.edit().putBoolean("dummy_data_inserted", true).apply();
    }
}
