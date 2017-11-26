package ahv1.app.autohelpv2.Cadastro_Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ahv1.app.autohelpv2.MainActivity;
import ahv1.app.autohelpv2.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    public static SharedPreferences sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessao = getSharedPreferences("usuarioLogado" , MODE_PRIVATE);
        System.out.println(sessao.toString());
        VerificaUser();
    }

    public void VerificaUser(){
        if(sessao.edit().equals("")){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Blogin) {

            EditText a = (EditText) findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            Contact contact = helper.RetornaUser(str);

            if(pass.equals(password)) {
                SharedPreferences.Editor edit  = sessao.edit();
                edit.putString(str, "user");
                edit.commit();

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("Username", str);
                i.putExtra("nomeCompleto", contact.getName());
                System.out.println("nome completo"+contact.getName());
                startActivity(i);
            } else {
                Toast temp = Toast.makeText(LoginActivity.this, "Usuário ou Senha Incorreto", Toast.LENGTH_SHORT);
                temp.show();
            }
        }

        if (v.getId() == R.id.Bsignup) {
            Intent i = new Intent(LoginActivity.this, SignUp.class);
            startActivity(i);
        }
    }

}
