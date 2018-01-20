package com.project.finalyear.thaispellinggame.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.adapter.SmRoundOneAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static com.project.finalyear.thaispellinggame.model.Constants.FIRST_COLUMN;
import static com.project.finalyear.thaispellinggame.model.Constants.SECOND_COLUMN;

public class SumRoundOneActivity extends AppCompatActivity {
    ListView listViewAnswer;
    ArrayList<String> answerSelect;
    ArrayList<String> answerRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_rond_one);
        initInstance();
//        checkAnswer();

    }

    private void initInstance() {
        listViewAnswer = (ListView) findViewById(R.id.listViewAnswer);
        checkAnswer();
    }


    private void checkAnswer() {
        // create row and column
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        // ดึงค่าจาก ArrayList มาแสดง
        Bundle bundle = getIntent().getExtras();
        answerRight = bundle.getStringArrayList("arrayListAnswerRight");
        answerSelect = bundle.getStringArrayList("arrayListAnswerSelect");


        for (int j = 0; j < answerSelect.size(); j++) {
            temp = new HashMap<String, String>();  // new temp
            temp.put(FIRST_COLUMN, answerSelect.get(j).toString()); // add คำที่เลือกใส่ column
            temp.put(SECOND_COLUMN, answerRight.get(j).toString()); // add คำที่ถูกต้องใส่ column
            list.add(temp); // add temp ลงใน list(แถว)
            Log.d("sizeTemp", answerSelect.get(j).toString());
        }

        Log.d("temp", String.valueOf(temp.size()));
        Log.d("tempList", String.valueOf(list.size()));
        SmRoundOneAdapter adapter = new SmRoundOneAdapter(this, list);
        listViewAnswer.setAdapter(adapter);

    }

}
