package com.project.finalyear.thaispellinggame.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.model.GameFive;
import com.project.finalyear.thaispellinggame.model.GameThree;

import java.util.ArrayList;
import java.util.Random;

public class GameFiveActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    private TextView tvLeftTwo,tvLeftOne,tvLeftThree,tvLeftFour;
    private TextView tvRightOne,tvRightTwo,tvRightThree,tvRightFour;
    Firebase firebase;
    DatabaseReference databaseReference;
    ArrayList<GameFive> gameFiveArrayList;
    ImageView imgCarLeft,imgCarRight;
    LinearLayout linearLeft,linearRight,linearCarLeft,linearCarRight;
   private int currentGameFiveIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_five);
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Game_Five");
        initInstance();
    }
    private void initInstance() {
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvLeftOne = (TextView) findViewById(R.id.tvLeftOne);
        tvLeftTwo = (TextView) findViewById(R.id.tvLeftTwo);
        tvLeftThree = (TextView) findViewById(R.id.tvLeftThree);
        tvLeftFour = (TextView) findViewById(R.id.tvLeftFour);
        tvRightOne = (TextView) findViewById(R.id.tvRightOne);
        tvRightTwo = (TextView) findViewById(R.id.tvRightTwo);
        tvRightThree = (TextView) findViewById(R.id.tvRightThree);
        tvRightFour = (TextView) findViewById(R.id.tvRightFour);
        linearLeft = (LinearLayout) findViewById(R.id.linearLeft);
        linearRight = (LinearLayout) findViewById(R.id.linearRight);
        imgCarLeft = (ImageView) findViewById(R.id.imgCarLeft);
        imgCarRight = (ImageView) findViewById(R.id.imgCarRight);
        linearCarLeft = (LinearLayout) findViewById(R.id.linearCarLeft);
        linearCarRight = (LinearLayout) findViewById(R.id.linearCarRight);

        linearCarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgCarLeft.setVisibility(View.VISIBLE);
                imgCarRight.setVisibility(View.INVISIBLE);

            }
        });

        linearCarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgCarRight.setVisibility(View.VISIBLE);
                imgCarLeft.setVisibility(View.INVISIBLE);
            }
        });
        dataPull();
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

    private void dataPull() {
        databaseReference.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                gameFiveArrayList = new ArrayList<GameFive>();

                for (com.google.firebase.database.DataSnapshot data : dataSnapshot.getChildren()) {
                    gameFiveArrayList.add(data.getValue(GameFive.class));

                }

                currentGameFiveIndex = 0;
                displayGameThree(currentGameFiveIndex);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkAnswer() {
        advance();
    }
    private void displayGameThree(int currentGameFiveIndex) {

        for (int i=0;i<gameFiveArrayList.size();i++) {
            Random random = new Random();
            int section = random.nextInt(gameFiveArrayList.size());
            for (int k=0;k<section;k++) {
            tvRightOne.setText(gameFiveArrayList.get(k).getWord());
            tvRightTwo.setText(gameFiveArrayList.get(k+1).getWord());

        }
        }

        for (int j = 0;j<gameFiveArrayList.size();j++) {
            Random random1 = new Random();
            int section1 = random1.nextInt(gameFiveArrayList.size());
            for (int h=0;h<section1;h++) {
                tvLeftOne.setText(gameFiveArrayList.get(h).getWord());
                tvLeftTwo.setText(gameFiveArrayList.get(h+1).getWord());
            }

        }
    }

    //----- เปลี่ยนข้อ -----//
    private void advance() {
        currentGameFiveIndex = (currentGameFiveIndex + 1) % gameFiveArrayList.size();
        Log.d("currentGameFiveIndex", String.valueOf(currentGameFiveIndex));
        displayGameThree(currentGameFiveIndex);
    }

    private void clickRight() {

    }

    private void clickLeft() {

    }

}
