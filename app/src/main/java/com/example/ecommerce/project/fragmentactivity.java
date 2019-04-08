package com.example.ecommerce.project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class fragmentactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentactivity);

        ViewPager viewpager =(ViewPager)findViewById(R.id.viewpager) ;

        viewpager.setAdapter(new SimpleFragmentPageAdapter(getSupportFragmentManager()));

        TabLayout tablayout = (TabLayout)findViewById(R.id.tablayout) ;
        tablayout.setupWithViewPager(viewpager);


    }
}
