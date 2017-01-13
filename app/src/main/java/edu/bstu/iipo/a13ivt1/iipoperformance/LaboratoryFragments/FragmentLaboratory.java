package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;


import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent_Table;
import edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments.LabsList.LabsListFragment;
import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class FragmentLaboratory extends Fragment {
    // final List<TestStudent> s = new Select().from(TestStudent.class).where(TestStudent_Table.id.is(((TestStudent) parent.getAdapter().getItem(position)).getId())).queryList();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public AdapterLaboratory listViewAdapter2;

    FragmentTransaction fragmentTransaction;
    LabsListFragment labsListFragment = new LabsListFragment();

    public static FragmentLaboratory newInstance() {
        FragmentLaboratory fragment = new FragmentLaboratory();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.laboratory);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laboratory, container, false);

//        for (int i = 0; i < 19; i++) {
//            TestStudent students = new TestStudent();
//            students.setName("Name "+(i+1));
//            students.setSurname("Surname "+(i+1));
//            students.save();
//        }

        listViewAdapter2 = new AdapterLaboratory(getActivity(),R.layout.list_laboratory,ShowAllRecords());

        final ListView listView=(ListView) view.findViewById(R.id.list_laboratiry);
        listView.setAdapter(listViewAdapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_navigation,labsListFragment);
                fragmentTransaction.commit();
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


}
