package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop<T> {

    private SimpleTask boxOffice = new SimpleTask();
    private List<Buyer> buyers = new ArrayList<>();

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

    public void addBuyer(int[] data, Comparator<Buyer> comparator) throws Exception {
        if (!isDataCorrect(data)) {
            throw new Exception("Data is incorrect");
        }
        Buyer buyer = Buyer.createNewBuyer(data);
        if (!isBuyerIncorrect(buyer)) {
            boxOffice.addElement(buyer, comparator);
        } else {
            buyer.setLeftTime(buyer.boxOfficeTime());
            buyers.add(buyer);
        }
    }

    public List<Buyer> resultOfSimulation() {
        int size = boxOffice.getSize();
        int time = 0;
        for (int i = buyers.size(); i < size; i++) {
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
}