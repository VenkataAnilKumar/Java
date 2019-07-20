/******************************************************
Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".

Solution
******************************************************/

public class ShortestPalindrom{

//1. Two Pointers: Time ~ O(N^2), Space ~ O(N) Exceed Time Limit!

public String shortestPalindrome(String s) {
    int n = s.length();
    if (n == 0) return s;
    
    int end = 0;
    for (int i = (n - 1) / 2; i >= 0; i--) {
        if (isPalindrome(s, 0, 2 * i)) { // [0..i-1] i [i+1..2i]
            end = 2 * i + 1;
            break;
        }
        if (isPalindrome(s, 0, 2 * i - 1)) {   // [0..i-1] [i..2i-1]
            end = 2 * i;
            break;
        }
    }
    if (end == n) return s;
    StringBuilder str = new StringBuilder(s.substring(end));
    str.reverse();
    str.append(s);
    return str.toString();
}

private boolean isPalindrome(String s, int left, int right) { // shrink from two side
    while (left < right && s.charAt(left) == s.charAt(right)) {
        left++;
        right--;
    }
    if (left < right)   return false;
    else                return true;
}

//2. Manacher's Algorithm: Time ~ O(N), Space ~ O(N)

public String shortestPalindrome(String s) {
    String t = preProcess(s);
    int n = t.length();
    if (n == 2) return s;        
    int[] L = new int[n];
    int C = 0, R = 0;
    int max = 0, center = 0;        
    for (int i = 1; i <= n / 2; i++) {  // search up to n/2
        int iMirror = 2 * C - i;
        L[i] = i < R ? Math.min(L[iMirror], R - i) : 1;
        while (t.charAt(i - L[i]) == t.charAt(i + L[i]))    L[i]++;
        if (i + L[i] > R) {
            C = i;
            R = i + L[i];
        }
        if ((i - L[i]) / 2 == 0 && L[i] > max) { // record max LPS beginning with s[0]
            max = L[i];
            center = i;
        }
    }
    
    int start = (center - max) / 2, end = start + max - 1;
    StringBuilder str = new StringBuilder(s.substring(end));
    return str.reverse().append(s).toString();
}

// Convert s to t: e.g., s = "abba", t = "^|a|b|b|a|$"
private String preProcess(String s) {
    int n = s.length();
    if (n == 0) return "^$";
    StringBuilder str = new StringBuilder("^");
    for (int i = 0; i < n; i++)
        str.append("|").append(s.charAt(i));
    str.append("|$");
    return str.toString();
}

}