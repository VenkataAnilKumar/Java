/*****************************************************
Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

Solution

1-d DP: Time ~ O(N), Space ~ O(N) 
Let d(i) be the length of valid parentheses in str[i ... N - 1] (parentheses starts from str[i]).
str[ i, i + 1 .. j - 1,  j,  j + 1.. N - 1 ]
              d(i + 1)            d(j + 1)         where j = i + d(i + 1) + 1
If str[ i ] = ')',   d(i) = 0
Otherwise,     d(i) = ( str[ j ] == ')' ? (d(i + 1) + 2  +  d(j + 1)) : 0.
*****************************************************/

public class LongestValidParenth{

public int longestValidParentheses(String s) {
        // d(i): length of the longest valid parentheses starting from s[i], i = N-1...0
        // d(i) = 0 if s[i] == ')'
        // d(i) = d(i+1) + 2 + d(i+1+d(i+1)+1) if s[i] == '(' and s[i+1+d(i+1)] == ')'
        int n = s.length();
        if (n < 2) return 0;
        int max = 0;
        int[] d = new int[n];
        d[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + 1 + d[i + 1];
                if (j < n && s.charAt(j) == ')') {
                    d[i] = d[i + 1] + 2;
                    if (j + 1 < n)
                        d[i] += d[j + 1];
                }
            }
            max = Math.max(max, d[i]);
        }
        return max;
    }
}