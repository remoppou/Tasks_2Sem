package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {

    private MyQueue boxOffice;
    private List<Buyer> buyers;

    public Shop() {
        boxOffice = new MyQueue();
        buyers = new ArrayList<>();
    }

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

    public void addBuyer(int[] data) throws Exception {
        if (!isDataCorrect(data)) {
            throw new Exception("Data is incorrect");
        }
        Buyer buyer = Buyer.createNewBuyer(data);
        if (!isBuyerIncorrect(buyer)) {
            boxOffice.add(buyer);
        } else {
            buyer.setLeftTime(buyer.boxOfficeTime());
            buyers.add(buyer);
        }
    }

    public List<Buyer> resultOfSimulation() {
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