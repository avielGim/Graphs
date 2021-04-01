package ex1.src;

import java.util.*;

/**
 * this class is presenting a Undirected (positive) Weighted Graph. 
 * the graph is contains nodes who are very similar to dots inside regular graph
 * in this class we can add and delete nodes , connect between them or disconnect,
 * get know the edge between two nodes if there is any and get the information of 
 * any node inside the graph. 
 * all the nodes must have a unique key.
 * in addition - the graph remember the number of nodes, edges and 
 * how many changes was make.
 * @author aviel11
 */

public class WGraph_DS implements  weighted_graph{
	private int edgeSize , nodeSize, change;
	private HashMap<Integer,node_info> nodes;

	/**
	 * constructor1 - build a new graph. 
	 * the graph has no edges,nodes and no changes was make.
	 */
	public WGraph_DS() {
		this.edgeSize = 0;
		this.nodeSize = 0;
		this.change = 0;
		this.nodes = new HashMap<Integer,node_info>();
	}

	/**
	 * deep copy constructor. 
	 * @param g - has all the information we want to copy.
	 */
	public WGraph_DS(weighted_graph g) {
		this.nodes = new HashMap<Integer,node_info>();
		this.nodeSize = g.nodeSize();
		this.edgeSize = g.edgeSize();
		this.change = 0;
		for(node_info n : g.getV()) {
			addNode(n.getKey());
			g.getNode(n.getKey()).setInfo(n.getInfo());
			g.getNode(n.getKey()).setTag(n.getTag());
		}
		for(node_info temp : g.getV()) {
			for(node_info curr : g.getV(temp.getKey())) {
				if(g.hasEdge(temp.getKey(), curr.getKey())) {
					connect(temp.getKey() , curr.getKey() , curr.getTag());
				}
			}
		}
	}

	/**
	 * 	by given number (must be int kind), check if there is some node with this
	 * 	key if true than return the information about it.
	 *	@param key - the unique key of the node the function will look for.
	 *	@return node with all the information about it.
	 */
	@Override
	public node_info getNode(int key) {
		// TODO Auto-generated method stub
		if(nodes.containsKey(key)) {
			node_info temp = nodes.get(key); 
			return temp;
		}
		return null;
	}

	/**
	 * check if there is a connection between two nodes.
	 * if the two number are equals return true.
	 * @param node1 - the unique key of the node.
	 * @param node2 - the unique key of the node.
	 * @return true if there is a connection between two nodes.
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if(node1 == node2) {
			return true;
		}
		NodeInfo temp = (NodeInfo) getNode(node1);
		if(temp != null) {
			if(temp.hasNi(node2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *first check if there is a connection between two nodes 
	 *if true than the function get the weight between them. else return -1.
	 *the tag of the node(node2) that inside node(node1) is presents
	 * the weight / edge between them.
	 *if the two number are equals return 0.
	 *@param node1 - the unique key of the node.
	 *@param node2 - the unique key of the node.
	 *@return if there is an edge then return the tag else return -1.
	 */
	@Override
	public double getEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		NodeInfo t1 = (NodeInfo) getNode(node1);
		if(node1 == node2) {
			return 0;
		}
		if(t1 != null) {
			NodeInfo t2 = (NodeInfo) t1.getNi(node2);
			if(t2 != null) {
				return t2.getTag();
			}
		}
		return -1;
	}

	/**if the graph has not node with such key - make a new node and
	 * add it to this graph. 
	 * @param key - connect new node to this number.
	 */
	@Override
	public void addNode(int key) {
		// TODO Auto-generated method stub
		if(getNode(key) == null) {
			node_info temp = new NodeInfo(key);
			this.nodes.put(key,temp);
			this.nodeSize++;
			this.change++;
		}
	}
	
	/**
	 * first check this graph contains node that carry the given keys 
	 * and they are different if not contain and equals - do not do nothing.
	 * else check f already has edge between them if
	 * 		yes - then check if the weight is not equal to 'w' than change it to the given weight(w)
	 * 		no - make a new node1 and node2 and add to the neighbor of old node1 - new node2 and set the tag - w.
	 * 			 the same thing do for old node2 - add new node1 and set the tag - w.
	 * @param node1 - get the node with the key - node1
	 * @param node2 - get the node with the key - node2
	 * @param w - the weight between the two nodes.
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		// TODO Auto-generated method stub
		if(node1 != node2 && getNode(node1) != null && getNode(node2) != null) {
			if(w >= 0) {
				if(hasEdge(node1,node2)) {
					NodeInfo t1 = (NodeInfo) getNode(node1);
					node_info temp = t1.getNi(node2);
					if(temp.getTag() != w) {
						temp.setTag(w);	
						NodeInfo t2 = (NodeInfo) getNode(node2);
						temp = t2.getNi(node1);
						temp.setTag(w);
						change++;
					}
				}
				else {
					NodeInfo t1 = (NodeInfo) getNode(node1);
					NodeInfo t2 = (NodeInfo) getNode(node2);
					NodeInfo newt1 = new NodeInfo(t1);
					newt1.setTag(w);
					NodeInfo newt2 = new NodeInfo(t2);
					newt2.setTag(w);
					t1.addNi(newt2);
					t2.addNi(newt1);
					edgeSize++;
					change++;
				}
			}	
		}
	}
	
	/**
	 * get the pointer for neighbors that in HashMap and return collection type pointer.
	 * @return collection of all the node inside this graph 
	 */
	@Override
	public Collection<node_info> getV() {
		// TODO Auto-generated method stub
		return nodes.values();
	}

	/**
	 * first check that the node who has the key - node_id - is different from null.
	 * if true than go over the collection of the node and create a list that get the info of the node from the graph 
	 * and the key and the tag (present the weight) from the getNi function.
	 * return the nodes that has connect to this current node.
	 * the info we get from the graph to see if it has been changed.
	 * @param node_id - the unique key of the node that need to see who has connect with it.
	 * @return collection of all the node that have connect to node(node_id) 
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		// TODO Auto-generated method stub
		NodeInfo temp = (NodeInfo) getNode(node_id);
		if(temp != null) {
			Iterator<NodeInfo> t = temp.getNi().iterator();
			LinkedList<node_info> ret =new LinkedList<node_info>();
			while(t.hasNext()) {
				node_info a = t.next();
				node_info b = getNode(a.getKey());
				NodeInfo c = new NodeInfo(b.getKey(),b.getInfo(),a.getTag());
				ret.add(c);
			}
			return ret;
		}
		return null;
	}

	/**
	 * get the node from the graph by given the unique key if it not null
	 * than get all the nodes that connects to the current node and disconnect between them.
	 * @param key - the unique key of the node nthat need to delete
	 * @return node_info - the information about the node the function deleted
	 */
	@Override
	public node_info removeNode(int key) {
		// TODO Auto-generated method stub
		NodeInfo curr = (NodeInfo) getNode(key);
		if(curr == null) {
			return null;
		}
		for(NodeInfo n : curr.getNi()) {
			NodeInfo temp = (NodeInfo) getNode(n.getKey());
			temp.removeNode(curr);
			this.edgeSize--;
		}
		nodes.remove(key);
		change++;
		nodeSize--;
		return curr;
	}

	/**
	 * get two nodes and disconnect between them.
	 * @param node1 - the node with the unique key node1.
	 * @param node2 - the node with the unique key node12 
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if(node1 != node2) {
			NodeInfo t1 = (NodeInfo) getNode(node1);
			NodeInfo t2 = (NodeInfo) getNode(node2);
			if(t1 != null && t2 != null) {
				t1.removeNode(t2);
				t2.removeNode(t1);
				this.edgeSize--;
				this.change++;

			}
		}
	}

	/**
	 * @return the number of the nodes the graph has.
	 */
	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return nodeSize;
	}

	/**
	 * @return the number of the edges the graph has.
	 */
	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edgeSize;
	}

	/**
	 * @return the number of the changes the made on the graph.
	 */
	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return change;
	}

	/**
	 * get two object - type WGraph_DS and check if they are equals.
	 * go one by one on the nodes inside the current graph and go over each one of them
	 * and check if b has too the connection and the same weight.
	 * @param b - the second graph need to check
	 * @return true if equals.
	 */
	@Override
	public boolean equals(Object b) {
		// TODO Auto-generated method stub
		if(this == b) {
			return true;
		}
		if(b == null) {
			return false;
		}
		if(this.getClass() != b.getClass()) {
			return false;
		}
		if((b instanceof WGraph_DS)) {
			weighted_graph bb = (WGraph_DS) b;
			if(this.nodeSize() != bb.nodeSize()) {
				return false;
			}
			if(this.edgeSize() != bb.edgeSize()) {
				return false;
			}
			for(node_info ca : this.getV()) {
				for(node_info ta : this.getV(ca.getKey())) {
					if(bb.hasEdge(ca.getKey(), ta.getKey())) {
						double eb = bb.getEdge(ca.getKey(), ta.getKey());
						if(eb != ta.getTag()) {
							return false;
						}
					}
					else {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * this class is present the information of node. 
	 * the node has unique key, info - String kind, tag ,neighbors and previous.
	 * previous - remember the shortest path from some another node in the graph.
	 * this node save the nodes that has connect and their tag save the weight.
	 * the neighbor of the node points on another place in the memory from the ones in the graph. 
	 * @author aviel11
	 *
	 */
	public class NodeInfo implements node_info{
		private int key;
		private double tag , prev;
		private String info;
		private HashMap<Integer,NodeInfo> neighbor;


		/**
		 * constructor 1 - get number and crate node. the value of info - null, tag - 0.
		 * @param key
		 */
		public NodeInfo(int key) {
			this.key = key;
			this.info = null;
			this.tag = 0;
			this.prev = 0.1;
			this.neighbor = new HashMap<Integer,NodeInfo>();
		}

		/**
		 * copy constructor 2 - get the information about the given node and create
		 * another one with same details (point on another location in the memory)
		 * @param other
		 */
		public NodeInfo(node_info other) {
			this.key = other.getKey();
			this.info = other.getInfo();
			this.tag = other.getTag();
			this.prev = 0.1;
			this.neighbor = new HashMap<Integer,NodeInfo>();
		}
		
		/**
		 * constructor 3 - copy the data of another NodeData except from the neighbors 
		 * @param key
		 * @param info
		 * @param tag
		 */
		public NodeInfo(int key , String info, double tag) {
			this.key = key;
			this.info = info;
			this.tag = tag;
		}

		/**
		 * @param key - the key of the node.
		 * @return true if the node with the given number is connect.
		 */
		public boolean hasNi(int key) { 
			// TODO Auto-generated method stub 
			if(this.neighbor != null ) {
				if(this.neighbor.containsKey(key))
					return true;
			}
			return false;               
		}
		
		/**
		 * @param t - get node and add create a connection between them.
		 */
		public void addNi(NodeInfo t) {
			// TODO Auto-generated method stub
			if (!(hasNi(t.getKey()))) {
				this.neighbor.put(t.getKey(), t);
			}
		}
		
		/**
		 * @param key - the number of the node.
		 * @return the information about the node(key) if there is a connection
		 */
		public NodeInfo getNi(int key) {
			// TODO Auto-generated method stub
			if (hasNi(key)) {
				return neighbor.get(key);
			}
			return null;
		}
		
		/**
		 * @return all the nodes that connected to this current node.
		 */
		public Collection<NodeInfo> getNi() {
			// TODO Auto-generated method stub
			return neighbor.values();
		}

		/**
		 * disconnect between this node to the given node.
		 * @param node - type NodeInfo
		 */
		public void removeNode(NodeInfo node) {
			// TODO Auto-generated method stub
			this.neighbor.remove(node.getKey());
		}

		/**
		 * @return the one node that has shortest path to this node.
		 */
		public double getPrev() {
			return this.prev;
		}
		
		/**
		 * @param prev - the node that has shortest path to this node. 
		 */
		public void setPrev(double prev) {
			this.prev = prev;
		}
		
		//////////////////////////////////////////////////////////
		/**
		 * @return the unique key.
		 */
		@Override
		public int getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		/**
		 * @return the info of this node.
		 */
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return info;
		}
		
		/**
		 * change the info of the node.
		 */
		@Override
		public void setInfo(String s) {
			// TODO Auto-generated method stub
			this.info = s;
		}
		
		/**
		 * @return the tag of this node(represent the weight).
		 */
		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return tag;
		}

		/**
		 * @param change the tag of the node (represent the weight).
		 */
		@Override
		public void setTag(double t) {
			// TODO Auto-generated method stub
			this.tag = t;
		}

	}
}
