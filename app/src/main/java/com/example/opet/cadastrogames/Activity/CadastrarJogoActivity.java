package com.example.opet.cadastrogames.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.cadastrogames.DAO.Jogo;
import com.example.opet.cadastrogames.DAO.JogoDAO;

import com.example.opet.cadastrogames.DAO.Jogo;
import com.example.opet.cadastrogames.DAO.JogoDAO;
import com.example.opet.cadastrogames.R;

public class CadastrarJogoActivity extends Activity {

    private EditText editTitulo;
    private Spinner spinnerEstilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_jogo);

        editTitulo = (EditText) findViewById(R.id.editTitulo);
        spinnerEstilo = (Spinner) findViewById(R.id.spinnerEstilo);
    }

    public void salvarJogo(View v){
        JogoDAO jogoDAO = new JogoDAO(this);
        Jogo jogo = new Jogo();
        jogo.setTitulo(editTitulo.getText().toString());
        jogo.setEstilo(spinnerEstilo.getSelectedItem().toString());

        long resultado = jogoDAO.insereDado(jogo);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarJogos = new Intent(CadastrarJogoActivity.this,ListarJogosActivity.class);
            startActivity(listarJogos);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
