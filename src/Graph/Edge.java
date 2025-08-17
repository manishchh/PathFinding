package Graph;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents a path, or connection, between two nodes.
 * Originally created for Data Structures, SP2 2017.
 * Reworked for Data Structures Advanced, SP5 2022
 * @author James Baumeister
 * @author Daniel Ablett, Gun Lee
 * @version 5.1
 */
public class Edge implements Serializable {
	private Node fromNode; //source node
	private Node toNode; //destination node
	private double weight; //weight

	/**
	 * @param node1
	 * @param node2
	 */
	public Edge(Node fromNode, Node toNode) {
		// TODO
		this.fromNode = fromNode;
		this.toNode = toNode;
		calculateWeight();
		
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node node) {
		this.fromNode = node;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node node) {
		this.toNode = node;
	}

	/**
	 * Calculates the weight of travel along this edge,
	 * taking into account each node's elevation.
	 * The weight should be calculated as:
	 * w(e) = d(p1, p2) * (0.01+|e2-e1|)
	 * where e1 is the elevation of the "from" node, and
	 * e2 is the elevation of the "to" node. d is a
	 * function that calculates the Euclidean distance
	 * between two 2D points, p1 and p2, that are the positions of each node.
	 */
	private void calculateWeight() {
		// TODO
		
		Position p1 = fromNode.getPosition();
		Position p2 = toNode.getPosition();
		
		//Euclidean distance between "from node" to "to node"
		double distance = p1.distance(p2); 
		// elevation fromNode
		double e1 = fromNode.getElevation(); 
		 //elevation toNode
		double e2 = toNode.getElevation();
		
		//weight of travel along the edge by w(e) = d(p1, p2) * (0.01+|e2-e1|)
		weight = distance * (0.01 + (Math.abs(e2 - e1)));
	
		
		
	
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Edge{" +
				"fromNode=" + fromNode +
				", toNode=" + toNode +
				", weight=" + weight +
				'}';
	}
}
