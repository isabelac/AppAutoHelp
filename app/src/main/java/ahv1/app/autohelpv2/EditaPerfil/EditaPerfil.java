package ahv1.app.autohelpv2.EditaPerfil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ahv1.app.autohelpv2.Cadastro_Login.Contact;
import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.Cadastro_Login.LoginActivity;
import ahv1.app.autohelpv2.MainActivity;
import ahv1.app.autohelpv2.R;

public class EditaPerfil extends AppCompatActivity {
    ImageView imagem;
    byte[] byteImg;
    Button botao;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    String autor;
    Toolbar toolbar;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_perfil);

        extra = getIntent().getExtras();
        if(extra!=null){
            autor = extra.getString("Usuario");
        }
        //inicializa toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarPerfil);
        toolbar.setTitle(autor);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        imagem = (ImageView) findViewById(R.id.imageView5);
        botao = (Button) findViewById(R.id.button4);
        TextView textImg = (TextView) findViewById(R.id.textMudaFoto);
        editText2 = (EditText) findViewById(R.id.EditNameUser);
        editText3 = (EditText) findViewById(R.id.EditEmail);


        DatabaseHelper bd2 = new DatabaseHelper(this);
        CircleImage circleImg = new CircleImage(this);

        final byte[] imageAtual = bd2.recuperaFoto(autor);
        if(imageAtual != null) {
            ByteArrayInputStream stream = new ByteArrayInputStream(imageAtual);
            Bitmap byteImagem = circleImg.getCroppedBitmap(BitmapFactory.decodeStream(stream), 300);
            imagem.setImageBitmap(byteImagem);
        }

        Contact contact = bd2.RetornaUser(autor);
        editText2.setText(contact.getName());
        editText3.setText(contact.getEmail());

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbreGaleria();
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contatoNovo = new Contact();
                if(byteImg == null){
                    byteImg = imageAtual;
                }
                contatoNovo.setName(editText2.getText().toString());
                contatoNovo.setEmail(editText3.getText().toString());
                String resp = SalvaImage(byteImg, contatoNovo);
                Toast.makeText(EditaPerfil.this, ""+resp+"", Toast.LENGTH_SHORT).show();
            }
        });

        textImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbreGaleria();
            }
        });

    }

    private void AbreGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public String SalvaImage(byte[] byteImg, Contact contato){
        try{
            DatabaseHelper bd = new DatabaseHelper(this);
            String resposta = bd.UpdateContacts(contato, byteImg, autor);
            autor = null;
            return resposta;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri localImg = data.getData();

            try {
                Bitmap bitimg = MediaStore.Images.Media.getBitmap( getContentResolver() ,localImg);

                CircleImage t = new CircleImage(this);
                Bitmap img = t.getCroppedBitmap(bitimg, 300);

                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG, 75, byteArrayInputStream);


                imagem.setImageBitmap(img);
                byteImg = byteArrayInputStream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
