package com.company;

import java.util.ArrayList;
import java.util.List;

// Mycell (with set amd get);
// List name  - class dont need;

public class Table{
    public List<List<String>> listOfRows;
    public List<String> names;
    public int countRow;
    public int countCol;

    public Table() {
        listOfRows = new ArrayList<>();
        names = new ArrayList<>();
        listOfRows.add(names);
        countRow = 0;
        countCol = 0;
    }

    //Количество строк
    public int sizeOfRows() {
        return countRow;
    }

    //Количество столбцов
    public int numColumn() {
        return names.size();
    }

    //Доб. столбца
    public void addCol(String nameOfNewCol) {
        names.add(nameOfNewCol);
        countCol++;
    }

//    public MyCell cell(int indexRow, int indexCol) {
//        return row(indexRow).get(indexCol);
//    }

    //Добавление элемента до момента, пока не заполнится строка
    public void addInRow(String a) {
        int index = listOfRows.size();
        if (index == 1) {
            List<String> row = new ArrayList<>();
            listOfRows.add(row);
            countRow++;
            index++;
        }
        if (listOfRows.get(index).size() < countCol) {
            listOfRows.get(index).add(a);
        }
        if (listOfRows.get(index).size() == countCol) {
            listOfRows.get(index).add(a);
            System.out.println("Строка заполнена!!!");
            addRow();
        }
    }

    //Удаление колонки по индексу
    public void removeCol(int indexCol){
        if (indexCol < 0 || indexCol >= countCol)
            throw new IndexOutOfBoundsException();
        for (int i = 0; i < listOfRows.size(); i++) {
            listOfRows.get(i).remove(indexCol);
        }
        names.remove(indexCol);
        countCol--;
    }

    //Добавление строки в лист строк
    public void addRow() {
        List<String> row = new ArrayList<>();
        listOfRows.add(row);
        countRow++;
    }

    //Получение строки по индексу
    public List<String> row(int index) {
        if (index < 0 || index >= countRow)
            throw new IndexOutOfBoundsException();
        return listOfRows.get(index);
    }

    //Удаление строки по индексу
    public void removeRow (int index) {
        if (index < 0 || index >= countRow)
            throw new IndexOutOfBoundsException();
        listOfRows.remove(index);
        countRow--;
    }

    //Очистка таблицы
    public void clearTable() {
        listOfRows.clear();
        countRow = 0;
    }

    //Очистка всей таблицы(Вместе с именами)
    public void clearAllTable(){
        listOfRows.clear();
        names.clear();
        countRow = 0;
        countCol = 0;
    }
}
