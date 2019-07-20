/*********************************************
Valid Palindrome: Palindrome String

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define empty string as valid palindrome.

Solution
*********************************************/

public class PalindromString{
//1. Time ~ O(2N), Space ~ O(N) 

public boolean isPalindrome(String s) {        
    if (s == null) return true;    // null string
    int N = s.length();
    String sLC = s.toLowerCase();
    char[] str = new char[N];
    int Nc = 0;
    for (int i = 0; i < N; i++) {
        char c = sLC.charAt(i);
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
            str[Nc++] = c;
    }
    for (int j = 0; j < Nc/2; j++)
        if (str[j] != str[Nc-j-1])   return false;
    return true;
}

//2. Time ~ O(N), Space ~ O(1) 

public boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
        while (i < j && !Character.isLetterOrDigit(s.charAt(i)))  i++;
        while (i < j && !Character.isLetterOrDigit(s.charAt(j)))  j--;
        if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
            return false;
        i++; j--;
    }
    return true;
}
}