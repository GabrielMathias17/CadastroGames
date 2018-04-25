package com.example.opet.cadastrogames.DAO;

/**
 * Created by Diego on 13/09/2017.
 */

public class Jogo {
    private int ID;
    private String titulo;
    private String estilo;

    public Jogo() {
    }

    public Jogo(int ID, String titulo, String genero) {
        this.ID = ID;
        this.titulo = titulo;
        this.estilo = estilo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) { this.estilo = estilo; }

}
