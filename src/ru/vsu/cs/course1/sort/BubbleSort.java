package ru.vsu.cs.course1.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Класс с реализацией сортировки методом пузырька для массивов и списков
 */
public class BubbleSort {

    /**
     * Сортировка методом пузырька
     *
     * @param <T> Произвольный (но ссылочный) тип элементов массива
     * @param data Сортируемый массив типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(T[] data, Comparator<T> c) {
        int size = data.length;
        for (int i = 1; i < size - 1; i++) {
            for (int j = size - 1; j >= i; j--) {
                if (c.compare(data[j - 1], data[j]) > 0) {  // data[j - 1] > data[j]
                    T tmp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = tmp;
                }
            }
        }
    }

    /**
     * Сортировка методом пузырька (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов массива
     * @param data Сортируемый массив типа T
     */
    public static <T extends Comparable<T>> void sort(T[] data) {
        /*
        class TempComparator implements Comparator<T> {
            @Override
            public int compare(T a, T b) {
                return a.compareTo(b);
            }
        }
        sort(data, new TempComparator());
        */
        /*
        sort(data, (a, b) -> a.compareTo(b));
        */
        sort(data, Comparable::compareTo);
    }





    /**
     * Сортировка методом пузырька массива целых (int) чисел (в качестве
     * примера)
     *
     * @param data Сортируемый массив целых (примитивный тип int) чисел
     */
    public static void sort(int[] data) {
        int size = data.length;
        for (int i = 1; i < size - 1; i++) {
            for (int j = size - 1; j >= i; j--) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }





    /**
     * Сортировка методом пузырька
     *
     * @param <T> Произвольный (но ссылочный) тип элементов списка
     * @param data Сортируемый список типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(List<T> data, Comparator<T> c) {
        int size = data.size();
        for (int i = 1; i < size - 1; i++) {
            for (int j = size - 1; j >= i; j--) {
                T a = data.get(j - 1);
                T b = data.get(j);
                if (c.compare(a, b) > 0) {  // a > b
                    data.set(j - 1, b);
                    data.set(j, a);
                }
            }
        }
    }

    /**
     * Сортировка методом пузырька (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов списка
     * @param data Сортируемый список типа T
     */
    public static <T extends Comparable<T>> void sort(List<T> data) {
        sort(data, (a, b) -> a.compareTo(b));
    }
}
