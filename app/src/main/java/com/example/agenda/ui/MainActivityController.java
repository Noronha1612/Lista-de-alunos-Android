package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class MainActivityController {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public MainActivityController(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void abrirDialog(Aluno aluno) {
        // Construindo dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        // Colocando os dados no dialog
        dialog.setTitle(String.format("Removendo %s", aluno.getNome()));
        dialog.setMessage(String.format("Tem certeza que quer remover %s?", aluno.getNome()));
        dialog.setPositiveButton("Sim", (dialog1, which) -> removerAluno(aluno));
        dialog.setNegativeButton("Não", null);

        // Exibindo o dialog
        dialog.show();
    }

    public void removerAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }

    public Aluno getAlunoPorItem(@NonNull MenuItem item) {
        return adapter.getItem(
                ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo())
                        .position
        );
    }

    public void atualizar() {
        adapter.atualiza(dao.getAlunos());
    }
}
