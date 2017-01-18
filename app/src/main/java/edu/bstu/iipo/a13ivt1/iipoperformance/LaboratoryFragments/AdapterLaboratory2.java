package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students;
import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class AdapterLaboratory2 extends ArrayAdapter<Students> {

    public AdapterLaboratory2(Context context, int resource, ArrayList<Students> values) {
        super(context, resource,values);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Students item = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_laboratory,null);
        }
        ((TextView) convertView.findViewById(R.id.name))
                .setText(item.getName());
        ((TextView) convertView.findViewById(R.id.surname))
                .setText(item.getSurname());

        return convertView;
    }
}
