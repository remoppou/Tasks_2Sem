package com.company;

import java.util.ArrayList;
import java.util.List;

// Mycell (with set amd get);

public class Table{
    public List<List<String>> listOfRows;
    public List<String> names;
    public int countRow;
    public int countCol;

    class MyCell{
        String nameCol;
        int indexRow;

        public String getValue() throws Exception {
            if (numCol(nameCol) == -1) {
                throw new Exception("Такой колонки нет!!!!!!");
            }
            if (indexRow < 0 || indexRow >= sizeRow()) {
                throw new IndexOutOfBoundsException();
            }
            return listOfRows.get(indexRow).get(numCol(nameCol));
        }

        public int getValueAsInt() throws Exception {
            if (numCol(nameCol) == -1) {
                throw new Exception("Такой колонки нет!!!!!!");
            }
            if (indexRow < 0 || indexRow >= sizeRow()) {
                throw new IndexOutOfBoundsException();
            }
            String trueValue = listOfRows.get(indexRow).get(numCol(nameCol));
            return Integer.parseInt(trueValue);
        }


        public int numCol(String nameCol) {
            int numCol = -1;
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(nameCol)) {
                    numCol = i;
                    return numCol;
                }
            }
            return numCol;
        }

        public void setValue(int value) throws Exception {
            if (numCol(nameCol) == -1) {
                throw new Exception("Такой колонки нет!!!!!!");
            }
            if (indexRow < 0 || indexRow >= sizeRow()) {
                throw new IndexOutOfBoundsException();
            }
            listOfRows.get(indexRow).remove(numCol(nameCol));
            String trueValue = Integer.toString(value);
            listOfRows.get(indexRow).add(numCol(nameCol), trueValue);
        }

        public void setValue(String value) throws Exception {
            if (numCol(nameCol) == -1) {
                throw new Exception("Такой колонки нет!!!!!!");
            }
            if (indexRow < 0 || indexRow >= sizeRow()) {
                throw new IndexOutOfBoundsException();
            }
            listOfRows.get(indexRow).remove(numCol(nameCol));
            listOfRows.get(indexRow).add(numCol(nameCol), value);
        }
    }

    public Table() {
        listOfRows = new ArrayList<>();
        names = new ArrayList<>();
        countRow = 0;
        countCol = 0;
    }

    //Доб. столбца
    public void addCol(String nameOfNewCol) {
        names.add(nameOfNewCol);
        countCol++;
    }

    //Добавление элемента до момента, пока не заполнится строка
    public void addInRow(String a) {
        int index = listOfRows.size();
        if (index == 0) {
            List<String> row = new ArrayList<>();
            listOfRows.add(row);
            countRow++;
        }
        if (listOfRows.get(index - 1).size() < countCol - 1) {
            listOfRows.get(index - 1).add(a);
        } else
        if (listOfRows.get(index - 1).size() == countCol - 1) {
            listOfRows.get(index - 1).add(a);
            System.out.println("Строка заполнена!!!");
            addRow();
        }
    }

    //Удаление колонки по индексу
    public void removeCol(int indexCol){
        if (indexCol < 0 || indexCol >= countCol)
            throw new IndexOutOfBoundsException();
        for (int i = 0; i < listOfRows.size() - 1; i++) {
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

    //Работа с ячейкой по индесам
    public void cell(int indexCol, int indexRow, String value){
        if (indexRow < 0 || indexRow >= countRow)
            throw new IndexOutOfBoundsException();
        if (indexCol < 0 || indexCol >= countCol)
            throw new IndexOutOfBoundsException();

        listOfRows.get(indexRow).remove(indexCol);
        listOfRows.get(indexRow).add(indexCol,value);
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

    //Узнать количество столбцов
    public int sizeCol() {
        return countCol;
    }

    //Узнать кол-во строк
    public int sizeRow() {
        return countRow;
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
