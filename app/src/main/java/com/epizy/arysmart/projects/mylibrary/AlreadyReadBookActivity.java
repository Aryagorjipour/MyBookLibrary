package com.epizy.arysmart.projects.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        RecyclerView rvAlreadyRead = findViewById(R.id.rvAlreadyRead);

        BookRVAdapter adapter = new BookRVAdapter(this,"alreadyRead");
        rvAlreadyRead.setAdapter(adapter);
        rvAlreadyRead.setLayoutManager(new LinearLayoutManager(this));


        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
    }

    //For go back to main activity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//flags for clear back stack and create new task
        startActivity(intent);
    }
}