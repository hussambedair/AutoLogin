package com.example.android.autologin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.autologin.AlertDialogManager;
import com.example.android.autologin.R;
import com.example.android.autologin.SessionManager;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    TextView mName;
    TextView mEmail;
    Button logout;

    // Alert Dialog Manager
    AlertDialogManager alertDialogManager = new AlertDialogManager();

    // Session Manager Class
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mName = findViewById(R.id.lblName);
        mEmail = findViewById(R.id.lblEmail);
        logout = findViewById(R.id.btnLogout);


        sessionManager = new SessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + sessionManager.isLoggedIn(),
                Toast.LENGTH_LONG).show();

        sessionManager.checkLogin();

        // get user data from session
        HashMap<String, String> user = sessionManager.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

        // displaying user data
        mName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        mEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                sessionManager.logoutUser();
            }
        });







    }

}
