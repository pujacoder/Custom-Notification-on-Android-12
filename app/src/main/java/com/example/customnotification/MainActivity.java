package com.example.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final static String CHANNEL_ID="1";
    Button startBt,stopBt;
    NotificationManager notificationManager;
    Notification builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           CharSequence name = "channel_name";//getString(R.string.channel_name);
            String description = "channel_dependant";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
             notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("app header")
                .setContentText("custom notification test example")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        //startForeground(ONGOING_NOTIFICATION_ID, builder);
        startForegroundService(notificationIntent);*/
        //notificationManager.notify(1,builder);
        MyService myService=new MyService();
        myService.sum();
        startBt=findViewById(R.id.startService);
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startServiceIntent = new Intent(MainActivity.this, MyService.class);
                startService(startServiceIntent);
            }
        });
        stopBt=findViewById(R.id.StopBt);
        stopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startServiceIntent = new Intent(MainActivity.this, MyService.class);
                stopService(startServiceIntent);
            }
        });
    }


   /* @Override
    protected void onResume() {
        super.onResume();
        notificationManager.notify(1,builder);
        //startService(new Intent(getApplication(),MyService.class));
    }*/
}