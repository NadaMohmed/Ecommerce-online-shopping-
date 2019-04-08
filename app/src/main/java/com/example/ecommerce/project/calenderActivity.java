package com.example.ecommerce.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;

public class calenderActivity extends AppCompatActivity {
    String date ;
    CalendarView calendarView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView = (CalendarView)findViewById(R.id.calendarView) ;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = (i1+1) + "/" + (i2) + "/" + i ;

                Intent intent =new Intent(calenderActivity.this,signup.class) ;
                intent.putExtra("date",date) ;
                startActivity(intent);
            }
        });


    }
}
