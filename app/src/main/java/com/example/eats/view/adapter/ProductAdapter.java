package com.example.eats.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eats.R;
import com.example.eats.data.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

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
        holder.productPrice.setText(String.format("%.2f SAR", product.getPrice()));

        holder.itemView.setOnClickListener(v -> Toast.makeText(context
                , ""+holder.productName.getText().toString(), Toast.LENGTH_SHORT).show());

        // Load product image
        if (product.getUrl_image() != null && !product.getUrl_image().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_image())
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_broken_image_24)
                    .into(holder.
                            productImage);
        } else {
            holder.productImage.setImageResource(R.drawable.baseline_image_24);
        }

        // Load restaurant logo
        if (product.getUrl_restaurant_logo() != null && !product.getUrl_restaurant_logo().isEmpty()) {
            Picasso.get()
                    .load(product.getUrl_restaurant_logo())
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_broken_image_24)
                    .into(holder.logoImage);
        } else {
            holder.logoImage.setImageResource(R.drawable.baseline_image_24
            );
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
            productImage = itemView.findViewById(R.id.productImage);
            logoImage = itemView.findViewById(R.id.logoImage);
        }
    }




}

