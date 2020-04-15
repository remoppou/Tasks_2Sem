package ru.vsu.cs.course1.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Класс с реализацией сортировки простым выбором для массивов и списков
 */
public class SelectionSort {

    /**
     * Сортировка простым выбором
     *
     * @param <T> Произвольный (но ссылочный) тип элементов массива
     * @param data Сортируемый массив типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(T[] data, Comparator<T> c) {
        int size = data.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (c.compare(data[j], data[minIndex]) < 0) {  // data[j] < data[minIndex]
                    minIndex = j;
                }
            }
            // обмер элементов [i] и [minIndex]
            // (можно добавить дополнительную проверку, что minIndex != i)
            T tmp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = tmp;
        }
    }

    /**
     * Сортировка простым выбором (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов массива
     * @param data Сортируемый массив типа T
     */
    public static <T extends Comparable<T>> void sort(T[] data) {
        sort(data, Comparable::compareTo);
    }





    /**
     * Сортировка простым выбором массива целых (int) чисел (в качестве примера)
     *
     * @param data Сортируемый массив целых (примитивный тип int) чисел
     */
    public static void sort(int[] data) {
        int size = data.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            // обмер элементов [i] и [minIndex]
            // (можно добавить дополнительную проверку, что minIndex != i)
            int tmp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = tmp;
        }
    }





    /**
     * Сортировка простым выбором
     *
     * @param <T> Произвольный (но ссылочный) тип элементов списка
     * @param data Сортируемый список типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(List<T> data, Comparator<T> c) {
        int size = data.size();
        for (int i = 0; i < size - 1; i++) {
            T value = data.get(i);
            int minIndex = i;
            T minValue = value;
            for (int j = i + 1; j < size; j++) {
                T tmp = data.get(j);
                if (c.compare(tmp, minValue) < 0) {  // value < min
                    minIndex = j;
                    minValue = tmp;
                }
            }
            // обмер элементов [i] и [minIndex]
            // (можно добавить дополнительную проверку, что minIndex != i)
            data.set(i, minValue);
            data.set(minIndex, value);
        }
    }

    /**
     * Сортировка простым выбором (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов списка
     * @param data Сортируемый список типа T
     */
    public static <T extends Comparable<T>> void sort(List<T> data) {
        sort(data, (a, b) -> a.compareTo(b));
    }
}
