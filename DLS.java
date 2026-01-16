//package tra;
import java.util.*;
public class TRA {
int N; 
boolean[][] G; 
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
// DLS Implementation 
boolean cutoffOccurred;
void DLS(int start, int destination, int limit) {
boolean[] visited = new boolean[N];
Map<Integer, Integer> parent = new HashMap<>();
System.out.println("\nStarting DLS from " + retCity(start) + " to " + retCity(destination));
System.out.println("Depth Limit: " + limit);
System.out.println("Visited Path:");
cutoffOccurred = false;
String result = recursiveDLS(start, destination, limit, visited, parent, 0);
if (result.equals("found")) {
System.out.println("\nGoal found!");
printPath(parent, start, destination);
} else if (result.equals("cutoff")) {
System.out.println("\nCutoff: Goal not found within the given depth limit.");
} else {
System.out.println("\nFailure: Goal not found in the graph.");
}
}
String recursiveDLS(int node, int destination, int limit, boolean[] visited, Map<Integer, Integer> parent, int depth) {
System.out.print(retCity(node) + " ");
if (node == destination)
return "found";
if (depth == limit) {
cutoffOccurred = true;
return "cutoff";
}
visited[node] = true;
boolean cutoff = false;
for (int i = 0; i < N; i++) {
if (G[node][i] && !visited[i]) {
parent.put(i, node);
String result = recursiveDLS(i, destination, limit, visited, parent, depth + 1);
if (result.equals("cutoff"))
cutoff = true;
else if (result.equals("found"))
return "found";
}
}
if (cutoff)
return "cutoff";
else
return "failure";
}



// Function to print the found path
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
TRA traGraph = new TRA(12, 0);
System.out.println("List of Cities:");
for (int i = 0; i < 12; i++)
System.out.println("[" + i + "] " + retCity(i));
System.out.print("\nEnter start city number: ");
int start = inp.nextInt();
System.out.print("Enter destination city number: ");
int dest = inp.nextInt();
System.out.print("Enter depth limit: ");
int limit = inp.nextInt();
if (start < 0 || start >= 12 || dest < 0 || dest >= 12 || limit < 0) {
System.out.println("Invalid input!");
System.exit(0);
}
traGraph.DLS(start, dest, limit);
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
