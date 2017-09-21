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
import ahv1.app.autohelpv2.Activity.RespostaActivity;
import ahv1.app.autohelpv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {

    private ArrayList<Comentario> listaItens;
    private ListView lista;
    private View view;

    public ForumFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_forum, container, false);
        lista = (ListView) view.findViewById(R.id.List_id);
        final ForumDAO forum = new ForumDAO(getActivity());
        forum.recuperaPost(lista, getActivity());


        listaItens = forum.getListaItens();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Respostas(position);
            }
        });

        return view;
    }

    public void Respostas(int posicao){
        System.out.println("Entrei on clik");
        Comentario comentario = listaItens.get(posicao);
        Intent intent = new Intent(getActivity(), RespostaActivity.class);
        intent.putExtra("txtComentario", comentario.getTxt_comentario());
        intent.putExtra("data", comentario.getDataPost());
        intent.putExtra("autor", comentario.getUsuario());
        System.out.printf("startando activity");
        startActivity(intent);
    }



}