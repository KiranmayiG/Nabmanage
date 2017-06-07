package com.nabmanage.nabmanage;

/**
 * Created by Kiranmayi on 26-05-2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openMainActivity = new Intent(Intro.this,StartPage.class);
                    startActivity(openMainActivity);
                }
            }
        };

        timer.start();
    }
}
