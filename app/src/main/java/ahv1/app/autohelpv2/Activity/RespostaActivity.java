package ahv1.app.autohelpv2.Activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.EditaPerfil.CircleImage;
import ahv1.app.autohelpv2.R;

public class RespostaActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    TextView textautor;
    TextView textdata;
    String txtPergunta;
    String dataPost;
    ListView listaResp;
    String autor;
    RespostaDAO resp;
    String Autor2;
    ImageView imagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        Bundle extra = getIntent().getExtras();
        textView = (TextView) findViewById(R.id.textResposta);
        textautor = (TextView) findViewById(R.id.textautor);
        textdata = (TextView) findViewById(R.id.textdata);
        imagem = (ImageView) findViewById(R.id.imageViewResp);

        listaResp = (ListView) findViewById(R.id.ListR);

        if(extra != null){
            txtPergunta = extra.getString("txtComentario");
            dataPost = extra.getString("data");
            autor = extra.getString("autor");
            Autor2 = extra.getString("Username");
        }

        CircleImage circleImage = new CircleImage(this);
        DatabaseHelper dataHelper = new DatabaseHelper(this);
        byte[] imgbyte = dataHelper.recuperaFoto(autor);

        if(imgbyte != null) {
           ByteArrayInputStream streamFoto = new ByteArrayInputStream(imgbyte);
           Bitmap fotoBitmap = BitmapFactory.decodeStream(streamFoto);
           imagem.setImageBitmap(fotoBitmap);
        }

        textView.setText(txtPergunta);
        textautor.setText(autor);
        textdata.setText(dataPost);

        //inicializa toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarConversa);
        toolbar.setTitle("AutoHelp");
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        resp = new RespostaDAO(RespostaActivity.this);
        resp.recuperaResposta(listaResp, dataPost, RespostaActivity.this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initLFloatingButtons() {

        final int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());

        final ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, size, size);
            }
        };

        ImageButton floatingButton = ((ImageButton) findViewById(R.id.floatingButton));
        floatingButton.setOutlineProvider(viewOutlineProvider);
        floatingButton.setClipToOutline(true);

    }

    public void ButtonResposta(View v) {
        System.out.println("onclik");
        if (v.getId() == R.id.ButtonResposta) {
            listaResp = (ListView) findViewById(R.id.ListR);
            EditText edit = (EditText) findViewById(R.id.EditTeste);
            Comentario post = new Comentario();
            Locale locale = new Locale("pt", "BR");
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);

            post.setDataPost(formatador.format(calendar.getTime()));
            post.setUsuario(Autor2);
        System.out.println("è este autor: "+getIntent().getStringExtra("Username")+" "+Autor2);
            post.setTxt_comentario(edit.getText().toString());

            System.out.println(edit.getText().toString());

            resp = new RespostaDAO(RespostaActivity.this);
            String resultado = resp.GuardaResposta(post, dataPost, listaResp, RespostaActivity.this);

            Toast.makeText(RespostaActivity.this, ""+resultado+"", Toast.LENGTH_SHORT).show();
            edit.setText("");
        }
    }



}
