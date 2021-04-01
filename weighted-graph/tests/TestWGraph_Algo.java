package ex1.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestWGraph_Algo {
	private static weighted_graph g;
	private WGraph_Algo temp;

	@BeforeEach
	void test() {
		g = new WGraph_DS();
		temp = new WGraph_Algo();
	}

	/**
	 * check the function:
	 * 			1.WGraph_Algo() 
	 * 			2.init(weighted_graph g)
	 * 			3.copy()
	 */
	@Test
	void test1() {
		g = graph_creator(4);
		g.connect(1, 2, 5.2);
		WGraph_Algo temp = new WGraph_Algo();
		temp.init(g);
		weighted_graph t = temp.copy();
		g.addNode(5);
		g.connect(1, 3, 4.7);
		t.connect(1, 3, 7.1);

		int index = 0;

		for(node_info n : g.getV()) {
			assertEquals(n.getKey(), index);
			index++;
		}
		for(node_info n : g.getV(1)) {
			if(n.getKey() == 2)
				assertEquals(n.getTag(), 5.2);
			else if(n.getKey() == 3)
				assertEquals(n.getTag(), 4.7);
		}
		index = 0;
		for(node_info n : t.getV()) {
			assertEquals(n.getKey(), index);
			index++;
		}
		for(node_info n : t.getV(1)) {
			if(n.getKey() == 2)
				assertEquals(n.getTag(), 5.2);
			else if(n.getKey() == 3)
				assertEquals(n.getTag(), 7.1);
		}

		System.out.println("test 1 is completed - good job!");
	}

	/**
	 * check the function 
	 * 				1.isConnected()
	 */
	@Test
	void test2() {
		g = graph_creator(10);

		g.connect(0, 2, 7.3);
		g.connect(2, 10, 1);
		g.connect(2, 3, 2);
		g.connect(3, 10, 1);
		g.connect(3, 4, 3.1);
		g.connect(4, 5, 2.1);
		g.connect(10, 5, 6);
		g.connect(5, 6, 1.7);
		g.connect(6, 9, 3.7);
		g.connect(9, 7, 1);
		g.connect(9, 8, 0.5);
		g.connect(8, 0, 1.2);
		temp.init(g);
		assertFalse(temp.isConnected());
		g.removeNode(1);
		assertTrue(temp.isConnected());
		System.out.println("test 2 is completed - good job!");
	}


	/**
	 * check the function 
	 * 				1.shortestPathDist(int src,int dest);
	 */
	@Test
	void test3() {
		g = graph_creator(10);

		g.connect(10, 4, 2);
		g.connect(0, 2, 7.3);
		g.connect(2, 10, 1);
		g.connect(2, 3, 2);
		g.connect(3, 10, 1);
		g.connect(3, 4, 3.1);
		g.connect(4, 5, 2.1);
		g.connect(10, 5, 6);
		g.connect(5, 6, 1.7);
		g.connect(6, 9, 3.7);
		g.connect(9, 7, 1);
		g.connect(9, 8, 0.5);
		g.connect(8, 0, 1.2);

		temp.init(g);
		WGraph_Algo temp = new WGraph_Algo();
		temp.init(g);
		assertEquals(temp.shortestPathDist(0, 6), 5.4);
		assertEquals(temp.shortestPathDist(2, 4), 3);

		System.out.println("test 3 is completed - good job!");
	}

	/**
	 * check the function 
	 * 				1.shortestPath(int src,int dest);
	 */
	@Test
	void test4() {
		g = graph_creator(10);

		g.connect(0, 2, 7.3);
		g.connect(2, 10, 1);
		g.connect(2, 3, 2);
		g.connect(3, 10, 1);
		g.connect(3, 4, 3.1);
		g.connect(4, 5, 2.1);
		g.connect(10, 5, 6);
		g.connect(5, 6, 1.7);
		g.connect(6, 9, 3.7);
		g.connect(9, 7, 1);
		g.connect(9, 8, 0.5);
		g.connect(8, 0, 1.2);
		g.connect(10, 4, 2);

		temp.init(g);
		List<node_info> list = temp.shortestPath(0, 6);
		int [] arr = new int[] {0,8,9,6};
		for(int i = 0 ; i<list.size() ; i++) {
			assertEquals(list.get(i).getKey(), arr[i]);
		}
		list = temp.shortestPath(1, 1);
		assertEquals(temp.shortestPathDist(1, 1),0);
		list = temp.shortestPath(2, 5);
		arr = new int[] {2,10,4,5};
		for(int i = 0 ; i<list.size() ; i++) {
			assertEquals(list.get(i).getKey(), arr[i]);
		}
		System.out.println("test 4 is completed - good job!");
	}

	/**
	 * check the function 
	 * 				1.getGraph()
	 */
	@Test
	void test5() {	
		g = graph_creator(5);
		g.connect(3, 1, 7.1);
		g.connect(3, 4, 3.1);
		g.connect(3, 5, 2);
		g.connect(1, 0, 3.1);
		g.connect(2, 1, 1.01);
		WGraph_Algo temp = new WGraph_Algo();
		temp.init(g);
		assertTrue(temp.isConnected());
		weighted_graph g2 = temp.getGraph();
		temp.init(g2);
		assertTrue(temp.isConnected());

		g2.removeNode(0);
		g.removeEdge(1, 3);
		if(g.getNode(0) != null) {
			fail("this node not exist");
		}
		assertEquals(g2.getEdge(1, 3), -1);
		System.out.println("test 5 is completed - good job!");
	}

	/**
	 * check the function 
	 * 				1.save(String file)
	 * 				2.load(String file)
	 */
	@Test
	void test6() {	
		g = graph_creator(25);
		for(int i = 0 ; i < 50 ; i++) {
			int m = (int) (Math.random()*26);
			int n = (int) (Math.random()*26);
			double l = Math.random()*100;
			g.connect(n, m, l);
		}

		temp.init(g);
		temp.save("test.txt");
		WGraph_Algo t1 = new WGraph_Algo();
		weighted_graph g1 = new WGraph_DS();
		t1.load("test.txt");
		g1 = t1.getGraph();
		assertEquals(g, g1);
		System.out.println("test 6 is completed - good job!");
	}

	public static weighted_graph graph_creator(int nodesize) {
		weighted_graph te = new WGraph_DS();
		for(int i = 0 ; i < nodesize+1 ; i++) {
			te.addNode(i);
		}
		return te;

	}
	
}
