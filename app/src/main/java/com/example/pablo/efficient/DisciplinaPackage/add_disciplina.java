package com.example.pablo.efficient.DisciplinaPackage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablo.efficient.R;

public class add_disciplina extends AppCompatActivity {

    private disciplinaBD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disciplina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bd = new disciplinaBD(this);

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
            bd.addDisciplina(disciplina);
            Toast.makeText(getBaseContext(), "Disciplina adicinonada", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
