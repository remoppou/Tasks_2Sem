package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<List<String>> listOfRows;
    public List<String> row;
    public int countRow;
    public int countCol;

    //Класс, отвечающий за имена колонок
    class Name {
        List<String> names = new ArrayList<>();
        int countCol;

        public void setNames(String name) {
            names.add(name);
            countCol++;
        }

        public int findColumn(String nameCol) throws Exception {
            int numCol = -1;
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(nameCol)) {
                    numCol = i;
                    break;
                }
            }
            if (numCol == -1) {
                throw new Exception("No column with these name");
            }
            return numCol;
        }

        public void removeCol(int index) {
            names.remove(index);
            countCol--;
        }

        public int getCountCol() {
            return countCol;
        }

        public void removeAll() {
            names.clear();
            countCol = 0;
        }
    }

    public Table() {
        listOfRows = new ArrayList<>();
        row = new ArrayList<>();
        countRow = 0;
        countCol = 0;
    }

    //Количество строк
    public int sizeOfRows() {
        return countRow;
    }

    //Количество столбцов
    public int numColumn() {
        Name name = new Name();
        return name.getCountCol();
    }

    //Доб. столбца
    public void addCol(String nameOfNewCol) {
        Name name = new Name();
        name.setNames(nameOfNewCol);
        countCol++;
    }

//    public void cell(String a, int indexRow, String indexCol) {
//        int indexC = name(indexCol); //Реализовать имена колонок
//        listOfRows.get(indexRow).add(indexC, a);
//    }

    //Добавление элемента до момента, пока не заполнится строка
    public void addInRow(String a) {
        while (row.size() < numColumn()) {
            row.add(a);
        }
        System.out.println("Строка заполнена!!!");
        addInListOfRows(row);
    }

    //Удаление колонки
    public void removeCol(int indexCol){
        if (indexCol < 0 || indexCol >= countCol)
            throw new IndexOutOfBoundsException();
        for (int i = 0; i < listOfRows.size(); i++) {
            listOfRows.get(i).remove(indexCol);
        }
        Name name = new Name();
        name.removeCol(indexCol);
    }

    //Добавление строки в лист строк
    public void addInListOfRows(List<String> row) {
        listOfRows.add(row);
        row.clear();
        countRow++;
    }

    //Получение строки по индексу
    public List<String> getRow(int index) {
        if (index < 0 || index >= countRow)
            throw new IndexOutOfBoundsException();
        List<String> str = listOfRows.get(index);
        return str;
    }

    //Удаление строки
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
        Name name = new Name();
        name.removeAll();
    }
}
