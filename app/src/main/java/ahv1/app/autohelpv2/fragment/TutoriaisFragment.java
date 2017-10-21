package ahv1.app.autohelpv2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import ahv1.app.autohelpv2.ExpandableAdapter;
import ahv1.app.autohelpv2.R;

/**
 * Created by bella on 11/10/2017.
 */

public class TutoriaisFragment extends Fragment {
    static final String listdesc[][][][] = {
            { //Problemas
                    {  // Motor
                            {"Motor", "Vazamento de Agua"},
                            {"     Problema na Bomba", ""},
                            {"     Junta", ""},
                            {"     Tubulação", ""}
                    },
                    {  // Superaquecimento
                            {"Motor", "Superaquecimento"},
                            {"     Ventoinha", ""},

                    },
                    {
                            {"Motor", "Fumaça"},
                            {"     Fumaça branca em excesso", ""},
                            {"     Fumaça escura", ""}
                    },
                    {
                            {"Motor", "Vazamento de óleo"},
                            {"     Mangueira", ""},
                            {"     Junta", ""},
                            {"     Filtro", ""},
                            {"     Reservatório", ""}

                    }
            },
            {
                    {
                            {"Elétrica", "Faróis queimados"}
                    },
                    {
                            {"Elétrica", "Carro não liga"},
                            {"      Motor de arranque", ""},
                            {"      Bateria", ""}
                    },
                    {
                            {"Elétrica", "Som"},
                            {"      Bateria", ""}
                    },
                    {
                            {"Elétrica", "Bateria"},
                            {"      Bateria arriada", ""}
                    }
            },
            {
                    {
                            {"Rodas", "Pneus furados"}
                    },
                    {
                            {"Rodas", "Rodas tortas"},
                            {"      Parafuso da roda", ""},
                            {"      Parafuso tambor do freio", ""}
                    },
                    {
                            {"Rodas", "Barulhos nas rodas"},
                            {"      Roda torta", ""},
                            {"      Problemas comuns", ""}
                    }
            }
    };

    View v;
    private ExpandableAdapter colorExpListAdapter;
    ExpandableListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.main, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ExpandableListView) v.findViewById(R.id.listAdp);
        colorExpListAdapter =
                new ExpandableAdapter(
                        getActivity(),
                        list,
                        listdesc
                );
        list.setAdapter(colorExpListAdapter);
        list.setGroupIndicator(null);
    }
}