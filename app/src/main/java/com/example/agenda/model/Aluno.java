package com.example.agenda.model;

import androidx.annotation.NonNull;

import com.example.agenda.DAO.AlunoDAO;

import java.io.Serializable;

public class Aluno implements Serializable {
    private final String alunoNome;
    private final String alunoTelefone;
    private final String alunoEmail;

    private int id = 0;

    public Aluno(String alunoNome, String alunoTelefone, String alunoEmail) {
        this.alunoNome = alunoNome;
        this.alunoTelefone = alunoTelefone;
        this.alunoEmail = alunoEmail;

        this.id = AlunoDAO.getContadorDeIds();
    }

    @NonNull
    @Override
    public String toString() {
        return this.alunoNome;
    }

    public String getEmail() {
        return alunoEmail;
    }

    public String getNome() {
        return alunoNome;
    }

    public String getTelefone() {
        return alunoTelefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
