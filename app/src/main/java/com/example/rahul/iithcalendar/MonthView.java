package com.example.rahul.iithcalendar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

@TargetApi(Build.VERSION_CODES.M)
public class MonthView extends AppCompatActivity
{
    CalendarView calendarView;
    Bundle clickedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        setup();
        initializeCalendar();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                Intent eventLauncher= new Intent(MonthView.this,Events.class);
                clickedDate=new Bundle();
                clickedDate.putInt("year",year);
                clickedDate.putInt("month",month);
                clickedDate.putInt("dayOfMonth",dayOfMonth);
                clickedDate.putString("date",""+dayOfMonth+""+month+""+year);
                eventLauncher.putExtra("clickedDate",clickedDate);
                startActivity(eventLauncher);
            }
        });
    }

    private void initializeCalendar()
    {
        //add initialization code
    }

    private void setup()
    {
        calendarView=(CalendarView)findViewById(R.id.CVMonth);
    }

}
