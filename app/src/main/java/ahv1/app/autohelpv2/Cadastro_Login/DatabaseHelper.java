package ahv1.app.autohelpv2.Cadastro_Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M on 28/07/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AHcontacts";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;
    public static String autorAtual = null;

    private static final String TABLE_CREATE = "create table contacts (id primary key not null," +
            "name text not null, email text not null, uname text not null, pass text not null, Imagem BLOB);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    public void insertContact(Contact c)
    {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String query = "select * from contacts ";
            Cursor cursor = db.rawQuery(query, null);
            int count = cursor.getCount();
            values.put(COLUMN_ID, count);
            values.put(COLUMN_NAME, c.getName());
            values.put(COLUMN_EMAIL, c.getEmail());
            values.put(COLUMN_UNAME, c.getUname());
            values.put(COLUMN_PASS, c.getPass());

            db.insert(TABLE_NAME, null, values);
            db.close();
    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname,pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = null;

        if(cursor.moveToFirst()) {
            do{
                a = cursor.getString(0);

                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            } while(cursor.moveToNext());
        }

        return b;

    }

    public Contact RetornaUser(String nomeUser){
        db = this.getReadableDatabase();
        String query = "select * from contacts where uname = '"+nomeUser+"'";
        Cursor cursor = db.rawQuery(query, null);

        int indexUser = cursor.getColumnIndex("name");
        int indexNameUser = cursor.getColumnIndex("uname");
        int indexEmail = cursor.getColumnIndex("email");
        Contact contact = null;

        if(cursor.moveToFirst()) {
            contact = new Contact();
            contact.setEmail(cursor.getString(indexEmail));
            contact.setUname(cursor.getString(indexNameUser));
            contact.setName(cursor.getString(indexUser));
        }

        return contact;
    }

    public byte[] recuperaFoto(String nome){
        try{
            db = getReadableDatabase();
            String query = "select * from contacts where uname = '"+nome+"'";
            Cursor cursor = db.rawQuery(query, null);
            int indexFoto = cursor.getColumnIndex("Imagem");
            byte[] img = null;
            cursor.moveToFirst();
            if(cursor.getCount() == 0){
                return null;
            } else {
                img = cursor.getBlob(indexFoto);
            }
            return img;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String mudaFoto(String autor, byte[] foto) {
        try {
            String resultado;
            if (foto == null || autor.equals(null)) {
                resultado = "Nenhuma Imagem Selecionada";
            } else {
                db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                String query = "select * from contacts ";
                Cursor cursor = db.rawQuery(query, null);
                int count = cursor.getCount();
                cursor.moveToFirst();

                values.put("Imagem", foto);

                db.update("contacts", values, ("name = '" + autor + "'"), null);
                db.close();
                resultado = "Imagem Guardada com Sucesso";
            }
            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String UpdateContacts(Contact contact, byte[] foto, String userAtual){
        String result;
        System.out.println("olaqrida");
        if(contact.getName().equals("") || contact.getEmail().equals("")){
            result = "Por Favor, Preencha Todos os Campos";
        } else {

            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("name", contact.getName());
            values.put("email", contact.getEmail());
            values.put("Imagem", foto);

            db.update(TABLE_NAME, values, "uname = '"+userAtual+"'", null);
            db.close();
            result = "Atualização Feita com Sucesso!";
        }

        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXIST " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
