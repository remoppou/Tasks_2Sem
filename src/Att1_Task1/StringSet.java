package Att1_Task1;

import java.util.Arrays;

/**
 * Класс для хранения уникального множества строк
 */
public class StringSet {
    /**
     * Первоначальный размер массива для хранения элементов
     */
    public static final int DEFAULT_START_SIZE = 16;

    /**
     * Массив для хранения элементов множества
     */
    private String[] data;
    /**
     * Кол-во элементов множества
     */
    private int count;

    /**
     * Конструктор с параметром
     * @param startSize Первоначальный размер массива для хранения элементов
     */
    public StringSet(int startSize) {
        data = new String[startSize];
        count = 0;
    }

    /**
     * Конструктор по умолчанию (без параметров)
     */
    public StringSet() {
        // вызов конструктора с параметром со значением по умолчанию
        this(DEFAULT_START_SIZE);
    }

    /**
     * Добавления строки в множество
     * @param str Добавляемая строка
     * @return true - если строка добавлена, false - если строка уже была
     * @throws Exception
     */
    public boolean add(String str) throws Exception {
        if (str == null) {
            throw new Exception("Str is null");
        }
        if (contains(str)) {
            return false;
        } else {
            if (count >= data.length) {
                // если закончилось место, пересоздаем массив data размером в 2 раза больше
                data = Arrays.copyOf(data, count * 2);
            }
            data[count] = str;
            count++;
            return true;
        }
    }

    /**
     * Очистка множества
     * @return true - если множество было не пустое
     */
    public boolean clear() {
        boolean result = count > 0;
        for (int i = 0; i < count; i++) {
            data[i] = null;
        }
        count = 0;
        return result;
    }

    /**
     * Поиск позиции (индекса) элемента в массиве
     * @param str Искомая строка
     * @return индекс элемента в массиве data
     */
    private int getIndex(String str) {
        for (int i = 0; i < count; i++) {
            if (data[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Проверка наличия строки в множестве
     * @param str Проверяемая строка
     * @return true - если строка присутствует
     */
    public boolean contains(String str) {
        if (str == null) {
            return false;
        }
        return getIndex(str) >= 0;
    }

    /**
     * Проверка на пустоту
     * @return true - если множество пустое
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Удаление строки из множества
     * @param str Удалаемая строка
     * @return true - если строка удалена, false - если строки не было
     */
    public boolean remove(String str) {
        if (str == null) {
            return false;
        }
        int index = getIndex(str);
        if (index < 0) {
            return false;
        } else {
            data[index] = null;
            if (index < count - 1) {
                data[index] = data[count - 1];
                data[count - 1] = null;
            }
            count--;
            return true;
        }
    }

    /**
     * Размер множества
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * Элементы множества в виде массива
     * @return
     */
    public String[]	toArray() {
        return Arrays.copyOf(data, count);
    }
}
