package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students_Table;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent_Table;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.UniversityDB;
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

//        for (int i = 0; i < 19; i++) {
//            TestStudent students = new TestStudent();
//            students.setName("Name "+(i+1));
//            students.setSurname("Surname "+(i+1));
//            students.save();
//        }


        final ArrayAdapter<TestStudent> listViewAdapter = new AdapterLaboratory(getActivity(),R.layout.list_laboratory,ShowAllRecords());
        final ListView listView=(ListView) view.findViewById(R.id.list_laboratiry);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                List<TestStudent> s = new Select().from(TestStudent.class).where(TestStudent_Table.id.is(((TestStudent) parent.getAdapter().getItem(position)).getId())).queryList();

                s.get(0).setName(s.get(0).getName()+" Edit");
                s.get(0).save();

                listViewAdapter.notifyDataSetChanged();// это не работает
                return true;
            }
        });
        return view;
    }


    // Удаление всех записей из таблицы TestStudent (возвращает пустой список)
    public ArrayList<TestStudent> ClearAllRecords(){
        ArrayList<TestStudent> arrayList = new ArrayList<TestStudent>();
        List<TestStudent> studentses = new Select().from(TestStudent.class).where().queryList();
        for (int i = 0; i < studentses.size(); i++) {
             studentses.get(i).delete(); //Удаление всех записей из таблицы
        }
        for (int i = 0; i < studentses.size(); i++) {
             arrayList.add(studentses.get(i));
        }

        return arrayList;
    }

    // Отображение всех записей из таблицы TestStudent (возвращает список со всеми элементами таблицы)
    public ArrayList<TestStudent> ShowAllRecords(){
        ArrayList<TestStudent> arrayList = new ArrayList<TestStudent>();
        List<TestStudent> studentses = new Select().from(TestStudent.class).where().queryList();
        for (int i = 0; i < studentses.size(); i++) {
            arrayList.add(studentses.get(i));
        }

        return arrayList;
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
