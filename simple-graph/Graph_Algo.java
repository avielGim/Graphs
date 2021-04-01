package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
	private graph myGraph;
	
	//constructor1
	public Graph_Algo() {
		this.myGraph = null;
	}
	//constructor2
	public Graph_Algo(graph g) {
		this.myGraph = g;
	}
	
	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub
		this.myGraph = g;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		graph g = new Graph_DS(myGraph);
		return g;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		if(myGraph.nodeSize() <= 1) {
			return true;
		}
		LinkedList<node_data> t1 = new LinkedList<node_data>();
		Iterator<node_data> it = myGraph.getV().iterator();
		if(it.hasNext()) {
			t1.add(it.next());
		}
		while(!t1.isEmpty()) {
			node_data curr = t1.poll();
			for(node_data n : myGraph.getV(curr.getKey())) {
				if(n.getTag() == 0) {
					t1.add(n);
					n.setTag(1);
				}
			}
		}
		int size = 0;
		for(node_data n : myGraph.getV()) {
			if(n.getTag() != 0)
				size++;
			n.setTag(0);
		}
		if(size == myGraph.nodeSize())
			return true;
		return false;
	}
	
	@Override
	public int shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		if(myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
			return -1;
		}
		if(src == dest) return 0;
		LinkedList<node_data> q = bfs(src,dest);
		if(q.isEmpty()) {
			return -1;
		}
		int count = myGraph.getNode(dest).getTag();
		while(!q.isEmpty()) {
			node_data n = q.poll();
			n.setTag(0);
		}
		return count-1;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		node_data currtemp = myGraph.getNode(src);
		LinkedList<node_data> list = new LinkedList<node_data>();
		if(currtemp == null || myGraph.getNode(dest) == null) {
			return list;
		}
		if(src == dest) {
			list.add(currtemp);
			return list;
		}
		LinkedList<node_data> q = bfs(src,dest);
		if(q.isEmpty()) {
			return null;
		}
		currtemp = myGraph.getNode(dest);
		boolean bool = false;
		int count = currtemp.getTag()-1;
		list.add(currtemp);
		Queue<node_data> qtemp = new LinkedList<node_data>();
		qtemp.add(currtemp);
		while(!qtemp.isEmpty() && count >= 0) {
			bool = false;
			currtemp = qtemp.poll();
			for(node_data n :myGraph.getV(currtemp.getKey())) {
				if(n.getTag() == count && !bool) {
					list.addLast(n);;
					qtemp.add(n);
					bool = true;
				}
			}
			count--;
		}
		while(!q.isEmpty()) {
			currtemp = q.poll();
			currtemp.setTag(0);
		}
		return list;
	}
	
	private LinkedList<node_data> bfs(int src , int dest){
		int count = 1;
		LinkedList<node_data> q = new LinkedList<node_data>();
		LinkedList<node_data> qtemp = new LinkedList<node_data>();
		node_data curr = myGraph.getNode(src);
		q.add(curr);
		curr.setTag(1);
		qtemp.add(curr);
		while(!qtemp.isEmpty()) {
			boolean bool = false;
			curr = qtemp.poll();
			count = curr.getTag()+1;
			for(node_data n : myGraph.getV(curr.getKey())) {	
				if(n.getKey() == dest) {
					n.setTag(count);
					q.add(n);
					bool = true;
				}
				else if((n.getTag() > count  || n.getTag() == 0) && !bool) {
					n.setTag(count);
					qtemp.add(n);
					q.add(n);
				}
			}
		}
		if(myGraph.getNode(dest).getTag() != 0) {
			return q;
		}
		while(!(q.isEmpty())) {
			curr = q.poll();
			curr.setTag(0);
		}
		return q;
	}
}

