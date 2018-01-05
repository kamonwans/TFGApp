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

public class LowPitchedPageTwoFragment extends Fragment {
    ImageView imgBack;
    ImageView imgNext;
    TextView voice_to_tunk;
    TextView voice_no_nu;
    TextView voice_po_pan;
    TextView voice_fo_fun;
    TextView voice_po_suumpao;
    TextView voice_mo_mar;
    TextView voice_yo_yark;
    TextView voice_ro_rue;
    TextView voice_lo_ling;
    TextView voice_wo_wan;
    TextView voice_ro_jura;
    TextView voice_ho_nukhuk;
    MediaPlayer voice_low_pitched;

    public LowPitchedPageTwoFragment() {
        // Required empty public constructor
    }


    public static LowPitchedPageTwoFragment newInstance(String param1, String param2) {
        LowPitchedPageTwoFragment fragment = new LowPitchedPageTwoFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_low_pitched_page_two,
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
                FragmentThreeLetter();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentLowPitched();
            }
        });
        voice_to_tunk = (TextView) view.findViewById(R.id.voice_to_tunk);
        voice_to_tunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.to_tunk);
                voice_low_pitched.start();
            }
        });
        voice_no_nu = (TextView) view.findViewById(R.id.voice_no_nu);
        voice_no_nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.no_nu);
                voice_low_pitched.start();
            }
        });
        voice_po_pan = (TextView) view.findViewById(R.id.voice_po_pan);
        voice_po_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.po_pan);
                voice_low_pitched.start();
            }
        });
        voice_fo_fun = (TextView) view.findViewById(R.id.voice_fo_fun);
        voice_fo_fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.fo_fun);
                voice_low_pitched.start();
            }
        });
        voice_po_suumpao = (TextView) view.findViewById(R.id.voice_po_suumpao);
        voice_po_suumpao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.po_sumpao);
                voice_low_pitched.start();
            }
        });
        voice_mo_mar = (TextView) view.findViewById(R.id.voice_mo_mar);
        voice_mo_mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.mo_mar);
                voice_low_pitched.start();
            }
        });
        voice_yo_yark = (TextView) view.findViewById(R.id.voice_yo_yark);
        voice_yo_yark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.yo_yark);
                voice_low_pitched.start();
            }
        });
        voice_ro_rue = (TextView) view.findViewById(R.id.voice_ro_rue);
        voice_ro_rue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.ro_reu);
                voice_low_pitched.start();
            }
        });
        voice_lo_ling = (TextView) view.findViewById(R.id.voice_lo_ling);
        voice_lo_ling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.lo_ling);
                voice_low_pitched.start();
            }
        });
        voice_wo_wan = (TextView) view.findViewById(R.id.voice_wo_wan);
        voice_wo_wan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.wo_wan);
                voice_low_pitched.start();
            }
        });
        voice_ro_jura = (TextView) view.findViewById(R.id.voice_ro_jura);
        voice_ro_jura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.ro_jura);
                voice_low_pitched.start();
            }
        });
        voice_ho_nukhuk = (TextView) view.findViewById(R.id.voice_ho_nukhuk);
        voice_ho_nukhuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_low_pitched = MediaPlayer.create(getActivity(), R.raw.ho_nukhuk);
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
    private void FragmentLowPitched() {
        Fragment fragment = new LowPitchedFragment();
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
