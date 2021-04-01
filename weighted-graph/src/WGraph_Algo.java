package ex1.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import ex1.src.WGraph_DS.NodeInfo;


public class WGraph_Algo implements weighted_graph_algorithms {
	private weighted_graph myGraph;

	/**
	 * constructor1 - build a new weighted_graph_algorithms. 
	 */
	public WGraph_Algo() {
		this.myGraph = new WGraph_DS();
	}

	/**
	 * @param g - work on weighted_graph g
	 */
	@Override
	public void init(weighted_graph g) {
		// TODO Auto-generated method stub
		this.myGraph = g;

	}

	/**
	 * @return the information of the weighted_graph this wgraph_algo works.
	 */
	@Override
	public weighted_graph getGraph() {
		// TODO Auto-generated method stub
		return this.myGraph;
	}

	/**
	 * deep copy of the current weighted_graph
	 */
	@Override
	public weighted_graph copy() {
		// TODO Auto-generated method stub
		weighted_graph g = new WGraph_DS(myGraph);
		return g;
	}

	/**
	 * @return true if and only if (if) there is a valid path from every node to
	 * another.
	 */
	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		if(myGraph.nodeSize() <= 1) {
			return true;
		}
		NodeInfo temp;
		LinkedList<NodeInfo> t1 = new LinkedList<NodeInfo>();
		HashMap<Integer,String> t2 = new HashMap<Integer,String>();
		if(myGraph.getV().iterator().hasNext()) {
			int num = myGraph.getV().iterator().next().getKey();
			temp = (NodeInfo) myGraph.getNode(num); 
			t1.add(temp);
			t2.put(num , "in");
		}
		int num = 0;
		while(!t1.isEmpty()) {
			NodeInfo curr = t1.poll();
			for(node_info n : myGraph.getV(curr.getKey())) {
				if(!t2.containsKey(n.getKey())) {
					num = n.getKey();
					temp = (NodeInfo) myGraph.getNode(num);
					t1.add(temp);
					t2.put(n.getKey() , "in");
				}
			}
		}
		int size = t2.size();
		int siseNode = myGraph.nodeSize();
		if(siseNode == size) {
			return true;
		}
		return false;
	}


	/**
	 * @param src - start node.
	 * @param dest - end (target) node.
	 * @returns the weight of the shortest path between src to dest.
	 * if no such path --> returns -1
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		if(myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
			return -1;
		}
		if(src == dest) return 0;
		LinkedList<NodeInfo> q = bfs(src,dest);
		if(q.isEmpty()) {
			return -1;
		}
		double count = myGraph.getNode(dest).getTag();
		while(!q.isEmpty()) {
			NodeInfo n = q.poll();
			n.setTag(0);
			n.setPrev(0);
		}
		return count-1;
	}

	/**
	 * the function use at the private function bfs.
     * @param src - start node
     * @param dest - end (target) node
     * @return the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		node_info t1 = myGraph.getNode(src);
		LinkedList<node_info> list = new LinkedList<node_info>();
		if(t1 == null || myGraph.getNode(dest) == null) {
			return list;
		}
		if(src == dest) {
			list.add(t1);
			return list;
		}
		LinkedList<NodeInfo> q = bfs(src,dest);
		if(q.isEmpty()) {
			return null;
		}
		NodeInfo t2 = (NodeInfo) myGraph.getNode(dest);
		node_info t3 = t2;
		list.add(t3);
		while(t2.getKey() != src) {
			t3 = myGraph.getNode((int) t2.getPrev());
			list.addFirst(t3);
			t2.setPrev(0.1);
			t2.setTag(0);
			t2 = (NodeInfo) myGraph.getNode(t3.getKey());;

		}
		t2.setPrev(0.1);
		t2.setTag(0);
		return list;
	}

	 /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
	@Override
	public boolean save(String file) {
		// TODO Auto-generated method stub
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));

			StringBuilder sb = new StringBuilder();
			for (node_info t1 :  myGraph.getV()) {
				//NodeInfo t2 = (NodeInfo) myGraph.getNode(t1.getKey());
				sb.append(t1.getKey());
				sb.append(",");
				sb.append(t1.getInfo());
				sb.append(":");
				for(node_info t3 :myGraph.getV(t1.getKey())) {
					sb.append(t3.getKey());
					sb.append(",");
					sb.append(t3.getInfo());
					sb.append(",");
					sb.append(t3.getTag());
					sb.append("!");
				}
				sb.append("\n");
				pw.write(sb.toString());

				sb.setLength(0);
			}

			pw.close();

			return true;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return false;
		}


	}

	/**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			WGraph_DS g = new WGraph_DS();
			String line;
			while ((line = br.readLine()) != null){
				String [] str = line.split(":");
				String [] str1 = str[0].split(",");
				int num = Integer.parseInt(str1[0]);
				g.addNode(num);
				g.getNode(num).setInfo(str1[1]);
				if(str.length > 1) {
					String [] str2 = str[1].split("!");
					for(int i = 0 ; i < str2.length ; i++) {
						String [] str3 = str2[i].split(",");
						g.connect(num,Integer.parseInt(str3[0]), Double.parseDouble(str3[2]));
					}

				}
			}
			br.close();
			myGraph = g;
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param start node.
     * @param dest - end (target) node.
	 * @returns the weight of the shortest path between src to dest.
	 * if no such path --> returns -1
	 */
	private LinkedList<NodeInfo> bfs(int src , int dest){
		LinkedList<NodeInfo> q = new LinkedList<NodeInfo>();
		LinkedList<NodeInfo> qtemp = new LinkedList<NodeInfo>();
		NodeInfo curr = (NodeInfo) myGraph.getNode(src);
		q.add(curr);
		curr.setTag(1);
		qtemp.add(curr);
		double count = 0;
		while(!qtemp.isEmpty()) {
			boolean bool = false;
			curr = qtemp.poll();
			double tag = curr.getTag();
			for(node_info n : myGraph.getV(curr.getKey())) {
				count = n.getTag() + tag;
				NodeInfo n2 = (NodeInfo) myGraph.getNode(n.getKey());
				if(n.getKey() == dest && n2.getTag() > count) {
					n2.setTag(count);
					n2.setPrev(curr.getKey());
					q.add(n2);
					bool = true;
				}
				else if((n2.getTag() > count  || n2.getTag() == 0) && !bool) {
					n2.setPrev(curr.getKey());
					n2.setTag(count);
					qtemp.add(n2);
					q.add(n2);
				}
			}
		}
		if(myGraph.getNode(dest).getTag() != 0) {
			return q;
		}
		while(!(q.isEmpty())) {
			curr = q.poll();
			curr.setTag(0);
			curr.setPrev(0);
		}
		return q;
	}

	
}
