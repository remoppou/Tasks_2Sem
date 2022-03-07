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
        countCol = 0;
    }

    public int sizeOfRows() {
        return countRow;
    }

    public int numColumn() {
        return countCol;
    }

    public void addInRow(String a) {
        row.add(a);
        countCol++;
    }

    public void removeCol(int indexCol){
        if (indexCol < 0 || indexCol >= countCol)
            throw new IndexOutOfBoundsException();
        for (int i = 0; i < listOfRows.size(); i++) {
            listOfRows.get(i).remove(indexCol);
        }
        countCol--;
    }

    public void addInListOfRows(List<String> row) {
        listOfRows.add(row);
        row.clear();
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
