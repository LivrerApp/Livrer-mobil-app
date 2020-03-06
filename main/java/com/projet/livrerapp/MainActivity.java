package com.projet.livrerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {
    public void redirectActivity() {

        if (ParseUser.getCurrentUser().getString("clientOuLivreur").equals("client")) {

            Intent intent = new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(getApplicationContext(), ViewRequestsActivity.class);
            startActivity(intent);
        }
    }

    public void Commencer(View view) {

        Switch userTypeSwitch = (Switch) findViewById(R.id.switchUser);

        Log.i("Switch value", String.valueOf(userTypeSwitch.isChecked()));

        String userType = "client";

        if (userTypeSwitch.isChecked()) {

            userType = "livreur";

        }

        ParseUser.getCurrentUser().put("clientOuLivreur", userType);

        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                redirectActivity();

            }
        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();



        if (ParseUser.getCurrentUser() == null) {

            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (e == null) {

                        Log.i("Info", "Connexion Réussie");

                    } else {

                        Log.i("Info", "Connexion Non Réussie");

                    }


                }
            });

        } else {

            if (ParseUser.getCurrentUser().get("clientOuLivreur") != null) {

                Log.i("Info", "Redirecting as " + ParseUser.getCurrentUser().get("clientOuLivreur"));

                redirectActivity();

            }


        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }



}

