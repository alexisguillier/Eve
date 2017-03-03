package fr.esiee.guillier_marret.eveapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    Button accountButton;
    Button addEventButton;
    Button logoutButton;

    Session session;
    User user;

    List<Event> events;
    ListView eventsContainer;

    List<Participation> participations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        session = new Session(getApplicationContext());
        session.checkLogin();
        user = session.getUser();

        accountButton = (Button) findViewById(R.id.accountBtn);
        addEventButton = (Button) findViewById(R.id.addEvent);

        if(user.getStatus() > 1){
            addEventButton.setVisibility(View.GONE);
        }

        logoutButton= (Button) findViewById(R.id.eventslogout);
        eventsContainer = (ListView)  findViewById(R.id.eventsContainer);
        events = Event.find(Event.class, "is_Active = ?", "1");

        if(events.size() > 0){
            EventArrayAdapter adapter = new EventArrayAdapter(this, events);
            eventsContainer.setAdapter(adapter);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "No scheduled events";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        accountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(myIntent);
                finish();
            }
        });


        addEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), NewEventActivity.class);
                startActivity(myIntent);
                finish();
            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                session.logoutUser();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}