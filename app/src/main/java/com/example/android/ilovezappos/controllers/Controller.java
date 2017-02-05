package com.example.android.ilovezappos.controllers;


import android.content.Context;

import com.example.android.ilovezappos.views.Adapter.ProductAdapter;
import com.example.android.ilovezappos.views.activity.ProductActivity;
import android.app.LoaderManager;
import android.content.Loader;
/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public class Controller {
    private ProductAdapter adapter;
    private Context context;
    public Controller(Context _context){
        this.context = _context;
    }

}
