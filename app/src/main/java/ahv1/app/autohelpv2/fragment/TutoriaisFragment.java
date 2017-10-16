package ahv1.app.autohelpv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ahv1.app.autohelpv2.Activity.Comentario;
import ahv1.app.autohelpv2.R;

/**
 * Created by bella on 11/10/2017.
 */

public class TutoriaisFragment extends Fragment {

    private ArrayList<Comentario> listaItens;
    private ArrayList<String> listaProb;
    private ListView lista;
    private View view;

    public TutoriaisFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tutoriais, container, false);

        lista = (ListView) view.findViewById(R.id.listviewprob);

        try {
            ProblemasDAO persisteProb = new ProblemasDAO(getActivity());
            persisteProb.listaProblemas(lista, getActivity());

                persisteProb.insereProblemas("Rodas", lista, getActivity());
                persisteProb.insereProblemas("Motor", lista, getActivity());
                persisteProb.insereProblemas("Suspensão", lista, getActivity());
                persisteProb.insereProblemas("Elétrica", lista, getActivity());

            persisteProb.listaProblemas(lista, getActivity());


            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Respostas(position);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    public void Respostas(int posicao) {

        String problema = listaProb.get(posicao);
        Intent intent = new Intent(getActivity(), ListaProbEspecifico.class);
        intent.putExtra("problema", problema);
        System.out.println(problema + "thales");
        startActivity(intent);
    }

}

