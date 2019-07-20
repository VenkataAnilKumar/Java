/*******************************************
Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Solution
********************************************/
public class TrappingRainWater{

//1. Time ~ O(2N), Space ~ O(N) 

public int trap(int[] A) {
    int vol = 0;
    if (A.length <= 2)  return vol;
    
    // go from left to right, find the left highest bar for each bar
    int[] lmh = new int[A.length];
    lmh[0] = 0;
    int max = A[0];
    for (int i = 1; i < A.length; i++) {
        lmh[i] = max;
        if (A[i] > max) max = A[i];
    }
    
    // go from right to left, find the right highest bar for each bar
    max = A[A.length - 1];
    for (int i = A.length - 2; i >= 0; i--) {
        int left = lmh[i];
        int right = max;
        if (A[i] > max) max = A[i];
        // calculate the volume if there's a bowl shape
        int min = Math.min(left, right);
        if (min > A[i]) vol += min - A[i];
    }
    
    return vol;
}

//2. Two Pointers: Time ~ O(N), Space ~ O(1)

public int trap(int[] A) {
    int vol = 0;
    int lmh = 0, rmh = 0;
    int start = 0, end = A.length - 1;
    
    while (start < end) {
        lmh = Math.max(lmh, A[start]);
        rmh = Math.max(rmh, A[end]);
        if (lmh < rmh) {
            vol += lmh - A[start];
            start++;
        } else {
            vol += rmh - A[end];
            end--;
        }
    }
    
    return vol;        
}

//3. Two Pointers: Time ~ O(N), Space ~ O(1)

public int trap(int[] A) {
    int vol = 0;
    int start = 0, end = A.length - 1;
    int mh = 0;
    
    while (start < end) {
        int min = Math.min(A[start], A[end]);
        if (mh < min)   mh = min;
        if (A[start] < A[end])  vol += mh - A[start++];
        else                    vol += mh - A[end--];
    }
    
    return vol;
}

}