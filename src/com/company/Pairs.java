package com.company;

public class Pairs {
    private int additionNumber;
    private int multiplicationNumber;
    private int a;
    private int b;
    Pairs(int a,int b){
        additionNumber = Math.abs(a) + Math.abs(b);
        multiplicationNumber = Math.abs(a) * Math.abs(b);
        this.a = a;
        this.b = b;
    }
    public String toString(){
        String str = "";
        if (a<0){
            str+="*";
        }
        str += Math.abs(a) + " & ";
        if (b<0){
            str+="*";
        }
        str += Math.abs(b) + ", " + additionNumber + " & " + multiplicationNumber;
        return str;
    }

    public int getAdditionNumber() {
        return additionNumber;
    }
    public int getMultiplicationNumber() {
        return multiplicationNumber;
    }
}
