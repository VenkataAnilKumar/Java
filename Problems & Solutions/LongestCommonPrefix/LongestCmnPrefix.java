
/*******************************************************88
Write a function to find the longest common prefix string amongst an array of strings.

Solution

Time ~ O(N), Space ~ O(1) 

*******************************************************88

public LongestCmnPrefix{

public String longestCommonPrefix(String[] strs) {
    if (strs.length == 1)   return strs[0];
    
    // Compare each two adjacent pairs
    String prefix = "";
    int minPrefix = Integer.MAX_VALUE;
    for (int i = 1; i < strs.length; i++) {            
        int maxPrefix = 0;
        // compare two adjacent pairs
        int m = strs[i - 1].length(), n = strs[i].length();
        for (int j = 0; j < Math.min(Math.min(m, n), minPrefix); j++) {
            if (strs[i - 1].charAt(j) == strs[i].charAt(j))     maxPrefix++;
            else                                                break;
        }
        if (maxPrefix < minPrefix) {
            minPrefix = maxPrefix;
            prefix = strs[i - 1].substring(0, minPrefix);
        }
    }
    return prefix;
}
}