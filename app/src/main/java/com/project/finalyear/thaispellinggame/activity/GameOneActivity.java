package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.fragment.GameOneFragment;
import com.project.finalyear.thaispellinggame.fragment.LearningMainFragment;
import com.project.finalyear.thaispellinggame.fragment.SummaryRoundOneFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameOneActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    private static TextView tvTimer, tvMeaning;
    private static Button btnChoiceOne, btnChoiceTwo, btnChoiceThree;
    LinearLayout linearGameOne;
    private Firebase firebase;
    DatabaseReference databaseReference;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private static final String URL_Firebase = "https://thaispellinggame-28cfe.firebaseio.com/Game_one";
    private int currentGameOneIndex;
    private ArrayList<GameOne> gameOneArrayList;
    private String selectedWords;
    public TextView answer;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
        Firebase.setAndroidContext(this);
        initInstance();
    }

    private void initInstance() {
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        btnChoiceOne = (Button) findViewById(R.id.btnChoiceOne);
        btnChoiceTwo = (Button) findViewById(R.id.btnChoiceTwo);
        btnChoiceThree = (Button) findViewById(R.id.btnChoiceThree);
        tvMeaning = (TextView) findViewById(R.id.tvMeaning);
        firebase = new Firebase(URL_Firebase);
        dataPull();
        CountDownTimer();



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
//                checkAnswer();
                Intent intent = new Intent(GameOneActivity.this, SummaryRoundOneActivity.class);
//                intent.putExtra("arrayList", arrayList );
                startActivity(intent);


            }
        }.start();
    }

    private void dataPull() {

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                Map<String, String> map = dataSnapshot.getValue(Map.class);
//                String meaning = map.get("meaning");
//                String btnOne = map.get("choiceB");
//                String btnTwo = map.get("choiceA");
//                String btnThree = map.get("choiceC");
//
//                Log.v("E_Value", "Meaning : " + meaning);
//                Log.v("E_Value", "choiceB : " + btnOne);
//                Log.v("E_Value", "choiceA : " + btnTwo);
//                Log.v("E_Value", "choiceC : " + btnThree);
//                tvMeaning.setText(meaning);
//                btnChoiceOne.setText(btnOne);
//                btnChoiceTwo.setText(btnTwo);
//                btnChoiceThree.setText(btnThree);
                gameOneArrayList = new ArrayList<GameOne>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    gameOneArrayList.add(data.getValue(GameOne.class));
                }
                currentGameOneIndex = 0;
                displayGameOne(currentGameOneIndex);
                btnChoiceOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click();
                    }
                });
                btnChoiceTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click();
                    }
                });
                btnChoiceThree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click();
                    }
                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void Click() {
        advance();
        answerIsRight();
//        if (this.answerIsRight()) {
//            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
//            advance();
//        } else {
//            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
//            advance();
//        }
        selectedWords = btnChoiceOne.getText().toString();
        selectedWords = btnChoiceTwo.getText().toString();
        selectedWords = btnChoiceThree.getText().toString();

    }

    private boolean answerIsRight() {
        String answer = "correctAnswer";
        if (btnChoiceOne == btnChoiceOne) answer = "correctAnswer";
        if (btnChoiceTwo == btnChoiceTwo) answer = "correctAnswer";
        if (btnChoiceThree == btnChoiceThree) answer = "correctAnswer";
        return gameOneArrayList.get(currentGameOneIndex).isCorrectAnswer(answer);
    }


    private void displayGameOne(int index) {
        tvMeaning.setText(gameOneArrayList.get(currentGameOneIndex).getMeaning());
        btnChoiceOne.setText(gameOneArrayList.get(currentGameOneIndex).getChoiceA());
        btnChoiceTwo.setText(gameOneArrayList.get(currentGameOneIndex).getChoiceB());
        btnChoiceThree.setText(gameOneArrayList.get(currentGameOneIndex).getChoiceC());
    }

    private void advance() {
        currentGameOneIndex = (currentGameOneIndex + 1) % gameOneArrayList.size();
        displayGameOne(currentGameOneIndex);
    }

//    public void sendData(String data) {
//        btnChoiceOne.getText().toString();
//        btnChoiceTwo.getText().toString();
//        btnChoiceThree.getText().toString();
//        Log.d("one","one");
//        Log.d("two","two");
//        Log.d("three","three");
//    }
//    public void checkAnswer() {
//        HashMap<String, String> map;
//        map = new HashMap<String, String>();
//        map.put("primary key", "choice");
//        arrayList.add(map);
//        Log.d("map", "map");
//
//    }

}