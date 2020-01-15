package com.example.cm_project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> scoreList = new ArrayList<String[]>();

    static class ItemViewHolder {
        TextView s1;
        TextView s2;
        TextView s3;
        TextView s4;
        TextView s5;
        TextView s6;
        TextView s7;
        TextView s8;
        TextView c1;
        TextView c2;
        TextView c3;
        TextView c4;
        TextView c5;
        TextView c6;
        TextView c7;
        TextView c8;
        TextView Date;

    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.s1 = (TextView) row.findViewById(R.id.s1);
            viewHolder.s2 = (TextView) row.findViewById(R.id.s2);
            viewHolder.s3 = (TextView) row.findViewById(R.id.s3);
            viewHolder.s4 = (TextView) row.findViewById(R.id.s4);
            viewHolder.s5 = (TextView) row.findViewById(R.id.s5);
            viewHolder.s6 = (TextView) row.findViewById(R.id.s6);
            viewHolder.s7 = (TextView) row.findViewById(R.id.s7);
            /*viewHolder.s8 = (TextView) row.findViewById(R.id.s8);
            viewHolder.c1 = (TextView) row.findViewById(R.id.c1);
            viewHolder.c2 = (TextView) row.findViewById(R.id.c2);
            viewHolder.c3 = (TextView) row.findViewById(R.id.c3);
            viewHolder.c4 = (TextView) row.findViewById(R.id.c4);
            viewHolder.c5 = (TextView) row.findViewById(R.id.c5);
            viewHolder.c6 = (TextView) row.findViewById(R.id.c6);
            viewHolder.c7 = (TextView) row.findViewById(R.id.c7);
            viewHolder.c8 = (TextView) row.findViewById(R.id.c8);
            /*viewHolder.Date = (TextView) row.findViewById(R.id.date);*/

            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.s1.setText(stat[0]);
        viewHolder.s2.setText(stat[1]);
        viewHolder.s3.setText(stat[2]);
        viewHolder.s4.setText(stat[3]);
        viewHolder.s5.setText(stat[4]);
        viewHolder.s6.setText(stat[5]);
        viewHolder.s7.setText(stat[6]);
        /*viewHolder.s8.setText(stat[7]);
        viewHolder.c1.setText(stat[8]);
        viewHolder.c2.setText(stat[9]);
        viewHolder.c3.setText(stat[10]);
        viewHolder.c4.setText(stat[11]);
        viewHolder.c5.setText(stat[12]);
        viewHolder.c6.setText(stat[13]);
        viewHolder.c7.setText(stat[14]);
        viewHolder.c8.setText(stat[15]);
         viewHolder.Date.setText(stat[16]);*/
        return row;
    }
}
