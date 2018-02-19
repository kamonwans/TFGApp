package com.project.finalyear.thaispellinggame.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.controller.GameFiveController;
import com.project.finalyear.thaispellinggame.model.GameFive;
import com.project.finalyear.thaispellinggame.model.GameThree;

import java.util.ArrayList;
import java.util.Random;

public class GameFiveActivity extends AppCompatActivity implements GameFiveController.GameFiveCallBack {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    private TextView tvLeftTwo, tvLeftOne, tvLeftThree, tvLeftFour;
    private TextView tvRightOne, tvRightTwo, tvRightThree, tvRightFour;
    Firebase firebase;
    DatabaseReference databaseReference;
    ArrayList<GameFive> gameFiveArrayList;
    ImageView imgCarLeft, imgCarRight;
    LinearLayout linearLeft, linearRight, linearCarLeft, linearCarRight;
    private int currentGameFiveIndex;
    String typeLeft;
    String typeRight;
    private GameFiveController gameFiveController;
    int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_five);
        initInstance();
        gameFiveController = new GameFiveController(this);
        loadDataGameFive();

    }

    private void initInstance() {
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvLeftOne = (TextView) findViewById(R.id.tvLeftOne);
        tvLeftTwo = (TextView) findViewById(R.id.tvLeftTwo);
        tvLeftThree = (TextView) findViewById(R.id.tvLeftThree);

        tvRightOne = (TextView) findViewById(R.id.tvRightOne);
        tvRightTwo = (TextView) findViewById(R.id.tvRightTwo);
        tvRightThree = (TextView) findViewById(R.id.tvRightThree);

        linearLeft = (LinearLayout) findViewById(R.id.linearLeft);
        linearRight = (LinearLayout) findViewById(R.id.linearRight);
        imgCarLeft = (ImageView) findViewById(R.id.imgCarLeft);
        imgCarRight = (ImageView) findViewById(R.id.imgCarRight);
        linearCarLeft = (LinearLayout) findViewById(R.id.linearCarLeft);
        linearCarRight = (LinearLayout) findViewById(R.id.linearCarRight);

        height = getWindowManager().getDefaultDisplay().getHeight();
        Log.d("heightDisplay", String.valueOf(height));
        linearCarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgCarLeft.setVisibility(View.VISIBLE);
                imgCarRight.setVisibility(View.INVISIBLE);
                setTextLeftThree(gameFiveArrayList);
                setTextLeftTwo(gameFiveArrayList);
                setTextLeftOne(gameFiveArrayList);
                fallingLeft();
                marginTopLeft = -100;
            }
        });

        linearCarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgCarRight.setVisibility(View.VISIBLE);
                imgCarLeft.setVisibility(View.INVISIBLE);
                setTextLeftThree(gameFiveArrayList);
                setTextRightTwo(gameFiveArrayList);
                setTextRightOne(gameFiveArrayList);
                fallingRight();
                marginTopRight = -100;
            }
        });
//        dataPull();

    }

    private void CountDownTimer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%d", millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));

            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");

            }
        }.start();

    }

    private void loadDataGameFive() {
        gameFiveController.dataPull();
    }


    private void checkAnswer() {
      //  advance();
    }
    private int marginTopLeft = 0;
    private int marginTopRight = 0;
    private void fallingLeft() {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvLeftOne.getLayoutParams();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                marginTopLeft += 50;
                params.setMargins(0, marginTopLeft, 0, 0);
                tvLeftOne.setLayoutParams(params);
                //handler.postDelayed(this, 3000);
                // check marginTop มากกว่าขนาดหน้าจอ ให้ marginTop = -100
                if (marginTopLeft > (height - 1000)) {
                    marginTopLeft = -100;
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void fallingRight() {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvRightOne.getLayoutParams();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                marginTopRight += 50;
                params.setMargins(0, marginTopRight, 0, 0);
                tvRightOne.setLayoutParams(params);
               // handler.postDelayed(this, 3000);
                // check marginTop มากกว่าขนาดหน้าจอ ให้ marginTop = -100
                if (marginTopRight > (height - 1000)) {
                    marginTopRight = -100;
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void setTextLeftOne(ArrayList<GameFive> gameFiveArrayList) {
        Random random = new Random();
        for (int i = 0; i < gameFiveArrayList.size(); i++) {
            int section = random.nextInt(gameFiveArrayList.size());
            for (int k = 0; k < section; k++) {
                tvLeftOne.setText(gameFiveArrayList.get(section).getWord());
                typeLeft = gameFiveArrayList.get(section).getType();

            }
        }
    }

    private void setTextLeftTwo(ArrayList<GameFive> gameFiveArrayList) {
        Random random = new Random();
        for (int i = 0; i < gameFiveArrayList.size(); i++) {
            int section = random.nextInt(gameFiveArrayList.size());
            for (int k = 0; k < section; k++) {
                tvLeftTwo.setText(gameFiveArrayList.get(section).getWord());
                typeLeft = gameFiveArrayList.get(section).getType();
            }
        }
    }

    private void setTextLeftThree(ArrayList<GameFive> gameFiveArrayList) {
        Random random = new Random();
        for (int i = 0; i < gameFiveArrayList.size(); i++) {
            int section = random.nextInt(gameFiveArrayList.size());
            for (int k = 0; k < section; k++) {
                tvLeftThree.setText(gameFiveArrayList.get(section).getWord());
                typeLeft = gameFiveArrayList.get(section).getType();
            }
        }
    }

    private void setTextRightOne(ArrayList<GameFive> gameFiveArrayList) {
        Random random1 = new Random();
        for (int j = 0; j < gameFiveArrayList.size(); j++) {
            int section1 = random1.nextInt(gameFiveArrayList.size());
            for (int h = 0; h < section1; h++) {
                tvRightOne.setText(gameFiveArrayList.get(section1).getWord());
                typeRight = gameFiveArrayList.get(section1).getType();

            }

        }
    }

    private void setTextRightTwo(ArrayList<GameFive> gameFiveArrayList) {
        Random random1 = new Random();
        for (int j = 0; j < gameFiveArrayList.size(); j++) {
            int section1 = random1.nextInt(gameFiveArrayList.size());
            for (int h = 0; h < section1; h++) {
                tvRightTwo.setText(gameFiveArrayList.get(section1).getWord());
                typeRight = gameFiveArrayList.get(section1).getType();

            }

        }
    }

    private void setTextRightThree(ArrayList<GameFive> gameFiveArrayList) {
        Random random1 = new Random();
        for (int j = 0; j < gameFiveArrayList.size(); j++) {
            int section1 = random1.nextInt(gameFiveArrayList.size());
            for (int h = 0; h < section1; h++) {
                tvRightThree.setText(gameFiveArrayList.get(section1).getWord());
                typeRight = gameFiveArrayList.get(section1).getType();

            }

        }
    }
    //----- เปลี่ยนข้อ -----//
//    private void advance() {
//        currentGameFiveIndex = (currentGameFiveIndex + 1) % gameFiveArrayList.size();
//        Log.d("currentGameFiveIndex", String.valueOf(currentGameFiveIndex));
//        displayGameThree(currentGameFiveIndex);
//    }

    private void clickRight() {

    }

    private void clickLeft() {

    }

    @Override
    public void displayGameFive(int index, ArrayList<GameFive> gameFiveArrayList) {
        this.gameFiveArrayList = gameFiveArrayList;
        CountDownTimer();
        setTextLeftOne(gameFiveArrayList);
        setTextLeftTwo(gameFiveArrayList);
        setTextLeftThree(gameFiveArrayList);

        setTextRightOne(gameFiveArrayList);
        setTextRightTwo(gameFiveArrayList);
        setTextRightThree(gameFiveArrayList);


    }

    @Override
    public void onCancel(String massageError) {

    }
}
