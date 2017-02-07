package com.example.android.ilovezappos.Controller;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ProductListItemBinding;
import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.ProductActivity;
import com.example.android.ilovezappos.views.activity.ProductDetail;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

public class ProductItemController {

    private Context context;
    private Product product;
    private ProductListItemBinding binding;

    public  ProductItemController(Product _product, Context _context, ProductListItemBinding _binding){
        product =_product;
        context = _context;
        binding = _binding;
    }


    public String getBrandName() {
        return product.getBrandName();
    }


    public String getThumbnailImageUrl() {
        return product.getThumbnailImageUrl();
    }


    public String getOriginalPrice() {
        return product.getOriginalPrice();
    }


    public String getPrice() {
        return product.getPrice();
    }


    public String getPercentOff() {
        return product.getPercentOff();
    }


    public String getProductUrl() {
        return product.getProductUrl();
    }


    public String getProductName() {
        return product.getProductName();
    }

    public Product getProduct(){
        return product;
    }

    public void onSelected(View view) {
        ProductDetail.startIntent(view.getContext(), product);
    }


}
