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
import java.util.Scanner;

public class eight_puzzle {
	//the setting of different initial state
	public static String easy_initialState="134862705";
	public static String medium_initialState="281043765";
	public static String hard_initialState="567408321";
	public static String goal_finialState= "123804765";
	public static Scanner in;
	
	
	public static void main(String args[]) {
		
		boolean flag=true;
		boolean flag2=true;
		String chooseMode="";
		String chooseAlgorithm="";
		//set chooseModeNumber and chooseAlgorithmNumber to -1 and wait to later replace it 
		int chooseModeNumber=-1;
		int chooseAlgorithmNumber=-1;
		
		System.out.println("Eight puzzle is starting up.");
		
		while(flag) {
			System.out.println("Choose the starting States number: [1]easy [2]medium [3]hard or type 'quit' to end the program");
			//create a scanner and wait user to input
			in = new Scanner(System.in);
			chooseMode=in.nextLine();
			//set flag2 to true since it already get the first input
			flag2=true;
		
			//but if user type quit, the program would jump out of the loop, so we set flags to false
			if(chooseMode.equals("quit")){
				flag=false;
				flag2=false;
				System.out.println("Cancelled by user request.");
				break;
			}
			
			//avoid user type not a number
			try {
				chooseModeNumber = Integer.parseInt(chooseMode);
			}catch(NumberFormatException e){
				System.out.println("Not a number"); 
			}
			
			//check the input number is between 1~3
			if(chooseModeNumber<4 && chooseModeNumber>0) {
				System.out.println("You choose :"+chooseMode);
				System.out.println("Please continue to choose the search algorithm: ");
			}else {
				//if the user type wrong, we set flag to false avoid to run next loop, so we will start the loop previous 
				flag2=false;
				System.out.println("You type wrong, please type again");
			}
			
			
			while(flag2) {				
				System.out.println("[1]Breadth-First [2]Depth-First [3]Uniform-Cost [4]Greedy Best-First [5]A* V1 [6]A* V2");
				//receive the user input from scanner
				chooseAlgorithm=in.nextLine();
				
				//avoid user type not a number
				try {
					chooseAlgorithmNumber = Integer.parseInt(chooseAlgorithm);
				}catch(NumberFormatException e){
					System.out.println("Not a number"); 
				}
				//check the input number is between 1~6
				if(chooseAlgorithmNumber<7 && chooseAlgorithmNumber>0) {
					System.out.println("You choose :"+chooseAlgorithm);
					userChoose(chooseMode,chooseAlgorithm);
					flag2=false;
				}else{					
					System.out.println("You type wrong, please type again");
				}				
			}
			
		}
		

	     
	}
	
	//accept the user choose mode and algorithm and send it to Search class to deal with.
	public static void userChoose(String mode, String algorithm) {
		String initialState="";
		long finishTime=0;
		long totalTime =0;
		agentSearch agent;
		
		//assign initial State depend on the user input number
		switch (mode) {
		case "1":
			initialState=easy_initialState;
			break;
		case "2":
			initialState=medium_initialState;
			break;
		case "3":
			initialState=hard_initialState;
			break;
		default:
			break;	
	}
	
	//choose algorithm to solve the problem depend on user input
		switch (algorithm) {
			case "1":
				//BFS------------------------------------------------
				 agent =new agentSearch(new Node(initialState),goal_finialState);
				 //print the search success or not
			     System.out.println("Success ="+ agent.breadth_First_Search());
			     break;				
			case "2":
			 	//DFS------------------------------------------------
				 agent =new agentSearch(new Node(initialState),goal_finialState);
				//print the search success or not
			     System.out.println("Success ="+ agent.depth_First_Search());
			     break;
			case "3":
				//uniform------------------------------------------
				 agent =new agentSearch(new Node(initialState),goal_finialState);
				//print the search success or not
			     System.out.println("Success ="+ agent.uniform_Cost_Search());
			     break;
			case "4":
				//Best first-------------------------------------------
				 agent =new agentSearch(new Node(initialState),goal_finialState);
				//print the search success or not
			     System.out.println("Success ="+ agent.best_First_Search());
			     break;
			case "5":
				 //A* 01--------------------------------------------------
				agent =new agentSearch(new Node(initialState),goal_finialState);
				System.out.println("Success ="+ agent.a_Star_version01());
				break;
			case "6":
				//A* 02---------------------------------------------------
				agent =new agentSearch(new Node(initialState),goal_finialState);
				//print the search success or not
				System.out.println("Success ="+ agent.a_Star_version02());	
				break;
			default:
				break;
				
		}
		
	    System.out.println("============================================");	
	}

}
