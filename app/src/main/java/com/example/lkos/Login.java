package com.example.lkos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.example.Controllers.NetController;
import com.example.Models.User;
import java.io.IOException;

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
                NetController yes = new NetController();
                User A = new User(username.getText().toString(),password.getText().toString());
                try {
                    yes.getToken(A);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
