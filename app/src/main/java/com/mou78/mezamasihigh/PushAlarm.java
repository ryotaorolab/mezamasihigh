package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class PushAlarm extends Service {

    final static String TAG = "MyService";
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        // アラームを再生
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.morning);

        mp = MediaPlayer.create(this, uri);
        mp.start();
        mp.setLooping(true);

        return START_STICKY;
    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        if (mp.isPlaying()) {
//            mp.stop();
//        }
//
//        Log.d(TAG, "onDestroy");
//    }
}