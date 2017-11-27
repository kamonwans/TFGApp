package com.project.finalyear.thaispellinggame.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.fragment.GameOneFragment;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentGameOne,new GameOneFragment());
        fragmentTransaction.commit();

    }



}
