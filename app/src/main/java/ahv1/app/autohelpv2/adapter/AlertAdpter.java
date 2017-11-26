package ahv1.app.autohelpv2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import ahv1.app.autohelpv2.Activity.Comentario;
import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.EditaPerfil.CircleImage;
import ahv1.app.autohelpv2.R;

/**
 * Created by Lucas Estacio on 25/11/2017.
 */

public class AlertAdpter {
    private String tutoriais;
    private Context context;
    private String texto;

    public AlertAdpter(String objects, String texto) {
        //super(c, 0, objects);
        //this.context = c;
        this.tutoriais = objects;
        this.texto = texto;
    }
    public View view(){
        View view = null;
        if(tutoriais != null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alert_dialog, null);

            TextView comentario = (TextView) view.findViewById(R.id.textView5);
            TextView autor = (TextView) view.findViewById(R.id.textView6);
            comentario.setText(tutoriais);
            comentario.setText(texto);
        }

        return  view;
    }
}
