package ahv1.app.autohelpv2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;

import java.util.List;
import java.util.Map;

public class DebugSimpleExpandableListAdapter extends SimpleExpandableListAdapter {

    Context context;
    List listachild;
    static final String listdesc[][][][] = {
            {{{"Problema na Bomba", "Junta", "Tubulação"},
                   {"Ventoinha"}, {"Fumaça branca em excesso", "Fumaça escura"},
                   {"Mangueira", "Junta", "Filtro", "Reservatório"}}}, {
            {{"Motor de arranque", "Bateria"},{"Bateria"},{"Bateria arriada"}}},
            {{{"Parafuso da roda", "Parafuso tambor do freio"},
                   {"Roda torta", "Problemas comuns"}}}
                };

  String prob;

    int tpai, tchild;

    public DebugSimpleExpandableListAdapter(int tpai, int tchild, Context context,
                                            List<? extends Map<String, ?>> groupData, int groupLayout,
                                            String[] groupFrom, int[] groupTo,
                                            List<? extends List<? extends Map<String, ?>>> childData,
                                            int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData,
                childLayout, childFrom, childTo);
        this.context = context;
        this.listachild = childData;
        this.tchild = tchild;
        this.tpai = tpai;
    }

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = super.getChildView( groupPosition, childPosition, isLastChild, convertView, parent );
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.out.println("Teste: "+groupPosition +" "+ childPosition+ " "+ tchild+" "+ tpai);
                if(tpai==0){
                     prob = listdesc[tpai][tchild][groupPosition][childPosition];
                } else {
                     prob =  listdesc[tpai][tchild][groupPosition-1][childPosition];
                }
                AlertDialog.Builder alert =  new AlertDialog.Builder(context)
                        .setTitle("Resposta: ")
                        .setMessage("Você clicou em: "+prob).
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                alert.create().show();
            }
        });
        return v;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = super.getGroupView( groupPosition, isExpanded, convertView, parent );
        return v;
    }

    private static final String LOG_TAG = "DebugSimpleExpandableListAdapter";
}

