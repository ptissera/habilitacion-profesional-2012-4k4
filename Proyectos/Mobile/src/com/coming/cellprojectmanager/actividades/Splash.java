package com.coming.cellprojectmanager.actividades;

import java.util.Timer;
import java.util.TimerTask;

import com.coming.cellprojectmanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask()
        {           
            @Override
            public void run()
            {
                Intent intent = new Intent(Splash.this, Login.class);
        		startActivity(intent);
        		finish();
            }
        };
        t.schedule(timerTask, 3000);
    }

}
