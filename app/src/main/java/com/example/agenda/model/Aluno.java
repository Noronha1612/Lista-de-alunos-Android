package com.example.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private final String alunoNome;
    private final String alunoTelefone;
    private final String alunoEmail;

    public Aluno(String alunoNome, String alunoTelefone, String alunoEmail) {
        this.alunoNome = alunoNome;
        this.alunoTelefone = alunoTelefone;
        this.alunoEmail = alunoEmail;
    }

    @NonNull
    @Override
    public String toString() {
        return this.alunoNome;
    }
}
