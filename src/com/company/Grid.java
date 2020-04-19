package com.company;

import java.util.*;

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
    public static Grid Generate(int highestGridNumber,int numberOFPairs,int numberOfBlanks){
        Random rand = new Random(); //instance of random class
        int upperbound = (int) Math.round(Math.sqrt(highestGridNumber));
        ArrayList pN = new ArrayList<>();
        ArrayList bN = new ArrayList<>();
        for (int i = 0;i<numberOFPairs;i++){
            int a = rand.nextInt(upperbound) + 1;
            int b = rand.nextInt(upperbound) + 1;
            pN.add(a);
            pN.add(b);
            bN.add(a+b);
            bN.add(a*b);
        }
        Collections.sort(pN);
        for (int i = 0;i<numberOfBlanks;i++){
            pN.set(rand.nextInt(pN.size()),-1);
        }
        return new Grid(bN,pN);
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
    private boolean testIfPair(int a,int b){
        boolean m = false;
        boolean ad = false;
        for (int i:boxesToGetTo){
            if (a * b == i){
                if (ad){
                    return true;
                }
                m = true;
            }
            if (a + b == i){
                if(m){
                    return true;
                }
                ad = true;
            }
        }
        return false;
    }
    public ArrayList<Pairs> solve(){
        ArrayList<Pairs> pp = new ArrayList<>();
        int xPrev = 0;
        for (int x = 0; x<pairNumbers.size();x++){
            if (pairNumbers.get(x) != xPrev){
                int yPrev = 0;
                for(int y = x + 1; y< pairNumbers.size();y++){
                    if (yPrev != pairNumbers.get(y)){
                        if (testIfPair(pairNumbers.get(x),pairNumbers.get(y))){
                            Pairs nP = new Pairs(pairNumbers.get(x),pairNumbers.get(y));
                            pp.add(nP);
                        }
                    }
                    yPrev = pairNumbers.get(y);
                }
            }
            xPrev = pairNumbers.get(x);
        }
        //check question mark numbers against others
        ArrayList<Integer> doneAlready = new ArrayList<>();
        ArrayList<Integer> BoxDoneAlready = new ArrayList<>();
        for (Pairs p: pp){
            BoxDoneAlready.add(p.getA());
            BoxDoneAlready.add(p.getB());
            doneAlready.add(p.getAdditionNumber());
            doneAlready.add(p.getMultiplicationNumber());
        }
        ArrayList<Integer> missedNumbers = new ArrayList<>();
        for (int i: boxesToGetTo){
            Boolean found = false;
            for (int j:doneAlready){
                if (i==j){
                    found = true;
                }
            }
            if (!found){
                missedNumbers.add(i);
            }
        }
        ArrayList<Integer> missingBox = new ArrayList<>();
        for (int x:pairNumbers){
            boolean found = false;
            for(int y:BoxDoneAlready){
                if(x==y){
                    found = true;
                }
            }
            if (!found){
                missingBox.add(x);
            }
        }
        ArrayList<Pairs> endPairs = getMysteryOnes(missingBox,missedNumbers);
        pp.addAll(endPairs);
        return pp;
    }
    public ArrayList<Pairs> getMysteryOnes(ArrayList<Integer> missingPairN,ArrayList<Integer> missingBoxN){
        ArrayList<Pairs> r = new ArrayList<>();
        ArrayList<QuestionMarkNumber> qmb = questionMarkBoxes;

        ArrayList<Integer> foundBoxNumber = new ArrayList<>();
        for(int mbn = 0;mbn<missingBoxN.size();mbn++){
            for(int mpn:missingPairN){
                int other = missingBoxN.get(mbn)/mpn;
                if (missingBoxN.contains(mpn+other)){
                    for(QuestionMarkNumber qm:qmb){
                        if(qm.testValue(other)){
                            r.add(new Pairs(mpn,0-other));
                            qmb.remove(qm);
                            foundBoxNumber.add(other+mpn);
                            foundBoxNumber.add(other*mpn);
                            break;
                        }
                    }
                }
            }
        }
        missingBoxN.removeAll(foundBoxNumber);
        //got all the one half mystery ones
        for (int qmn = 0;qmn<qmb.size();qmn++){
            for(int i:qmb.get(qmn).getAll()){
                for (int qmnj = qmn+1;qmnj<qmb.size();qmnj++){
                    for(int j:qmb.get(qmnj).getAll()){
                        if(missingBoxN.contains(i+j)){
                            if(missingBoxN.contains(i*j)){
                                r.add(new Pairs(0-i,0-j));
                            }
                        }
                    }
                }
            }
        }
        return r;
    }
}
