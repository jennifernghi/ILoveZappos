package com.example.android.ilovezappos.views.Adapter;

import android.support.v7.widget.RecyclerView;

import com.example.android.ilovezappos.Controller.ProductItemController;
import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import com.example.android.ilovezappos.model.POJO.Product;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ProductListItemBinding binding;
    private ProductItemController productItemController;

    public ProductViewHolder(ProductListItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Product product) {
        setProductItemController(new ProductItemController(product, itemView.getContext()));
        binding.setProductItemController(productItemController);
    }

    public void setProductItemController(ProductItemController _productItemController) {
        productItemController = _productItemController;
    }

    public ProductItemController getProductItemController() {
        return this.productItemController;
    }

}
