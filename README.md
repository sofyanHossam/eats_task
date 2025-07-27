# 🛒 Simple Product Grid Demo - Android MVVM (Java + Room)

This project is a single-screen Android demo app built using **Java**, following the **MVVM Clean Architecture pattern**.  
It loads a list of products from a local **Room Database** and displays them in a **2-column Grid RecyclerView**.

## 📦 Screenshot 

![imgonline-com-ua-twotoone-me8O7ITjFmN](https://github.com/user-attachments/assets/963dbb96-3de3-4118-a9f7-3b01aba648f4)

---

## 📦 Features

- 🔄 **MVVM Clean Architecture**:
  - **Data layer** (Room DB, DAO, Models, Repository Implementation)
  - **Domain layer** (Repository Interface)
  - **View layer** (ViewModel, Activity, Adapter)

- 🗂 **Room Database** with `ProductDao` and `ProductModel`

- 📊 **LiveData**: Observes database changes and updates UI automatically.

- 🖼 **Picasso**: For loading and displaying product images.

- 💾 **SharedPreferences**: Used to insert dummy data only once (first app launch) to avoid empty lists.

- 🔵 **CircularImageView**: Product images are displayed in rounded/circle shape.

- 🔄 **Manual threading**: A separate thread is used for Room DB operations to avoid blocking the UI.

- 🖱 **UI**:
  - Products displayed in `RecyclerView` with **GridLayoutManager (2 columns)**
  - Each item is **tappable**, showing a **Toast** with the product name

- 🤖 **Proxi AI Plugin** integrated throughout the app  
  (More usages will be documented soon...)

---

## 📁 Project Structure

```plaintext
├── data/
│   ├── local/
│   │   ├── ProductDao.java
│   │   ├── ProductDatabase.java
│   ├── model/
│   │   └── ProductModel.java
│   └── repository/
│       └── ProductRepositoryImpl.java

├── domain/
│   └── repository/
│       └── ProductRepository.java

├── view/
│   ├── adapter/
│   │   └── ProductAdapter.java
│   ├── viewmodel/
│   │   └── ProductViewModel.java
│   └── MainActivity.java

```

# Proxi AI Chat :


## Main Layout

**Me:**  
I'm building a category screen for my food app. I want an XML layout that contains:  
- A top `MaterialToolbar` (RTL, title in center, search icon)  
- A horizontal filter bar  
- A `RecyclerView` grid (2 columns) to show product cards  
- A floating cart bar at the bottom with total price and a button to go to cart  
Can you generate the layout file?

---

**ProxyAI:**  
Absolutely! Here's a complete `ConstraintLayout` XML with the components you mentioned:

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Toolbar -->
    <com.google.android.material.appbar.MaterialToolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layoutDirection="rtl"
        android:background="@color/primaryColor"
        app:title="التصنيفات"
        app:titleCentered="true"
        app:navigationIcon="@drawable/baseline_search_24"
        app:titleTextColor="@color/dark_blue"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Filter Scroll -->
    <HorizontalScrollView
        android:id="@+id/filterScroll"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        app:layout_constraintTop_toBottomOf="@id/toolbar"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <LinearLayout
            android:id="@+id/filterContainer"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layoutDirection="rtl"
            android:orientation="horizontal"
            android:gravity="center_vertical"/>
    </HorizontalScrollView>

    <!-- Product RecyclerView -->
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/productRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:padding="8dp"
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        tools:listitem="@layout/item_product"
        app:spanCount="2"
        app:layout_constraintTop_toBottomOf="@id/filterScroll"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Cart Bar -->
    <com.google.android.material.card.MaterialCardView
        android:id="@+id/cartBar"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:layout_margin="16dp"
        android:backgroundTint="@color/light_blue"
        app:cardCornerRadius="24dp"
        app:cardElevation="6dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingHorizontal="16dp">

            <TextView
                android:id="@+id/btnCart"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:drawableStart="@drawable/baseline_arrow_back_ios_24"
                android:text="عرض السلة"
                android:textSize="16sp"
                android:textStyle="bold"
                android:textColor="@android:color/white"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>

            <TextView
                android:id="@+id/cartPrice"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="23.85"
                android:textSize="20sp"
                android:textStyle="bold"
                android:textColor="@android:color/white"
                app:layout_constraintEnd_toStartOf="@id/cartCur"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>

            <TextView
                android:id="@+id/cartCur"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="SAR"
                android:textSize="14sp"
                android:textStyle="bold"
                android:textColor="@android:color/white"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>
        </androidx.constraintlayout.widget.ConstraintLayout>
    </com.google.android.material.card.MaterialCardView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

# 🧠 Prompt 2: Product Card Design – AI Conversation Log

### 🎯 Goal:
Design a product card to be displayed in a grid. The card should include:
- A circular product image.
- A small logo overlayed in the top-right corner.
- Product name (with ellipsis if too long).
- Price and quantity info at the bottom.

---

### 👤 Me:
I forgot to mention — each product in the grid should look nice.  
I want a card with:  
- Circular product image  
- A small top-right logo  
- Product name with ellipsis  
- Price + quantity count  

---

### 🤖 AI:
Got it! Here's the XML for an `item_product.xml` that meets those needs.

```xml
<!-- item_product.xml -->
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardCornerRadius="16dp"
    app:cardElevation="6dp">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="12dp">

        <!-- Product Image with circular shape -->
        <ImageView
            android:id="@+id/productImage"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_centerHorizontal="true"
            android:scaleType="centerCrop"
            android:layout_marginBottom="8dp"
            android:background="@drawable/circle_background"
            android:clipToOutline="true"
            android:contentDescription="Product Image" />

        <!-- Top-right logo overlay -->
        <ImageView
            android:id="@+id/logoOverlay"
            android:layout_width="24dp"
            android:layout_height="24dp"
            android:layout_alignTop="@id/productImage"
            android:layout_alignEnd="@id/productImage"
            android:src="@drawable/ic_logo"
            android:background="@drawable/circle_white_bg"
            android:padding="2dp"
            android:elevation="4dp"
            android:contentDescription="Logo" />

        <!-- Product name -->
        <TextView
            android:id="@+id/productName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/productImage"
            android:layout_marginTop="8dp"
            android:text="Product Name"
            android:textSize="16sp"
            android:textColor="#000"
            android:maxLines="1"
            android:ellipsize="end"
            android:gravity="center" />

        <!-- Price and Quantity Row -->
        <LinearLayout
            android:id="@+id/priceQtyRow"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/productName"
            android:layout_marginTop="4dp"
            android:orientation="horizontal"
            android:gravity="center"
            android:weightSum="2">

            <TextView
                android:id="@+id/productPrice"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:text="$19.99"
                android:textSize="14sp"
                android:textColor="#4CAF50"
                android:gravity="start" />

            <TextView
                android:id="@+id/productQty"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:text="x3"
                android:textSize="14sp"
                android:textColor="#757575"
                android:gravity="end" />
        </LinearLayout>

    </RelativeLayout>
</androidx.cardview.widget.CardView>
```
### User:
I'm building an Android app using Java and XML. I want to add a horizontal filter chips section like the one in this image named menu_filter:

Here's what I need:

A horizontal scrollable row of chips inside a HorizontalScrollView, each chip should be:

Checkable

Have wrap_content width

When selected: light blue background, white check icon on the left, and primary color text.

When unselected: gray background with no check icon.

I want to manage the selected chip state in MainActivity.java, and make sure only one chip is selected at a time.

Create:

A separate XML layout for a single chip (e.g., filter_chip.xml)

Color selector files for chip background and text

A drawable vector for the white check icon (if needed)

Finally, provide me with the full Java code to inflate and update the chip selection in the activity.

Please return the full working code as explained above based on mainActivity and activity_main.xml
### CodeGPT:
To implement the horizontal filter chips as described, I'll provide:

1. `filter_chip.xml` — layout for a single chip with a CheckableFrameLayout or MaterialChip.
2. Color selector XML files for chip background and text color.
3. A vector drawable for the white check icon.
4. Updated `MainActivity.java` code to inflate chips dynamically, manage single selection, and update UI accordingly.

---

### 1. `filter_chip.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.chip.Chip xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:checkable="true"
    android:clickable="true"
    android:paddingStart="12dp"
    android:paddingEnd="12dp"
    android:textColor="@color/chip_text_color_selector"
    android:textSize="14sp"
    android:textStyle="bold"
    app:chipBackgroundColor="@color/chip_background_selector"
    app:chipIcon="@drawable/ic_check_white_24dp"
    app:chipIconTint="@color/white"
    app:chipIconVisible="false"
    app:chipIconEnabled="true"
    app:closeIconEnabled="false"
    app:chipCornerRadius="16dp"
    />
```

- Using MaterialChip for built-in checkable behavior.
- `chipIconVisible` will be toggled programmatically to show/hide the check icon.

---

### 2. Color selector files

**`res/color/chip_background_selector.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/light_blue" android:state_checked="true" />
    <item android:color="@color/gray_light" />
</selector>
```

**`res/color/chip_text_color_selector.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/primaryColor" android:state_checked="true" />
    <item android:color="@color/gray_dark" />
</selector>
```

---

### 3. Vector drawable for white check icon

**`res/drawable/ic_check_white_24dp.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z"/>
</vector>
```

---

### 4. Updated `MainActivity.java`

```java
package com.example.eats;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout filterContainer;
    private List<Chip> chipList = new ArrayList<>();
    private Chip selectedChip = null;

    // Sample filter names (Arabic text from your image)
    private final String[] filters = {
            "أجوان",
            "أجبان قابلة للدهن",
            "مستورد",
            "أفضل العروض"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         filterContainer = findViewById(R.id.filterContainer);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (String filter : filters) {
            final Chip chip = (Chip) inflater.inflate(R.layout.filter_chip, filterContainer, false);
            chip.setText(filter);
            chip.setChecked(false);
            chip.setChipIconVisible(false);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chip == selectedChip) {
                        // Optional: allow deselect or keep selected
                        return;
                    }
                    if (selectedChip != null) {
                        selectedChip.setChecked(false);
                        selectedChip.setChipIconVisible(false);
                    }
                    chip.setChecked(true);
                    chip.setChipIconVisible(true);
                    selectedChip = chip;
                }
            });

            filterContainer.addView(chip);
            chipList.add(chip);
        }
    }
}
```

---

### Explanation

- `filter_chip.xml` defines a MaterialChip with checkable behavior and icon support.
- Color selectors control background and text color based on checked state.
- The white check icon is a vector drawable shown only when chip is selected.
- In `MainActivity`, chips are inflated dynamically from `filter_chip.xml`.
- Only one chip can be selected at a time; clicking a chip updates selection and UI accordingly.
- The check icon visibility is toggled programmatically to meet your requirement.

This setup matches your UI image and requirements for a horizontal scrollable filter chips section with single selection behavior.
### User:
make some edits on chips based on my color values :
first about the text if not selected : it will be @dark_blue and if selected it will be light_blue 
and the background color if selected : primaryColor if not => light_gray and no need for chip text color and bg color files 
also edit the check color to dark_blue  

```xml:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/res/values/colors.xml
primaryColor
```
### CodeGPT:
Based on your updated color requirements and existing colors in `colors.xml`, here are the changes:

- Chip text color:  
  - Selected: `@color/light_blue`  
  - Unselected: `@color/dark_blue`  
- Chip background color:  
  - Selected: `@color/primaryColor`  
  - Unselected: `@color/light_gray`  
- Check icon tint color: `@color/dark_blue`  
- No need for separate selector XML files for text or background colors; handle colors programmatically in Java.

---

### Updated `filter_chip.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.chip.Chip xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/chip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:checkable="true"
    android:clickable="true"
    android:paddingStart="12dp"
    android:paddingEnd="12dp"
    android:textSize="14sp"
    android:textStyle="bold"
    app:chipCornerRadius="16dp"
    app:chipIcon="@drawable/ic_check_white_24dp"
    app:chipIconEnabled="true"
    app:chipIconVisible="false"
    app:closeIconEnabled="false" />
```

- Removed `chipBackgroundColor` and `textColor` attributes to set them dynamically.

---

### Updated `MainActivity.java`

```java
package com.example.eats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout filterContainer;
    private List<Chip> chipList = new ArrayList<>();
    private Chip selectedChip = null;

    private final String[] filters = {
            "أجوان",
            "أجبان قابلة للدهن",
            "مستورد",
            "أفضل العروض"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filterContainer = findViewById(R.id.filterContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String filter : filters) {
            final Chip chip = (Chip) inflater.inflate(R.layout.filter_chip, filterContainer, false);
            chip.setText(filter);
            setChipUnselectedStyle(chip);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chip == selectedChip) {
                        // Optional: allow deselect or keep selected
                        return;
                    }
                    if (selectedChip != null) {
                        setChipUnselectedStyle(selectedChip);
                    }
                    setChipSelectedStyle(chip);
                    selectedChip = chip;
                }
            });

            filterContainer.addView(chip);
            chipList.add(chip);
        }
    }

    private void setChipSelectedStyle(Chip chip) {
        chip.setChecked(true);
        chip.setChipIconVisible(true);
        chip.setChipBackgroundColorResource(R.color.primaryColor);
        chip.setTextColor(getResources().getColor(R.color.light_blue));
        chip.setChipIconTintResource(R.color.dark_blue);
    }

    private void setChipUnselectedStyle(Chip chip) {
        chip.setChecked(false);
        chip.setChipIconVisible(false);
        chip.setChipBackgroundColorResource(R.color.light_gray);
        chip.setTextColor(getResources().getColor(R.color.dark_blue));
    }
}
```

---

### Explanation

- Removed XML color selectors and set colors dynamically in Java.
- When a chip is selected, background is `primaryColor`, text color is `light_blue`, and check icon tint is `dark_blue`.
- When unselected, background is `light_gray`, text color is `dark_blue`, and check icon is hidden.
- This matches your color scheme exactly using your existing colors.xml values.

Let me know if you want me to provide the updated vector drawable with `dark_blue` tint hardcoded, but setting tint programmatically is preferred.
### User:
instaed of making single selection make it multiple
### CodeGPT:
To allow multiple chip selections instead of single selection, you need to:

- Remove the logic that deselects the previously selected chip.
- Toggle the clicked chip's selected state independently.
- Update the chip's UI accordingly.

Here is the updated `MainActivity.java` reflecting multiple selection behavior:

```java
package com.example.eats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout filterContainer;
    private List<Chip> chipList = new ArrayList<>();

    private final String[] filters = {
            "أجوان",
            "أجبان قابلة للدهن",
            "مستورد",
            "أفضل العروض"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filterContainer = findViewById(R.id.filterContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String filter : filters) {
            final Chip chip = (Chip) inflater.inflate(R.layout.filter_chip, filterContainer, false);
            chip.setText(filter);
            setChipUnselectedStyle(chip);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chip.isChecked()) {
                        // Deselect chip
                        setChipUnselectedStyle(chip);
                    } else {
                        // Select chip
                        setChipSelectedStyle(chip);
                    }
                }
            });

            filterContainer.addView(chip);
            chipList.add(chip);
        }
    }

    private void setChipSelectedStyle(Chip chip) {
        chip.setChecked(true);
        chip.setChipIconVisible(true);
        chip.setChipBackgroundColorResource(R.color.primaryColor);
        chip.setTextColor(getResources().getColor(R.color.light_blue));
        chip.setChipIconTintResource(R.color.dark_blue);
    }

    private void setChipUnselectedStyle(Chip chip) {
        chip.setChecked(false);
        chip.setChipIconVisible(false);
        chip.setChipBackgroundColorResource(R.color.light_gray);
        chip.setTextColor(getResources().getColor(R.color.dark_blue));
    }
}
```

---

### Explanation

- Each chip toggles its own selected state on click.
- Multiple chips can be selected simultaneously.
- The UI updates independently for each chip based on its checked state.

This change fully supports multiple chip selection as requested.
### User:
make a simple java class model(@Entity) -anotate with entity to work with room-   that contains :
id ==> int ==> autocomplete == > primary key
name ==> string ==>not nullable 
price ==>double==>not nullable 
category == > String 
url_image ==>String
url_restaurant_logo ==> string
### CodeGPT:
Here is a simple Java model class annotated with Room's `@Entity` for your requirements:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/model/Product.java
package com.example.eats.model;

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
```

This class defines a Room entity named `products` with an auto-generated primary key `id`, non-null `name` and `price`, and other optional fields.
### User:
now inside data--> local 
make a dao that i need it just insert new product and show (get) all product
### CodeGPT:
Here is a simple DAO interface for Room with methods to insert a new product and get all products:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/data/local/ProductDao.java
package com.example.eats.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eats.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
}
```

This DAO provides:

- `insertProduct(Product product)` to insert a new product into the database.
- `getAllProducts()` to retrieve all products as a list.
### User:
good now AppDatabase.java and remember it should be singltone
### CodeGPT:
Here is a singleton `AppDatabase` class for Room that includes the `ProductDao`:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/data/local/AppDatabase.java
package com.example.eats.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.eats.model.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ProductDao productDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "eats_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
```

This class:

- Defines the Room database with `Product` entity.
- Provides a thread-safe singleton instance.
- Exposes the `ProductDao` for database operations.
### User:
now make a product repo for both functions get all and insert intefaces
### CodeGPT:
Here is a `ProductRepo` interface and its implementation class to provide `insert` and `getAll` functions using the Room DAO:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/domain/ProductRepo.java
package com.example.eats.domain;

import com.example.eats.model.Product;

import java.util.List;

public interface ProductRepo {
    void insertProduct(Product product);
    List<Product> getAllProducts();
}
```

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/data/repository/ProductRepoImpl.java
package com.example.eats.data.repository;

import android.content.Context;

import com.example.eats.data.local.AppDatabase;
import com.example.eats.data.local.ProductDao;
import com.example.eats.domain.ProductRepo;
import com.example.eats.model.Product;

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
    public List<Product> getAllProducts() {
        // Room does not allow DB queries on main thread by default.
        // For simplicity, this blocks the thread; consider LiveData or async in real apps.
        return productDao.getAllProducts();
    }
}
```

---

### Explanation

- `ProductRepo` interface declares the required methods.
- `ProductRepoImpl` implements the interface, uses `AppDatabase` singleton to get DAO.
- Insert runs asynchronously on a background thread.
- `getAllProducts()` runs synchronously here; in production, consider LiveData or RxJava for async data observation.
### User:
now create a viewmodel
### CodeGPT:
Here is a simple `ProductViewModel` class that uses the `ProductRepo` to insert and get all products. It exposes the product list as `LiveData` for UI observation and handles insertion asynchronously:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/ui/viewmodel/ProductViewModel.java
package com.example.eats.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.eats.data.repository.ProductRepoImpl;
import com.example.eats.domain.ProductRepo;
import com.example.eats.model.Product;

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
        loadProducts();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return productsLiveData;
    }

    public void insertProduct(Product product) {
        executorService.execute(() -> {
            productRepo.insertProduct(product);
            loadProducts(); // Refresh list after insert
        });
    }

    private void loadProducts() {
        executorService.execute(() -> {
            List<Product> products = productRepo.getAllProducts();
            productsLiveData.postValue(products);
        });
    }
}
```

---

### Explanation

- Extends `AndroidViewModel` to get application context.
- Uses `MutableLiveData` to hold and expose product list.
- Loads products asynchronously on initialization and after insert.
- Provides `insertProduct()` method to add new products asynchronously.
- UI can observe `getProductsLiveData()` for updates.
### User:
now  create the adapter

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/view/viewmodel/ProductViewModel.java
ExecutorService
```
### CodeGPT:
Here is a RecyclerView adapter for displaying the list of `Product` items:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/view/adapter/ProductAdapter.java
package com.example.eats.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eats.R;
import com.example.eats.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList = new ArrayList<>();

    public void setProducts(List<Product> products) {
        productList.clear();
        if (products != null) {
            productList.addAll(products);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format("$%.2f", product.getPrice()));
        holder.categoryTextView.setText(product.getCategory());

        // Load product image
        if (product.getUrl_image() != null && !product.getUrl_image().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_image())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_error)
                    .into(holder.productImageView);
        } else {
            holder.productImageView.setImageResource(R.drawable.ic_image_placeholder);
        }

        // Load restaurant logo
        if (product.getUrl_restaurant_logo() != null && !product.getUrl_restaurant_logo().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_restaurant_logo())
                    .placeholder(R.drawable.ic_logo_placeholder)
                    .error(R.drawable.ic_logo_placeholder)
                    .into(holder.restaurantLogoImageView);
        } else {
            holder.restaurantLogoImageView.setImageResource(R.drawable.ic_logo_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, priceTextView, categoryTextView;
        ImageView productImageView, restaurantLogoImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvProductName);
            priceTextView = itemView.findViewById(R.id.tvProductPrice);
            categoryTextView = itemView.findViewById(R.id.tvProductCategory);
            productImageView = itemView.findViewById(R.id.ivProductImage);
            restaurantLogoImageView = itemView.findViewById(R.id.ivRestaurantLogo);
        }
    }
}
```

---

### Explanation

- Adapter holds a list of `Product` objects.
- `setProducts()` updates the list and refreshes the RecyclerView.
- Uses Picasso library to load images from URLs with placeholders and error images.
- Binds product name, price, category, product image, and restaurant logo to views.
- Assumes you have `item_product.xml` layout with the referenced IDs and drawable resources for placeholders.

If you need, I can also provide the `item_product.xml` layout.
### User:
update ids based on this layout
### CodeGPT:
Based on your provided `item_product.xml` layout, here is the updated `ProductAdapter.java` with the correct view IDs:

```java:C:/Users/20112/AndroidStudioProjects/eats/app/src/main/java/com/example/eats/view/adapter/ProductAdapter.java
package com.example.eats.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eats.R;
import com.example.eats.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList = new ArrayList<>();

    public void setProducts(List<Product> products) {
        productList.clear();
        if (products != null) {
            productList.addAll(products);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.productCategory.setText(product.getCategory());

        // Load product image
        if (product.getUrl_image() != null && !product.getUrl_image().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_image())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_error)
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(R.drawable.ic_image_placeholder);
        }

        // Load restaurant logo
        if (product.getUrl_restaurant_logo() != null && !product.getUrl_restaurant_logo().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_restaurant_logo())
                    .placeholder(R.drawable.ic_logo_placeholder)
                    .error(R.drawable.ic_logo_placeholder)
                    .into(holder.logoImage);
        } else {
            holder.logoImage.setImageResource(R.drawable.ic_logo_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice, productCategory;
        CircleImageView productImage, logoImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productCategory = itemView.findViewById(R.id.productCategory);
            productImage = itemView.findViewById(R.id.productImage);
            logoImage = itemView.findViewById(R.id.logoImage);
        }
    }
}
```

---

### Explanation

- Updated all view IDs to match your `item_product.xml` layout:  
  - `productName` (TextView)  
  - `productPrice` (TextView)  
  - `productCategory` (TextView)  
  - `productImage` (CircleImageView)  
  - `logoImage` (CircleImageView)  
- This ensures proper binding of data to views in the adapter.
### User:
update main activity by doing that :
make a function on create that insert 6 items of products as dummy data just to show and i will delete later 
then show all the items on recycle view by using adapter and viewmodel
### CodeGPT:
null

