package com.example.android.ilovezappos.views.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.Product;

import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
    private ArrayList<Product> products = new ArrayList<>();

    public ProductAdapter(ArrayList<Product> data){
        super();
        products = data;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_list_item, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bindConnection(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setLoadedProducts(ArrayList<Product> items){
        products=items;
        notifyDataSetChanged();
    }
}