public class Contact implements Comparable<Contact> {
    public String firstName;                                        // Initialise firstName as an object property
    public String lastName;                                         // Initialise lastName as an object property

    public Contact(String f, String l) {                            // Constructor for contacts
        firstName = f; lastName = l;
    }

    public String toString() {                                      // Method to output both names of a contact
        return firstName + " " + lastName;
    }

    public int compareTo(Contact c) {                               // Method for lexicographic comparison of two contacts

        if (this.lastName.compareTo(c.lastName) == 0) {             // If both last names are the same...

            if (this.firstName.compareTo(c.firstName) == 0)         // ...check if both forenames are also the same
                return 0;                                           // If so, confirm the contacts as identical

            else if (this.firstName.compareTo(c.firstName) < 0)     // If the first name of c is alphabetically higher (closer to Z)...
                return -1;                                          // ...return a negative number

            else                                                    // If the first name of c is alphabetically lower...
                return 1;                                           // ...return a positive number
        }
        else if (this.lastName.compareTo(c.lastName) < 0)           // If the last names are different, and that of c is alphabetically higher...
            return -1;                                              // ...return a negative number

        else                                                        // If the last names are different, and that of c is alphabetically lower...
            return 1;                                               // ...return a positive number
    }

    // ------------------------
    //         TESTING
    // ------------------------

    public static void main(String[] args) {

        String Word1 = "John";
        String Word2 = "David";
        int stringCompareHigher = Word1.compareTo(Word2);       // Testing the built in compareTo against mine as a benchmark

        String Word3 = "Ethan";
        String Word4 = "Mike";
        int stringCompareLower = Word3.compareTo(Word4);

        Contact Person1 = new Contact("Andrew","Ryan");
        Contact Person2 = new Contact("Gordon","Freeman");
        int contactCompareHigher = Person1.compareTo(Person2);

        Contact Person3 = new Contact("Jason","Brody");
        Contact Person4 = new Contact("Arthur","Morgan");
        int contactCompareLower = Person3.compareTo(Person4);

        System.out.println();
        System.out.println(Word1 + " is alphabetically higher than " + Word2);
        System.out.println("So the comparison value, " + stringCompareHigher + ", is > 0");

        System.out.println();
        System.out.println(Word3 + " is alphabetically lower than " + Word4);
        System.out.println("So the comparison value, " + stringCompareLower + ", is < 0");

        System.out.println();
        System.out.println(Person1 + "'s surname is alphabetically higher than " + Person2 + "'s");
        System.out.println("So " + contactCompareHigher + " should be > 0");

        System.out.println();
        System.out.println(Person3 + "'s surname is alphabetically lower than " + Person4 + "'s");
        System.out.println("So " + contactCompareLower + " should be < 0");
    }

}