/*********************************************
Longest Palindromic Substring

Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Solution

********************************************/

public class LongestPalindrom{

//1. Two Pointers: Time ~ O(N^2), Space ~ O(1) 

public String longestPalindrome(String s) {
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2 + 1;
        }
    }
    return s.substring(start, end);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--; R++;
    }
    return (R - 1) - (L + 1) + 1;
}


/****************************************************
Issue: repeated characters and overlapping palindromic substrings cause the algorithm to approach its worst-case complexity O(N^2)
Improvement: see the following O(N) algorithm

2. Manacher's Algorithm: Time ~ O(N), Space ~ O(N) 
Reference: Detail explanation see (1, 2, 3), animation (1), simple implementation (1, 2).
Idea: scan from left to right, we should be able to use the previous result to save comparisons.
Specifically, given a palindrome substring s[L...R],
String index:  ... L ... i’ ... C ... i ... R ... 
Let L[ i ] be the LPS's length at index i. We can compute L[ i ] using L[ i' ] where i' is the mirrored index of i around center C.
if L[ i’ ] < R - i, then L[ i ] = L[ i’ ] (no way to expand)
if L[ i’ ] > R - i, then L[ i ] = R - i (no way to expand)
if L[ i’ ] == R - i, then:
                       if R == s[End], then L[ i ] = L[ i’ ] = R - i; (no way to expand)
                       else,  then L[ i ] >= R - i (possible to expand)
Implementation:
1. preProcess(s): for simple code to handle odd/even cases;
add ' | ' between each char (including begin and end), and add ' ^ ' and ' $ ' at begin and end
e.g., "abba" => "^|a|b|b|a|$"
2. compute L[ i ]
if i > R, then L[ i ] = 1;
if i < R, then L[ i ] = min{L[ i’ ], R - i};
Then expand anyway for all the above three cases (simple code): note that for the above three cases, it should stop after one comparison.
3. update C and R
move C to i (update new R) only when i + L[ i ] exceeds R
*****************************************************/

public String longestPalindrome(String s) {
    String t = preProcess(s);        
    int n = t.length();
    if (n == 2) return s;
    int[] L = new int[n];
    int C = 0, R = 0;
    int max = 0, center = 0;
    
    for (int i = 1; i < n - 1; i++) {
        int iMirror = 2 * C - i;    // i' = C - (i - C)
        L[i] = i < R ? Math.min(L[iMirror], R - i) : 1;
        while (t.charAt(i - L[i]) == t.charAt(i + L[i]))    L[i]++;
        if (i + L[i] > R) {
            C = i;
            R = i + L[i];
        }
        if (L[i] > max) {
            max = L[i];
            center = i;
        }
    }
    
    int start = (center - max) / 2, end = start + max - 1; // max = len of LPS
    return s.substring(start, end);
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
