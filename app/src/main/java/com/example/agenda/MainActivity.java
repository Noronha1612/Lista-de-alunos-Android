package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.activity.FormularioAlunoActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AlunoDAO dao = new AlunoDAO();

        dao.salva(new Aluno("Noronha", "dasniodnaso", "odasnmdoa"));
        dao.salva(new Aluno("Froid", "dasniodnaso", "odasnmdoa"));

        final List<Aluno> alunos = dao.getAlunos();

        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        listaAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getAlunos()));

        listaAlunos.setOnItemClickListener((AdapterView<?> adapterView, View view, int index, long id) -> {
            Aluno aluno = alunos.get(index);

            Intent vaiParaFormulario = new Intent(MainActivity.this, FormularioAlunoActivity.class);
            vaiParaFormulario.putExtra("aluno", aluno);

            startActivity(vaiParaFormulario);
        });
    }

    public void handleFAB(View view) {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
}
