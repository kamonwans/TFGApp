package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.project.finalyear.thaispellinggame.fragment.LearningMainFragment;
import com.project.finalyear.thaispellinggame.fragment.SummaryRoundOneFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameOneActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView tvTimer, tvMeaning;
    Button btnChoiceOne, btnChoiceTwo, btnChoiceThree;
    LinearLayout linearGameOne;
    private Firebase firebase;
    DatabaseReference databaseReference;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private String wordMeaning, wordBtnOne, wordBtnTwo, wordBtnThree;
    private static final String URL_Firebase = "https://thaispellinggame-28cfe.firebaseio.com/Game_one";
    private int currentGameOneIndex;
    private ArrayList<GameOne> gameOneArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
        Firebase.setAndroidContext(this);
        initInstance();
    }

    private void initInstance() {
        btnChoiceOne = (Button) findViewById(R.id.btnChoiceOne);
        btnChoiceTwo = (Button) findViewById(R.id.btnChoiceTwo);
        btnChoiceThree = (Button) findViewById(R.id.btnChoiceThree);
        tvMeaning = (TextView) findViewById(R.id.tvMeaning);
        firebase = new Firebase(URL_Firebase);
        dataPull();
        CountDownTimer();



    }

    private void CountDownTimer() {
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%d", millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                FragmentSummaryRoundOne();

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
    }

    private boolean answerIsRight() {
        String answer = "";
        if (btnChoiceOne == btnChoiceOne) answer = "a";
        if (btnChoiceTwo == btnChoiceTwo) answer = "b";
        if (btnChoiceThree == btnChoiceThree) answer = "b";
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
//    public void setFragment(Fragment fragment){
//        if(fragment!=null){
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.contentContainerRoundOne,fragment);
//            transaction.commit();
//        }
//    }

    public void FragmentSummaryRoundOne() {
        Fragment fragment = new SummaryRoundOneFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contentContainerRoundOne, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
