package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid g = new Grid(new ArrayList<>(Arrays.asList(72, 38, 19, 153, 17, 140, 23, 52, 36, 26, 132, 48, 39, 168, 31, 13)), new ArrayList<>(Arrays.asList(1,-1,3,4,4,7,9,9,11,12,13,16,17,24,-1,-1)));
        //g.print();
        for (Pairs p: g.wordOutAllPairs()){
            System.out.println(p.toString());
        }

    }


}
