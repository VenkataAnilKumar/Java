/*************************************************

Combination Sum III: sum up k elements (1 to 9) to n

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Ensure that numbers within the set are sorted in ascending order.
Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

Solution

DFS: Time ~ O(N!), Space ~ O(N)
Recursively call:
addUp(i + 1, k - 1, n - i), i = start ... 9
i + 1: candidate
k - 1: number of elements to sum up
n - i: target
*************************************************/
public class CombinationSumThree{

private List<List<Integer>> listSet = new ArrayList<List<Integer>>();
private List<Integer> list = new ArrayList<Integer>();

public List<List<Integer>> combinationSum3(int k, int n) {
    addUp(1, k, n);
    return listSet;
}

private void addUp(int start, int k, int n) {
    if (k < 0 || n < 0) return;
    else if (k == 0 && n == 0) {
        listSet.add(new ArrayList<Integer>(list));
    } else {
        for (int i = start; i <= 9; i++) {
            list.add(i);
            addUp(i + 1, k - 1, n - i);
            list.remove(list.size() - 1);
        }
    }
}

}
