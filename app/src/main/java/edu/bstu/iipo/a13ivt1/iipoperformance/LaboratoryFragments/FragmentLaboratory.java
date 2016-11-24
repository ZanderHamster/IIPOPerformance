package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class FragmentLaboratory extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static FragmentLaboratory newInstance() {
        FragmentLaboratory fragment = new FragmentLaboratory();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getActivity().setTitle(R.string.laboratory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laboratory, container, false);
        getActivity().setTitle(getString(R.string.laboratory));

        ArrayList<HelpLaboratory> infoItem = new ArrayList<HelpLaboratory>();
        infoItem.add(new HelpLaboratory("David"));
        infoItem.add(new HelpLaboratory("Nikola"));

        ListView listView = (ListView) view.findViewById(R.id.list_laboratiry);
        ArrayAdapter<HelpLaboratory> listViewAdapter = new AdapterLaboratory(getActivity(),R.layout.list_laboratory,infoItem);
        listView.setAdapter(listViewAdapter);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
