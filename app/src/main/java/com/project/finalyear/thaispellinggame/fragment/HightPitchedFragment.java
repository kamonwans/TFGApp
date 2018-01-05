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

public class HightPitchedFragment extends Fragment {
    ImageView imgNext;
    ImageView imgBack;
    TextView voice_kho_khai;
    TextView voice_kho_khod;
    TextView voice_cho_ching;
    TextView voice_to_tan;
    TextView voice_to_tung;
    TextView voice_po_pueng;
    TextView voice_fo_fha;
    TextView voice_so_sara;
    TextView voice_so_ruesee;
    TextView voice_so_sue;
    TextView voice_ho_heeb;
    MediaPlayer voice_hight_pitched;

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
        voice_kho_khai = (TextView) view.findViewById(R.id.voice_kho_khai);
        voice_kho_khai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.kho_khai);
                voice_hight_pitched.start();

            }
        });
        voice_kho_khod = (TextView) view.findViewById(R.id.voice_kho_khod);
        voice_kho_khod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.kho_khod);
                voice_hight_pitched.start();

            }
        });
        voice_cho_ching = (TextView) view.findViewById(R.id.voice_cho_ching);
        voice_cho_ching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.cho_ching);
                voice_hight_pitched.start();

            }
        });
        voice_to_tan = (TextView) view.findViewById(R.id.voice_to_tan);
        voice_to_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.to_tan);
                voice_hight_pitched.start();

            }
        });
        voice_to_tung = (TextView) view.findViewById(R.id.voice_to_tung);
        voice_to_tung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.to_tung);
                voice_hight_pitched.start();

            }
        });
        voice_po_pueng = (TextView) view.findViewById(R.id.voice_po_pueng);
        voice_po_pueng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.po_pueng);
                voice_hight_pitched.start();

            }
        });
        voice_fo_fha = (TextView) view.findViewById(R.id.voice_fo_fha);
        voice_fo_fha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.fo_fha);
                voice_hight_pitched.start();

            }
        });
        voice_so_sara = (TextView) view.findViewById(R.id.voice_so_sara);
        voice_so_sara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.so_sara);
                voice_hight_pitched.start();

            }
        });
        voice_so_ruesee = (TextView) view.findViewById(R.id.voice_so_ruesee);
        voice_so_ruesee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.so_ruesee);
                voice_hight_pitched.start();

            }
        });
        voice_so_sue = (TextView) view.findViewById(R.id.voice_so_sue);
        voice_so_sue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.so_seu);
                voice_hight_pitched.start();

            }
        });
        voice_ho_heeb = (TextView) view.findViewById(R.id.voice_ho_heeb);
        voice_ho_heeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_hight_pitched = MediaPlayer.create(getActivity(), R.raw.ho_heeb);
                voice_hight_pitched.start();

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
