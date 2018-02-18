package com.project.finalyear.thaispellinggame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.activity.GameFiveActivity;
import com.project.finalyear.thaispellinggame.activity.GameOneActivity;
import com.project.finalyear.thaispellinggame.activity.GameThreeActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment{

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    // Android Layout
    private CircleImageView mDisplayImage;
    private TextView mName;
    private TextView tvRank,tvLevel,tvScore, tvBestScore;
    private Button btnPlayGame;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);

        btnPlayGame = (Button) view.findViewById(R.id.btnPlayGame);
        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameThreeActivity.class);
                startActivity(intent);


            }
        });
        return view;
    }

//    private void FragmentGameOne() {
//        Fragment fragment = new GameOneFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.content_main, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }


}
