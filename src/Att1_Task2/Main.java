package Att1_Task2;

public class Main {

    public static void main(String[] args) throws Exception {
        IntegerSimpleLinkedList list = new IntegerSimpleLinkedList();

        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(16);
        list.addFirst(8);
        list.addLast(32);
        list.addLast(30);
        list.addLast(40);
        list.addLast(40);
        list.addLast(40);
        list.addLast(40);
        list.addLast(40);
        list.addLast(40);
        list.addLast(40);
        list.addLast(64);
        list.doubling();


        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();

        list.removeDoubles();
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();
    }
}
