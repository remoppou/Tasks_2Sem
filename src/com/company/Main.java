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
        //проверка на добавление и удаление колонок - work
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

        //Проверка на работу со строками - work
        System.out.println("Количество имеющихся строк(Ответ 0): " + table.sizeRow());
        table.addRow();
        table.addRow();
        System.out.println("Количество имеющихся строк(Ответ 2): " + table.sizeRow());
        table.removeRow(0);
        System.out.println("Количество имеющихся строк(Ответ 1): " + table.sizeRow());

        //Именнованные столбцы;
        for (int i = 0; i < table.countCol; i++) {
            System.out.print(table.names.get(i));
        }
        System.out.println();

        //работа с заполняемостью строк - work
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");

        //Получение строки по индексу
        System.out.println(table.getRow(0));

        //удаление строки по индексу - work
        table.removeRow(0);
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65");
        table.addInRow("65654");
        System.out.println(table.getRow(0));

        //Запись в конкретную ячейку - work
        table.cell(2,0,"88005553535");
        System.out.println(table.getRow(0));

        //Удаление колонки - work
        table.removeCol(0);
        System.out.println(table.getRow(0));
        for (int i = 0; i < table.countCol; i++) {
            System.out.print(table.names.get(i));
        }
        System.out.println();

        //Проверка на очистку содержимого(работает)

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
