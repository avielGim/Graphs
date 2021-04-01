# SIMPLE GRAPH

the class DodeData has two constructors 1)new nodedata. 2 ) deep copy - copy all the data from another node_data exept from the neighbor.
	also has the option to ask if another node is connected to this node, get all the information about his neighbors,to add and delete another node_data to the neighbors, ant get all the information about this node.

the class Graph_DS has two constructors 1)new Graph_DS 2 ) deep copy - copy all the data from another graph include the nodes and all the connections.
	also has the option to ask if two nodes are connected, connecet between two nodes , get all the information about node in the graph,to add and delete nodes ,and has the information about the edge ,nodes sizes, and how much changes was made in the graph.

the class Graph_Algo has object type graph and can return the question what the langth between two nodes if there is any, what the path between them and if there is a valid path between all the nodes.


*1-------------------- the idea of all the linkedlist -------------------- 
o	q - save all the nodes that this function change their tag and at the end restart the tag to '0'.
o	list - remember the path from node(dest) to node(src). go backwards.
o	qtemp - temporary linkedlist with it help we go over the nodes from node(dest) to node(src). 

explain about bfs -
the function add the node that has been seen to the list and change it to the number "count". count represent the number of all the nodes go through between node(src) to this current node.
this functionrun in O(n) at the worst case - all the node can see only one time because all the nodes hava tag. if we see the node we "tag" it.
 in the best case O(1) - if the given number are equals the return 0 or only the node(src). if the node(src) ot the node(dest) not in the graph return null or -1.

1.	isConnected - check if there is a valid path from one node to the rest of the nodes in this graph. in this function i use at the idea of "bfs". 
first if there is only one node in the graph return true beause no need for any connection.
new linkedlist called "t1", that save all the node we saw. add the first node in the graph. (by get the collection of all noeds in the graph and use the function "iterator".
after that, run with a loop on t1. pool the first from the list (idea FIFO) and check his neighbors. check the tag each one, if it is equal to '0' it's means we are not see it yet. than do 2 things- 1) "tag" it by change the tag to '1'. 2) add it to t1.
after t1 is empty it mean that we saw all the nodes, that in one way or another, relate to the first noed we saw.
size variable, count all the node that have the tag '1'. if it '1' 1) restart and set the tag to '0' 2)size++. else - we wasn't see this node.
if size eqaul to the number of the nodes in this graph - return true because each one of the node have a valid path to another.	

2.	shortestPathDist - get two numbers , first check if there a nodes with the key src and dest. if there is than send to the function "bfs" that return a Queue who remember all the nodes the function saw. if it different from null it means that there is a valid path from the node(src) to the node(dest). else "bfs" = null return -1.
int count that get the tag from the node with the key - dest. go over all the nodes inside q and restart the tag to '0'. return count.  
explain about all the linkedlist - *1	

3.	shortestPath -  get two numbers , first check if there a nodes with the key src and dest. if there is than send to the function "bfs" that return a Queue who remember all the nodes the function saw. if it different from null it means that there is a valid path from the node(src) to the node(dest). else "bfs" = null - no path -return null.
add new linkedlist - list - remember the path from node(src) to node(dest). int count = node(dest).getTag -1. add to list node(dest).
add new linkedlist - qtemp. go all over in q and restart their tag to '0'. 
explain about all the linkedlist - *1

bfs - get two numbers , first check if there a nodes with the key src and dest. if there is, than curr = get the node with the key src in this graph. int count = 1.  set tag to curr to 'count' -the first node.
add curr to q and qtemp. while qtemp is not empty, bool = false and get out the first node in the line in qtemp and call it curr, count = curr.getTag + 1. 
loop that go all over the nieghbors of curr and 
	1) if n(getkey) = dest than set the tag to 'count'. add n to q and bool = true. 
	2)	a. else if n.getTag > 'count' this means this n already has been seen and the path from node(src) is shorter. than setTag for n to 'count'. 
		b. else if n(get tag) is '0' this means we haven't seen it yet, than setTag for n to 'count'. 
			set tag for n to "count" and add it to qtemp and q.	
end loop.	
count = to the tag of node(dest). if 'count' != 0. return q.
else go over all node inside q and restart the tag to '0'. return count. and now q = null.
explain about all the linkedlist - *1
