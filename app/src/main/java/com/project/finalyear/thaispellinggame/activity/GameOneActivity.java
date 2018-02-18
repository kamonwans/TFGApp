package com.project.finalyear.thaispellinggame.activity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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
    MediaPlayer soundCorret, soundWrong, soundWow, soundGameOne;
    ImageView imgIconOne, imgIconTwo, imgBonus, imgSetting, imgCancelSetting;
    Switch soundGame;
    Switch soundEffect;
    ImageView imgMusic, imgNotMusic, imgEffect, imgNotEffect;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
        Firebase.setAndroidContext(this);
        PlaySound();
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

        soundGame = (Switch) findViewById(R.id.soundGame);
        soundEffect = (Switch) findViewById(R.id.soundEffect);
        firebase = new Firebase(URL_Firebase);

        imgMusic = (ImageView) findViewById(R.id.imgMusic);
        imgNotMusic = (ImageView) findViewById(R.id.imgNotMusic);
        imgEffect = (ImageView) findViewById(R.id.imgEffect);
        imgNotEffect = (ImageView) findViewById(R.id.imgNotEffect);

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundGameOne.stop();
                imgNotMusic.setVisibility(View.VISIBLE);
                imgMusic.setVisibility(View.INVISIBLE);
            }
        });
        imgNotMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaySound();
                imgMusic.setVisibility(View.VISIBLE);
                imgNotMusic.setVisibility(View.INVISIBLE);
            }
        });
        imgEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNotEffect.setVisibility(View.VISIBLE);

                imgEffect.setVisibility(View.INVISIBLE);
            }
        });
        imgNotEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEffect.setVisibility(View.VISIBLE);
                soundWrong.start();
                soundWow.start();
                soundCorret.start();
                imgNotEffect.setVisibility(View.INVISIBLE);

            }
        });
        dataPull();
        CountDownTimer();

    }
    //--- function countdown ---//
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
                savedWord(selectAnswer, answerRight, scoreText);
                soundGameOne.stop();
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

                // ส่ง button กับจำนวนครั้งที่กด (counter) ไป
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

    //------ ส่งจำนวน count ไปเพื่อ check ว่าตอบติดกัน 5 ข้อหรือไม่ -----//
    private void Click(Button btn, int count) {
        answerIsRight(btn, count);

    }

    //---- check คำตอบ ----//
    private void answerIsRight(Button btn, int count) {
        // get ข้อความใน button เก็บไว้ในตัวแปร answer
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

            // ถ้าตอบติดกัน 5 ข้อ ได้คะแนนโบนัส 250 คะแนน
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

        selectAnswer.add(answer); // add คำตอบที่เลือกเก็บไว้ใน arrayList selectAnswer
        answerRight.add(selectedWordsFour); // add คำตอบที่ถูกต้องเก็บไว้ใน arrayList answerRight

//        for (int i = 0; i < answerRight.size(); i++) {
//            Log.d("answer ", answerRight.get(i) + i);
//        }

        advance();

    }

    //---- ส่งค่าของคำตอบที่เลือกและคำตอบที่ถูกต้องไปหน้าสรุป ----//
    private void savedWord(ArrayList<String> selectAnswer, ArrayList<String> answerRight, String scoreText) {
        Intent intent = new Intent(GameOneActivity.this, SumRoundOneActivity.class);
        intent.putExtra("arrayListAnswerSelect", selectAnswer);
        intent.putExtra("arrayListAnswerRight", answerRight);
        intent.putExtra("scoreRoundOne", scoreText);
        startActivity(intent);
        Log.d("score ", scoreText);

    }


    //---- แสดงข้อความ ----//
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

    //---- เมื่อกดแล้วจะเปลี่ยนข้อ ----//
    private void advance() {
        currentGameOneIndex = (currentGameOneIndex + 1) % gameOneArrayList.size();
        Log.d("currentGameOneIndex", String.valueOf(currentGameOneIndex));
        displayGameOne(currentGameOneIndex);
    }

    public void PlaySound() {
        soundGameOne = MediaPlayer.create(GameOneActivity.this, R.raw.sound_game_1);
        soundGameOne.start();

    }


    // ทำงานเมื่อเปิด app เข้ามา
    public void onResume() {
        super.onResume();
        soundGameOne.start();

    }

    // ทำงานเมื่อแอปหยุดทำงานชั่วคราว
    public void onPause() {
        super.onPause();
        soundGameOne.pause();
        soundGameOne.release();

    }

    // ทำงานเมื่อแอปถูปิดลง
    public void onDestroy() {
        super.onDestroy();


    }

    public void onBackPressed() {
        super.onBackPressed();

    }

    public void onStop() {
        super.onStop();
        if (soundGameOne.isPlaying()) {
            soundGameOne.stop();
            soundGameOne.release();
        }
    }


}