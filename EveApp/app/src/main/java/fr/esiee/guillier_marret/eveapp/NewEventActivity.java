package fr.esiee.guillier_marret.eveapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewEventActivity extends AppCompatActivity {

    Session session;
    User user;

    Button cancelButton;
    Button launchButton;

    EditText newEventTitle;
    EditText newEventDescription;
    EditText newEventDate;
    EditText newEventMaxTicket;
    EditText newEventPrice;
    EditText newEventPriceNonContributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        session = new Session(getApplicationContext());
        session.checkLogin();
        user = session.getUser();

        cancelButton = (Button) findViewById(R.id.cancelNew);
        launchButton = (Button) findViewById(R.id.launchNew);

        newEventTitle = (EditText) findViewById(R.id.newEventName);
        newEventDescription = (EditText) findViewById(R.id.newEventDescription);
        newEventDate = (EditText) findViewById(R.id.newEventDate);
        newEventMaxTicket = (EditText) findViewById(R.id.newEventMaxTickets);
        newEventPrice = (EditText) findViewById(R.id.newEventPrice);
        newEventPriceNonContributor = (EditText) findViewById(R.id.newEventPriceNC);

        if(user.getStatus() > 1){
            Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(myIntent);
            finish();
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });


        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                if(newEventTitle.toString().trim().length() == 0 || newEventMaxTicket.toString().trim().length() == 0 || newEventDate.toString().trim().length() == 0 || newEventPrice.toString().trim().length() == 0 || newEventPriceNonContributor.toString().trim().length() == 0) {
                    text = "Fill all the fields";
                } else{
                    Date parsed = new Date();
                    try {
                        SimpleDateFormat format =
                                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                        parsed = format.parse(newEventDate.toString());
                    }
                    catch(ParseException pe) {
                        throw new IllegalArgumentException();
                    }
                    int maxT = 0;
                    try {
                        maxT = Integer.parseInt(newEventMaxTicket.toString());
                    }
                    catch(Exception e) {
                        throw new NumberFormatException();
                    }
                    float price = 0;
                    try {
                        price = Float.parseFloat(newEventPrice.toString());
                    }
                    catch(Exception e) {
                        throw new NumberFormatException();
                    }
                    float priceNC = 0;
                    try {
                        priceNC = Float.parseFloat(newEventPriceNonContributor.toString());
                    }
                    catch(Exception e) {
                        throw new NumberFormatException();
                    }
                    Event e = new Event(newEventTitle.toString(), parsed, maxT, price, priceNC, newEventDescription.toString());
                    e.save();
                    text = "Event launched";
                    Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                    startActivity(myIntent);
                    finish();
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(myIntent);
                finish();

            }
        });

    }
}
