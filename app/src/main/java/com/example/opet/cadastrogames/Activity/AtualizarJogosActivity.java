package com.example.opet.cadastrogames.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.cadastrogames.DAO.Jogo;
import com.example.opet.cadastrogames.DAO.JogoDAO;
import com.example.opet.cadastrogames.R;

public class AtualizarJogosActivity extends Activity {

    private int ID_JOGO;
    private JogoDAO jogoDAO;
    private Jogo jogo;

    private EditText editTitulo;
    private Spinner spinnerEstilo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_jogos);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_JOGO")){
            ID_JOGO = intent.getIntExtra("ID_JOGO",0);
        }
        jogoDAO = new JogoDAO(this);
        jogo = jogoDAO.carregaJogoPorID(ID_JOGO);

        editTitulo = (EditText) findViewById(R.id.editTituloUpdate);
        spinnerEstilo = (Spinner) findViewById(R.id.spinnerEstiloUpdate);

        editTitulo.setText(jogo.getTitulo());
        selectSpinnerItemByValue(spinnerEstilo,jogo.getEstilo());
    }

    public void atualizarJogo(View v){
        jogo.setTitulo(editTitulo.getText().toString());
        jogo.setEstilo(spinnerEstilo.getSelectedItem().toString());
        if(jogoDAO.atualizaJogo(jogo))
            Toast.makeText(AtualizarJogosActivity.this, "Jogo atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AtualizarJogosActivity.this, "Erro ao atualizar jogo.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerJogo(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jogoDAO.deletaRegistro(ID_JOGO);
                Toast.makeText(AtualizarJogosActivity.this, "Jogo removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarJogosActivity.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}
