package com.example.android.ilovezappos.views.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import com.example.android.ilovezappos.model.POJO.Product;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder{

    private ProductListItemBinding binding;
    public ProductViewHolder(ProductListItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Product product){
        binding.setProduct(product);
    }


}
