package com.company;

import java.util.ArrayList;
import java.util.List;

public class Table{
    private List<List<String>> listOfRows;
    private List<String> names;
    private int countRow;
    private int countCol;
    private int calk = -1;

    public void printNames() {
        for (int i = 0; i < countCol; i++) {
            System.out.print(names.get(i));
        }
    }

    public List<String> getColumn(String nameCol) {
        List<String> column = new ArrayList<>();
        int numCol = -1;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(nameCol)) {
                numCol = i;
            }
        }
        if (numCol < 0){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < listOfRows.size() - 1; i++) {
            String tmp = listOfRows.get(i).get(numCol);
            column.add(tmp);
        }
        return column;
    }

    public class MyRow {
        private int indexRow;

        private MyRow(int indexRow) {
            this.indexRow = indexRow;
        }

        public MyCell cell(String nameCol) {
            MyCell cell = new MyCell(nameCol, indexRow);
            return cell;
        }
    }

    public class MyColumn {
        private String nameCol;

        private MyColumn(String nameCol) {
            this.nameCol = nameCol;
        }

        public MyCell cell(int index) {
            MyCell cell = new MyCell(nameCol, index);
            return cell;
        }
    }

    public class MyCell{
        private String nameCol;
        private int indexRow;

        private MyCell(String nameCol, int indexRow) {
            this.nameCol = nameCol;
            this.indexRow = indexRow;
        }

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

        private int numCol(String nameCol) {
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

    public MyRow row(int index) {
        MyRow row1 = new MyRow(index);
        return row1;
    }

    public MyColumn column(String colName) {
        MyColumn column = new MyColumn(colName);
        return column;
    }

    public MyCell cell(String nameOfCol, int indexRow) {
        MyCell cell = new MyCell(nameOfCol , indexRow);
        return cell;
    }

    public MyCell cell(int indexRow, String nameOfCol) {
        MyCell cell = new MyCell(nameOfCol, indexRow);
        return cell;
    }

    //Доб. столбца
    public void addCol(String nameOfNewCol) {
        names.add(nameOfNewCol);
        countCol++;
    }

    //Добавление элемента до момента, пока не заполнится строка
    public void addInRow(String a) {
        if (calk != 0) {
            List<String> row = new ArrayList<>();
            listOfRows.add(row);
            countRow++;
            calk++;
        }
        int index= listOfRows.size() - 1;
        if (listOfRows.get(index).size() < countCol - 1) {
            listOfRows.get(index).add(a);
        } else
        if (listOfRows.get(index).size() == countCol - 1) {
            listOfRows.get(index).add(a);
            System.out.println("Строка заполнена!!!");
            calk--;
        }
    }

    //Удаление колонки по названию
    public void removeCol(String nameCol) {
        int numCol = -1;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(nameCol)) {
                numCol = i;
            }
        }
        if (numCol < 0){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < listOfRows.size(); i++) {
            listOfRows.get(i).remove(numCol);
        }
        names.remove(numCol);
        countCol--;
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
    private void addRow() {
        List<String> row = new ArrayList<>();
        listOfRows.add(row);
        countRow++;
    }

    //Получение строки по индексу
    public List<String> getRow(int index) {
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

    //Очистка всей таблицы(Вместе с именами)
    public void clearAllTable(){
        listOfRows.clear();
        names.clear();
        countRow = 0;
        countCol = 0;
    }
}
