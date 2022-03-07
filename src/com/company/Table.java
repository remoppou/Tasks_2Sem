package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<List<String>> listOfRows;
    public List<String> row;
    public int countRow;
    public int countCol;

    public Table() {
        listOfRows = new ArrayList<>();
        row = new ArrayList<>();
        countRow = 0;
    }

    public int sizeOfRows() {
        return countRow;
    }

    public int numColumn() {
        return countCol;
    }

    public void addInListOfRows(List<String> row) {
        listOfRows.add(row);
        countRow++;
    }

    public List<String> getRow(int index) {
        if (index < 0 || index >= countRow)
            throw new IndexOutOfBoundsException();
        List<String> str = listOfRows.get(index);
        return str;
    }

    public void removeRow (int index) {
        if (index < 0 || index >= countRow)
            throw new IndexOutOfBoundsException();
        listOfRows.remove(index);
        countRow--;
    }

    public void clearTable() {
        listOfRows.clear();
        countRow = 0;
    }
}
