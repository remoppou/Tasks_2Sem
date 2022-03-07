package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<List<String>> listOfRows;
    public List<String> row;
    public int countRow;
    public int countCol;
    private int calk;

    class Name {
        List<String> names = new ArrayList<>();
        int countCol;

        public void setNames(String name) {
            names.add(name);
            countCol++;
        }

        public int getCountCol() {
            return countCol;
        }
    }

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
        // инициализировать класс и взять оттуда колонки!!!!!!!!
        return ;
    }

    public void countColPlus(String nameOfNewCol) {
        countCol++;
    }

//    public void cell(String a, int indexRow, String indexCol) {
//        int indexC = name(indexCol); //Реализовать имена колонок
//        listOfRows.get(indexRow).add(indexC, a);
//    }

    public void addInRow(String a) {
        if (calk > 0) {
            while (row.size() < countCol) {
                row.add(a);
            }
            System.out.println("Строка заполнена!!!");
            addInListOfRows(row);
        } else {
            row.add(a);
            countCol++;
            calk++;
        }
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
