package br.senac.pi.projetoestoque;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.senac.pi.projetoestoque.domain.Produto;
import br.senac.pi.projetoestoque.domain.ProdutoDB;

public class CadastrarProduto extends AppCompatActivity implements View.OnClickListener {
    private EditText edtnome,edtpreco,edtquantidade;
    private boolean booleanproduto = true;
    private Produto produtoalterar,pegaproduto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        edtnome = (EditText) findViewById(R.id.edtnome);
        edtpreco = (EditText) findViewById(R.id.edtpreco);
        edtquantidade = (EditText) findViewById(R.id.edtquantidade);
        Button btnCadastrarProduto =(Button) findViewById(R.id.btnCadastrarProduto);
        btnCadastrarProduto.setOnClickListener(this);
        Intent intent = getIntent();
        final Produto alterarproduto = (Produto) intent.getSerializableExtra("produtoSelecionado");
        produtoalterar = alterarproduto;
        if (alterarproduto != null){
            booleanproduto = false;
            btnCadastrarProduto.setText(R.string.alterar);
            edtnome.setText(alterarproduto.getNome().toString());
            edtpreco.setText(String.valueOf(alterarproduto.getPreco()));
            edtquantidade.setText(String.valueOf(alterarproduto.getQuantidade()));
        }
    }
    public Produto produtoestoque(Produto produto){
        produto.setNome(edtnome.getText().toString());
        produto.setPreco(Double.parseDouble(edtpreco.getText().toString()));
        produto.setQuantidade(Integer.parseInt(edtquantidade.getText().toString()));

        return produto;
    }
    public void onClick(View v) {
        pegaproduto = new Produto();
        ProdutoDB database = new ProdutoDB(CadastrarProduto.this);
        String nomeproduto =edtnome.getText().toString();
        String precoproduto =edtpreco.getText().toString();
        String quantidadeProduto = edtquantidade.getText().toString();
        if(nomeproduto ==null || precoproduto==null || quantidadeProduto==null || nomeproduto.equals("") || precoproduto.equals("") || quantidadeProduto.equals("")){
            Toast.makeText(CadastrarProduto.this,R.string.campos,Toast.LENGTH_SHORT).show();
        }else{

            if(booleanproduto){
                Produto produtonovo = produtoestoque(pegaproduto);
                database.salva(produtonovo);
                database.close();
                finish();
            }else{
                database.alterar(produtoestoque(produtoalterar));
                produtoalterar = null;
                booleanproduto=true;
                database.close();
                finish();
            }

        }
    }
}
