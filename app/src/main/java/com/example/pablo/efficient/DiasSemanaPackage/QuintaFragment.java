package com.example.pablo.efficient.DiasSemanaPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pablo.efficient.HorarioPackage.Horario;
import com.example.pablo.efficient.HorarioPackage.horarioAdapter;
import com.example.pablo.efficient.HorarioPackage.horarioBD;
import com.example.pablo.efficient.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 03/08/2017.
 */

public class QuintaFragment extends Fragment {
    //Dividir horario
    private horarioBD bd;
    private ArrayList<Horario> listaHorarios;


    private static final String TAB = "QuintaFragment";


    public QuintaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_quinta, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new horarioBD(this.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        //Separador pra quarta
        ListView lista = (ListView) getView().findViewById(R.id.lvquinta);
        listaHorarios = bd.getAllQuinta();
        horarioAdapter adapter = new horarioAdapter(this.getActivity(), listaHorarios);
        lista.setAdapter(adapter);

    }

}