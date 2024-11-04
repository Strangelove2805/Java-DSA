/** A stack abstract data type that contains Strings. */
public class StringStack {

    String[] tower;    // Initialise an array for the stack
    int maxcap;        // Holds the maximum capacity of the stack
    int top = 0;       // Holds the position in the array of the string at the top of the stack
    String output;     // Holds the string about to be popped

    /**
     * Constructor for creating a new StringStack with a certain capacity.
     * @param capacity the maximum number of strings the stack can hold
     */
    public StringStack(int capacity) {

        tower = new String[capacity];   // Create an array of the size defined in the method
        maxcap = capacity;              // Store the maximum capacity of the array
        top = 0;                        // Set the ceiling of the stack to 0
    }

    /**
     * Puts the given String on top of the stack (if there is enough space).
     * @param s the String to add to the top of the stack
     * @return false if there was not enough space in the stack to add the string;
     *         otherwise true
     */
    public boolean push(String s) {

        if (top == maxcap || maxcap == 0){     // If the ceiling reaches maximum capacity or there is no capacity
            return false;                      // Deny the push
        }
        else{                                  // If a push is possible
            top++;                             // Raise the ceiling of the stack by 1
            tower[top - 1] = s;                // Place the method input s at the top of the stack
            return true;                       // Confirm a successful push
        }
    }

    /**
     * Removes the String on top of the stack from the stack and returns it.
     * @return the String on top of the stack, or null if the stack is empty.
     */
    public String pop() {

        if (maxcap == 0 || top == 0) {         // If there is no capacity or no remaining space in the array
            return "null";
        }
        else if (tower[top - 1] == null) {     // If the stack is empty
            return "null";
        }
        else                                   // If there is an element that can be removed
            top--;                             // Lower the ceiling by 1
            output = tower[top];               // Hold the string at the top of the stack as the output
            tower[top] = null;                 // Clear the top of the stack
            return output;                     // Return the string from the stack
    }

    /**
     * Returns the number of Strings in the stack.
     * @return the number of Strings in the stack
     */
    public int count() {

        return top;
    }
}
