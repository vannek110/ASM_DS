import java.util.*;

class Graph {
    private final Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Method to add edges to the graph
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.get(source).add(new Edge(destination, weight));
        // If the graph is undirected, uncomment the line below
        // addEdge(destination, source, weight);
    }

    // Dijkstra's Algorithm implementation
    public Map<Integer, Integer> dijkstra(int source) {
        // Initialize distances and priority queue
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        // Set all distances to infinity initially
        for (Integer vertex : adjacencyList.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(source, 0); // Distance from source to itself is 0
        priorityQueue.add(new Node(source, 0)); // Add the source to the priority queue

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll(); // Get the node with the smallest distance
            int currentVertex = currentNode.vertex;

            // Process each neighbor of the current node
            for (Edge edge : adjacencyList.getOrDefault(currentVertex, new ArrayList<>())) {
                int neighbor = edge.destination;
                int newDistance = distance.get(currentVertex) + edge.weight;

                // Check if the neighbor is already in the distance map
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, Integer.MAX_VALUE); // Initialize distance if it doesn't exist
                }

                // Only consider this new path if it's better
                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    priorityQueue.add(new Node(neighbor, newDistance)); // Update the priority queue
                }
            }
        }

        return distance; // Return the map of shortest distances from the source
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

    // Inner class to represent nodes in the priority queue
    private static class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        // Create a sample graph
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 1);
        graph.addEdge(4, 5, 3);

        int source = 1; // Starting vertex
        Map<Integer, Integer> distances = graph.dijkstra(source);

        // Output the shortest distances from the source
        System.out.println("Shortest distances from node " + source + ":");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("To node " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
