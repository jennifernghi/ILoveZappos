package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.view.View;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.views.activity.ProductDetail;

/**
 * Created by jennifernghinguyen on 2/6/17.
 */

/**
 * subclass of AbstractProductDetailViewModel
 * view model of product_list_item.xml
 */
public class ProductItemViewModel extends AbstractProductDetailViewModel {

    public ProductItemViewModel(Context _context, Product _product) {
        super(_context, _product);
    }

    /**
     * called when user select 1 product
     * @param view
     */
    public void onSelected(View view) {
        //open ProductDetail activity
        ProductDetail.startIntent(view.getContext(), getProduct());
    }


}
