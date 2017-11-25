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
    static final String listdesc[][][][] = new StringTutoriais().Stringtuto();

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