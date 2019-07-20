/******************************************************************
Combination Sum I: each number can be used for unlimited times

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 = a2 = … = ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

Solution

Time ~ O(N!), Space ~ O(N) 
1. Sort the candidate array first so as to generate non-descending order results.
2. If target > 0, recursively call:
addUp(candidate, i, target - candidate[i]), i = start ... N - 1
    If target == 0, a combination is found, and add list to listSet.
    If target < 0, a combination is not found, just return and remove previous added element.
3. Avoid duplicated combinations (two ways):
skip candidate[i] if candidate[i] == candidate[i - 1], i > start; (Backtracking)
don't add the list if listSet.contains(list). (DFS)
******************************************************************/

public class CombinationSumOne{

private List<List<Integer>> listSet = new ArrayList<List<Integer>>();
private List<Integer> list = new ArrayList<Integer>();

public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    addUp(candidates, 0, target);
    return listSet;
}

private void addUp(int[] candidates, int start, int target) {
    if      (target < 0)    return;
    else if (target == 0) {
        /** if (!listSet.contains(list)) **/    // No need to check duplicate lists!!    
            listSet.add(new ArrayList<Integer>(list));
    } else {
        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;  // avoid duplicated list: another alternative (command the 3rd line above)
            list.add(candidates[i]);
            addUp(candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}

}