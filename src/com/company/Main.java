package com.company;

public class Main {

    public static void main(String[] argv) {
	// write your code here
        Deap deap = new Deap(10000);
        int[] data = {10,9,8,7,6,5,4,3,2,1,0};
        //add data to deap
        for (int i = 0; i < data.length; i++) {
            deap.add(data[i]);
        }
        // print
        print(deap);

        /**
         * Assignment: isEmpty() , size() , getLow() , getHigh() , add(x) , removeHigh() , removeLow();
         */
        //isEmpty()
        System.out.println("Deap is empty: "+deap.isEmpty());

        //size()
        System.out.println("Deap size: "+deap.size());

        //getLow()
        System.out.println("Deap get the lowest element: "+deap.getLow());

        //getHigh()
        System.out.println("Deap get the highest element: "+deap.getHigh());

        //add(x)
        System.out.println("Deap add '66' :");
        deap.add(66);
        print(deap);

        //removeHigh()
        System.out.println("Deap remove high element :");
        deap.removeHigh();
        print(deap);

        //removeLow()
        System.out.println("Deap remove low element :");
        deap.removeLow();
        print(deap);


    }

    public static void print(Deap deap) {
        int levelNum = 2;
        int currLevel = 0;
        int blank = 8;
        for (int i = 1; i <= deap.getNumOfDeap(); i++) {
            for (int j = 0; j < blank - 1; j++) {
                System.out.print(" ");
            }
            if (currLevel != 0) {
                for (int j = 0; j < blank - 1; j++) {
                    System.out.print(" ");
                }
            }
            if (Integer.toString(deap.getDeap() [i]).length() == 1) {
                System.out.print(" ");
            }
            System.out.print(deap.getDeap()[i]);
            currLevel++;
            if (currLevel == levelNum) {
                System.out.println();
                currLevel = 0;
                levelNum *= 2;
                blank /= 2;
            }
        }
        System.out.println();
        if (currLevel != 0) {
            System.out.println();
        }
    }
}
