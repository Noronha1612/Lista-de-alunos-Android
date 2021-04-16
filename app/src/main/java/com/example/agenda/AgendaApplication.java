package com.example.agenda;

import android.app.Application;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();

        dao.salva(new Aluno("Noronha", "112233445566", "noronha@email.com"));
        dao.salva(new Aluno("Froid", "112233445566", "froid@email.com"));
    }
}
