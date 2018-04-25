package com.example.opet.cadastrogames.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.opet.cadastrogames.DAO.Jogo;
import com.example.opet.cadastrogames.R;

/**
 * Created by Diego on 13/09/2017.
 */

public class JogoAdapter extends ArrayAdapter<Jogo> {

    private int resource;
    private List<Jogo> jogos;

    public JogoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Jogo> objects) {
        super(context, resource, objects);
        this.resource = resource;
        jogos = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Jogo jogo = jogos.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.textTitulo);
        TextView textGenero = (TextView) mView.findViewById(R.id.textEstilo);

        if(textID != null){
            textID.setText(String.valueOf(jogo.getID()));
        }
        if(textTitulo != null){
            textTitulo.setText(jogo.getTitulo());
        }
        if(textGenero != null){
            textGenero.setText(jogo.getEstilo());
        }
        return mView;
    }
}
