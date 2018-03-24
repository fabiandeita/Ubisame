package com.tecnologiasdenegocio.ubisame;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by fdeitaze on 13/03/2018.
 */

public class ConeccionRecibida extends BroadcastReceiver {
    private static final String TAG = "ConeccionRecibida";
    int SDK_INT;
    private GPSTracker gpsTracker;
    private Location location;
    String idDevice = "";

    public ConeccionRecibida() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Network connectivity change");
        /*if (Util.existeConexion(context)) {w
            Intent i = new Intent(context.getApplicationContext(), Inicio.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }*/

        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        idDevice = android.provider.Settings.System.getString(context.getContentResolver(), android.provider.Settings.System.ANDROID_ID);
        Toast.makeText(context, "Device: " + idDevice, Toast.LENGTH_SHORT).show();

        Log.d("ConeccionRecibida","--");
        SDK_INT = android.os.Build.VERSION.SDK_INT;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        gpsTracker = new GPSTracker(context, this);

        //Se crea la tarea
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.MINUTES);
    }

    public void sendGPS(){
        try {
            location = gpsTracker.getLocation();
            int respuesta = 0;
            Log.d("Entra a sender", "");
            try{
                URL url = new URL("http://tecnologiasdenegocio.com/Ubisame/index.php?latitud="+Double.toString(location.getLatitude())+"&longitud="+Double.toString(location.getLongitude())+"&idUsuario=1"+"&device="+idDevice);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                respuesta = connection.getResponseCode();
                Log.d("Latitude from Daemon: ", url.toString());
            }
            catch (Exception e){

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }



    final Runnable tarea = new Runnable() {
        public void run() {
            sendGPS();
        }
    };


   /* public void apagarIcono(){
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, Inicio.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }*/

}
