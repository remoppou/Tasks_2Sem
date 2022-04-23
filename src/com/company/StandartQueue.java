package com.company;

// здесь реализация с использованием реализации стека / очереди, которая уже есть в стандартной библиотеки языка Java.

import util.ArrayUtils;

import java.util.*;

public class StandartQueue {

    private static Queue boxOffice = new ArrayDeque();
    private static List<Buyer> buyers = new ArrayList<>();

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

    public static void addBuyer(int[] data) throws Exception {
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

    public static List<Buyer> resultOfSimulation() {
        int size = boxOffice.size();
        int time = 0;
        for (int i = buyers.size(); i < size; i++) {
            try {
                Buyer buyer = (Buyer) boxOffice.peek();
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

    public static void startSimulation(String fileName){
        int[][] arr = ArrayUtils.readIntArray2FromFile(fileName);
        for(int i = 0; i < arr.length; i++){
            try {
                addBuyer(arr[i]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage()+" in line "+i);
            }
        }
        showResult(resultOfSimulation());
    }

    public static void showResult(List<Buyer> buyers){
        System.out.println("Начало симуляции!");
        System.out.println();
        for(Buyer buyer: buyers){
            System.out.println("Покупатель со следующими данными:\nВремя прихода в магазин: "+buyer.getS()+
                    "\nВремя, потраченное на выбор товаров: "+buyer.getT()+
                    "\nКоличество товаров: "+buyer.getN()+"\nПокинет магазин в "+buyer.getLeftTime());
            System.out.println();
        }
        System.out.println("Конец симуляции!");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите название файла: ");
        startSimulation(scn.nextLine());
    }

}
