**Directed and weighted graph at python.**

***

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/depositphotos_200527056-stock-illustration-graph-network-logo-icon-design.jpg)

**the class Nodes** --> presents the information of a node_data type
and present all the ribs that go to and go from the mode on the graph.

**the class DiGraph** --> presents the information of a directed_weighted_graph type.
also has the option to ask if two nodes are connected and get the wieght of the edge between them. connecet between two nodes with direction or disconnect,
get all the information about node in the graph ,to add and delete nodes ,and has the information 
about the edge ,nodes sizes, and how much changes was made in the graph.

**the class GraphAlgo** --> has object type directed_weighted_graph and can return the question what the weight between two nodes if there is
any, what the path between them and finds the Strongly Connected Component(SCC) of a single node id1 is a part of and also all the Strongly Connected Component(SCC) in the graph.
can save the information about the current DiGraph to file and get infomation about DiGraph from file and build new one.
and also draw the DiGraph on a frame.

**explain about bfs:** 
the function add the key of the node that has been seen to the list and set the weight from node(src) untils the current node. the wight represent the sum of all the wieght of the nodes go through between node(src) to this current node. than add it to the list() - map_dict.
this functionrun in O(n) at the worst case - all the node can see only one time because the map save their key.

**explain about scc:**
the function get two numbers - id1, id2. and check: 
1. if the two number are equals then return false. 
2. if there is not path from node(id1) to node(id2) return false. use at bfs algorithm
3. if there is not path from node(id2) to node(id1) return false. use at bfs algorithm
4. return true.

***

The program receives a graph from the Json file and allows you to do a variety of actions on it,
for example:
>* Drawing the graph by interface "Plot".
> * Get the shortest path between two points.
> * Get the weight of the shortest way between two points.
> * Save and load the graph using the Json file format.
> * Get the connect component of one node on the graph.
>> and more options that will be detailed later....


***
* **in plot the program produces a randomly graph so that between each edge of a large nodes to a small edge the edge is red, else the edge is green**

The program ran time check relative to NetWork program,
A run time check was done between the programs we did in Java, this program and the program in NetWork.
The results can be seen in the graph below (the numbers are run times in seconds)


**Tested on a computer**

***

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/PC-chack.png)


**Graph construction**

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/do%20graph.png)


**shortest path**

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/shortest%20path.png)


**connected component**

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/connected_component(node-0).png)


**connected components**

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/connected_components.png)



***

To see run times you need to run the classes:
1) Compare_Graph
2) Compare_net

* Compare_Graph:
Checking out the program we built ... printing times of graph construction, shortest path, and connected components

* Compare_net: 
Which prints times of graph construction and short cut


(connected components the bindings of 8,000_1,000. 10,000_80.00. 20,000. 30,000 full-time lengths so we did not run them)

***

There are tests for all the classes, including tests given by the lecturers.
(pictures of the testers given by the lecturers)

**chack 0 attempts 1** 

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/check0-picture1.png)

**chack 0 attempts 2** 

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/check0-picture2.png)


**chack 1 attempts 1** 

![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/check1-picture1.png)

**chack 1 attempts 2**
 
![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/check1-picture2.png)

**chack 2**
 
![](https://github.com/LIADN7/Directed-and-weighted-graph-on-Py/blob/master/imgs/check2.png)



***


**[@authors liadn7](https://github.com/liadn7)**

**[@authors avielc11](https://github.com/avielc11)**
