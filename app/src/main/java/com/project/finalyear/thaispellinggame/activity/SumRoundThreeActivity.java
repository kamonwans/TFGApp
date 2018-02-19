package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.adapter.SumRoundOneAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.project.finalyear.thaispellinggame.model.Constants.FIRST_COLUMN;
import static com.project.finalyear.thaispellinggame.model.Constants.SECOND_COLUMN;


public class SumRoundThreeActivity extends AppCompatActivity {
    ListView listViewAnswer;
    TextView tvAnswerSelect;
    TextView tvTimer;
    String totalScore;

    CountDownTimer countDownTimer;
    ArrayList<String> playerAnswer = new ArrayList<>();
    ArrayList<String> correctAnswer = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_round_three);
        initInstance();
    }

    private void initInstance() {
        listViewAnswer = (ListView) findViewById(R.id.listViewAnswer);
        tvAnswerSelect = (TextView) findViewById(R.id.tvAnswerSelect);
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        showAnswer();
        countDownTimer();
    }


    private void countDownTimer() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                String time = String.format("%d", l / 1000);
                tvTimer.setText(String.valueOf(time));

            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                Intent intent = new Intent(SumRoundThreeActivity.this, GameOneActivity.class);
//                intent.putExtra("scoreRoundOne", totalScore);
//                Log.d("totalScore", totalScore);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void showAnswer() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();

        Bundle bundle = getIntent().getExtras();
        playerAnswer = bundle.getStringArrayList("playerAnswer");
        correctAnswer = bundle.getStringArrayList("rightAnswer");
        totalScore = bundle.getString("scoreRoundThree");

        for (int i = 0; i < playerAnswer.size(); i++) {
            temp = new HashMap<String,String>();  // new temp
            temp.put(FIRST_COLUMN, playerAnswer.get(i).toString()); // add คำที่เลือกใส่ column
            temp.put(SECOND_COLUMN, correctAnswer.get(i).toString()); // add คำที่ถูกต้องใส่ column
            list.add(temp); // add temp ลงใน list(แถว)
        }

        SumRoundOneAdapter adapter = new SumRoundOneAdapter(this, list);
        listViewAnswer.setAdapter(adapter);
    }

}
