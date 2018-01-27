package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

TextView tvSumRoundOneUser1;
String totalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        initInstance();
    }

    private void initInstance() {
        tvSumRoundOneUser1 = (TextView) findViewById(R.id.tvSumRoundOneUser1);
        totalScore();

        tvSumRoundOneUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SummaryActivity.this, GameThreeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void totalScore() {
        Bundle bundle = getIntent().getExtras();
        totalScore = bundle.getString("scoreRoundOne");
        tvSumRoundOneUser1.setText(totalScore);

    }
}
