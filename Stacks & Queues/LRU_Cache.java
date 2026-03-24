import java.util.*;

/*
Problem: LRU Cache (Least Recently Used)

Design a cache with:
- get(key)
- put(key, value)

Eviction:
Remove the least recently used item.
*/
/*
-------------------------------------
Brute Force Approach
-------------------------------------
Idea:
- Use ArrayList to store keys in order of usage
- Use HashMap for key → value
- On get/put → update order manually

TC:
- get → O(n)
- put → O(n)

SC: O(n)
*/

class LRUCacheBrute {
    int capacity;
    Map<Integer, Integer> map;
    List<Integer> order;
    public LRUCacheBrute(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        order = new ArrayList<>();
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        // Move key to end (most recent)
        order.remove((Integer) key);
        order.add(key);
        return map.get(key);
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            order.remove((Integer) key);
        } else if (map.size() == capacity) {
            int lru = order.remove(0);
            map.remove(lru);
        }
        map.put(key, value);
        order.add(key);
    }
}
/*
-------------------------------------
Optimal Approach (HashMap + DLL)
-------------------------------------
Idea:
- HashMap → key → node
- Doubly Linked List → maintain order
    head → most recent
    tail → least recent

TC:
- get → O(1)
- put → O(1)

SC: O(n)
*/
class LRUCache {
    class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            val = v;
        }
    }
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head.next = tail;
        tail.prev = head;
    }

    // Add node right after head (most recent)
    private void addNode(Node node) {
        Node temp = head.next;

        node.next = temp;
        node.prev = head;

        head.next = node;
        temp.prev = node;
    }

    // Remove node from list
    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);

        // Move to front
        deleteNode(node);
        addNode(node);

        return node.val;
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            deleteNode(node);
            addNode(node);
        } else {
            if (map.size() == capacity) {
                // Remove LRU
                Node lru = tail.prev;
                deleteNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addNode(newNode);
            map.put(key, newNode);
        }
    }
}
class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // evicts 2
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4); // evicts 1
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}

/* Using stack logic 
get(key):
    temp stack
    found = false

    while stack not empty:
        pop element
        if element == key:
            found = true
        else:
            push to temp

    if found:
        push key to main stack

    restore temp → main

    return value */
