package br.senac.pi.projetoestoque;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarProduto extends AppCompatActivity implements View.OnClickListener {
    private SQLiteOpenHelper helper;
    private EditText edtnome,edtpreco,edtquantidade;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        //findViewById(R.id.btnCadastrarProduto).setOnClickListener(cadastrarproduto());
        edtnome = (EditText) findViewById(R.id.edtnome);
        edtpreco = (EditText) findViewById(R.id.edtpreco);
        edtquantidade = (EditText) findViewById(R.id.edtquantidade);
        Button btnCadastrarProduto =(Button) findViewById(R.id.btnCadastrarProduto);
        btnCadastrarProduto.setOnClickListener(this);
    }
   /* public View.OnClickListener cadastrarproduto(){
        return new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(CadastrarProduto.this,R.string.app_name,Toast.LENGTH_LONG).show();
            }
        };
    }*/

    @Override
    public void onClick(View v) {
        /*SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome",edtnome.getText().toString());
        values.put("preco",edtpreco.getText().toString());
        values.put("quantidade",edtquantidade.getText().toString());*/
        Toast.makeText(CadastrarProduto.this,R.string.app_name,Toast.LENGTH_SHORT).show();

    }
}
