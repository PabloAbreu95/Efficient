package br.efficient.NotaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.efficient.R;

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
        View cor = (View)rowView.findViewById(R.id.linha_layout_nota_simples);
        TextView data  = (TextView) rowView.findViewById(R.id.textViewNotaData);

        titulo.setText(elementos.get(position).getTitulo());
        texto.setText(elementos.get(position).getTexto());
        cor.setBackgroundColor(elementos.get(position).getCor());
        data.setText(elementos.get(position).getData());
        return rowView;
    }
}
