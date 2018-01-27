package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.common.Util;
import com.project.finalyear.thaispellinggame.model.GameOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameOneActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    private static TextView tvTimer, tvMeaning, tvScoreOne;
    private static Button btnChoiceOne, btnChoiceTwo, btnChoiceThree, button;
    LinearLayout linearGameOne;
    private Firebase firebase;
    DatabaseReference databaseReference;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private static final String URL_Firebase = "https://thaispellinggame-28cfe.firebaseio.com/Game_one";
    private int currentGameOneIndex;
    private ArrayList<GameOne> gameOneArrayList;
    ArrayList<String> selectAnswer = new ArrayList<String>();
    ArrayList<String> answerRight = new ArrayList<String>();
    private String selectedWords;
    public TextView answer;
    // Intent intent;
    int score;
    int counter = 0;
    String scoreText;
    String correctAnswer;
    MediaPlayer soundCorret, soundWrong, soundWow;
    ImageView imgIconOne, imgIconTwo, imgBonus;
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
        tvScoreOne = (TextView) findViewById(R.id.tvScoreOne);
        imgIconOne = (ImageView) findViewById(R.id.imgIconOne);
        imgIconTwo = (ImageView) findViewById(R.id.imgIconTwo);
        imgBonus = (ImageView) findViewById(R.id.imgBonus);
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
                savedWord(selectAnswer, answerRight,scoreText);
                finish();
            }
        }.start();
    }

    private void dataPull() {
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gameOneArrayList = new ArrayList<GameOne>();


                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    gameOneArrayList.add(data.getValue(GameOne.class));

                }
                currentGameOneIndex = 0;
                displayGameOne(currentGameOneIndex);
                btnChoiceOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click(btnChoiceOne, counter);

                    }
                });
                btnChoiceTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click(btnChoiceTwo, counter);

                    }
                });
                btnChoiceThree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Click(btnChoiceThree, counter);


                    }
                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void Click(Button btn, int count) {
        answerIsRight(btn, count);

    }

    private boolean answerIsRight(Button btn, int count) {
        String answer = btn.getText().toString();
        // check correctAnswer ตรงกับที่เลือกไหม
        String selectedWordsFour = correctAnswer.toString();
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);

        if (answer.equals(selectedWordsFour)) {
            imgIconOne.setVisibility(View.VISIBLE);
            imgIconTwo.setVisibility(View.INVISIBLE);
            imgIconOne.startAnimation(animation);
            score += 50;
            counter++;
            scoreText = Integer.toString(score);
            tvScoreOne.setText(scoreText);

            soundCorret = Util.playMediaSound(this, R.raw.correct);
            soundCorret.start();

            // ถ้าตอบติดกัน 5 ข้อ ได้คะแนนโบนส 250 คะแนน
            if (counter == 5) {
                soundWow = Util.playMediaSound(this, R.raw.wow);
                soundWow.start();
                imgBonus.setVisibility(View.VISIBLE);
                imgBonus.startAnimation(animation);
                score = score + 250;
                scoreText = Integer.toString(score);
                tvScoreOne.setText(scoreText);
                counter = 0; //set counter = 0 เพื่อนับ 1 ใหม่
            } else {
                imgBonus.setVisibility(View.INVISIBLE);

            }
        } else if (!answer.equals(selectedWordsFour)) {
            imgIconTwo.setVisibility(View.VISIBLE);
            imgIconOne.setVisibility(View.INVISIBLE);
            imgIconTwo.startAnimation(animation);
            counter = 0;
            imgBonus.setVisibility(View.INVISIBLE);
            soundWrong = Util.playMediaSound(this, R.raw.wrong);
            soundWrong.start();


        }

        selectAnswer.add(answer);
        answerRight.add(selectedWordsFour);

        for (int i = 0; i < answerRight.size(); i++) {
            Log.d("answer ", answerRight.get(i) + i);
        }

//        Toast.makeText(this, "selectAnswer"+ selectAnswer+"\nanswerRight"+answerRight,Toast.LENGTH_SHORT).show();


        advance();
        return true;
    }

    private void savedWord(ArrayList<String> selectAnswer, ArrayList<String> answerRight,String scoreText) {
        Intent intent = new Intent(GameOneActivity.this, SumRoundOneActivity.class);
        intent.putExtra("arrayListAnswerSelect", selectAnswer);
        intent.putExtra("arrayListAnswerRight", answerRight);
        intent.putExtra("scoreRoundOne", scoreText);
        startActivity(intent);
        Log.d("score ", scoreText);

    }


    private void displayGameOne(int index) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int section = random.nextInt(gameOneArrayList.size());
            tvMeaning.setText(gameOneArrayList.get(section).getMeaning());
            btnChoiceOne.setText(gameOneArrayList.get(section).getChoiceA());
            btnChoiceTwo.setText(gameOneArrayList.get(section).getChoiceB());
            btnChoiceThree.setText(gameOneArrayList.get(section).getChoiceC());
            // ดึงคำตอบเก็บไว้ในตัวแปร correctAnswer
            correctAnswer = (gameOneArrayList.get(section).getCorrectAnswer());
//            gameOneArrayList.remove(section);
        }

    }


    private void advance() {
        currentGameOneIndex = (currentGameOneIndex + 1) % gameOneArrayList.size();
        Log.d("currentGameOneIndex", String.valueOf(currentGameOneIndex));
        displayGameOne(currentGameOneIndex);
    }


    // ทำงานเมื่อเปิด app เข้ามา
    public void onResume() {
        super.onResume();

    }

    // ทำงานเมื่อแอปหยุดทำงานชั่วคราว
    public void onPause() {
        super.onPause();

    }

    // ทำงานเมื่อแอปถูปิดลง
    public void onDestroy() {
        super.onDestroy();

    }


}