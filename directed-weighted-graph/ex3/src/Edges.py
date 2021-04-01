class Edges:

    def __init__(self, src: int, dest: int, weight: float):
        self.__src = src
        self.__dest = dest
        self.__weight = weight

    def __str__(self) -> str:
        return f"\"src\": {self.__src}, \"dest\": {self.__dest}, \"w\": {self.__weight}"

    def __repr__(self) -> str:
        return f"{{\"src\": {self.__src}, \"dest\": {self.__dest}, \"w\": {self.__weight}}}"

    def getSrc(self) -> int:
        return self.__src

    def getDest(self) -> int:
        return self.__dest

    def getWeight(self) -> float:
        return self.__weight

    def setWeight(self, weight: float) -> float:
        self.__weight = weight
