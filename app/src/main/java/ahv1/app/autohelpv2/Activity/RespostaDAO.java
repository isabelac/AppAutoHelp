package ahv1.app.autohelpv2.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.adapter.ComentarioAdapter;


/**
 * Created by bella on 20/09/2017.
 */

public class RespostaDAO extends SQLiteOpenHelper {
    private SQLiteDatabase bancoDados;
    String verificaGuarda = null;
    static String sql = "CREATE TABLE IF NOT EXISTS Resp_Coment(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
            " txtPost VARCHAR(50) NOT NULL, autor VARCHAR(40), dataPost VARCHAR(20), id_questao VARCHAR(20) NOT NULL);";
    ArrayList<Comentario> listaItensResp;
    ArrayAdapter itemResp;

    public RespostaDAO(Context context) {
        super(context, "AutoHelp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bancoDados) {

        bancoDados.execSQL(sql);
        System.out.println("criei bd");
        this.bancoDados = bancoDados;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST Resp_Coment";
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
                System.out.println("entrei aqui");

                values.put("txtPost", comentario.getTxt_comentario());
                values.put("autor", comentario.getUsuario());
                values.put("dataPost", comentario.getDataPost());
                values.put("id_questao", dataPost);

                System.out.println("entrei aqui tbm");

                bancoDados.insert("Resp_Coment", null, values);
                verificaGuarda = "Resposta Publicada";
                recuperaResposta(lista, dataPost, context);
            }

            return verificaGuarda;
        } catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public void recuperaResposta(ListView lista, String dataPost, Context context){
        try {
            bancoDados = getReadableDatabase();
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM Resp_Coment WHERE id_questao = "+ dataPost +" ORDER BY id DESC", null);
            int postIndex = cursor.getColumnIndex("txtPost");
            int autorIndex = cursor.getColumnIndex("autor");
            int dataIndex = cursor.getColumnIndex("dataPost");

            listaItensResp = new ArrayList<>();
            cursor.moveToFirst();
            System.out.println("Cheguei pra lista");

            Comentario post;
            while (!cursor.isAfterLast()) {
                post = new Comentario();

                post.setTxt_comentario(cursor.getString(postIndex));
                post.setUsuario(cursor.getString(autorIndex));
                post.setDataPost(cursor.getString(dataIndex));

                Log.i("Resultado: ", cursor.getString(postIndex));
                listaItensResp.add(post);
                cursor.moveToNext();
            }

            //itensAdaptados = new ArrayAdapter<>(RespostaActivity.this, R.layout.list, listaItens);
            itemResp = new ComentarioAdapter(context, listaItensResp);

            lista.setAdapter(itemResp);

            System.out.println("To aqui");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
