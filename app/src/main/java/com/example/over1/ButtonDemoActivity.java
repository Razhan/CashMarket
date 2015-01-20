package com.example.over1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ButtonDemoActivity extends Activity {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_demo);

        sharedPreferences = this.getSharedPreferences("rate",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        final TextView name1 = (TextView)findViewById(R.id.R1N);
        final TextView success1 = (TextView)findViewById(R.id.R1C );
        final TextView sum1 = (TextView)findViewById(R.id.R1S);
        final TextView rate1 = (TextView)findViewById(R.id.R1R);

        String Name1 = sharedPreferences.getString("Rule1", "RuleX");
        Integer Success1 = sharedPreferences.getInt("R1-success", 0);
        Integer Sum1 = sharedPreferences.getInt("R1-sum", 0);
        float Rate1 = 3 / 7 * 100;

        name1.setText(Name1);
        success1.setText(String.valueOf(Success1));
        sum1.setText(String.valueOf(Sum1));
        rate1.setText(String.valueOf(Rate1));
    }

    public void save(){
        String name = "";
        Integer age = 1;
        editor.putInt("age", age);
        editor.putString("name", name);

        editor.commit();
    }
}
