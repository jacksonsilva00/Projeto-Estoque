package br.senac.pi.projetoestoque.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDB extends SQLiteOpenHelper {
    private static final String NOME_DB = "produto.sqlite";
    private static final int VERSAO_DB = 3;

    public ProdutoDB(Context context) {

        super(context, NOME_DB, null, VERSAO_DB);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS produto(id integer primary key  autoincrement, nome TEXT,preco DOUBLE, quantidade INTEGER);");
        //db.execSQL("CREATE TABLE IF NOT EXISTS produtovendido(id integer primary key autoincrement , nome TEXT , preco DOUBLE,quantidade INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        String ddl = "DROP TABLE IF EXISTS produto";
        String ddl2 = "DROP TABLE IF EXIST produtovendido";
        db.execSQL(ddl);
        db.execSQL(ddl2);

        this.onCreate(db);
        */
    }

    public void salva(Produto produto) {

        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());

        getWritableDatabase().insert("produto", null, values);

    }

    public List<Produto> getLista() {
        String[] colunas = {"id", "nome", "preco", "quantidade"};
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Cursor cursor = getWritableDatabase().query("produto", colunas, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
            produto.setQuantidade(cursor.getInt(3));
            produtos.add(produto);
        }

        return produtos;
    }

    public void vender(Produto produto, int quantidade) {
        ContentValues values = new ContentValues();
        values.put("quantidade", produto.getQuantidade() - quantidade);
        String[] args = {produto.getId().toString()};
        getWritableDatabase().update("produto", values, "id=?", args);
        //this.listarprodutos(produto);

    }

    public void deletar(Produto produto) {
        String[] args = {produto.getId().toString()};
        getWritableDatabase().delete("produto", "id=?", args);

    }

    public void alterar(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());
        String[] args = {produto.getId().toString()};
        getWritableDatabase().update("produto", values, "id=?", args);

    }
    // para a classe produtos , que ira mostrar os produtos vendidos
    /*
    public void listarprodutos(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("quantidade", +1);
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        String[] args = {produto.getId().toString()};
        getWritableDatabase().update("produtovendido", values, "id=?", args);

    }
    // listando os produtos vendidos , da classe produtos
    public List<Produto> getListaProdutosVendidos() {
        String[] colunas = {"id", "nome", "preco", "quantidade"};
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Cursor cursor = getWritableDatabase().query("produtovendido", colunas, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
            produto.setQuantidade(cursor.getInt(3));
            produtos.add(produto);
        }

        return produtos;
    }
    */


}
