package edu.bstu.iipo.a13ivt1.iipoperformance.LaboratoryFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo.a13ivt1.iipoperformance.DataBase.Students;
import edu.bstu.iipo.a13ivt1.iipoperformance.R;

public class AdapterLaboratory extends ArrayAdapter<Students> {

    public AdapterLaboratory(Context context, int textViewResourceId, ArrayList<Students> values){
        super(context,textViewResourceId,values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Students item = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_laboratory,null);
        }
        ((TextView) convertView.findViewById(R.id.name))
                .setText(item.getName());

        return convertView;
    }
}
