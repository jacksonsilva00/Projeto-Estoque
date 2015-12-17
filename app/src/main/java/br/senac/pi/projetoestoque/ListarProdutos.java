package br.senac.pi.projetoestoque;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senac.pi.projetoestoque.domain.Produto;
import br.senac.pi.projetoestoque.domain.ProdutoDB;

public class ListarProdutos extends AppCompatActivity {
    private ListView lista;
    private Produto produto;
    int numerodevendas = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        //
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // criando um produto serializado
                // um cast do produto serializado
                Produto produtoclicado = (Produto) adapter.getItemAtPosition(position);
                Intent intent = new Intent(ListarProdutos.this, CadastrarProduto.class);
                // manda o produto serializado para poder modificar o produto diretamente na proxima classe.
                intent.putExtra("produtoSelecionado", produtoclicado);
                startActivity(intent);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                //pega a posição do produto
                produto = (Produto) adapter.getItemAtPosition(posicao);
                return false;
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem vender = menu.add(R.string.venda);
        vender.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if (produto.getQuantidade() > 0) {
                    ProdutoDB db = new ProdutoDB(ListarProdutos.this);
                    //numero de vendas do produto , a venda por enquanto e apenas 1 de cada vez
                    //isso facilita uma futura modificação futura
                    //receber um parametro do usuario e mandar esse paramentro para esse metodo resolver
                    db.vender(produto, numerodevendas);
                    db.close();
                    carregaLista();
                    Toast.makeText(ListarProdutos.this, R.string.venda_produto, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListarProdutos.this, R.string.venda_indisponivel, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        MenuItem deletar = menu.add(R.string.deletar);
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
