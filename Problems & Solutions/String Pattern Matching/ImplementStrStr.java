/****************************************
Implement strStr()

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Solution

Substring Search (txt: haystack, pat: needle), return the starting index of matched substring.
******************************************************/

public class ImplementStrStr{

//1. Brute Force: Time ~ O(MN), Space ~ O(1) 

public int strStr(String haystack, String needle) {
    int N = haystack.length();
    int M = needle.length();
    for (int i = 0; i <= N - M; i++) {
        int j;
        for (j = 0; j < M; j++)
            if (haystack.charAt(i+j) != needle.charAt(j))
                break;
        if (j == M) return i;
    }
    return -1;
}

/*********************
2. Booyer-Moore algorithm: Time ~ O(N/M), Space ~ O(1) 
No backup. Skip as many as M chars when finding one not in pattern.
Two cases:
if mismatched char not in pat, increment i one char beyond the mismatched char;
if mismatched char in pat, line up the rightmost same char in pat with the mismatched char.
Need to create a Boyer-Moore Skip Table: int[] right = new int[R].
right[c] = the rightmost index of c in pat ( -1 if c is not in pat ).
loop i : scan txt forward; From 0 to N - M, i += skip;
loop j : scan pat backward; From M - 1 to 0, j--;
if there's a mismatch: txt.charAt(i + j) != pat.charAt(j),   skip = max{1, j - right[txt.charAt(i + j)]};
if no mismatch for all j: skip == 0, the matched substring is found, and return i.
*********************************************/

public int strStr(String haystack, String needle) {
    int N = haystack.length();
    int M = needle.length();
    
    int R = 256; // ASCII
    int[] right = new int[R];
    Arrays.fill(right, -1);
    for (int j = 0; j < M; j++)
        right[needle.charAt(j)] = j;
    
    int skip;
    for (int i = 0; i <= N - M; i += skip) {
        skip = 0;
        for (int j = M-1; j >= 0; j--)
            if (haystack.charAt(i+j) != needle.charAt(j)) {
                skip = Math.max(1, j - right[haystack.charAt(i+j)]);
                break;
            }
        if (skip == 0) return i;
    }
    return -1;
}
}