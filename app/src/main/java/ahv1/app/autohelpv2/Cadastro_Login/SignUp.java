package ahv1.app.autohelpv2.Cadastro_Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ahv1.app.autohelpv2.EditaPerfil.AdicionaFotoPerfil;
import ahv1.app.autohelpv2.R;

/**
 * Created by M on 28/07/2017.
 */
public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v) {
        if(v.getId()== R.id.Bsignupbutton) {

            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str)) {
                Toast pass = Toast.makeText(SignUp.this, "Senhas Incopativeis", Toast.LENGTH_SHORT);
                pass.show();

            } else{
                // insert to detailes in database
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);

                String[] arrayEmail = c.getEmail().split("");
                String[]arrayemail2 = c.getEmail().split(".");
                boolean verificaEmail = false;
                boolean verificaEmail2 = false;

                for(int i = 0; i < arrayEmail.length; i++){
                    if(arrayEmail[i].equals("@")){
                        verificaEmail2 = true;
                    }
                }

                if(!c.getEmail().equals("") && !c.getName().equals("") && !c.getPass().equals("")
                        && !c.getUname().equals("")){

                    if( !verificaEmail2 ){
                        Toast.makeText(SignUp.this, "Formato de Email Incorreto", Toast.LENGTH_SHORT).show();

                    } else {

                        String result = helper.searchPass(c.getUname());

                        if (result == null) {
                            helper.insertContact(c);
                            Toast.makeText(SignUp.this, "Usuário Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this, AdicionaFotoPerfil.class);
                            i.putExtra("UserF", unamestr);
                            i.putExtra("nomeCompleto", helper.RetornaUser(c.getUname()).getName());
                            startActivity(i);
                        } else {
                            Toast.makeText(SignUp.this, "Nome de Usuário Já Cadastrado no Sistema", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(SignUp.this, "Por Favor Preencha Todos os Campos", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}


