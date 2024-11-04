public class StringRepeater {

    public String repeatString(String s, int n){       // Defining the method and inputs

        StringBuffer sbuff = new StringBuffer("");     // Construct sbuff as a new string buffer
        for(int i=0; i<n; i++) {                       // Loop as many times as is defined in the method call
            sbuff.append(s);                           // Add string s to the established string(s)
        }

        return sbuff.toString();                       // Output the string of n * s

    }
}                                                      // Running takes an avg. 0.0000604s as opposed to the 0.0016484s of the original

/*
public class StringRepeater {                          // Original version of the class (for comparison sake)
    public String repeatString(String s, int n){
        String result = "";
        for(int i=0; i<n; i++) {
            result = result + s;
        }
        return result;
    }
}
*/