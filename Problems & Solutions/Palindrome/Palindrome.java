/*****************************************
Valid Palindrome: Palindrome Number

Determine whether an integer is a palindrome. Do this without extra space.
Some hints:
Could negative integers be palindromes? (ie, -1) No.
If you are thinking of converting the integer to string, note the restriction of using extra space.
You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
There is a more generic way of solving this problem.

Solution
*****************************************/

public class Palindrome{

//1. Time ~ O(2N) where N is number of digits, Space ~ O(1)

public boolean isPalindrome(int x) {
    if (x < 0)  return false;
    int div = 1;
    while (x / div >= 10) {
        div *= 10;
    }
    int num = x;
    while (num != 0) {
        int l = num / div;
        int r = num % 10;
        if (l != r) return false;
        num = (num % div) / 10;
        div /= 100;
    }
    return true;
}

//2. Time ~ O(N), Space ~ O(1) 

public boolean isPalindrome(int x) {
    int num = x, val = 0;
    if (x < 0)  return false;
    while (num != 0) {
        val = val * 10 + num % 10;
        num = num / 10;
    }
    if (val == x)   return true;
    else            return false;
}
}