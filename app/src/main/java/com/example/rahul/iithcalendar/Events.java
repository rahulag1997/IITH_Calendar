package com.example.rahul.iithcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Events extends AppCompatActivity
{
    int year=0,month=0,dayOfMonth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        if(!(getIntent().getExtras()==null))
        {
            Bundle clickedDate=getIntent().getBundleExtra("clickedDate");
            year=clickedDate.getInt("year");
            month=clickedDate.getInt("month")+1;
            dayOfMonth=clickedDate.getInt("dayOfMonth");
        }
        Toast.makeText(this,"No events for "+dayOfMonth+"/"+month+"/"+year,Toast.LENGTH_SHORT).show();
    }
}
