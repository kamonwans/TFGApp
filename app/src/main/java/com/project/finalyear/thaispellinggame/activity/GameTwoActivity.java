package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;


public class GameTwoActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    LinearLayout linearGame2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);
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
        linearGame2 = (LinearLayout) findViewById(R.id.linearGame2);
        linearGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameTwoActivity.this, GameThreeActivity.class);
                startActivity(intent);
            }
        });
    }
}
