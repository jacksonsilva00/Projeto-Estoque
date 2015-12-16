package br.senac.pi.projetoestoque;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.projetoestoque.domain.Produto;
import br.senac.pi.projetoestoque.domain.ProdutoDB;

public class ListarProdutos extends AppCompatActivity {
    private ListView lista;
    private Produto produto;

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

            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {

                produto = (Produto) adapter.getItemAtPosition(posicao);

                return false;
            }

        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem vender = menu.add(R.string.venda);
        vender.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (produto.getQuantidade() > 0) {
                    ProdutoDB db = new ProdutoDB(ListarProdutos.this);
                    db.vender(produto);
                    db.close();
                    Toast.makeText(ListarProdutos.this, R.string.venda_produto, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListarProdutos.this, R.string.venda_indisponivel, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        MenuItem deletar = menu.add(R.string.deletar);
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ProdutoDB db = new ProdutoDB(ListarProdutos.this);
                db.deletar(produto);
                db.close();
                carregaLista();
                Toast.makeText(ListarProdutos.this, R.string.deletado, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        ProdutoDB db = new ProdutoDB(this);
        List<Produto> produtos = db.getLista();
        db.close();
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, layout, produtos);
        lista.setAdapter(adapter);
    }
}
