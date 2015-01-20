package com.example.over1;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Rule7 extends Rules{
    public Rule7(){

        sum = 0;
        locked = false;
        tag = "-1";
        oldsum = 0;
        locktag = "-1";
    }

    private Integer sum;
    private Integer oldsum;

    public Boolean[] Execute(ArrayList<String> nodes){
        Log.i("Execute", "Rule1 executed!");
        Boolean[] res =  new Boolean[2];
        res[0] = false;
        res[1] = false;

        if(locked){
            Boolean l =  LockedProc(nodes);
            if (!l){
                //Log.i("Execute", "符合规则!");
                res[0] = true;
                res[1] = true;
            }
            else{
                oldsum = sum;
                sum = 0;
                locked = false;
            }
        }else {
            Boolean m =  MeetRule(nodes);
            if (m){
                //Log.i("Execute", "符合规则!");
                res[0] = true;
                res[1] = true;
                locked = true;

                String temp = nodes.get(nodes.size() - 1);
                String[] array = temp.split(",");

                tag =  array[2];
            }
        }
        return res;
    }

    public void Remove(String node){
        String[] array = node.split(",");

        if (locked){
            //进lock范围
            if (tag.equals(array[2])){
                tag = "-1";
                locked = false;
                oldsum = sum;
                sum = 0;
            } else {
                sum -= Integer.parseInt(array[1]);
            }

        } else {
            //出lock范围
            if (locktag.equals(array[2])){
                locked = true;
                sum = oldsum - Integer.parseInt(array[1]);
            }
        }
    }

    public Boolean MeetRule(ArrayList<String> nodes){
        if (nodes.size() >= 6){
            String temp1 = nodes.get(nodes.size() - 6);
            String[] array1 = temp1.split(",");
            if (!array1[1].equals("-1")) return false;

            String temp2 = nodes.get(nodes.size() - 5);
            String[] array2 = temp2.split(",");
            if (!array2[1].equals("-1")) return false;

            String temp3 = nodes.get(nodes.size() - 4);
            String[] array3 = temp3.split(",");
            if (!array3[1].equals("-1")) return false;

            String temp4 = nodes.get(nodes.size() - 3);
            String[] array4 = temp4.split(",");
            if (!array4[1].equals("1")) return false;

            String temp5 = nodes.get(nodes.size() - 2);
            String[] array5 = temp5.split(",");
            if (!array5[1].equals("-1")) return false;

            String temp6 = nodes.get(nodes.size() - 1);
            String[] array6 = temp6.split(",");
            if (!array6[1].equals("1")) return false;

            Boolean a0 = Addition0(nodes);

            if (!a0){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private Boolean Addition0(ArrayList<String> nodes){
        if (nodes.size() >= 7){

            String temp7 = nodes.get(nodes.size() - 7);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

            return true;
        }
        else{
            return false;
        }
    }

    public Boolean LockedProc(ArrayList<String> nodes){
        String temp = nodes.get(nodes.size() - 1);
        String[] array = temp.split(",");

        sum += Integer.parseInt(array[1]);

        if (sum == 2 || sum == -2) {
            locktag = array[2];
            return true;
        }

        return false;
    }

    public void Reset(){
        sum = 0;
        locked = false;
        tag = "-1";
        oldsum = 0;
        locktag = "-1";    }
}