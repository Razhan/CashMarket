package com.example.over1;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public class Rule9 extends Rules{
    public Rule9(){

        locked = false;
        count1 = 0;
        count2 = 0;
        flag1 = false;
        flag2 = false;
        ftag1 = "-1";
        ftag2 = "-1";

    }

    private Integer count1, count2;
    private Boolean flag1, flag2;
    private String ftag1, ftag2;



    public Boolean[] Execute(ArrayList<String> nodes){
        Log.i("Execute", "Rule1 executed!");
        Boolean[] res =  new Boolean[2];
        res[0] = false;
        res[1] = false;

        if(locked){
            Boolean l =  LockedProc(nodes);
            if (l){
                //Log.i("Execute", "符合规则!");
                locked = false;
                res[0] = false;
                res[1] = true;
            }
        }else {
            if (flag1) count1++;
            Log.i("count1", count1.toString());

            if (flag2) count2++;
            //Log.i("count2", count2.toString());

            Boolean m =  MeetRule(nodes);
            if (m){
                //Log.i("Execute", "符合规则!");
                res[0] = true;
                res[1] = true;
            }
        }
        return res;
    }

    public Boolean MeetRule(ArrayList<String> nodes){

        if (nodes.size() >= 4){
            Boolean a6 = Addition6(nodes);
            Boolean a7 = Addition7(nodes);

            Log.i("a7", a7.toString());
            String temp3 = nodes.get(nodes.size() - 4);
            String[] array3 = temp3.split(",");
            if (!array3[1].equals("-1")) return false;

            String temp4 = nodes.get(nodes.size() - 3);
            String[] array4 = temp4.split(",");
            if (!array4[1].equals("-1")) return false;

            String temp5 = nodes.get(nodes.size() - 2);
            String[] array5 = temp5.split(",");
            if (!array5[1].equals("1")) return false;

            String temp6 = nodes.get(nodes.size() - 1);
            String[] array6 = temp6.split(",");
            if (!array6[1].equals("-1")) return false;

            Boolean a0 = Addition0(nodes);
            Boolean a1 = Addition1(nodes);
            Boolean a2 = Addition2(nodes);
            Boolean a3 = Addition3(nodes);
            Boolean a4 = Addition4(nodes);
            Boolean a5 = Addition5(nodes);

            if (!a0 && !a1 && !a2 && !a3 && !a4 && !a5 && !a6 && !a7){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Boolean LockedProc(ArrayList<String> nodes){

        return false;
    }

    private Boolean Addition0(ArrayList<String> nodes){
        if (nodes.size() >= 5){

            String temp7 = nodes.get(nodes.size() - 5);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("-1")) return false;

            return true;
        }
        else{
            return false;
        }
    }

//    private Boolean Addition1(ArrayList<String> nodes){
//        if (nodes.size() >= 7){
//
//            String temp9 = nodes.get(nodes.size() - 7);
//            String[] array9 = temp9.split(",");
//            if (!array9[1].equals("1")) return false;
//
//            String temp8 = nodes.get(nodes.size() - 6);
//            String[] array8 = temp8.split(",");
//            if (!array8[1].equals("1")) return false;
//
//            String temp7 = nodes.get(nodes.size() - 5);
//            String[] array7 = temp7.split(",");
//            if (!array7[1].equals("1")) return false;
//
//            if (nodes.size() > 7){
//                String temp12 = nodes.get(nodes.size() - 8);
//                String[] array12 = temp12.split(",");
//                if (array12[1].equals("-1")) return true;
//                else return false;
//            }
//
//            return true;
//        }
//        else{
//            return false;
//        }
//    }


    private Boolean Addition1(ArrayList<String> nodes){

        if (findup(nodes)) return true;

        return false;
    }

    private Boolean findup(ArrayList<String> nodes){
        Integer size = nodes.size();

        if (size < 7) return false;

        Integer count = 0;
        for (int i = 5; i <= nodes.size(); i++){
            String temp = nodes.get(nodes.size() - i);
            String[] array = temp.split(",");
            if (array[1].equals("1")){
                count++;
            }
            if (array[1].equals("-1")) break;
            if (count > 12) return false;
        }

        if (count >=3 && count <= 12) return true;
        Log.i("count", count.toString());
        return false;
    }

    private Boolean Addition2(ArrayList<String> nodes){
        if (nodes.size() >= 9){

            String temp11 = nodes.get(nodes.size() - 9);
            String[] array11 = temp11.split(",");
            if (!array11[1].equals("-1")) return false;

            String temp10 = nodes.get(nodes.size() - 8);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("-1")) return false;

            String temp9 = nodes.get(nodes.size() - 7);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 6);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("-1")) return false;

            String temp7 = nodes.get(nodes.size() - 5);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            if (nodes.size() > 9){
                String temp12 = nodes.get(nodes.size() - 10);
                String[] array12 = temp12.split(",");
                if (array12[1].equals("1")) return true;
                else return false;
            }

            return true;
        }
        else{
            return false;
        }
    }

    private Boolean Addition3(ArrayList<String> nodes){
        if (nodes.size() >= 9){

            String temp11 = nodes.get(nodes.size() - 9);
            String[] array11 = temp11.split(",");
            if (!array11[1].equals("-1")) return false;

            String temp10 = nodes.get(nodes.size() - 8);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("-1")) return false;

            String temp9 = nodes.get(nodes.size() - 7);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 6);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("1")) return false;

            String temp7 = nodes.get(nodes.size() - 5);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            if (nodes.size() > 9){
                String temp12 = nodes.get(nodes.size() - 10);
                String[] array12 = temp12.split(",");
                if (array12[1].equals("1")) return true;
                else return false;
            }

            return true;
        }
        else{
            return false;
        }
    }

    private Boolean Addition4(ArrayList<String> nodes){
        if (nodes.size() >= 7){

            String temp9 = nodes.get(nodes.size() - 7);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("-1")) return false;

            String temp8 = nodes.get(nodes.size() - 6);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("1")) return false;

            String temp7 = nodes.get(nodes.size() - 5);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            if (nodes.size() > 7){
                String temp12 = nodes.get(nodes.size() - 8);
                String[] array12 = temp12.split(",");
                if (array12[1].equals("1")) return true;
                else return false;
            }

            return true;
        }
        else{
            return false;
        }
    }

    private Boolean Addition5(ArrayList<String> nodes){
        if (nodes.size() >= 8){

            String temp10 = nodes.get(nodes.size() - 8);
            String[] array10 = temp10.split(",");
            if (!array10[1].equals("1")) return false;

            String temp9 = nodes.get(nodes.size() - 7);
            String[] array9 = temp9.split(",");
            if (!array9[1].equals("1")) return false;

            String temp8 = nodes.get(nodes.size() - 6);
            String[] array8 = temp8.split(",");
            if (!array8[1].equals("-1")) return false;

            String temp7 = nodes.get(nodes.size() - 5);
            String[] array7 = temp7.split(",");
            if (!array7[1].equals("1")) return false;

            if (nodes.size() > 8){
                String temp12 = nodes.get(nodes.size() - 9);
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

    private Boolean Addition6(ArrayList<String> nodes){
        if (nodes.size() >= 6){

            if (!flag1) {
                //Log.i("count1", count1.toString());

                String temp12 = nodes.get(nodes.size() - 6);
                String[] array12 = temp12.split(",");
                if (!array12[1].equals("1")) return false;

                String temp11 = nodes.get(nodes.size() - 5);
                String[] array11 = temp11.split(",");
                if (!array11[1].equals("1")) return false;

                String temp10 = nodes.get(nodes.size() - 4);
                String[] array10 = temp10.split(",");
                if (!array10[1].equals("1")) return false;

                String temp9 = nodes.get(nodes.size() - 3);
                String[] array9 = temp9.split(",");
                if (!array9[1].equals("-1")) return false;

                String temp8 = nodes.get(nodes.size() - 2);
                String[] array8 = temp8.split(",");
                if (!array8[1].equals("-1")) return false;

                String temp7 = nodes.get(nodes.size() - 1);
                String[] array7 = temp7.split(",");
                if (!array7[1].equals("1")) return false;

                if (nodes.size() > 6){
                    String temp1 = nodes.get(nodes.size() - 7);
                    String[] array1 = temp1.split(",");
                    if (array1[1].equals("-1")){
                        flag1 = true;
                        return true;
                    }
                    else return false;
                }

                flag1 = true;
                return true;
            } else {
                Log.i("count1", count1.toString());
                if (count1 > 12) {
                    flag1 = false;
                    String temp = nodes.get(nodes.size() - 1);
                    String[] array = temp.split(",");

                    ftag1 = array[2];

                    count1 = 0;
                    return false;
                }
                return true;
            }
        }
        else{
            return false;
        }
    }

    private Boolean Addition7(ArrayList<String> nodes){
        if (nodes.size() >= 9){

            if (!flag2) {
                String temp12 = nodes.get(nodes.size() - 9);
                String[] array12 = temp12.split(",");
                if (!array12[1].equals("-1")) return false;

                String temp11 = nodes.get(nodes.size() - 8);
                String[] array11 = temp11.split(",");
                if (!array11[1].equals("-1")) return false;

                String temp10 = nodes.get(nodes.size() - 7);
                String[] array10 = temp10.split(",");
                if (!array10[1].equals("-1")) return false;

                String temp9 = nodes.get(nodes.size() - 6);
                String[] array9 = temp9.split(",");
                if (!array9[1].equals("1")) return false;

                String temp8 = nodes.get(nodes.size() - 5);
                String[] array8 = temp8.split(",");
                if (!array8[1].equals("1")) return false;

                String temp7 = nodes.get(nodes.size() - 4);
                String[] array7 = temp7.split(",");
                if (!array7[1].equals("-1")) return false;

                String temp6 = nodes.get(nodes.size() - 3);
                String[] array6 = temp6.split(",");
                if (!array6[1].equals("-1")) return false;

                String temp5 = nodes.get(nodes.size() - 2);
                String[] array5 = temp5.split(",");
                if (!array5[1].equals("1")) return false;

                String temp4 = nodes.get(nodes.size() - 1);
                String[] array4 = temp4.split(",");
                if (!array4[1].equals("-1")) return false;

                if (nodes.size() > 9){
                    String temp1 = nodes.get(nodes.size() - 10);
                    String[] array1 = temp1.split(",");
                    if (array1[1].equals("1")){
                        flag2 = true;
                        return true;
                    }
                    else return false;
                }


                flag2 = true;
                return true;
            } else {
                if (count2 > 30) {
                    flag2 = false;

                    String temp = nodes.get(nodes.size() - 1);
                    String[] array = temp.split(",");

                    ftag1 = array[2];

                    count2 = 0;
                    return false;
                }
                return true;
            }
        }
        else{
            return false;
        }
    }

    public void Reset(){
        locked = false;
        count1 = 0;
        count2 = 0;
        flag1 = false;
        flag2 = false;
        ftag1 = "-1";
        ftag2 = "-1";
    }

    public void Remove(String node){
        String[] array = node.split(",");

        if (flag1){
            if (count1 > 0) count1--;
            else flag1 = false;
        } else {
             if ( ftag1.equals(array[2])){
                 flag1 = true;
                 count1 = 12;
                 Log.i("count1", count1.toString());

             }
        }

        if (flag2){
            if (count2 > 0) count2--;
            else flag2 = false;
        } else {
            if ( ftag2.equals(array[2])){
                flag2 = true;
                count1 = 30;
            }
        }
    }
}