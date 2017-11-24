package com.project.finalyear.thaispellinggame.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;

public class GameFiveActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView tvTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_five);
        initInstance();
    }
    private void initInstance() {
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

    }
}
