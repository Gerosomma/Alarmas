package com.somma.alarmas;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmaService extends Service {

    public static final String MIS_LOGS = "MIS_LOGS";

    protected MediaPlayer reproductor;

    @Override
    public void onCreate() {
        super.onCreate();

        reproductor = MediaPlayer.create(getApplicationContext(), R.raw.gallo);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MIS_LOGS, "Se disparo la alarma");

        reproductor.start();

        SystemClock.sleep(3000);
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        reproductor.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
