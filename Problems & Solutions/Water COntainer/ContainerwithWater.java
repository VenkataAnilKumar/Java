/*******************************************************
Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container.

Solution

********************************************************/

public class ContainerwithWater{

//1. Time ~ O(N^2), Space ~ O(1) Exceed Time Limit! Find all the pairs of lines ( ~ N^2 pairs).

public int maxArea(int[] height) {
    int maxArea = 0;
    for (int i = 0; i < height.length; i++)
        for (int j = i + 1; j < height.length; j++)
            maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
    return maxArea;
}

//2. Greedy algorithm: Time ~ O(N), Space ~ O(1) 

public int maxArea(int[] height) {
    int maxArea = 0;
    int left = 0, right = height.length - 1;
    while (left < right) {
        maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
        if (height[left] < height[right])   left++;
        else                                right--;
    }
    return maxArea;
}

}