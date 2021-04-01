from unittest import TestCase
from ex3.src.DiGraph import DiGraph
from ex3.src.Node import Node
import time
import datetime


def create_graph():
    grp = DiGraph()
    grp.add_node(0)
    grp.add_node(1)
    grp.add_node(2)
    grp.add_node(3)
    grp.add_node(4)
    grp.add_node(5)
    grp.add_node(6)
    grp.add_node(7)
    grp.add_node(8)
    grp.add_node(9)
    return grp


class TestDiGraph(TestCase):
    def test_v_size(self):
        """
        check if a new node is added to the graph the size of the node increase by 1
        also check if the add a number and the has already node with the same number
        not need to change the size of the node
        """
        grp = create_graph()
        self.assertEqual(grp.v_size(), 10)
        grp.add_node(4)
        grp.add_node(9)
        self.assertEqual(grp.v_size(), 10)
        grp.add_node(10)
        self.assertEqual(grp.v_size(), 11)
        grp.remove_node(5)
        self.assertEqual(grp.v_size(), 10)

    def test_e_size(self):
        """
        check if a new edge between valid node is added to the graph the size of the edge increase by 1
        else not need to change the edge size
        """
        grp = create_graph()
        grp.add_edge(1, 2, 1)
        grp.add_edge(2, 6, 2)
        grp.add_edge(6, 1, 1)
        grp.add_edge(3, 2, 1)
        grp.add_edge(3, 7, 1)
        grp.add_edge(3, 5, 1)
        grp.add_edge(7, 3, 1)
        grp.add_edge(5, 4, 1)
        grp.add_edge(4, 5, 1)
        self.assertEqual(grp.e_size(), 9)
        grp.add_edge(4, 5, 2)  # already exists
        self.assertEqual(grp.e_size(), 9)
        grp.add_edge(4, 7, 8)
        self.assertEqual(grp.e_size(), 10)
        grp.remove_edge(3, 7)
        self.assertEqual(grp.e_size(), 9)
        grp.remove_node(6)  # two nodes connected
        self.assertEqual(grp.e_size(), 7)
        grp.remove_edge(12, 1)  # the node(12) not exists
        self.assertEqual(grp.e_size(), 7)
        grp.add_edge(1, 3, -4)  # negative weight
        self.assertEqual(grp.e_size(), 7)
        grp.add_edge(1, 3, 4)
        self.assertEqual(grp.e_size(), 8)

    def test_get_all_v(self):
        grp = create_graph()
        for i in grp.get_all_v():
            if i != 0 and i != 1 and i != 2 and i != 3 and i != 4 and i != 5 and i != 6 and i != 7 and i != 8 and i != 9:
                self.fail("not need to be Exists")
        self.assertEqual(len(grp.get_all_v()), 10)

    def test_all_in_out_edges_of_node(self):
        """
        check if the edge between two nodes is exist (after the edge is added to the graph
        """
        grp = create_graph()
        grp.add_edge(1, 2, 1)
        grp.add_edge(2, 1, 1)
        grp.remove_edge(2, 1)
        grp.add_edge(2, 6, 2)
        grp.add_edge(6, 1, 1)
        grp.add_edge(3, 2, 8)
        grp.add_edge(3, 7, 6)
        grp.add_edge(3, 5, 1)
        grp.add_edge(7, 3, 1)
        grp.add_edge(5, 4, 1)
        grp.add_edge(4, 5, 1)
        for i in grp.all_out_edges_of_node(1):
            self.assertEqual(i, 2)
        for i in grp.all_out_edges_of_node(2):
            self.assertEqual(i, 6)
        for i in grp.all_out_edges_of_node(6):
            self.assertEqual(i, 1)

        for i in grp.all_in_edges_of_node(1):
            self.assertEqual(i, 6)
        for i in grp.all_in_edges_of_node(2):
            if i == 3:
                self.assertEqual(i, 3)
            elif i == 1:
                self.assertEqual(i, 1)
            else:
                self.fail("not need to be Exists")
        for i in grp.all_in_edges_of_node(6):
            self.assertEqual(i, 2)

    def test_mc(self):
        """
        check if after every action the number of the changes increase
        """
        grp = create_graph()
        self.assertEqual(grp.get_mc(), 10)
        grp.add_node(9)  # already exist
        grp.add_node(10)
        self.assertEqual(grp.get_mc(), 11)
        grp.add_edge(11, 1, 2)  # the node(11) not exist
        grp.add_edge(1, 12, 2)  # the node(12) not exist
        grp.add_edge(1, 5, 2)
        grp.add_edge(1, 8, 2)
        grp.add_edge(7, 1, 1)
        grp.add_edge(7, 6, 1)
        grp.add_edge(7, 1, 1)   # the edge already exist
        grp.add_edge(6, 1, 1)
        grp.add_edge(5, 4, 1)
        self.assertEqual(grp.get_mc(), 17)
        grp.remove_node(11)     # the node(11) not exist
        grp.remove_node(1)
        grp.remove_node(2)
        self.assertEqual(grp.get_mc(), 19)
        grp.remove_edge(1, 15)  # the node(15) not exist
        grp.remove_edge(5, 4)
        grp.remove_edge(5, 2)   # the node 2 and the edge not exist
        self.assertEqual(grp.get_mc(), 20)

    def test_add_edge(self):
        """
        check the add_edge function
        if try to connect between nodes and one of them not exist do nothing
        else connect between them only if the given weight is positive
        """
        grp = create_graph()
        grp.add_edge(1, 2, -2)
        grp.add_edge(1, 1, 8)
        grp.add_edge(2, 6, 0.2)
        grp.add_edge(6, 2, 3)
        grp.add_edge(6, 1, 8)
        grp.add_edge(6, 1, 1)
        if grp.all_out_edges_of_node(1).__contains__(2):
            self.fail("not need to be Exists")
        if grp.all_out_edges_of_node(2).__contains__(1):
            self.fail("not need to be Exists")
        z = grp.all_out_edges_of_node(2)[6]
        self.assertEqual(z, 0.2)
        w = grp.all_out_edges_of_node(6)[1]
        self.assertEqual(w, 8)
        w = grp.all_out_edges_of_node(6)[2]
        self.assertEqual(w, 3)

    def test_remove_node(self):
        """
        if the node with the given key is valid in the graph delete it
        and all the connection to the other nodes
        """
        grp = create_graph()
        grp.add_edge(5, 1, 1)
        grp.add_edge(2, 5, 1)
        grp.remove_node(5)
        for i in grp:
            if i == 5:
                self.fail("notnot need to be Exists")
        self.assertEqual(grp.e_size(), 0)
        self.assertEqual(grp.v_size(), 9)
        self.assertEqual(grp.get_mc(), 13)

    def test_remove_edge(self):
        """
        check if the edge between two node is really delete
        """
        grp = create_graph()
        grp.add_edge(5, 1, 2)
        grp.add_edge(1, 5, 1)
        grp.remove_edge(5, 1)
        w = grp.all_out_edges_of_node(5)
        self.assertEqual(w, {})
        grp.remove_edge(1, 5)
        w = grp.all_in_edges_of_node(5)
        self.assertEqual(w, {})

    def test_get_node(self):
        """
        check if success to get all the information by getNode function
        """
        grp = create_graph()
        if grp.getNode(1) != None:
            self.assertEqual(grp.getNode(1).getKey(), 1)
            w = grp.getNode(1).getLocal()
            self.assertEqual(grp.getNode(1).getLocal(), w)
            self.assertEqual(grp.getNode(1).getTag(), 0)
            self.assertEqual(grp.getNode(1).getInfo(), "")
            self.assertEqual(grp.getNode(1).getWeight(), 0)
            self.assertEqual(grp.getNode(1).getPrev(), -1)
        self.assertEqual(grp.getNode(10), None)

    def test_graphMillion(self):
        """
        Build a graph with a million vertices
        each vertex has 3 edges
        """
        st = time.time()
        g = DiGraph()
        for i in range(1000):
            g.add_node(i)
        for i in range(1000):
            for j in range(3):
                g.add_edge(i, i + j, 1)
        print("Graph construction 10,000 nodes run time: ", round(time.time() - st, 3), " sec")
        for i in range(10000, 100000):
            g.add_node(i)
        for i in range(10000, 100000):
            for j in range(3):
                g.add_edge(i, i + j, 1)
        print("Graph construction 100,000 nodes run time: ", round(time.time() - st, 3), " sec")
        for i in range(100000, 1000000):
            g.add_node(i)
        for i in range(100000, 1000000):
            for j in range(3):
                g.add_edge(i, i + j, 1)
        print("Graph construction 1,000,000 nodes run time: ", round(time.time() - st, 3), " sec")
