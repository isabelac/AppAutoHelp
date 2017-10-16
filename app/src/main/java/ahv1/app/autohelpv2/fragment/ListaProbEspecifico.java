package ahv1.app.autohelpv2.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import ahv1.app.autohelpv2.R;

public class ListaProbEspecifico extends AppCompatActivity {

    ListView lista;
    String prob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prob_especifico);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            prob = extras.getString("problema");
            System.out.println(prob + "thales2");
        }

        lista = (ListView)findViewById(R.id.listviewprobEsp);

        ListaEspecificaDAO persisteLista = new ListaEspecificaDAO(ListaProbEspecifico.this);

        if(persisteLista.getListaProb() == null){
            persisteLista.insereProbEsp("Vazamento de óleo", prob, lista, ListaProbEspecifico.this);
            System.out.println("persistiu probEsp");
            persisteLista.insereProbEsp("Vazamento de água", prob, lista, ListaProbEspecifico.this);

        }
        persisteLista.listaProbEsp(lista, prob, ListaProbEspecifico.this);
        System.out.println("chegouuu");

    }
}
