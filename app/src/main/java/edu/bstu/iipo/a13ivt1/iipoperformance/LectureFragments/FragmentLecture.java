package edu.bstu.iipo.a13ivt1.iipoperformance.LectureFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class FragmentLecture extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.lecture));

        return inflater.inflate(R.layout.fragment_lecture, container, false);
    }

}
