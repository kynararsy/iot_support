package com.example.iot_asma_support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        callFragment(new LoginFragment(auth));
        tag = "Login";

        Button button = findViewById(R.id.btnSwitch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.setText(tag);

                if(tag == "Login"){
                    callFragment(new RegisterFragment(auth));

                    tag = "SignUp";
                }
                else{
                    callFragment(new LoginFragment(auth));
                    tag = "Login";
                }
            }
        });
    }

    private void callFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.form_container, fragment);
        ft.commit();
    }
}