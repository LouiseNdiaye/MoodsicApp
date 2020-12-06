package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.database.myDatabase;
import com.android.database.myPlaylistAdapter;
import com.android.model.myPlaylist;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    String date;
    TextView test;
    ImageView calendar, arrowLeft;
    RecyclerView myPlaylistList;
    com.android.database.myPlaylistAdapter myPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent i = getIntent();
        date = (String) i.getSerializableExtra("date");
        test = findViewById(R.id.test);
        test.setText(date);

        myPlaylistList = findViewById(R.id.RecyclerView);
        myPlaylistList.hasFixedSize();
        myPlaylistList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<myPlaylist> playlists = (ArrayList<myPlaylist>) myDatabase.getDatabase(getApplicationContext()).historyDAO().getAllHistory();

        myPlaylistAdapter = new myPlaylistAdapter(playlists, date);
        myPlaylistAdapter.getFilter().filter(date);
        myPlaylistList.setAdapter(myPlaylistAdapter);

        calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        arrowLeft = findViewById(R.id.arrow);
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });


    }

}