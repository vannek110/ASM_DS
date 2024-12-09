import java.util.*;

class Graph1 {
    private final Map<Integer, List<Edge>> adjacencyList;

    public Graph1() {
        this.adjacencyList = new HashMap<>();
    }

    // Method to add edges to the graph
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight)); // For undirected graph
    }

    // Prim's Algorithm implementation
    public List<Edge> prim(int start) {
        List<Edge> mstEdges = new ArrayList<>();
        Set<Integer> includedVertices = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        // Add the initial edges to the priority queue
        includedVertices.add(start);
        priorityQueue.addAll(adjacencyList.get(start));

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int destination = edge.destination;

            // Only consider this edge if it connects to an unvisited vertex
            if (!includedVertices.contains(destination)) {
                includedVertices.add(destination);
                mstEdges.add(edge); // Include this edge in the MST

                // Add all edges from the newly added vertex to the priority queue
                priorityQueue.addAll(adjacencyList.get(destination));
            }
        }
        return mstEdges; // Return the list of edges in the MST
    }

    // Inner class to represent edges in the graph
    private static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Graph1 graph1 = new Graph1();
        // Create a sample graph
        graph1.addEdge(1, 2, 4);
        graph1.addEdge(1, 3, 1);
        graph1.addEdge(2, 3, 2);
        graph1.addEdge(2, 4, 5);
        graph1.addEdge(3, 4, 8);
        graph1.addEdge(3, 5, 10);
        graph1.addEdge(4, 5, 2);

        int start = 1; // Starting vertex
        List<Edge> mst = graph1.prim(start);

        // Output the edges in the Minimum Spanning Tree
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println("From node " + start + " to node " + edge.destination + " with weight " + edge.weight);
            start = edge.destination; // Move to the next starting vertex
        }
    }
}
