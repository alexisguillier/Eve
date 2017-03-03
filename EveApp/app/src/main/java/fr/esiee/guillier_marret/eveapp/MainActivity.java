package fr.esiee.guillier_marret.eveapp;


import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.SugarRecord;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SyncStatusObserver;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    Button signupButton;
    EditText id;
    EditText pwd;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//Clear and recreate database
/*
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
*/

        session = new Session(getApplicationContext());

/*
        if(session.isLoggedIn() ){
            Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(myIntent);
            finish();
        }
*/

        submitButton = (Button) findViewById(R.id.submit);
        signupButton = (Button) findViewById(R.id.signup);
        id= (EditText) findViewById(R.id.idfield);
        pwd= (EditText) findViewById(R.id.pwdfield);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                if(id.getText().toString().trim().length() == 0 || pwd.getText().toString().trim().length() == 0){
                    text = "Fill all the fields";
                } else {
                    List<User> users = User.find(User.class, "email = ? and password = ?", id.getText().toString(), pwd.getText().toString());
                    if(users.size() == 1){
                        User u = users.get(0);
                        text = "Connected";
                        session.createSession(u.getId(), u.getFirstName(), u.getLastName());

                        Intent myIntent = new Intent(getApplicationContext(), EventsActivity.class);
                        startActivity(myIntent);
                        finish();

                    } else {
                        text = "Account not found";
                    }
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}