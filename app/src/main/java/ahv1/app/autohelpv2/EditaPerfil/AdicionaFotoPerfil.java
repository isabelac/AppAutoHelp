package ahv1.app.autohelpv2.EditaPerfil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ahv1.app.autohelpv2.Cadastro_Login.DatabaseHelper;
import ahv1.app.autohelpv2.MainActivity;
import ahv1.app.autohelpv2.R;

public class AdicionaFotoPerfil extends AppCompatActivity {

    ImageView imageView;
    byte[] byteImg = null;
    String autor = null;
    Button botaoPular;
    Button botaoSalvar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_foto_perfil);

        botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        botaoPular = (Button) findViewById(R.id.buttonPular);
        imageView = (ImageView) findViewById(R.id.imageViewPerfil);
        text = (TextView) findViewById(R.id.textusuario);

        Bundle autr = getIntent().getExtras();

        if(autr != null){
            autor = autr.getString("UserF");
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompartilharFoto();
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompartilharFoto();
            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resp = SalvaImage(byteImg);
                Toast.makeText(AdicionaFotoPerfil.this, ""+resp+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdicionaFotoPerfil.this, MainActivity.class);
                intent.putExtra("Username", autor);
                startActivity(intent);
            }
        });

        botaoPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionaFotoPerfil.this, MainActivity.class);
                intent.putExtra("Username", autor);
                startActivity(intent);
            }
        });
    }

    public void CompartilharFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public String SalvaImage(byte[] byteImg){
        try{
            String resposta;
            if(byteImg == null){
                resposta = "Imagem não selecionada";
            } else {
                DatabaseHelper bd = new DatabaseHelper(this);

                bd.mudaFoto(autor, byteImg);
                resposta = "Imagem Salva Com Sucesso!";
            }
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
                Bitmap imagem = MediaStore.Images.Media.getBitmap( getContentResolver() ,localImg);

                CircleImage t = new CircleImage(this);
                Bitmap img = t.getCroppedBitmap(imagem, 300);

                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG, 75, byteArrayInputStream);


                imageView.setImageBitmap(img);
                byteImg = byteArrayInputStream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
