package ru.vsu.cs.course1.sort.demo;

import java.util.Arrays;


public class StudentSortSample {

    static class Student implements Comparable<Student> {
        String name;
        char sex;  // 'B' / 'G' - boy / girl
        int height;

        public Student(String name, char sex, int height) {
            this.name = name;
            this.sex = sex;
            this.height = height;
        }

        @Override
        public String toString() {
            return String.format("%s(%s,%s)", name, sex, height);
        }

        @Override
        public int compareTo(Student o) {
            return this.name.compareTo(o.name);
        }
    }


    public static void demo() {
        Student[] students = {
            new Student("Вася", 'B', 180),
            new Student("Оля", 'G', 158),
            new Student("Маша", 'G', 165),
            new Student("Коля", 'B', 182),
            new Student("Петя", 'B', 162)
        };

        // сортировка по росту, как на занятия по физкультуре (сначала парни, затем девушки)
        Arrays.sort(students, (a, b) -> {
            if (a.sex == b.sex) {
                return b.height - a.height;
            } else {
                return a.sex == 'B' ? -1 : 1;
            }
        });
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("-------------");

        /*
            Конструкция (a, b) -> код
            называется лямбда-выражением и является "синтаксическим сахаром",
            т.е. более короткой формой записи следующего кода

                Arrays.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student a, Student b) {
                        if (a.sex == b.sex) {
                            return b.height - a.height;
                        } else {
                            return a.sex == 'B' ? -1 : 1;
                        }
                    }
                });

            Что в свою очередь соответствует:

                class TempComparator implements Comparator<Student> {

                    @Override
                    public int compare(Student a, Student b) {
                        if (a.sex == b.sex) {
                            return b.height - a.height;
                        } else {
                            return a.sex == 'B' ? -1 : 1;
                        }
                    }
                }
                Arrays.sort(students, new TempComparator());
         */


        /*
            Если сортируемые объекты сравнимы в каком-то общепринятом смысле, что в мире Java означает, что
            класс объектов реализует интерфейс Comparable<Class> (числа, строки и т.п. уже реализуют),
            то объекты можно сравнивать без передачи критериев сравнения объектов.

            Для класса Student реализован данный интерфейс (метод compareTo) в виде сравнения студентов по имени,
            поэтому при сортировке студентов по умолчанию они выстроятся по имени (по алфавиту)
         */
        Arrays.sort(students);
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("-------------");
    }
}
