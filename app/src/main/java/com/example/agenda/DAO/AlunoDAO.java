package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if ( alunoEncontrado != null ) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);

            alunos.set(posicaoDoAluno, aluno);
        }
    }

    private Aluno buscaAlunoPorId(Aluno aluno) {
        Aluno alunoEncontrado = null;

        for (Aluno a: alunos) {
            if ( a.getId() == aluno.getId() ) {
                return a;
            }
        }

        return null;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public static int getContadorDeIds() {
        return contadorDeIds;
    }
}
