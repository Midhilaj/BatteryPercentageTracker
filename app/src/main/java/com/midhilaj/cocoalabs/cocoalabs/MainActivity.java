package com.midhilaj.cocoalabs.cocoalabs;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView close_inter_vel_page;
    TimeBattryWatcher mAdapter;
    FrameLayout welcomepage,updareintervelpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.timedetails_recyclerview);
        if(!new Milla().isMyServiceRunning(getApplicationContext(),ReadDetailsService.class)){
            Log.i("info_bird","isMyServiceRunning  is false");
            Intent readtime = new Intent(getApplicationContext(), ReadDetailsService.class);
            startService(readtime);
        }else{
            Log.i("info_bird","isMyServiceRunning  is true");
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try{
                  TimeDetails.deleteAll(TimeDetails.class);
                  welcomepage.setVisibility(View.VISIBLE);
                  recyclerView.setVisibility(View.GONE);
                  Intent readtime = new Intent(getApplicationContext(), ReadDetailsService.class);
                  stopService(readtime);
                  startService(readtime);
                  try {
                      checkUpdate();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }catch (Exception e){

              }
            }
        });
        close_inter_vel_page=(TextView)findViewById(R.id.close_inter_vel_page);
        close_inter_vel_page.setVisibility(View.GONE);
        welcomepage=(FrameLayout)findViewById(R.id.welcomepage);
        updareintervelpage=(FrameLayout)findViewById(R.id.updateintewell);
        welcomepage.setVisibility(View.GONE);
        updareintervelpage.setVisibility(View.GONE);
        close_inter_vel_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updareintervelpage.setVisibility(View.GONE);
                close_inter_vel_page.setVisibility(View.GONE);


                 {
                    Log.i("info_bird","isMyServiceRunning  is false");
                    Intent readtime = new Intent(getApplicationContext(), ReadDetailsService.class);
                    stopService(readtime);
                    startService(readtime);
                     try {
                         final Handler handler=new Handler();
                         final int delay = 6000;
                         handler.postDelayed(new Runnable(){
                             public void run(){
                                 //do something
                                 try {
                                     checkUpdate();
                                 } catch (Exception e) {

                                     e.printStackTrace();
                                 }
                                 //handler.postDelayed(this, delay);
                             }
                         }, delay);

                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                }


            }
        });



        try {
            checkUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
        //timer();
          handler = new Handler();
        //delay=30000; //milliseconds
        delay=60200;
        //30000; //milliseconds
        if(new Milla().getDefaults("intervel__",getApplicationContext())!=null?new Milla().getDefaults("intervel__",getApplicationContext()).trim().length()>0?true:false:false){
            delay=Integer.parseInt(new Milla().getDefaults("intervel__",getApplicationContext()));
            delay*=60200;
        }
        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                try {
                    checkUpdate();
                } catch (Exception e) {

                    e.printStackTrace();
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }  Handler handler; int delay = 1000;
    private void timer1(){
        try {
            checkUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
        Log.i("info_bird","timer  is called");
        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run () {
                Log.i("info_bird","timer  is called T");
                try {
                    checkUpdate();
                } catch (Exception e) {

                    e.printStackTrace();
                }
                // your code here...
            }
        };
        int intervel=1000;
        if(new Milla().getDefaults("intervel__",getApplicationContext())!=null?new Milla().getDefaults("intervel__",getApplicationContext()).trim().length()>0?true:false:false){
            intervel=Integer.parseInt(new Milla().getDefaults("intervel__",getApplicationContext()));
            intervel*=1000;
        }
// schedule the task to run starting now and then every hour...
        timer.schedule (hourlyTask, 0, intervel*60*60);
    }
    private void checkUpdate() throws Exception {
        List<TimeDetails> list=TimeDetails.listAll(TimeDetails.class);
        if(list!=null?list.size()>0?true:false:false){
            welcomepage.setVisibility(View.GONE);

            recyclerView.setVisibility(View.VISIBLE);
            Log.i("info_bird","hi aganin called me "+list.size());
            Collections.reverse(list);

            mAdapter=new TimeBattryWatcher(list,getApplicationContext());

            // TODO: 10/16/18 update grid spcae cound based on diffrent resulation
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }else{
            welcomepage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Log.i("info_bird","hi aganin called me list is null");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            updareintervelpage.setVisibility(View.VISIBLE);
            close_inter_vel_page.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
