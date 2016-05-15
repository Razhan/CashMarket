package com.example.cashmarket.Rules;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by razha_000 on 1/11/2015.
 */
public abstract class Rules {
    public abstract Boolean[]  Execute(ArrayList<String> nodes);
    public abstract void Reset();
    //public abstract void CheckResult(String str, Map<Integer, Integer[]> successrate);
    public Boolean locked;
    public String tag;
    public String lockstart;
    public String lockend;

    public Integer up, down;

    public abstract Boolean MeetRule(ArrayList<String> nodes);
    public  abstract Boolean LockedProc(ArrayList<String> nodes);
    public  abstract void Remove(String nodes);

}