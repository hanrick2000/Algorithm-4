/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
*/

class Solution {
    /*
    the brute force solution, go through every subarray and find the min length one that meets requirement
    time: O(n^2), space: O(1)
    */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int cur = 0;
            for (int end = start; end < nums.length; end++) {
                cur += nums[end];
                if (cur >= s) {
                    minLen = Math.min(minLen, end - start + 1);
                    break;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    /*
    optimized brute force: increment start pointer when there is no better solution at the current start pointer
    time still O(n^2)
    */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int currSum = 0;
            for (int end = start; end < nums.length; end++) {
                if (end - start + 1 >= minLen) {  // some small optimization
                    break;
                }
                currSum += nums[end];
                if (currSum >= s) {
                    minLen = Math.min(minLen, end - start + 1);
                    break;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    /*
    two pointers:
    We could keep 2 pointer,one for the start and another for the end of the current subarray, 
    and make optimal moves so as to keep the sum greater than ss as well as maintain the lowest size possible.
    
    time: O(2n) sicne each element can be visited atmost twice, once by the end pointer and (atmost) once by the start pointer.
    space: O(1)
    */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int sum = 0;
        for (int end = 0; end < n; end++) {
            sum += nums[end];
            while (sum >= s && start <= end) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start++];
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
