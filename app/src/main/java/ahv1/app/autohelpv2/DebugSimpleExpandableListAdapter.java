package ahv1.app.autohelpv2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ahv1.app.autohelpv2.adapter.AlertAdpter;
import ahv1.app.autohelpv2.fragment.StringTutoriais;

public class DebugSimpleExpandableListAdapter extends SimpleExpandableListAdapter {

    Context context;
    List listachild;
    static final String listdesc[][][][] = new StringTutoriais().StringtutoResp();
    static final String listdescResp[][][][] = new StringTutoriais().StringtutorialResp();

    String prob, resolucao;
    DebugExpandableListView[] listcache;
    int tpai, tchild;

    public DebugSimpleExpandableListAdapter(DebugExpandableListView listcache[], int tpai, int tchild, Context context,
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
        this.listcache = listcache;
    }

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = super.getChildView( groupPosition, childPosition, isLastChild, convertView, parent );
        System.out.println("listdesc["+tpai+"]["+tchild+"]["+groupPosition+"]["+childPosition+"]");
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prob = listdesc[tpai][tchild][groupPosition][childPosition];
                resolucao = listdescResp[tpai][tchild][groupPosition][childPosition];

                System.out.println("Teste: "+groupPosition +" "+ childPosition+ " "+ tchild+" "+ tpai+" "+prob);

                AlertDialog.Builder alert =  new AlertDialog.Builder(context)
                        .setTitle(prob)
                        .setMessage(resolucao).
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

    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = super.getGroupView( groupPosition, isExpanded, convertView, parent );
        //lista[tpai][tchild][groupPosition].length;

        return v;
    }

    private static final String LOG_TAG = "DebugSimpleExpandableListAdapter";
}

