package edu.bstu.iipo.a13ivt1.iipoperformance.CreditFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.bstu.iipo.a13ivt1.iipoperformance.R;


public class FragmentCredit extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.credit));
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }
}
