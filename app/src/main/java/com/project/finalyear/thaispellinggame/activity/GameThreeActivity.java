package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;


public class GameThreeActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    TextView tvGame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        initInstance();
    }
    private void initInstance() {
        tvGame3 = (TextView) findViewById(R.id.tvGame3);
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
        tvGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameThreeActivity.this, GameFourActivity.class);
                startActivity(intent);
            }
        });
    }
}
