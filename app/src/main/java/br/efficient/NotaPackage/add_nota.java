package br.efficient.NotaPackage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.efficient.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class add_nota extends AppCompatActivity {
    private notasBD bd;
    private int cor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        bd = new notasBD(this);
        cor = 24249242;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cor) {
            final LinearLayout layout = (LinearLayout)findViewById(R.id.nota_layout);
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
                            layout.setBackgroundColor(i);
                            Toast.makeText(getBaseContext(), Integer.toHexString(i) ,Toast.LENGTH_SHORT).show();
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

        if (id == R.id.salvar_nota)
        {
            EditText titulo = (EditText)findViewById(R.id.editTextnotatitulo);
            EditText texto = (EditText)findViewById(R.id.editTextnotatexto);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDateandTime = sdf.format(new Date());



            Nota nota = new Nota();
            nota.setTitulo(titulo.getText().toString());
            nota.setTexto(texto.getText().toString());
            nota.setCor(cor);
            nota.setData(currentDateandTime);

            bd.addNota(nota);
            Toast.makeText(getBaseContext(),"Nota adicionada",Toast.LENGTH_SHORT).show();
            finish();
        }

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
