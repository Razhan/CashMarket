package com.example.cashmarket;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cashmarket.R;

public class NewLogin extends Activity {

    EditText userET;
    EditText passwordET;
    Button logBT;

    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        userET = (EditText) findViewById(R.id.etUserName);
        passwordET = (EditText) findViewById(R.id.etPass);
        logBT = (Button) findViewById(R.id.btnSingIn);


        logBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                user = userET.getText().toString();
                password = passwordET.getText().toString();

                if (!((user.equals("admin")) && (password.equals("admin")))) {
                    Toast.makeText(NewLogin.this, "Password error, please re-enter",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewLogin.this, "Successful login",
                            Toast.LENGTH_SHORT).show();
                    // 跳转到另一个Activity
                    // do something

                    Intent intent = new Intent();
                    intent.setClass(NewLogin.this, Home.class);
                    NewLogin.this.startActivity(intent);
                    NewLogin.this.finish();
                }
            }
        });

    }



}
