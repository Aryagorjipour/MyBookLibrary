package com.epizy.arysmart.projects.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_list);

        RecyclerView rvCurrentlyReadingList = findViewById(R.id.rvCurrentlyReadingList);

        BookRVAdapter adapter = new BookRVAdapter(this,"currentlyReading");

        rvCurrentlyReadingList.setAdapter(adapter);
        rvCurrentlyReadingList.setLayoutManager(new LinearLayoutManager(this));


        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//flags for clear back stack and create new task
        startActivity(intent);
    }
}