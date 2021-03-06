/*
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
Each process only has one parent process, but may have one or more children processes. 
This is just like a tree structure. 
!!!Only one process has PPID that is 0, which means this process has no parent process.   
All the PIDs will be distinct positive integers.
We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. 
You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.

Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.

 */

import java.util.*;
public class KillProcess {
	
	/*
Time complexity : O(n^n). O(n^n) function calls will be made in the worst case 
Space complexity : O(n). The depth of the recursion tree can go upto nn.
	 */
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> res = new ArrayList<>();
		// base cases
        if (kill == 0)
            return res;
        res.add(kill);
        for(int i = 0; i < ppid.size(); i++)
            if(ppid.get(i) == kill)
                res.addAll(killProcess(pid, ppid, pid.get(i)));
        return res;
    }
	
	/*
tree simulation
	 */
	class Node {
        int val;
        List < Node > children = new ArrayList < > ();
    }
	
    public List < Integer > killProcess11(List < Integer > pid, List < Integer > ppid, int kill) {
        HashMap < Integer, Node > map = new HashMap < > ();
        for (int id: pid) {
            Node node = new Node();
            node.val = id;
            map.put(id, node);
        }
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                Node par = map.get(ppid.get(i));
                par.children.add(map.get(pid.get(i)));
            }
        }
        List < Integer > l = new ArrayList < > ();
        l.add(kill);
        getAllChildren(map.get(kill), l);
        return l;
    }
    public void getAllChildren(Node pn, List < Integer > l) {
        for (Node n: pn.children) {
            l.add(n.val);
            getAllChildren(n, l);
        }
    }
	
}
