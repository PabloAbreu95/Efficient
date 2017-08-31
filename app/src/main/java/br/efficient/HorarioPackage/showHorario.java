package br.efficient.HorarioPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.efficient.R;

public class showHorario extends AppCompatActivity {

    private horarioBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_horario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        bd = new horarioBD(this);
        final int idd = intent.getIntExtra("ID", 0);
        Horario horarioa = bd.getHorario(idd);

        TextView nome = (TextView)findViewById(R.id.txt_disciplina_horario);
        TextView dia = (TextView)findViewById(R.id.txt_dia_horario);
        TextView sala = (TextView)findViewById(R.id.txt_sala_horario);
        TextView inicio = (TextView)findViewById(R.id.txt_inicio_horario);

        nome.setText(horarioa.getNome());
        inicio.setText(horarioa.getInicio());
        sala.setText(horarioa.getSala());
        dia.setText(horarioa.getDia());




    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_horario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.excluir_onshow_horario){
            Intent intent = getIntent();
            final int idd = intent.getIntExtra("ID", 0);
            Horario horario = new Horario();
            horario.setId(idd);
            bd.deleteHorario(horario);
            Toast.makeText(getBaseContext(), "Disciplina removida.",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
