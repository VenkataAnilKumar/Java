/*********************************************
Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example, Given s = “eceba”,
T is "ece" which its length is 3.

Solution

***********************************************/

public class LongestSubstring{

/***********************************************
1. Three pointers: Time ~ O(N), Space ~ O(1)
// two distinct chars
************************************************/

public int lengthOfLongestSubstringTwoDistinct(String s) {
    int n = s.length();
    int start = 0, last = -1, max = 0;
    for (int curr = 1; curr < n; curr++) {
        if (s.charAt(curr) != s.charAt(curr - 1)) {
            if (last >= 0 && s.charAt(curr) != s.charAt(last)) {
                max = Math.max(max, curr - start);
                start = last + 1;
            }
            last = curr - 1;
        }
    }
    return Math.max(n - start, max);
}

/************************************************
2. Two pointer + Hash Table: Time ~ O(2N), Space ~ O(1)
**********************************************?

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int[] count = new int[256]; // ASCII
    int start = 0, numDistinct = 0, maxLen = 0;
    for (int i = 0; i < s.length(); i++) {
        if (count[s.charAt(i)] == 0) numDistinct++;
        count[s.charAt(i)]++;
        
        while (numDistinct > k) {
            count[s.charAt(start)]--;
            if (count[s.charAt(start)] == 0) numDistinct--;
            start++;
        }
        
        maxLen = Math.max(i - start + 1, maxLen);
    }
    return maxLen;
}
}