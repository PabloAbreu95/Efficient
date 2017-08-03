package com.example.pablo.efficient.HorarioPackage;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pablo.efficient.DisciplinaPackage.disciplinaBD;
import com.example.pablo.efficient.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class add_Horario extends AppCompatActivity {
    private static final int PERMISSAO_REQUEST = 1;
    private disciplinaBD bd2;
    private horarioBD bd;

    private ArrayList<String> nomes;
    int horainicial, minutoinicial, horafinal, minutofinal;
    EditText horainicio, horariofim;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__horario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Banco De Dados do de Horarios
        bd = new horarioBD(this);

        //Spinner Disciplina
        bd2 = new disciplinaBD(this);
        nomes = bd2.getAllAb();
        Spinner spin = (Spinner) findViewById(R.id.spinnerDisciplinaHorario);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        //Spinner Dia
        Spinner spinnerDiaHorario = (Spinner) findViewById(R.id.spinnerDiaHorario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dias_semana, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiaHorario.setAdapter(adapter);


        horainicio = (EditText) findViewById(R.id.edtTextHorarioInicio);
        horariofim = (EditText) findViewById(R.id.edtTextHorarioFim);


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

    public void set_timeinicio(View view) {
        final Calendar calendar = Calendar.getInstance();
        horainicial = calendar.get(calendar.HOUR_OF_DAY);
        minutoinicial = calendar.get(calendar.MINUTE);
        TimePickerDialog timepickerinicio = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horainicio.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, horainicial, minutoinicial, true);
        timepickerinicio.show();
    }


    public void set_timefim(View view) {
        final Calendar calendar = Calendar.getInstance();
        horafinal = calendar.get(calendar.HOUR_OF_DAY);
        minutofinal = calendar.get(calendar.MINUTE);
        TimePickerDialog timepickerfim = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horariofim.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, horafinal, minutofinal, true);
        timepickerfim.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_horario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.salvar_horario) {

            Spinner spindisciplina = (Spinner) findViewById(R.id.spinnerDisciplinaHorario);
            Spinner spinnerDiaHorario = (Spinner) findViewById(R.id.spinnerDiaHorario);
            EditText salahorario = (EditText) findViewById(R.id.editTextSalaHorario);
            EditText horainicio = (EditText) findViewById(R.id.edtTextHorarioInicio);
            EditText horariofim = (EditText) findViewById(R.id.edtTextHorarioFim);


            //Setando valores e adicionando
            Horario horario = new Horario();
            horario.setNome(spindisciplina.getSelectedItem().toString());
            horario.setDia(spinnerDiaHorario.getSelectedItem().toString());
            horario.setSala(salahorario.getText().toString());
            horario.setInicio(horainicio.getText().toString());
            horario.setFim(horariofim.getText().toString());
            bd.addHorario(horario);
            Toast.makeText(getBaseContext(), "Horario adicionado", Toast.LENGTH_SHORT).show();
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
