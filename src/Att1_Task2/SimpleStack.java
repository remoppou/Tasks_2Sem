package Att1_Task2;

public interface SimpleStack<T> {
    void push(T value);
    T pop() throws Exception;
    T peek() throws Exception;
    int count();
    boolean empty();
}
