package fr.esiee.guillier_marret.eveapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-i5 on 02/03/2017.
 */

public class EventArrayAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> events;
    private EventArrayAdapter adapter;
    Session session;
    User user;

    List<Participation> participations;

    public EventArrayAdapter(Context context, List<Event> objects) {
        super(context, 0, objects);
        this.context = context;
        this.events = objects;
        session = new Session(context);
        session.checkLogin();
        user = session.getUser();
        participations = Participation.find(Participation.class, "user = ?", String.valueOf(user.getId()));
        adapter = this;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Event property = events.get(position);
        boolean isJoined = false;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.events_layout, null);
        TextView eventTitle = (TextView) view.findViewById(R.id.eventTitle);
        TextView eventDate = (TextView) view.findViewById(R.id.eventDate);
        TextView eventPrice = (TextView) view.findViewById(R.id.eventPrice);
        Button eventJoin =  (Button) view.findViewById(R.id.eventJoin);

        eventTitle.setText(property.getName());
        eventDate.setText(property.getDate());
        eventPrice.setText(String.valueOf(property.getPrice()));

        for(Participation p : participations){
            if(p.getEvent().getId() == property.getId()){
                eventJoin.setText("Leave");
                setOnClick(eventJoin, p);
                isJoined = true;
            }
        }
        if(!isJoined){
            eventJoin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Participation p = new Participation(user,property);
                    p.save();
                    CharSequence text = "Registered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent myIntent = new Intent(context, EventsActivity.class);
                    context.startActivity(myIntent);
                }
            });
        }
        return view;
    }
    private void setOnClick(final Button btn, final Participation pt){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt.delete();
                CharSequence text = "Unregistered";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent myIntent = new Intent(context, EventsActivity.class);
                context.startActivity(myIntent);
            }
        });
    }
}
