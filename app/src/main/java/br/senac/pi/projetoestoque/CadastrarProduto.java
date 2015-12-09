package br.senac.pi.projetoestoque;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.projetoestoque.domain.Produto;

public class CadastrarProduto extends AppCompatActivity implements View.OnClickListener {
    private EditText edtnome,edtpreco,edtquantidade;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        edtnome = (EditText) findViewById(R.id.edtnome);
        edtpreco = (EditText) findViewById(R.id.edtpreco);
        edtquantidade = (EditText) findViewById(R.id.edtquantidade);
        Button btnCadastrarProduto =(Button) findViewById(R.id.btnCadastrarProduto);
        btnCadastrarProduto.setOnClickListener(this);
    }
    public Produto produtoestoque(){
        Produto produto = new Produto();

        produto.setNome(edtnome.getText().toString());
        produto.setPreco(Double.parseDouble(edtpreco.getText().toString()));
        produto.setQuantidade(Integer.parseInt(edtquantidade.getText().toString()));

        return produto;
    }
    public void alterarproduto(Produto produtoalterar){
        edtnome.setText(produtoalterar.getNome());
        edtpreco.setText(String.valueOf(produtoalterar.getPreco()));
        edtquantidade.setText(produtoalterar.getQuantidade());
    }
    public void onClick(View v) {
        Toast.makeText(CadastrarProduto.this,R.string.app_name,Toast.LENGTH_SHORT).show();

        finish();

    }
}
