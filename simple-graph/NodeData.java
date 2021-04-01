package ex0;

import java.util.*;

public class NodeData implements node_data {
	
	private static int defualtkey = 0;
	private int key ,tag;
	private String info;
	private HashMap<Integer,node_data> neighbor;
	
	//constructor.1
	public NodeData() {
		defualtkey++;
		this.key = defualtkey;
		this.info = null;
		this.tag = 0;
		this.neighbor = new HashMap<Integer,node_data>();
	}
	
	//constructor.2 - copy the data of another NodeData exept from the neighbors
	public NodeData(int key , String info , int tag) {
		this.key = key;
		this.info = info;
		this.tag = tag;
		this.neighbor = new HashMap<Integer,node_data>();
	}
		
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override 
	public Collection<node_data> getNi() {
		// TODO Auto-generated method stub
		return neighbor.values();
	}

	@Override
	public boolean hasNi(int key) { 
		// TODO Auto-generated method stub 
		if(this.neighbor != null ) {
			if(this.neighbor.containsKey(key))
				return true;
		}
		return false;               
	}

	@Override
	public void addNi(node_data t) {
		// TODO Auto-generated method stub
		if (!(hasNi(t.getKey()))) {
			this.neighbor.put(t.getKey(), t);
		}
	}
	
	@Override
	public void removeNode(node_data node) {
		// TODO Auto-generated method stub
		this.neighbor.remove(node.getKey());
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info = s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this.tag = t;
	}

}
