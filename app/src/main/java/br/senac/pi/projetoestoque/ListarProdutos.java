package br.senac.pi.projetoestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.projetoestoque.domain.Produto;
import br.senac.pi.projetoestoque.domain.ProdutoDB;

public class ListarProdutos extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        //
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produto produtoclicado = (Produto) adapter.getItemAtPosition(position);
                Intent intent = new Intent(ListarProdutos.this, CadastrarProduto.class);
                intent.putExtra("produtoSelecionado", produtoclicado);
                startActivity(intent);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Produto produto = (Produto) adapter.getItemAtPosition(posicao);

                return false;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_produto,menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.lista_alunos, menu);

            return super.onCreateOptionsMenu(menu);
        }*/


    @Override
    protected void onResume() {
        super.onResume();
    }
    private void carregaLista(){
        ProdutoDB db = new ProdutoDB(this);
        List<Produto> produtos = db.getLista();
        db.close();
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,layout,produtos);
        lista.setAdapter(adapter);
    }
}
