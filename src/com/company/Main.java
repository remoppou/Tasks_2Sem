package com.company;

import util.ArrayUtils;

import java.util.*;

import static com.company.StandartQueue.*;

/**    Смоделировать работу магазина. Отсчет времени начинается с 0, все время дискретно (т.е. события случаются только в
 «целочисленные» моменты времени, длительность любого действия также «целочисленна»). Покупатели приходят в
 магазин, набирают товары и идут на кассу. Для каждого покупателя известно, когда он пришел в магазин (S),
 сколько по времени выбирал товары (T), сколько товаров выбрал (N, будем считать, что время обслуживания на
 кассе пропорционально кол-ву набранных товаров и также составляет N). Когда покупатель подходит к кассе,
 если касса обслуживает другого покупателя, то подошедший становится в очередь. Если к кассе одновременно
 подходят несколько покупателей, то они могут встать в очередь в случайном порядке.
 Для указанных входных данных для каждого покупателя найти момент времени, когда он покинет (может покинуть)
 магазин.
 «Рисовать» анимацию не требуется, достаточно, чтобы можно было ввести входные данные (или загрузить из файла)
 и просмотреть результат моделирования. */
//Path PC: D:\IdeaProjects\Oop_Task3_num24-Shop-\src\Test.txt
//Path NB: D:\Projects(IDEA)\Oop_Task3_num24-Shop-\src\Test.txt


public class Main {

    public static void startSimulationMyQueue(String fileName){
        int[][] arr = ArrayUtils.readIntArray2FromFile(fileName);
        Shop shop = new Shop();
        for(int i = 0; i < arr.length; i++){
            try {
                shop.addBuyer(arr[i]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage()+" in line "+i);
            }
        }
        showResult(shop.resultOfSimulation());
    }

    public static void startSimulationStandartQueue(String fileName){
        StandartQueue standartQueue = new StandartQueue();
        int[][] arr = ArrayUtils.readIntArray2FromFile(fileName);
        for(int i = 0; i < arr.length; i++){
            try {
                standartQueue.addBuyer(arr[i]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage()+" in line "+i);
            }
        }
        showResult(standartQueue.resultOfSimulation());
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
        String path = scn.nextLine();
        System.out.println("Модель работы магазина, реализованная собственной очередью: ");
        startSimulationMyQueue(path);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Модель работы магазина, реализованная встроенной очередью: ");
        startSimulationStandartQueue(path);
    }
}
