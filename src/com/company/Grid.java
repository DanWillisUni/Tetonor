package com.company;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Integer> boxesToGetTo;
    private List<Integer> pairNumbers;
    private ArrayList<QuestionMarkNumber>  questionMarkBoxes;
    Grid(ArrayList<Integer> boxTGT,ArrayList<Integer> pairN){
        boxTGT.sort(Integer::compareTo);
        boxesToGetTo = boxTGT;
        pairNumbers = new ArrayList<>();
        questionMarkBoxes = new ArrayList<>();
        int min = 1;
        for (int i = 0 ; i<pairN.size(); i++){
            if (pairN.get(i) == -1){//if its a question box
                int max=0;
                for (int j = i + 1;j < pairN.size();j++){
                    if (pairN.get(j) != -1){
                        max = pairN.get(j);
                        break;
                    }
                }
                if (max == 0){
                    max = boxesToGetTo.get(15) - 1;
                }
                questionMarkBoxes.add(new QuestionMarkNumber(min,max));
            } else {
                pairNumbers.add(pairN.get(i));
                min = pairN.get(i);//change the min back
            }
        }
    }
    public int getBoxN(int i){
        return boxesToGetTo.get(i);
    }
    public int getPairN(int i){
        return pairNumbers.get(i);
    }
    public QuestionMarkNumber getQMB(int i){
        return questionMarkBoxes.get(i);
    }
    public void print(){
        System.out.println("Products to make:");
        for(int i = 0;i < 16;i++){
            if (i == 15){
                System.out.println(getBoxN(i) + "\nNumbers to make pairs with:");
            } else {
                System.out.print(getBoxN(i) + ", ");
            }
        }
        for(int i = 0;i < pairNumbers.size();i++){
            if (i == pairNumbers.size() - 1){
                System.out.println(getPairN(i) + "\nQuestion Mark Numbers:");
            } else {
                System.out.print(getPairN(i) + ", ");
            }
        }
        for(int i = 0;i < questionMarkBoxes.size();i++){
            if (i == questionMarkBoxes.size() - 1){
                System.out.println(getQMB(i).toString());
            } else {
                System.out.print(getQMB(i).toString() + ", ");
            }
        }

    }
}
