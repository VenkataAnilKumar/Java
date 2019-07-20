
/******************************************
Sqrt(x)

Implement int sqrt(int x).
Compute and return the square root of x.

Solution

Binary Search: Time ~ O(logN), Space ~ O(1) 
Find a mid and check:
if x / mid < mid, move lower;
if x / mid > mid, move higher;
if x / mid == mid, return mid.
Don't compare x with mid * mid, which may lead to overflow.
Return lo.
*********************************************/

public class SqrtX{
public int sqrt(int x) {
    if (x < 2) return x;
    int lo = 1, hi = x; // lo not start from 0 to avoid divided by 0
    while (lo < hi) {
        int mid = (lo + hi) / 2;
        int div = x / mid; // don't compare x with mid * mid that will overflow
        if      (div > mid) lo = mid + 1;
        else if (div < mid) hi = mid;
        else            return mid;
    }
    return lo - 1;
}
}