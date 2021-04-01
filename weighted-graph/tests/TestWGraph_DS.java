package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;

class TestWGraph_DS {
	private static weighted_graph g;
	
	@BeforeEach
	void test() {
		g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
	}
	
	/**
	 * check if the node we just add is exist
	 * and check if he get the correct key.
	 * check function:
	 * 			1.getNode(int key)
	 * 			1.getkey() 
	 */

	@Test
	void test1() {	
		node_info a = g.getNode(1);
		assertEquals(a.getKey(), 1);
		System.out.println("test 1 is completed - good job!");
	}
	
	/**
	 * check if there is connection between three nodes nodes
	 * two are connected and 3 is not.
	 * if we connect with negative number - connect should not exist.
	 * check function:
	 * 			1.connect(int node1,int node2, double w) - w >= 0 - checked
	 * 			2.hasEdge(int node1,int node2)
	 * 			3.getEdge(int node1,int node2); 
	 */
	@Test
	void test2() {
		g.connect(1, 2, 7.2);
		g.connect(1, 3, 1.2);
		g.connect(2, 3, -2);
		g.connect(4, 3, 3.3);
		assertTrue(g.hasEdge(1,2));
		assertTrue(g.hasEdge(2,1));
		assertTrue(g.hasEdge(1, 3));
		assertFalse(g.hasEdge(3, 2));
		
		assertEquals(g.getEdge(1, 2), 7.2);
		assertEquals(g.getEdge(4, 3), 3.3);
		System.out.println("test 2 is completed - good job!");
	}
	
	/**
	 * check the collection of this g(weighted_graph)
	 * check function:
	 * 			1.getV()
	 * 			2.getV(int node_id)
	 * 			3.getTag()
	 * 			4.setTag(double t)
	 */
	@Test
	void test3() {
		int  i = 0;
		for(node_info n : g.getV()) {
			assertEquals(n.getKey(),i);
			i++;
		}
		g.connect(4, 0, -1);
		g.connect(1, 4, 5.2);
		g.connect(2, 4, 7.5);
		g.connect(3, 4, 3);
		i = 1;
		for(node_info n : g.getV(4)) {
			assertEquals(n.getKey(),i);
			assertEquals(g.getNode(n.getKey()).getTag(),0);
			assertNotEquals(n.getTag(),0);
			i++;
		}
		assertEquals(i, 4);
		System.out.println("test 3 is completed - good job!");
	}
	
	/**
	 * check the collection of this g(weighted_graph)
	 * and if we change some "info" for one of the neighbor
	 * check function:
	 * 			1.getV(int node_id)
	 * 			2.setInfo(String s)
	 * 			3.getInfo()
	 * 			
	 */
	@Test
	void test4() {
		g.connect(4, 0, 0);
		g.connect(1, 4, 5.2);
		g.connect(2, 4, 7.5);
		g.connect(3, 4, 3);
		
		node_info a = g.getNode(1);
		a.setInfo("black");
		node_info b = g.getNode(2);
		b.setInfo("white");
		for(node_info n : g.getV(4)) {
			if(n.getKey() == 1)
				assertEquals(n.getInfo(), "black");
			else if(n.getKey() == 2) 
				assertEquals(n.getInfo(), "white");
		}
		
		g.removeEdge(4, 2);
		for(node_info n : g.getV(4))
			if(n.getKey() == 2)
				fail("sohld not exist");
		System.out.println("test 4 is completed - good job!");
	}
	
	/**
	 * check if some node is deleted it is actually deleted from the graph 
	 * check function:
	 * 			1.removeNode(int key)
	 * 			2.removeEdge(int node1, int node2)
	 */
	@Test
	void test5() {
		g.connect(4, 0, 23);
		g.connect(1, 4, 4);
		g.connect(2, 4, 3);
		g.connect(3, 4, 5.8);
		g.removeNode(0);
		int i = 1;
		for(node_info n : g.getV(4)) {
			assertEquals(n.getKey(),i);
			i++;
		}
		if(g.getNode(0) != null)
			fail("should not exist");
		
		g.removeEdge(4, 2);
		for(node_info n : g.getV(4))
			if(n.getKey() == 2)
				fail("sohld not exist");
		System.out.println("test 5 is completed - good job!");
	}
	
	/**
	 * check number of the nodes in the graph
	 * check number of the edges in the graph
	 * check number of the changes in the graph
	 * check function:
	 * 			1.nodeSize()
	 * 			2.edgeSize()
	 * 			3.getMC()
	 */
	@Test
	void test6() {
		g.connect(4, 0, 23);
		g.connect(1, 4, 4);
		g.connect(2, 4, 3);
		g.connect(3, 4, 5.8);
		assertEquals(g.nodeSize(), 5);
		assertEquals(g.edgeSize(), 4);
		assertEquals(g.getMC(), 9);
		g.removeNode(0);
		assertEquals(g.getMC(), 10);
		g.addNode(5);
		g.addNode(6);
		assertEquals(g.getMC(), 12);
		g.removeNode(4);
		assertEquals(g.getMC(), 13);
		g.connect(5, 8, 4.0);
		g.addNode(7);
		g.addNode(8);
		assertEquals(g.nodeSize(), 7);
		assertEquals(g.getMC(), 15);
		g.removeNode(4);
		g.connect(5, 8, 4.0);
		g.connect(5, 8, 4.0);
		g.connect(3, 7, 8);
		g.connect(1, 3, 7.6);
		assertEquals(g.edgeSize(), 3);
		assertEquals(g.getMC(), 18);
		System.out.println("test 6 is completed - good job!");
	}
	
	

	
}


