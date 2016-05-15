package com.example.cashmarket;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Success extends Activity {

    private SharedPreferences sharedPreferences;
    private Bundle bundle;
    private Intent intent;
    private String filename;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        String ans = "";

        intent = this.getIntent();
        bundle = intent.getExtras();
        filename = bundle.getString("FileName");
        filename = getFileNameNoEx(filename);

        sharedPreferences = this.getSharedPreferences(filename, MODE_PRIVATE);

        TextView begin = (TextView)findViewById(R.id.begin);
        begin.setText("存档:  " +  filename + "统计");



//        final TextView name1 = (TextView)findViewById(R.id.R1N);
//        final TextView success1 = (TextView)findViewById(R.id.R1C );
//        final TextView fail1 = (TextView)findViewById(R.id.R1F);
//        final TextView sum1 = (TextView)findViewById(R.id.R1S);
//        final TextView rate1 = (TextView)findViewById(R.id.R1R);
//
//        Integer Name1 = sharedPreferences.getInt("Rule,1", -1);
//        Integer Success1 = sharedPreferences.getInt("success,1", 0);
//        Integer Fail1 = sharedPreferences.getInt("fail,1", 0);
//        Integer Sum1 = Success1 + Fail1;
//        float Rate1 = (float)Success1 / (float)Sum1 * 100;
//        ans = String.format("%.2f",Rate1);
//
//        name1.setText("-2+2-1+1");
//        success1.setText(String.valueOf(Success1));
//        fail1.setText(String.valueOf(Fail1));
//        sum1.setText(String.valueOf(Sum1));
//        rate1.setText(ans + "%");
//
//        final TextView name2 = (TextView)findViewById(R.id.R2N);
//        final TextView success2 = (TextView)findViewById(R.id.R2C );
//        final TextView fail2 = (TextView)findViewById(R.id.R2F);
//        final TextView sum2 = (TextView)findViewById(R.id.R2S);
//        final TextView rate2 = (TextView)findViewById(R.id.R2R);
//
//        Integer Name2 = sharedPreferences.getInt("Rule,2", -1);
//        Integer Success2 = sharedPreferences.getInt("success,2", 0);
//        Integer Fail2 = sharedPreferences.getInt("fail,2", 0);
//        Integer Sum2 = Success2 + Fail2;
//        float Rate2 = (float)Success2 / (float)Sum2 * 100;
//        ans = String.format("%.2f",Rate2);
//
//        name2.setText("-2+2-1+2-1");
//        success2.setText(String.valueOf(Success2));
//        fail2.setText(String.valueOf(Fail2));
//        sum2.setText(String.valueOf(Sum2));
//        rate2.setText(ans + "%");

//        final TextView name3 = (TextView)findViewById(R.id.R3N);
//        final TextView success3 = (TextView)findViewById(R.id.R3C );
//        final TextView fail3 = (TextView)findViewById(R.id.R3F);
//        final TextView sum3 = (TextView)findViewById(R.id.R3S);
//        final TextView rate3 = (TextView)findViewById(R.id.R3R);
//
//        Integer Name3 = sharedPreferences.getInt("Rule,3", -1);
//        Integer Success3 = sharedPreferences.getInt("success,3", 0);
//        Integer Fail3 = sharedPreferences.getInt("fail,3", 0);
//        Integer Sum3 = Success3 + Fail3;
//        float Rate3 = (float)Success3 / (float)Sum3 * 100;
//        ans = String.format("%.2f",Rate3);
//
//        name3.setText("-3+2-1+1");
//        success3.setText(String.valueOf(Success3));
//        fail3.setText(String.valueOf(Fail3));
//        sum3.setText(String.valueOf(Sum3));
//        rate3.setText(ans + "%");
//
//
//        final TextView name4 = (TextView)findViewById(R.id.R4N);
//        final TextView success4 = (TextView)findViewById(R.id.R4C );
//        final TextView fail4 = (TextView)findViewById(R.id.R4F);
//        final TextView sum4 = (TextView)findViewById(R.id.R4S);
//        final TextView rate4 = (TextView)findViewById(R.id.R4R);
//
//        Integer Name4 = sharedPreferences.getInt("Rule,4", -1);
//        Integer Success4 = sharedPreferences.getInt("success,4", 0);
//        Integer Fail4 = sharedPreferences.getInt("fail,4", 0);
//        Integer Sum4 = Success4 + Fail4;
//        float Rate4 = (float)Success4 / (float)Sum4 * 100;
//        ans = String.format("%.2f",Rate4);
//
//        name4.setText("+1-1+2-1+1");
//        success4.setText(String.valueOf(Success4));
//        fail4.setText(String.valueOf(Fail4));
//        sum4.setText(String.valueOf(Sum4));
//        rate4.setText(ans + "%");
//
//        final TextView name5 = (TextView)findViewById(R.id.R5N);
//        final TextView success5 = (TextView)findViewById(R.id.R5C );
//        final TextView fail5 = (TextView)findViewById(R.id.R5F);
//        final TextView sum5 = (TextView)findViewById(R.id.R5S);
//        final TextView rate5 = (TextView)findViewById(R.id.R5R);
//
//        Integer Name5 = sharedPreferences.getInt("Rule,5", -1);
//        Integer Success5 = sharedPreferences.getInt("success,5", 0);
//        Integer Fail5 = sharedPreferences.getInt("fail,5", 0);
//        Integer Sum5 = Success5 + Fail5;
//        float Rate5 = (float)Success5 / (float)Sum5 * 100;
//        ans = String.format("%.2f",Rate5);
//
//        name5.setText("+1-1+2-1+1-1+1");
//        success5.setText(String.valueOf(Success5));
//        fail5.setText(String.valueOf(Fail5));
//        sum5.setText(String.valueOf(Sum5));
//        rate5.setText(ans + "%");
//
//        final TextView name6 = (TextView)findViewById(R.id.R6N);
//        final TextView success6 = (TextView)findViewById(R.id.R6C );
//        final TextView fail6 = (TextView)findViewById(R.id.R6F);
//        final TextView sum6 = (TextView)findViewById(R.id.R6S);
//        final TextView rate6 = (TextView)findViewById(R.id.R6R);
//
//        Integer Name6 = sharedPreferences.getInt("Rule,6", -1);
//        Integer Success6 = sharedPreferences.getInt("success,6", 0);
//        Integer Fail6 = sharedPreferences.getInt("fail,6", 0);
//        Integer Sum6 = Success6 + Fail6;
//        float Rate6 = (float)Success6 / (float)Sum6 * 100;
//        ans = String.format("%.2f",Rate6);
//
//        name6.setText("+1-1+2-1+2-1+1");
//        success6.setText(String.valueOf(Success6));
//        fail6.setText(String.valueOf(Fail6));
//        sum6.setText(String.valueOf(Sum6));
//        rate6.setText(ans + "%");
//
//        final TextView name7 = (TextView)findViewById(R.id.R7N);
//        final TextView success7 = (TextView)findViewById(R.id.R7C );
//        final TextView fail7 = (TextView)findViewById(R.id.R7F);
//        final TextView sum7 = (TextView)findViewById(R.id.R7S);
//        final TextView rate7 = (TextView)findViewById(R.id.R7R);
//
//        Integer Name7 = sharedPreferences.getInt("Rule,7", -1);
//        Integer Success7 = sharedPreferences.getInt("success,7", 0);
//        Integer Fail7 = sharedPreferences.getInt("fail,7", 0);
//        Integer Sum7 = Success7 + Fail7;
//        float Rate7 = (float)Success7 / (float)Sum7 * 100;
//        ans = String.format("%.2f",Rate7);
//
//        name7.setText("-3+1-1+1");
//        success7.setText(String.valueOf(Success7));
//        fail7.setText(String.valueOf(Fail7));
//        sum7.setText(String.valueOf(Sum7));
//        rate7.setText(ans + "%");
//
        final TextView name8 = (TextView)findViewById(R.id.R8N);
        final TextView success8 = (TextView)findViewById(R.id.R8C );
        final TextView fail8 = (TextView)findViewById(R.id.R8F);
        final TextView sum8 = (TextView)findViewById(R.id.R8S);
        final TextView rate8 = (TextView)findViewById(R.id.R8R);

        Integer Name8 = sharedPreferences.getInt("Rule,8", -1);
        Integer Success8 = sharedPreferences.getInt("success,8", 0);
        Integer Fail8 = sharedPreferences.getInt("fail,8", 0);
        Integer Sum8 = Success8 + Fail8;
        float Rate8 = (float)Success8 / (float)Sum8 * 100;
        ans = String.format("%.2f",Rate8);

        name8.setText("+3-1+1-1");
        success8.setText(String.valueOf(Success8));
        fail8.setText(String.valueOf(Fail8));
        sum8.setText(String.valueOf(Sum8));
        rate8.setText(ans + "%");

        final TextView name9 = (TextView)findViewById(R.id.R9N);
        final TextView success9 = (TextView)findViewById(R.id.R9C );
        final TextView fail9 = (TextView)findViewById(R.id.R9F);
        final TextView sum9 = (TextView)findViewById(R.id.R9S);
        final TextView rate9 = (TextView)findViewById(R.id.R9R);

        Integer Name9 = sharedPreferences.getInt("Rule,9", -1);
        Integer Success9 = sharedPreferences.getInt("success,9", 0);
        Integer Fail9 = sharedPreferences.getInt("fail,9", 0);
        Integer Sum9 = Success9 + Fail9;
        float Rate9 = (float)Success9 / (float)Sum9 * 100;
        ans = String.format("%.2f",Rate9);

        name9.setText("-2+3-2+2-1");
        success9.setText(String.valueOf(Success9));
        fail9.setText(String.valueOf(Fail9));
        sum9.setText(String.valueOf(Sum9));
        rate9.setText(ans + "%");

        final TextView name10 = (TextView)findViewById(R.id.R10N);
        final TextView success10 = (TextView)findViewById(R.id.R10C );
        final TextView fail10 = (TextView)findViewById(R.id.R10F);
        final TextView sum10 = (TextView)findViewById(R.id.R10S);
        final TextView rate10 = (TextView)findViewById(R.id.R10R);

        Integer Name10 = sharedPreferences.getInt("Rule,10", -1);
        Integer Success10 = sharedPreferences.getInt("success,10", 0);
        Integer Fail10 = sharedPreferences.getInt("fail,10", 0);
        Integer Sum10 = Success10 + Fail10;
        float Rate10 = (float)Success10 / (float)Sum10 * 100;
        ans = String.format("%.2f",Rate10);

        name10.setText("+1-2+1-1");
        success10.setText(String.valueOf(Success10));
        fail10.setText(String.valueOf(Fail10));
        sum10.setText(String.valueOf(Sum10));
        rate10.setText(ans + "%");

        final TextView name11 = (TextView)findViewById(R.id.R11N);
        final TextView success11 = (TextView)findViewById(R.id.R11C );
        final TextView fail11 = (TextView)findViewById(R.id.R11F);
        final TextView sum11 = (TextView)findViewById(R.id.R11S);
        final TextView rate11 = (TextView)findViewById(R.id.R11R);

        Integer Name11 = sharedPreferences.getInt("Rule,11", -1);
        Integer Success11 = sharedPreferences.getInt("success,11", 0);
        Integer Fail11 = sharedPreferences.getInt("fail,11", 0);
        Integer Sum11 = Success11 + Fail11;
        float Rate11 = (float)Success11 / (float)Sum11 * 100;
        ans = String.format("%.2f",Rate11);

        name11.setText("+2-1+2-1+1-1");
        success11.setText(String.valueOf(Success11));
        fail11.setText(String.valueOf(Fail11));
        sum11.setText(String.valueOf(Sum11));
        rate11.setText(ans + "%");

        final TextView name12 = (TextView)findViewById(R.id.R12N);
        final TextView success12 = (TextView)findViewById(R.id.R12C );
        final TextView fail12 = (TextView)findViewById(R.id.R12F);
        final TextView sum12 = (TextView)findViewById(R.id.R12S);
        final TextView rate12 = (TextView)findViewById(R.id.R12R);

        Integer Name12 = sharedPreferences.getInt("Rule,12", -1);
        Integer Success12 = sharedPreferences.getInt("success,12", 0);
        Integer Fail12 = sharedPreferences.getInt("fail,12", 0);
        Integer Sum12 = Success12 + Fail12;
        float Rate12 = (float)Success12 / (float)Sum12 * 100;
        ans = String.format("%.2f",Rate12);

        name12.setText("+3-2+1-1");
        success12.setText(String.valueOf(Success12));
        fail12.setText(String.valueOf(Fail12));
        sum12.setText(String.valueOf(Sum12));
        rate12.setText(ans + "%");

        final TextView name13 = (TextView)findViewById(R.id.R13N);
        final TextView success13 = (TextView)findViewById(R.id.R13C );
        final TextView fail13 = (TextView)findViewById(R.id.R13F);
        final TextView sum13 = (TextView)findViewById(R.id.R13S);
        final TextView rate13 = (TextView)findViewById(R.id.R13R);

        Integer Name13 = sharedPreferences.getInt("Rule,13", -1);
        Integer Success13 = sharedPreferences.getInt("success,13", 0);
        Integer Fail13 = sharedPreferences.getInt("fail,13", 0);
        Integer Sum13 = Success13 + Fail13;
        float Rate13 = (float)Success13 / (float)Sum13 * 100;
        ans = String.format("%.2f",Rate13);

        name13.setText("+5-1+1-1");
        success13.setText(String.valueOf(Success13));
        fail13.setText(String.valueOf(Fail13));
        sum13.setText(String.valueOf(Sum13));
        rate13.setText(ans + "%");

        final TextView name14 = (TextView)findViewById(R.id.R14N);
        final TextView success14 = (TextView)findViewById(R.id.R14C );
        final TextView fail14 = (TextView)findViewById(R.id.R14F);
        final TextView sum14 = (TextView)findViewById(R.id.R14S);
        final TextView rate14 = (TextView)findViewById(R.id.R14R);

        Integer Name14 = sharedPreferences.getInt("Rule,14", -1);
        Integer Success14 = sharedPreferences.getInt("success,14", 0);
        Integer Fail14 = sharedPreferences.getInt("fail,14", 0);
        Integer Sum14 = Success14 + Fail14;
        float Rate14 = (float)Success14 / (float)Sum14 * 100;
        ans = String.format("%.2f",Rate14);

        name14.setText("+5-2+1-1");
        success14.setText(String.valueOf(Success14));
        fail14.setText(String.valueOf(Fail14));
        sum14.setText(String.valueOf(Sum14));
        rate14.setText(ans + "%");

        final TextView name15 = (TextView)findViewById(R.id.R15N);
        final TextView success15 = (TextView)findViewById(R.id.R15C );
        final TextView fail15 = (TextView)findViewById(R.id.R15F);
        final TextView sum15 = (TextView)findViewById(R.id.R15S);
        final TextView rate15 = (TextView)findViewById(R.id.R15R);

        Integer Name15 = sharedPreferences.getInt("Rule,15", -1);
        Integer Success15 = sharedPreferences.getInt("success,15", 0);
        Integer Fail15 = sharedPreferences.getInt("fail,15", 0);
        Integer Sum15 = Success15 + Fail15;
        float Rate15 = (float)Success15 / (float)Sum15 * 100;
        ans = String.format("%.2f",Rate15);

        name15.setText("+7-2+1-1");
        success15.setText(String.valueOf(Success15));
        fail15.setText(String.valueOf(Fail15));
        sum15.setText(String.valueOf(Sum15));
        rate15.setText(ans + "%");

        final TextView name0 = (TextView)findViewById(R.id.R0N);
        final TextView success0 = (TextView)findViewById(R.id.R0C );
        final TextView fail0 = (TextView)findViewById(R.id.R0F);
        final TextView sum0 = (TextView)findViewById(R.id.R0S);
        final TextView rate0 = (TextView)findViewById(R.id.R0R);

        Integer Name0 = sharedPreferences.getInt("Rule,0", -1);
        Integer Success0 = sharedPreferences.getInt("success,0", 0);
        Integer Fail0 = sharedPreferences.getInt("fail,0", 0);
        Integer Sum0 = Success0 + Fail0;
        float Rate0 = (float)Success0 / (float)Sum0 * 100;
        ans = String.format("%.2f",Rate0);

        name0.setText("总计");
        success0.setText(String.valueOf(Success0));
        fail0.setText(String.valueOf(Fail0));
        sum0.setText(String.valueOf(Sum0));
        rate0.setText(ans + "%");
    }

    private String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
