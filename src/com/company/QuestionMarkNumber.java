package com.company;

public class QuestionMarkNumber {
    private int min;
    private int max;
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
}
