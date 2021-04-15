package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.List;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class MainActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);

        carregarListaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_activity_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if ( itemId == R.id.activity_main_activity_menu_remover ) removerPeloMenu(item);

        return super.onContextItemSelected(item);
    }

    private void removerPeloMenu(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        removerAluno(alunoEscolhido);
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarLista();
    }

    private void atualizarLista() {
        adapter.clear();
        adapter.addAll(dao.getAlunos());
    }

    private void carregarListaAlunos() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);

        configuraListenerDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void removerAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        listaAlunos.setAdapter(adapter);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((AdapterView<?> adapterView, View view, int index, long id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(index);

            abreFormularioModoEditaAluno(aluno);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormulario = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        vaiParaFormulario.putExtra(CHAVE_ALUNO, aluno);

        startActivity(vaiParaFormulario);
    }

    public void abreFormularioModoInsereAluno(View view) {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
}
