package com.example.rahul.iithcalendar;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Events extends AppCompatActivity implements newEventDialog.onConfirm
{
    int year=0,month=0,dayOfMonth=0;
    String date;
    FloatingActionButton newEvent;
    ListView listView;
    ArrayAdapter<String> adapter;
    int noOfEvents=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        setValues();
        setup();
    }

    private void setup()
    {
        newEvent=(FloatingActionButton)findViewById(R.id.newEvent);
        listView=(ListView)findViewById(R.id.listView);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        SharedPreferences prefs=getApplication().getSharedPreferences("MY_PREFS",MODE_PRIVATE);
        noOfEvents=prefs.getInt(date,0);
        for(int i=0;i<noOfEvents;i++)
            adapter.add(prefs.getString(date+i,"NO EVENTS"));

        newEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle =new Bundle();
                bundle.putString("date",date);
                bundle.putInt("noOfEvents",noOfEvents);
                DialogFragment dialogBox=new newEventDialog();
                dialogBox.setArguments(bundle);
                dialogBox.show(getFragmentManager(),"event");
            }
        });

        if(noOfEvents==0)
            Toast.makeText(this,"No Events",Toast.LENGTH_SHORT).show();
    }

    private void setValues()
    {
        if(!(getIntent().getExtras()==null))
        {
            Bundle clickedDate=getIntent().getBundleExtra("clickedDate");
            year=clickedDate.getInt("year");
            month=clickedDate.getInt("month")+1;
            dayOfMonth=clickedDate.getInt("dayOfMonth");
            date=clickedDate.getString("date");
        }
    }

    @Override
    public void addEvent(Bundle bundle)
    {
        SharedPreferences prefs=getApplication().getSharedPreferences("MY_PREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString(bundle.getString("date")+bundle.getInt("noOfEvents"),bundle.getString("name"));
        editor.putInt(bundle.getString("date"),bundle.getInt("noOfEvents")+1);
        editor.apply();
        adapter.add(bundle.getString("name"));
        adapter.notifyDataSetChanged();
    }
}
