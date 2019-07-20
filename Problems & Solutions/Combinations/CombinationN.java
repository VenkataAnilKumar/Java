/*********************************************************
Combinations: c(n, k)

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
For example,
If n = 4 and k = 2, a solution is:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Solution
**********************************************************/

public class CombinationN{

DFS: Time ~ O(N!), Space ~ O(N) 
private List<List<Integer>> listSet = new ArrayList<List<Integer>>();
private List<Integer> list = new ArrayList<>();

public List<List<Integer>> combine(int n, int k) {
    addUp(1, n, k);
    return listSet;
}

private void addUp(int start, int n, int k) {
    if (list.size() == k)   listSet.add(new ArrayList<Integer>(list));
    else {
        for (int i = start; i <= n; i++) {
            list.add(i);
            addUp(i + 1, n, k);
            list.remove(list.size() - 1);
        }
    }
}

}