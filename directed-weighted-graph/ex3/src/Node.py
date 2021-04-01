class Node:
    """
    this class presents a node in a graph
    """
    defKey = 0

    def __init__(self, Key: int,  local=(0.0, 0.0), tag=0, nw=0.0, info="", **kwargs):
        self.__key = Key
        self.__local = local
        self.__tag = tag
        self.__nw = nw
        self.__info = info
        self.__forw = dict()
        self.__back = dict()
        self.__prev = -1

    def __str__(self) -> str:
        return f"\"id\": {self.__key}, \"pos\": {self.__local}"

    def __repr__(self) -> str:
        return f"{{\"id\": {self.__key}, \"pos\": {self.__local}}}"

    def getKey(self) -> int:
        return self.__key

    def getLocal(self) -> tuple:
        return self.__local

    def setLocal(self, local: tuple):
        self.__local = local

    def getWeight(self) -> float:
        return self.__nw

    def setWeight(self, nw: float):
        self.__nw = nw

    def getInfo(self) -> str:
        return self.__info

    def setInfo(self, info: str):
        self.__info = info

    def getTag(self) -> int:
        return self.__tag

    def setTag(self, tag: str):
        self.__tag = tag

    def getPrev(self):
        return self.__prev

    def setPrev(self, prev: int):
        self.__prev = prev

    def addTo(self, key: int, w: float):
        """
        create edge from this node to key with the weight w
        """
        self.__forw[key] = w

    def addFrom(self, key: int, w: float):
        """
        create edge from key to this node with the weight w
        """
        self.__back[key] = w

    def getW(self, dest: int) -> float:
        """
        return the weight between this node to dest
        """
        return self.__forw.get(dest)

    def getForw(self) -> dict:
        """
        return a dictionary of all the nodes connected from node_id , each node is represented using a pair (key,
        weight)
        """
        return self.__forw.copy()

    def getBack(self) -> dict:
        """
        return a dictionary of all the nodes connected to (into) node_id ,
        each node is represented using a pair (key, weight)
        """
        return self.__back.copy()

    def removeForw(self, key: int) -> bool:
        """
        disconnect the edge between from this node to key if there is any
        """
        if self.__forw.get(key) != None:
            self.__forw.pop(key)
            return True
        return False

    def removeBack(self, key: int) -> bool:
        """
        disconnect the edge between from key to this node if there is any
        """
        if self.__back.get(key) != None:
            self.__back.pop(key)
            return True
        return False




