package com.example.android.ilovezappos.views.Adapter;

import android.support.v7.widget.RecyclerView;

import com.example.android.ilovezappos.model.ViewModel.ProductItemViewModel;
import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import com.example.android.ilovezappos.model.POJO.Product;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ProductListItemBinding binding;
    private ProductItemViewModel productItemViewModel;

    public ProductViewHolder(ProductListItemBinding _binding) {
        super(_binding.getRoot());
        binding = _binding;
    }

    public void bindConnection(Product product) {
        setProductItemViewModel(new ProductItemViewModel(itemView.getContext(), product));
        binding.setProductItemViewModel(productItemViewModel);
    }

    public void setProductItemViewModel(ProductItemViewModel _productItemViewModel) {
        productItemViewModel = _productItemViewModel;
    }

    public ProductItemViewModel getProductItemViewModel() {
        return this.productItemViewModel;
    }

}
