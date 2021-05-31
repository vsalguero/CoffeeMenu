package com.coffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.coffee.R;

/**
 * Created by Vladimir Salguero on 25/09/2015.
 */
public class MenuArrayAdapter extends ArrayAdapter<CafeMenu> {

    private ArrayList<CafeMenu> mItems;

    public MenuArrayAdapter(Context context, int resourceId, ArrayList<CafeMenu> items) {
        super(context, resourceId, items);
        mItems = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.menu_item, null);
        }
        CafeMenu menu = mItems.get(position);
        TextView name = (TextView) v.findViewById(R.id.menu_item_name);
        name.setText(menu.getName());
        TextView price = (TextView) v.findViewById(R.id.menu_item_price);
        price.setText(NumberFormat.getCurrencyInstance(Locale.US).format(menu.getPrice()));
        return v;

    }

    public CafeMenu getItem(int position) {
        return mItems.get(position);
    }
}
