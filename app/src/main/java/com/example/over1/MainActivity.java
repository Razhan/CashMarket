package com.example.over1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button button, button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1M);
        button2 = (Button) findViewById(R.id.button2M);
        button3 = (Button) findViewById(R.id.button3M);
        button4 = (Button) findViewById(R.id.button4M);
        //button5 = (Button) findViewById(R.id.button5M);


        button.setOnClickListener(new ButtonListener());
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());
        button3.setOnClickListener(new ButtonListener());
        button4.setOnClickListener(new ButtonListener());
        //button5.setOnClickListener(new ButtonListener());

    }

    class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();

            switch (arg0.getId())
            {
                case R.id.button:
                    intent.setClass(MainActivity.this, Test.class);
                    break;
                case R.id.button1M:
                    intent.putExtra("From",false);
                    intent.putExtra("FileName","");
                    intent.setClass(MainActivity.this, XYChartBuilder.class);
                    break;
                case R.id.button2M:
                    intent.setClass(MainActivity.this, FileChooser.class);
                    break;
                case R.id.button3M:
                    intent.setClass(MainActivity.this, Rules_webview.class);
                    break;
                case R.id.button4M:
                intent.setClass(MainActivity.this, Help.class);
                break;
//                case R.id.button5M:
//                    intent.setClass(MainActivity.this, Test.class);
//                    break;
            }
            MainActivity.this.startActivity(intent);
        }
    }
}
