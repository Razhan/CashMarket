package com.example.over1;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Rule6 extends Rules{
    public Rule6(){

        locked = false;
        sum = 0;
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
            if (!array1[1].equals("1")) return false;

            String temp2 = nodes.get(nodes.size() - 5);
            String[] array2 = temp2.split(",");
            if (!array2[1].equals("-1")) return false;

            String temp3 = nodes.get(nodes.size() - 4);
            String[] array3 = temp3.split(",");
            if (!array3[1].equals("1")) return false;

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
            Boolean a1 = Addition1(nodes);
            Boolean a2 = Addition2(nodes);

            if (!a0 && !a1 && !a2){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Boolean LockedProc(ArrayList<String> nodes){
        String temp = nodes.get(nodes.size() - 1);
        String[] array = temp.split(",");

        sum += Integer.parseInt(array[1]);

        if (sum == 2 || sum == -2){
            locktag = array[2];
            return true;
        }

        return false;
    }

    private Boolean Addition0(ArrayList<String> nodes){
        if (nodes.size() >= 7){

            String temp7 = nodes.get(nodes.size() - 7);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            return true;
        }
        else{
            return false;
        }
    }

    private Boolean Addition1(ArrayList<String> nodes){
        if (nodes.size() >= 10){
             String temp10 = nodes.get(nodes.size() - 10);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("1")) return false;

            String temp9 = nodes.get(nodes.size() - 9);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 8);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("1")) return false;

            String temp7 = nodes.get(nodes.size() - 7);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

            if (nodes.size() > 10){
                String temp12 = nodes.get(nodes.size() - 11);
                String[] array12 = temp12.split(",");
                if (array12[1].equals("-1")) return true;
                else return false;
            }

            return true;
        }
        else{
            return false;
        }
    }

    private Boolean Addition2(ArrayList<String> nodes){
        if (nodes.size() >= 8){

            String temp8 = nodes.get(nodes.size() - 8);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("1")) return false;

            String temp7 = nodes.get(nodes.size() - 7);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

        }
        else{
            return false;
        }

        if (finddown(nodes)) return true;

        return false;
    }

    private Boolean finddown(ArrayList<String> nodes){
        Integer size = nodes.size();

        if (size < 11) return false;

        Integer count = 0;
        for (int i = 9; i <= nodes.size(); i++){
            String temp = nodes.get(nodes.size() - i);
            String[] array = temp.split(",");
                if (array[1].equals("-1")){
                    count++;
                }
            if (array[1].equals("1")) break;
            if (count > 12) return false;
        }

        if (count >=3 && count <= 12) return true;
        Log.i("count", count.toString());
        return false;
    }

    public void Reset(){
        locked = false;
        sum = 0;
        tag = "-1";
        oldsum = 0;
        locktag = "-1";
    }
}