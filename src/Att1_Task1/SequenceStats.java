package Att1_Task1;

import java.util.ArrayList;
import java.util.List;

public class SequenceStats {
    private double sum;
    private double average;
    private int size;

    public void put(double num) {
        size++;
        sum += num;
    }

    public int getCount() {
        return size;
    }

    public double getAvg() {
        if (size == 0) {
            return 0;
        }
        return sum/size;
    }

}