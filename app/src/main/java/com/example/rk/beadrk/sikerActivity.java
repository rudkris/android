package com.example.rk.beadrk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class sikerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siker);
        Intent intent = getIntent();
        String nev = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(getResources().getString(R.string.grat)+" " + nev + getResources().getString(R.string.youdidit));
        setContentView(textView);
    }
}
