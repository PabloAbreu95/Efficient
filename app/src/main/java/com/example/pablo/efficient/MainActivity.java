package com.example.pablo.efficient;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pablo.efficient.DiasSemanaPackage.DiasSemanaActivity;
import com.example.pablo.efficient.DisciplinaPackage.DisciplinaActivity;
import com.example.pablo.efficient.GaleriaPackage.GaleriaActivity;
import com.example.pablo.efficient.HorarioPackage.horarioBD;
import com.example.pablo.efficient.HorarioPackage.horariosActivity;
import com.example.pablo.efficient.NotaPackage.add_nota;
import com.example.pablo.efficient.NotaPackage.notasActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private horarioBD bd;
    private final int TIRAR_FOTO = 3;
    private final int PERMISSAO_REQUEST = 2;
    private ImageView imagem;
    private final int CAMERA = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new horarioBD(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_nota.class);
                startActivity(intent);
            }
        });

        ImageView cameramain = (ImageView)findViewById(R.id.camera_main); //Imagem da camera clicada
        cameramain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String horarioatual = bd.getHorarioAtual();


                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    try {
                        arquivoFoto = criarArquivo(horarioatual);
                    } catch (IOException ex) {
// Manipulação em caso de falha de criação do arquivo
                        Toast.makeText(getBaseContext(), "Erro ao criar arquivo", Toast.LENGTH_SHORT).show();
                    }
                    if (arquivoFoto != null) {
                        Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                                getBaseContext().getApplicationContext().getPackageName() +
                                        ".provider", arquivoFoto);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAMERA);
                    }

                    Toast.makeText(getBaseContext(),horarioatual,Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView galeriamain = (ImageView)findViewById(R.id.galeria_main); // Imagem galeria clicada
        galeriamain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GaleriaActivity.class);
                startActivity(intent);
            }
        });

        ImageView disciplinamain = (ImageView)findViewById(R.id.disciplinas_main); //Imagem disciplina clicada
        disciplinamain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisciplinaActivity.class);
                startActivity(intent);
            }
        });

        ImageView horariosmain = (ImageView)findViewById(R.id.horario_main);
        horariosmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiasSemanaActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Permissões para leitura e escrita
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
           String horarioatual = bd.getHorarioAtual();


            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                try {
                    arquivoFoto = criarArquivo(horarioatual);
                } catch (IOException ex) {
// Manipulação em caso de falha de criação do arquivo
                    Toast.makeText(getBaseContext(), "Erro ao criar arquivo", Toast.LENGTH_SHORT).show();
                }
                if (arquivoFoto != null) {
                    Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                            getBaseContext().getApplicationContext().getPackageName() +
                                    ".provider", arquivoFoto);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, CAMERA);
                }

                Toast.makeText(getBaseContext(),horarioatual,Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_gallery) { //Galeria
            Intent intent = new Intent(MainActivity.this, GaleriaActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) { //Disciplinas
            Intent intent = new Intent(MainActivity.this, DisciplinaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) { //Horários
            Intent intent = new Intent(MainActivity.this, DiasSemanaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) { //Notas
            Intent intent = new Intent(MainActivity.this, notasActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_send) { //Sobre
            showDialog();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }

        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(arquivoFoto))
            );

        }
    }


    private File arquivoFoto = null;
    private File criarArquivo(String nome) throws IOException {
        String timeStamp = new
                SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pasta = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), nome);
        if (!pasta.mkdirs()) {
            pasta.mkdir();
        }
        File imagem = new File(pasta.getPath() + File.separator
                + nome + timeStamp + ".jpg");
        return imagem;
    }



    private void showDialog(){
        final Dialog mydialog = new Dialog(this);
        mydialog.setTitle("Sobre");

        mydialog.setContentView(R.layout.sobre);
        mydialog.show();
    }
}
