class LinkedList {

    public void addFirst(Object o) {
        head = new ListNode(o, head);
        if (n == 0) {                                   // If the number of elements is now 1
            tail = head;                                // Set the head node to also be the tail node
        }
        n++;
    }

    private ListNode tail = null;                       // Initialise tail

    public void add(Object o) {
        var node = new ListNode(o,tail);                // Create a new node with the value 'o'

        if (head == null) {                             // If the linked list is empty
            head = node;                                // Point both the head
            tail = node;                                // And tail at this newly made node
        }
        else {                                          // If the list isn't empty
            tail.next = node;                           // Point the tail at the new node
        }

        tail = node;                                    // Set the tail to the new node
    }


    ListNode head = null;
    private int n = 0; // list size

    public Object get(int i) {
        if (i < 0 || i >= n) ; // …error!
        ListNode node = head;
        for (int j = 0; j < i; j++)
            node = node.next;
        return node.element;
    }

    public void insert(Object o, int i) {
        if (i < 0 || i > n) ; // …error!
        if (i == 0) {
            addFirst(o);
            return;
        }
        ListNode node = head;
        for (int j = 0; j < i - 1; j++)
            node = node.next;
        node.next = new ListNode(o,node.next);
        n++;
        tail = node.next;                           // Set tail to the new end node
    }

    public void remove(int i) {
        if (i < 0 || i >= n) ; // …error!
        if (i == 0) { // special case
            head = head.next;
            n--;
            return;
        }
        ListNode node = head;
        for (int j = 0; j < i - 1; j++)
            node = node.next;
        node.next = node.next.next;
        n--;
        tail = node.next;                           // Set tail to the new end node

    }
}

