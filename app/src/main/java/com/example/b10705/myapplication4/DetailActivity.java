package com.example.b10705.myapplication4;

import android.os.Bundle;

/**
 * Created by B10705 on 2017-11-21.
 */

public class DetailActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailsFragment details = new DetailsFragment();
        details.setSelection(getIntent().getIntExtra("index",-1));
        getSupportFragmentManager().beginTransaction().replace(R.id.details, details).commit();
    }
}
