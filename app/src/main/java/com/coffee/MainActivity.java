package com.coffee;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

import com.coffee.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static double PRICE_COFFEE = 1.25;
    public int mQuantity = 0;
    public double mPrice;

    public double mSelectedItemPrice;
    static final String ARG_QUANTITY = "arg_quantity";
    static final String ARG_PRICE = "arg_price";

    static final int REQUEST_CODE_MENU = 1;  // The request code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mQuantity = savedInstanceState.getInt(ARG_QUANTITY);
            mPrice = savedInstanceState.getDouble(ARG_PRICE);
        }
        setContentView(R.layout.activity_main);

    }

    protected void onStart() {
        super.onStart();

        displayQuantity(mQuantity);
        //displayPrice(mPrice);

        addMessage();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_QUANTITY, mQuantity);
        outState.putDouble(ARG_PRICE, mPrice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void increment(View view) {
        mQuantity++;
        displayQuantity(mQuantity);
    }

    public void decrement(View view) {
        if (mQuantity > 0) {
            mQuantity--;
        }
        displayQuantity(mQuantity);
    }

    public void order(View view) {
        mPrice = mSelectedItemPrice * mQuantity;
        displayPrice(mPrice);
        addMessage();
    }


    void displayQuantity(int quantity) {
        TextView view = (TextView) findViewById(R.id.quantity);
        view.setText("" + quantity);
    }

    void displayPrice(double price) {
        TextView view = (TextView) findViewById(R.id.total);
        view.setText(getResources().getString(R.string.total) + ":"
                + NumberFormat.getCurrencyInstance(Locale.US).format(price));
    }

    void addMessage() {
        TextView view = (TextView) findViewById(R.id.total);
        String message = view.getText().toString();

        SharedPreferences shared_pref = PreferenceManager.getDefaultSharedPreferences(this);
        String pref_like_val = shared_pref.getString(
                SettingsActivity.KEY_PREF_LIKE, getResources().getString(R.string.like));
        message += " \nExcelente, Lo que mas te gusta de nuestro café es su: ";
        switch (pref_like_val) {
            case "1":
                message += " Olor";
                break;
            case "2":
                message += " Sabor";
                break;
            case "3":
                message += " Temperatura";
                break;

        }

        view.setText(message);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_menu) {
            Intent intent = new Intent(this, MenuActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE_MENU);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_MENU:
                if (data != null) {
                    mSelectedItemPrice = Double.valueOf(
                            data.getStringExtra(MenuActivity.MENU_PRICE)
                    );
                    mPrice = mSelectedItemPrice * mQuantity;
                    displayPrice(mPrice);
                }
                break;
            default:
                break;
        }

    }
}


