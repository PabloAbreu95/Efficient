package br.efficient.NotaPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.efficient.R;

public class showNota extends AppCompatActivity {
    private notasBD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Recupera os dados
        Intent intent = getIntent();
        bd = new notasBD(this);
        final int idd = intent.getIntExtra("ID", 0);
        Nota nota = bd.getNota(idd);

        //Recupera os elementos text view
        TextView titulo = (TextView)findViewById(R.id.txt_nota_titulo);
        TextView texto = (TextView)findViewById(R.id.txt_nota_texto);
        View view = (View)findViewById(R.id.mostrarnota);

        titulo.setText(nota.getTitulo());
        texto.setText(nota.getTexto());
        view.setBackgroundColor(nota.getCor());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.nota_onshow_excluir){
            Intent intent = getIntent();
            final int idd = intent.getIntExtra("ID", 0);
            Nota nota = new Nota();
            nota.setId(idd);
            bd.deleteNota(nota);
            Toast.makeText(getBaseContext(), "Nota removida.",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}


