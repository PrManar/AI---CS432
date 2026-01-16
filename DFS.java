import java.util.*;
class TRA {
int N; // number of vertices
boolean[][] G; // adjacency matrix
TRA(int size) {
N = size;
setupGraph();
}
void setupGraph() {
G = new boolean[N][N];
G[0][1] = G[1][0] = true;
//G[0][8] = G[8][0] = true; // trying to remove edges to see what will happen
//G[0][4] = G[4][0] = true;
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
// DFS using stack (LIFO):
boolean dfsStack(int start, int goal) {
boolean[] visited = new boolean[N];
int[] parent = new int[N]; // store parent of each node
Arrays.fill(parent, -1);
Stack<Integer> stack = new Stack<>();
stack.push(start);
while (!stack.isEmpty()) {
int node = stack.pop();
// System.out.println("Pop: " + retCity(node)); //this is only to print which node is poped
if (!visited[node]) { // to check if the current node is visited before or not, by doing this we prevent infinity 
loop
visited[node] = true;
if (node == goal) {
printPath(parent, start, goal);
return true;
}
for (int i = N - 1; i >= 0; i--) {
if (G[node][i] && !visited[i]) {
// System.out.println("Push: " + retCity(i)); //this is only to print which node is pushed
stack.push(i);
parent[i] = node;
}
}
}
}
return false;
}
void printPath(int[] parent, int start, int goal) {
List<Integer> path = new ArrayList<>();
for (int at = goal; at != -1; at = parent[at]) {
path.add(at);
}
Collections.reverse(path);
System.out.print("Path found: ");
for (int city : path) {
System.out.print(retCity(city) + "[" + city + "] -> "); // print the path cities with their numbers
}
System.out.println("\nGoal Reached!");
}
public static void main(String[] args) {
Scanner inp = new Scanner(System.in);
TRA traGraph = new TRA(12);
System.out.println("\nThe cities:");
for (int i = 0; i < 12; i++) {
System.out.println(retCity(i) + " city[" + i + "]");
}
System.out.print("\nEnter city number to start with: ");


int start = inp.nextInt();
System.out.print("Enter destination (the goal) city number: ");
int goal = inp.nextInt();
if (start < 0 || start >= 12 || goal < 0 || goal >= 12) {
System.out.println("Invalid input. Run the program again.");
System.exit(0);
}
System.out.println("\nBy using DFS (with stack) from " + retCity(start) + " to " + retCity(goal) + "...\n");
if (!traGraph.dfsStack(start, goal)) {
System.out.println("No path found.");
}
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
