package apps.ArraysAndStrings;

public class ArraysAndStrings {

    // Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
    private static boolean hasAllUniqueChars(String s){
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static String testHasUniqueChars(){
        String s = "A man a plan a canal Panama";
        //String s = "Daniel";
        boolean result = hasAllUniqueChars(s);
        if(result){
            return "The string " + s + " has all unique chars";
        }
        else{
            return "The string " + s + " doesn't have all unique chars";
        }
    }
}
