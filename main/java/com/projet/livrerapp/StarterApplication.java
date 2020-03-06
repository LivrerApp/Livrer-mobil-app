package com.projet.livrerapp;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;


public class StarterApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("c458f582a52e3f5a0344a5911b649c16292726d0")
                .clientKey("63495ce88ebeba8e316678cebc84f0888ef378f3")
                .server("http://18.223.21.58:80/parse/")
                .build()
        );



       // ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

}
