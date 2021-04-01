package ex0;

import java.util.*;

public class Graph_DS implements graph {
	private int edgeSize , nodeSize, change;
	private HashMap<Integer,node_data> draw;
	
	//constructor1
	public Graph_DS() {
		this.edgeSize = 0;
		this.nodeSize = 0;
		this.change = 0;
		this.draw = new HashMap<Integer,node_data>();
	}
	//constructor2
	public Graph_DS(graph g) {
		this.draw = new HashMap<Integer,node_data>();
		this.nodeSize = 0;
		this.edgeSize = 0;
		this.change = 0;
		for(node_data n : g.getV()) {
			node_data curr = new NodeData(n.getKey(),n.getInfo(),n.getTag());
			addNode(curr);
		}
		for(node_data temp : g.getV()) {
			for(node_data curr : g.getV(temp.getKey())) {
				if(g.hasEdge(temp.getKey(), curr.getKey())) {
					connect(temp.getKey() , curr.getKey());
				}
			}
		}
	}
	
	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		if(draw.containsKey(key)) {
			node_data temp1 = draw.get(key);
			if(temp1 != null) 
				return temp1;
		}
		return null;
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		node_data temp = getNode(node1);
		if(temp != null) {
			if(temp.hasNi(node2)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		if(getNode(n.getKey()) == null) {
			this.draw.put(n.getKey(),n);
			this.nodeSize++;
			this.change++;
		}
	}

	@Override
	public void connect(int node1, int node2) {
		// TODO Auto-generated method stub
		if(node1 != node2) {
			boolean b = hasEdge(node1,node2);
			if(!b) {
				node_data t1 = getNode(node1);
				node_data t2 = getNode(node2);
				if(t1 != null && t2 != null) {
					t1.addNi(t2);
					t2.addNi(t1);
					this.edgeSize++;
					this.change++;
				}
			}	
		}
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return draw.values();
	}

	@Override
	public Collection<node_data> getV(int node_id) {
				// TODO Auto-generated method stub
		return getNode(node_id).getNi();
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		node_data curr = getNode(key);
		if(curr == null) {
			return null;
		}
		Collection<node_data> t = getV(key);
		int size = t.size();
		node_data[] y = t.toArray(new node_data[size]);
	    for(int i = 0 ; i < y.length ; i++) {
	    	y[i].removeNode(curr);
	    	this.edgeSize--;
	    }
	    this.draw.remove(key);
	    this.change++;
	    this.edgeSize++;
		this.nodeSize--;
		return curr;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		node_data t1 = getNode(node1);
		node_data t2 = getNode(node2);
		if(t1 != null && t2 != null) {
			t1.removeNode(t2);
			t2.removeNode(t1);
			this.edgeSize--;
			this.change++;
	
		}
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return this.nodeSize;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.edgeSize;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return this. change;
	}

}
