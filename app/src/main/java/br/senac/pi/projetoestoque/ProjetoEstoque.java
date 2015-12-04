package br.senac.pi.projetoestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProjetoEstoque extends AppCompatActivity {

    private Button btnCadastrar,btnVender,btnListar;
    //private EditText preco,quantidade,nome;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_estoque);
        // chamando button
        findViewById(R.id.btnCadastrar).setOnClickListener(cadastrar());
        findViewById(R.id.btnVender).setOnClickListener(venderproduto());
        findViewById(R.id.btnListar).setOnClickListener(listarproduto());
    }
    public View.OnClickListener cadastrar() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProjetoEstoque.this, CadastrarProduto.class);
                startActivity(intent);
            }
        };
    }

    public View.OnClickListener venderproduto() {
        return new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ProjetoEstoque.this, R.string.vender, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public View.OnClickListener listarproduto() {
        return new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ProjetoEstoque.this,R.string.list_produtos,Toast.LENGTH_SHORT).show();
            }
        };
    }
}
