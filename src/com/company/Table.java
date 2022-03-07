package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<List<Row>> listOfRows;
    public List<Row> row;
    public int count;

    public Table() {
        listOfRows = new ArrayList<>();
        count = 0;
    }

    public void add(List<Row> row) {
        listOfRows.add(row);
        count++;
    }

    public Object getList(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        Object arr = listOfRows.get(index);
        return arr;
    }

    public void removeRow (int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        listOfRows.remove(index);
        count--;
    }


    public void clearTable() {
        listOfRows.clear();
        count = 0;
    }
}
