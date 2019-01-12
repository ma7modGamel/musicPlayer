package com.example.xxpc.muzzicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton Btn_play, Btn_pause, Btn_stop;
    TextView textSong, textArtist;
    MediaPlayer mediaPlayer;
    modelSound mSound;
    int pauseValue;
    TextView textStart;
    SeekBar seekbar;
Button btn_back;
Intent BackIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        textArtist = findViewById(R.id.artist_two);
        textSong = findViewById(R.id.name_song_two);
        textStart = findViewById(R.id.tvstart);
        Btn_pause = findViewById(R.id.pausebtnid);
        Btn_play = findViewById(R.id.playbtnid);
        Btn_stop = findViewById(R.id.stopbtnId);
        seekbar = findViewById(R.id.seekBar);
        Intent intent = getIntent();
        mSound = intent.getParcelableExtra("allinfo");
        textSong.setText(mSound.getNameSound());
        textArtist.setText(mSound.getArtist());
        Btn_play.setOnClickListener(this);
        Btn_pause.setOnClickListener(this);
        Btn_stop.setOnClickListener(this);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackIntent=new Intent(TwoActivity.this,MainActivity.class);
                startActivity(BackIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.pausebtnid:
                pauseMethod();
                break;
            case R.id.stopbtnId:
                StopMethod();
                break;
            case R.id.playbtnid:
                playMethod();
                break;
        }
    }

    int finalTime;
    int startTime;
    int oneTimeOnly = 0;
    Handler myHandler;

    private void playMethod() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), mSound.sound);
            mediaPlayer.start();
            moveSeekBar();
        } else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(pauseValue);
            mediaPlayer.start();
            moveSeekBar();
        }
    }

    private void moveSeekBar() {

        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        myHandler = new Handler();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }


        textStart.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

        seekbar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime, 100);

    }


    private Runnable UpdateSongTime = new Runnable() {

        public void run() {

            if(mediaPlayer==null) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), mSound.sound);

            }else {

            startTime = mediaPlayer.getCurrentPosition();
            textStart.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );}
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);


        }

        ;
    };

    private void pauseMethod() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            pauseValue = mediaPlayer.getCurrentPosition();
        }
    }

    private void StopMethod() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        } else {
            Toast.makeText(this, "00", Toast.LENGTH_SHORT).show();
        }
    }
}

