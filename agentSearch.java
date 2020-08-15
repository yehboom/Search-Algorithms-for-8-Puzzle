/*--------------------------------------------------------
1.Tsai-Ting,Yeh 2020/04/22

2.java build 1.8.0_191 

3.(before you start it need to change the path the data folder)
command line compilation examples:

>javac eight_puzzle.java

4.examples to run this program:

In shell window:

> java eight_puzzle


6. The file submit and you need for the program.

 a. eight_puzzle.java
 b. agentSearch.java
 c. Node.java
 d. NodeSuccessors.java
 
7. Note:
First, use the "javac eight_puzzle.java" in the shell window command line and continue type "java eight_puzzle" in the shell window,
it will start the whole program. Second, in the shell window, you will see a text menu, which you can choose the mode to start the initial state of 
the puzzle(just type 1 or 2 or 3 or quit to stop). After that, it will give you the second menu to choose the algorithm.

For example, when you start running the program in the shell. It will show you:

Eight puzzle is starting up.
Choose the starting States number: [1]easy [2]medium [3]hard or type 'quit' to end the program
1
You choose :1
Please continue to choose the search algorithm: 
[1]Breadth-first [2]Depth-first [3]Uniform-Cost [4]Greedy Best-first [5]A* v1 [6]A* v2
2
...
after that you will see the puzzle movement and the result

----------------------------------------------------------*/
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class agentSearch {
	private String goalSate;
	private Node root;
	//set root
    public void setRoot(Node root) {
        this.root = root;
    }
	//get root
    public Node getRoot() {
        return root;
    }
    //set goal state
    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }
    //get goal state
    public String getGoalSate() {
        return goalSate;
    }
    //constructor
    public agentSearch(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }
    
    
//BFS 
    public boolean breadth_First_Search() {
    	long start = System.currentTimeMillis();
		long end = start + 300*1000; // 300(5 min) seconds * 1000 ms/sec
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	//use queue since it is FIFO
	    	Queue<Node> frontier = new LinkedList<Node>();
	    	Node node = new Node(root.getState());
	    	//create a new node with root's state and add it to the frontier
	    	frontier.add(node);
	    	 
	    	Set<String> explored = new HashSet<String>();    	
	    	Node currentNode;
	    	while(!frontier.isEmpty()) {
	    		//maintain the max frontier size
	    		if(frontier.size()>maxFrontierSize) {
	    			maxFrontierSize = frontier.size();
	    		}
	    		//poll from queue
	    		currentNode=frontier.poll();
	    		//add it to already explore for later check
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(1,currentNode,maxFrontierSize,root, count);  			
	    			 return true;
	    		}
	    		//create a new nodeSuccessors to retrieve the list of state
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	            //put unexpanded node into queue
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
		                //if explored does not have this arrangement and add it!
		                explored.add(n);
		                //parent already check, so now add new child
		                Node child = new Node(n);
		                currentNode.addChild(child);
		                child.setParent(currentNode);
		                //add it to the queue and wait next time to expand
		                frontier.add(child);
	                }
	            }
	            count=count+1;	            
	    	}
	    	//if the frontier is empty return false
	    	return false;			
		}
		//if running time is over 5 mins and return false
		return false;
    	
    }
  
//DFS
    public boolean depth_First_Search() {
    	long start = System.currentTimeMillis();
		long end = start + 300*1000; // 300(5 min) seconds * 1000 ms/sec
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	//use stack to implement, since stack is LIFO
	    	Stack<Node> frontier=new Stack<Node>();
	    	Node node = new Node(root.getState());
	    	frontier.push(node);
	    	Node currentNode = node;
	    	
	    	Set<String> explored = new HashSet<String>();
	    	while(!frontier.isEmpty()) {
	    		//Keep track of the max size of frontier
	    		if(frontier.size()>maxFrontierSize) {
	    			maxFrontierSize = frontier.size();
	    		}
	    		currentNode=frontier.pop();
	    		//add current node's state to the explored list
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(2,currentNode, maxFrontierSize, root, count);   			
	    			 return true;
	    		}
	    		//create a new nodeSuccessors to retrieve the list of state
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	    		//put unexpanded node into frontier
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
	                //if explored does not have this arrangement and add it!
	                explored.add(n);
	                //parent already check, so now add new child
	                Node child = new Node(n);
	                currentNode.addChild(child);
	                child.setParent(currentNode);
	                //add it to the queue and wait next time to expand
	                frontier.push(child);
	                }
	            }
	            count=count+1;
	    	}
	    	//if the frontier is empty return false
	    	return false;		
		}
		//if running time is over 5 mins and return false
		return false;
    }
   
    
    //uniform Cost Search
    public boolean uniform_Cost_Search() {
    	
    	long start = System.currentTimeMillis();
		long end = start + 300*1000; // 300(5 min) seconds * 1000 ms/sec
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	int tempParentCost=0;
	    	int tempParentIndex=-1;
	    	char tempChar;
	    	Set<String> explored = new HashSet<String>();
	    	Node currentNode;
	    	
	    	Node node = new Node(root.getState());	    	 
	    	//put an comparator(have compare rule) for priority queue for sort the pq
	    	//and 30 is the initial value for PriorityQueue's size
	    	PriorityQueue<Node> pq = new PriorityQueue<Node>(30, new PQComparator());
	    	pq.add(node);
	    	
	    	while(!pq.isEmpty()) {
	    		//maintain the queue's size
	    		if(pq.size()>maxFrontierSize) {
	    			maxFrontierSize = pq.size();
	    		}
	    		//choose the lowest-cost node in the frontier
	    		currentNode=pq.poll();
	    		//add the current node into explore and wait for later check
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(3,currentNode,maxFrontierSize, root, count);
	    			 return true;
	    		}
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	            //put unexpanded node into queue
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
	                //if explored does not have this arrangement and add it!
	                explored.add(n);
	                //parent already check, so now add new child
	                Node child = new Node(n);
	                currentNode.addChild(child);
	                child.setParent(currentNode);
	                tempParentCost=currentNode.getTotalCost();
	                //find partent's 0 index and put it to child, so it can find which number switch with parent's 0
	                tempParentIndex=currentNode.getState().indexOf('0');
	                tempChar=child.getState().charAt(tempParentIndex);
	                //after parse the char into int, since the cost depending on puzzle block number
	                int tempCost=Integer.parseInt(String.valueOf(tempChar));
	                //child set the cost and wait to next call
	                child.setTotalCost(tempParentCost+tempCost,0);
	                
	                //add it to the priority queue, sort it and later poll the lowest cost node
	                pq.add(child);
	                }
	            }
	            count=count+1;
	            
	    	}
	    	//if pq is empty return false
	    	return false;
		}
		//if the time is over than 5 mins return false
		return false;
    	
    }
  
  
    //Greedy best_first_search
    public boolean best_First_Search() {
    	long start = System.currentTimeMillis();
    	// 300(5 min) seconds * 1000 ms/sec
		long end = start + 300*1000; 
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	//Queue<Node> frontier = new LinkedList<Node>();
	    	Node node = new Node(root.getState());
	    	//put an comparator(have compare rule) for priority queue for sort the pq
	    	//and 30 is the initial value for PriorityQueue's size
	    	PriorityQueue<Node> pq = new PriorityQueue<Node>(30, new PQComparator());
	    	//add node have root state to the priority queue
	    	pq.add(node);
	    	Set<String> explored = new HashSet<String>();
	    	
	    	Node currentNode;
	    	//keep track the max size of pq
	    	while(!pq.isEmpty()) {
	    		if(pq.size()>maxFrontierSize) {
	    			maxFrontierSize = pq.size();
	    		}
	    		//choose the lowest-cost node in the frontier
	    		currentNode=pq.poll();
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(4,currentNode,maxFrontierSize, root, count);
	    			 return true;
	    		}
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	            //put unexpanded node into queue
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
	                //if explored does not have this arrangement and add it!
	                explored.add(n);
	                //parent already check, so now add new child
	                Node child = new Node(n);
	                currentNode.addChild(child);
	                child.setParent(currentNode);                
	                //child set the cost and wait to next call
	                child.setTotalCost(0,heuristic_1(child.getState(), goalSate));
	                //add it to the priority queue, sort it and later poll the lowest cost node
	                pq.add(child);
	                }
	            }
	            count=count+1;
	            
	    	}
	    	//if pq is empty so return false
	    	return false;
		}
		//if the time is over than 5 mins return false
		return false;

    }
    
    //A* version01
    public boolean a_Star_version01() {
    	long start = System.currentTimeMillis();
		long end = start + 300*1000; // 300(5 min) seconds * 1000 ms/sec
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	int tempParentCost=0;
	    	int tempParentIndex=-1;
	    	char tempChar;
	    	
	    	Node node = new Node(root.getState());
	    	//put an comparator(have compare rule) for priority queue for sort the pq
	    	//and 30 is the initial value for PriorityQueue's size
	    	PriorityQueue<Node> pq = new PriorityQueue<Node>(30, new PQComparator());
	    	pq.add(node);
	    	 
	    	Set<String> explored = new HashSet<String>();
	    
	    	Node currentNode;
	    	
	    	while(!pq.isEmpty()) {
	    		//keep track of the max size of pq
	    		if(pq.size()>maxFrontierSize) {
	    			maxFrontierSize = pq.size();
	    		}
	    		//choose the lowest-cost node in the frontier
	    		currentNode=pq.poll();
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(5,currentNode,maxFrontierSize, root, count);		
	    			 return true;
	    		}
	    		
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	            //put unexpanded node into queue
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
	                //if explored does not have this arrangement and add it!
	                explored.add(n);
	                //parent already check, so now add new child
	                Node child = new Node(n);
	                currentNode.addChild(child);
	                child.setParent(currentNode);
	                tempParentCost=currentNode.getTotalCost();
	                //find partent's 0 index and put it to child, so it can find which number switch with parent's 0
	                tempParentIndex=currentNode.getState().indexOf('0');
	                tempChar=child.getState().charAt(tempParentIndex);
	                //after parse the char into int, since the cost depending on puzzle block number
	                int tempCost=Integer.parseInt(String.valueOf(tempChar));
	                //child set the cost and wait to next call
	                child.setTotalCost(tempParentCost+tempCost,heuristic_1(child.getState(), goalSate));
	                
	                //add it to the priority queue, sort it and later poll the lowest cost node
	                pq.add(child);
	                }
	            }
	            count=count+1;
	            
	    	}
	    	return false;
		}
		//if the time is over than 5 mins return false
		return false;
    	
    	
    	
    }

    
  //A* version02
    public boolean a_Star_version02() {
    	long start = System.currentTimeMillis();
    	//300(5 min) seconds * 1000 ms/sec
		long end = start + 300*1000; 
		//if it run more than 5 mins and stop it
		while(start < end) {
			int count=0;
	    	int maxFrontierSize=0;
	    	int tempParentCost=0;
	    	int tempParentIndex=-1;
	    	char tempChar;
	    	
	    	//create a node with the initial state
	    	Node node = new Node(root.getState());
	    	//put an comparator(have compare rule) for priority queue for sort the pq
	    	//and 30 is the initial value for PriorityQueue's size
	    	PriorityQueue<Node> pq = new PriorityQueue<Node>(30, new PQComparator());
	    	pq.add(node);
	    	 
	    	Set<String> explored = new HashSet<String>();
	    
	    	Node currentNode;
	    	while(!pq.isEmpty()) {
	    		//keep track the max size of pq
	    		if(pq.size()>maxFrontierSize) {
	    			maxFrontierSize = pq.size();
	    		}
	    		//choose the lowest-cost node in the frontier
	    		currentNode=pq.poll();
	    		explored.add(currentNode.getState());
	    		//if in the loop we get the answer call successor's print and return true
	    		if(currentNode.getState().equals(goalSate)) {
	    			 NodeSuccessors.print(6,currentNode,maxFrontierSize, root, count);
	    			 return true;
	    		}
	    		
	    		List<String> nodeSuccessors = NodeSuccessors.getSuccessors(currentNode.getState());
	            //put unexpanded node into queue
	            //iteration the list to get different arrangement
	            for (String n : nodeSuccessors) {
	                if (!explored.contains(n)) {
	                //if explored does not have this arrangement and add it!
	                explored.add(n);
	                //parent already check, so now add new child
	                Node child = new Node(n);
	                currentNode.addChild(child);
	                child.setParent(currentNode);
	                tempParentCost=currentNode.getTotalCost();
	                //find partent's 0 index and put it to child, so it can find which number switch with parent's 0
	                tempParentIndex=currentNode.getState().indexOf('0');
	                tempChar=child.getState().charAt(tempParentIndex);
	                //after parse the char into int, since the cost depending on puzzle block number
	                int tempCost=Integer.parseInt(String.valueOf(tempChar));
	                //child set the cost and wait to next call
	                child.setTotalCost(tempParentCost+tempCost,heuristic_2(child.getState(), goalSate));
	                
	                //add it to the priority queue, sort it and later poll the lowest cost node
	                pq.add(child);
	                }
	            }
	            count=count+1;
	            
	    	}
	    	//is pq is empty, and return false
	    	return false;
		}
		//if the time is over than 5 mins return false
		return false;
    	
    }
    
    
    //heuristic_1 count difference index between the original state and goalState
    //so it can get the number of misplaced tiles
    private int heuristic_1(String State, String goalSate) {
        int missPlace = 0;
        //iterate the state each index to get char
        for (int i = 0; i < State.length(); i += 1) {
        	//if the char in the state's is not equal goalState's char, the counter ++
            if (State.charAt(i) != goalSate.charAt(i)) {
            	missPlace += 1;
                }
        }
        return missPlace;
    }
   
    
    //heuristic_2 use the cloumn and row's difference of originally state and goal state 
    //to find each tile need to move to target place's Manhathan distance
    private int heuristic_2(String State, String goalSate) {
        int manhathan_distance = 0;
        
        //i use to iterate in the original state and j use to iterate the goalState's target index 
        for (int i = 0; i < State.length(); i += 1) {
            for (int j = 0; j < goalSate.length(); j += 1) {
            	
            	//compare the state's char in which index in goalstate
                if (State.charAt(i) == goalSate.charAt(j)) {
                	//'%' check state's i and goalSate's j in which column
                	//'/'check state's i and goalSate's j in which row
                	//so when we get the column and row, we can get the manhathan distance
                	//add them together, and wait to return
                	manhathan_distance = manhathan_distance + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
                	}
            }
        }
        return manhathan_distance;
    }

    
    //for Uniform-Cost priority queue's comparator
    class PQComparator implements Comparator<Node>{           
        // Overriding compare()method of Comparator  
        public int compare(Node n1, Node n2) { 
            if (n1.getTotalCost() < n2.getTotalCost()) { 
                return -1; 
            }else if (n1.getTotalCost() > n2.getTotalCost()) {
                return 1; 
            }else {
            	 return 0; 
            }
       } 
    }
}
