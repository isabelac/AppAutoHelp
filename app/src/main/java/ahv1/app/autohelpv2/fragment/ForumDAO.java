package ahv1.app.autohelpv2.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.Activity.Comentario;
import ahv1.app.autohelpv2.adapter.ComentarioAdapter;

/**
 * Created by bella on 20/09/2017.
 */

public class ForumDAO extends SQLiteOpenHelper {

    SQLiteDatabase bancoDados;
    String verificaGuarda = null;
    static String sql = "CREATE TABLE IF NOT EXISTS Coment(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
            " txtPost VARCHAR(50) NOT NULL, autor VARCHAR(40), dataPost VARCHAR(20));";
    ArrayList<Comentario> listaItens;
    ArrayAdapter itensAdaptados;

    public ForumDAO(Context context) {
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
        String query = "DROP TABLE IF EXIST Coment";
        db.execSQL(query);
        this.onCreate(db);
    }

    public ArrayList<Comentario> getListaItens(){
        return listaItens;
    }

    public String GuardaPost(Comentario comentario, ListView lista, Context context) {
        System.out.println("entrei na funçao");

        try {
            if (comentario.getTxt_comentario().equals("")) {
                verificaGuarda = "Nenhum texto digitado";
            } else {
                System.out.println("entrei aqui");

                bancoDados = this.getReadableDatabase();
                Cursor t = bancoDados.rawQuery("SELECT * FROM Coment", null);
                System.out.println("testando select");
                int postIndex = t.getColumnIndex("txtPost");
                t.moveToFirst();

                boolean verificaExistencia = false;

                while (!t.isAfterLast()) {
                    if (t.getString(postIndex).equals(comentario.getTxt_comentario())) {
                        verificaExistencia = true;
                    }
                    t.moveToNext();
                }

                if (!verificaExistencia) {

                    bancoDados = this.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    String query = "select * from Coment ";
                    Cursor cursor = bancoDados.rawQuery(query,null);
                    int count = cursor.getCount();
                    values.put("txtPost", comentario.getTxt_comentario());
                    values.put("autor", comentario.getUsuario());
                    values.put("dataPost", comentario.getDataPost());

                    bancoDados.insert("Coment", null, values);

                    verificaGuarda = "Dúvida Publicada";
                    //recuperaPost(lista);

                } else {
                    verificaGuarda = "Dúvida Publicada";
                }
            }
                recuperaPost(lista, context);
                bancoDados.close();
                return verificaGuarda;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void recuperaPost(ListView lista, Context context){
        try {
            bancoDados = this.getReadableDatabase();
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM Coment ORDER BY id DESC", null);
            //int IdIndex = cursor.getColumnIndex("id");
            int postIndex = cursor.getColumnIndex("txtPost");
            int autorIndex = cursor.getColumnIndex("autor");
            int dataIndex = cursor.getColumnIndex("dataPost");
            listaItens = new ArrayList<>();
            cursor.moveToFirst();
            System.out.println("Cheguei pra lista");

            Comentario post;

            while (!cursor.isAfterLast()) {
                post = new Comentario();

                post.setTxt_comentario(cursor.getString(postIndex).toString());
                post.setUsuario(cursor.getString(autorIndex).toString());
                post.setDataPost(cursor.getString(dataIndex).toString());

                Log.i("Resultado: ", cursor.getString(postIndex).toString());
                listaItens.add(post);
                cursor.moveToNext();
            }
            //itensAdaptados = new ArrayAdapter<String>(context, R.layout.list,teste);

            itensAdaptados = new ComentarioAdapter(context, listaItens);

            System.out.println("To aqui");
            lista.setAdapter(itensAdaptados);


        }catch (Exception e){
            e.printStackTrace();
        }
    }



}

