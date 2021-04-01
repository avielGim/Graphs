import networkx as nx
import json
import time
import datetime

def build_10_80_0():
    st = time.time()
    grp = load_json("../data/G_10_80_0.json")
    print("successfully construction from \"G_10_80_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 0, 4, weight='weight')
    print("shortest_path 0 -->> 4 run time: ", round(time.time() - st, 3), " sec")


def build_100_800_0():
    st = time.time()
    grp = load_json("../data/G_100_800_0.json")
    print("successfully construction from \"G_100_800_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 48, 4, weight='weight')
    print("shortest_path 48 -->> 4 run time: ", round(time.time() - st, 3), " sec")


def build_1000_8000_0():
    st = time.time()
    grp = load_json("../data/G_1000_8000_0.json")
    print("successfully construction from \"G_1000_8000_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 400, 700, weight='weight')
    print("shortest_path 400 -->> 700 run time: ", round(time.time() - st, 3), " sec")


def build_10000_80000_0():
    st = time.time()
    grp = load_json("../data/G_10000_80000_0.json")
    print("successfully construction from \"G_10000_80000_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 7005, 70, weight='weight')
    print("shortest_path 7005 -->> 70 run time: ", round(time.time() - st, 3), " sec")

def build_20000_160000_0():
    st = time.time()
    grp = load_json("../data/G_20000_160000_0.json")
    print("successfully construction from \"G_20000_160000_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 5000, 15250, weight='weight')
    print("shortest_path 5000 -->> 15250 run time: ", round(time.time() - st, 3), " sec")

def build_30000_240000_0():
    st = time.time()
    grp = load_json("../data/G_30000_240000_0.json")
    print("successfully construction from \"G_30000_240000_0.json\" run time: ", round(time.time() - st, 3), " sec")
    st = time.time()
    nx.shortest_path(grp, 0, 4, weight='weight')
    print("shortest_path 0 -->> 4 run time: ", round(time.time() - st, 3), " sec")

def load_json(file_name: str):
        graph = nx.DiGraph()
        dict_graph = list()
        try:
            with open(file_name, "r") as file:
                dict_graph = json.load(file)
                for node in dict_graph['Nodes']:
                    graph.add_node(node["id"])
                for edge in dict_graph["Edges"]:
                    graph.add_edge(edge["src"], edge["dest"], weight=edge["w"])
        except IOError as e:
            print(e)
        return graph

if __name__ == '__main__':
    build_10_80_0()
    build_100_800_0()
    build_1000_8000_0()
    build_10000_80000_0()
    build_20000_160000_0()
    build_30000_240000_0()
    print("done")