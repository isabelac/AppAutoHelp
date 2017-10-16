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

import ahv1.app.autohelpv2.R;

/**
 * Created by Thales on 28/09/2017.
 */
public class ListaEspecificaDAO extends SQLiteOpenHelper {

    SQLiteDatabase BdProbEspecifico;
    ArrayList<String> listaProbEsp;
    ArrayAdapter<String> adapter;
    String sqlProblemasEspecificos = "CREATE TABLE IF NOT EXISTS ProblemasEpecificos(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL" +
            ", txtProb VARCHAR(40) NOT NULL, idProb VARCHAR(40) NOT NULL);";

    public ListaEspecificaDAO(Context context) {
        super(context, "AutoHelp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BdProbEspecifico) {
        BdProbEspecifico.execSQL(sqlProblemasEspecificos);
        this.BdProbEspecifico = BdProbEspecifico;
    }

    @Override
    public void onUpgrade(SQLiteDatabase BdProbEspecifico, int i, int i1) {
        String query = "DROP TABLE IF EXIST ProblemasEpecificos";
        BdProbEspecifico.execSQL(query);
        this.onCreate(BdProbEspecifico);
    }

    public ArrayList<String> getListaProb(){
        return listaProbEsp;
    }

    public void listaProbEsp(ListView lista, String id, Context context){
        try {

            BdProbEspecifico = this.getReadableDatabase();
            System.out.println("chegou lista");
            String query = "select * from ProblemasEpecificos where idProb = '"+id+"'";
            Cursor cursor = BdProbEspecifico.rawQuery(query, null);
            int txtIndex = cursor.getColumnIndex("txtProb");

            listaProbEsp = new ArrayList<>();
            cursor.moveToFirst();

            //Comentario post;
            while (!cursor.isAfterLast()) {
                Log.i("Resultado: ", cursor.getString(txtIndex));
                listaProbEsp.add(cursor.getString(txtIndex));
                cursor.moveToNext();
            }

            adapter = new ArrayAdapter<String>(context , R.layout.list, listaProbEsp);

            lista.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean insereProbEsp(String problema, String idProblema, ListView lista, Context context){
        try {
            BdProbEspecifico = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String query = "select * from Problemas where idProb = '"+idProblema+"'";

            Cursor cursor = BdProbEspecifico.rawQuery(query,null);
            System.out.println("inseriuEsp");
            int count = cursor.getCount();

            values.put("txtProb", problema);

            BdProbEspecifico.insert("ProblemasEpecificos", null, values);
            listaProbEsp(lista,idProblema, context);
            return  true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

