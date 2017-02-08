package com.example.android.ilovezappos.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.android.ilovezappos.R;

public class AddCartSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart_success);
        animation();
        disappear(2);//disappear after 1 seconds

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddCartSuccess.class);
        context.startActivity(intent);
    }

    private void animation() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void disappear(int second) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AddCartSuccess.this.finish();
                animation();
            }
        }, second * 1000);
    }
}
