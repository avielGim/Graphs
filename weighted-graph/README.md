# weighted graph

the inner class nodeInfo that inside WGraph_DS has 3 constructors 
	1) new nodeInfo. 
	2) deep copy - copy all the data from another node_info exept from the neighbor.
	also has the option to ask if another node is connected to this node and the wight between them. 
	to add and delete another node_info to and from the neighbors, and get all the information
	about this node.

the class WGraph_DS has two constructors 
	1) new Graph_DS. 
	2) deep copy - copy all the data from another graph include the nodes , all the connections
	     and their weight.
	also has the option to ask if two nodes are connected and get the wieght. connecet between two nodes ,
	get all the information about node in the graph ,to add and delete nodes ,and has the information 
	about the edge ,nodes sizes, and how much changes was made in the graph.

1. first check that the node who has the key - node_id - is different from null. if true than go over the collection 
of the node and create a list that get the info of the node from the graph and the key and the tag (present the weight) from the getNi function.
return the nodes that has connect to this current node.
the info we get from the graph is to see if it not has been changed.

the class WGraph_Algo  has object type graph and can return the question what the weight between two nodes if there is
any, what the path between them and if there is a valid path between all the nodes.
also can save the information about the current WGraph_DS to file and get infomation about WGraph_DS and build new one.  

explain about bfs -
the function add the node that has been seen to the list and change it to the weight from node(src) untils the current node. the wight represent the sum of all the wieght of the nodes go through between node(src) to this current node.
this functionrun in O(n) at the worst case - all the node can see only one time because all the nodes hava tag. if we see the node we with wieght >= 0.
 in the best case O(1) - if the given number are equals then return 0 or only node(src) = node(dest). if the node(src) or the node(dest) not in the graph return null or -1.

1. isConnected - check if there is a valid path from one node to the rest of the nodes in this graph. in this function i use at the idea of "bfs". 
first if there is only one node in the graph return true beause no need for any connection.
build new linkedlist called "t1" that go over the first node - call it temporary - FIRST (no matter who)and add it to "t1". than go over with a loop on all his nieghbor if "t2" does not contains the key of the neighbor add it to "t1" and "t2". than poll the first one in the line (type queue) and do the same thing. after "t1" see all the nodes that has valid path to FIRST than get out of the loop. 
build new hashmap called "t2", that save all the node we saw. add the first node in the graph. (by get the collection of all noeds in the graph and use the function "iterator").
after that check it the size of "t2" equals to the number of the nodes inside the graph. if equals return true.


2. shortestPathDist - get two numbers , first check if there a nodes with the key src and dest. if there is than send to the function "bfs" that return a Queue who remember all the nodes the function saw. if it different from null it means that there is a valid path from the node(src) to the node(dest). else "bfs" = null return -1.
int count that get the weight from the node with the key - dest. go over all the nodes inside q and restart their tag to '0'. return count.  
explain about all the linkedlist - *1	

3. shortestPath -  get two numbers , first check if there a nodes with the key src and dest. if there is than send to the function "bfs" that return a Queue who remember all the nodes the function saw. if it different from null it means that there is a valid path from the node(src) to the node(dest). else "bfs" = null - no path -return null.
add new linkedlist - "list" - remember the path from node(src) to node(dest).
add new linkedlist - qtemp. go all over in q and restart their tag to '0' and restart prev to '0.1'. 
every node remember the the previous node that has the shortest gath from all the neighbors. 
go with a loop - get the node(dest) and add his previous to the "list". than get the node(previous) while the node(previous) different from node(src) than go in the loop.
finally add node(src) to "list". 
do qtemp thing and return "list".
explain about all the linkedlist - *1

4. save - use at the PrintWriter, file and StringBuilder functions.
go over the nodes and buils String to write the information about the current node in the loop. 
the String - the writing the key and his info - key,info. than ':' to write all the nodes that connect to the current node. 
after the char ':' it look like "key,info,tag". the tag represent the weight between them.  

5. load - take the information from the file and first split by the char ':' = String str.
than take str[0] and split by the char ',' = str1.
add to the graph - str1[0] = 'num' by the function Integer.parseInt. and set the info - str1[1].
than take str[1] and split by the char '!' = str2.
go over with a loop over str2[i] and split by the char ',' = str3. 
make connection between 'num' and str3[0] (Integer.parseInt). the weight is (Double.parseDouble) str3[2].
the idea of the funtion - take line create new NodeInfo and connect to it the neighbor. 

6. bfs - get two numbers - src and dest. curr is a type NodeInfo and point on the node with the key src in this graph. set his tag to = 1. 
add curr to q and qtemp. while (loop) qtemp is not empty than - 1. bool = false , 2. get out the first node in the line in qtemp and call it curr, tag = curr.getTag. 
loop that go all over the nieghbors of curr. n is type NodeInfo and repressent the neighbors. 
	1) if n(getkey) = dest than {only if n(getkey) > 'tag'} set the tag to 'tag'+the weight between curr and node(dest). add n to q and bool = true. 
	2)	a. else if n.getTag > 'tag' this means this n already has been seen and the path from node(src) is shorter. than setTag for n to 'tag' +the weight between curr and n(getkey). 
		b. else if n(get tag) is '0' this means we haven't seen it yet, than setTag for n to 'tag'+the weight between curr and n(getkey). set prev of n to curr.getkey.
			add n to qtemp and q.
end loop.	
tag = to the tag of node(dest). if 'tag' != 0. return q.
else go over all node inside q and restart the tag to '0' , and prev toreturn count. and now q = null.
this means there is no valid path between src to dest.
explain about all the linkedlist - *1

*1-------------------- the idea of all the linkedlist -------------------- 
o	q - save all the nodes that this function change their tag and prev. at the end restart the tag and prev to '0'.
o	list - remember the path from node(dest) to node(src). go backwards.
o	qtemp - temporary linkedlist with it help we go over the nodes from node(dest) to node(src). 
