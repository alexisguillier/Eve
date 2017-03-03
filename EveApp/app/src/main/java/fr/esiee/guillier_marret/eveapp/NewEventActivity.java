package fr.esiee.guillier_marret.eveapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NewEventActivity extends AppCompatActivity {

    Session session;
    User user;

    NewEventActivity current;

    Button cancelButton;
    Button launchButton;

    EditText newEventTitle;
    EditText newEventDescription;
    EditText newEventLocation;
    EditText newEventDate;
    EditText newEventMaxTicket;
    EditText newEventPrice;
    EditText newEventPriceNonContributor;
    DatePickerFragment newFragment;
    int year, month, day;

    DatePicker datePicker;
    Calendar calendar;

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
        newEventLocation = (EditText) findViewById(R.id.newEventLocation);
        newEventDate = (EditText) findViewById(R.id.newEventDate);
        newEventMaxTicket = (EditText) findViewById(R.id.newEventMaxTickets);
        newEventPrice = (EditText) findViewById(R.id.newEventPrice);
        newEventPriceNonContributor = (EditText) findViewById(R.id.newEventPriceNC);

        current = this;

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        if(user.getStatus() > 1){
            Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(myIntent);
            finish();
        }

        newEventDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newFragment = new DatePickerFragment();
                newFragment.setOnDateSetListener(current);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });


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
                if(newEventTitle.getText().toString().trim().length() == 0 || newEventLocation.getText().toString().trim().length() == 0 || newEventMaxTicket.getText().toString().trim().length() == 0 || newEventDate.getText().toString().trim().length() == 0 || newEventPrice.getText().toString().trim().length() == 0 || newEventPriceNonContributor.getText().toString().trim().length() == 0) {
                    text = "Fill all the fields";
                } else {
                    int maxT = 0;
                    try {
                        maxT = Integer.parseInt(newEventMaxTicket.getText().toString());
                    }
                    catch(Exception e) {
                        maxT = 0;
                    }
                    float price = 0;
                    try {
                        price = Float.parseFloat(newEventPrice.getText().toString());
                    }
                    catch(Exception e) {
                        price = 0;
                    }
                    float priceNC = 0;
                    try {
                        priceNC = Float.parseFloat(newEventPriceNonContributor.getText().toString());
                    }
                    catch(Exception e) {
                        priceNC = 0;
                    }
                    Event e = new Event(newEventTitle.getText().toString(), newEventDate.getText().toString(), maxT, price, priceNC, newEventDescription.getText().toString());
                    e.save();
                    text = "Event launched";
                    Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                    startActivity(myIntent);
                    finish();
                }


                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                /*


                if(newEventTitle.toString().trim().length() == 0 || newEventMaxTicket.toString().trim().length() == 0 || newEventDate.toString().trim().length() == 0 || newEventPrice.toString().trim().length() == 0 || newEventPriceNonContributor.toString().trim().length() == 0) {
                    text = "Fill all the fields";
                } else{
                    System.out.println("date : empty");
                    /*
                    Date parsed = new Date();
                    try {
                        /*
                        SimpleDateFormat format =
                                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                        parsed = format.parse(newEventDate.toString());
                        System.out.println("date : empty"+parsed.toString());

                    }
                    catch(ParseException pe) {
                        throw new IllegalArgumentException();
                    }
                    */
                    /*
                    System.out.println("title : "+newEventTitle.toString());
                    System.out.println("description : "+newEventDescription.toString());*/
                    /*
                    Event e = new Event(newEventTitle.toString(), parsed, maxT, price, priceNC, newEventDescription.toString());
                    e.save();
                    text = "Event launched";
                    Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                    startActivity(myIntent);
                    finish();

                }
                /*
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(myIntent);
                finish();
                */

            }
        });
    }


    public void showDate(int year, int month, int day) {
        newEventDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
