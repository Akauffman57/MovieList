package com.example.android.movielist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.movielist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String[] movies = {"JAWS", "Airplane!", "Raiders of the Lost Ark", "Ghostbusters", "Groundhog Day", "Dumb and Dumber"};
    public static final String[] IMBDcode = {"tt0073195", "tt0080339", "tt0082971", "tt0087332", "tt0107048", "tt0109686"};
    public static ListView listView;
    public static ArrayList<String> movieTitles, movieIMBD;
    public static final String preurl = "https://www.imdb.com/title/";
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.movielist.R.layout.activity_main);
        listView = findViewById(com.example.android.movielist.R.id.list);
        movieTitles = new ArrayList<String>();
        movieIMBD = new ArrayList<String>();

        for (int i = 0; i < movies.length; i++) {
            movieTitles.add(movies[i]);
            movieIMBD.add(IMBDcode[i]);
        }
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, com.example.android.movielist.R.layout.list_item_view, movieTitles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        url = preurl + IMBDcode[i];
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);
                    }
                }
        );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                movieTitles.remove(pos);
                movieIMBD.remove(pos);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Movie Deleted", Toast.LENGTH_SHORT).show();
                listView.setAdapter(adapter);
                return true;
            }
        });
    }

    public void add(View view) {
        Intent intent = new Intent(this, additem.class);
        startActivityForResult(intent, 1);
    }

@Override
    public void onActivityResult(int requestcode, int resultcode,Intent sendback) {
        if (resultcode == RESULT_OK) {

                movieTitles.add(sendback.getStringExtra("Title"));
                movieIMBD.add(sendback.getStringExtra("Code"));
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item_view, movieTitles);
                listView.setAdapter(adapter);

        }
    }
    @Override
    public void onStop(){
        super.onStop();
        SharedPreferences p = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=p.edit();
        for (int i = 0; i<movieTitles.size();i++){
            edit.putString("Title"+i+"", movieTitles.get(i));
            edit.putString("Code"+i+"",movieIMBD.get(i));
        }
        edit.putInt("Size",movieTitles.size());
        edit.apply();
    }



}





