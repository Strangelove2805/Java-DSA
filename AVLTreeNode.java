public class AVLTreeNode
{
    AVLTreeNode left;                   // Pointer facing the node branching to the left
    AVLTreeNode right;                  // Pointer facing the node branching to the right
    String element;                     // The value stored within the node
    int height;                         // The current level of the node, with 0 being the root

    public AVLTreeNode() {              // Constructor for the empty root when the tree is created
        left = null;
        right = null;
        element = null;
        height = 0;
    }

    public AVLTreeNode(String e) {      // Generic node constructor
        left = null;
        right = null;
        element = e;
        height = 0;
    }
}
