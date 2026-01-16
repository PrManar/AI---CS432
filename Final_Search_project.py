# # --------------------------------------------------
مسار عمقي (لیس بالضرورة الأقصر) :DFS # #
# # --------------------------------------------------
# def dfs(graph, start, goal):
# stack = [start]
# visited = {start: None} # parent
# while stack:
# current = stack.pop()
# if current == goal:
# path = []
# while current is not None:
# path.append(current)
# current = visited[current]
# path.reverse()
# total_dist = 0.0
# for i in range(len(path) - 1):
# a, b = path[i], path[i+1]
# total_dist += graph[a][b]
# return path, total_dist
# for neigh in graph[current]:
# if neigh not in visited:
# visited[neigh] = current
# stack.append(neigh)
# return None, None
# # --------------------------------------------------
(heuristic یستخدم) أقصر مسار حسب المسافة : *A # #
# # --------------------------------------------------
# def astar(graph, coords, start, goal):
# # (f, g, node)
# open_heap = []
# heapq.heappush(open_heap, (0.0, 0.0, start))
# came_from = {start: None}
# g_cost = {start: 0.0}
# while open_heap:
# f, g, current = heapq.heappop(open_heap)
# if current == goal:
إعادة بناء المسار # #
# path = []
# while current is not None:
# path.append(current)
# current = came_from[current]
# path.reverse()
# return path, g_cost[goal]
# for neigh, w in graph[current].items():
# tentative_g = g_cost[current] + w
# if neigh not in g_cost or tentative_g < g_cost[neigh]:
# g_cost[neigh] = tentative_g
# h = heuristic(neigh, goal, coords)
# f_new = tentative_g + h
# came_from[neigh] = current
# heapq.heappush(open_heap, (f_new, tentative_g, neigh))
# return None, None
# # --------------------------------------------------
مثال استخدام # #
# # --------------------------------------------------
# if __name__ == "__main__":
# graph = load_graph("graph.txt")
# coords = load_coords("coords.txt")
على كیفك goal و start غیري الـ # #
# start = "fms"
# goal= "hudba_cafe"
# print("BFS:")
# path, dist = bfs(graph, start, goal)
# print("path:", path)
# print("total distance:", dist)
# print("\nDFS:")
# path, dist = dfs(graph, start, goal)
# print("path:", path)
# print("total distance:", dist)
# print("\nA*:")
# path, dist = astar(graph, coords, start, goal)
# print("path:", path)


# print("total distance:", dist)
# # -*- coding: utf-8 -*-
# import math
# from collections import deque
# import heapq
# import time
# # --------------------------------------------------
txt.coords قراءة ملف الإحداثیات # #
# # --------------------------------------------------
# def load_coords(filename="coords.txt"):
# coords = {}
# with open(filename, "r", encoding="utf-8") as f:
# for line in f:
# line = line.strip()
# if not line:
# continue
# parts = line.split()
# name = parts[0]
# lat = float(parts[1])
# lon = float(parts[2])
# coords[name] = (lat, lon)
# return coords
# # --------------------------------------------------
txt.graph قراءة ملف القراف # #
# # --------------------------------------------------
# def load_graph(filename="graph.txt"):
# graph = {}
# with open(filename, "r", encoding="utf-8") as f:
# for line in f:
# line = line.strip()
# if not line:
# continue
# parts = line.split()
# a = parts[0]
# b = parts[1]
# w = float(parts[2])
(graphundirected (نضیف العلاقة في الاتجاھین # #
# graph.setdefault(a, {})[b] = w
# graph.setdefault(b, {})[a] = w
# return graph
# # --------------------------------------------------
(heuristic للـ) دالة تحسب المسافة الإقلیدیة بین نقطتین # #
# # --------------------------------------------------
# def heuristic(node, goal, coords):
# (lat1, lon1) = coords[node]
# (lat2, lon2) = coords[goal]
# dx = lat1 - lat2
# dy = lon1 - lon2
# return math.sqrt(dx * dx + dy * dy)
# # --------------------------------------------------
أقصر مسار بعدد الخطوات (لا یھتم بالمسافات) :BFS # #
# # --------------------------------------------------
# def bfs(graph, start, goal):
# queue = deque()
# queue.append(start)
# visited = {start: None} # parent
# nodes_visited = 0
# while queue:
# current = queue.popleft()
# nodes_visited += 1
# if current == goal:
إعادة بناء المسار # #
# path = []
# while current is not None:
# path.append(current)
# current = visited[current]
# path.reverse()
نحسب المسافة الكلیة بناءً على الأوزان # #
# total_dist = 0.0
# for i in range(len(path) - 1):
# a, b = path[i], path[i+1]
# total_dist += graph[a][b]
# return path, total_dist, nodes_visited
# for neigh in graph[current]:
# if neigh not in visited:
# visited[neigh] = current


# queue.append(neigh)
# return None, None, nodes_visited # مسار یوجد لا
# # --------------------------------------------------
مسار عمقي (لیس بالضرورة الأقصر) :DFS # #
# # --------------------------------------------------
# def dfs(graph, start, goal):
# stack = [start]
# visited = {start: None} # parent
# nodes_visited = 0
# while stack:
# current = stack.pop()
# nodes_visited += 1
# if current == goal:
# path = []
# while current is not None:
# path.append(current)
# current = visited[current]
# path.reverse()
# total_dist = 0.0
# for i in range(len(path) - 1):
# a, b = path[i], path[i+1]
# total_dist += graph[a][b]
# return path, total_dist, nodes_visited
# for neigh in graph[current]:
# if neigh not in visited:
# visited[neigh] = current
# stack.append(neigh)
# return None, None, nodes_visited
# # --------------------------------------------------
(heuristic یستخدم) أقصر مسار حسب المسافة : *A # #
# # --------------------------------------------------
# def astar(graph, coords, start, goal):
# open_heap = []
# heapq.heappush(open_heap, (0.0, 0.0, start))
# came_from = {start: None}
# g_cost = {start: 0.0}
# nodes_visited = 0
# while open_heap:
# f, g, current = heapq.heappop(open_heap)
# nodes_visited += 1
# if current == goal:
# path = []
# while current is not None:
# path.append(current)
# current = came_from[current]
# path.reverse()
# return path, g_cost[goal], nodes_visited
# for neigh, w in graph[current].items():
# tentative_g = g_cost[current] + w
# if neigh not in g_cost or tentative_g < g_cost[neigh]:
# g_cost[neigh] = tentative_g
# h = heuristic(neigh, goal, coords)
# f_new = tentative_g + h
# came_from[neigh] = current
# heapq.heappush(open_heap, (f_new, tentative_g, neigh))
# return None, None, nodes_visited
# # --------------------------------------------------
دالة لطباعة المسار بشكل واضح # #
# # --------------------------------------------------
# def print_path(path, graph):
# if not path:
# print("No path found")
# return
# total_dist = 0.0
# print("Path:")
# for i in range(len(path)-1):
# a, b = path[i], path[i+1]
# d = graph[a][b]
# total_dist += d
# print(f"{a} -> {b} : {d:.2f} meters")
# print(f"Total distance: {total_dist:.2f} meters")
# # --------------------------------------------------
مثال استخدام # #
# # --------------------------------------------------


# if __name__ == "__main__":
# graph = load_graph("graph.txt")
# coords = load_coords("coords.txt")
# start = "fms"
# goal = "hudba_cafe"
# print("===== BFS =====")
# t0 = time.time()
# path, dist, steps = bfs(graph, start, goal)
# t1 = time.time()
# print_path(path, graph)
# print(f"Nodes visited: {steps}")
# print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
# print("===== DFS =====")
# t0 = time.time()
# path, dist, steps = dfs(graph, start, goal)
# t1 = time.time()
# print_path(path, graph)
# print(f"Nodes visited: {steps}")
# print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
# print("===== A* =====")
# t0 = time.time()
# path, dist, steps = astar(graph, coords, start, goal)
# t1 = time.time()
# print_path(path, graph)
# print(f"Nodes visited: {steps}")
# print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
# -*- coding: utf-8 -*-
import math
from collections import deque
import heapq
import time
# --------------------------------------------------
txt.coords قراءة ملف الإحداثیات #
# --------------------------------------------------
def load_coords(filename="coords.txt"):
coords = {}
with open(filename, "r", encoding="utf-8") as f:
for line in f:
line = line.strip()
if not line:
continue
parts = line.split()
name = parts[0]
lat = float(parts[1])
lon = float(parts[2])
coords[name] = (lat, lon)
return coords
# --------------------------------------------------
txt.graph قراءة ملف القراف #
# --------------------------------------------------
def load_graph(filename="graph.txt"):
graph = {}
with open(filename, "r", encoding="utf-8") as f:
for line in f:
line = line.strip()
if not line:
continue
parts = line.split()
a = parts[0]
b = parts[1]
w = float(parts[2])
(graphundirected (نضیف العلاقة في الاتجاھین #graph.setdefault(a, {})[b] = w
graph.setdefault(b, {})[a] = w
return graph
# --------------------------------------------------
(heuristic للـ) دالة تحسب المسافة الإقلیدیة بین نقطتین #
# --------------------------------------------------
def heuristic(node, goal, coords):
(lat1, lon1) = coords[node]
(lat2, lon2) = coords[goal]
dx = lat1 - lat2
dy = lon1 - lon2
return math.sqrt(dx * dx + dy * dy)
# --------------------------------------------------
أقصر مسار بعدد الخطوات (لا یھتم بالمسافات) :BFS #


# --------------------------------------------------
def bfs(graph, start, goal):
queue = deque()
queue.append(start)
visited = {start: None} # parent
visited_nodes = set() # الفریدةالعقد
while queue:
current = queue.popleft()
visited_nodes.add(current)
if current == goal:
إعادة بناء المسار #path = []
while current is not None:
path.append(current)
current = visited[current]
path.reverse()
نحسب المسافة الكلیة بناءً على الأوزان #total_dist = 0.0
for i in range(len(path) - 1):
a, b = path[i], path[i+1]
total_dist += graph[a][b]
return path, total_dist, len(visited_nodes)
for neigh in graph[current]:
if neigh not in visited:
visited[neigh] = current
queue.append(neigh)
return None, None, len(visited_nodes) # مسار یوجد لا
# --------------------------------------------------
مسار عمقي (لیس بالضرورة الأقصر) :DFS #
# --------------------------------------------------
def dfs(graph, start, goal):
stack = [start]
visited = {start: None} # parent
visited_nodes = set() # الفریدةالعقد
while stack:
current = stack.pop()
visited_nodes.add(current)
if current == goal:
path = []
while current is not None:
path.append(current)
current = visited[current]
path.reverse()
total_dist = 0.0
for i in range(len(path) - 1):
a, b = path[i], path[i+1]
total_dist += graph[a][b]
return path, total_dist, len(visited_nodes)
for neigh in graph[current]:
if neigh not in visited:
visited[neigh] = current
stack.append(neigh)
return None, None, len(visited_nodes)
# --------------------------------------------------
(heuristic یستخدم) أقصر مسار حسب المسافة : *A #
# --------------------------------------------------
def astar(graph, coords, start, goal):
open_heap = []
heapq.heappush(open_heap, (0.0, 0.0, start))
came_from = {start: None}
g_cost = {start: 0.0}
visited_nodes = set() # الفریدةالعقد
while open_heap:
f, g, current = heapq.heappop(open_heap)
visited_nodes.add(current)
if current == goal:
path = []
while current is not None:
path.append(current)
current = came_from[current]
path.reverse()
return path, g_cost[goal], len(visited_nodes)
for neigh, w in graph[current].items():
tentative_g = g_cost[current] + w
if neigh not in g_cost or tentative_g < g_cost[neigh]:
g_cost[neigh] = tentative_g



h = heuristic(neigh, goal, coords)
f_new = tentative_g + h
came_from[neigh] = current
heapq.heappush(open_heap, (f_new, tentative_g, neigh))
return None, None, len(visited_nodes)
# --------------------------------------------------
دالة لطباعة المسار بشكل واضح #
# --------------------------------------------------
def print_path(path, graph):
if not path:
print("No path found")
return
total_dist = 0.0
print("Path:")
for i in range(len(path)-1):
a, b = path[i], path[i+1]
d = graph[a][b]
total_dist += d
print(f"{a} -> {b} : {d:.2f} meters")
print(f"Total distance: {total_dist:.2f} meters")
# --------------------------------------------------
مثال استخدام #
# --------------------------------------------------
if __name__ == "__main__":
graph = load_graph("graph.txt")
coords = load_coords("coords.txt")
start = "university_hospital_entrance"
goal = "dental#_clinic"
print("===== BFS =====")
t0 = time.time()
path, dist, nodes_expanded = bfs(graph, start, goal)
t1 = time.time()
print_path(path, graph)
print(f"Nodes Expanded (unique): {nodes_expanded}")
print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
print("===== DFS =====")
t0 = time.time()
path, dist, nodes_expanded = dfs(graph, start, goal)
t1 = time.time()
print_path(path, graph)
print(f"Nodes Expanded (unique): {nodes_expanded}")
print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
print("===== A* =====")
t0 = time.time()
path, dist, nodes_expanded = astar(graph, coords, start, goal)
t1 = time.time()
print_path(path, graph)
print(f"Nodes Expanded (unique): {nodes_expanded}")
print(f"Time elapsed: {t1 - t0:.6f} seconds\n")
