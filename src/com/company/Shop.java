package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {

    private boolean isBuyerIncorrect(Buyer buyer) {
        int N = buyer.getN();
        return (N == 0);
    }

    private boolean isDataCorrect(int[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i] < 0) {
                return false;
            }
        }
        return arr.length == 3;
    }

    public void addBuyer(int[] data, Comparator<Buyer> comparator, MyQueue boxOffice, List<Buyer> buyers) throws Exception {
        if (!isDataCorrect(data)) {
            throw new Exception("Data is incorrect");
        }
        Buyer buyer = Buyer.createNewBuyer(data);
        if (!isBuyerIncorrect(buyer)) {
            boxOffice.add(buyer, comparator);
        } else {
            buyer.setLeftTime(buyer.boxOfficeTime());
            buyers.add(buyer);
        }
    }

    public List<Buyer> resultOfSimulation(MyQueue boxOffice, List<Buyer> buyers) {
        int size = boxOffice.count();
        int time = 0;
        for (int i = 0; i < size; i++) {
            try {
                Buyer buyer = boxOffice.remove();
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
}