from ex3.src.GraphAlgoInterface import GraphAlgoInterface
from ex3.src.DiGraph import DiGraph
from ex3.src.Edges import Edges
from ex3.src.Node import Node
import json
import matplotlib.pyplot as plt
import numpy as np

class GraphAlgo(GraphAlgoInterface):

    def __init__(self, grp=DiGraph):
        self.__grp = grp

    def get_graph(self) -> DiGraph:
        """
        :return: the directed graph on which the algorithm works on.
        """
        return self.__grp

    def load_from_json(self, file_name: str) -> bool:
        """
        Loads a graph from a json file.
        @param file_name: The path to the json file
        @returns True if the loading was successful, False o.w.
        """
        graph = DiGraph()
        dict_graph = list()
        try:
            with open(file_name, "r") as file:
                dict_graph = json.load(file)
                for i in dict_graph['Nodes']:
                    if i.__contains__("pos"):
                        s = i["pos"].split(",")
                        x = float(s[0])
                        y = float(s[1])
                        graph.add_node(i["id"], (x, y))
                    else:
                        graph.add_node(i["id"])
                for i in dict_graph["Edges"]:
                    graph.add_edge(i["src"], i["dest"], i["w"])
        except IOError as e:
            print(e)
            return False
        self.__grp = graph
        return True

    def save_to_json(self, file_name: str) -> bool:
        """
        Saves the graph in JSON format to a file
        @param file_name: The path to the out file
        @return: True if the save was successful, False o.w.
        """
        e = list()
        n = list()
        for i in self.__grp:
            no = {"id": i.getKey()}
            n.append(no)
            for j in i.getForw():
                edge = {"src": i.getKey(), "dest": j, "w": i.getW(j)}
                e.append(edge)
        dict_t = {"Nodes": n, "Edges": e}
        with open(file_name, "w") as file:
            json.dump(dict_t, default=lambda m: m.__dict__,fp=file)
            return True
        return False

    def shortest_path(self, id1: int, id2: int) -> (float, list):
        """
        Returns the shortest path from node id1 to node id2 using Dijkstra's Algorithm
        @param id1: The start node id
        @param id2: The end node id
        @return: The distance of the path, the path as a list
        Example:
#        >>> from GraphAlgo import GraphAlgo
#        >>> g_algo = GraphAlgo()
#        >>> g_algo.addNode(0)
#        >>> g_algo.addNode(1)
#        >>> g_algo.addNode(2)
#        >>> g_algo.addEdge(0,1,1)
#        >>> g_algo.addEdge(1,2,4)
#        >>> g_algo.shortestPath(0,1)
#        (1, [0, 1])
#        >>> g_algo.shortestPath(0,2)
#        (5, [0, 1, 2])
        More info:
        https://en.wikipedia.org/wiki/Dijkstra's_algorithm
        """
        if self.__grp.getNode(id1) == None or self.__grp.getNode(id2) == None:
            return 0, []
        q = list()
        if id1 == id2:
            return 0, [id1]
        temp_dict = self.bfs(id1, id2)
        if self.__grp.getNode(id2).getPrev() != -1:
            s = self.__grp.getNode(id2).getWeight()
            t = self.__grp.getNode(id2)
            q.append(id2)
            while t.getPrev() != -1:
                pre = t.getPrev()
                t.setPrev(-1)
                t.setWeight(0)
                q.insert(0, pre)
                t = self.__grp.getNode(pre)
            t.setWeight(0)
            lis = (s-1, q)
            return lis
        for i in temp_dict:
            t = self.__grp.getNode(i)
            t.setWeight(0)
            t.setPrev(-1)
        return 0, []

    def connected_component(self, id1: int) -> list:
        """
        Finds the Strongly Connected Component(SCC) that node id1 is a part of.
        @param id1: The node id
        @return: The list of nodes in the SCC
        """
        q = list()
        if self.__grp.getNode(id1) != None:
            for i in self.__grp.get_all_v():
                if self.scc(id1, i):
                    q.append(i)
        return q

    def connected_components(self): # -> List[list]:
        """
        Finds all the Strongly Connected Component(SCC) in the graph.
        @return: The list all SCC
        """
        size = self.__grp.v_size()
        if size <= 1:
            return
        lis = list()
        for i in self.__grp.get_all_v():
            q = self.connected_component(i)
            lis.append(q)
        return lis

    def plot_graph(self) -> None:
        """
        Plots the graph.
        If the nodes have a position, the nodes will be placed there.
        Otherwise, they will be placed in a random but elegant manner.
        @return: None
        """
        loc = self.__grp.getAllLocation()
        for i in loc:
            plt.scatter(loc[i][0], loc[i][1], color="m")
            plt.text(loc[i][0], loc[i][1], i)
            edges = self.__grp.all_out_edges_of_node(i)
            for j in edges:
                v = self.__grp.getNode(j).getLocal()
                x = v[0]
                y = v[1]
                if i>j:
                    plt.plot((loc[i][0], x), (loc[i][1], y), color="red")
                else:
                    plt.plot((loc[i][0]-0.00005, x-0.00005), (loc[i][1]-0.00005, y-0.00005), color="green")

        plt.show()

    def bfs(self, src: int, des: int):
        map_dict = list()
        qem = list()
        self.__grp.getNode(src).setWeight(1)
        map_dict.append(src)
        qem.append(src)
        way = -1
        for i in qem:
            b = False
            weight = self.__grp.getNode(i).getWeight()
            for j in self.__grp.all_out_edges_of_node(i):
                count = self.__grp.getNode(i).getW(j) + weight
                n = self.__grp.getNode(j)
                if j == des and (way > count or way == -1):
                    way = count
                    n.setWeight(way)
                    n.setPrev(i)
                    map_dict.append(n.getKey())
                    b = True
                elif (n.getWeight() > count or n.getWeight() == 0) and b == False:
                    n.setWeight(count)
                    n.setPrev(i)
                    qem.append(n.getKey())
                    map_dict.append(n.getKey())
        return map_dict

    def scc(self, id1: int, id2: int):
        if id1 == id2:
            return False
        if not self.bfs1(id1, id2):
            return False
        if not self.bfs1(id2, id1):
            return False
        return True

    def bfs1(self, src: int, des: int):
        map_dict = list()
        qem = list()
        self.__grp.getNode(src).setWeight(1)
        map_dict.append(src)
        qem.append(src)
        for i in qem:
            for j in self.__grp.all_out_edges_of_node(i):
                n = self.__grp.getNode(j)
                if j == des:
                    n.setWeight(1)
                    map_dict.append(n.getKey())
                    self.__clear_weight(map_dict)
                    return True
                elif n.getWeight() == 0:
                    n.setWeight(1)
                    qem.append(n.getKey())
                    map_dict.append(n.getKey())
        self.__clear_weight(map_dict)
        return False

    def __clear_weight(self, map_dict: list):
        for i in map_dict:
            self.__grp.getNode(i).setWeight(0)
