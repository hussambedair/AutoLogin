package com.example.android.autologin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.autologin.AlertDialogManager;
import com.example.android.autologin.Base.BaseActivity;
import com.example.android.autologin.R;
import com.example.android.autologin.SessionManager;

public class LoginActivity extends BaseActivity {

    EditText userName;
    EditText password;
    Button login;

    AlertDialogManager alertDialogManager = new AlertDialogManager();

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        login = findViewById(R.id.btnLogin);

        // Session Manager
        sessionManager = new SessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + sessionManager.isLoggedIn(),
                Toast.LENGTH_LONG).show();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get username, password from EditText
                String sUsername = userName.getText().toString();
                String sPassword = password.getText().toString();

                // Check if username, password is filled
                if(sUsername.trim().length() > 0 && sPassword.trim().length() > 0){

                    // For testing puspose username, password is checked with sample data
                    // username = test
                    // password = test
                    if(sUsername.equals("test") && sPassword.equals("test")) {

                        // Creating user login session
                        // For testing i am stroing name, email as follow

                        // Use user real data
                        sessionManager.createLoginSession("AndroidHive", "anroidhive@gmail.com");

                        // Staring HomeActivity
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();


                    } else {
                        // username / password doesn't match
                        showMessage("Error", "Login failed", "ok");

                    }


                } else {
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    showMessage("Error", "Login failed. Please enter username and password", "ok");
                }

            }

        });




    }



}
