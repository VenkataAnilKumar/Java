/******************************************************************

Letter Combinations of a Phone Number

Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note: Although the above answer is in lexicographical order, your answer could be in any order you want.

Solution

DFS: Time ~ O(N!), Space ~ O(N) 
******************************************************************/

public class CombinationPhoneNumber{

private static final Map<Character, Character[]> map = new HashMap<Character, Character[]>() {{
    put('2', new Character[]{'a', 'b', 'c'});
    put('3', new Character[]{'d', 'e', 'f'});
    put('4', new Character[]{'g', 'h', 'i'});
    put('5', new Character[]{'j', 'k', 'l'});
    put('6', new Character[]{'m', 'n', 'o'});
    put('7', new Character[]{'p', 'q', 'r', 's'});
    put('8', new Character[]{'t', 'u', 'v'});
    put('9', new Character[]{'w', 'x', 'y', 'z'});
}};

public List<String> letterCombinations(String digits) {
    List<String> set = new ArrayList<>();
    if (!digits.isEmpty())  addUp(0, digits, new StringBuilder(), set);
    return set;
}

private void addUp(int start, String digits, StringBuilder str, List<String> set) {
    if (str.length() == digits.length())    set.add(str.toString());
    else {
        for (int i = start; i < digits.length(); i++) {
            for (char c : map.get(digits.charAt(i))) {
                str.append(c);
                addUp(i + 1, digits, str, set);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }
}

}