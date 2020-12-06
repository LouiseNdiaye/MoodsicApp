package com.android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

public class CalendarActivity extends AppCompatActivity {

    CalendarView mCalendarView;
    ImageView arrowLeft;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month +1;
                if (dayOfMonth < 10) {
                    String day = String.format("%02d", dayOfMonth);
                    date = day + "/" + month + "/" + year ;
                } else {
                    date = dayOfMonth + "/" + month + "/" + year ;
                }
                Log.d("azerty", date);
                Intent intent = new Intent(CalendarActivity.this, TestActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

        arrowLeft = findViewById(R.id.arrow);
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }


}