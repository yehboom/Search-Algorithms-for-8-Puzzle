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
import java.util.ArrayList;
public class Node {
	private ArrayList<Node> childrenList;
    private String state;
    private Node parent;
    private int cost;
    private int totalCost;
    
    // Constructor for node
    public Node(String state) {
        this.state = state;
        childrenList = new ArrayList<Node>();
    }
    
    //receive parent to set this class parent
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    //get parent and return
    public Node getParent() {
        return parent;
    }
    
    //receive the cost and set it to this node cost
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    //get cost and return
    public int getCost() {
        return cost;
    }
    
    //reveive total cost and set totoal cost to this node
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }  
    
    //get the total cost and return
    public int getTotalCost() {
        return totalCost;
    }

    //if it needs to add g and h
    public void setTotalCost(int g_cost, int h_cost) {
        this.totalCost = g_cost + h_cost;
    }

    //set a string of state
    public void setState(String puzzle_state) {
        this.state = puzzle_state;
    }
    
    // get the state and return
    public String getState() {
        return state;
    }
    
    //get children and return
    public ArrayList<Node> getChildren() {
        return childrenList;
    }
    //add child to this node children list
    public void addChild(Node child) {
    	childrenList.add(child);
    }
    

}