package com.example.android.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView counter;
    SeekBar countSeekbar;
    Boolean counterIsActive=false;
    Button controllerBtn;
    CountDownTimer countDownTimer;

    public void runTimerButtonCLick(View view){
     if(counterIsActive==false){
         counterIsActive=true;
         countSeekbar.setEnabled(false);
         controllerBtn.setText("STOP");
         countDownTimer =  new CountDownTimer((countSeekbar.getProgress()*1000)+100,1000){
             @Override
             public void onTick(long millisUntilFinished) {
                 Log.d("sec left : ",""+millisUntilFinished/1000);
                 counter.animate().rotation(360f).setDuration(400);
                 updateView((int) millisUntilFinished/1000);
             }

             @Override
             public void onFinish() {
                 counter.setText("0:00");
                 MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                 mp.start();
                 counterIsActive=false;
                 controllerBtn.setText("GO");
                 countSeekbar.setEnabled(true);
             }
         }.start();
     }else
     {
         countDownTimer.cancel();
         countSeekbar.setProgress(30);
         counterIsActive=false;
         controllerBtn.setText("GO");
         countSeekbar.setEnabled(true);
         counter.setText("0:30");
     }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       countSeekbar = (SeekBar)findViewById(R.id.seekBarTimer);
        counter = (TextView) findViewById(R.id.TextViewCountdown);

        controllerBtn = (Button)findViewById(R.id.button);

        countSeekbar.setMax(240);
        countSeekbar.setProgress(30);
        countSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             //Task, given seconds, convert it into min and seconds
               updateView(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public  void updateView(int seconds)
    {
        int min=0,sec=0;
        min=(int) seconds/60;
        sec=seconds-min*60;


        String secondString=Integer.toString(sec);
        Log.d("MIN : "+min,"sec : "+sec);
        if(sec==0)
        {
            secondString = "00";
            Log.i("h","hehe 00s");

        }

        //counter.animate().alpha(0f).setDuration(200);
        counter.setText(Integer.toString(min)+" : "+secondString);

        //counter.animate().alpha(1f).setDuration(200);
    }
}
