package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.view.View;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.AddCartSuccess;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

/**
 * subclass of AbstractProductDetailViewModel
 * view model of activity_product_detail.xml
 */
public class ProductDetailViewModel extends AbstractProductDetailViewModel {

    public ProductDetailViewModel(Context _context, Product _product) {
        super(_context, _product);
    }


    /**
     * called when float button is clicked
     *
     * @param view
     */
    public void addToCard(View view) {
        //start AddCartSuccess activity
        AddCartSuccess.start(getContext());
    }

}
