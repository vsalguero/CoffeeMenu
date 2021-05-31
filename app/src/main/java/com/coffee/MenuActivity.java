package com.coffee;

import com.coffee.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vladimir Salguero on 25/09/2015.
 */
public class MenuActivity extends ListActivity {//implements AdapterView.OnItemLongClickListener {
    public static final String MENU_NAME = "menu_name";
    public static final String MENU_PRICE = "menu_price";
    public static final String MENU_ATARI = "menu_atari";

    private static final ArrayList<CafeMenu> LIST_MENU = new ArrayList<CafeMenu>(
            Arrays.asList(
                    new CafeMenu("Cafe Latte", 1.5),
                    new CafeMenu("Cafe Mocha", 2.5),
                    new CafeMenu("Cafe Americano", 3.0),
                    new CafeMenu("Cafe Listo", 0.25)

            ));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuArrayAdapter adapter = new MenuArrayAdapter(
                this,
                R.layout.menu_item,
                LIST_MENU);

        setListAdapter(adapter);

    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        CafeMenu item = (CafeMenu) getListAdapter().getItem(position);
        Intent intent = getIntent();
        intent.putExtra(MENU_NAME, item.getName());
        intent.putExtra(MENU_PRICE, String.valueOf(item.getPrice()));
        setResult(RESULT_OK, intent);
        finish();
    }


}
