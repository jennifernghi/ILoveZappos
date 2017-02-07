package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.ilovezappos.databinding.ActivityProductBinding;
import com.example.android.ilovezappos.model.POJO.Product;


/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductActivityViewModel {

    private Context context;
    private Product product;
    private ActivityProductBinding binding;

    public ProductActivityViewModel(Context _context, ActivityProductBinding _binding) {
        context = _context;
        binding = _binding;
    }


    public RecyclerView getRecyclerView() {
        return binding.list;
    }

    public RelativeLayout getEmptyView() {
        return binding.emptyView;
    }

    public TextView getEmptyTextView() {
        return binding.emptyTextView;
    }

    public EditText getEditField() {
        return binding.inputText;
    }

}
