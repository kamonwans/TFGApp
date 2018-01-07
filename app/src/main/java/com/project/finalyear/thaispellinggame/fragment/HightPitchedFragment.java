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

public class HightPitchedFragment extends Fragment {
    ImageView imgNext;
    ImageView imgBack;
    TextView voiceKhoKhai;
    TextView voiceKhoKhod;
    TextView voiceChoChing;
    TextView voiceToTan;
    TextView voiceToTung;
    TextView voicePoPueng;
    TextView voiceFoFha;
    TextView voiceSoSara;
    TextView voiceSoRuesee;
    TextView voiceSoSue;
    TextView voiceHoHeeb;
    MediaPlayer voiceHightPitched;

    public HightPitchedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hight_pitched,
                container, false);
        initInstances(view);
        return view;
    }


    private void initInstances(View view) {
        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgNext = (ImageView) view.findViewById(R.id.imgNext);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentMidrange();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThreeLetter();
            }
        });
        voiceKhoKhai = (TextView) view.findViewById(R.id.voiceKhoKhai);
        voiceKhoKhai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.kho_khai);
                voiceHightPitched.start();
            }
        });

        voiceKhoKhod = (TextView) view.findViewById(R.id.voiceKhoKhod);
        voiceKhoKhod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.kho_khod);
                voiceHightPitched.start();
            }
        });
        voiceChoChing = (TextView) view.findViewById(R.id.voiceChoChing);
        voiceChoChing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.cho_ching);
                voiceHightPitched.start();
            }
        });
        voiceToTan = (TextView) view.findViewById(R.id.voiceToTan);
        voiceToTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.to_tan);
                voiceHightPitched.start();

            }
        });
        voiceToTung = (TextView) view.findViewById(R.id.voiceToTung);
        voiceToTung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched =  Util.playMediaSound(getActivity(), R.raw.to_tung);
                voiceHightPitched.start();

            }
        });
        voicePoPueng = (TextView) view.findViewById(R.id.voicePoPueng);
        voicePoPueng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.po_pueng);
                voiceHightPitched.start();

            }
        });
        voiceFoFha = (TextView) view.findViewById(R.id.voiceFoFha);
        voiceFoFha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.fo_fha);
                voiceHightPitched.start();

            }
        });
        voiceSoSara = (TextView) view.findViewById(R.id.voiceSoSara);
        voiceSoSara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched = Util.playMediaSound(getActivity(), R.raw.so_sara);
                voiceHightPitched.start();

            }
        });
        voiceSoRuesee = (TextView) view.findViewById(R.id.voiceSoRuesee);
        voiceSoRuesee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched =  Util.playMediaSound(getActivity(), R.raw.so_ruesee);
                voiceHightPitched.start();

            }
        });
        voiceSoSue = (TextView) view.findViewById(R.id.voiceSoSue);
        voiceSoSue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched =  Util.playMediaSound(getActivity(), R.raw.so_seu);
                voiceHightPitched.start();

            }
        });
        voiceHoHeeb = (TextView) view.findViewById(R.id.voiceHoHeeb);
        voiceHoHeeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
               voiceHightPitched =  Util.playMediaSound(getActivity(), R.raw.ho_heeb);
                voiceHightPitched.start();

            }
        });
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
        voiceHightPitched.pause();
        voiceHightPitched.release();

    }
    // ทำงานเมื่อแอปถูปิดลง
    public void onDestroy() {
        super.onDestroy();
        voiceHightPitched.stop();
        voiceHightPitched.release();
        voiceHightPitched = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void FragmentMidrange() {
        Fragment fragment = new MidrangeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void FragmentThreeLetter() {
        Fragment fragment = new ThreeLettersFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
