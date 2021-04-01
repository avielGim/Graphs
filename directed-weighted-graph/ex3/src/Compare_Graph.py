from DiGraph import DiGraph
from ex3.src.GraphAlgo import GraphAlgo
import time
import datetime

def build_G_10_80_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_10_80_0.json")
    if flag:
        print("successfully construction from \"G_10_80_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(0, 4)
        print("shortest_path 0 -->> 4 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")

def build_G_100_800_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_100_800_0.json")
    if flag:
        print("successfully construction from \"G_100_800_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(48, 4)
        print("shortest_path 48 -->> 4 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")

def build_G_1000_8000_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_1000_8000_0.json")
    if flag:
        print("successfully construction from \"G_1000_8000_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(400, 700)
        print("shortest_path 400 -->> 700 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")

def build_G_10000_80000_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_10000_80000_0.json")
    if flag:
        print("successfully construction from \"G_10000_80000_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(7005, 70)
        print("shortest_path 7005 -->> 70 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")

def build_G_20000_160000_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_20000_160000_0.json")
    if flag:
        print("successfully construction from \"G_20000_160000_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(5000, 15250)
        print("shortest_path 5000 -->> 15250 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")

def build_G_30000_240000_0():
    algo = GraphAlgo()
    st = time.time()
    flag = algo.load_from_json("../data/G_30000_240000_0.json")
    if flag:
        print("successfully construction from \"G_30000_240000_0.json\" run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.shortest_path(0, 4)
        print("shortest_path 0 -->> 4 run time: ", round(time.time() - st, 3), " sec")
        """
        st = time.time()
        algo.connected_component(0)
        print("connected_component - node 0 run time: ", round(time.time() - st, 3), " sec")
        st = time.time()
        algo.connected_components()
        print("connected_components run time: ", round(time.time() - st, 3), " sec")
        """

if __name__ == '__main__':
    build_G_10_80_0()
    build_G_100_800_0()
    build_G_1000_8000_0()
    build_G_10000_80000_0()
    build_G_20000_160000_0()
    build_G_30000_240000_0()
    print("done")
