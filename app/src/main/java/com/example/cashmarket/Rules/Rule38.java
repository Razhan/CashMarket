package com.example.cashmarket.Rules;

/**
 * Created by razha_000 on 2/1/2015.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Rule38 extends Rules{
    public Rule38(){

        locked = false;
        lockstart = "-1";
        lockend = "-1";
        up = 0;
        down = 0;
    }

    private Integer sum;
    public Boolean[] Execute(ArrayList<String> nodes){
        //Log.i("Execute", "Rule1 executed!");
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
                //Log.i("Execute", "符合规则!");
                res[0] = true;
                res[1] = true;
                locked = true;

                String temp = nodes.get(nodes.size() - 1);
                String[] array = temp.split(",");
                lockstart = array[2];
            }
        }
        return res;
    }

    public void Remove(String node){
        String[] array = node.split(",");

        if (!locked){
            if (lockend.equals(array[2])){
                locked = true;
                lockend = "-1";
                if (array[1].equals("-1")) up--;
                else down--;
            }
        } else {
            if (lockstart.equals(array[2])) {
                locked = false;
                lockstart = "-1";
            }
        }
    }

    public Boolean MeetRule(ArrayList<String> nodes){
        if (nodes.size() >= 11){

            String temp0 = nodes.get(nodes.size() - 11);
            String[] array0 = temp0.split(",");
            if (!array0[1].equals("1")) return false;

            String temp1 = nodes.get(nodes.size() - 10);
            String[] array1 = temp1.split(",");
            if (!array1[1].equals("1")) return false;

            String temp2 = nodes.get(nodes.size() - 9);
            String[] array2 = temp2.split(",");
            if (!array2[1].equals("1")) return false;

            String temp3 = nodes.get(nodes.size() - 8);
            String[] array3 = temp3.split(",");
            if (!array3[1].equals("1")) return false;

            String temp4 = nodes.get(nodes.size() - 7);
            String[] array4 = temp4.split(",");
            if (!array4[1].equals("1")) return false;

            String temp5 = nodes.get(nodes.size() - 6);
            String[] array5 = temp5.split(",");
            if (!array5[1].equals("1")) return false;

            String temp6 = nodes.get(nodes.size() - 5);
            String[] array6 = temp6.split(",");
            if (!array6[1].equals("1")) return false;

            String temp7 = nodes.get(nodes.size() - 4);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 3);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("-1")) return false;

            String temp9 = nodes.get(nodes.size() - 2);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("1")) return false;

            String temp10 = nodes.get(nodes.size() - 1);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("-1")) return false;

            Boolean a0 = Addition0(nodes);

            if (!a0){
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
        if (array[1].equals("-1")){
            up++;
            Log.i("up", up.toString());

            return true;
        }
        lockend = array[2];

        down++;
        Log.i("down", down.toString());

        return false;
    }

    private Boolean Addition0(ArrayList<String> nodes){
        if (nodes.size() >= 12){

            String temp7 = nodes.get(nodes.size() - 12);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            return true;
        }
        else{
            return false;
        }
    }


    public void Reset(){
        locked = false;
        lockstart = "-1";
        lockend = "-1";
        up = 0;
        down = 0;
    }
}
