Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

/*****************************************************
The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)   (p is the wildcard expression)

Some examples:
isMatch("aa","a") ? false
isMatch("aa","aa") ? true
isMatch("aaa","aa") ? false
isMatch("aa", "*") ? true
isMatch("aa", "a*") ? true
isMatch("ab", "?*") ? true
isMatch("aab", "c*a*b") ? false

Solution
******************************************************/

public class WildcardMatching{

******************************************************/
1. 2-d DP: Time ~ O(SP), Space ~ O(SP) 
Let d(i, j) = true if s[0, i - 1] matches p[0, j - 1] (i, j are string lengths).
Initialize:
d(0, 0) = true, 
d(0, j): if p[j - 1] == '*', d(j) = d(0, j - 1) // deletion; else d(j) = false.
Fill up the table:
if         p[j - 1] matches s[i - 1],   d(i, j) = d(i - 1, j - 1);
else if  p[j - 1] == '*',  find if there is a s[0, k - 1] that matches p[0, j - 1]
                                     for (k : 0 to i) { if d(k, j - 1) == true,  d(i, j) = true; }
Note: “p[j] matches s[i]” means p[j] == s[i] || p[j] == '?'.
Return d(M, N).
******************************************************/

public boolean isMatch(String s, String p) {
    int lenS = s.length(), lenP = p.length();

    // deal with the exceeding time limit case
    int count = 0;
    for (int i = 0; i < lenP; i++) {
        if (p.charAt(i) != '*') count++;
    }
    if (count > lenS) return false;
    
    boolean[][] d = new boolean[lenS + 1][lenP + 1]; // i, j are the lengths of s[0..i-1] and p[0..j-1]
    d[0][0] = true;
    // initialize the first row
    for (int j = 1; j <= lenP; j++) {
        if (p.charAt(j - 1) == '*') d[0][j] = d[0][j - 1];
    }
    // fill up the table
    for (int i = 1; i <= lenS; i++) {
        for (int j = 1; j <= lenP; j++) {
            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?')
                d[i][j] = d[i - 1][j - 1];
            else if (p.charAt(j - 1) == '*') {
                for (int k = 0; k <= i; k++) {
                    if (d[k][j - 1] == true) {
                        d[i][j] = true;
                        break;
                    }
                }
            }
        }
    }
    return d[lenS][lenP];
}

//2. 1-d DP Time ~ O(SP), Space ~ O(P) 

public boolean isMatch(String s, String p) {
    int lenS = s.length(), lenP = p.length();
    
    // deal with the exceeding time limit case
    int count = 0;
    for (int i = 0; i < lenP; i++) {
        if (p.charAt(i) != '*') count++;
    }
    if (count > lenS) return false;
    
    boolean[] d = new boolean[lenP + 1];
    boolean[] isTrue = new boolean[lenP + 1]; // isTrue[j-1] == T iff any d[m][j-1] == T (0 <= m <= i) 
    d[0] = true;
    isTrue[0] = true;
    // initialize the first row
    for (int j = 1; j <= lenP; j++) {
        if (p.charAt(j - 1) == '*') d[j] = d[j - 1];
        if (d[j] == true)   isTrue[j] = true;
    }
    // fill up the table
    for (int i = 1; i <= lenS; i++) {
        boolean prev = d[0];    // prev stores d[i - 1][j - 1]
        d[0] = false;   // add this line for 1D reduction!!
        for (int j = 1; j <= lenP; j++) {
            boolean curr = d[j];    // curr stores d[i - 1][j]
            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?')
                d[j] = prev;
            else if (p.charAt(j - 1) == '*') {
                if (isTrue[j - 1] == true)  d[j] = true;
            } else
                d[j] = false;   // add this line for 1D reduction!!
            if (d[j] == true)   isTrue[j] = true;
            prev = curr;
        }
    }
    return d[lenP];
}

}
