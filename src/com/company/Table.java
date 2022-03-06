package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<Object> list;
    public int count;

    public Table() {
        list = new ArrayList<>();
        count = 0;
    }

    public void add(Object array) {
        list.add(array);
        count++;
    }

    public Object getList(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        Object arr = list.get(index);
        return arr;
    }

    public void removeRow (int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        list.remove(index);
        count--;
    }


    public void clearTable() {
        list.clear();
        count = 0;
    }
}
