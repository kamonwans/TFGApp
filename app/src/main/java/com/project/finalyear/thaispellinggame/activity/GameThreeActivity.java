package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.common.Util;
import com.project.finalyear.thaispellinggame.controller.GameThreeController;
import com.project.finalyear.thaispellinggame.model.GameThree;

import java.util.ArrayList;
import java.util.Random;

import static android.view.ViewGroup.*;
import static java.lang.Thread.sleep;


public class GameThreeActivity extends AppCompatActivity implements GameThreeController.GameThreeCallBack {
    CountDownTimer countDownTimer;
    TextView tvTimer;
    RelativeLayout ballOne, ballTwo, ballThree;
    TextView tvOne, tvTwo, tvThree;
    private ArrayList<GameThree> gameThreeArrayList;
    String typeBallOne;
    String typeBallTwo;
    String typeBallThree;
    int score;
    int counter = 0;
    String scoreText;
    ImageView imgIconOne;
    ImageView imgIconTwo;
    TextView tvScoreOne;
    ImageView imgBonus, imgMusic, imgNotMusic, imgEffect, imgNotEffect;
    MediaPlayer soundCorrect, soundWrong, soundGameThree, soundWow;
    int height;
    ProgressBar progressBarLoad;
    private GameThreeController gameThreeController;
    ArrayList<String> playerAnswer = new ArrayList<String>();
    ArrayList<String> answerRight = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        PlaySound();
        initInstance();
        gameThreeController = new GameThreeController(this);
        loadDataGameThree();

    }

    private void initInstance() {

        ballOne = (RelativeLayout) findViewById(R.id.ballOne);
        ballTwo = (RelativeLayout) findViewById(R.id.ballTwo);
        ballThree = (RelativeLayout) findViewById(R.id.ballThree);

        tvOne = (TextView) findViewById(R.id.tvOne);
        tvTwo = (TextView) findViewById(R.id.tvTwo);
        tvThree = (TextView) findViewById(R.id.tvThree);

        imgIconOne = (ImageView) findViewById(R.id.imgIconOne);
        imgIconTwo = (ImageView) findViewById(R.id.imgIconTwo);
        imgBonus = (ImageView) findViewById(R.id.imgBonus);
        tvScoreOne = (TextView) findViewById(R.id.tvScoreOne);
        imgMusic = (ImageView) findViewById(R.id.imgMusic);
        imgEffect = (ImageView) findViewById(R.id.imgEffect);
        imgNotMusic = (ImageView) findViewById(R.id.imgNotMusic);
        imgNotEffect = (ImageView) findViewById(R.id.imgNotEffect);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        progressBarLoad = (ProgressBar) findViewById(R.id.progressBarLoad);

        // pullData();

        height = getWindowManager().getDefaultDisplay().getHeight();
        Log.d("heightDisplay", String.valueOf(height));


        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgMusic.setVisibility(View.INVISIBLE);
                imgNotMusic.setVisibility(View.VISIBLE);
                soundGameThree.pause();
            }
        });

        imgNotMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNotMusic.setVisibility(View.INVISIBLE);
                imgMusic.setVisibility(View.VISIBLE);
                soundGameThree.start();


            }
        });
        imgEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEffect.setVisibility(View.INVISIBLE);
                imgNotEffect.setVisibility(View.VISIBLE);
                soundCorrect = Util.playMediaSound(GameThreeActivity.this, R.raw.correct);
                soundWow = Util.playMediaSound(GameThreeActivity.this, R.raw.wow);
                soundWrong = Util.playMediaSound(GameThreeActivity.this, R.raw.wrong);
                soundCorrect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                });
                soundWow.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                });
                soundWrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                });


            }
        });
        imgNotEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNotEffect.setVisibility(View.INVISIBLE);
                imgEffect.setVisibility(View.VISIBLE);


            }
        });

        ballOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtOne = tvOne.getText().toString();
                String typeTxtOne = typeBallOne.toString();
                //----- ส่งค่า คำศัพท์ที่ลือก , typeBallOne ของคำศัพท์ ----//
                checkAnswer(txtOne, typeTxtOne);
                final Animation animation = AnimationUtils.loadAnimation(GameThreeActivity.this, R.anim.move);
                ballOne.setAnimation(animation);
                marginTopBallOne = -100;
                // setText ข้อใหม่
                setTextToBallOne(gameThreeArrayList);

            }
        });

        ballTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtTwo = tvTwo.getText().toString();
                String typeTxtTwo = typeBallTwo.toString();
                checkAnswer(txtTwo, typeTxtTwo);
                final Animation animation = AnimationUtils.loadAnimation(GameThreeActivity.this, R.anim.move);
                ballTwo.setAnimation(animation);
                marginTopBallTwo = -150;
                setTextToBallTwo(gameThreeArrayList);
            }
        });


        ballThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtThree = tvThree.getText().toString();
                String typeTxtThree = typeBallThree.toString();
                checkAnswer(txtThree, typeTxtThree);
                final Animation animation = AnimationUtils.loadAnimation(GameThreeActivity.this, R.anim.move);
                ballThree.setAnimation(animation);
                marginTopBallThree = -50;
                setTextToBallThree(gameThreeArrayList);
            }
        });



    }

    private void countDownTimer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%d", millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                saveWord(playerAnswer, answerRight, scoreText);
                soundGameThree.stop();
            }
        }.start();

    }

    private void loadDataGameThree() {
        progressBarLoad.setVisibility(VISIBLE);
        // เรียก pullData จาก GameThreeController
        gameThreeController.pullData();
    }

    private int marginTopBallOne = -100;
    private int marginTopBallTwo = -30;
    private int marginTopBallThree = -10;

    private void fallingBallOne() {
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ballOne.getLayoutParams();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                marginTopBallOne += 15;
                params.setMargins(10, marginTopBallOne, 0, 0);
                ballOne.setLayoutParams(params);
                handler.postDelayed(this, 35);//

                // check marginTop > ขนาดหน้าจอ ให้ marginTop = -100
                if (marginTopBallOne > (height - 1000)) {
                    marginTopBallOne = -100;
                }
            }
        };

        handler.postDelayed(runnable, 100);

    }
    private void fallingBallTwo() {
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ballTwo.getLayoutParams();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                marginTopBallTwo += 15;
                params.setMargins(50, marginTopBallTwo, 0, 0);
                ballTwo.setLayoutParams(params);
                handler.postDelayed(this, 35);
                // check marginTop > ขนาดหน้าจอ ให้ marginTop = -100
                if (marginTopBallTwo > (height - 1000)) {
                    marginTopBallTwo = -100;
                }
            }
        };

        handler.postDelayed(runnable, 100);

    }
    private void fallingBallThree() {
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ballThree.getLayoutParams();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                marginTopBallThree += 15;
                params.setMargins(100, marginTopBallThree, 0, 0);
                ballThree.setLayoutParams(params);
                handler.postDelayed(this, 35);
                // check marginTop มากกว่าขนาดหน้าจอ ให้ marginTop = -100
                if (marginTopBallThree > (height - 1000)) {
                    marginTopBallThree = -100;
                }
            }
        };
        handler.postDelayed(runnable, 100);

    }


    private void checkAnswer(String text, String typeClick) {
        String answer = text.toString();

        String typeAnswer = typeClick.toString();
        Log.d("typeClick", typeAnswer);
        Log.d("answerClick", answer);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);

        // ถ้าตอบถูก
        if (typeAnswer.equals("correct")) {
            imgIconOne.setVisibility(View.VISIBLE);
            imgIconTwo.setVisibility(View.INVISIBLE);
            imgIconOne.startAnimation(animation);
            score = score + 50;
            counter++;
            scoreText = Integer.toString(score);
            tvScoreOne.setText(scoreText);
            soundCorrect = Util.playMediaSound(this, R.raw.correct);
            soundCorrect.start();
            Log.d("score", String.valueOf(tvScoreOne));

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
            } else if (counter != 5) {
                imgBonus.setVisibility(View.INVISIBLE);
            }
            playerAnswer.add("ถูก");
            answerRight.add(answer);

            // ถ้าตอบผิด
        } else if (!typeAnswer.equals("correct")) {
            imgIconTwo.setVisibility(View.VISIBLE);
            imgIconOne.setVisibility(View.INVISIBLE);
            imgIconTwo.startAnimation(animation);
            counter = 0;
            soundWrong = Util.playMediaSound(this, R.raw.wrong);
            soundWrong.start();
            imgBonus.setVisibility(View.INVISIBLE);

            playerAnswer.add("ผิด");
            answerRight.add(answer);
        }
    }

    private void saveWord(ArrayList<String> playerAnswer, ArrayList<String> answerRight, String scoreText) {
        Intent intent = new Intent(GameThreeActivity.this, SumRoundThreeActivity.class);
        intent.putExtra("playerAnswer", playerAnswer);
        intent.putExtra("rightAnswer", answerRight);
        intent.putExtra("scoreRoundThree", scoreText);
        startActivity(intent);
    }

    public void PlaySound() {
        soundGameThree = MediaPlayer.create(GameThreeActivity.this, R.raw.sound_game_3);
        soundGameThree.start();
    }

    // ทำงานเมื่อเปิด app เข้ามาใหม่
    public void onResume() {
        super.onResume();
        soundGameThree.start();
    }

    // ทำงานเมื่อแอปหยุดทำงานชั่วคราว
    public void onPause() {
        soundGameThree.pause();

        super.onPause();
    }

    // ทำงานเมื่อแอปถูปิดลง
    public void onDestroy() {
        super.onDestroy();

        soundCorrect.stop();
        soundCorrect.release();
        soundCorrect = null;

        soundWrong.stop();
        soundWrong.release();
        soundWrong = null;

        soundWow.stop();
        soundWow.release();
        soundWow = null;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onStop() {
        super.onStop();

    }

    private void setTextToBallOne(ArrayList<GameThree> gameThreeArrayList) {
        Random random = new Random();
        //  random ball one
        for (int i = 0; i < 1; i++) {
            int section = random.nextInt(gameThreeArrayList.size());
            Log.d("section", String.valueOf(section));
            tvOne.setText(gameThreeArrayList.get(section).getWord());
            typeBallOne = gameThreeArrayList.get(section).getType();
            Log.d("typeCorrect : ", typeBallOne);
        }


    }

    private void setTextToBallTwo(ArrayList<GameThree> gameThreeArrayList) {
        // random ball Two
        Random random = new Random();
        for (int j = 0; j < 1; j++) {
            int section1 = random.nextInt(gameThreeArrayList.size());
            Log.d("section1", String.valueOf(section1));
            tvTwo.setText(gameThreeArrayList.get(section1).getWord());
            typeBallTwo = gameThreeArrayList.get(section1).getType();
            Log.d("typeCorrect1 : ", typeBallTwo);
        }
    }

    private void setTextToBallThree(ArrayList<GameThree> gameThreeArrayList) {
        Random random = new Random();
        //random ball three
        for (int k = 0; k < 1; k++) {
            int section2 = random.nextInt(gameThreeArrayList.size());
            Log.d("section2", String.valueOf(section2));
            tvThree.setText(gameThreeArrayList.get(section2).getWord());
            typeBallThree = gameThreeArrayList.get(section2).getType();
            Log.d("typeCorrect2 : ", typeBallThree);
        }

    }

    @Override
    public void displayGameThree(int index, ArrayList<GameThree> gameThreeArrayList) {
        progressBarLoad.setVisibility(INVISIBLE);
        this.gameThreeArrayList = gameThreeArrayList;
        countDownTimer();
        setTextToBallOne(gameThreeArrayList);
        setTextToBallTwo(gameThreeArrayList);
        setTextToBallThree(gameThreeArrayList);


        fallingBallOne();
        fallingBallTwo();
        fallingBallThree();

    }

    @Override
    public void onCancel(String messageError) {
        //TODO : Dialog show error
    }
}
