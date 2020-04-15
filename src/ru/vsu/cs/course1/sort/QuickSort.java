package ru.vsu.cs.course1.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Класс с реализацией быстрой сортировки (QuickSort) для массивов и списков
 */
public class QuickSort {

    /**
     * Разделение - составная часть быстрой сортировки
     *
     * @param <T> произвольный (но ссылочный) тип элементов массива
     * @param data Массив типа T
     * @param left Левая граница (индекс первого элемента) диапазона
     * @param right Первый элемент за правой границей диапазона
     * @param c Компаратор для сравнения элементов
     * @return индекс элемента, слева которого все элементы &lt;= данного, а
     * справа &gt;= данного
     */
    private static <T> int partition(T[] data, int left, int right, Comparator<T> c) {
        int l = left;
        int r = right - 1;  // т.к. right не входит в диапазон

        T x = data[(l + r) / 2];
        while (l <= r) {
            while (c.compare(data[l], x) < 0) {  // while (data[left] < x)
                l++;
            }
            while (c.compare(data[r], x) > 0) {  // while (data[right] > x)
                r--;
            }
            // в этом месте:
            //   l - индекс первого элемента >= x (возможно указывает непосредственно на x)
            //   r - индекс последнего элемента <= x (возможно указывает непосредственно на x)
            if (l <= r) {
                // обмен элементов [r] и [l]
                T tmp = data[r];
                data[r] = data[l];
                data[l] = tmp;

                l++;
                r--;
            }
            // в этом месте можно утверждать:
            //   если l <= r то:
            //     слева [l] все элементы <= x
            //     справа [r] все элементы >= x
            //   иначе (возврат с результатом l):
            //     l - индекс x или элемента, непосредственно следующего за х
        }

        // так как при обмене увеличиваем l, может оказаться, что l == right, который не входит в сортируемый диапазон
        // (возможно, если только в качестве опорного элемента брать последний элемент из диапазона)
        if (l == right) {
            l--;
        }
        return l;
    }

    /**
     * Быстрая сортировка части массива
     *
     * @param <T> Тип элементов массива
     * @param data Массив типа T
     * @param left Левая граница (индекс первого элемента) сортируемого диапазона
     * @param right Первый элемент за правой границей сортируемого диапазона
     * @param c Компаратор (задает правила сравнения элементов)
     */
    private static <T> void sort(T[] data, int left, int right, Comparator<T> c) {
        if (left < right - 1) {
            int xIndex = partition(data, left, right, c);
            sort(data, left, xIndex, c);
            sort(data, xIndex, right, c);
        }
    }

    /**
     * Быстрая сортировка массива (QuickSort)
     *
     * @param <T> Тип элементов массива
     * @param data Массив типа T
     * @param c Компаратор (задает правила сравнения элементов)
     */
    public static <T> void sort(T[] data, Comparator<T> c) {
        sort(data, 0, data.length, c);
    }

    /**
     * Быстрая сортировка (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов массива
     * @param data Сортируемый массив типа T
     */
    public static <T extends Comparable<T>> void sort(T[] data) {
        sort(data, Comparable::compareTo);
    }





    /**
     * Разделение - составная часть быстрой сортировки
     *
     * @param data Массив типа int
     * @param left Левая граница (индекс первого элемента) диапазона
     * @param right Первый элемент за правой границей диапазона
     * @return индекс элемента, слева которого все элементы &lt;= данного, а
     * справа &gt;= данного
     */
    private static int partition(int[] data, int left, int right) {
        int l = left;
        int r = right - 1;  // т.к. right не входит в диапазон

        int x = data[(l + r) / 2];
        while (l <= r) {
            while (data[l] < x) {
                l++;
            }
            while (data[r] > x) {
                r--;
            }
            // в этом месте:
            //   l - индекс первого элемента >= x (возможно указывает непосредственно на x)
            //   r - индекс последнего элемента <= x (возможно указывает непосредственно на x)
            if (l <= r) {
                // обмен элементов [r] и [l]
                int tmp = data[r];
                data[r] = data[l];
                data[l] = tmp;

                l++;
                r--;
            }
            // в этом месте можно утверждать:
            //   если l <= r то:
            //     слева [l] все элементы <= x
            //     справа [r] все элементы >= x
            //   иначе (возврат с результатом l):
            //     l - индекс x или элемента, непосредственно следующего за х
        }

        // так как при обмене увеличиваем l, может оказаться, что l == right, который не входит в сортируемый диапазон
        // (возможно, если только в качестве опорного элемента брать последний элемент из диапазона)
        if (l == right) {
            l--;
        }

        return l;
    }

    /**
     * Быстрая сортировка части массива
     *
     * @param data Массив типа int
     * @param left Левая граница (индекс первого элемента) сортируемого диапазона
     * @param right Первый элемент за правой границей сортируемого диапазона
     */
    private static void sort(int[] data, int left, int right) {
        if (left < right - 1) {
            int xIndex = partition(data, left, right);
            sort(data, left, xIndex);
            sort(data, xIndex, right);
        }
    }

    /**
     * Быстрая сортировка массива (QuickSort) (в качестве примера)
     *
     * @param data Массив типа int
     */
    public static void sort(int[] data) {
        sort(data, 0, data.length);
    }





    /**
     * Разделение - составная часть быстрой сортировки
     *
     * @param <T> произвольный (но ссылочный) тип элементов списка
     * @param data Список типа T
     * @param left Левая граница (индекс первого элемента) диапазона
     * @param right Первый элемент за правой границей диапазона
     * @param c Компаратор для сравнения элементов
     * @return индекс элемента, слева которого все элементы &lt;= данного,
     * а справа &gt;= данного
     */
    private static <T> int partition(List<T> data, int left, int right, Comparator<T> c) {
        int l = left;
        int r = right - 1;  // т.к. right не входит в диапазон

        T x = data.get((l + r) / 2);
        while (l <= r) {
            T leftValue, rightValue;
            while (c.compare(leftValue = data.get(l), x) < 0) {  // while (data[left] < x)
                l++;
            }
            while (c.compare(rightValue = data.get(r), x) > 0) {  // while (data[right] > x)
                r--;
            }
            // в этом месте:
            //   l - индекс первого элемента >= x (возможно указывает непосредственно на x)
            //   r - индекс последнего элемента <= x (возможно указывает непосредственно на x)
            if (l <= r) {
                // обмен элементов [r] и [l]
                data.set(r, leftValue);
                data.set(l, rightValue);

                l++;
                r--;
            }
            // в этом месте можно утверждать:
            //   если l <= r то:
            //     слева [l] все элементы <= x
            //     справа [r] все элементы >= x
            //   иначе (возврат с результатом l):
            //     l - индекс x или элемента, непосредственно следующего за х
        }

        // так как при обмене увеличиваем l, может оказаться, что l == right, который не входит в сортируемый диапазон
        // (возможно, если только в качестве опорного элемента брать последний элемент из диапазона)
        if (l == right) {
            l--;
        }

        return l;
    }

    /**
     * Быстрая сортировка части списка
     *
     * @param <T> Тип элементов списка
     * @param data Массив типа T
     * @param left Левая граница (индекс первого элемента) сортируемого диапазона
     * @param right Первый элемент за правой границей сортируемого диапазона
     * @param c Компаратор (задает правила сравнения элементов)
     */
    private static <T> void sort(List<T> data, int left, int right, Comparator<T> c) {
        if (left < right - 1) {
            int xIndex = partition(data, left, right, c);
            sort(data, left, xIndex, c);
            sort(data, xIndex, right, c);
        }
    }

    /**
     * Быстрая сортировка списка (QuickSort)
     *
     * @param <T> Тип элементов списка
     * @param data Список типа T
     * @param c Компаратор (задает правила сравнения элементов)
     */
    public static <T> void sort(List<T> data, Comparator<T> c) {
        sort(data, 0, data.size(), c);
    }

    /**
     * Быстрая сортировка (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов списка
     * @param data Сортируемый список типа T
     */
    public static <T extends Comparable<T>> void sort(List<T> data) {
        sort(data, (a, b) -> a.compareTo(b));
    }
}
