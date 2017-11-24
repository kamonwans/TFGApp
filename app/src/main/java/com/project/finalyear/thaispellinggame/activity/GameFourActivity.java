package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;


public class GameFourActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView tvTimer;
    ImageView imgGame4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_four);
        initInstance();
    }
    private void initInstance() {
        imgGame4 = (ImageView) findViewById(R.id.imgGame4);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%d", millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));

            }

            @Override
            public void onFinish() {
                tvTimer.setText("00");

            }
        }.start();

        imgGame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameFourActivity.this, GameFiveActivity.class);
                startActivity(intent);
            }
        });
    }
}
