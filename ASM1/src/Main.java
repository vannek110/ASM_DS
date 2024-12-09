public class Main {
    public static void main(String[] args) {
        LinkedList_Queue<Integer> queue = new LinkedList_Queue<>();

        // Enqueue operations (People joining the queue)
        queue.enqueue(1); // Person A
        queue.enqueue(2); // Person B
        queue.enqueue(3); // Person C
        queue.enqueue(4); // Person D

        System.out.println("Initial queue:");
        System.out.println("First element: " + queue.peek()); // Should be 1 (Person A)

        // Dequeue operations (People being served in order)
        queue.dequeue(); // Person A is served and leaves
        queue.dequeue(); // Person B is served and leaves

        System.out.println("After serving two people:");
        System.out.println("First element: " + queue.peek()); // Should be 3 (Person C)

        // Another person joins the queue
        queue.enqueue(5); // Person E

        System.out.println("After new person joins:");
        System.out.println("First element: " + queue.peek()); // Should still be 3 (Person C)

        // Dequeue remaining people
        queue.dequeue(); // Person C is served
        queue.dequeue(); // Person D is served
        queue.dequeue(); // Person E is served
    }
}