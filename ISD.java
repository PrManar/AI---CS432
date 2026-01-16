//package tra;
import java.util.*;
public class TRA {
int N; // number of vertices in the graph
boolean[][] G; // adjacency matrix
TRA(int size) {
N = size;
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
// --- IDS Implementation ---
void IDS(int start, int destination) {
System.out.println("\nStarting IDS from " + retCity(start) + " to " + retCity(destination));
Map<Integer, Integer> parent = new HashMap<>();
for (int limit = 0; limit <= N; limit++) {
System.out.println("\n--- Depth Limit: " + limit + " ---");
boolean[] visited = new boolean[N];
parent.clear();
String result = recursiveDLS(start, destination, limit, visited, parent, 0);
if (result.equals("found")) {
System.out.println("\nGoal found at depth " + limit + "!");
printPath(parent, start, destination);
return;
}
}
System.out.println("\nFailure: Goal not found in the maximum depth limit of the graph.");
}
// Recursive DLS used by IDS
String recursiveDLS(int node, int destination, int limit, boolean[] visited, Map<Integer, Integer> parent, int depth) {
System.out.print(retCity(node) + " ");
if (node == destination)
return "found";
if (depth == limit)
return "cutoff";
visited[node] = true;
for (int i = 0; i < N; i++) {
if (G[node][i] && !visited[i]) {
parent.put(i, node);
String result = recursiveDLS(i, destination, limit, visited, parent, depth + 1);
if (result.equals("found"))
return "found";
}
}
return "failure";
}
// Print path function
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



for (int i = 0; i < path.size(); i++) {
System.out.print(retCity(path.get(i)));
if (i != path.size() - 1)
System.out.print(" -> ");
}
System.out.println();
}
public static void main(String[] args) {
Scanner inp = new Scanner(System.in);
TRA traGraph = new TRA(12);
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
traGraph.IDS(start, dest);
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
