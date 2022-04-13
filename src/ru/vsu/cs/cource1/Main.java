package ru.vsu.cs.cource1;

import java.util.Comparator;

public class Main {

    //(*) Реализовать для списка пузырьковую сортировку:
    //https://ru.wikipedia.org/wiki/Сортировка_пузырьком).
    //Новых объектов ListNode  / ListItem – не создавать.
    //Обмениваться должны сами элементы, а не их значения (требование, конечно, нелогичное,
    // исключительно для того, чтобы задачу сделать более сложной / интересной).


    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.addLast(140);
        list.addFirst(50);
        list.addLast(10);
        list.addLast(10);
        list.addLast(30);
        list.addLast(40);
        list.addFirst(100);
        list.addFirst(50);
        list.addFirst(200);
        list.addLast(2);

        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        list.bubbleSort(comparator);
        list.addLast(777);
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();
    }
}
