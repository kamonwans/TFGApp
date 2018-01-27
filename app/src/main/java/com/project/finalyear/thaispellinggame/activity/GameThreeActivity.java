package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.squareup.okhttp.internal.framed.FrameReader;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import io.grpc.internal.SharedResourceHolder;

import static java.lang.Thread.sleep;


public class GameThreeActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    Button btnBallOne, btnBallTwo, btnBallThree, btnBallFour, btnBallFive, btnBallSix, btnBallSeven;
    LinearLayout layout;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        initInstance();
    }

    private void initInstance() {
        btnBallOne = (Button) findViewById(R.id.btnBallOne);
//        btnBallTwo = (Button) findViewById(R.id.btnBallTwo);
//        btnBallThree = (Button) findViewById(R.id.btnBallThree);
//        btnBallThree = (Button) findViewById(R.id.btnBallThree);
//        btnBallFour = (Button) findViewById(R.id.btnBallFour);
//        btnBallFive = (Button) findViewById(R.id.btnBallFive);
//        btnBallSix = (Button) findViewById(R.id.btnBallSix);
//        btnBallSeven = (Button) findViewById(R.id.btnBallSeven);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        layout = (LinearLayout) findViewById(R.id.layout);
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


    }}
