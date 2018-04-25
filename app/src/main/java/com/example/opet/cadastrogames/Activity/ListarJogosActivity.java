package com.example.opet.cadastrogames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import com.example.opet.cadastrogames.Adapter.JogoAdapter;
import com.example.opet.cadastrogames.DAO.Jogo;
import com.example.opet.cadastrogames.DAO.JogoDAO;
import com.example.opet.cadastrogames.R;

public class ListarJogosActivity extends Activity {


    private ListView listaJogos;
    private JogoAdapter myAdapter;
    JogoDAO jogoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_jogos);

        carregarElementos();
    }

    public void carregarElementos(){
        listaJogos = (ListView) findViewById(R.id.listJogos);
        jogoDAO = new JogoDAO(this);
        List<Jogo> jogos = jogoDAO.carregaDadosLista( 1);
        myAdapter = new JogoAdapter(this,R.layout.item_jogo,jogos);
        listaJogos.setAdapter(myAdapter);
        listaJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Jogo jogo = (Jogo)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarJogosActivity.this,AtualizarJogosActivity.class);
                atualizarIntent.putExtra("ID_JOGO",jogo.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}
