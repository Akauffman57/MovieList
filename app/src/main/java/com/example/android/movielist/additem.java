package com.example.android.movielist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.movielist.R;

public class additem extends AppCompatActivity {
    private EditText movietitle, moviecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.movielist.R.layout.activity_additem);
    }

    public void addmovie(View view){
        movietitle = findViewById(com.example.android.movielist.R.id.movietitle);
        moviecode = findViewById(R.id.moviecode);
        String newmovie = movietitle.getText().toString();
        String newcode = moviecode.getText().toString();
        Intent sendback = new Intent();
        sendback.putExtra("Title", newmovie);
        sendback.putExtra("Code", newcode);
        setResult(RESULT_OK, sendback);
        Toast.makeText(this, "Movie Added to List", Toast.LENGTH_SHORT).show();
    }


}


