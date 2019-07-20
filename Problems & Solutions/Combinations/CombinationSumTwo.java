
/********************************************************************
Combination Sum II: each number can be used for only once

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 = a2 = … = ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 

Solution

Time ~ O(N!), Space ~ O(N) 
Very slight modification of the above algorithm:
Recursively call:
addUp(candidate, i + 1, target - candidate[i]), i = start ... N - 1

********************************************************************/

public class CombinationSumTwo{

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
            addUp(candidates, i + 1, target - candidates[i]);   // only modification
            list.remove(list.size() - 1);
        }
    }
}

}