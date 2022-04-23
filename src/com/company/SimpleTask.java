package com.company;

import java.util.Comparator;

public class SimpleTask<T> {

    protected class Element {
        private Buyer buyer;
        private Element next;

        public Element(Buyer buyer, Element next) {
            this.buyer = buyer;
            this.next = next;
        }
    }

    private Element head;
    private Element tail;
    private int size = 0;

    public int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void findPlace(Buyer buyer, Comparator<Buyer> comparator) {
        if (head.buyer.boxOfficeTime() > buyer.boxOfficeTime()) {
            head = new Element(buyer, head);
        } else {
            Element current = head;
            while (current.next != null) {
                int t = comparator.compare(buyer, current.next.buyer);
                if (t == 1) {
                    current = current.next;
                    break;
                } else if (t == 0) {
                    if (buyer.getN() >= current.next.buyer.getN()) {
                        current = current.next;
                        continue;
                    } else {
                        current.next = new Element(buyer, current.next);
                        return;
                    }
                } else {
                    current.next = new Element(buyer, current.next);
                    return;
                }
            }
            if (current.next == null) {
                tail.next = new Element(buyer, null);
                tail = tail.next;
            }
        }
    }


    public boolean addElement(Buyer buyer, Comparator<Buyer> comparator) {
        if (isEmpty()) {
            head = tail = new Element(buyer, null);
        } else {
            findPlace(buyer, comparator);
        }
        size++;
        return true;
    }

    public Buyer poll() throws Exception {
        if (!isEmpty()) {
            Buyer current = head.buyer;
            head = head.next;
            size--;
            return current;
        }
        throw new Exception("Queue is empty!");
    }
}