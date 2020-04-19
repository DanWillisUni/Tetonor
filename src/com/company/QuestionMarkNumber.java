package com.company;

import java.util.ArrayList;

public class QuestionMarkNumber {
    private int min;//inclusive
    private int max;//inclusive
    QuestionMarkNumber(int min,int max){
        this.min = min;
        this.max = max;
    }
    public boolean testValue(int i){
        return (i>=min&&i<=max);
    }
    public void setMin(int min){
        this.min = min;
    }
    public void setMax(int max){
        this.max=max;
    }
    public String toString(){
        return min + "-" + max;
    }
    public ArrayList<Integer> getAll(){
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = min; i <= max;i++){
            r.add(i);
        }
        return r;
    }
}
