/****************************************************************

There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Solution

Time ~ O(log(M+N)), Space ~ O(1) 
Key: Given A[i], it can take constant time to check if it's the median of A and B, i.e.,
if B[j] <= A[i] <= B[j + 1] where j = mid - 1 - i (A[0 .. i -1] contains i numbers, B[0 .. j] contains j + 1 numbers, in total there are i + j + 1 numbers):
if m + n is odd, then the median is A[i];
if m + n is even, then the median is (A[i] + max{A[i - 1], B[j]}) / 2 (be careful if i and j have reached the boundary!).
if the condition is not satisfied, we need to move A[i]:
if A[i] < B[j], move A[i] to a larger one;
if A[i] > B[j + 1], move A[i] to a smaller one.

Take a binary search: i = (left + right) / 2
initialize: left = max{0, mid - n}, right = max{m - 1, mid} (make sure 0 <= j < n, consider two cases: m <= n or m > n)
search: if A[i] < B[j], left = i + 1; if A[i] > B[j + 1], right = i - 1( right = i);
termination: loop continues if left <= right (left < right,?? A[i] number left = rigth = i ).
Note: The above procedure can only found the median in A. If the median is in B, then no median is found after searching A. In such case, we need to take another binary search on B.

****************************************************************/

public class MedianSortedArrays{

public double findMedianSortedArrays(int A[], int B[]) {
    int m = A.length, n = B.length, mid = (m + n)/2;
    boolean even = ((m + n) & 1) == 0;
    // left and right use to find the median of A[] 
    // (m < n: left = 0, right = m - 1; m > n: left = mid - n, right = mid)
    int left = Math.max(0, mid - n), right = Math.min(m - 1, mid);
    while (left <= right) { // binary search in A[]
        int i = (left + right) / 2, j = mid - 1 - i;        
        if      (j >= 0    && A[i] < B[j])      // if A[i]<median, get a larger A[i] (increase left)
            left = i + 1;
        else if (j < n - 1 && A[i] > B[j + 1])  // if A[i]>median, get a smaller A[i] (decrease right)  
            right = i - 1;  
        else {                                  // if B[j] <= A[i] <= B[j+1], median is found
            if      (!even)             // m+n is odd, return A[i]
                return A[i];                                    
            else if (j < 0 || j >= n)   // m+n is even, the search of B[] is finished
                return (A[i] + A[i - 1])/2.0;                   
            else if (i == 0)            // m+n is even, the search of A[] is finished
                return (A[i] + B[j])/2.0;                       
            else                        // m+n is even, both A[] and B[] haven't finished search
                return (A[i] + Math.max(B[j], A[i - 1]))/2.0;   
        }
    }
    return findMedianSortedArrays(B, A); // binary search in B[] when the search of A[] is finished
}
}