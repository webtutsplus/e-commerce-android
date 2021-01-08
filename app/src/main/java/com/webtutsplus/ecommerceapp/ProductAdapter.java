package com.webtutsplus.ecommerceapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;
    private OnItemClickListener onItemClickListener1;

    public ProductAdapter(Context context, List<Product> products, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.products = products;
        this.onItemClickListener1 = onItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, onItemClickListener1);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = products.get(position);
        String url = p.getImageURL();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivProduct);
        holder.tvId.setText("Id: " + p.getId());
        holder.tvName.setText(p.getName());
        holder.tvPrice.setText("Price: " + p.getPrice());
        holder.tvDescription.setText(p.getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivProduct;
        TextView tvId, tvName, tvPrice, tvDescription;
        OnItemClickListener onItemClickListener;

        public ProductViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(products.get(getAdapterPosition()));
        }
    }
}

interface OnItemClickListener {
    public void onItemClick(Product p);
}

