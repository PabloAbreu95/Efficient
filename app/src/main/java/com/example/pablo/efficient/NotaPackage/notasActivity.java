package com.example.pablo.efficient.NotaPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pablo.efficient.R;

import java.util.ArrayList;

public class notasActivity extends AppCompatActivity {
    private notasBD bd;
    private ArrayList<Nota> listaNota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bd = new notasBD(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_nota);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notasActivity.this, add_nota.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView lista = (ListView) findViewById(R.id.lvnotas);
        listaNota = bd.getAllNotas();
        notaAdapter adapter = new notaAdapter(this, listaNota);
        lista.setAdapter(adapter);
    }


}
