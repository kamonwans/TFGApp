package com.project.finalyear.thaispellinggame.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.activity.GameOneActivity;

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
                Intent intent = new Intent(getActivity(), GameOneActivity.class);
                startActivity(intent);

            }
        });
//        initInstances(view);
        return view;
    }




//
//    private void initInstances(View view){
//
//        mName = (TextView) view.findViewById(R.id.tvProfile);
//        mDisplayImage = (CircleImageView) view.findViewById(R.id.profilePic);
//        tvRank = (TextView) view.findViewById(R.id.tvRank);
//        tvLevel = (TextView) view.findViewById(R.id.tvLevel);
//        tvScore = (TextView) view.findViewById(R.id.tvScore);
//        tvBestScore = (TextView) view.findViewById(R.id.tvBestScore);
//
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String  current_uid = mCurrentUser.getUid();
//
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
//
//        mUserDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                String name = dataSnapshot.child("name").getValue().toString();
//                String image = dataSnapshot.child("image").getValue().toString();
//                String status = dataSnapshot.child("status").getValue().toString();
//                String level = dataSnapshot.child("level").getValue().toString();
//                String rank = dataSnapshot.child("rank").getValue().toString();
//                String score = dataSnapshot.child("score").getValue().toString();
//                String bestScore = dataSnapshot.child("bestScore").getValue().toString();
//
//                mName.setText(name);
//                tvRank.setText(rank);
//                tvLevel.setText(level);
//                tvScore.setText(score);
//                tvBestScore.setText(bestScore);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////
////        if (user != null){
////            String name = user.getDisplayName();
////            String email = user.getEmail();
////            String uid = user.getUid();
////            String photoUrl = user.getPhotoUrl().toString();
////
////            setTvProfile(name);
////
////            //Picasso.with(this).load(photoUrl).into(profilePic);
////        }else {
////            //goLoginScreen();
////        }
////
//    }
//
//    public void setTvProfile(String text){
//        tvProfile.setText(text);
//    }





}
