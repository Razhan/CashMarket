package com.example.cashmarket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.cashmarket.Rules.*;
import com.example.cashmarket.Helper.*;


/**
 * Created by razha_000 on 1/11/2015.
 */
public class Executor {
    private ArrayList<Rules> ruleslist = new ArrayList<Rules>();
    Integer ups, downs;

    public Executor() {
        ups = 0;
        downs = 0;

//        Rules rule13 = new Rule13();
//        Rules rule14 = new Rule14();
//        Rules rule15 = new Rule15();
//        Rules rule16 = new Rule16();
//        Rules rule17 = new Rule17();
//        Rules rule18 = new Rule18();
//        Rules rule21 = new Rule21();
//        Rules rule22 = new Rule22();
//        Rules rule23 = new Rule23();
//        Rules rule24 = new Rule24();
//        Rules rule25 = new Rule25();
//        Rules rule26 = new Rule26();
//        Rules rule27 = new Rule27();
        Rules rule31 = new Rule31();
        Rules rule32 = new Rule32();
        Rules rule33 = new Rule33();
        Rules rule34 = new Rule34();
        Rules rule35 = new Rule35();
        Rules rule36 = new Rule36();
        Rules rule37 = new Rule37();
        Rules rule38 = new Rule38();


//       ruleslist.add(rule13);
//        ruleslist.add(rule14);
//        ruleslist.add(rule15);
//        ruleslist.add(rule16);
//        ruleslist.add(rule17);
//        ruleslist.add(rule18);
//        ruleslist.add(rule21);
//        ruleslist.add(rule22);
//        ruleslist.add(rule23);
//        ruleslist.add(rule24);
//        ruleslist.add(rule25);
//        ruleslist.add(rule26);
        ruleslist.add(rule31);
        ruleslist.add(rule32);
        ruleslist.add(rule33);
        ruleslist.add(rule34);
        ruleslist.add(rule35);
        ruleslist.add(rule36);
        ruleslist.add(rule37);
        ruleslist.add(rule38);


    }

    public Boolean[] Act(ArrayList<String> nodes) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Boolean flag = false;
        Boolean[] res = new Boolean[2];

        res[0] = false;
        res[1] = false;

        for (int i = 0; i < ruleslist.size(); ++i) {

            Boolean[] returnvalue = new Boolean[2];
            returnvalue = ruleslist.get(i).Execute(nodes);
            //result.add("符合规则" + Integer.toString(i + 1);

            if (returnvalue[0]) {
                flag = true;
            }
            if (!returnvalue[0] && returnvalue[1]) {
                res[1] = true;
            }
        }
        res[0] = flag;
        return res;
    }

    public void Reset() {
        ups = 0;
        downs = 0;

        for (int i = 0; i < ruleslist.size(); ++i) {
            ruleslist.get(i).Reset();
        }
    }

    public void Remove(String node) {
        for (int i = 0; i < ruleslist.size(); ++i) {
            ruleslist.get(i).Remove(node);
        }
    }

    public List<Pair<Integer, Integer>> GetResult() {
        List<Pair<Integer, Integer>> pairList = new ArrayList<Pair<Integer, Integer>>();


        for (int i = 0; i < ruleslist.size(); ++i) {

            int l = ruleslist.get(i).up;
            int r = ruleslist.get(i).down;
            Pair pair = new Pair(l, r);
            pairList.add(pair);
        }
        return pairList;
    }
}
