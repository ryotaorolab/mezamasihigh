package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChangeTimeActivity extends AppCompatActivity {

    final static public int EDIT_REQ_CODE = 2;
    int currentApiVersion = Build.VERSION.SDK_INT;

    // シェアプリ宣言
    SharedPreferences preferences = getSharedPreferences("", Context.MODE_PRIVATE);

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changetime);

        // タイムピッカーを取得
        timePicker = findViewById(R.id.change);

        int hour = timePicker.getHour();

        Date date = new Date(2020, 7, 17, 18, 40);
    }

    // 決定ボタン
    public void select(View v){

        // timepickerから時間を取得
        int hour;
        int minute;
        if (currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute()

        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        }

        // Date型をつくる
    hour = timePicker.getHour();
    minute = timePicker.getMinute();

    Date date = new Date(2020, 7, 17, 18, 40);

        // シェアプリに保存する


        // アラーム設定


        // finish

    }

    // 日付・時間取得

    // 保存する


    public void back(View v){
        finish();
    }

}