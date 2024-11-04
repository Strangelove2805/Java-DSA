class DoublyLinkedList {
    private ListNode2 head = null;
    private ListNode2 tail = null;
    private int n = 0; // list size

    public void addFirst(Object o) {                            // Method to add a node at the start of the list

            ListNode2 Node = new ListNode2(o,null,head);     // Create a new node with parameters - element o, previous...
                                                                // ...node null and next node head
            if (head != null) {                                 // If the head is not empty...
                head.prev = Node;                               // ...set the previous node parameter of head as the new node
            }

            if (tail == null) {                                 // If the tail is empty...
                tail = Node;                                    // ...set the tail value to the new node
            }

            head = Node;                                        // Set the head as the new node
            n++;                                                // Increase the size of the list by 1
        }

    public Object get(int i) {                                  // ==========================================
        if (i < 0 || i >= n);
            ListNode2 node = head;
        for (int j = 0; j < i; j++)                             // Same method as the single linked list
            node = node.next;
        return node.element;
    }                                                           // ==========================================

    public void insert(Object o,int i) {                        // Method to add a node at index i
        if (i < 0 || i > n) ; // …error!

        if (i == 0) {                                           // If the index is at the start of the list
            addFirst(o);                                        // Run the addFirst method to save time
            return;
        }

        if (i == n) {                                           // If the index is at the end of the list
            ListNode2 node = new ListNode2(o,tail,null);     // Create a new node at the end
            tail.next = node;                                   // Point the tail's next pointer at the new node
            tail = node;                                        // Set the new node as the new tail
            n++;                                                // Increase the size of the list by 1
            return;
        }

        ListNode2 node = head;                                  // Create a marker at the list's head

        for (int j = 0; j < i; j++) {                           // Sweep through the list up to the index
            node = node.next;                                   // Keep incrementing the marker until the desired index is found
        }

        ListNode2 newNode = new ListNode2(o,node.prev,node);    // Create a new node at the index
        node.prev.next = newNode;                               // Point the previous node's next pointer at the new node
        node.prev = newNode;                                    // Set the previous node to the new node
        n++;                                                    // Increase the size of the list by 1
    }

    public void remove(int i) {                                 // Method to remove a node at index i
        n--;                                                    // Decrease the number of nodes by 1
        if (i < 0 || i >= n); // …error!

        if (n == 0) {                                           // If there are no nodes left
            head = null;                                        // Set the head to null
            tail = null;                                        // Set the tail to null
            return;
        }

        if (i == 0) {                                           // If removing the first node
            head = head.next;                                   // Move the head forward by 1
            head.prev = null;                                   // Make the head's new prev pointer null
            return;
        }

        if (i == n){                                            // If removing the last node
            tail = tail.prev;                                   // Move the tail backward by 1
            tail.next = null;                                   // Make the tail's new next pointer null
            return;
        }

        ListNode2 node = head;                                  // Create a marker at the list's head

        for (int j = 0; j < i; j++) {                           // Sweep through the list up to the index
            node = node.next;                                   // Keep incrementing the marker until the desired index is found
        }

        node.prev.next = node.next;                             // Change the pointer of the node next to the index node
        node.next.prev = node.prev;                             // Change the pointer of the node previous to the index node
    }


    /**
     * Prints out the elements in the list from the first to the last (front to back) and then from the last to the first
     * (back to front). This is useful to test whether the list nodes are connected correctly with next and prev references.
     */
    public void print() {
        // no elements to print for empty list
        if (head == null) {
            System.out.println("list empty.");
            return;
        }

        // follow next references to list elements from the front to the back of the list
        System.out.print("front to back: ");
        ListNode2 node = head;
        System.out.print(node.element + " ");
        while (node.next != null) {
            node = node.next;
            System.out.print(node.element + " ");
        }

        // follow prev references to list elements from the back to the front of the list
        System.out.print("-- and back to front: ");
        while (node != null) {
            System.out.print(node.element + " ");
            node = node.prev;
        }
        System.out.println();
    }
}
