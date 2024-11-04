public interface SocialNetwork {

    void registerUser(String name);

    void becomeFriends(String name1, String name2);

    boolean areTheyFriends(String name1, String name2);
}
class DNABook implements SocialNetwork{

    String[] users = new String[1];                             // Create an array of users
    boolean[][] friends = new boolean[100][100];                // Create an array cataloguing the friendship status of all user pairs
    int top = 0;                                                // Number of users on the network

    public static int searchArray(String[] array, String x) {   // Function for performing binary search on arrays
                                                                // This function is derived from a binary search algorithm created
                                                                // by Youtube channel HackerRank in 2016: https://www.youtube.com/watch?v=P3YID7liBug
                                                                // It has been modified to work with strings instead of integers

        int left = 0;                                           // For recognising the leftmost element of the array
        int right = array.length - 1;                           // For recognising the rightmost element
        while (left <= right) {
            int mid = left + ((right - left) / 2);              // Finding the central element of the array
            if (array[mid] == x) {                              // If the string at the center is the value we want...
                return mid;                                     // ...return the index of said string
            }   else if (x.compareTo(array[mid]) < 0) {         // If x is alphabetically lower than the central string...
                right = mid - 1;                                // Move the bounds of the array and re-search for x
            }   else {
                left = mid + 1;
            }
        }
        return -1;                                              // If value x is not found, assume it is not present and return -1
    }

    public void registerUser(String name) {                     // Implement registerUser
        users[top] = name;                                      // The new user's name is added at the next empty array element
        top++;                                                  // The number of people in the network is increased by 1
        String[] copy = new String[(users.length + 1)];         // Create a new string array one index larger than the users array
        for (int i = 0; i < users.length; i++) {                // For the length of the users array...
                copy[i] = users[i];                             // Copy the values from users to the new array
        }
        users = copy;
    }

    public void becomeFriends(String name1, String name2) {     // Implements becomeFriends
        int x = searchArray(users,name1);                       // Search the user base for the index of person 1
        int y = searchArray(users,name2);                       // Search the user base for the index of person 2
        friends[x][y] = true;                                   // Set the friendship status of both parties to 1
        }

    public boolean areTheyFriends(String name1, String name2) {     // Implement areTheyFriends
        if (name1.compareTo(name2) > 0) {                           // If name2 is alphabetically lower than name1...
            String hold = name1;                                    // Hold the value of name1
            name1 = name2;                                          // Swap the values of both names
            name2 = hold;                                           // This means we only need to fill half of the friends array
        }
        int x = searchArray(users,name1);                           // Search the user base for the index of person 1
        int y = searchArray(users,name2);                           // Search the user base for the index of person 2
        if (friends[x][y] == true) {                                // If the users are confirmed to be friends
            return true;
        } else {                                                    // If the users are not friends
            return false;
        }
    }
}
