package br.senac.pi.projetoestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.senac.pi.projetoestoque.domain.Produto;
import br.senac.pi.projetoestoque.domain.ProdutoDB;

public class Produtos extends AppCompatActivity {
    //private ListView lista2;

    // não concluido

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        /*lista2 = (ListView) findViewById(R.id.lista2);
        registerForContextMenu(lista2);
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produto detalhesproduto = (Produto) adapter.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra("produtoSelecionado", detalhesproduto);
                startActivity(intent);
            }
        });*/
    }
    /*protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        ProdutoDB db = new ProdutoDB(this);
        List<Produto> produtos = db.getListaProdutosVendidos();
        db.close();
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, layout, produtos);
        lista2.setAdapter(adapter);
    }*/

}
