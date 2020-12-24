package com.example.hle1_gearbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class itemAdapter extends ArrayAdapter<item> {

    public itemAdapter(Context context, int textViewResourceID, ArrayList<item> items) {
        super(context, textViewResourceID, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView itemDate = (TextView) convertView.findViewById(R.id.date);
        TextView itemMaker = (TextView) convertView.findViewById(R.id.maker);
        TextView itemDescription = (TextView) convertView.findViewById(R.id.description);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.price);
        TextView itemComment = (TextView) convertView.findViewById(R.id.comment);

        itemDate.setText(String.valueOf(item.getDate()));
        itemMaker.setText(item.getMaker());
        itemDescription.setText(item.getDescription());
        itemPrice.setText(String.valueOf(item.getPrice()));
        itemComment.setText(item.getComment());

        return convertView;
    }

}//end itemAdapter