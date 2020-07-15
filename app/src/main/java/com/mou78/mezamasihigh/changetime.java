package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class changetime extends AppCompatActivity {

    // シェアプリ宣言

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changetime);

        timePicker = findViewById(R.id.change);
    }

    public  void  back(View v){
        finish();
    }

    // 決定buttonのメソッドを作る


    // シェアプリで保存


}