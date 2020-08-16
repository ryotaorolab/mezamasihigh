package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

public class AlarmActivity extends AppCompatActivity {

    int[] hairetu; //問題となる数字が入る配列
    String mondai;//実際に表示させる問題　文字列
    int seikai;//4桁のうち今何行目を回答しているのかを覚えておく変数
    TextView textView;//問題を表示させるTextView
    MediaPlayer p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm2);


        // 音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(), R.raw.morning);
        // 連続再生設定
        p.setLooping(true);

        textView = (TextView) findViewById(R.id.textView6);//関連付け
        start();

            //時刻表示するコードを追加
            Calendar cal = Calendar.getInstance();       //カレンダーを取得
            System.out.println(String.format("%06d", 1));


        int iHour = cal.get(Calendar.HOUR);         //時を取得
            int iMinute = cal.get(Calendar.MINUTE);    //分を取得

            String strTime = iHour + ":" + iMinute + ""; //時刻を表示形式で設

            TextView tv = (TextView)findViewById(R.id.textView3);
            tv.setText(strTime);
        }


    public void start() {

        hairetu = new int[4];
        Random rand = new Random();
        hairetu[0] = rand.nextInt(4) + 1;
        hairetu[1] = rand.nextInt(4) + 1;
        hairetu[2] = rand.nextInt(4) + 1;
        hairetu[3] = rand.nextInt(4) + 1;

        mondai =
                Integer.toString(hairetu[0])

                        + Integer.toString(hairetu[1])
                        + Integer.toString(hairetu[2])
                        + Integer.toString(hairetu[3]);

        textView.setText(mondai);
        seikai = 0;
    }

    public void number1(View v) {

        if (hairetu[seikai] == 1) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;

            if (seikai == 4) {
                //目覚まし停止
                p.pause();
                //解除後の画面に移行
                Intent intent = new Intent(AlarmActivity.this, UnlockActicity.class);
                startActivity(intent);
                //finish();
            }

        } else {
            Toast.makeText(this, "選択された数字が一致しません", Toast.LENGTH_SHORT).show();
        }
    }



    public void number2(View v) {

        if (hairetu[seikai] == 2) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;

            if (seikai == 4) {
                //目覚まし停止
                p.pause();
                //解除後の画面に移行
                Intent intent = new Intent(AlarmActivity.this, UnlockActicity.class);
                startActivity(intent);
                //finish();
            }
        } else {

            Toast.makeText(this, "選択された数字が一致しません！", Toast.LENGTH_SHORT).show();
        }
    }

    public void number3(View v) {

        if (hairetu[seikai] == 3) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;

            if (seikai == 4) {
                //目覚まし停止
                p.pause();
                //解除後の画面に移行
                Intent intent = new Intent(AlarmActivity.this, UnlockActicity.class);
                startActivity(intent);
                //finish();
            }
        } else {

            Toast.makeText(this, "選択された数字が一致しません", Toast.LENGTH_SHORT).show();
        }
    }

    public void number4(View v) {

        if (hairetu[seikai] == 4) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;

            if (seikai == 4) {
                //目覚まし停止
                p.pause();
                //解除後の画面に移行
                Intent intent = new Intent(AlarmActivity.this, UnlockActicity.class);
                startActivity(intent);
                //finish();
            }
        } else {

            Toast.makeText(this, "選択された数字が一致しません", Toast.LENGTH_SHORT).show();
        }
    }


    //アラームコード
    @Override
    protected void onResume() {
        super.onResume();
        p.start(); // 再生
    }

    // アプリ終了時に実行
    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.release();// メモリの解放
        p = null; // 音楽プレーヤーを破棄
    }
}