package com.company;

import java.util.Comparator;

public class MyQueue {

    private class Element {
        public Buyer buyer;
        public Element next;

        public Element(Buyer buyer, Element next) {
            this.buyer = buyer;
            this.next = next;
        }

        public Element(Buyer buyer) {
            this(buyer, null);
        }
    }

    private Element head = null;  // first, top
    private Element tail = null;  // last
    private int count = 0;

    public void add (Buyer buyer, Comparator<Buyer> comparator) throws Exception {
        if (count == 0) {
            head = tail = new Element(buyer);
        } else {
            findPlace(buyer, comparator);
        }
        count++;
    }

    public Buyer remove() throws Exception {
        Element result = element();
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return result.buyer;
    }

    private void findPlace(Buyer buyer, Comparator<Buyer> comparator) throws Exception {
        if (comparator.compare(head.buyer, buyer) >= 0) {
            head = new Element(buyer, head);
        } else if (comparator.compare(tail.buyer, buyer) < 0){
            tail.next = new Element(buyer, null);
            tail = tail.next;
        } else {
            Element curr = element();
            while (comparator.compare(buyer, curr.buyer) < 0) {
                curr = curr.next;
            }
            curr.next = new Element(buyer, curr.next);
        }
    }

    public Element element() throws Exception {
        if (count() == 0) {
            throw new Exception("Queue is empty");
        }
        return head;
    }

    public int count() {
        return count;
    }

    public boolean empty() {
        return count() == 0;
    }
}
