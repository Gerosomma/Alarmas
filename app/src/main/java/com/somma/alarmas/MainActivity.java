package com.somma.alarmas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CODIGO_SOLICITUD_INTENCION_ALARMA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnEstablecerAlarmaOnClick(View view) {
        Intent intencionServicio = new Intent(getApplicationContext(), AlarmaService.class);
        PendingIntent intencionAlarma = PendingIntent.getService(getApplicationContext(),
                CODIGO_SOLICITUD_INTENCION_ALARMA, intencionServicio, 0);

        AlarmManager gestorAlarmas = (AlarmManager)getSystemService(ALARM_SERVICE);

        //gestorAlarmas.set();  suena una vez y listo
        //gestorAlarmas.setRepeating(); se repite cada tanto tiempo
        //gestorAlarmas.setInexactRepeating();



        //el tiempo que seteo para que se dispare, este toma como referencia el tiempo en el cual el sistema arranco hasta el momento de disparada la alarma.
        /*gestorAlarmas.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10000,
                intencionAlarma);*/

        //RTC -> el tiempo que seteo para que se dispare, es la hora actual + 10000 milisegundos
        /*gestorAlarmas.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 10000,
                intencionAlarma);*/

        //luego hacemos un ejemplo de alarma repetitiva.

        gestorAlarmas.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 10000,
                5000,
                intencionAlarma);


        Toast.makeText(this, "Alarma establecida!", Toast.LENGTH_SHORT);
    }

    public void btnCancelarAlarmaOnClick(View view) {
        Intent intencionServicio = new Intent(getApplicationContext(), AlarmaService.class);
        PendingIntent intencionAlarma = PendingIntent.getService(getApplicationContext(),
                CODIGO_SOLICITUD_INTENCION_ALARMA, intencionServicio, 0);

        AlarmManager gestorAlarmas = (AlarmManager)getSystemService(ALARM_SERVICE);

        gestorAlarmas.cancel(intencionAlarma);
        Toast.makeText(this, "Alarma CANCELADA!", Toast.LENGTH_SHORT);
    }
}
