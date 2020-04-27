package com.example.lkos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Controllers.NetController;
import com.example.Models.User;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton;
    private TextView logo;
    private static final String TAG = Login.class.getSimpleName();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private NetController netController = new NetController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        loginButton = (Button)findViewById(R.id.loginButton);
        pref = getSharedPreferences("APPDetails", Context.MODE_PRIVATE);
        editor = pref.edit();
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User AttemptToLogin = new User (username.getText().toString(), password.getText().toString());
                try {
                    String A = netController.getToken(AttemptToLogin);
                    saveToken(A);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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
    public void saveToken (String token){
            editor.putString("token",token);
        editor.commit();
    }
    public String returnToken(){
        String token = pref.getString("token", null);
        if (token ==null)
            return "This bitch empty";
        return token;
    }
}
