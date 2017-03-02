package fr.esiee.guillier_marret.eveapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-i5 on 02/03/2017.
 */

public class EventArrayAdapter  extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> events;

    public EventArrayAdapter(Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.events = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Event property = events.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.events_layout, null);

        ImageView eventImage = (ImageView) view.findViewById(R.id.eventImage);

        TextView eventTitle = (TextView) view.findViewById(R.id.eventTitle);
        TextView eventDate = (TextView) view.findViewById(R.id.eventDate);
        TextView eventPrice = (TextView) view.findViewById(R.id.eventPrice);
        Button eventJoin =  (Button) view.findViewById(R.id.eventJoin);

        eventTitle.setText(property.getName());
        eventDate.setText(property.getDate().toString());
        eventPrice.setText(String.valueOf(property.getPrice()));

        //get the image associated with this property
        //int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
        //image.setImageResource(imageID);

        return view;
    }
}
