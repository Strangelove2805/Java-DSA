class SkipList {
    private SkipListNode head;                                              // Initialises the head node
    private int n = 0; // list size                                         // Determines the list size
    private int maxlanes = 5;                                               // Determines the maximum amount of allowed lanes
    private int lanes;                                                      // Determines the lanes needed

    public SkipList() {                                                     // List constructor
        lanes = 3;                                                          // Set the amount of lanes needed (could be a constructor input)
        if (lanes > maxlanes) {                                             // If the required lane count is higher than allowed...
            System.out.println("Lane count too high");                      // ...warn the user
            System.exit(0);                                           // Terminate the program
        }
        head = new SkipListNode(null,lanes);                             // Create the head node
    }

    public void createTestList() {                                          // I did this manually as I couldn't make a functional method to add nodes
        SkipListNode Anne = new SkipListNode("Anne",3);             // Create the first node with 3 lanes and element "Anne"
        head.next[0] = Anne;                                                // Point head, lane 1 at Anne
        head.next[1] = Anne;                                                // Point head, lane 2 at Anne
        head.next[2] = Anne;                                                // Point head, lane 3 at Anne
        n++;                                                                // Increase the number of nodes in the list by 1

        SkipListNode Ben = new SkipListNode("Ben",1);               // Create the second node with 1 lane and element "Ben"
        Anne.next[0] = Ben;                                                 // Point Anne, lane 1 at Ben
        n++;                                                                // Increase the number of nodes in the list by 1

        SkipListNode Charlie = new SkipListNode("Charlie",2);       // Create the third node with 2 lanes and element "Charlie"
        Anne.next[1] = Charlie;                                             // Point Anne, lane 2 at Charlie
        Ben.next[0] = Charlie;                                              // Point Ben, lane 1 at Charlie
        n++;                                                                // Increase the number of nodes in the list by 1

        SkipListNode Don = new SkipListNode("Don",1);               // Create the fourth node with 1 lane and element "Don"
        Charlie.next[0] = Don;                                              // Point Charlie, lane 1 at Don
        n++;                                                                // Increase the number of nodes in the list by 1

        SkipListNode Ernie = new SkipListNode("Ernie",3);           // Create the fifth node with 3 lanes and element "Ernie"
        Anne.next[2] = Ernie;                                               // Point Anne, lane 3 at Ernie
        Charlie.next[1] = Ernie;                                            // Point Charlie, lane 2 at Ernie
        Don.next[0] = Ernie;                                                // Point Don, lane 1 at Ernie
        n++;                                                                // Increase the number of nodes in the list by 1
    }

    public void print() {                                                   // Method for printing each lane of the skip list
        for (int i = lanes-1; i > -1; i--) {                                // For each lane, starting from the top...
            if (head.next[0] == null) {                                     // If the node next to the head in the bottom lane is empty
                System.out.println("List is empty");                        // Declare that the list is empty
                return;
            }
            if (head.next[i] == null) {                                     // If there is no node after the head in the given lane
                System.out.println("Lane " + (i+1) + " is empty");          // Declare that the lane is empty
                continue;                                                   // Skip this for loop iteration
            }
            SkipListNode node = head;                                       // Create a new node holding the value of the head
            System.out.print("Lane " + (i+1) + " - ");
            while (node.next[i] != null) {                                  // While the node next to the current node isn't empty...
                node = node.next[i];                                        // Move the current node over in the current lane
                if (node.next[i] == null) {                                 // If there are no more nodes in the current lane...
                    System.out.print(node.element);                         // ...print the last element and start the next lane
                    break;
                }
                System.out.print(node.element + ", ");
            }
            System.out.println("");
        }
    }

    public boolean inList(String s) {                                                       // Method to determine if input string s is in the list
        SkipListNode node = head;                                                           // Create a new node holding the value of the head
        int laneIndex = lanes - 1;                                                          // Determines the lane being searched
        while(laneIndex > -1) {                                                             // While on or above lane 1...
            if (laneIndex < 0)                                                              // If the lane drops below lane 1
                break;                                                                      // End the loop
            if (node.next[laneIndex] == null || node.next[laneIndex].element == null) {     // If the next node, or its element is null...
                // (I know it's ugly but it's the only way I can get this method working)
                laneIndex--;                                                                // Drop the lane by 1
                continue;                                                                   // Start the next loop iteration
            }
            String nextel = node.next[laneIndex].element;                                   // Declare the string we will be comparing to
            int compare = s.compareTo(nextel);                                              // Compare the next element string to the input string s
            if (compare < 0) {                                                              // If next el is alphabetically lower than s...
                laneIndex--;                                                                // ...drop a lane
            } else if (compare > 0) {                                                       // If next el is alphabetically higher than s...
                node = node.next[laneIndex];                                                // ...move along one node in the current lane
            } else if (compare == 0) {                                                      // If next el is equal to s...
                return true;                                                                // ...confirm that s has been found in the list
            }
        }
        return false;                                                                       // If none of the cases are true for every lane, assume s is not in the list
    }

    public static void main(String[] args) {
        SkipList sl = new SkipList();
        sl.createTestList();
        sl.print();
        System.out.println();

        System.out.println(sl.inList("Anne") + " should be true");
        System.out.println(sl.inList("Ben") + " should be true");
        System.out.println(sl.inList("Charlie") + " should be true");
        System.out.println(sl.inList("Don") + " should be true");
        System.out.println(sl.inList("Ernie") + " should be true");

        System.out.println(sl.inList("Arya") + " should be false");
        System.out.println(sl.inList("Elmo") + " should be false");
        System.out.println(sl.inList("Zorro") + " should be false");
    }
}