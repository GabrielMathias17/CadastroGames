package com.example.opet.cadastrogames.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.opet.cadastrogames.Factory.DatabaseFactory;
import com.example.opet.cadastrogames.Util.BancoJogos;

/**
 * Created by Diego on 13/09/2017.
 *
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Livro.
 *
 */

public class JogoDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public static final int JOGOS_TOTAL = 1;


    public JogoDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Jogo jogo) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoJogos.TITULO_JOGO, jogo.getTitulo());
        valores.put(BancoJogos.ESTILO_JOGO, jogo.getEstilo());

        resultado = db.insert(BancoJogos.TABELA_JOGOS, null, valores);
        db.close();

        return resultado;

    }

    public Jogo carregaJogoPorID(int id){
        Cursor cursor;
        String[] campos = {BancoJogos.ID_JOGO, BancoJogos.TITULO_JOGO, BancoJogos.ESTILO_JOGO};
        db = banco.getReadableDatabase();

        String where = BancoJogos.ID_JOGO + " = " + id;

        cursor = db.query(BancoJogos.TABELA_JOGOS, campos, where, null, null, null, null, null);

        Jogo jogo = new Jogo();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoJogos.ID_JOGO));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoJogos.TITULO_JOGO));
            String estilo = cursor.getString(cursor.getColumnIndexOrThrow(BancoJogos.ESTILO_JOGO));

            jogo.setID(ID);
            jogo.setTitulo(titulo);
            jogo.setEstilo(estilo);
        }
        db.close();
        return jogo;
    }

    public Cursor carregaDados(int id) {
        Cursor cursor;
        String[] campos = {BancoJogos.ID_JOGO, BancoJogos.TITULO_JOGO, BancoJogos.ESTILO_JOGO};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoJogos.TABELA_JOGOS, campos, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Jogo> carregaDadosLista(int id) {
        Cursor cursor;
        cursor = carregaDados(1);

        List<Jogo> jogos = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Jogo jogo = new Jogo();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoJogos.ID_JOGO));
                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoJogos.TITULO_JOGO));
                    String estilo = cursor.getString(cursor.getColumnIndexOrThrow(BancoJogos.ESTILO_JOGO));

                    jogo.setID(ID);
                    jogo.setTitulo(titulo);
                    jogo.setEstilo(estilo);

                    jogos.add(jogo);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return jogos;
    }

    public void deletaRegistro(int id){
        String where = BancoJogos.ID_JOGO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoJogos.TABELA_JOGOS,where,null);
        db.close();
    }

    public boolean atualizaJogo(Jogo jogo){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoJogos.ID_JOGO + " = " + jogo.getID();

        valores = new ContentValues();
        valores.put(BancoJogos.TITULO_JOGO, jogo.getTitulo());
        valores.put(BancoJogos.ESTILO_JOGO, jogo.getEstilo());

        int resultado = db.update(BancoJogos.TABELA_JOGOS,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}