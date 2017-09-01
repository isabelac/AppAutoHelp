package ahv1.app.autohelpv2.Cadastro_Login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ahv1.app.autohelpv2.R;

/**
 * Created by M on 28/07/2017.
 */
public class Display extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
    }


}
