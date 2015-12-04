package br.senac.pi.projetoestoque.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aluno on 04/12/2015.
 */
public class ProdutoDB extends SQLiteOpenHelper {
    private static final String NOME_DB="produto.sqlite";
    private static final int VERSAO_DB=1;

    public ProdutoDB(Context context) {

        super(context,NOME_DB,null ,VERSAO_DB);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS produto(_id integer primary key autoincrement, nome TEXT ,preco DOUBLE , quantidade INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE produto ADD COLUMN descricao;");
    }

}
