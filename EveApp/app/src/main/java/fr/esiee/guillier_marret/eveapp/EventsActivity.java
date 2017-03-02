package fr.esiee.guillier_marret.eveapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventsActivity extends AppCompatActivity {

    Button accountButton;
    Button logoutButton;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        session = new Session(getApplicationContext());
        session.checkLogin();

        accountButton = (Button) findViewById(R.id.accountBtn);
        logoutButton= (Button) findViewById(R.id.eventslogout);


        accountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), AccountActivity.class);
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