package com.example.over1;

import android.text.BoringLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Executor {
    private ArrayList<Rules> ruleslist = new ArrayList<Rules>();

    public Executor() {
        Rules rule1 = new Rule1();
        Rules rule3 = new Rule3();
        Rules rule4 = new Rule4();
        Rules rule5 = new Rule5();
        Rules rule6 = new Rule6();
        Rules rule7 = new Rule7();
        Rules rule8 = new Rule8();
//        Rules rule9 = new Rule9();
//        Rules rule10 = new Rule10();

        ruleslist.add(rule1);
        ruleslist.add(rule3);
        ruleslist.add(rule4);
        ruleslist.add(rule5);
        ruleslist.add(rule6);
        ruleslist.add(rule7);
        ruleslist.add(rule8);
//        ruleslist.add(rule9);
//        ruleslist.add(rule10);
    }

    public Boolean[] Act(ArrayList<String> nodes){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Boolean flag = false;
        Boolean[] res =  new Boolean[2];

        for(int i = 0;i < ruleslist.size(); ++i){

            Boolean[] returnvalue = new Boolean[2];
            returnvalue = ruleslist.get(i).Execute(nodes);
            //result.add("符合规则" + Integer.toString(i + 1);

            if (returnvalue[0]){
                flag = true;
            } else {
                if (returnvalue[1]){
                    res[0] = false;
                    res[1] = true;
                    return res;
                }
            }
        }
        res[0] = flag;
        res[1] = false;

        return res;
    }

    public void Reset(){
        for(int i = 0;i < ruleslist.size(); ++i) {
            ruleslist.get(i).Reset();
        }
    }

    public void Remove(String node) {
        for (int i = 0; i < ruleslist.size(); ++i) {
            ruleslist.get(i).Remove(node);
        }
    }
}
