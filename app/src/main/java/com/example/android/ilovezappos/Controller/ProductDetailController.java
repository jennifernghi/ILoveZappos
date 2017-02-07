package com.example.android.ilovezappos.Controller;

import android.content.Context;
import android.view.View;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.AddCartSuccess;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

public class ProductDetailController {
    private Context context;
    private Product product;
    public ProductDetailController(Context _context, Product _product){
        context = _context;
        product = _product;
    }

    public Product getProduct(){
        return product;
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

    /*public void setVisibilityOrginalPrice(View view){

    }*/

    public void addToCard(View view){
        AddCartSuccess.start(context);
    }

}
