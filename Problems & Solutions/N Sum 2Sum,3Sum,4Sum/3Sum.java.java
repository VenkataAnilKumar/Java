/*...........................................
3Sum

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a = b = c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},
    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)

Solution

Two pointers: Time ~ O (N ^ 2), Space ~ O (1)
First sort array, and then select num [i] (as -target), in num [i + 1 .. N -1] to find 2Sum = -target (two clamp).
Because this question may exist more than one solution, one situation is the existence of the same elements in the array, you can use the following methods to speed up and avoid duplic triplets:
Use sur (num, lo) instead of lo ++, with decrement (num, hi) instead of hi - that is, when there is a continuous element to continue lo ++ or hi - until the emergence of new elements.
.............................................*/
public class 3Sum{

	public List<List<Integer>> threeSum(int[] num) {
    	Arrays.sort(num);
    
    	List<List<Integer>> listSet = new ArrayList<List<Integer>>();        
    	for (int i = 0; i < num.length - 2; i = increment(num, i)) {
        	int a = num[i], lo = i + 1, hi = num.length - 1;
        	while (lo < hi) {
            	int b = num[lo], c = num[hi];
            	if (a + b + c == 0) {
               	 	List<Integer> list = Arrays.asList(a, b, c);
                	listSet.add(list); // if (!listSet.contains(list)) listSet.add(list);                     
                	lo = increment(num, lo); // lo++;
                	hi = decrement(num, hi); // hi--;
            	} else if (a + b + c > 0) {
                	hi = decrement(num, hi); // hi--;
            	} else if (a + b + c < 0) {
                	lo = increment(num, lo); // lo++;
            	}
        	}
    	}        
    	return listSet;
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