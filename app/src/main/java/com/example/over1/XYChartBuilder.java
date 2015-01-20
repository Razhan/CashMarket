package com.example.over1;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Log;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;

public class XYChartBuilder extends Activity {

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;
    private Button mNewSeries, increase, decrease, remove,reset, resetall, save, close;
    private GraphicalView mChartView;
    private double previous_x;
    private double previous_y;
    private ArrayList<String> nodes = new ArrayList<String>();
    private Bundle bundle;
    private Intent intent;
    private String filename;
    private Boolean from;
    private Executor executor = new Executor();
    private MediaPlayer mp;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current data, for instance when changing screen orientation
        outState.putSerializable("dataset", mDataset);
        //Log.i("dataset", serialize(map));
        outState.putSerializable("renderer", mRenderer);
        outState.putSerializable("current_series", mCurrentSeries);
        outState.putSerializable("current_renderer", mCurrentRenderer);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

        mDataset = (XYMultipleSeriesDataset) savedState.getSerializable("dataset");
        mRenderer = (XYMultipleSeriesRenderer) savedState.getSerializable("renderer");
        mCurrentSeries = (XYSeries) savedState.getSerializable("current_series");
        mCurrentRenderer = (XYSeriesRenderer) savedState.getSerializable("current_renderer");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xychart_builder);

        previous_x = 0;
        previous_y = 0;
        //nodes.add("1,-1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
        //bottoms.add("0,0");

        intent = this.getIntent();
        bundle = intent.getExtras();
        filename = bundle.getString("FileName");
        from = bundle.getBoolean("From");

        mp = MediaPlayer.create(this, R.raw.dtmf);
        mp.setLooping(false);

        RendersInit();
        ButtonsInit();
    }

    private void initRenderer(XYSeriesRenderer renderer ) {
        // 随机颜色
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);

        String [] array = {"CIRCLE", "DIAMOND", "SQUARE", "TRIANGLE"};
        PointStyle style = PointStyle.valueOf(array[random.nextInt(4)]);

        renderer.setColor(ranColor);
        renderer.setPointStyle(style);
        renderer.setFillPoints(true);
        renderer.setLineWidth(1);
        renderer.setDisplayChartValues(true);
        renderer.setDisplayChartValuesDistance(10);
        renderer.setChartValuesSpacing(15);//显示的点的值与图的距离
        renderer.setChartValuesTextSize(30);//点的值的文字大小
    }

    @Override
    protected void onResume() {
        super.onResume();

//        mNewSeries.setEnabled(false);
//        resetall.setEnabled(false);

        if (mChartView == null) {
                if (from) {
                    Load(filename);
                    setDisabled(false);
                }
                else {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
                    mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);
                    layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

                    String seriesTitle = "产品 " + (mDataset.getSeriesCount() + 1);
                    XYSeries series = new XYSeries(seriesTitle);
                    mDataset.addSeries(series);
                    mCurrentSeries = series;
                    mCurrentSeries.add(0, 0);
                    XYSeriesRenderer renderer = new XYSeriesRenderer();
                    initRenderer(renderer);

                    mRenderer.addSeriesRenderer(renderer);
                    mCurrentRenderer = renderer;
                    mChartView.repaint();
                }
        }
        else {
            mChartView.repaint();
        }
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
            previous_x = saveobj.X;
            previous_y = saveobj.Y;
            nodes = saveobj.nodes;
            //bottoms = saveobj.bottoms;

            mRenderer.setChartTitle(name + "——存档");
            mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);

            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            layout.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));

            is.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.v("Serialization Read Error : ",ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void Save (String filename){
        FileOutputStream fos;
        try {
            fos = this.getBaseContext().openFileOutput(filename, Context.MODE_PRIVATE);


            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //SaveObj saveobj = new SaveObj(mRenderer, mDataset, mCurrentRenderer, mCurrentSeries, previous_x, previous_y, bottoms, nodes);
            SaveObj saveobj = new SaveObj(mRenderer, mDataset, mCurrentRenderer, mCurrentSeries, previous_x, previous_y, nodes);

            oos.writeObject(saveobj);
            oos.close();
            //saveSharedPreferences();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void RendersInit() {
        mRenderer.setChartTitle("新建项目");
        //mRenderer.setXTitle("Time");
        //mRenderer.setYTitle("Price");
        mRenderer.setXAxisMin(-1);
        mRenderer.setAxisTitleTextSize(30);
        mRenderer.setChartTitleTextSize(40);
        mRenderer.setLabelsTextSize(25);
        mRenderer.setXAxisMax(25);
        mRenderer.setYAxisMin(-10);
        mRenderer.setYAxisMax(10);
        mRenderer.setAxesColor(Color.RED);
        mRenderer.setLabelsColor(Color.WHITE);
        mRenderer.setShowGrid(true);
        mRenderer.setGridColor(Color.GRAY);
        mRenderer.setXLabels(25);
        mRenderer.setYLabels(25);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setPointSize((float) 6);
        mRenderer.setShowLegend(true);
        mRenderer.setLegendTextSize(30);
        mRenderer.setMargins(new int[]{10, 30, 30, 10});
        mRenderer.setBackgroundColor(Color.BLACK);
    }

    private void ButtonsInit(){
        increase = (Button) findViewById(R.id.button4);
        decrease = (Button) findViewById(R.id.button5);
        remove = (Button) findViewById(R.id.button6);
        reset = (Button) findViewById(R.id.button7);
        //resetall = (Button) findViewById(R.id.button8);
        save = (Button) findViewById(R.id.button9);
        //mNewSeries = (Button) findViewById(R.id.new_series);
        close = (Button) findViewById(R.id.button8);

        increase.setOnClickListener(new ButtonListener());
        decrease.setOnClickListener(new ButtonListener());
        remove.setOnClickListener(new ButtonListener());
        reset.setOnClickListener(new ButtonListener());
        //resetall.setOnClickListener(new ButtonListener());
        save.setOnClickListener(new ButtonListener());
        //mNewSeries.setOnClickListener(new ButtonListener());
        close.setOnClickListener(new ButtonListener());
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View arg0) {
            switch (arg0.getId())
            {
                //增加
                case R.id.button4:
                    mCurrentSeries.add(++previous_x, ++previous_y);
                    //AddBottoms();
                    nodes.add("0,1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
                    //Log.i("当前位置", Double.toString(previous_x) + ", " + Double.toString(previous_y));
                    remove.setEnabled(true);

                    Boolean res =  ReultProc(executor.Act(nodes));

//                    mp.start();
//                    mp.reset();


                    mChartView.repaint();
                    break;

                //减少
                case R.id.button5:
                    mCurrentSeries.add(++previous_x, --previous_y);

                    if (nodes.size() > 0) {
                        String[] nodeinfo = nodes.get(nodes.size() - 1).split(",");

                        if (Integer.parseInt(nodeinfo[0]) == 1) {
                            String temp = "0," + nodeinfo[1] + "," + nodeinfo[2] + "," + nodeinfo[3];
                            nodes.set(nodes.size() - 1, temp);
                        }
                    }

                    nodes.add("1,-1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
                    //Log.i("当前位置", Double.toString(previous_x) + ", " + Double.toString(previous_y));
                    remove.setEnabled(true);

                    Boolean res1 =  ReultProc(executor.Act(nodes));

//                    Uri notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                    Ringtone r1 = RingtoneManager.getRingtone(getApplicationContext(), notification1);
//                    r1.play();
//                    mp.start();
//                    mp.reset();

                    mChartView.repaint();
                    break;

                //删除
                case R.id.button6:
                    if (mCurrentSeries.getItemCount() > 1) {
                        mCurrentSeries.remove(mCurrentSeries.getItemCount() - 1);
//                        String temp = bottoms.get(bottoms.size() - 1);
//                        String[] array = temp.split(",");
//                        Log.i(array[0], Double.toString(previous_x));
//
//                        if (Double.parseDouble(array[0]) == (previous_x - 1)){
//                            bottoms.remove(bottoms.size() - 1);
//                            Log.i("删除谷底", array[0] + ", " + array[1] );
//                        }
                        executor.Remove(nodes.get(nodes.size() - 1));
                        nodes.remove(nodes.size() - 1);

                        remove.setEnabled(false);

                        --previous_x;
                        previous_y = mCurrentSeries.getY(mCurrentSeries.getItemCount() - 1);
                        mChartView.repaint();
                    }
                    break;

                //重置
                case R.id.button7:
                    new AlertDialog.Builder(XYChartBuilder.this)
                            .setTitle("确认")
                            .setMessage("确定重置当前产品吗？")
                            .setNegativeButton("否", null)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mCurrentSeries.clear();
                                    nodes.clear();
//                                    bottoms.clear();
//                                    bottoms.add("0,0");
                                    executor.Reset();

                                    previous_x = 0;
                                    previous_y = 0;
                                    mCurrentSeries.add(0, 0);
                                    mChartView.repaint();
                                }
                            })
                            .show();
                    break;

                //全部重置
//                case R.id.button8:
//                    new AlertDialog.Builder(XYChartBuilder.this)
//                            .setTitle("确认")
//                            .setMessage("确定重置所有产品吗？")
//                            .setNegativeButton("否", null)
//                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    mRenderer.removeAllRenderers();
//                                    mDataset.clear();
//                                    nodes.clear();
////                                    bottoms.clear();
////                                    bottoms.add("0,0");
//                                    executor.Reset();
//
//                                    previous_x = 0;
//                                    previous_y = 0;
//
//                                    String seriesTitle = "产品 " + (mDataset.getSeriesCount() + 1);
//                                    // create a new series of data
//                                    XYSeries series = new XYSeries(seriesTitle);
//                                    mDataset.addSeries(series);
//                                    mCurrentSeries = series;
//                                    mCurrentSeries.add(0, 0);
//                                    // create a new renderer for the new series
//                                    XYSeriesRenderer renderer = new XYSeriesRenderer();
//                                    initRenderer(renderer);
//                                    mRenderer.addSeriesRenderer(renderer);
//                                    // set some renderer properties
//                                    mCurrentRenderer = renderer;
//                                    mChartView.repaint();
//
//                                    mChartView.repaint();
//                                }
//                            })
//                            .show();
//                    break;

                //保存
                case R.id.button9:
                    final EditText inputServer = new EditText(XYChartBuilder.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(XYChartBuilder.this);
                    builder.setTitle("保存").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                            .setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Save(inputServer.getText().toString());
                        }
                    });
                    builder.show();
                    break;

                //新产品
//                case R.id.new_series:
//                    previous_x = 0;
//                    previous_y = 0;
//                    nodes.clear();
////                    bottoms.clear();
////                    bottoms.add("0,0");
//                    executor.Reset();
//
//
//                    String seriesTitle = "产品 " + (mDataset.getSeriesCount() + 1);
//                    // create a new series of data
//                    XYSeries series = new XYSeries(seriesTitle);
//                    mDataset.addSeries(series);
//                    mCurrentSeries = series;
//                    mCurrentSeries.add(0, 0);
//                    // create a new renderer for the new series
//                    XYSeriesRenderer renderer = new XYSeriesRenderer();
//                    initRenderer(renderer);
//
//                    mRenderer.addSeriesRenderer(renderer);
//                    mCurrentRenderer = renderer;
//                    mChartView.repaint();
//                    break;
                case R.id.button8:
                    new AlertDialog.Builder(XYChartBuilder.this)
                            .setTitle("确认")
                            .setMessage("确定关闭当前产品吗？")
                            .setNegativeButton("否", null)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                finish();
                                }
                            })
                            .show();
                    break;
            }
        }
    }

    private Boolean ReultProc(Boolean[] result){
        if (result[0]){
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            long [] pattern = {100,350};
            vibrator.vibrate(pattern, -1);

//            new AlertDialog.Builder(XYChartBuilder.this)
//                    .setTitle("符合规则")
//                    .setMessage("建议买入")
//                    .setPositiveButton("确定", null)
//                    .show();

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();


            return true;

        }
//        else if (result[1]){
//            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//            long [] pattern = {50,600};
//            vibrator.vibrate(pattern, -1);
//
//            new AlertDialog.Builder(XYChartBuilder.this)
//                    .setTitle("符合规则")
//                    .setMessage("建议停止买入")
//                    .setPositiveButton("确定", null)
//                    .show();
//        }
        return false;

    }

    private void saveSharedPreferences(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("rate",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Rule1", "规则一");
        editor.putInt("R1-success", 3);
        editor.putInt("R1-sum", 5);

        editor.commit();
    }

    private void setDisabled(boolean enabled) {
        increase.setEnabled(enabled);
        decrease.setEnabled(enabled);
        remove.setEnabled(enabled);
        reset.setEnabled(enabled);
        //resetall.setEnabled(enabled);
        save.setEnabled(enabled);
        //mNewSeries.setEnabled(enabled);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Integer i = 0 ;
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){

            mCurrentSeries.add(++previous_x, ++previous_y);
            nodes.add("0,1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
            remove.setEnabled(true);

            Boolean res =  ReultProc(executor.Act(nodes));

            mChartView.repaint();


            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            mCurrentSeries.add(++previous_x, --previous_y);

            if (nodes.size() > 0) {
                String[] nodeinfo = nodes.get(nodes.size() - 1).split(",");

                if (Integer.parseInt(nodeinfo[0]) == 1) {
                    String temp = "0," + nodeinfo[1] + "," + nodeinfo[2] + "," + nodeinfo[3];
                    nodes.set(nodes.size() - 1, temp);
                }
            }

            nodes.add("1,-1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
            remove.setEnabled(true);

            Boolean res1 =  ReultProc(executor.Act(nodes));

            mChartView.repaint();

            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mCurrentSeries.getItemCount() > 1) {
                mCurrentSeries.remove(mCurrentSeries.getItemCount() - 1);
//                        String temp = bottoms.get(bottoms.size() - 1);
//                        String[] array = temp.split(",");
//                        Log.i(array[0], Double.toString(previous_x));
//
//                        if (Double.parseDouble(array[0]) == (previous_x - 1)){
//                            bottoms.remove(bottoms.size() - 1);
//                            Log.i("删除谷底", array[0] + ", " + array[1] );
//                        }
                executor.Remove(nodes.get(nodes.size() - 1));
                nodes.remove(nodes.size() - 1);

                remove.setEnabled(false);

                --previous_x;
                previous_y = mCurrentSeries.getY(mCurrentSeries.getItemCount() - 1);
                mChartView.repaint();
            }

            return true;
        }

        else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){

            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            return true;
        }
        else return super.onKeyUp(keyCode, event);
    }
}