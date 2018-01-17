package com.project.finalyear.thaispellinggame.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.adapter.SmRoundOneAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.project.finalyear.thaispellinggame.model.Constants.FIRST_COLUMN;
import static com.project.finalyear.thaispellinggame.model.Constants.SECOND_COLUMN;

public class SumRondOneActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> list;
    ListView listViewAnswer;
    TextView tvAnswerSelect;
    TextView tvAnswerRight;
    TextView tv;
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
        // ดึงค่าจาก ArrayList มาแสดง

        Bundle bundle = getIntent().getExtras();
        answerRight = bundle.getStringArrayList("arrayListAnswerRight");
        answerSelect = bundle.getStringArrayList("arrayListAnswerSelect");

      //  String select = answerSelect.toString();
//        String right = answerRight.toString();
        list = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> temp = new HashMap<String, String>();
            for (int i=0;i<answerSelect.size();i++) {
                String select = answerSelect.get(i).toString();
                temp.put(FIRST_COLUMN, select);
            }
            for (int j=0;j<answerRight.size();j++) {
                String right = answerRight.get(j).toString();
                temp.put(SECOND_COLUMN, right);
            }


            list.add(temp);



//
//        HashMap<String, String> temp1 = new HashMap<String, String>();
//        temp1.put(FIRST_COLUMN, select);
//        temp1.put(SECOND_COLUMN, right);
//        list.add(temp1);
//        SmRoundOneAdapter adapter = new SmRoundOneAdapter(this, list);
//        listViewAnswer.setAdapter(adapter);


//
//        ArrayAdapter<String> answerSelectAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, answerSelect);
//        listViewAnswer.setAdapter(answerSelectAdapter); // set ลง listView
//        ArrayAdapter<String> answerRightAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, answerRight);
//        listViewAnswerRight.setAdapter(answerRightAdapter);

    }

}
