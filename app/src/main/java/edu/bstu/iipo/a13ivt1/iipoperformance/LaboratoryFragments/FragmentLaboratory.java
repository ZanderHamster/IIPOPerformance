package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class FragmentLaboratory extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SwipeRefreshLayout swipeLayout;
    private OnFragmentInteractionListener mListener;
    public ArrayAdapter<TestStudent> listViewAdapter;
    public AdapterLaboratory listViewAdapter2;
    private MenuInflater menuInflater;


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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laboratory, container, false);
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

//        for (int i = 0; i < 19; i++) {
//            TestStudent students = new TestStudent();
//            students.setName("Name "+(i+1));
//            students.setSurname("Surname "+(i+1));
//            students.save();
//        }


        //final ArrayAdapter<TestStudent> listViewAdapter = new AdapterLaboratory(getActivity(),R.layout.list_laboratory,ShowAllRecords());
        listViewAdapter = new AdapterLaboratory(getActivity(),R.layout.list_laboratory,ShowAllRecords());

        final ListView listView=(ListView) view.findViewById(R.id.list_laboratiry);
        listView.setAdapter(listViewAdapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final List<TestStudent> s = new Select().from(TestStudent.class).where(TestStudent_Table.id.is(((TestStudent) parent.getAdapter().getItem(position)).getId())).queryList();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = inflater.inflate(R.layout.alert_dialog_edit_name,null);
                builder.setView(dialogView);

                final EditText edName = (EditText) dialogView.findViewById(R.id.editTextName);
                final EditText edSurname = (EditText) dialogView.findViewById(R.id.editTextSurname);
                builder.setTitle(R.string.edit)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                s.get(0).setName(edName.getText().toString());
                                s.get(0).setSurname(edSurname.getText().toString());
                                s.get(0).save();
                            }
                        });

                edName.setText(s.get(0).getName());
                edSurname.setText(s.get(0).getSurname());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

//                view.invalidate();
//                listViewAdapter.notifyDataSetChanged();// это не работает
                return true;
            }
        });
        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(getContext(),"edit",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(getContext(),"delete",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
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

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(),"Обновилось",Toast.LENGTH_SHORT).show();
        listViewAdapter.notifyDataSetChanged();
        swipeLayout.setRefreshing(false);
    }

    public MenuInflater getMenuInflater() {
        return menuInflater;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getMenuInflater().inflate(R.menu.navigation,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add) Toast.makeText(getContext(),"23",Toast.LENGTH_SHORT).show();
//        switch (item.getItemId()){
//            case R.id.action_add:
//                Toast.makeText(getContext(),"23",Toast.LENGTH_SHORT).show();
//                break;
//        }
        return  super.onOptionsItemSelected(item);
    }
}
