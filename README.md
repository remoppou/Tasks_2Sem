# OOP_Task1_var12

Таблица данных (именованные столбцы, произвольное количество строк, добавление, удаление строк, столбцов, работа с ячейками и т.д.).
Должен быть интерфейс (набор открытых методов и классов), позволяющий выполнить следующий код (идет работа с одной и той же ячейкой):
 
 
 table.column("column_name").cell(5).setValue("123");   
 
 int value = table.row(5).cell("column_name").getValueAsInt("123");
 
 table.cell("column_name", 5).setValue(value + 1);  // параметрический полиморфизм

table.cell(5, "column_name").setValue(value + 2);  // параметрический полиморфизм
