package com.example.opet.cadastrogames.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.opet.cadastrogames.Util.BancoJogos;

/**
 * Created by Diego on 13/09/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoJogos.NOME_BANCO,null,BancoJogos.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE USUARIO(_id integer primary key autoincrement
        String sql = "CREATE TABLE "+ BancoJogos.TABELA_JOGOS+"("
                + BancoJogos.ID_JOGO+ " integer primary key autoincrement,"
                + BancoJogos.TITULO_JOGO + " text,"
                + BancoJogos.ESTILO_JOGO + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoJogos.TABELA_JOGOS);
        onCreate(db);
    }
}
