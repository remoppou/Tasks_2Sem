package Att1_Task1;

import java.util.Locale;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        SequenceStats sequenceStats = new SequenceStats();
        System.out.println(sequenceStats.getAvg());
        System.out.println(sequenceStats.getCount());
        sequenceStats.put(5.7);
        System.out.println(sequenceStats.getCount());
        sequenceStats.put(4.3);
        System.out.println(sequenceStats.getAvg());
        System.out.println(sequenceStats.getCount());
    }
}
