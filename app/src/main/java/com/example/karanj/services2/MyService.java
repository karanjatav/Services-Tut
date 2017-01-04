package com.example.karanj.services2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    static String LOG = "k_log";
    Runnable r;
    Thread myThread;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG, "onStartCommand method called");

        r = new Runnable() {
            @Override
            public void run() {

                if (!Thread.interrupted()){
                    Log.d(LOG, "Thread running" );
                    for (int i = 1; i <= 10; i++) {

                        long futureTime = System.currentTimeMillis() + 1000;
                        while (System.currentTimeMillis() < futureTime) {
                            synchronized (this) {
                                try {
                                    wait(futureTime - System.currentTimeMillis());
                                    Log.d(LOG, "Service working:" + i + "----" + futureTime);
                                } catch (Exception e) {

                                    String excp = e.toString();
                                    Log.d(LOG, excp);
                                    futureTime = System.currentTimeMillis();
                                   // myThread.interrupt();
                                    i=11;
                                }
                            }
                        }
                    }
            }
                myThread.interrupt();
                stopForeground(true);
            }
        };


        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)

                .setContentTitle("My Awesome App")
                .setContentText("Doing some work...")
                .setContentIntent(pendingIntent).build();

        startForeground(1337, notification);

        myThread = new Thread(r);
        myThread.start();



        return Service.START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        Log.d(LOG, "onDestroy method called");


        myThread.interrupt();

        stopForeground(true);

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
