package com.company;

import java.util.Comparator;

public class CustomQueue {

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
    Comparator<Buyer> comparator;

    public CustomQueue(Comparator<Buyer> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void findPlace(Buyer buyer) {    //передается компаратор
        if (head.buyer.boxOfficeTime() > buyer.boxOfficeTime()) {
            head = new Element(buyer, head);
        } else {
            Element current = head;
            while (current.next != null) {
                int t = comparator.compare(buyer, current.next.buyer);
                switch (t) {
                    case 0:
                        if (buyer.getN() >= current.next.buyer.getN()) {
                            current = current.next;
                            continue;
                        } else {
                            current.next = new Element(buyer, current.next);
                            return;
                        }
                    case -1:
                        current.next = new Element(buyer, current.next);
                        return;
                    default:
                        current = current.next;
                        break;
                }
            }
            if (current.next == null) {
                tail.next = new Element(buyer, null);
                tail = tail.next;
            }
        }
    }


    public boolean addElement(Buyer buyer) {
        if (isEmpty()) {
            head = tail = new Element(buyer, null);
        } else {
            findPlace(buyer);
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