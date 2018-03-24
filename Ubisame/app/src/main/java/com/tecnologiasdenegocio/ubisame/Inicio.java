package com.tecnologiasdenegocio.ubisame;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Inicio extends AppCompatActivity {
    int SDK_INT;
    private GPSTracker gpsTracker;
    private Location location;
    String idDevice = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        String id = Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        idDevice = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.System.ANDROID_ID);
        Toast.makeText(getApplicationContext(), "Device: " + idDevice, Toast.LENGTH_SHORT).show();


        SDK_INT = android.os.Build.VERSION.SDK_INT;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        gpsTracker = new GPSTracker(getBaseContext(), this);


        //Se crea la tarea
        //ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        //timer.scheduleAtFixedRate(tarea, 1, 5, TimeUnit.MINUTES);
        //  apagarIcono();
    }

    public void sendGPS(){
        try {
            location = gpsTracker.getLocation();
            int respuesta = 0;
            try{
                URL url = new URL("http://tecnologiasdenegocio.com/Ubisame/index.php?latitud="+Double.toString(location.getLatitude())+"&longitud="+Double.toString(location.getLongitude())+"&idUsuario=1"+"&device="+idDevice);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                respuesta = connection.getResponseCode();
                Log.d("Latitude Inicio: ", Double.toString(location.getLatitude()));
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


    public void apagarIcono(){
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, Inicio.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    class Sender extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String msg) {

        }


    }
}
