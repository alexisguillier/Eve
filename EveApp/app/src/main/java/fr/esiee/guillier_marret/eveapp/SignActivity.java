package fr.esiee.guillier_marret.eveapp;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SignActivity extends AppCompatActivity {

    public static final String[] STATUS_ARRAY = {"Administrator", "Publisher", "User"};

    Button returnButton;
    Button signButton;
    EditText firstName;
    EditText secondName;
    EditText mail;
    EditText password;
    Spinner status;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        session = new Session(getApplicationContext());
        returnButton = (Button) findViewById(R.id.returnBtn);
        signButton = (Button) findViewById(R.id.sign);
        firstName = (EditText) findViewById(R.id.firstName);
        secondName = (EditText) findViewById(R.id.secondName);
        mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        status = (Spinner) findViewById(R.id.spinnerStatus);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, STATUS_ARRAY); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(spinnerArrayAdapter);

        signButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                if(firstName.getText().toString().trim().length() == 0 || secondName.getText().toString().trim().length() == 0 || mail.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0) {
                    text = "Fill all the fields";
                } else{
                    List<User> users = User.find(User.class, "email = ?", mail.getText().toString());
                    if(users.size() > 0){
                        text = "Mail already used";
                    } else{
                        User u = new User(firstName.getText().toString(), secondName.getText().toString(), false, mail.getText().toString(), password.getText().toString(), status.getSelectedItemPosition());
                        u.save();
                        text = "Account created";
                        session.createSession(u.getId(), u.getFirstName(), u.getLastName());
                        Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}