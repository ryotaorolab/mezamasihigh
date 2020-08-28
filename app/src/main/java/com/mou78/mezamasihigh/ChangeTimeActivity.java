package com.mou78.mezamasihigh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class ChangeTimeActivity extends AppCompatActivity {
    //UI
    private TimePicker timePicker;
    // SharedPreference
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int requestCode = 1;
    private PendingIntent pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changetime);

        setTitle("時間指定");

        //TimePickerの関連付け
        timePicker = findViewById(R.id.timePicker);

        // SharedPreferencesの設定
        sharedPreferences = getSharedPreferences("Ararm", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    //アラームをセットする
    public void settimer (View view) {
        //アラームをセットするbuttonを押した際の動作
        Context context = getApplicationContext();

        Toast.makeText(context , "セットしました！", Toast.LENGTH_LONG).show();

        //時間を取得する
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();


        //  Calendarを作る
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        //　現在時間を取得
        Calendar nowCalender = Calendar.getInstance();
        nowCalender.setTimeInMillis(System.currentTimeMillis());

        // 時間を比較する
        int diff = calendar.compareTo(nowCalender);

        if (diff <= 0) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        }

        //Alarmをセットする
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Toast.makeText(this, "if文外だよ！", Toast.LENGTH_SHORT).show();

        //通知の振り分け
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Intent intent = new Intent(getApplicationContext(), AlarmNotification.class);
            intent.putExtra("RequestCode",requestCode);
            pending = PendingIntent.getBroadcast(
                    getApplicationContext(),requestCode, intent, 0);
            Toast.makeText(this, "android8.0以上の端末だよ！！", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(this, AlarmActivity.class);
            intent.setData(Uri.parse(String.valueOf(0)));
            PendingIntent alarmIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(alarmIntent);
            Toast.makeText(this, "android8.0以下だよ！", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), null), alarmIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                Toast.makeText(this, "if文は機能してないよ！！", Toast.LENGTH_SHORT).show();
            }
        }

        //もともとのやつ
//        Intent intent = new Intent(this, AlarmActivity.class);
//        intent.setData(Uri.parse(String.valueOf(0)));
//        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//        alarmManager.cancel(alarmIntent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), null), alarmIntent);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
//
//        } else {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
//
//        }

        // SharedPreferencesに値を保存する
        long time = calendar.getTimeInMillis();
        editor.putLong("time", time);
        editor.apply();

        // MainActivityに値を保存する
        Intent resultIntent = new Intent();
        resultIntent.putExtra("time", time);
        setResult(RESULT_OK, resultIntent);

        finish();
    }



    public void back (View v){
        onBackPressed();
    }

    public void test (View v){
        Intent intent = new Intent(ChangeTimeActivity.this, AlarmActivity.class);
        startActivity(intent);
    }
}