package com.project.finalyear.thaispellinggame.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.finalyear.thaispellinggame.R;


public class SummaryRoundOneFragment extends Fragment {


    public SummaryRoundOneFragment() {
        // Required empty public constructor
    }


    public static SummaryRoundOneFragment newInstance(String param1, String param2) {
        SummaryRoundOneFragment fragment = new SummaryRoundOneFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary_round_one,
                container, false);
//        FragmentSummaryRoundOne();
        return view;
    }

//    public void FragmentSummaryRoundOne() {
//        Fragment fragment = new SummaryRoundOneFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.contentContainerRoundOne, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
