package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.io.Serializable;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String NOVO_ALUNO = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(NOVO_ALUNO);

        iniciarCampos();

        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if(aluno != null)
            campoNome.setText(aluno.toString());
    }

    private void iniciarCampos() {
        this.campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        this.campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        this.campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    public void handleSaveStudent(View view) {
        String alunoNome = campoNome.getText().toString();
        String alunoTelefone = campoTelefone.getText().toString();
        String alunoEmail = campoEmail.getText().toString();

        Aluno aluno = new Aluno(alunoNome, alunoTelefone, alunoEmail);

        AlunoDAO DAO = new AlunoDAO();
        DAO.salva(aluno);

        finish();
    }
}