package com.example.rk.beadrk;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class asyncActivity extends ActionBarActivity {
    ListView listView;
    private String[] nevek = {"Péter","Kristóf","Ádám", "Csaba", "Zoltán"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        listView= (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }
    class MyTask extends AsyncTask<Void,String,String>
    {
        ArrayAdapter<String> adapter;
        ProgressBar progressbar;
        int count;

        @Override
        protected void onPreExecute(){
            adapter=(ArrayAdapter<String>) listView.getAdapter();
            progressbar=(ProgressBar) findViewById(R.id.progress_bar);
            progressbar.setMax(5);
            progressbar.setProgress(0);
            progressbar.setVisibility(View.VISIBLE);
            count=0;

        }

        @Override
        protected String doInBackground(Void... params) {
            for (String Name : nevek)
            {
                publishProgress(Name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return (getResources().getString(R.string.did));
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressbar.setProgress(count);
        }

        @Override
        protected void onPostExecute(String  result) {
            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            progressbar.setVisibility(View.GONE );
        }
    }
}
