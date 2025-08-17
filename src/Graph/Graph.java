package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import GUI.GraphLoader;
import javafx.scene.control.skin.TextInputControlSkin.Direction;

/**
 * Represents a non-directional graph where each vertex
 * is a Node object. Connections between nodes are based
 * on the cartesian coordinate system.
 * @author James Baumeister on 30/4/17
 */
public class Graph {
	
	

	/**
	 * Connects all nodes, building their E, W, S, N, NE, SE, NW, SW edges,
	 * in that order. The nodes should form a 10x10 square grid, and the array
	 * is such that every i'th node % 10 = 9 is a right edge.
	 * See the assignment specification for more information.
	 * @param nodes An array of Node objects to be connected
	 * @return An array of connected Node objects
	 */
	public void connectNodes(Node[] nodes) {
		// TODO
		int rows = 10;
        int cols = 10;
        //iterate each row and column in grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Node currentNode = nodes[row * cols + col];

                // East 
                if (col < cols - 1) {
                    Node eastNode = nodes[row * cols + col + 1];
                    Edge eastEdge = new Edge(currentNode, eastNode);
                    currentNode.getEdges().add(eastEdge);
                }

                // West
                if (col > 0) {
                    Node westNode = nodes[row * cols + col - 1];
                    Edge westEdge = new Edge(currentNode, westNode);
                    currentNode.getEdges().add(westEdge);
                }

                // South
                if (row < rows - 1) {
                    Node southNode = nodes[(row + 1) * cols + col];
                    Edge southEdge = new Edge(currentNode, southNode);
                    currentNode.getEdges().add(southEdge);
                }

                // North
                if (row > 0) {
                    Node northNode = nodes[(row - 1) * cols + col];
                    Edge northEdge = new Edge(currentNode, northNode);
                    currentNode.getEdges().add(northEdge);
                }

                // North-East
                if (row > 0 && col < cols - 1) {
                    Node northeastNode = nodes[(row - 1) * cols + col + 1];
                    Edge northeastEdge = new Edge(currentNode, northeastNode);
                    currentNode.getEdges().add(northeastEdge);
                }

                // South-East
                if (row < rows - 1 && col < cols - 1) {
                    Node southeastNode = nodes[(row + 1) * cols + col + 1];
                    Edge southeastEdge = new Edge(currentNode, southeastNode);
                    currentNode.getEdges().add(southeastEdge);
                }

                // North-West
                if (row > 0 && col > 0) {
                    Node northwestNode = nodes[(row - 1) * cols + col - 1];
                    Edge northwestEdge = new Edge(currentNode, northwestNode);
                    currentNode.getEdges().add(northwestEdge);
                }

                // South-West
                if (row < rows - 1 && col > 0) {
                    Node southwestNode = nodes[(row + 1) * cols + col - 1];
                    Edge southwestEdge = new Edge(currentNode, southwestNode);
                    currentNode.getEdges().add(southwestEdge);
                }
            }
        }
    }


	/**
	 * Searches for an edge from the source node to the destination.
	 * @param source The source, or first, node
	 * @param destination The destination, or second, node
	 * @return The edge between the nodes, or null if not found
	 */
	public Edge getEdge(Node source, Node destination) {
		
		// TODO
		//assign list of edges retrieved from source Node
		ArrayList<Edge> edges = source.getEdges(); 
		//iterate each edge in list and retrieves edge object
		for (int i = 0; i < edges.size(); i++) {
		    Edge edge = edges.get(i);
		    
		    //returns edge if current node equals given node
		    if (edge.getToNode().equals(destination)) {
		        return edge; 
		    }
		}
		// return  null if not equal
		return null;
	}

	/**
	 * From an array of Node objects, this calculates the total cost of
	 * travelling (i.e. sum of weights) from the first to the last nodes.
	 * @param vertices An array of Node objects representing a path
	 * @return The total cost of travel
	 */
	public double calculateTotalWeight(Node[] vertices) {
		// TODO
		// Check if the vertices array is null or contains less than 2 nodes
	    if (vertices == null || vertices.length < 2) {
	        return 0.0;
	    }
	    
	    // Check if the start and end node are the same
	    if (vertices[0].equals(vertices[vertices.length - 1])) {
	        return 0.0;
	    }

	    double totalCost = 0.0;

	    // Loop through the array and sum up the costs of the edges between adjacent nodes
	    for (int i = 0; i < vertices.length - 1; i++) {
	    	//get the edge that connected the current and next vertex
	        Edge edge = getEdge(vertices[i], vertices[i + 1]);
	        //add weight with edge exists between two vertices
	        if (edge != null) {
	            totalCost += edge.getWeight(); 
	        } else {
	            return -1.0;
	        }
	    }

	    // Return the total cost of travel
	    return totalCost;
		
	}


	/**
	 * Performs a breadth-first search of the graph and returns the shortest
	 * path from one node to another.
	 * @param start The node from which to start searching
	 * @param target The target node to which a path is built
	 * @return An array of Node objects representing the path from start to target, in that order
	 */
	public Node[] breadthFirstSearch(Node start, Node target) {
		// TODO
		Queue<Node> queue = new LinkedList<>();
		Map<Node, Node> parentMap = new HashMap<>();
		Set<Node> visited = new HashSet<>();

		queue.add(start);
		visited.add(start);
		
		while (!queue.isEmpty()) {
	        Node currentNode = queue.poll();
	        /*
	         * If the target node is reached, 
	         * construct the path using the parent references and 
	         * return
	         */
	        if (currentNode.equals(target)) {
	            
	            List<Node> path = new ArrayList<>();
	            Node current = target;

	            while (current != null) {
	                path.add(current);
	                current = parentMap.get(current);
	            }

	            Collections.reverse(path);
	            return path.toArray(new Node[0]);
	        }
	        for (Edge edge : currentNode.getEdges()) {
	            Node neighbor = edge.getToNode();
	         // Enqueue and mark unvisited neighbors noting their parents.
	            if (!visited.contains(neighbor)) {
	                queue.add(neighbor);
	                visited.add(neighbor);
	                parentMap.put(neighbor, currentNode);
	            }
	        }
		}
		return null;
	}


	/**
	 * Performs a depth-first search of the graph and returns the first-found
	 * path from one node to another.
	 * @param start The node from which to start searching
	 * @param target The target node to which a path is built
	 * @return An array of Node objects representing the path from start to target, in that order
	 */
	public Node[] depthFirstSearch(Node start, Node target) {
		// TODO
		Set<Node> visited = new HashSet<>();
	    Deque<Node> stack = new LinkedList<>();
	    stack.push(start);
	    
	    /*
	     * check the top node of the stack during traversal,
	     * if the target node is found, the path from start to target is constructed from the stack,
	     * reversed to get the correct order & returned.
	     */
	    while (!stack.isEmpty()) {
	        Node current = stack.peek();

	        if (current.equals(target)) {
	            // Create a list to hold the nodes in the correct order
	            List<Node> pathList = new ArrayList<>(stack);
	            Collections.reverse(pathList);
	            return pathList.toArray(new Node[0]);
	        }
	        //add current node to the set of visited nodes
	        visited.add(current);

	        boolean unvisitedNode = false;
	        /*
	         * Iterate through the current node's edges to find the next unvisited node.
	         * if found, push it onto the stack and mark as visited.
	         */
	        for (Edge edge : current.getEdges()) {
	            Node neighbor = edge.getToNode();
	            if (!visited.contains(neighbor)) {
	                stack.push(neighbor);
	                visited.add(neighbor);
	                unvisitedNode = true;
	                break;
	            }
	        }
	        //pop it from the stack to backtrack if the current node has no unvisited node
	        if (!unvisitedNode) {
	            stack.pop();
	        }
	    }

		//else null if no path found
	    return null;
	    
	}


	/**
	 * Performs a search of the graph using Dijkstra's algorithm, which takes into
	 * account the edge weight. It should return the least-costly path from one node
	 * to another.
	 * @param start The node from which to start searching
	 * @param target The target node to which a path is built
	 * @return An array of Node objects representing the path from start to target, in that order
	 */
	public Node[] dijkstrasSearch(Node start, Node target) {
		// TODO
		
		Map<Node, Double> distance = new HashMap<>();
	    Map<Node, Node> previousNodes = new HashMap<>();
	    Set<Node> visitedNodes = new HashSet<>();

	    // Convert the array of nodes from GraphLoader into an ordered list
	    List<Node> orderedNodes = Arrays.asList(GraphLoader.getNodes()); 

	    //Initialized the starting node distance to 0.
	    distance.put(start, 0.0);
	    /*
	     * Start from the initial node, 
	     * traverse each adjacent node to determine the shortest distance to the target node.
	     */
	    Node currentNode = start;

	    while (currentNode != null) {
	        if (currentNode.equals(target)) {
	            break;
	        }
	        //add the current node in set visitedNodes
	        visitedNodes.add(currentNode);

	        // Loop through edges in the order of connected Nodes
	        for (Edge edge : currentNode.getEdges()) {
	            Node adjacentNode = edge.getToNode();
	            if (!visitedNodes.contains(adjacentNode)) {
	                double newDist = distance.get(currentNode) + edge.getWeight();
	                // Update the shortest distance and preceding node if a shorter path is found
	                if (newDist < distance.getOrDefault(adjacentNode, Double.MAX_VALUE)) {
	                    distance.put(adjacentNode, newDist);
	                    previousNodes.put(adjacentNode, currentNode);
	                }
	            }
	        }
	        
	        // Move to the node with the smallest distance with nodes order of connected Nodes
	        currentNode = null;
	        double smallestDistance = Double.MAX_VALUE;
	        for (Node potentialNode : orderedNodes) {
	            if (!visitedNodes.contains(potentialNode) && distance.getOrDefault(potentialNode, Double.MAX_VALUE) < smallestDistance) {
	                smallestDistance = distance.get(potentialNode);
	                currentNode = potentialNode;
	            }
	        }
	    }
	    /*
	     * trace back the shortest path from the target node to the starting node,
	     * reverse it and
	     * return as an array.
	     */
	    List<Node> path = new ArrayList<>();
	    for (Node at = target; at != null; at = previousNodes.get(at)) {
	        path.add(at);
	    }
	    Collections.reverse(path);
	    return path.toArray(new Node[0]);
        
    }
}
