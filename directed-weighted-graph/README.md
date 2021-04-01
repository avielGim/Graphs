# Directed and weighted graph

***

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/depositphotos_200527056-stock-illustration-graph-network-logo-icon-design.jpg)

the class Nodes presents the information of a node_data type
and present all the ribs that go to and go from the mode on the graph.
*   the constructor (init) must get a value for the key and set the loction 
use in dictionary method -
*	forw - save the edge that this key is the source node.
*	back - save the numbers (keys) this key is the destination node.
the Nodes has:
*	key - int - can get.
*	tag - int - can get and set.
*	weight - float - can get and set.
*	info - str - can get and set.
*	local - tuple(x, y) - can get and set.
* prev - int - can get and set.
* addTo(int num, float w) - check if forw contains the given num. if false -> atach the num - w and add them to forw 
* addFrom(int num, float w) - check if back contains the given num. if false -> atach the num - w and add them to back 
*	getW(int dest) - search the edge in forward if forward contains value that atach to the number -> return the value.
*	removeForw(int node) - delete the edge atach to the given number at forw(dict)
*	removeBack(int node) - delete the number atach to the given number at back(dict)
* str - print string(the key and the pos)
* repr - print string in list kind(the key and the pos)


the class DiGraph presents the information of a directed_weighted_graph type.
also has the option to ask if two nodes are connected and get the wieght of the edge between them. connecet between two nodes with direction or disconnect,
get all the information about node in the graph ,to add and delete nodes ,and has the information 
about the edge ,nodes sizes, and how much changes was made in the graph.
use in dictionary method -
*	nodes - dict() - save all the node inside the graph - can get as a collection by use the function getV().
the DiGraph has:
*   v_size(self) -> int - can get the number of node in the graph
*   e_size(self) -> int - can get the number of ribs in the graph
*   get_all_v(self) -> dict: Return a dictionary of all the nodes in the Graph, each node is represented using a pair (key, node_data)
*   all_in_edges_of_node(self, id1: int) -> dict: Return a dictionary of all the nodes connected to (into) node_id, each node is represented using a pair (key, weight)
*   all_out_edges_of_node(self, id1: int) -> dict: Return a dictionary of all the nodes connected from node_id, each node is represented using a pair (key, weight)
*   get_mc(self) -> int: Returns the current version of this graph, on every change in the graph state - the MC should be increased
*   add_edge(self, id1: int, id2: int, weight: float) -> bool: Connect between two nodes with direction 
*   add_node(self, node_id: int, pos: tuple = None) -> bool: Adds a node to the graph.
*   remove_node(self, node_id: int) -> bool: Removes a node from the graph and all is edges.
*   remove_edge(self, node_id1: int, node_id2: int) -> bool: disconnect between two nodes (directed)
*   getNode(self, id1: int) -> Node:  Return a Node by the node key.
*   getAllLocation(self): Returns a list of all locations of each Node.


the class GraphAlgo has object type directed_weighted_graph and can return the question what the weight between two nodes if there is
any, what the path between them and finds the Strongly Connected Component(SCC) of a single node id1 is a part of and also all the Strongly Connected Component(SCC) in the graph.
can save the information about the current DiGraph to file and get infomation about DiGraph from file and build new one.
and also draw the DiGraph on a frame.
the GraphAlgo has:
*   get_graph(self) -> DiGraph: return the graph this class is work on. 
*   load_from_json(self, file_name: str) -> bool: if successfully create new DiGraph from the data in file. The file is written as json.
*   save_to_json(self, file_name: str) -> bool: if successfully save the data of a graph this class is work on to file. Written as json file.
*   shortest_path(self, id1: int, id2: int) -> (float, list): finds the shortest path between node(id1) to node(id2).
*   connected_component(self, id1: int) -> list: finds the Strongly Connected Component(SCC) that node id1 is a part of.
*   connected_components(self) -> List[list]:  finds all the Strongly Connected Component(SCC) in the graph.
*   plot_graph(self) -> None: draw the graph on Plot so the edge in the high NodeKey to the low is red else the edge is green..
use at other algorithms:
*   the function shortest_path use at bfs algorithm.
*   the function connected_component use at scc algorithm. 
*   the function connected_components use at connected_component for each node

explain about bfs -
the function add the key of the node that has been seen to the list and set the weight from node(src) untils the current node. the wight represent the sum of all the wieght of the nodes go through between node(src) to this current node. than add it to the list() - map_dict.
this functionrun in O(n) at the worst case - all the node can see only one time because the map save their key.

explain about scc -
the function get two numbers - id1, id2. and check: 
1. if the two number are equals then return false. 
2. if there is not path from node(id1) to node(id2) return false. use at bfs algorithm
3. if there is not path from node(id2) to node(id1) return false. use at bfs algorithm
4. return true.




**[@authors liadn7](https://github.com/liadn7)**

**[@authors avielc11](https://github.com/avielc11)**
