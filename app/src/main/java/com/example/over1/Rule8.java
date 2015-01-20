package com.example.over1;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Rule8 extends Rules{
    public Rule8(){

        locked = false;
        locktag = "-1";
    }

    public Boolean[] Execute(ArrayList<String> nodes){
        Log.i("Execute", "Rule1 executed!");
        Boolean[] res =  new Boolean[2];
        res[0] = false;
        res[1] = false;

        if(locked){
            Boolean l =  LockedProc(nodes);
            if (l){
                res[0] = true;
                res[1] = true;
            } else {
                locked = false;
                res[0] = false;
                res[1] = true;
            }

        }else {
            Boolean m =  MeetRule(nodes);
            if (m){
                res[0] = true;
                res[1] = true;
                locked = true;

            }
        }
        return res;
    }

    public void Remove(String node){
        String[] array = node.split(",");

        if (!locked){
            if (locktag.equals(array[2])){
                locked = true;
            }
        }
    }

    public Boolean MeetRule(ArrayList<String> nodes){
        if (nodes.size() >= 8){

            String temp3 = nodes.get(nodes.size() - 8);
            String[] array3 = temp3.split(",");
            if (!array3[1].equals("-1")) return false;

            String temp4 = nodes.get(nodes.size() - 7);
            String[] array4 = temp4.split(",");
            if (!array4[1].equals("-1")) return false;

            String temp5 = nodes.get(nodes.size() - 6);
            String[] array5 = temp5.split(",");
            if (!array5[1].equals("-1")) return false;

            String temp6 = nodes.get(nodes.size() - 5);
            String[] array6 = temp6.split(",");
            if (!array6[1].equals("-1")) return false;

            String temp7 = nodes.get(nodes.size() - 4);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 3);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("1")) return false;

            String temp9 = nodes.get(nodes.size() - 2);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("-1")) return false;

            String temp10 = nodes.get(nodes.size() - 1);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("1")) return false;

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
        if (nodes.size() >= 9){

            String temp7 = nodes.get(nodes.size() - 9);
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
        if (array[1].equals("1")) return true;

        locktag = array[2];

        return false;
    }

    public void Reset(){
        locked = false;
        locktag = "-1";
    }
}