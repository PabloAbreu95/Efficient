package br.efficient.DiasSemanaPackage.DiasSemanaPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.efficient.HorarioPackage.Horario;
import br.efficient.HorarioPackage.horarioAdapter;
import br.efficient.HorarioPackage.horarioBD;
import br.efficient.HorarioPackage.showHorario;
import br.efficient.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 03/08/2017.
 */

public class SextaFragment extends Fragment {
    //Dividir horario
    private horarioBD bd;
    private ArrayList<Horario> listaHorarios;


    private static final String TAB = "SextaFragment";


    public SextaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_sexta, container, false);
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
        ListView lista = (ListView) getView().findViewById(R.id.lvsexta);
        listaHorarios = bd.getAllSexta();
        horarioAdapter adapter = new horarioAdapter(this.getActivity(), listaHorarios);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), showHorario.class);
                intent.putExtra("ID", listaHorarios.get(position).getId());
                startActivity(intent);
            }
        });

    }





}