package com.company;

//Таблица данных (именованные столбцы, произвольное количество строк, добавление, удаление строк, столбцов, работа с ячейками и т.д.).
//Должен быть интерфейс (набор открытых методов и классов), позволяющий выполнить следующий код (идет работа с одной и той же ячейкой):
// table.column("column_name").cell(5).setValue("123");   !!!!!!!LIST IN LIST!!!!!!!!!!
// int value = table.row(5).cell("column_name").getValueAsInt("123");
// table.cell("column_name", 5).setValue(value + 1);  // параметрический полиморфизм
// table.cell(5, "column_name").setValue(value + 2);  // параметрический полиморфизм
//1. ... +
//2. именнованные столбцы+
//3. удаление и добавление данных в строку +
//4. удаление столбца +
//5. очистка всей таблицы +
//6. запись в ячейку и работа с ней +-
//7. класс с названиями + колонками + строками


import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);


        Table table = new Table();

//        list.add(arr);
//        for (int i = 0; i < list.count; i++) {
//            for (int j = 0; j < arr.column(); j++) {
//                System.out.println(list.getList(i).get(j));
//            }
//        }
    }
}
