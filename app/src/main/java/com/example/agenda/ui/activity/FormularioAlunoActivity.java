package com.example.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String NOVO_ALUNO = "Novo aluno";
    private static final String EDITAR_ALUNO = "Editar aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO DAO = new AlunoDAO();

    private Aluno alunoEditavel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        iniciarCampos();
        iniciarAlunoSeExistir();

        setTitle(alunoEditavel != null ? EDITAR_ALUNO : NOVO_ALUNO);
    }

    private void iniciarAlunoSeExistir() {
        Aluno aluno = (Aluno) getIntent().getSerializableExtra(CHAVE_ALUNO);
        if(aluno != null) {
            alunoEditavel = aluno;

            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        }
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

        finalizaFormulario(aluno);
    }

    private void finalizaFormulario(Aluno aluno) {
        if (alunoEditavel != null) {
            aluno.setId(alunoEditavel.getId());
            DAO.edita(aluno);
        }
        else DAO.salva(aluno);

        finish();
    }
}