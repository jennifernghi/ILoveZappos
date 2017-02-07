package com.example.android.ilovezappos.views.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.ilovezappos.Controller.ProductDetailController;
import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ActivityProductDetailBinding;
import com.example.android.ilovezappos.model.POJO.Product;

import static com.example.android.ilovezappos.utils.URLUtils.context;

public class ProductDetail extends AppCompatActivity {
    public final static String PRODUCT_INFO = "PRODUCT_INFO";
    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        getProductInfo();
    }

    public static void startIntent(Context context, Product product){
        Intent intent = new Intent(context, ProductDetail.class);
        intent.putExtra(PRODUCT_INFO, product);
        context.startActivity(intent);
        //return intent;
    }
    private void getProductInfo() {
        Product people = (Product) getIntent().getSerializableExtra(PRODUCT_INFO);
        ProductDetailController productDetailController = new ProductDetailController(this,people);
        binding.setProductDetailController(productDetailController);
        setTitle(people.getProductName());
    }
}
