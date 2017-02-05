package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;

import com.example.android.ilovezappos.model.POJO.Product;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductDetailModel {
    private Product product;
    private Context context;

    public ProductDetailModel(Product _product, Context _context){
        this.product= _product;
        this.context= _context;
    }

    public String getProductName(){
        return product.getProductName();
    }

    public String getProductBrand(){
        return product.getBrandName();
    }

    public String getPrice(){
        return product.getPrice();
    }

    public String getOriginalPrice(){
        return product.getOriginalPrice();
    }

    public String getPercentOff(){
        return product.getPercentOff();
    }

    public String getThumnailImage(){
        return product.getThumbnailImageUrl();
    }
}
