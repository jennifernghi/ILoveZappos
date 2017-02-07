package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.view.View;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.ProductDetail;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

public class ProductItemViewModel extends AbstractProductDetailViewModel {

    public ProductItemViewModel(Context _context, Product _product) {
        super(_context, _product);
    }


    public void onSelected(View view) {
        ProductDetail.startIntent(view.getContext(), getProduct());
    }


}
