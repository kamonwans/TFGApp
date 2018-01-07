package com.project.finalyear.thaispellinggame.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.common.Util;

public class LowPitchedFragment extends Fragment {

    ImageView imgNext;
    ImageView imgBack;
    TextView voiceKhoKhway;
    TextView voiceKhoKon;
    TextView voiceKhoRakung;
    TextView voiceNgoNgu;
    TextView voiceChoChang;
    TextView voiceChoCho;
    TextView voiceChoCher;
    TextView voiceYoYing;
    TextView voiceToMonto;
    TextView voiceToPutao;
    TextView voiceNoNaen;
    TextView voiceToTahan;
    MediaPlayer voiceLowPitched;

    public LowPitchedFragment() {
        // Required empty public constructor
    }


    public static LowPitchedFragment newInstance(String param1, String param2) {
        LowPitchedFragment fragment = new LowPitchedFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_low_pitched,
                container, false);
        initInstances(view);
        return view;
    }

    private void initInstances(View view) {
        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        imgNext = (ImageView) view.findViewById(R.id.imgNext);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentLowPitchedPageTwo();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentMidrange();
            }
        });

        voiceKhoKhway = (TextView) view.findViewById(R.id.voiceKhoKhway);
        voiceKhoKhway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.kho__khway);
                voiceLowPitched.start();
            }
        });
        voiceKhoKon = (TextView) view.findViewById(R.id.voiceKhoKon);
        voiceKhoKon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.kho_kon);
                voiceLowPitched.start();
            }
        });
        voiceKhoRakung = (TextView) view.findViewById(R.id.voiceKhoRakung);
        voiceKhoRakung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.kho_rakung);
                voiceLowPitched.start();
            }
        });
        voiceNgoNgu = (TextView) view.findViewById(R.id.voiceNgoNgu);
        voiceNgoNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.ngo_ngu);
                voiceLowPitched.start();
            }
        });
        voiceChoChang = (TextView) view.findViewById(R.id.voiceChoChang);
        voiceChoChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.cho_chang);
                voiceLowPitched.start();
            }
        });
        voiceChoCho = (TextView) view.findViewById(R.id.voiceChoCho);
        voiceChoCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.cho_cho);
                voiceLowPitched.start();
            }
        });
        voiceChoCher = (TextView) view.findViewById(R.id.voiceChoCher);
        voiceChoCher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.cho_cher);
                voiceLowPitched.start();
            }
        });
        voiceYoYing = (TextView) view.findViewById(R.id.voiceYoYing);
        voiceYoYing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.yo_ying);
                voiceLowPitched.start();
            }
        });
        voiceToMonto = (TextView) view.findViewById(R.id.voiceToMonto);
        voiceToMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.to_monto);
                voiceLowPitched.start();
            }
        });
        voiceToPutao = (TextView) view.findViewById(R.id.voiceToPutao);
        voiceToPutao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.to_putao);
                voiceLowPitched.start();
            }
        });
        voiceNoNaen = (TextView) view.findViewById(R.id.voiceNoNaen);
        voiceNoNaen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.no_naen);
                voiceLowPitched.start();
            }
        });
        voiceToTahan = (TextView) view.findViewById(R.id.voiceToTahan);
        voiceToTahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceLowPitched = Util.playMediaSound(getActivity(), R.raw.to_tahan);
                voiceLowPitched.start();
            }
        });
    }

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

    public void onPause() {
        super.onPause();
        voiceLowPitched.pause();
        voiceLowPitched.release();

    }

    // ทำงานเมื่อแอปถูปิดลง
    public void onDestroy() {
        super.onDestroy();
        voiceLowPitched.stop();
        voiceLowPitched.release();
        voiceLowPitched = null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void FragmentLowPitchedPageTwo() {
        Fragment fragment = new LowPitchedPageTwoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void FragmentMidrange() {
        Fragment fragment = new MidrangeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
