package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String NOVO_ALUNO = "Novo aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(NOVO_ALUNO);
    }

    public void handleSaveStudent(View view) {
        String alunoNome = ((EditText) findViewById(R.id.activity_formulario_aluno_nome))
                .getText().toString();
        String alunoTelefone = ((EditText) findViewById(R.id.activity_formulario_aluno_telefone))
                .getText().toString();
        String alunoEmail = ((EditText) findViewById(R.id.activity_formulario_aluno_email))
                .getText().toString();

        Aluno aluno = new Aluno(alunoNome, alunoTelefone, alunoEmail);

        AlunoDAO DAO = new AlunoDAO();
        DAO.salva(aluno);

        finish();
    }
}