package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.ilovezappos.databinding.ActivityProductBinding;


/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class ProductActivityViewModel {

    private Context context;

    //bind to active_product.xml
    private ActivityProductBinding binding;

    public ProductActivityViewModel(Context _context, ActivityProductBinding _binding) {
        context = _context;
        binding = _binding;
    }

    /**
     * get recycler view in active_product.xml
     *
     * @return recyclerview
     */
    public RecyclerView getRecyclerView() {
        return binding.list;
    }

    /**
     * get empty view relative layout in active_product.xml
     *
     * @return relativelayout
     */
    public RelativeLayout getEmptyView() {
        return binding.emptyView;
    }

    /**
     * get text view in empty view in active_product.xml
     *
     * @return text view
     */
    public TextView getEmptyTextView() {
        return binding.emptyTextView;
    }

    /**
     * get search edittext in active_product.xml
     *
     * @return edittext
     */
    public EditText getEditField() {
        return binding.inputText;
    }

    /**
     * get progressbar in active_product.xml
     *
     * @return progressbar
     */
    public ProgressBar getProgressBar() {
        return binding.loadingIndicator;
    }

    /**
     * get the context
     *
     * @return context
     */
    public Context getContext() {
        return context;
    }


    /**
     * enable emptyview
     *
     * @param status  - t/f
     * @param message - message shown in emptytextview
     */
    public void enableEmptyView(boolean status, String message) {
        if (status) {
            getRecyclerView().setVisibility(View.GONE);
            getEmptyView().setVisibility(View.VISIBLE);
            getEmptyTextView().setVisibility(View.VISIBLE);
            getEmptyTextView().setText(message);
        } else {
            getRecyclerView().setVisibility(View.VISIBLE);
            getEmptyView().setVisibility(View.GONE);
            getEmptyTextView().setVisibility(View.GONE);
        }
    }

    /**
     * show progress bar
     *
     * @param on T/F
     */
    public void showProgressBar(boolean on) {
        if (on) {
            getProgressBar().setVisibility(View.VISIBLE);
        } else {
            getProgressBar().setVisibility(View.GONE);
        }
    }
}
