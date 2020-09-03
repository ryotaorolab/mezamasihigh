package com.mou78.mezamasihigh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import static android.icu.text.DateFormat.HOUR;
import static android.icu.text.DateFormat.HOUR_OF_DAY1_FIELD;
import static android.icu.text.DateFormat.MINUTE;
import static android.system.Os.remove;
import static androidx.core.os.LocaleListCompat.create;

public class MainActivity extends AppCompatActivity {

    View view;
    TextView timeTextView;
    //UI
    private TimePicker timePicker;
    //ShardPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //ArarmManager
    private AlarmManager alarmManager;
    MediaPlayer test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("絶対に起こす目覚まし");


        // ビューの関連付け
        timeTextView = findViewById(R.id.timeTextView);

        // SharedPreferencesの設定
        sharedPreferences =getSharedPreferences("Ararm", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //AlarmManagerの取得
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


        // alarmの時間を取得
        long time = sharedPreferences.getLong("time", 0);

        // TextViewに表示
            if (time !=0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                String timeText = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
                timeTextView.setText(timeText);



        } else {
            timeTextView.setText("--:--");

        }
    }

    // SetTimeActivityでアラームを設定する
    public  void setTime(View view) {
        Intent intent = new Intent(this, ChangeTimeActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //リクエストを確認する
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //データー取得
            long millis = data.getLongExtra("time", 0);
            //カレンダー型を精製して時間を取り出す。
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
//            String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            String time = hour + ":" + String.format("%02d", minute);
            // TextViewに表示させる
            timeTextView.setText(time);
//            System.out.println(String.format("%06d", 1));
        }
    }

    public void release(View view) {
        //アラームを解除する
        Intent intent = new Intent(this, AlarmActivity.class);
        intent.setData(Uri.parse(String.valueOf(-1)));
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, -1,intent,0);
        alarmManager.cancel(alarmIntent);

        // TextViewに表示させる
        timeTextView.setText("--:--");

        //SharedPreferencesの値を削除
        editor.remove("time");
        editor.commit();
    }
    public void Announce(View view) {
        Intent intent = new Intent(MainActivity.this, AnnounceActivity.class);
        startActivity(intent);
    }

    public  void Testaram (View view) {

        // 音楽の読み込み
        test = MediaPlayer.create(getApplicationContext(), R.raw.morning);
        // 一回再生
        test.start();
    }

}