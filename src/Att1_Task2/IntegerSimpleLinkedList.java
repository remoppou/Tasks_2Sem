package Att1_Task2;

public class IntegerSimpleLinkedList extends SimpleLinkedList<Integer>{

    private boolean checkNumForDegree(int num) {
        if (num >= 1) {
            while (num != 1 && num % 2 == 0) {
                num /= 2;
            }
            if (num == 1) {
                return true;
            }
        }
        return false;
    }

    public void doubling() {
        SimpleLinkedListNode curr = head;
        SimpleLinkedListNode temp;
        for(int i = 0; i < size; i ++) {
            if (checkNumForDegree(curr.value)) {
                temp = curr.next;
                curr.next = new SimpleLinkedListNode(curr.value);
                curr.next.next = temp;
                curr = curr.next.next;
                size++;
                i++;
                if (i == size - 1) { //если в конце идет дубль, то сдвигаем tail на след
                    tail = tail.next;
                }
            } else curr = curr.next;
        }
    }

    public void removeDoubles() throws SimpleLinkedListException {
        SimpleLinkedListNode prev = head;
        SimpleLinkedListNode curr = head.next;
        for(int i = 0; i < size - 2; i ++) {
            if (prev.value.equals(curr.value)){
                prev.next = curr.next;
            }
            if (curr.next == null) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }
}
