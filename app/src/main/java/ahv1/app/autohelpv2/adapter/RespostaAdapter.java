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
 * Created by bella on 26/10/2017.
 */

public class RespostaAdapter extends ArrayAdapter<Comentario> {
    private ArrayList<Comentario> comentarios;
    private Context context;

    public RespostaAdapter(Context c, ArrayList<Comentario> objects) {
        super(c, 0, objects);
        this.context = c;
        this.comentarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(comentarios != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_resposta, parent, false);

            TextView comentario = (TextView) view.findViewById(R.id.texComent);
            TextView autor = (TextView) view.findViewById(R.id.textView);
            TextView data = (TextView) view.findViewById(R.id.textView2);
            ImageView imagem = (ImageView) view.findViewById(R.id.imageView);

            Comentario post = comentarios.get(position);

            DatabaseHelper dataHelper = new DatabaseHelper(context);
            CircleImage circleImage = new CircleImage(context);

            byte[] fotouser = dataHelper.recuperaFoto(post.getUsuario());

            if(fotouser != null){
                ByteArrayInputStream stream = new ByteArrayInputStream(fotouser);
                Bitmap foto = circleImage.getCroppedBitmap(BitmapFactory.decodeStream(stream), 300);
                imagem.setImageBitmap(foto);
            }

            comentario.setText(post.getTxt_comentario());
            autor.setText(post.getUsuario());
            data.setText(post.getDataPost());
        }
        return  view;
    }
}
