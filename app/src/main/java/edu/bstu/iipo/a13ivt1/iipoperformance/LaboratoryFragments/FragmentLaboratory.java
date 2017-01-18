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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent;
import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.TestStudent_Table;
import edu.bstu.iipo.a13ivt1.iipoperformance.ExpandableListAdapter;
import edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments.LabsList.LabsListFragment;
import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class FragmentLaboratory extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Students> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public AdapterLaboratory2 listViewAdapter2;

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

        listViewAdapter2 = new AdapterLaboratory2(getActivity(),R.layout.list_laboratory,ShowAllRecords());

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        prepareListData();
        listAdapter = new ExpandableListAdapter(getContext(),listDataHeader,listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getContext(),String.valueOf(listDataHeader.get(groupPosition).getName()),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),
                        String.valueOf(listDataHeader.get(groupPosition).getName()) +" "+String.valueOf(listDataChild.get(String.valueOf(listDataHeader.get(groupPosition).getId())).get(childPosition)),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        final ListView listView=(ListView) view.findViewById(R.id.list_laboratiry);
//        listView.setAdapter(listViewAdapter2);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    private void prepareListData(){
        listDataHeader = ShowAllRecords();
        listDataChild = new HashMap<String, List<String>>();

        List<String> allLabs = new ArrayList<String>();
        allLabs.add("Лабораторная работа № 1");
        allLabs.add("Лабораторная работа № 2");
        allLabs.add("Лабораторная работа № 3");
        allLabs.add("Лабораторная работа № 4");
        allLabs.add("Лабораторная работа № 5");

        for (int i = 0; i < listDataHeader.size(); i++) {
            listDataChild.put(String.valueOf(listDataHeader.get(i).getId()),allLabs);
        }
    }
//    // Удаление всех записей из таблицы TestStudent (возвращает пустой список)
//    public ArrayList<TestStudent> ClearAllRecords(){
//        ArrayList<TestStudent> arrayList = new ArrayList<TestStudent>();
//        List<TestStudent> studentses = new Select().from(TestStudent.class).where().queryList();
//        for (int i = 0; i < studentses.size(); i++) {
//             studentses.get(i).delete(); //Удаление всех записей из таблицы
//        }
//        for (int i = 0; i < studentses.size(); i++) {
//             arrayList.add(studentses.get(i));
//        }
//
//        return arrayList;
//    }
//
//    // Отображение всех записей из таблицы TestStudent (возвращает список со всеми элементами таблицы)
//    public ArrayList<TestStudent> ShowAllRecords(){
//        ArrayList<TestStudent> arrayList = new ArrayList<TestStudent>();
//        List<TestStudent> studentses = new Select().from(TestStudent.class).where().queryList();
//        for (int i = 0; i < studentses.size(); i++) {
//            arrayList.add(studentses.get(i));
//        }
//
//        return arrayList;
//    }

    // Удаление всех записей из таблицы TestStudent (возвращает пустой список)
    public ArrayList<Students> ClearAllRecords(){
        ArrayList<Students> arrayList = new ArrayList<Students>();
        List<Students> studentses = new Select().from(Students.class).where().queryList();
        for (int i = 0; i < studentses.size(); i++) {
             studentses.get(i).delete(); //Удаление всех записей из таблицы
        }
        for (int i = 0; i < studentses.size(); i++) {
             arrayList.add(studentses.get(i));
        }

        return arrayList;
    }

    // Отображение всех записей из таблицы TestStudent (возвращает список со всеми элементами таблицы)
    public ArrayList<Students> ShowAllRecords(){
        ArrayList<Students> arrayList = new ArrayList<Students>();
        List<Students> studentses = new Select().from(Students.class).where().queryList();
        for (int i = 0; i < studentses.size(); i++) {
            arrayList.add(studentses.get(i));
        }

        return arrayList;
    }


}
