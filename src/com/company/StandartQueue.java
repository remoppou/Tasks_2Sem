package com.company;

// здесь реализация с использованием реализации стека / очереди, которая уже есть в стандартной библиотеки языка Java.

import util.ArrayUtils;

import java.util.*;

public class StandartQueue {

    private static boolean isBuyerIncorrect(Buyer buyer) {
        int N = buyer.getN();
        return (N == 0);
    }

    private static boolean isDataCorrect(int[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i] < 0) {
                return false;
            }
        }
        return arr.length == 3;
    }

    public static void addBuyer(int[] data, List<Buyer> inShop, List<Buyer> buyers) throws Exception {
        if (!isDataCorrect(data)) {
            throw new Exception("Data is incorrect");
        }
        Buyer buyer = Buyer.createNewBuyer(data);
        if (!isBuyerIncorrect(buyer)) {
            inShop.add(buyer);
        } else {
            buyer.setLeftTime(buyer.boxOfficeTime());
            buyers.add(buyer);
        }
    }

    public static List<Buyer> resultOfSimulation(Queue<Buyer> boxOffice, List<Buyer> buyers) {
        int size = boxOffice.size();
        int time = 0;
        for (int i = 0; i < size; i++) {
            try {
                Buyer buyer = boxOffice.poll();
                if (buyer.boxOfficeTime() >= time) {
                    buyer.setLeftTime(buyer.boxOfficeTime() + buyer.getN());
                } else {
                    buyer.setLeftTime(time + buyer.getN());
                }
                time = buyer.getLeftTime();
                buyers.add(buyer);
            } catch (Exception e) {
                break;
            }
        }
        return buyers;
    }

    public static Queue<Buyer> sortAndAdd(List<Buyer> inShop, Queue<Buyer> boxOffice) {
        inShop.sort(Comparator.comparing(Buyer::boxOfficeTime));
        for (int i = 0; i < inShop.size(); i++) {
            boxOffice.add(inShop.get(i));
        }
        return boxOffice;
    }

}
