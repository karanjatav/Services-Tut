package com.example.karanj.services2;

import android.content.Intent;
import android.util.Log;

/**
 * Created by karan.j on 1/2/2017.
 */

public class IntentService extends android.app.IntentService{

    static String LOG = "k_log";

    public IntentService() {
        super("IntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(LOG,"service started");



    }
}