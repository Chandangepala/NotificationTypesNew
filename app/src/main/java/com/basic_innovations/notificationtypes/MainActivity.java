package com.basic_innovations.notificationtypes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static String CHANNEL_ID = "custom";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //bitmap for large icon
        Bitmap  bitIcon = ((BitmapDrawable) (getResources().getDrawable(R.drawable.logo))).getBitmap();
        //something  changed
        Intent iNotify = new Intent(this, MainActivity.class);
        //Flag to clear previous activity
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //pending intent flag update current to update notification of same notification id
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, iNotify, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "My Channel";
            String description = "Channel for updates";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name,importance);
            channel.setDescription(description);

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        //BigPictureStyle

        //Basic Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("New Message from Sona")
                .setContentText("Hey, how's you doing?")
                .setOngoing(true)  //so that it can't be slide removed
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(bitIcon)
                .setChannelId(CHANNEL_ID)
                .build();

        if(notificationManager != null)
            notificationManager.notify(100, notification);
    }
}
