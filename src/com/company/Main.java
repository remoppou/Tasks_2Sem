package com.company;

//Таблица данных (именованные столбцы, произвольное количество строк, добавление, удаление строк, столбцов, работа с ячейками и т.д.).
//Должен быть интерфейс (набор открытых методов и классов), позволяющий выполнить следующий код (идет работа с одной и той же ячейкой):
// table.column("column_name").cell(5).setValue("123");   !!!!!!!LIST IN LIST!!!!!!!!!!
// int value = table.row(5).cell("column_name").getValueAsInt("123");
// table.cell("column_name", 5).setValue(value + 1);  // параметрический полиморфизм
// table.cell(5, "column_name").setValue(value + 2);  // параметрический полиморфизм


import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        Table table = new Table();
        //проверка на добавление и удаление колонок;
        table.addCol("1");
        table.addCol("2");
        table.addCol("3");
        System.out.println("Количество имеющихся колонок(ответ 3): " + table.sizeCol());
        table.addCol("5");
        System.out.println("Количество имеющихся колонок(Ответ 4): " + table.sizeCol());
        table.removeCol(0);
        table.removeCol(0);
        System.out.println("Количество имеющихся колонок(Ответ 2): " + table.sizeCol());
        table.addCol("1");
        table.addCol("2");
        System.out.println("Количество имеющихся колонок(Ответ 4): " + table.sizeCol());

        //Именнованные столбцы;
        table.printNames();

        //работа с заполняемостью строк;
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");

        //Получение строки по индексу;
        System.out.println(table.getRow(0));

        //удаление строки по индексу;
        table.removeRow(0);
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65654");
        System.out.println(table.getRow(0));
        System.out.println("---------------------");

        Table.MyColumn c1 = table.column("1");
        Table.MyColumn c2 = table.column("2");
        for (int i = 0; i < table.sizeRow(); i++) {
            String tmp  = c1.cell(i).getValue();
            c1.cell(i).setValue(c2.cell(i).getValue());
            c2.cell(i).setValue(tmp);
        }
        for (int i = 0; i < table.sizeRow(); i++) {
            System.out.println(table.getRow(i));
        }

        //Удаление колонки;
        table.removeCol(0);
        System.out.println(table.getRow(0));
//        table.removeCol("5");
        table.printNames();
        System.out.println();

        // table.column("column_name").cell(5).setValue("123");
        System.out.println(table.getRow(0));
        table.column("5").cell(0).setValue("123");
        System.out.println(table.getRow(0));
        // int value = table.row(5).cell("column_name").getValueAsInt("123");
        int value = table.row(0).cell("2").getValueAsInt();
        System.out.println(value);
        // table.cell("column_name", 5).setValue(value + 1);  // параметрический полиморфизм
        System.out.println(table.getRow(0));
        table.cell("1", 0).setValue(4 + 1);
        System.out.println(table.getRow(0));
        // table.cell(5, "column_name").setValue(value + 2);  // параметрический полиморфизм
        table.cell(0, "1").setValue(4 + 2);
        System.out.println(table.getRow(0));
        //Проверка на очистку всей таблицы-work
        table.clearAllTable();
        System.out.println("Количество имеющихся строк(Ответ 0): " + table.sizeRow());
        System.out.println("Количество имеющихся столбцов(Ответ 0): " + table.sizeCol());
    }
}
