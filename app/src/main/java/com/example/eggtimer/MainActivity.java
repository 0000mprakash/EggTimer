package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar timer;
    boolean i =true;
    boolean t=true;
    CountDownTimer countDownTimer;


    public void start(View view){
        Button button=(Button) findViewById(R.id.button);
        if(t==true) {
            button.setText("Stop");
            t=false;
        }
        if(i==false){
            button.setText("GO!");
            timer.setEnabled(true);
            countDownTimer.cancel();
            textView.setText("00:30");
            timer.setProgress(30);
            i=true;

        }

        else {
            countDownTimer=new CountDownTimer(timer.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updatetimer((int) millisUntilFinished / 1000);
                    i=false;
                        timer.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    i=true;
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mediaPlayer.start();
                    if(t==false){
                        button.setText("Go!");
                        if(timer.getProgress()!=0) {
                            t = true;
                        }
                        timer.setEnabled(true);
                    }

                }
            }.start();
        }
    }
    public void updatetimer(int progress){
        int minute=progress/60;
        int second=progress-(minute*60);
        String secondstring=Integer.toString(second);
        if(second<=9){
            secondstring='0'+Integer.toString(second);
        }
        textView.setText(minute +":"+secondstring);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=findViewById(R.id.seekBar3);
        textView=findViewById(R.id.textview5);
        timer.setMax(600);
        timer.setProgress(30);
        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                updatetimer(seekBar.getProgress());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }
}