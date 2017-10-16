package ahv1.app.autohelpv2.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.R;

/**
 * Created by Thales on 28/09/2017.
 */
public class ProblemasDAO extends SQLiteOpenHelper {

    SQLiteDatabase BdProblemas;
    ArrayList<String> listaProb;
    ArrayAdapter<String> adapter;
    String sqlProblemas = "CREATE TABLE IF NOT EXISTS Problemas(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL" +
                           ", txtProb VARCHAR(40) NOT NULL);";

    public ProblemasDAO(Context context) {
        super(context, "AutoHelpProblemas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BdProblemas) {
        BdProblemas.execSQL(sqlProblemas);
        this.BdProblemas = BdProblemas;
    }

    @Override
    public void onUpgrade(SQLiteDatabase BdProblemas, int i, int i1) {
        String query = "DROP TABLE IF EXIST Problemas";
        BdProblemas.execSQL(query);
        this.onCreate(BdProblemas);
    }

    public ArrayList<String> getListaProb(){
        return listaProb;
    }
    public void setListaProb(ArrayList<String> listaProb){
        this.listaProb = listaProb;
    }

    public void listaProblemas(ListView lista, Context context){
        try {
            BdProblemas = this.getReadableDatabase();
            Cursor cursor = BdProblemas.rawQuery("SELECT * FROM Problemas ORDER BY txtProb", null);

            int txtIndex = cursor.getColumnIndex("txtProb");

            listaProb = new ArrayList<>();
            cursor.moveToFirst();

            //Comentario post;
            while (!cursor.isAfterLast()) {
                //Log.i("Resultado: ", cursor.getString(postIndex));
                listaProb.add(cursor.getString(txtIndex));
                cursor.moveToNext();
            }

            adapter = new ArrayAdapter<String>(context , R.layout.list, listaProb);

            lista.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean insereProblemas(String problema, ListView lista, Context context){
        try {
            BdProblemas = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String query = "select * from Problemas ";
            Cursor cursor = BdProblemas.rawQuery(query, null);
            int count = cursor.getCount();
            values.put("txtProb", problema);

            BdProblemas.insert("Problemas", null, values);
            listaProblemas(lista, context);
            return  true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
