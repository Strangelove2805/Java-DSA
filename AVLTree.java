class AVLTree {
    AVLTreeNode root;                                          // Initialise the root node

    public AVLTree() {                                         // Method for setting up an AVL tree
        root = new AVLTreeNode();                              // Construct a new node at the root of the tree with no values and height 0
    }

    public void createTestTree() {                             // Method for creating a pre-defined balanced tree
        root.element = "4";                                    // Set the value of the root to a string, 4

        AVLTreeNode Child1 = new AVLTreeNode("2");          // Create a new node, the left child of the root with a string, 2
        Child1.height = 1;                                     // Set the height of this new node to 1
        root.left = Child1;                                    // Set this as the left child of the root

        AVLTreeNode Child1_1 = new AVLTreeNode("1");        // Create a new node, the left-left leaf of the root with a string, 1
        Child1_1.height = 2;                                   // Set the height of this new node to 2
        Child1.left = Child1_1;                                // Set this as the left child of its parent node

        AVLTreeNode Child1_2 = new AVLTreeNode("3");        // Create a new node, the left-right leaf of the root with a string, 3
        Child1_2.height = 2;                                   // Set the height of this new node to 2
        Child1.right = Child1_2;                               // Set this as the right child of its parent node

        AVLTreeNode Child2 = new AVLTreeNode("6");          // Create a new node, the right child of the root with a string, 6
        Child2.height = 1;                                     // Set the height of this new node to 1
        root.right = Child2;                                   // Set this as the right child of the root

        AVLTreeNode Child2_1 = new AVLTreeNode("5");        // Create a new node, the right-left leaf of the root with a string, 5
        Child2_1.height = 2;                                   // Set the height of this new node to 2
        Child2.left = Child2_1;                                // Set this as the left child of its parent node

        AVLTreeNode Child2_2 = new AVLTreeNode("7");        // Create a new node, the right-right leaf of the root with a string, 7
        Child2_2.height = 2;                                   // Set the height of this new node to 2
        Child2.right = Child2_2;                               // Set this as the right child of its parent node

    }

    public void print() {                          // Method for printing the tree with indentations indicating the height of a node
        print("", root, false);     // Run the modified print method with indentations, starting from the root and assuming the root is the highest node
    }

    public void print(String indent, AVLTreeNode Node, boolean leftCheck) {             // This method is a modified version of a method by Laurent Demailly.
                                                                                        // Please see the end of this script for the reference
        if (Node != null) {                                                             // If the node at the top of the tree is not empty...

            // The ternary operations determine whether a node should have an indentation or not, depending on its height
            System.out.println(indent + (leftCheck ? "  " : "  ") + Node.element);        // Print the current element with an indentation if necessary
            print(indent + (leftCheck ? "  " : "  "), Node.left, true);     // Recursively print tree values until there are no nodes left
            print(indent + (leftCheck ? "  " : "  "), Node.right, false);

        }
    }

    public boolean inTree(String e) {                       // Wrapper method to find whether a given string element is in the tree
        return inTree(root, e);                             // Return either a true or false value calculated by the inTree method
    }

    public boolean inTree(AVLTreeNode root, String e) {     // Method for recursively finding an element in the tree

        if (root == null) {                                 // If the tree is empty

            return false;

        } else if (e.compareTo(root.element) < 0) {         // If the element e is numerically lower than the current node...

            return inTree(root.left, e);                    // ...use recursion to search the node to the left

        } else if (e.compareTo(root.element) > 0) {         // If the element e is numerically higher than the current node...

            return inTree(root.right, e);                   // ...use recursion to search the node to the right

        } else {                                            // If none of these conditions are met...

            return true;                                    // We can assume the element has been found

        }
    }

    public void insert(String e) {                          // Wrapper method for recursively inserting a node into the tree and balancing if necessary
        root = insert(root, e);                             // Run the insert method at the root
    }

    AVLTreeNode insert(AVLTreeNode root, String e) {        // Insert method run recursively, checking the balance of subtrees at each node

        if (root == null)                                   // If the tree doesn't exist yet
            root = new AVLTreeNode(e);                      // Create a new node at the root

        else {                                              // If the tree does happen to exist...

            // Technically the elements are strings, but I'll be referring to the comparisons as numerical
            if (e.compareTo(root.element) < 0)              // If the new node is numerically lower than the root...
                root.left = insert(root.left, e);           // Recursively insert at the left of the root

            else                                            // If the new node is numerically higher than the root...
                root.right = insert(root.right, e);         // Recursively insert at the right of the root
        }

        root.height = compare(getHeight(root.left), getHeight(root.right)) + 1; // We are moving the root for each subtree, so we need to check its height

        // The balance is the height of the left subtree - the height of the right subtree
        // We've got to check this with each insertion or else the tree will destabilise
        int balance = getHeight(root.left) - getHeight(root.right); // Create a balance value for the current root

        if (balance > 1) {                                          // If the tree / subtree is left weighted, we need either a left or left-right rotation

            if (e.compareTo(root.left.element) < 0) {               // If the element to the left is numerically lower than our new node

                root = rotateRight(root);                           // Perform a single left rotation of the subtree

            } else {                                                // If the element to the left is numerically higher than our new node

                root.left = rotateLeft(root.left);                  // Perform a double left...
                root = rotateRight(root);                           // ...right rotation of the subtree
            }

        } else if (balance < -1) {                                  // If the tree / subtree is right weighted, we need either a right or right-left rotation

            if (e.compareTo(root.right.element) > 0) {              // If the element to the right is numerically higher than our new node

                root = rotateLeft(root);                            // Perform a single right rotation of the subtree

            } else {                                                // If the element to the left is numerically higher than our new node

                root.right = rotateRight(root.right);               // Perform a double right...
                root = rotateLeft(root);                            // ...left rotation of the subtree
            }
        }

        return root;                                                // Output the new node
    }

    AVLTreeNode rotateRight(AVLTreeNode root) {                     // Method for single right rotating a subtree with a given root

        AVLTreeNode leftChild = root.left;                          // Create a new node marker for the node left of the given root
        root.left = leftChild.right;                                // Point the left of the root to the right of its child
        leftChild.right = root;                                     // Point the right of said child back at the root

        root.height = compare(getHeight(root.left), getHeight(root.right)) + 1;                     // Calculate and assign the new height of the given sub root
        leftChild.height = compare(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;      // Calculate and assign the new height of the rotated child

        return leftChild;
    }

    AVLTreeNode rotateLeft(AVLTreeNode root) {                      // Method for single right rotating a subtree with a given root

        AVLTreeNode rightChild = root.right;                        // Create a new node marker for the node right of the given root
        root.right = rightChild.left;                               // Point the right of the root to the left of its child
        rightChild.left = root;                                     // Point the left of said child back at the root

        root.height = compare(getHeight(root.left), getHeight(root.right)) + 1;                     // Calculate and assign the new height of the given sub root
        rightChild.height = compare(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;   // Calculate and assign the new height of the rotated child

        return rightChild;

    }

    private int getHeight(AVLTreeNode Node) {       // Method to output the assigned height value of a given node

        if (Node == null)                           // If the tree is empty or just a root...
            return 0;                               // ...return nothing

        else
            return Node.height;                     // Return the integer value for the height of the node

    }

    private int compare(int Left, int Right) {      // Method to compare two integer values and return the highest

        if (Left > Right)                           // If the left integer is numerically greater than the right...
            return Left;                            // ...return the left integer

        else
            return Right;                           // And vice versa

    }

    // ------------------------
    //         TESTING
    // ------------------------

    public static void main(String[] args) {

        AVLTree t = new AVLTree();
        t.createTestTree();
        System.out.println("Test Tree Output:");
        t.print();

        t.insert("8");
        System.out.println();
        System.out.println("A Node With Number 8 Has Been Inserted:");
        t.print();

        t.insert("9");
        System.out.println();
        System.out.println("A Node With Number 9 Has Been Inserted:");
        t.print();

        t.insert("10");
        System.out.println();
        System.out.println("A Node With Number 10 Has Been Inserted:");
        t.print();

        System.out.println();
        System.out.println(t.inTree("2") + " should be true");
        System.out.println(t.inTree("5") + " should be true");
        System.out.println(t.inTree("6") + " should be true");
        System.out.println(t.inTree("11") + " should be false");
        System.out.println(t.inTree("15") + " should be false");
        System.out.println(t.inTree("42") + " should be false");
    }
}

// ------------------------------
//          Reference
//
// [1] 2013, D. Laurent, StackOverflow, Binary Tree Print Method [Online]
// Available from: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/19484210#19484210
// [Accessed 28th November 2020]
//
// ------------------------------