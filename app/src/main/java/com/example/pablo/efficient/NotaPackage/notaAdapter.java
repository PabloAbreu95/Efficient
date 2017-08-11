package com.example.pablo.efficient.NotaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pablo.efficient.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 10/08/2017.
 */

public class notaAdapter extends ArrayAdapter{

    private final Context context;
    private final ArrayList<Nota> elementos;

    public notaAdapter(Context context, ArrayList<Nota> elementos) {
        super(context, R.layout.linha_nota, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_nota, parent, false);
        TextView titulo = (TextView) rowView.findViewById(R.id.textViewNotaTitulo);
        TextView texto = (TextView) rowView.findViewById(R.id.textViewNotaTexto);
        TextView cor  = (TextView) rowView.findViewById(R.id.textViewNotaCor);
        TextView data  = (TextView) rowView.findViewById(R.id.textViewNotaData);


        titulo.setText(elementos.get(position).getTitulo());
        texto.setText(elementos.get(position).getTexto());
        cor.setText(elementos.get(position).getCor());
        data.setText(elementos.get(position).getData());
        return rowView;
    }
}
