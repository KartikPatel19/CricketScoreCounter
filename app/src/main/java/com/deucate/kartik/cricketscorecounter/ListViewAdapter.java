package com.deucate.kartik.cricketscorecounter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class ListViewAdapter extends ArrayAdapter<ListViewGS> {


    ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ListViewGS> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.main_list_view, parent, false);
        }

        TextView team1 = (TextView) convertView.findViewById(R.id.listTeam1);
        TextView team2 = (TextView) convertView.findViewById(R.id.listTeam2);
        TextView over = (TextView) convertView.findViewById(R.id.mainListOver);
        TextView date = (TextView) convertView.findViewById(R.id.mainListDate);
        TextView complate = (TextView) convertView.findViewById(R.id.mainListComplate);

        ListViewGS listViewGS = getItem(position);

        assert listViewGS != null;
        team1.setText(listViewGS.getTeam1());
        team2.setText(listViewGS.getTeam2());
        over.setText(listViewGS.getOver()+"");
        date.setText(listViewGS.getDate());
        complate.setText("Comp");


        return convertView;
    }
}
