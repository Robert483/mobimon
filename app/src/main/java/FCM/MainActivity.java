package FCM;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alarmnotification.mobimon.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /*//Creating Views
    private Button button;
    private EditText editTextEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if the device is registered
        if(isRegistered()){
            startService(new Intent(this, NotificationListener.class));
        }

        //Initializing views
        button = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        //Attaching an onclicklistener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if the device is not already registered
                if (!isRegistered()) {
                    //registering the device
                    registerDevice();
                } else {
                    //if the device is already registered
                    //displaying a toast
                    Toast.makeText(MainActivity.this, "Already registered...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isRegistered() {
        //Getting shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

        //Getting the value from shared preferences
        //The second parameter is the default value
        //if there is no value in sharedprference then it will return false
        //that means the device is not registered
        return sharedPreferences.getBoolean(Constants.REGISTERED, false);
    }

    private void registerDevice() {
        //Creating a firebase object
        Firebase firebase = new Firebase(Constants.FIREBASE_APP);

        //Pushing a new element to firebase it will automatically create a unique id
        Firebase newFirebase = firebase.push();

        //Creating a map to store name value pair
        Map<String, String> val = new HashMap<>();

        //pushing msg = none in the map
        val.put("msg", "none");

        //saving the map to firebase
        newFirebase.setValue(val);

        //Getting the unique id generated at firebase
        String uniqueId = newFirebase.getKey();
        editTextEmail.setText(uniqueId);
        Log.d("Son","firebase id : " + uniqueId);
        //Finally we need to implement a method to store this unique id to our server
        //sendIdToServer(uniqueId);

        //Opening the shared preferences editor to save values
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Storing the unique id
        editor.putString(Constants.UNIQUE_ID, uniqueId);

        //Saving the boolean as true i.e. the device is registered
        editor.putBoolean(Constants.REGISTERED, true);

        //Applying the changes on sharedpreferences
        editor.apply();

        //Starting our listener service once the device is registered
        startService(new Intent(getBaseContext(), NotificationListener.class));
    }*/



}
