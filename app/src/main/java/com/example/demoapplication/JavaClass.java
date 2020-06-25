package com.example.demoapplication;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class JavaClass extends AppCompatActivity {

    ViewPager2 viewPager2 = new ViewPager2(this);

    void sth(){
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer() ;
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            }
        });
    }
}
