//package tra;
import java.util.*;
public class TRA {
int N; // number of vertices in the graph
boolean[][] G; // adjacency matrix
int currPos;
TRA(int size, int loc) {
N = size;
currPos = loc;
setupGraph();
}
void setupGraph() {
G = new boolean[N][N];
// Graph from lab
G[0][1] = G[1][0] = true;
G[0][8] = G[8][0] = true;
G[0][4] = G[4][0] = true;
G[1][2] = G[2][1] = true;
G[1][3] = G[3][1] = true;
G[2][6] = G[6][2] = true;
G[3][4] = G[4][3] = true;
G[3][5] = G[5][3] = true;
G[6][7] = G[7][6] = true;
G[8][9] = G[9][8] = true;
G[9][10] = G[10][9] = true;
G[10][11] = G[11][10] = true;
}
void BFS(int start, int destination) {
Queue<Integer> queue = new LinkedList<>();
boolean[] visited = new boolean[N];
Map<Integer, Integer> parent = new HashMap<>();
queue.add(start);
System.out.println("\nStarting BFS from " + retCity(start) + " to " + retCity(destination));
System.out.println("Visited Path:");
while (!queue.isEmpty()) {
int node = queue.remove();
if (!visited[node]) {
visited[node] = true;
System.out.print(retCity(node) + " ");
// Check if reached destination
if (node == destination) {
System.out.println("\nDestination reached!");
printPath(parent, start, destination);
return;
}
// Add connected cities to stack
for (int i = N - 1; i >= 0; i--) {
if (G[node][i] && !visited[i]) {
queue.add(i);
parent.put(i, node);
}
}
}
}
System.out.println("\nDestination not reachable!");
}
// --- Print Path ---
void printPath(Map<Integer, Integer> parent, int start, int dest) {
List<Integer> path = new ArrayList<>();
int curr = dest;
path.add(curr);
while (parent.containsKey(curr)) {
curr = parent.get(curr);
path.add(curr);
}
Collections.reverse(path);
System.out.println("\nFull Path:");
for (int i : path) {
System.out.print(retCity(i));
if (i != dest)
System.out.print(" -> ");
}
System.out.println();
}



public static void main(String[] args) {
Scanner inp = new Scanner(System.in);
TRA traGraph = new TRA(12, 0);
System.out.println("List of Cities:");
for (int i = 0; i < 12; i++)
System.out.println("[" + i + "] " + retCity(i));
System.out.print("\nEnter start city number: ");
int start = inp.nextInt();
System.out.print("Enter destination city number: ");
int dest = inp.nextInt();
if (start < 0 || start >= 12 || dest < 0 || dest >= 12) {
System.out.println("Invalid input!");
System.exit(0);
}
traGraph.BFS(start, dest);
}
public static String retCity(int i) {
if (i == 0) return "Buraydah";
else if (i == 1) return "Unayzah";
else if (i == 2) return "AlZulfi";
else if (i == 3) return "Al-Badai";
else if (i == 4) return "Riyadh-Alkhabra";
else if (i == 5) return "AlRass";
else if (i == 6) return "UmSedrah";
else if (i == 7) return "Shakra";
else if (i == 8) return "Al-Bukayriyah";
else if (i == 9) return "Sheehyah";
else if (i == 10) return "Dhalfa";
else return "Mulida";
}
}
