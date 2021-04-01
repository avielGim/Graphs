from unittest import TestCase
from ex3.src.DiGraph import DiGraph
from ex3.src.GraphAlgo import GraphAlgo
import random


def doSmallGraph() -> DiGraph():
    """
    Create a small graph with 10 nodes and 30 edges,
    test for getGraph
    """

    g = DiGraph()
    for i in range(10):
        g.add_node(i)
    for i in range(10):
        for j in range(3):
            g.add_edge(i, i + j, 1)
    grp = GraphAlgo(g)
    return grp.get_graph()


class TestGraphAlgo(TestCase):

    def test_save_and_load_from_json(self):
        """
        Test for save and load in json format,
        and plot the copy graph
        """
        grp = GraphAlgo(doSmallGraph())
        self.assertNotEqual(grp, None)

        self.assertEqual(grp.save_to_json("temp.txt"), True)
        grp2 = GraphAlgo()
        flag = grp2.load_from_json("temp.txt")
        if flag != True:
            self.fail("fail to load")
        grp2.plot_graph()

    def test_shortest_path(self):
        """
        test the shortest path from node 0 to node 9
        """
        grp = GraphAlgo(doSmallGraph())
        flag = grp.shortest_path(0, 9)
        if flag[0] != 5:
            self.fail(flag[0], " != 5")
        if flag[1] != [0, 1, 3, 5, 7, 9]:
            self.fail(flag[1], " != [0, 1, 3, 5, 7, 9] ")

    def test_connected_components(self):
        """
        Test connected components and connected component
        """
        g = doSmallGraph()
        for i in range(6):
            for j in range(2):
                g.add_edge(i + j, i, 1.5)
        grp = GraphAlgo(g)
        temp = grp.connected_component(5)
        if temp != [0, 1, 2, 3, 4, 6]:
            self.fail(temp, " != [0, 1, 2, 3, 4, 6]")
        temp = grp.connected_components()
        if temp == None:
            self.fail(temp, " == None")


