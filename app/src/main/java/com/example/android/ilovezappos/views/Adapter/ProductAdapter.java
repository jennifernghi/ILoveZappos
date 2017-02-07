package com.example.android.ilovezappos.views.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.ilovezappos.Controller.ProductItemController;
import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.POJO.Product;

import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import com.example.android.ilovezappos.views.activity.ProductActivity;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
    private ArrayList<Product> products = new ArrayList<>();
    private ProductItemController productItemController;


    public ProductAdapter(ArrayList<Product> data){
        super();
        products = data;
       // productActivity = _productActivity;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_list_item, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        holder.bindConnection(products.get(position));
        productItemController = holder.getProductItemController();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setLoadedProducts(ArrayList<Product> items){
        products=items;
        notifyDataSetChanged();
    }


    public ProductItemController getProductItemController(){
        return this.productItemController;
    }
}
