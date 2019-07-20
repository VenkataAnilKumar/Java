/**************************************************
Implement atoi to convert a string to an integer.
Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

Solution

Time ~ O(N), Space ~ O(1) 
**************************************************/

public class StringtoInt{

private static final int MAXDiv10 = Integer.MAX_VALUE / 10;

public int atoi(String str) {
    int i = 0, N = str.length(), sign = +1;
    // skip the whitespace
    while (i < N && Character.isWhitespace(str.charAt(i)))  i++;
    // find sign
    if (i < N && str.charAt(i) == '+') {
        sign = +1;
        i++;
    } else if (i < N && str.charAt(i) == '-') {
        sign = -1;
        i++;
    }
    
    // convert value (handle out-of-range case)s
    int value = 0;
    while (i < N && Character.isDigit(str.charAt(i))) {
        int digit = Character.getNumericValue(str.charAt(i));
        if (value > MAXDiv10 || value == MAXDiv10 && digit > 7) {
            return sign == +1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        value = value * 10 + digit;
        i++;
    }
    
    return value * sign;
}
}