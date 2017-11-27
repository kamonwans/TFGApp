package com.project.finalyear.thaispellinggame.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.adapter.RoundOneAdapter;
import com.project.finalyear.thaispellinggame.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SummaryRoundOneActivity extends AppCompatActivity {
    private static final String URL_Firebase = "https://thaispellinggame-28cfe.firebaseio.com/Game_one";
    private Firebase firebase;
    TextView tvCorrectAnswerUser1;
    RecyclerView recyclerViewRoundOne, rvCorrectAnswerser1, rvCorrectAnswerser2;
    private List<RoundOneModel> roundOneModels;
    private RoundOneAdapter adapter;
    //    private ArrayList<SummaryRoundOne> summaryRoundOneArrayList;
    private int currentRoundOne;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_round_one);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Game_one");
        Firebase.setAndroidContext(this);
        initInstance();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initInstance() {
        firebase = new Firebase(URL_Firebase);
        roundOneModels = new ArrayList<>();
        recyclerViewRoundOne = (RecyclerView) findViewById(R.id.recyclerViewRoundOne);
        rvCorrectAnswerser1 = (RecyclerView) findViewById(R.id.rvCorrectAnswerUser1);
        recyclerViewRoundOne.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewRoundOne.setLayoutManager(linearLayoutManager);
        createResult();
        adapter = new RoundOneAdapter(roundOneModels);
        recyclerViewRoundOne.setAdapter(adapter);
        updateList();

    }


    private void createResult() {

        roundOneModels.add(new RoundOneModel(""));
    }

    private void updateList() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                roundOneModels.add(dataSnapshot.getValue(RoundOneModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                RoundOneModel model = dataSnapshot.getValue(RoundOneModel.class);
                int index = getItemIndex(model);
                roundOneModels.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
                RoundOneModel model = dataSnapshot.getValue(RoundOneModel.class);
                int index = getItemIndex(model);
                roundOneModels.set(index, model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(RoundOneModel answer) {
        int index = -1;
        for (int i = 1; i < roundOneModels.size(); i++) {

        }
        return index;
    }


    private void checkAnswer() {

        ArrayList<String> arrayList = getIntent().getExtras().getStringArrayList("arrayList");
            for (int i = 0; i < arrayList.size(); i++) {


        }

    }

}
