package com.company;

public class Buyer {

    private int S;
    private int T;
    private int N;
    private int leftTime = 0;

    public int getS() {
        return S;
    }

    public int getN() {
        return N;
    }

    public int getT() {
        return T;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public int boxOfficeTime() {
        return S + T;
    }

    public static Buyer createNewBuyer(int[] data) throws Exception {
        if (isBuyerDataCorrect(data)) {
            Buyer newBuyer = new Buyer();
            newBuyer.S = data[0];
            newBuyer.T = data[1];
            newBuyer.N = data[2];
            return newBuyer;
        } else {
            throw new Exception("Данные покупателя некорректны");
        }
    }

    private static boolean isBuyerDataCorrect(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
