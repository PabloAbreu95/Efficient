package com.example.pablo.efficient.GaleriaPackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pablo.efficient.HorarioPackage.Horario;
import com.example.pablo.efficient.HorarioPackage.horarioAdapter;
import com.example.pablo.efficient.HorarioPackage.horarioBD;
import com.example.pablo.efficient.R;

import java.io.File;
import java.util.ArrayList;

public class GaleriaActivity extends AppCompatActivity {
    private horarioBD bd;
    private ArrayList<String> listaHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bd = new horarioBD(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView lista = (ListView) findViewById(R.id.lvgaleria);
        listaHorarios = bd.getAllHNames();
        galeriaAdapter adapter = new galeriaAdapter(this, listaHorarios);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = listaHorarios.get(position).toString();
                Toast.makeText(getBaseContext(), nome, Toast.LENGTH_SHORT).show();


                File imageRoot = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), nome); //Recria pasta caso não exista
                imageRoot.mkdir();

                




            }
        });
    }
}
