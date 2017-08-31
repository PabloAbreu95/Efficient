package br.efficient.NotaPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Pablo on 08/08/2017.
 */

public class notasBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotasDB";
    private static final String TABELA_NOTAS = "notas";
    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String TEXTO = "texto";
    private static final String COR = "cor";
    private static final String DATA = "data";
    private static final String[] COLUNAS = {ID, TITULO, TEXTO, COR, DATA};



    public notasBD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE notas(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT," +
                "texto TEXT," +
                "cor INTEGER," +
                "data TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS notas");
        this.onCreate(db);
    }

    public void addNota(Nota nota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITULO, nota.getTitulo());
        values.put(TEXTO, nota.getTexto());
        values.put(COR, nota.getCor());
        values.put(DATA, nota.getData());
        db.insert(TABELA_NOTAS, null, values);
        db.close();
    }

    public Nota getNota(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_NOTAS, // a. tabela
                COLUNAS, // b. colunas
                " id = ?", // c. colunas para comparar
                new String[] { String.valueOf(id) }, // d. parâmetros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Nota nota = cursorToNota(cursor);
            return nota;
        }
    }

    private Nota cursorToNota(Cursor cursor) {
        Nota nota = new Nota();
        nota.setId(Integer.parseInt(cursor.getString(0)));
        nota.setTitulo(cursor.getString(1));
        nota.setTexto(cursor.getString(2));
        nota.setCor(Integer.parseInt(cursor.getString(3)));
        nota.setData(cursor.getString(4));
        return nota;
    }

    public ArrayList<Nota> getAllNotas() {
        ArrayList<Nota> listaNotas = new ArrayList<Nota>();
        String query = "SELECT * FROM " + TABELA_NOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Nota nota = cursorToNota(cursor);
                listaNotas.add(nota);
            } while (cursor.moveToNext());
        }
        return listaNotas;
    }

    public int updateNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITULO, nota.getTitulo());
        values.put(TEXTO, nota.getTexto());
        values.put(COR, nota.getCor());
        values.put(DATA, nota.getData());
        int i = db.update(TABELA_NOTAS, //tabela
                values, // valores
                ID+" = ?", // colunas para comparar
                new String[] { String.valueOf(nota.getId()) }); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }


    public int deleteNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_NOTAS, //tabela
                ID+" = ?", // colunas para comparar
                new String[] { String.valueOf(nota.getId()) });
        db.close();
        return i; // número de linhas excluídas
    }
}
