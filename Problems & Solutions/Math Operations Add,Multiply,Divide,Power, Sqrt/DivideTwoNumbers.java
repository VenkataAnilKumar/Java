/*************************************************
Divide Two Integers
Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT.

Solution

Bit Manipulation: Time ~ O(logNa), Space ~ O(1) 
Find a N such that:
divisor * 2^N < dividen < divisor * 2^(N + 1)
Then,
result += 2^N;
dividen -= divisor * 2^N;
Repeat until dividen < divisor.
Overflow (i.e., Integer.MIN_VALUE / -1 = Integer.MAX_VALUE): Cast int to long, and do the division of abs first, and then check the sign.
If divisor == 0, throw an exception.
***************************************************/

public class DivideTwoNumbers{
public int divide(int dividend, int divisor) {
    if (divisor == 0)   throw new IllegalArgumentException("divisor can't be 0!");
    if (divisor == 1)   return dividend;
    if (divisor == -1)  return (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -dividend;
    
    // convert int to long, otherwise Math.abs(Integer.MIN_VALUE) will overflow
    long p = Math.abs((long) dividend);
    long q = Math.abs((long) divisor);
    
    long res = 0;
    while (p >= q) {
        int count = 0;
        while (p >= (q << count)) // q*2^n < p < q*2^(n+1)
            count++;
        res += (long)1 << (count - 1); // add 2^n
        p -= q << (count - 1);   // p <= p - q*2^n
    }
    
    if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
        return (res > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) res;
    else
        return (int)-res;
}
}