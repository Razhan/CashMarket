package com.example.cashmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cashmarket.Helper.SaveObj;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.cashmarket.listviewitems.BarChartItem;
import com.example.cashmarket.listviewitems.ChartItem;
import com.example.cashmarket.listviewitems.LineChartItem;
import com.example.cashmarket.listviewitems.PieChartItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.cashmarket.R;

import org.achartengine.ChartFactory;

public class ListViewMultiChartActivity extends Activity {

    private String filename;
    private ArrayList<String> nodes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_multi_chart);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        filename = bundle.getString("FileName");

        //String temp = getApplicationInfo().dataDir + "shared_prefs" + "/" + filename + ".xml";
        String temp  = getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filename;
        Load(filename);

        ListView lv = (ListView) findViewById(R.id.listView1);

        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        // 30 items
        for (int i = 0; i < 3; i++) {

            if(i % 3 == 0) {
                list.add(new LineChartItem(generateDataLine(i + 1), getApplicationContext()));
            }            else if(i % 3 == 1) {
                list.add(new BarChartItem(generateDataBar(i + 1), getApplicationContext()));
            }else if(i % 3 == 2) {
                list.add(new PieChartItem(generateDataPie(i + 1), getApplicationContext()));
            }
        }

        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    private LineData generateDataLine(int cnt) {
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < nodes.size(); i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < nodes.size(); i++) {
            String temp = nodes.get(i);
            String[] array = temp.split(",");

            yVals.add(new Entry(Float.parseFloat(array[3]), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, filename);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(3f);
        set1.setCircleSize(4f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (Integer i = 0; i < 10; i++) {
            xVals.add("Rule" + i.toString());
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 100);
            yVals1.add(new BarEntry(val, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(20f);
        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setHighLightAlpha(255);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        return data;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < 2; i++) {
            yVals1.add(new Entry((float) (Math.random() * 100) + 100 / 2, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (Integer i = 0; i < 2; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet set1 = new PieDataSet(yVals1, "");
        set1.setSliceSpace(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ColorTemplate.VORDIPLOM_COLORS[4]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[0]);



        set1.setColors(colors);

        PieData data = new PieData(xVals, set1);
        return data;
    }

    private void Load(String name) {
        try
        {
            FileInputStream fis = this.getBaseContext().openFileInput(name);
            ObjectInputStream is = new ObjectInputStream(fis);
            SaveObj saveobj = (SaveObj) is.readObject();

            nodes = saveobj.nodes;

            is.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.v("Deserialization Read Error : ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    protected String[] mParties = new String[] {
            "Success rate", "Failure rate"
    };
}
