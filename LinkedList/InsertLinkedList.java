import java.util.*;

// Node class to represent a linked list node
class Node {
    public int data;
    public Node next;

    // Constructor with both data and next node
    public Node(int data1, Node next1) {
        data = data1;
        next = next1;
    }

    // Constructor with only data (assuming next is initially null)
    public Node(int data1) {
        data = data1;
        next = null;
    }
}

public class InsertLinkedList {

    // Function to print the linked list
    public static void printLL(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Function to insert a new node at the head of the linked list
    public static Node insertHead(Node head, int val) {
        Node temp = new Node(val, head);
        return temp;
    }

    // Function to insert at the end of the linked list
    public static Node insertLast(Node head, int val) {
        Node temp = new Node(val);

        // If list is empty
        if (head == null)
            return temp;

        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = temp;
        return head;
    }

    // Function to insert at a specific index (0-based)
    public static Node insertInBetween(Node head, int index, int val) {
        if (index == 0) return insertHead(head, val);

        Node temp = new Node(val);
        Node curr = head;

        for (int i = 0; i < index - 1 && curr != null; i++) {
            curr = curr.next;
        }

        // If index is out of bounds, return original list
        if (curr == null) {
            System.out.println("Index out of bounds.");
            return head;
        }

        temp.next = curr.next;
        curr.next = temp;
        return head;
    }

    public static void main(String[] args) {
        // Sample array and value for insertion
        List<Integer> arr = Arrays.asList(12, 8, 5, 7);
        int valHead = 100;
        int valLast = 200;
        int valMid = 150;
        int insertIndex = 2;

        // Creating a linked list with initial elements from the array
        Node head = new Node(arr.get(0));
        head.next = new Node(arr.get(1));
        head.next.next = new Node(arr.get(2));
        head.next.next.next = new Node(arr.get(3));

        System.out.print("Original List: ");
        printLL(head);

        // Insert at head
        head = insertHead(head, valHead);
        System.out.print("After insert at head: ");
        printLL(head);

        // Insert at end
        head = insertLast(head, valLast);
        System.out.print("After insert at end: ");
        printLL(head);

        // Insert in between
        head = insertInBetween(head, insertIndex, valMid);
        System.out.print("After insert at index " + insertIndex + ": ");
        printLL(head);
    }
}
