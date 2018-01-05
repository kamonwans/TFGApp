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

public class MidrangeFragment extends Fragment {
    ImageView imgNext;
    ImageView imgBack;
    TextView voice_ao_arng;
    TextView voice_ko_kai;
    TextView voice_jo_jan;
    TextView voice_do_chada;
    TextView voice_to_patuk;
    TextView voice_do_dek;
    TextView voice_to_tao;
    TextView voice_bo_baimai;
    TextView voice_po_pla;
    MediaPlayer voice_mid_pitched;

    public MidrangeFragment() {
        // Required empty public constructor
    }


    public static MidrangeFragment newInstance(String param1, String param2) {
        MidrangeFragment fragment = new MidrangeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_midrange,
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
                FragmentLowPitched();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHighPitched();
            }
        });

        voice_jo_jan = (TextView) view.findViewById(R.id.voice_jo_jan);
        voice_jo_jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.jo_jan);
                voice_mid_pitched.start();
            }
        });
        voice_ko_kai = (TextView) view.findViewById(R.id.voice_ko_kai);
        voice_ko_kai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.ko_kai);
                voice_mid_pitched.start();
            }
        });
        voice_do_chada = (TextView) view.findViewById(R.id.voice_do_chada);
        voice_do_chada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.do_chada);
                voice_mid_pitched.start();
            }
        });
        voice_do_dek = (TextView) view.findViewById(R.id.voice_do_dek);
        voice_do_dek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.do_dek);
                voice_mid_pitched.start();
            }
        });
        voice_to_tao = (TextView) view.findViewById(R.id.voice_to_tao);
        voice_to_tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.to_tao);
                voice_mid_pitched.start();
            }
        });
        voice_to_patuk = (TextView) view.findViewById(R.id.voice_to_patuk);
        voice_to_patuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.to_patuk);
                voice_mid_pitched.start();
            }
        });
        voice_bo_baimai = (TextView) view.findViewById(R.id.voice_bo_baimai);
        voice_bo_baimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.bo_baimai);
                voice_mid_pitched.start();
            }
        });
        voice_po_pla = (TextView) view.findViewById(R.id.voice_po_pla);
        voice_po_pla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.po_pla);
                voice_mid_pitched.start();
            }
        });
        voice_ao_arng = (TextView) view.findViewById(R.id.voice_ao_arng);
        voice_ao_arng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voice_mid_pitched = MediaPlayer.create(getActivity(), R.raw.ao_arng);
                voice_mid_pitched.start();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void FragmentLowPitched() {
        Fragment fragment = new LowPitchedFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void FragmentHighPitched() {
        Fragment fragment = new HightPitchedFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
