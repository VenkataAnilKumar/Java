/*..............................
4Sum

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a = b = c = d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)

Solution
Two pointers: Time ~ O (N ^ 3), Space ~ O (1)
Similar to 3Sum, two for loop, given num [i] and num [j] (num [i] + num [j] = -target), in num [j + 1 .. N -1] -target.
.......................................*/

public FourSumm{

public List<List<Integer>> fourSum(int[] num, int target) {
    List<List<Integer>> listSet = new ArrayList<List<Integer>>();
    if (num.length < 4) return listSet;
    
    Arrays.sort(num);
    
    for (int i = 0; i < num.length - 3; i = increment(num, i)) {
        for (int j = i + 1; j < num.length - 2; j = increment(num, j)) {
            int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;
            while (lo < hi) {
                int c = num[lo], d = num[hi];
                int sum = a + b + c + d;
                if (sum == target) {
                    List<Integer> list = Arrays.asList(a, b, c, d);
                    listSet.add(list);
                    lo = increment(num, lo);
                    hi = decrement(num, hi);
                } else if (sum < target) {
                    lo = increment(num, lo);
                } else {
                    hi = decrement(num, hi);
                }
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