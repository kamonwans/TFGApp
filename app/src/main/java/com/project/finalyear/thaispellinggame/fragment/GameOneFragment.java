package com.project.finalyear.thaispellinggame.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.activity.GameOne;

import java.util.ArrayList;


public class GameOneFragment extends Fragment {


    CountDownTimer countDownTimer;
    TextView tvTimer, tvMeaning;
    Button btnChoiceOne, btnChoiceTwo, btnChoiceThree;
    private Firebase firebase;
    private String wordMeaning, wordBtnOne, wordBtnTwo, wordBtnThree;
    private static final String URL_Firebase = "https://thaispellinggame-28cfe.firebaseio.com/Game_one";
    private int currentGameOneIndex;
    private ArrayList<GameOne> gameOneArrayList;
    private String selectedWords;

    public GameOneFragment() {
        // Required empty public constructor
    }


    public static GameOneFragment newInstance(String param1, String param2) {
        GameOneFragment fragment = new GameOneFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_one,
                container, false);
        Firebase.setAndroidContext(getContext());
        initInstance(view);

        return view;
    }

    private void initInstance(View view) {
        tvTimer = (TextView) view.findViewById(R.id.tvTimer);
        btnChoiceOne = (Button) view.findViewById(R.id.btnChoiceOne);
        btnChoiceTwo = (Button) view.findViewById(R.id.btnChoiceTwo);
        btnChoiceThree = (Button) view.findViewById(R.id.btnChoiceThree);
        tvMeaning = (TextView) view.findViewById(R.id.tvMeaning);
        firebase = new Firebase(URL_Firebase);
        dataPull();
        CountDownTimer();
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
        selectedWords = btnChoiceOne.getText().toString();
        selectedWords = btnChoiceTwo.getText().toString();
        selectedWords = btnChoiceThree.getText().toString();

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
//                FragmentSummaryRoundOne();


            }
        }.start();
    }
//    public void FragmentSummaryRoundOne() {
//        Fragment fragment = new SummaryRoundOneFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.content_main, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
