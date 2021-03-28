package DiGraph_A5;

import java.util.HashMap;

public class Node {
	private long idNum;
	private String label;
	public boolean hasPath;
	private HashMap<String, Edge> inEdges;
	private HashMap<String, Edge> outEdges;

	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		inEdges = new HashMap<String, Edge>();
		outEdges = new HashMap<String, Edge>();
		hasPath = false;
	}
	
	public HashMap<String, Edge> getInEdges(){ return inEdges;}
	public HashMap<String, Edge> getOutEdges(){return outEdges;}
	public long getId() {return idNum;}
	public String getLabel() {return label;}
	public long getOutEdgeId(String dlabel) { return outEdges.get(dlabel).getId();}
	
	public void addEdge(Edge edge) {
		if(edge.getSLabel() == label) {
			outEdges.put(edge.getDLabel(), edge);
		} else {
			inEdges.put(edge.getSLabel(), edge);
		}
	}
	public boolean removeOutEdge(String dlabel) {
		if(outEdges.containsKey(dlabel)) {
			outEdges.remove(dlabel);
			return true;
		}
		return false;
	}

	public boolean removeInEdge(String slabel) {
		if(inEdges.containsKey(slabel)) {
			inEdges.remove(slabel);
			return true;
		}
		return false;
	}

}