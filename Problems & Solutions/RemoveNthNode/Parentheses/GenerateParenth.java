/*************************************************************
Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
"((()))", "(()())", "(())()", "()(())", "()()()"

Solution


DFS: Time ~ O(N!), Space ~ O(N) 

************************************************************/

public class GenerateParenth{

public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    addUp(n, 0, 0, new StringBuilder(), list);
    return list;
}

private void addUp(int n, int left, int right, StringBuilder str, List<String> list) {
    if (left == n) {
        while (right < n) {
            str.append(')');
            right++;
        }
        list.add(str.toString());
    } else if (left == right) { // the parenthese in str are pairs, only add '('
        addUp(n, left + 1, right, str.append('('), list);
    } else { // the parenthese in str are not pairs, we can either add '(' or ')'
        // try to add '('
        int len = str.length();
        addUp(n, left + 1, right, str.append('('), list);
        // try to add ')'
        str.delete(len, str.length()); // remove the parenthese generated in the previous line
        addUp(n, left, right + 1, str.append(')'), list);
    }
}

}