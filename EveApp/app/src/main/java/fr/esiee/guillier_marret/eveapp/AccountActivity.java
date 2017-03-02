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


public class AccountActivity extends AppCompatActivity {

    Button returnAccountButton;
    Button updateAccountButton;

    EditText firstNameAccount;
    EditText secondNameAccount;
    EditText mailAccount;
    EditText passwordAccount;
    EditText passwordConfirmAccount;
    Spinner accountStatus;

    Session session;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        session = new Session(getApplicationContext());
        session.checkLogin();
        user = session.getUser();
        returnAccountButton = (Button) findViewById(R.id.returnAccount);
        updateAccountButton = (Button) findViewById(R.id.editAccount);

        firstNameAccount = (EditText) findViewById(R.id.firstNameEdit);
        secondNameAccount = (EditText) findViewById(R.id.secondNameEdit);
        mailAccount = (EditText) findViewById(R.id.emailEdit);
        passwordAccount = (EditText) findViewById(R.id.passwordEdit);
        passwordConfirmAccount = (EditText) findViewById(R.id.passwordEditConfirm);
        accountStatus = (Spinner) findViewById(R.id.accountStatus);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SignActivity.STATUS_ARRAY); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountStatus.setAdapter(spinnerArrayAdapter);

        if(user != null){
            firstNameAccount.setText(user.getFirstName());
            secondNameAccount.setText(user.getLastName());
            mailAccount.setText(user.getEmail());
        }

        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;

                if(firstNameAccount.getText().toString().trim().length() == 0 || secondNameAccount.getText().toString().trim().length() == 0 || mailAccount.getText().toString().trim().length() == 0 || passwordAccount.getText().toString().trim().length() == 0 || passwordConfirmAccount.getText().toString().trim().length() == 0){
                    text = "Fill all the fields";
                } else{
                    if(passwordAccount.getText().toString().equals(passwordConfirmAccount.getText().toString())) {
                        user.setFirstName(firstNameAccount.getText().toString());
                        user.setLastName(secondNameAccount.getText().toString());
                        user.setEmail(mailAccount.getText().toString());
                        user.setPassword(passwordAccount.getText().toString());
                        user.setStatus(accountStatus.getSelectedItemPosition());
                        user.save();
                        text = "Account updated";
                        session.createSession(user.getId(), user.getFirstName(), user.getLastName());
                        Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                        startActivity(myIntent);
                        finish();
                    } else {
                        text = "Password fields are not equals";
                    }
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        returnAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }

}
