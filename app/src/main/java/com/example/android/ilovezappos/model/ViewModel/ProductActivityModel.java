package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.android.ilovezappos.R;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductActivityModel {

    public ObservableInt products;
    public ObservableField<String> emptyViewMesaage;
    private String term= "";
    private Context context;
    public ProductActivityModel(Context _context){
        context = _context;
        products = new ObservableInt(View.VISIBLE);
        emptyViewMesaage = new ObservableField<>("");
    }



    /*public void search(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                term = s.toString().trim();
                startLoading(1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/
}
