package com.example.cashmarket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cashmarket.Helper.SaveObj;
import com.example.cashmarket.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load extends Activity {

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;
    private GraphicalView mChartView;
    private String filename;
    private Button successrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        filename = bundle.getString("FileName");
        Load(filename);

        successrate = (Button)findViewById(R.id.button3L);

        successrate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            Intent intent = new Intent();
            intent.putExtra("FileName",filename);
            intent.setClass(Load.this, ListViewMultiChartActivity.class);
            Load.this.startActivity(intent);
            }
        });


    }

    private void Load(String name) {
        try
        {
            FileInputStream fis = this.getBaseContext().openFileInput(name);
            ObjectInputStream is = new ObjectInputStream(fis);
            SaveObj saveobj = (SaveObj) is.readObject();

            mRenderer = saveobj.XYMultipleSeriesRenderer;
            mDataset = saveobj.Dataset;
            mCurrentRenderer = saveobj.CurrentRenderer;
            mCurrentSeries = saveobj.CurrentSeries;
            //bottoms = saveobj.bottoms;

            mRenderer.setChartTitle(name + "——Archive");
            mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);

            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            is.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.v("Serialization Read Error : ", ex.getMessage());
            ex.printStackTrace();
        }
    }
}
