/*
Given a string, we have to find out all subsequences of it. A String is a subsequence of a given String, that is generated by deleting some character of a given string without changing its order.

Examples:

Input : abc
Output : a, b, c, ab, bc, ac, abc

Input : aaa
Output : a, aa, aaa
 */

import java.util.*;
public class SubsequencesofaString {
	
	/*
	solution 1:
	Step 1: Iterate over the entire String 
	Step 2: Iterate from the end of string in order to generate different substring, add the subtring to the set
	Step 3: Drop kth character from the substring obtained from above to generate different subsequence. 
	Step 4: if the subsequence is not in the set then recur.
	 */
	
    static TreeSet<String> set = new TreeSet<>(); // set to store all the subsequences, sorted alphabetically, ascending
	//static HashSet<String> set = new HashSet<>();
	
    // It computes all the subsequence of an string
    static void subsequence(String str)
    {
        // iterate over the entire string	
        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length(); j > i; j--) { // iterate from the end of the string to generate substrings
                String sub_str = str.substring(i, j);
                //System.out.println(sub_str);
                
                if (!set.contains(sub_str))
                    set.add(sub_str);
                
                //System.out.println(set);
 
                for (int k = 1; k < sub_str.length() - 1; k++) { // if substring.len >= 2, drop kth character in the substring and if its not in the set then recur
                    StringBuffer sb = new StringBuffer(sub_str);
                    sb.deleteCharAt(k);             
                    if (!set.contains(sb.toString()))
                    	subsequence(sb.toString());
                }
            }
        }
    }
    
    
    
    /*
    Alternate Solution : One by one fix characters and recursively generates all subsets starting from them. 
    After every recursive call, we remove last character so that next permutation can be generated.
     */
 
    // Driver code
    public static void main(String[] args)
    {
        String s = "xyz";
        subsequence(s);
        System.out.println(set);
        //for(String st : set) {
        	//System.out.println(st);
        //}
    }
}
