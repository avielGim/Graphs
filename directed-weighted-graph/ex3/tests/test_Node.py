from unittest import TestCase
from ex3.src.Node import Node

""""
this class is a type of unittest and tests all the function in Node class
@atuor liadn7
@athour avielc11
"""
node = Node(1)


class TestNode(TestCase):

    def test_getKey(self):
        """
        check if the key of the new node that created is as given at the constructor
        """
        self.assertEqual(node.getKey(), 1)

    def test_getLocal(self):
        """
        check if the location of the node is change to (5, 4.2)
        """
        node.setLocal((5, 4.2))
        self.assertEqual(node.getLocal(), (5, 4.2))

    def test_getWeight(self):
        """
        check if the weight of the node is change to (5.1)
        """
        node.setWeight(5.1)
        self.assertEqual(node.getWeight(), 5.1)

    def test_getInfo(self):
        """
        check if the information (type - str) of the node is change to ("good")
        """
        node.setInfo("good")
        self.assertEqual(node.getInfo(), "good")

    def test_getTag(self):
        """
        check if the tag of the node is change to (4)
        """
        node.setTag(4)
        self.assertEqual(node.getTag(), 4)

    def test_getPrev(self):
        """
        check if the prev of the node is change to (7)
        """
        node.setPrev(7)
        self.assertEqual(node.getPrev(), 7)

    def test_addTo_getW_getForw(self):
        """
        check if successfully connect node to the another node by give only positive weight (>=0)
        """
        node.addTo(2, 0.23)
        node.addTo(7, 1.3)

        self.assertEqual(node.getW(2), 0.23)
        self.assertEqual(node.getW(7), 1.3)
        for i in node.getForw():
            if i != 2 and i != 7:
                self.assertEqual(i, 2)

    def test_addFrom_getBack(self):
        """
        check if successfully connect another node to the current node by give only positive weight (>=0)
        """
        node = Node(1)
        node.addFrom(5, 2)
        node.addFrom(4, 9)
        node.addTo(13, 2.2)
        for i in node.getBack():
            if i != 5 and i != 4:
                self.fail("not good")

    def test_removeForw_removeBack(self):
        """
        check if successfully disconnect between two nodes
        """
        node.addTo(2, 0.23)
        node.addTo(7, 1.3)
        node.addTo(8, 1.3)
        node.removeForw(8)
        node.addFrom(5, 2)
        node.addFrom(4, 9)
        node.removeBack(12)
        for i in node.getForw():
            self.assertNotEqual(i, 8)
        for i in node.getBack():
            self.assertNotEqual(i, 12)
