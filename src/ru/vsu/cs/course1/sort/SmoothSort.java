package ru.vsu.cs.course1.sort;

import java.util.Arrays;
import java.util.Comparator;

public class SmoothSort<T>{

    private static int[] leonardoMemo = { 1, 1 };

    public static int leonardo(int n) {       //Вычисление чисел леонардо
        if (n < leonardoMemo.length) {
            if (leonardoMemo[n] != 0) {
                return leonardoMemo[n];
            }
        } else {
            int newLength = leonardoMemo.length * 2;
            leonardoMemo = Arrays.copyOf(leonardoMemo,
                    (newLength > n) ? newLength : (n + 1));
        }
        return leonardoMemo[n] = leonardo(n - 1)
                + leonardo(n - 2) + 1;
    }

    private static <T extends Comparable<? super T>> void sift(T[] data, int head, int exp) {   //просейка
        T t = data[head];
        while (exp > 1) {
            int r = head - 1;
            int l = head - 1 - leonardo(exp - 2);
            if (t.compareTo(data[l]) >= 0 &&
                    t.compareTo(data[r]) >= 0) {
                break;
            }
            if (data[l].compareTo(data[r]) >= 0) {
                data[head] = data[l];
                head = l;
                exp -= 1;
            } else {
                data[head] = data[r];
                head = r;
                exp -= 2;
            }
        }
        data[head] = t;
    }



    private static <T extends Comparable<? super T>> void arrange(T[] v, int head, long frac, int exp) {    //Расставление
        T t = v[head];
        while (frac > 1) {
            int next = head - leonardo(exp);
            if (t.compareTo(v[next]) >= 0) {
                break;
            }
            if (exp > 1) {
                int r = head - 1;
                int l = head - 1 - leonardo(exp - 2);
                if (v[l].compareTo(v[next]) >= 0
                        || v[r].compareTo(v[next]) >= 0) {
                    break;
                }
            }
            v[head] = v[next];
            head = next;
            int trail = Long.numberOfTrailingZeros(frac - 1);
            frac >>>= trail;
            exp += trail;
        }
        v[head] = t;
        sift(v, head, exp);
    }

    public static <T extends Comparable<? super T>> void sort(T[] data) {
        if (data.length == 0) return;
        int head = 0;
        long frac = 0;
        int exp = 0;
        while (head < data.length) {
            if ((frac & 3) == 3) {
                frac >>>= 2;
                exp += 2;
            } else if (exp > 1) {
                frac <<= exp - 1;
                exp = 1;
            } else {
                frac <<= exp;
                exp ^= 1;
            }
            frac++;
            if (exp > 0 && data.length - head - 1 <
                    leonardo(exp - 1) + 1) {
                arrange(data, head, frac, exp);
            }
            sift(data, head, exp);
            head++;
        }
        arrange(data, head - 1, frac, exp);
        while (head > 0) {
            head--;
            if (exp > 1) {
                frac <<= 2;
                frac--;
                exp -= 2;
                arrange(data, head - leonardo(exp) - 1, frac >> 1, exp + 1);
                arrange(data, head - 1, frac, exp);
            } else {
                int trail = Long.numberOfTrailingZeros(frac - 1);
                frac >>= trail;
                exp += trail;
            }
        }
    }

    /**
     * Сортировка методом пузырька (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов массива
     * @param data Сортируемый массив типа T
     */
//    public static <T extends Comparable<T>> void sort(T[] data) {
//        /*
//        class TempComparator implements Comparator<T> {
//            @Override
//            public int compare(T a, T b) {
//                return a.compareTo(b);
//            }
//        }
//        sort(data, new TempComparator());
//        */
//        /*
//        sort(data, (a, b) -> a.compareTo(b));
//        */
//        sort(data, Comparable::compareTo);
//    }
}
