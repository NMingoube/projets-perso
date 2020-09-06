import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;
import java.math.*;


/*
 * This file contains the definition of the Node, Graph, and NodeComparator object.
 * These three objects are necessary in order to run the A* algorithm
 * 
 * 
 */




/*
 * DEPENDENCIES:
 * DEPENDS ON: import java.util.ArrayList
 * 				Node class (itself)
 * 				import java.util.Comparator;
 * 
 * 
 * NECESSARY IN: Graph class
 * 				main algorithm
 * 				NodeComparator
 * 
 * 
 * This class describes the structure of a node among a graph.
 * The edges of the graph are represented by the "neighbor" relationship. As of now, these relationships
 * 	are NOT oriented nor weighted.
 * The nodes have a value stored inside, called "cost". They are named by their coordinates.  
 * Said coordinates serve only that purpose, and in this case, in the heuristic function.
 * Nodes with contiguous coordinates are not neighbors.
 * 
 * The heuristic number stores the result of the node's heuristic function.
 * The heuristic function is run in the algorithm and each node's stored value changes during the process,
 * as per the algorithm's specification
 * 
 * The nodes are comparable, and as such, can be ordered. This is done through the comparison of their heuristic.
 * This is an important feature for the algorithm as the open list must be ordered.
 */
class Node implements Comparable<Node>{
	short posX;
	short posY; 
	short cost;
	short heuristic;
	ArrayList<Node> neighbors;

	public Node(short x, short y, short cost) {
		this.neighbors = new ArrayList<Node>();
		this.posX=x;
		this.posY=y;
		
		this.cost=cost;
			
	}
	// Adds the node passed into the parameters to this node's neighbor list, effectively creating an edge between the two
	public void addNeighbor(Node node) {
		neighbors.add(node);
	}

	//Compares nodes and allows them to be superior or inferior to each other. Redundant with NodeComparator.
	@Override
	public int compareTo(Node o) {
		return this.heuristic-o.heuristic/Math.abs(this.heuristic-o.heuristic);
	}

}
/*
 * DEPENDENCIES:
 * DEPENDS ON: import java.util.ArrayList
 * 				Node class 
 * 
 * NECESSARY IN: main algorithm
 * 				
 * This class describes the structure of a graph.
 * Node and edge data can only be accessed through the objects contained in this one's nodeList.
 * Warning: Some edge and node data can be retrieved from a single node by going from neighbor to neighbor,
 * 	but this class is necessary, as it contains all of the graph's node, and graph crawling from a single node doesn't
 * 	guarantee the completeness of the data, as it can't travel between two non connex members of the graph
 */
class Graph{
	ArrayList<Node> nodeList;
	public Graph(ArrayList<Node> ndLst) {
		this.nodeList = ndLst;
	}
	
	public void addNode(Node node){
		nodeList.add(node);
	}
}


/*
 * DEPENDENCIES:
 * DEPENDS ON: Node class
 * 				
 * NECESSARY IN: main algorithm
 * 				import java.util.Comparator;
 * 
 * 
 * Compares nodes and allows them to be ordered. Necessary in order to build the open list.
 */
class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		if(node1.heuristic == node2.heuristic && node1.posX == node2.posX && node1.posY == node2.posY) {
			return 0;
		}else {
			if(node1.heuristic<node2.heuristic) {
				return 1;
			}else {
				return -1;
			}	
		}
	}
	public boolean equals(Node node1, Node node2) {
		if(node1.heuristic==node2.heuristic && node1.posX == node2.posX && node1.posY == node2.posY) {
			return true;
		}
		return false;
	}
}

public class algoMain {

	public static short distance(Node node1, Node node2) {
		return (short)Math.sqrt((node1.posX-node2.posX)*(node1.posX-node2.posX)+
				(node1.posY-node2.posY)*(node1.posY-node2.posY));
	}
	
	public static void main(String[] args) {
	
		/*
		 * Creating a list of node in order to build a graph
		 */
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				Node currentNode =new Node((short)i,(short)j,(short)999);
				nodeList.add(currentNode);
			}
		}
		/*
		 * Debugging. I do not understand why I have to do this but it wouldn't run without it.
		 */
		nodeList.add(new Node((short)0,(short)3,(short)999));
		nodeList.add(new Node((short)3,(short)0,(short)999));

		/*
		 * Creating the neighbor relationship between nodes. Effectively, edges.
		 * This overrides the previous statement that coordinate contiguity do not affect neighbor relationship,
		 * using coordinates in order to build those relationship is only a fast way to generate an understandable graph with no
		 * graphical representation.
		 */
		for(Node node1 : nodeList) {	
			for(Node node2 : nodeList) {
				if(Math.abs(node1.posX - node2.posX) + Math.abs(node1.posY - node2.posY)==1 && !node1.neighbors.contains(node2)) {
					node1.addNeighbor(node2);
					node2.addNeighbor(node1);
				}
			}
		}
		
		/*
		 * Choosing the beginning and end of the path to find.
		 */
		Node startingNode = nodeList.get(0); // Node (0,0)
		Node finishNode = nodeList.get(15); // Node(3,3)
		startingNode.cost=0; // As per the algorithm's definition
		
		ArrayList<Node> closedList = new ArrayList<Node>();
		
		Graph graph = new Graph(nodeList);
		
		/*
		 * Open list, as per its definition in A* algorithm. 
		 * Implemented as a TreeSet with a comparator passed as argument to automatically and effortlessly
		 * order nodes, again, in accordance to A*'s specifications.
		 */
		TreeSet<Node> openList = new TreeSet<Node>(new NodeComparator());
		
		/*
		 * This code translates to wikipedia's pseudo-code of A*
		 */
		
		openList.add(startingNode);
		
		
		while(!openList.isEmpty()) {
			Node currentNode = openList.pollFirst();
			//Exit condition. If it is not met and the openList is empty, no path was found.
			if(currentNode.posX==finishNode.posX && currentNode.posY == finishNode.posY) {
				System.out.println("Destination reached ! Went through:");
				String path="";
				short minCost=999;
				Node bestNode = null;
				Node currNode = finishNode;
				for(int i=0;i<finishNode.cost;i++) {
					for(Node node : currNode.neighbors) {
						if(node.cost<minCost) {
							bestNode = node;
							minCost=node.cost;
						}
					}
					path = "("+bestNode.posX+","+bestNode.posY+")"+path;
					currNode=bestNode;
				}
				System.out.println(path);
				return;
			}
			for(Node neighbor : currentNode.neighbors) {

				if(!(closedList.contains(neighbor) || (openList.contains(neighbor) && neighbor.cost < currentNode.cost))) {			
					neighbor.cost=(short) (currentNode.cost+1);
					neighbor.heuristic=(short) (neighbor.cost+distance(neighbor,finishNode));
					openList.add(neighbor);
				}
			}
			closedList.add(currentNode);
			
		}
		
		System.out.println("Error, no path found.");
	}
	
}
