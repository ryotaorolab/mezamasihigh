package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //時間を設定する画面に移動
    public void changebutton(View v){
        Intent intent = new Intent(this, ChangeTimeActivity.class);
        startActivity(intent);
    }
}
