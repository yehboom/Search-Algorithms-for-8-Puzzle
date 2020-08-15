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
import java.util.List;
import java.util.Stack;

public class NodeSuccessors {
	public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<String>();

        //make the puzzle's 0 move, and find it switch to which numbers
        switch (state.indexOf("0")) {
        	//if 0 is in the index 0
            case 0: {
                //-------------move index 0 down to index 3------------ 	                
                String tempState4=state;
            	char tempc4=tempState4.charAt(3);
            	tempState4=tempState4.replace(tempState4.charAt(0), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(3), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);
            	//-------------move index 0 right to index 1------------ 	                
                String tempState3=state;
            	char tempc3=tempState3.charAt(1);
            	tempState3=tempState3.replace(tempState3.charAt(0), 'x');	            	
            	tempState3=tempState3.replace(tempState3.charAt(1), '0');	            	
            	tempState3=tempState3.replace('x', tempc3);
                successors.add(tempState3);
                break;
            }
          //if 0 is in the index 1
            case 1: {
            	//-------------move index 1 left to index 0------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(0);
            	tempState2=tempState2.replace(tempState2.charAt(1), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(0), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
            	
                //-------------move index 1 right to index 2------------ 	                
                String tempState3=state;
            	char tempc3=tempState3.charAt(2);
            	tempState3=tempState3.replace(tempState3.charAt(1), 'x');	            	
            	tempState3=tempState3.replace(tempState3.charAt(2), '0');	            	
            	tempState3=tempState3.replace('x', tempc3);
                successors.add(tempState3);
                
                //-------------move index 1 down to index 4------------ 	                
                String tempState4=state;
            	char tempc4=tempState4.charAt(4);
            	tempState4=tempState4.replace(tempState4.charAt(1), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(4), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);                
                break;
            }
          //if 0 is in the index 2
            case 2: {
            	//-------------move index 2 left to index 1------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(1);
            	tempState2=tempState2.replace(tempState2.charAt(2), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(1), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
                
                //-------------move index 2 down to index 5------------  
                String tempState4=state;
            	char tempc4=tempState4.charAt(5);
            	tempState4=tempState4.replace(tempState4.charAt(2), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(5), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);                
                break;
            }
          //if 0 is in the index 3
            case 3: {
            	//-------------move index 3 up to index 0------------
            	String tempState=state;
            	char tempc=tempState.charAt(0);
            	tempState=tempState.replace(tempState.charAt(3), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(0), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);
            	
                //-------------move index 3 right to index 4------------ 	                
                String tempState3=state;
            	char tempc3=tempState3.charAt(4);
            	tempState3=tempState3.replace(tempState3.charAt(3), 'x');	            	
            	tempState3=tempState3.replace(tempState3.charAt(4), '0');	            	
            	tempState3=tempState3.replace('x', tempc3);
                successors.add(tempState3);

                //-------------move index 3 down to index 6------------ 	                
                String tempState4=state;
            	char tempc4=tempState4.charAt(6);
            	tempState4=tempState4.replace(tempState4.charAt(3), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(6), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);
                
                break;
            }
          //if 0 is in the index 4
            case 4: {
            	//-------------move index 4 up to index 1------------
            	String tempState=state;
            	char tempc=tempState.charAt(1);
            	tempState=tempState.replace(tempState.charAt(4), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(1), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);
                
                //-------------move index 4 left to index 3------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(3);
            	tempState2=tempState2.replace(tempState2.charAt(4), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(3), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
             
                //-------------move index 4 right to index 5------------ 	                
                String tempState3=state;
            	char tempc3=tempState3.charAt(5);
            	tempState3=tempState3.replace(tempState3.charAt(4), 'x');	            	
            	tempState3=tempState3.replace(tempState3.charAt(5), '0');	            	
            	tempState3=tempState3.replace('x', tempc3);
                successors.add(tempState3);
                
              //-------------move index 4 down to index 7------------ 	                
                String tempState4=state;
            	char tempc4=tempState4.charAt(7);
            	tempState4=tempState4.replace(tempState4.charAt(4), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(7), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);
        
                break;
            }
          //if 0 is in the index 5
            case 5: {
            	//-------------move index 5 up to index 2------------ 
            	String tempState=state;
            	char tempc=tempState.charAt(2);
            	tempState=tempState.replace(tempState.charAt(5), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(2), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);
            	
               //-------------move index 5 left to index 4------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(4);
            	tempState2=tempState2.replace(tempState2.charAt(5), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(4), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
                
                //-------------move index 5 down to index 8------------        
                String tempState4=state;
            	char tempc4=tempState4.charAt(8);
            	tempState4=tempState4.replace(tempState4.charAt(5), 'x');	            	
            	tempState4=tempState4.replace(tempState4.charAt(8), '0');	            	
            	tempState4=tempState4.replace('x', tempc4);
                successors.add(tempState4);
                
                break;
            }
          //if 0 is in the index 6
            case 6: {
            	//-------------move index 6 up to index 3------------ 
            	String tempState=state;
            	char tempc=tempState.charAt(3);
            	tempState=tempState.replace(tempState.charAt(6), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(3), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);
                	               
              //-------------move index 6 right to index 7------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(7);
            	tempState2=tempState2.replace(tempState2.charAt(6), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(7), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
                
                break;

            }
          //if 0 is in the index 7
            case 7: {
                //-------------move index 0 up to index 4------------ 
            	String tempState=state;
            	char tempc=tempState.charAt(4);
            	tempState=tempState.replace(tempState.charAt(7), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(4), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);
                
              //-------------move index 0 left to index 6------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(6);
            	tempState2=tempState2.replace(tempState2.charAt(7), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(6), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
                
              //-------------move index 0 right to index 8------------         
                String tempState3=state;
            	char tempc3=tempState3.charAt(8);
            	tempState3=tempState3.replace(tempState3.charAt(7), 'x');	            	
            	tempState3=tempState3.replace(tempState3.charAt(8), '0');	            	
            	tempState3=tempState3.replace('x', tempc3);
                successors.add(tempState3);
                
                break;
            }
          //if 0 is in the index 8
            case 8: {
                //-------------move index 8 up to index 5------------ 
            	String tempState=state;
            	char tempc=tempState.charAt(5);
            	tempState=tempState.replace(tempState.charAt(8), 'x');	            	
            	tempState=tempState.replace(tempState.charAt(5), '0');	            	
            	tempState=tempState.replace('x', tempc);
                successors.add(tempState);

                //-------------move index 8 left to index 7------------ 
                String tempState2=state;
            	char tempc2=tempState2.charAt(7);
            	tempState2=tempState2.replace(tempState2.charAt(8), 'x');	            	
            	tempState2=tempState2.replace(tempState2.charAt(7), '0');	            	
            	tempState2=tempState2.replace('x', tempc2);
                successors.add(tempState2);
                
                break;
            }
        }

        return successors;


    }
 //use this function to print out the result
 public static void print(int searchMode, Node goalNode,int queueMax, Node root, int time) {
	 	int totalCost_1 = 0;
        int totalCost_2 = 0;
        int displayTotalCost =0;
        int cost = 0;
        String originalState = root.getState();
        String targetState;

        //use stack since we start from the end, we push the goal node first
        //and continue the node's parent would on the top of stack and so on... 
        Stack<Node> solution = new Stack<Node>();
        solution.push(goalNode);
        
        //compare the goal Node's(and it's ancestor) state is equal to the root state or not
        //so we can record the solution path
        while (!goalNode.getState().equals(root.getState())) {
        	solution.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
       
       
        for (int i = solution.size() - 1; i >= 0; i--) {
            System.out.println("============================================");
            //retrieve the data from solution
            targetState = solution.get(i).getState();
            //if original state is not wqual to target state
            if (!originalState.equals(targetState)) {
            	//print the movement and the number
                System.out.println("Move " + targetState.charAt(originalState.indexOf('0')) + " " + moveChoose(originalState, targetState));
                //found the cost of the tile
                int originalStateIndex=originalState.indexOf('0');
                char targetCharAt=targetState.charAt(originalStateIndex);
                cost =Integer.parseInt(String.valueOf(targetCharAt));                
                //sum the cost for each iteration, and since bfs and dfs their cost movement is not affect by the tile number
                //so set the other cost variable
                totalCost_2 += cost;
                totalCost_1+=1;
            }
            
            //if the searchMode is bfs or dfs so each move only cost one
            //otherwise it depend on the tile's number
            if(searchMode==1 || searchMode==2) {
            	System.out.println("Cost of the movement: " + 1);
            }else {
            	System.out.println("Cost of the movement: " + cost);
            }
            originalState = targetState;
            
            //print the display of puzzle
            System.out.println(" ");
            //row_1
            System.out.print(" " + solution.get(i).getState().substring(0, 1)+"  ");
            System.out.print(" " + solution.get(i).getState().substring(1, 2)+"  ");
            System.out.println(" " + solution.get(i).getState().substring(2, 3)+"  ");
            //row_2
            System.out.print(" " + solution.get(i).getState().substring(3, 4)+"  ");
            System.out.print(" " + solution.get(i).getState().substring(4, 5)+"  ");
            System.out.println(" " + solution.get(i).getState().substring(5, 6)+"  ");
            //row_3
            System.out.print(" " + solution.get(i).getState().substring(6, 7)+"  ");
            System.out.print(" " + solution.get(i).getState().substring(7, 8)+"  ");
            System.out.println(" " + solution.get(i).getState().substring(8, 9)+"  ");
            
            System.out.println(" ");

        }
        //since if mode is breadth first search or depth first search the cost is the tile move step is one
        if (searchMode==1 || searchMode==2) {
        	displayTotalCost=totalCost_1;
        }else {
        	//otherwise, in the other algorithm the tile's number would affect the cost
        	displayTotalCost=totalCost_2;
        }
        //output the result for user
        System.out.println("-------------------------------------------");
        System.out.println("Length :  " + (solution.size() - 1));
        System.out.println("Cost : " + displayTotalCost);
        System.out.println("Time : " + time);
        System.out.println("Space : " + queueMax);
        System.out.println("-------------------------------------------");
	}


 //find the direction of tile move
    public static String moveChoose(String source, String destination) {
        int result = destination.indexOf('0') - source.indexOf('0');
        switch (result) {
        	case -1:
        		return "Right";
            case -3:
                return "Down";
            case 1:
                return "Left";
            case 3:
                return "Up";
        }
        return null;
    }

}
