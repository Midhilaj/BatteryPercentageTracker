package com.midhilaj.cocoalabs.cocoalabs;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class ReadDetailsService extends Service {
    public ReadDetailsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        Log.i("info_bird","onCreate  is called");

        //  Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    } Handler handler; int delay = 1000;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("info_bird","onStart is called");


        manageInfo();


        //timer();
        handler = new Handler();
        delay=60000;
        //30000; //milliseconds
        if(new Milla().getDefaults("intervel__",getApplicationContext())!=null?new Milla().getDefaults("intervel__",getApplicationContext()).trim().length()>0?true:false:false){
            delay=Integer.parseInt(new Milla().getDefaults("intervel__",getApplicationContext()));
            delay*=60000;
        }
        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        manageInfo();

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
                handler.postDelayed(this, delay);
            }
        }, delay);




// sch

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void manageInfo(){
        batteryLevel();

        Log.i("info_bird","manageInfo  is called ");

    }
    String battry_per="";
    private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;new Handler();
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                battry_per=level+"";
                Log.i("info_bird",battry_per+"%");
                new TimeDetails().setBattery_p(battry_per+"").save();

            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }



}
