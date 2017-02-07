package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.view.View;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.AddCartSuccess;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

public class ProductDetailViewModel extends AbstractProductDetailViewModel {

    public ProductDetailViewModel(Context _context, Product _product) {
        super(_context, _product);
    }


    public void addToCard(View view) {
        AddCartSuccess.start(getContext());
    }

}
