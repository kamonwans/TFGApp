package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.adapter.SumRoundOneAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.project.finalyear.thaispellinggame.model.Constants.FIRST_COLUMN;
import static com.project.finalyear.thaispellinggame.model.Constants.SECOND_COLUMN;

public class SumRoundOneActivity extends AppCompatActivity {
    ListView listViewAnswer;
    ArrayList<String> answerSelect;
    ArrayList<String> answerRight;
    ImageView imgCorrect;
    ImageView imgIncorrect;
    TextView tvAnswerSelect;
    CountDownTimer countDownTimer;
    TextView tvTimer;
    ArrayList<String> totalScoreOne = new ArrayList<String>();
    String totalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_rond_one);
        initInstance();


    }

    private void initInstance() {
        listViewAnswer = (ListView) findViewById(R.id.listViewAnswer);
//        imgCorrect = (ImageView) findViewById(R.id.imgCorrect);
//        imgIncorrect = (ImageView) findViewById(R.id.imgIncorrect);
        tvAnswerSelect = (TextView) findViewById(R.id.tvAnswerSelect);
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        checkAnswer();
        countDown();
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                String time = String.format("%d", l / 1000);
                tvTimer.setText(String.valueOf(time));

            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                Intent intent = new Intent(SumRoundOneActivity.this, SummaryActivity.class);
                intent.putExtra("scoreRoundOne", totalScore);
                Log.d("totalScore", totalScore);
                startActivity(intent);
            }
        }.start();
    }

    private void checkAnswer() {
        // create row and column
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        // ดึงค่าจาก ArrayList มาแสดง
        Bundle bundle = getIntent().getExtras();
        answerRight = bundle.getStringArrayList("arrayListAnswerRight");
        answerSelect = bundle.getStringArrayList("arrayListAnswerSelect");
        totalScore = bundle.getString("scoreRoundOne");

        for (int j = 0; j < answerSelect.size(); j++) {
            temp = new HashMap<String, String>();  // new temp
            temp.put(FIRST_COLUMN, answerSelect.get(j).toString()); // add คำที่เลือกใส่ column
            temp.put(SECOND_COLUMN, answerRight.get(j).toString()); // add คำที่ถูกต้องใส่ column
            list.add(temp); // add temp ลงใน list(แถว)
            Log.d("sizeTemp", answerSelect.get(j).toString());


        }

        Log.d("temp", String.valueOf(temp.size()));
        Log.d("tempList", String.valueOf(list.size()));
        SumRoundOneAdapter adapter = new SumRoundOneAdapter(this, list);
        listViewAnswer.setAdapter(adapter);

    }

}