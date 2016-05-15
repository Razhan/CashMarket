package com.example.cashmarket;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
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
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.example.cashmarket.Helper.*;

public class Chart extends Activity {

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries, average_5, average_10, golden;
    private XYSeriesRenderer mCurrentRenderer, average_5_render, average_10_render, golden_render;
    private Button mNewSeries, increase, decrease, remove,reset,save, close;
    private GraphicalView mChartView;
    private double previous_x;
    private double previous_y;
    private String previous_style;
    private int previous_color;
    private String previous_style_bu;
    private int previous_color_bu;

    private Integer success_count = 0;

    private ArrayList<String> nodes = new ArrayList<String>();
    private Executor executor = new Executor();
    private Boolean delete;
    private PowerManager.WakeLock mWakeLock;

    private SoundPool soundPool;
    private int soundID1, soundID2, soundID3;
    boolean plays = false, loaded = false;
    private AudioManager audioManager;

    private Deque<Double> deque_5 = new ArrayDeque<Double>();
    private Deque<Double> deque_10 = new ArrayDeque<Double>();

    private double sum_5 = 0;
    private double sum_10 = 0;
    private double prev_5 = 0;
    private double prev_10 = 0;
    private double max = 0;
    private double min = 0;
    private double max_bu = 0;
    private double min_bu = 0;


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // save the current data, for instance when changing screen orientation
//        outState.putSerializable("dataset", mDataset);
//        //Log.i("dataset", serialize(map));
//        outState.putSerializable("renderer", mRenderer);
//        outState.putSerializable("current_series", mCurrentSeries);
//        outState.putSerializable("current_renderer", mCurrentRenderer);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedState) {
//        super.onRestoreInstanceState(savedState);
//
//        mDataset = (XYMultipleSeriesDataset) savedState.getSerializable("dataset");
//        mRenderer = (XYMultipleSeriesRenderer) savedState.getSerializable("renderer");
//        mCurrentSeries = (XYSeries) savedState.getSerializable("current_series");
//        mCurrentRenderer = (XYSeriesRenderer) savedState.getSerializable("current_renderer");
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xychart_builder);

        previous_x = 0;
        previous_y = 0;

        previous_style = "CIRCLE";
        previous_color = Color.YELLOW;
        previous_style_bu = "CIRCLE";
        previous_color_bu = Color.YELLOW;

        //nodes.add("1,-1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
        //bottoms.add("0,0");

        delete = false;

        RendersInit();
        ButtonsInit();

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
    }

    private void initRenderer(XYSeriesRenderer renderer, String style, int color, Boolean value, int width, Boolean fill, boolean show ) {

        String [] array = {"CIRCLE", "DIAMOND", "SQUARE", "TRIANGLE"};
        PointStyle Style = PointStyle.valueOf(style);

        renderer.setColor(color);
        renderer.setPointStyle(Style);
        renderer.setFillPoints(true);
        renderer.setLineWidth(width);
        renderer.setDisplayChartValues(value);
        renderer.setDisplayChartValuesDistance(10);
        renderer.setChartValuesSpacing(15);//显示的点的值与图的距离
        renderer.setChartValuesTextSize(30);//点的值的文字大小
        renderer.setShowLegendItem(show);

    }

    private void initview(){

        XYSeries series_11 = new XYSeries("Golden Line  ");
        mDataset.addSeries(series_11);
        golden = series_11;
        golden.add(0, 0);
        XYSeriesRenderer renderer_11 = new XYSeriesRenderer();
        initRenderer(renderer_11, "DIAMOND", Color.RED, false, 2, false, true);
        mRenderer.addSeriesRenderer(renderer_11);
        golden_render = renderer_11;

        XYSeries series_5 = new XYSeries("Average Line - 5  ");
        mDataset.addSeries(series_5);
        average_5 = series_5;

        series_5.add(0, 0);
        XYSeriesRenderer renderer_5 = new XYSeriesRenderer();
        initRenderer(renderer_5, "DIAMOND", Color.WHITE, false, 3, false, true);
        mRenderer.addSeriesRenderer(renderer_5);
        average_5_render = renderer_5;


        XYSeries series_10 = new XYSeries("Average Line - 10");
        mDataset.addSeries(series_10);
        average_10 = series_10;
        series_10.add(0, 0);
        XYSeriesRenderer renderer_10 = new XYSeriesRenderer();
        initRenderer(renderer_10, "DIAMOND", Color.CYAN, false, 3, false, true);
        mRenderer.addSeriesRenderer(renderer_10);
        average_10_render = renderer_10;

        XYSeries series = new XYSeries("");
        mDataset.addSeries(series);
        mCurrentSeries = series;
        mCurrentSeries.add(0, 0);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        initRenderer(renderer, "CIRCLE", Color.YELLOW, true, 2, true, false);

        mRenderer.addSeriesRenderer(renderer);
        mCurrentRenderer = renderer;
    }

    @Override
    protected void onResume() {
        super.onResume();
        remove.setEnabled(false);
        mWakeLock.acquire();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // Load the sounds
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        soundID1 = soundPool.load(this, R.raw.ben, 1);
        soundID2 = soundPool.load(this, R.raw.bounce, 1);
        soundID3 = soundPool.load(this, R.raw.click, 1);



        if (mChartView == null) {

            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);
            layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

            initview();
            mChartView.repaint();

        }
        else {
            mChartView.repaint();
        }
    }

    protected void onPause() {
        super.onPause();
        mWakeLock.release();
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

        nodes.add("2,2," + Double.toString(previous_x) + "," + Double.toString(previous_y));

        FileOutputStream fos;
        try {
            fos = this.getBaseContext().openFileOutput(filename, Context.MODE_PRIVATE);


            ObjectOutputStream oos = new ObjectOutputStream(fos);

            SaveObj saveobj = new SaveObj(mRenderer, mDataset, mCurrentRenderer, mCurrentSeries, previous_x, previous_y, nodes);

            oos.writeObject(saveobj);
            oos.close();

            Statistics(executor.GetResult(), filename);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void Statistics(List<Pair<Integer,Integer>> res, String filename){
        Integer ups = 0;
        Integer downs = 0;
        Integer i = 8;


        for(Integer j=0; j < res.size();j++) {
            saveSharedPreferences(i++, res.get(j).getL(), res.get(j).getR(), filename);

            ups += res.get(j).getL();
            downs += res.get(j).getR();

        }
        saveSharedPreferences(0, ups, downs, filename);
    }

    private void saveSharedPreferences(Integer rule_num, Integer success, Integer fail, String filename){

        SharedPreferences sharedPreferences = this.getSharedPreferences(filename, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Rule," + rule_num.toString(), rule_num);
        editor.putInt("success," + rule_num.toString(), success);
        editor.putInt("fail," + rule_num.toString(), fail);
        editor.putInt("sum," + rule_num.toString(), success + fail);

        editor.commit();
    }

    private void RendersInit() {
        mRenderer.setChartTitle("New Product");
        //mRenderer.setXTitle("Time");
        //mRenderer.setYTitle("Price");
        mRenderer.setXAxisMin(-1);
        mRenderer.setAxisTitleTextSize(0);
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
        mRenderer.setPointSize((float) 7);
        mRenderer.setShowLegend(true);
        mRenderer.setLegendTextSize(30);
        mRenderer.setMargins(new int[]{10, 30, 35, 10});
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
                    new ButtonProc().execute("add");
                    //add();
                    break;

                //减少
                case R.id.button5:
                    new ButtonProc().execute("reduce");
                    //reduce();
                    break;

                //删除
                case R.id.button6:
                    new ButtonProc().execute("remove");
                    //remove();
                    break;
                case R.id.button7:
                    new AlertDialog.Builder(Chart.this)
                            .setTitle("Confirm")
                            .setMessage("Reset the product？")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mRenderer.removeAllRenderers();
                                    mDataset.clear();
                                    nodes.clear();
//                                    bottoms.clear();
//                                    bottoms.add("0,0");
                                    executor.Reset();
                                    remove.setEnabled(false);
                                    delete = false;

                                    previous_x = 0;
                                    previous_y = 0;
                                    previous_style = "CIRCLE";
                                    previous_color = Color.YELLOW;
                                    previous_style_bu = "CIRCLE";
                                    previous_color_bu = Color.YELLOW;

                                    deque_5.clear();
                                    deque_10.clear();

                                    sum_5 = 0;
                                    sum_10 = 0;
                                    prev_5 = 0;
                                    prev_10 = 0;

                                    max = 0;
                                    min = 0;
                                    max_bu = 0;
                                    min_bu = 0;

//                                    // create a new series of data
//                                    XYSeries series = new XYSeries("");
//                                    mDataset.addSeries(series);
//                                    mCurrentSeries = series;
//                                    mCurrentSeries.add(0, 0);
//                                    // create a new renderer for the new series
//                                    XYSeriesRenderer renderer = new XYSeriesRenderer();
//                                    initRenderer(renderer, "CIRCLE", Color.YELLOW, true);
//                                    mRenderer.addSeriesRenderer(renderer);
//                                    // set some renderer properties
//                                    mCurrentRenderer = renderer;
//                                    mChartView.repaint();
                                    initview();
                                    mChartView.repaint();
                                }
                            })
                            .show();
                    break;

                //保存
                case R.id.button9:
                    final EditText inputServer = new EditText(Chart.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Chart.this);
                    builder.setTitle("SAVE").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                            .setNegativeButton("Cancel", null);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            new ButtonProc().execute(inputServer.getText().toString());

                            //Save(inputServer.getText().toString());
                        }
                    });
                    builder.show();
                    break;
                case R.id.button8:
                    new AlertDialog.Builder(Chart.this)
                            .setTitle("Confirm")
                            .setMessage("Close the current product？")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                    break;
            }
        }
    }

    private Boolean[] ReultProc(Boolean[] result){
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
            return result;

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
        return result;
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
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            new ButtonProc().execute("add");
            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            new ButtonProc().execute("reduce");
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_BACK) {
            new ButtonProc().execute("remove");
            return true;
        }
        else
            return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if(keyCode==KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE){
//            remove();
//            return true;
//        }
//        else
//            return super.onKeyUp(keyCode, event);
//    }

    private void add(){

        //mCurrentSeries.add(++previous_x, ++previous_y);
        nodes.add("0,1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
        //remove.setEnabled(true);
        delete = true;

        if (loaded) {
            soundPool.play(soundID3, 0, 0, 1, -1, 1f);
            soundPool.play(soundID1, 1, 1, 1, 0, 1f);
        }

        goldenline(1, previous_x);
        Boolean[] res =  ReultProc(executor.Act(nodes));

        addhelper(res[0], res[1]);

        deque_5.addLast(previous_y);
        sum_5 += previous_y;

        if (deque_5.size() == 5){
            double y = sum_5 / 5.0;
            average_5.add(previous_x, y);

        }else if(deque_5.size() > 5){
            prev_5 = deque_5.remove();
            sum_5 -= prev_5;

            double y = sum_5 / 5.0;
            average_5.add(previous_x, y);
        }

        deque_10.addLast(previous_y);
        sum_10 += previous_y;

        if (deque_10.size() == 10){
            double y = sum_10 / 10.0;
            average_10.add(previous_x, y);

        }else if(deque_10.size() > 10){
            prev_10 = deque_10.remove();
            sum_10 -= prev_10;

            double y = sum_10 / 10.0;
            average_10.add(previous_x, y);
        }
    }

    private void reduce(){

        //mCurrentSeries.add(++previous_x, --previous_y);

//        if (nodes.size() > 0) {
//            String[] nodeinfo = nodes.get(nodes.size() - 1).split(",");
//
//            if (Integer.parseInt(nodeinfo[0]) == 1) {
//                String temp = "0," + nodeinfo[1] + "," + nodeinfo[2] + "," + nodeinfo[3];
//                nodes.set(nodes.size() - 1, temp);
//            }
//        }

        nodes.add("1,-1," + Double.toString(previous_x) + "," + Double.toString(previous_y));
        //remove.setEnabled(true);
        delete = true;

        if (loaded) {
            soundPool.play(soundID3, 0, 0, 1, -1, 1f);
            soundPool.play(soundID2, 1, 1, 1, 0, 1f);
        }

        goldenline(-1, previous_x);
        Boolean[] res1 =  ReultProc(executor.Act(nodes));

        reducehelper(res1[0], res1[1]);

        deque_5.addLast(previous_y);
        sum_5 += previous_y;

        if (deque_5.size() == 5){
            double y = sum_5 / 5.0;
            average_5.add(previous_x, y);

        }else if(deque_5.size() > 5){
            double first = deque_5.remove();
            sum_5 -= first;

            double y = sum_5 / 5.0;
            average_5.add(previous_x, y);
        }

        deque_10.addLast(previous_y);
        sum_10 += previous_y;

        if (deque_10.size() == 10){
            double y = sum_10 / 10.0;
            average_10.add(previous_x, y);

        }else if(deque_10.size() > 10){
            prev_10 = deque_10.remove();
            sum_10 -= prev_10;

            double y = sum_10 / 10.0;
            average_10.add(previous_x, y);
        }
    }

    private void remove(){
        if (mCurrentSeries.getItemCount() >= 1 && delete) {

            if (mCurrentSeries.getItemCount() == 1) {
                mDataset.removeSeries(mDataset.getSeriesCount() - 1);
                mRenderer.removeSeriesRenderer(mRenderer.getSeriesRendererAt(mRenderer.getSeriesRendererCount() - 1));

                mCurrentSeries = mDataset.getSeriesAt(mDataset.getSeriesCount() - 1);
                mCurrentRenderer = (XYSeriesRenderer) mRenderer.getSeriesRendererAt(mRenderer.getSeriesRendererCount() - 1);

                previous_style = previous_style_bu;
                previous_color = previous_color_bu;

            }

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
            removegoldline(previous_x, previous_y);

            nodes.remove(nodes.size() - 1);


            if (success_count > 0) success_count--;

            //remove.setEnabled(false);
            delete = false;

            if (loaded) {
                soundPool.play(soundID3, 0, 0, 1, -1, 1f);
                soundPool.play(soundID3, 1, 1, 1, 0, 1f);
            }

            --previous_x;
            previous_y = mCurrentSeries.getY(mCurrentSeries.getItemCount() - 1);

            if (deque_5.size() >= 5) {
                double last = deque_5.removeLast();
                deque_5.addFirst(prev_5);
                sum_5 -= last;
                sum_5 += prev_5;
                average_5.remove(average_5.getItemCount() - 1);
            }else{
                double last = deque_5.removeLast();
                sum_5 -= last;
            }

            if (deque_10.size() >= 10) {
                double last = deque_10.removeLast();
                deque_10.addFirst(prev_10);
                sum_10 -= last;
                sum_10 += prev_10;
                average_10.remove(average_10.getItemCount() - 1);
            }else{
                double last = deque_10.removeLast();
                sum_10 -= last;
            }
        }
    }

    @Override
    protected void onDestroy() {
        soundPool.release();
        soundPool = null;
        super.onDestroy();
    }

    private void addhelper(Boolean res1, Boolean res2){

        if (res1){

            success_count++;

            //if (previous_style.equals("CIRCLE") && previous_color == Color.YELLOW) {
            if (success_count == 1){
                mCurrentSeries.add(previous_x + 1, previous_y + 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, ++previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "SQUARE", Color.YELLOW, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "SQUARE";
                previous_color = Color.YELLOW;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            } else if (previous_color != Color.RED){
                mCurrentSeries.add(previous_x + 1, previous_y + 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, ++previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.RED, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.RED;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;

            } else {
                mCurrentSeries.add(++previous_x, ++previous_y);
            }
        }
        else if (res2){

            success_count = 0;
            if (previous_color != Color.RED){
                mCurrentSeries.add(previous_x + 1, previous_y + 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, ++previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.RED, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.RED;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            } else{
                mCurrentSeries.add(++previous_x, ++previous_y);
            }
        }
        else{

            success_count = 0;
            if (previous_color != Color.YELLOW){
                mCurrentSeries.add(previous_x + 1, previous_y + 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, ++previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.YELLOW, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.YELLOW;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            }
            else{
                mCurrentSeries.add(++previous_x, ++previous_y);
            }
        }

    }

    private void reducehelper(Boolean res1, Boolean res2){
        if (res1){

            success_count++;


            //if (previous_style.equals("CIRCLE") && previous_color == Color.YELLOW) {
            if (success_count == 1){
                mCurrentSeries.add(previous_x + 1, previous_y - 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, --previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "SQUARE", Color.YELLOW, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "SQUARE";
                previous_color = Color.YELLOW;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            } else if (previous_color != Color.GREEN){
                mCurrentSeries.add(previous_x + 1, previous_y - 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, --previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.GREEN, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.GREEN;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;

            } else {
                mCurrentSeries.add(++previous_x, --previous_y);
            }
        }
        else if (res2){

            success_count = 0;

            if (previous_color != Color.GREEN){
                mCurrentSeries.add(previous_x + 1, previous_y - 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, --previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.GREEN, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.GREEN;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            }
            else{
                mCurrentSeries.add(++previous_x, --previous_y);
            }
        }
        else{

            success_count = 0;

            if (previous_color != Color.YELLOW){
                mCurrentSeries.add(previous_x + 1, previous_y - 1);

                XYSeries series = new XYSeries("");
                mDataset.addSeries(series);
                mCurrentSeries = series;
                mCurrentSeries.add(++previous_x, --previous_y);
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                initRenderer(renderer, "CIRCLE", Color.YELLOW, true, 2, true, false);

                previous_style_bu = previous_style;
                previous_color_bu = previous_color;
                previous_style = "CIRCLE";
                previous_color = Color.YELLOW;

                mRenderer.addSeriesRenderer(renderer);
                mCurrentRenderer = renderer;
            }
            else{
                mCurrentSeries.add(++previous_x, --previous_y);
            }
        }
    }

    private void goldenline(Integer i, Double x){

        Double y = previous_y + i;

        if (y > max){
            max_bu = max;
            max = y;
        }
        if (y < min){
            min_bu = min;
            min = y;
        }

        y = min + (max - min) * 0.618;

        golden.clear();
        golden.add(0, y);
        golden.add(previous_x + 1, y);
    }

    private void removegoldline(Double x, Double y){


        if (min == y){
            min = min_bu;
        }
        if (max == y){
            max = max_bu;
        }

        double v = min + (max - min) * 0.618;

        golden.clear();
        golden.add(0, v);
        golden.add(x - 1, v);
    }



    class ButtonProc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... types) {

            String type = types[0];

            if (type.equals("add")){
                add();
            } else if (type.equals("reduce")){
                reduce();
            } else if (type.equals("remove")){
                remove();
            }else {
                Save(type);
            }
            return type;
        }
        @Override
        protected void onPostExecute(String type) {

            if (type.equals("add")){

                remove.setEnabled(true);
                mChartView.repaint();
            } else if (type.equals("reduce")){

                remove.setEnabled(true);
                mChartView.repaint();
            } else if (type.equals("remove")) {
                remove.setEnabled(false);
                mChartView.repaint();
            }else {
                Toast.makeText(getApplicationContext(), "Save Completed", Toast.LENGTH_LONG).show();
            }
        }
    }
}