package br.efficient.HorarioPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.efficient.R;

import java.util.ArrayList;

public class horariosActivity extends AppCompatActivity {
    private horarioBD bd;
    private ArrayList<Horario> listaHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bd = new horarioBD(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_horario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(horariosActivity.this, add_Horario.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView lista = (ListView) findViewById(R.id.lvhorarios);
        listaHorarios = bd.getAllHorarios();
        horarioAdapter adapter = new horarioAdapter(this, listaHorarios);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Horario horario = new Horario();
                horario.setId(listaHorarios.get(position).getId());
                bd.deleteHorario(horario);
            }
        });
    }
}


