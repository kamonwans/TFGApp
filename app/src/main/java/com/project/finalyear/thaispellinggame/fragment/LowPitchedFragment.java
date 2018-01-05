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
import android.widget.ImageView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;

public class LowPitchedFragment extends Fragment {

    ImageView imgNext;
    ImageView imgBack;
    TextView voice_kho_khway;
    TextView voice_kho_kon;
    TextView voice_kho_rakung;
    TextView voice_ngo_ngu;
    TextView voice_cho_chang;
    TextView voice_cho_cho;
    TextView voice_cho_cher;
    TextView voice_yo_ying;
    TextView voice_to_monto;
    TextView voice_to_putao;
    TextView voice_no_naen;
    TextView voice_to_tahan;
    MediaPlayer voice_low_pitched;

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

        voice_kho_khway = (TextView) view.findViewById(R.id.voice_kho_khway);
        voice_kho_khway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.kho__khway);
                voice_low_pitched.start();
            }
        });
        voice_kho_kon = (TextView) view.findViewById(R.id.voice_kho_kon);
        voice_kho_kon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.kho_kon);
                voice_low_pitched.start();
            }
        });
        voice_kho_rakung = (TextView) view.findViewById(R.id.voice_kho_rakung);
        voice_kho_rakung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.kho_rakung);
                voice_low_pitched.start();
            }
        });
        voice_ngo_ngu = (TextView) view.findViewById(R.id.voice_ngo_ngu);
        voice_ngo_ngu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.ngo_ngu);
                voice_low_pitched.start();
            }
        });
        voice_cho_chang = (TextView) view.findViewById(R.id.voice_cho_chang);
        voice_cho_chang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.cho_chang);
                voice_low_pitched.start();
            }
        });
        voice_cho_cho = (TextView) view.findViewById(R.id.voice_cho_cho);
        voice_cho_cho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.cho_cho);
                voice_low_pitched.start();
            }
        });
        voice_cho_cher = (TextView) view.findViewById(R.id.voice_cho_cher);
        voice_cho_cher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.cho_cher);
                voice_low_pitched.start();
            }
        });
        voice_yo_ying = (TextView) view.findViewById(R.id.voice_yo_ying);
        voice_yo_ying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.yo_ying);
                voice_low_pitched.start();
            }
        });
        voice_to_monto = (TextView) view.findViewById(R.id.voice_to_monto);
        voice_to_monto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.to_monto);
                voice_low_pitched.start();
            }
        });
        voice_to_putao = (TextView) view.findViewById(R.id.voice_to_putao);
        voice_to_putao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.to_putao);
                voice_low_pitched.start();
            }
        });
        voice_no_naen = (TextView) view.findViewById(R.id.voice_no_naen);
        voice_no_naen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.no_naen);
                voice_low_pitched.start();
            }
        });
        voice_to_tahan = (TextView) view.findViewById(R.id.voice_to_tahan);
        voice_to_tahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.to_tahan);
                voice_low_pitched.start();
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
