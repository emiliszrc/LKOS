package com.example.lkos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import com.example.Controllers.NetController;
import com.example.Controllers.SharedPreferenceController;
import com.example.Models.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton;
    private TextView logo;
    private static final String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NetController AAA = new NetController();
                String Username = username.getText().toString();
                String Password = password.getText().toString();
                Log.d (TAG, "Yes: " + Username);
                Log.d (TAG, "Yes: " + Username);
                User B = new User (Username, Password);
                //SharedPreferenceController sharedPreferenceController = new SharedPreferenceController();
                try {
                    String A = AAA.getToken(B);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // sharedPreferenceController.saveToken(A);

                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
