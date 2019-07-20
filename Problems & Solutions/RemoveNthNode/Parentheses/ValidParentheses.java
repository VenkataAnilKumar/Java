/****************************************************
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Solution

Use a Stack: Time ~ O(N), Space ~ O(1) 
****************************************************/

public class ValidParentheses{

public static final Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (Character c : s.toCharArray()) {
        if (stack.isEmpty() || map.containsKey(c)) {
            stack.push(c);
        }
        else {
            if (!c.equals(map.get(stack.pop()))) return false;
        }
    }
    return stack.isEmpty();
}

}