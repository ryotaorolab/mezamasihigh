package com.mou78.mezamasihigh;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mou78.mezamasihigh.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;


public class AlarmNotification extends BroadcastReceiver {

    public static final String CLICK_NOTIFICATION = "click_notification";
    public static final String DELETE_NOTIFICATION = "delete_notification";

    @Override   // データを受信した
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.morning);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Log.d("AlarmBroadcastReceiver","onReceive() pid=" + Process.myPid());


        int requestCode = intent.getIntExtra("RequestCode",0);

            PendingIntent pendingIntent =
            PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = "default";
        // app name
        String title = context.getString(R.string.app_name);

        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dataFormat =
                new SimpleDateFormat("HH:mm", Locale.JAPAN);
        String cTime = dataFormat.format(currentTime);

        // メッセージ　+ 11:22:331
        String message = "おはようございます！。 "+cTime+"になりました！";

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        // Notification　Channel 設定
        NotificationChannel channel = new NotificationChannel(
                channelId, title , IMPORTANCE_HIGH);
        channel.setDescription(message);
        channel.enableVibration(true);
        channel.canShowBadge();
        channel.enableLights(true);
        channel.setLightColor(Color.BLUE);
        // the channel appears on the lockscreen
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        channel.setSound(defaultSoundUri, null);
        channel.setShowBadge(true);


        if(notificationManager != null){
            notificationManager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(context, channelId)
                    .setContentTitle(title)
                    // android標準アイコンから
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentText(message)
                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent)
                    .build();

//            Intent intent = new Intent(this, AlarmActivity.class);
//            PendingIntent activityIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//                String action = intent.getAction();
//
//                switch (action) {
//                    case CLICK_NOTIFICATION:
//                        //通知タップ時のイベントを書く
//                        Toast.makeText(context, "通知がタップされました", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case DELETE_NOTIFICATION:
//                        //通知削除時のイベントを書く
//                        Toast.makeText(context, "通知が削除されました", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    default:
//                        break;
//                }

            // 通知
            notificationManager.notify(R.string.app_name, notification);

        }
    }
}