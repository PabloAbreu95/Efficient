package com.example.pablo.efficient.DisciplinaPackage;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pablo.efficient.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class add_disciplina extends AppCompatActivity {

    private disciplinaBD bd;
    private int cor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disciplina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bd = new disciplinaBD(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

       cor = 24249242;


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_disciplina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.salvar_disciplina) {
            EditText nome = (EditText) findViewById(R.id.edt_nome_disciplina);
            EditText abreviacao = (EditText) findViewById(R.id.edt_abreviacao_disciplina);
            EditText professor = (EditText) findViewById(R.id.edt_professor_disciplina);
            EditText email = (EditText) findViewById(R.id.edt_email_disciplina);



                Disciplina disciplina = new Disciplina();
                disciplina.setNome(nome.getText().toString());
                disciplina.setAbreviacao(abreviacao.getText().toString());
                disciplina.setProfessor(professor.getText().toString());
                disciplina.setEmail(email.getText().toString());
                disciplina.setCor(cor);
                bd.addDisciplina(disciplina);
                Toast.makeText(getBaseContext(), "Disciplina adicionada", Toast.LENGTH_SHORT).show();
                finish();
        }

        if (id == R.id.cor_disciplina){
            final View view = (View)findViewById(R.id.barra_cor_disciplina);
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Escolha a cor")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .alphaSliderOnly()
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int i) {

                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                            view.setBackgroundColor(i);
                            cor = i;
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .build()
                    .show();
        }

        if (id == android.R.id.home){
            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }




}
