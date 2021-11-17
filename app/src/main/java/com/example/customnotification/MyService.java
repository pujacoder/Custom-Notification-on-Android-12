package com.example.customnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static com.example.customnotification.MainActivity.CHANNEL_ID;

public class MyService<builder> extends Service {
    public static final String TAG="MyService";
    NotificationManager notificationManager;
    Notification builder;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      //  throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public void onCreate() {
        Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        super.onCreate();

    }
    public void sum(){
        System.out.println("in sun service class");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Invoke background service onStartCommand method.", Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
        Intent notificationIntent = new Intent(this,ExampleActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_IMMUTABLE);
        //Bitmap bitmap=Bitmap.createBitmap()
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.buddy);
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("app header")
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeIcon))
                .setContentText("custom notification test example skdfng jadgb ksgb iosgn ajfng ajsfgn akjfn adgn jafn jdbkajfdb jadfb jadfbn " +
                        "adjfv" +
                        "adf " +
                        "jfv " +
                        "ajf " +
                        "jfgbm")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        notificationManager.notify(1,builder);
        //startForeground(ONGOING_NOTIFICATION_ID, builder);
        startForeground(1,builder);
        //startForegroundService(notificationIntent);
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Invoke background service onDestroy method.", Toast.LENGTH_LONG).show();
    }
}