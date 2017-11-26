package ahv1.app.autohelpv2.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.adapter.RespostaAdapter;


/**
 * Created by bella on 20/09/2017.
 */

public class RespostaDAO extends SQLiteOpenHelper {
    SQLiteDatabase bancoDados;
    String verificaGuarda = null;
    static String sql = "CREATE TABLE IF NOT EXISTS PostResposta(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
            " txtPost VARCHAR(50) NOT NULL, autor VARCHAR(40), dataPost VARCHAR(20), id_questao VARCHAR(20) NOT NULL);";
    ArrayList<Comentario> listaItensResp;
    ArrayAdapter itemResp;

    public RespostaDAO(Context context) {
        super(context, "AutoHelpResp2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bancoDados) {

        bancoDados.execSQL(sql);
        this.bancoDados = bancoDados;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST PostResposta";
        db.execSQL(query);
        this.onCreate(db);
    }

    public String GuardaResposta(Comentario comentario, String dataPost, ListView lista, Context context) {

        try{
            if(comentario.getTxt_comentario().equals("")){
                verificaGuarda = "Digite sua Dúvida";
            } else {

                bancoDados = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                String query = "select * from PostResposta";
                Cursor cursor = bancoDados.rawQuery(query,null);
                int count = cursor.getCount();

                values.put("txtPost", comentario.getTxt_comentario());
                values.put("autor", comentario.getUsuario());
                values.put("dataPost", comentario.getDataPost());
                values.put("id_questao", dataPost);

                bancoDados.insert("PostResposta", null, values);
                verificaGuarda = "Resposta Publicada";
            }

            recuperaResposta(lista, dataPost, context);
            bancoDados.close();
            return verificaGuarda;
        } catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public void recuperaResposta(ListView lista, String dataPost, Context context){
        try {
            bancoDados = this.getReadableDatabase();

            String query =  "SELECT * FROM PostResposta WHERE  id_questao = '"+ dataPost+"' ORDER BY dataPost DESC";
            Cursor cursorResp = bancoDados.rawQuery(query, null);

            int postIndex = cursorResp.getColumnIndex("txtPost");
            int autorIndex = cursorResp.getColumnIndex("autor");
            int dataIndex = cursorResp.getColumnIndex("dataPost");

            listaItensResp = new ArrayList<>();
            cursorResp.moveToFirst();
            DatabaseHelper helper = new DatabaseHelper(context);
            Comentario post;
            while (!cursorResp.isAfterLast()) {
                post = new Comentario();
                String teste = cursorResp.getString(autorIndex);
                post.setTxt_comentario(cursorResp.getString(postIndex));
                post.setUsuario(helper.RetornaUser(cursorResp.getString(autorIndex).toString()).getName());
                post.setDataPost(cursorResp.getString(dataIndex));

                //Log.i("Resultado: ", cursorResp.getString(autorIndex));
                listaItensResp.add(post);
                cursorResp.moveToNext();
            }


            //itemResp = new ArrayAdapter<>(context, R.layout.list, listaItensResp);
            itemResp = new RespostaAdapter(context, listaItensResp);

            lista.setAdapter(itemResp);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
