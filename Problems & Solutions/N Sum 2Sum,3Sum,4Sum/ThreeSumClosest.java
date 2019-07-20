/*.............................

3Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
    For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Solution

Two pointers: Time ~ O(N^2), Space ~ O(1) 
If a + b + c == target, return sum;
Otherwise, check if | a + b + c - target | is smaller, update closest (track the closest sum) to the current sum.

...........................................*/
public class ThreeSumClosest{
public int thrSumClosest(int[] num, int target) {
    Arrays.sort(num);
    
    int closest = num[0] + num[1] + num[2];
    for (int i = 0; i < num.length - 2; i = increment(num, i)) {
        int a = num[i], lo = i + 1, hi = num.length - 1;
        while (lo < hi) {
            int b = num[lo], c = num[hi];
            int sum = a + b + c;
            if (sum == target)
                return sum;
            else if (Math.abs(target - sum) < Math.abs(target - closest))
                closest = sum;
            
            if (target > sum)   lo = increment(num, lo);
            else                hi = decrement(num, hi);
        }
    }
    return closest;
}

private int increment(int[] num, int lo) {
    while (lo < num.length - 1 && num[lo] == num[++lo]) {}
    return lo;
}
    
private int decrement(int[] num, int hi) {
    while (hi > 0 && num[hi] == num[--hi]) {}
    return hi;
}
}