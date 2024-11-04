public class Sorter {
    public static void selectionSort(Contact[] contacts) {      // Method for sorting a contact array with selection sort

        for ( int i=0; i < contacts.length-1; i++) {            // Iterate the entire length of the contact array

            int min = i;                                        // Store the minimum string as the current index

            for (int j=i+1; j < contacts.length; j++)           // Iterate the remaining length of the contact array above i
                if (contacts[j].compareTo(contacts[min]) < 0)   // If an alphabetically lower name is found
                    min = j;                                    // Store its index as the minimum string

            Contact tempArray = contacts[i];                    // Create a temporary array, length the current number of loop iterations
            contacts[i] = contacts[min];                        // Store the minimum string at the current index
            contacts[min] = tempArray;                          // Replace existing array with the new more-sorted array
        }

    }

    public static void insertionSort(Contact[] contacts) {      // Method for sorting a contact array with insertion sort

        for (int i = 1; i < contacts.length; i++) {             // Iterate the entire length of the contact array, start from the 2nd element

            Contact refKey = contacts[i];                       // Create the reference key for the current element
            int j = i - 1;                                      // Make j the element before the key so we can iterate backwards from it

            while (j >= 0) {                                    // Until j has looked at all elements before the key
                if (refKey.compareTo(contacts[j]) > 0)          // If the key is alphabetically lower than the current element...
                    break;                                      // ...break out of the the while loop, executing nothing else within it

                contacts[j + 1] = contacts[j];                  // Shift the elements of the array right by 1
                j--;                                            // Decrement j, moving to the element on the left
            }
            contacts[j + 1] = refKey;                           // Swap the next element with the key
        }
    }

    public static void quickSort(Contact[] contacts) {      // Wrapper method for sorting a contact array with quick sort

        int lower = 0;                                      // Define the lower...
        int upper = contacts.length - 1;                    // ...and upper extents of the contacts array
        quickSort(contacts,lower,upper);                    // Run the sorting method

    }
        static void quickSort(Contact[] contacts, int lower, int upper) { // Sorting algorithm for the quicksort method

            int median = lower + (upper - lower) / 2;           // Define the index for the median element (pivot)
            Contact pivot = contacts[median];                   // Store the element value of the pivot

            int i = lower;                                      // Index for the left sub array to be sorted, starting at lower
            int j = upper;                                      // Index for the right sub array to be sorted, starting at upper
            while (i <= j) {
                while (contacts[i].compareTo(pivot) < 0)        // While unsorted, increment the index for the lower sub array
                    i++;

                while (contacts[j].compareTo(pivot) > 0)        // While unsorted, decrement the index for the upper sub array
                    j--;

                if (i <= j) {                                   // If the lower array index and upper array index need to be swapped...
                    Contact tempHold = contacts[i];             // ...temporarily hold the current lower value
                    contacts[i] = contacts[j];                  // Store the current upper value in the lower index
                    contacts[j] = tempHold;                     // Store the held value in the upper index

                    i++;                                        // Increment i
                    j--;                                        // Decrement j
                }
            }

            if (lower < j)                                      // Recursively sort the lower sub array
                quickSort(contacts, lower, j);

            if (upper > i)                                      // Recursively sort the upper sub array
                quickSort(contacts, i, upper);
        }



    public static void mergeSort(Contact[] contacts) {  // Wrapper method for sorting an array with no given extents

        int lower = 0;                          // Set the lower extent for the contacts array
        int upper = contacts.length - 1;        // Set the upper extent for the contacts array
        if (lower == upper )                    // If the array is one element long, it is already sorted...
            return;                             // ...so cancel the sort
        mergeSort(contacts, lower, upper);      // If sortable, sort the array

    }

        public static void mergeSort(Contact[] contacts, int lower, int upper) {    // Method for recursive sorting of halves, then merging

            if (lower == upper )                            // If the array is one element long, it is already sorted...
                return;                                     // ...so cancel the sort
            int median = (lower + upper) / 2;               // Set the middle point for the array

            mergeSort(contacts, lower, median);             // Sort the half below the median
            mergeSort(contacts, median + 1, upper);   // Sort the half above the median
            merge(contacts, lower, median, upper);          // Merge the two halves together
        }

        public static void merge(Contact[] contacts, int lower, int median, int upper) {

            int n = upper - lower + 1;                                  // Find the size of the array
            Contact[] tempArray = new Contact[n];                       // Create a temporary array for merging into

            int idx1 = lower;                                           // Index for the current element in the lower half
            int idx2 = median + 1;                                      // Index for the current element in the upper half
            int j = 0;                                                  // The current null element in the temporary array

            while (idx1 <= median && idx2 <= upper) {                   // While the indexes are still in their respective halves...

                if (contacts[idx1].compareTo(contacts[idx2]) < 0) {     // If the lower index is alphabetically lower than the higher index...
                    tempArray[j] = contacts[idx1];                      // ...place the current lower element into the temporary array
                    idx1++;                                             // Increment the lower index by 1

                } else {                                                // But if the element in upper is lower...
                    tempArray[j] = contacts[idx2];                      // ...place it in the array instead
                    idx2++;                                             // Increment the upper index by 1
                }
                j++;                                                    // Increment the number of elements in tempArray by 1
            }

            while (idx1 <= median) {                                    // While the lower index is not at the middle yet...
                tempArray[j] = contacts[idx1];                          // Put any other entries into the first half of the temp array
                idx1++;                                                 // Increment the index by 1 until the it reaches the middle
                j++;                                                    // Increment the number of elements in tempArray by 1
            }

            while (idx2 <= upper) {                                     // While the upper index is not at the end yet...
                tempArray[j] = contacts[idx2];                          // Put any other entries into the first half of the temp array
                idx2++;                                                 // Increment the index by 1 until the it reaches the end
                j++;                                                    // Increment the number of elements in tempArray by 1
            }

            for (j = 0; j < n; j++) {                                   // For the length of the tempArray
                contacts[lower + j] = tempArray[j];                     // Copy the elements back into the contacts array
            }
    }

    // ------------------------
    //         TESTING
    // ------------------------

    public static void main(String[] args) {

        Contact Person1 = new Contact("Elliot","Alderson");
        Contact Person2 = new Contact("White","Rose");
        Contact Person3 = new Contact("Tyrell","Wellick");
        Contact Person4 = new Contact("Darlene","Alderson");
        Contact Person5 = new Contact("Phillip","Price");
        Contact Person6 = new Contact("Angela","Moss");


        Contact[] selSortArray = {Person1,Person2,Person3,Person4,Person5,Person6};
        Contact[] insSortArray = {Person1,Person2,Person3,Person4,Person5,Person6};
        Contact[] qckSortArray = {Person1,Person2,Person3,Person4,Person5,Person6};
        Contact[] mrgSortArray = {Person1,Person2,Person3,Person4,Person5,Person6};

        System.out.println();
        System.out.println("The Unsorted Array:");

        int n = selSortArray.length;

        for (int i=0; i<n; ++i) {
            System.out.println(selSortArray[i]);
        }

        System.out.println();
            Sorter.selectionSort(selSortArray);
        System.out.println("The Sorted Array Using Selection Sort");

        for (int i=0; i<n; ++i) {
            System.out.println(selSortArray[i]);
        }

        System.out.println();
            Sorter.insertionSort(insSortArray);
        System.out.println("The Sorted Array Using Insertion Sort:");

        for (int i=0; i<n; ++i) {
            System.out.println(insSortArray[i]);
        }

        System.out.println();
            Sorter.quickSort(qckSortArray);
        System.out.println("The Sorted Array Using Quick Sort:");

        for (int i=0; i<n; ++i) {
            System.out.println(qckSortArray[i]);
        }

        System.out.println();
            Sorter.mergeSort(mrgSortArray);
        System.out.println("The Sorted Array Using Merge Sort:");

        for (int i=0; i<n; ++i) {
            System.out.println(mrgSortArray[i]);
        }

    }
}