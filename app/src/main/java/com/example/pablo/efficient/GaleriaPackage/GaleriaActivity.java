package com.example.pablo.efficient.GaleriaPackage;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
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
    String SCAN_PATH;
    File[] allFiles ;

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
                File imageRoot = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), listaHorarios.get(position).toString()); //Recria pasta caso não exista

                allFiles = imageRoot.listFiles();
                new SingleMediaScanner(GaleriaActivity.this, allFiles[allFiles.length-1]);
                if(allFiles.length == 0){
                    Toast.makeText(getBaseContext(), "Sem fotos na galeria", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Deslize pra esquerda para ver fotos antigas", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

        public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

            private MediaScannerConnection mMs;
            private File mFile;

            public SingleMediaScanner(Context context, File f) {
                mFile = f;
                mMs = new MediaScannerConnection(context, this);
                mMs.connect();
            }

            public void onMediaScannerConnected() {
                mMs.scanFile(mFile.getAbsolutePath(), null);
            }

            public void onScanCompleted(String path, Uri uri) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
                mMs.disconnect();
            }

        }
    }

