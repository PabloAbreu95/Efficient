package com.example.pablo.efficient.GaleriaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pablo.efficient.HorarioPackage.Horario;
import com.example.pablo.efficient.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 11/08/2017.
 */

public class galeriaAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<String> elementos;

    public galeriaAdapter(Context context, ArrayList<String> elementos) {
        super(context, R.layout.linha_galeria, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_galeria, parent, false);
        TextView abreviacao = (TextView) rowView.findViewById(R.id.textViewNomeGaleria);



        abreviacao.setText(elementos.get(position));

        return rowView;
    }
}
