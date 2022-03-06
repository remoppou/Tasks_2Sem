package com.company;

import java.util.List;

public class Object {
    String[] data;
    private int actualSize;

    public Object(int initialCapacity) {
        data = new String[initialCapacity];
    }

    public void add(String value) {
        data[actualSize] = value;
        actualSize++;
    }

    public void removeCol(List<Object> list, int index) {
        if (index < 0 || index >= actualSize)
            throw new IndexOutOfBoundsException();
        for (int i = 0; i < list.size(); i++) {
            data[index] = null;
        }
        actualSize--;
    }

    public int column() {
        return actualSize;
    }

    public String get(int index) {
        if (index < 0 || index >= actualSize)
            throw new IndexOutOfBoundsException();
        return data[index];
    }
}
