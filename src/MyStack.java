public class MyStack<T> {
    private Node<T> top;
    private int size;

    // Node class
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public MyStack() {
        top = null;
        size = 0;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Push an element onto the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop the top element from the stack
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Peek at the top element without removing it
    public T peek() {
        return isEmpty() ? null : top.data;
    }

    // Get the size of the stack
    public int size() {
        return size;
    }

    // Clone the stack
    public MyStack<T> cloneStack() {
        MyStack<T> clone = new MyStack<>();
        Node<T> current = top;
        MyStack<T> tempStack = new MyStack<>();

        // Push all elements into a temporary stack (reversing the order)
        while (current != null) {
            tempStack.push(current.data);
            current = current.next;
        }

        // Push elements from the temporary stack into the clone stack (restoring original order)
        while (!tempStack.isEmpty()) {
            clone.push(tempStack.pop());
        }

        return clone;
    }
}
