import java.util.*;
/*
Problem: LFU Cache (Least Frequently Used)

Design a cache with:
- get(key) → return value
- put(key, value)

Eviction policy:
1. Remove least frequently used
2. If tie → remove least recently used

-------------------------------------
Brute Force Approach
-------------------------------------
Idea:
- Store all keys in a HashMap
- Track frequency in another HashMap
- On eviction:
    scan all keys → find min frequency
    if tie → remove oldest (use timestamp)

TC:
- get → O(1)
- put → O(n) (for eviction scan)

SC: O(n)
*/
class LFUCacheBrute {
    int capacity;
    int time = 0;
    Map<Integer, Integer> keyVal;
    Map<Integer, Integer> freq;
    Map<Integer, Integer> timestamp;
    public LFUCacheBrute(int capacity) {
        this.capacity = capacity;
        keyVal = new HashMap<>();
        freq = new HashMap<>();
        timestamp = new HashMap<>();
    }
    public int get(int key) {
        if (!keyVal.containsKey(key)) return -1;
        freq.put(key, freq.get(key) + 1);
        timestamp.put(key, ++time);
        return keyVal.get(key);
    }
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (keyVal.containsKey(key)) {
            keyVal.put(key, value);
            freq.put(key, freq.get(key) + 1);
            timestamp.put(key, ++time);
            return;
        }
        if (keyVal.size() == capacity) {
            int minFreq = Integer.MAX_VALUE;
            int oldestTime = Integer.MAX_VALUE;
            int keyToRemove = -1;
            for (int k : keyVal.keySet()) {
                int f = freq.get(k);
                int t = timestamp.get(k);
                if (f < minFreq || (f == minFreq && t < oldestTime)) {
                    minFreq = f;
                    oldestTime = t;
                    keyToRemove = k;
                }
            }
            keyVal.remove(keyToRemove);
            freq.remove(keyToRemove);
            timestamp.remove(keyToRemove);
        }
        keyVal.put(key, value);
        freq.put(key, 1);
        timestamp.put(key, ++time);
    }
/*
-------------------------------------
Optimal Approach (O(1))
-------------------------------------
Idea:
- key → Node map
- freq → Doubly Linked List map
- Maintain minFreq
- Each node stores (key, value, freq)
Operations:
- get → update frequency
- put → remove LFU (and LRU if tie)
TC:
- get → O(1)
- put → O(1)
SC: O(n)
*/
class LFUCache {
    class Node {
        int key, value, freq;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            value = v;
            freq = 1;
        }
    }
    class DLL {
        Node head, tail;
        int size;
        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        void addFront(Node node) {
            Node temp = head.next;
            node.next = temp;
            node.prev = head;
            head.next = node;
            temp.prev = node;
            size++;
        }
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        Node removeLRU() {
            if (size == 0) return null;
            Node lru = tail.prev;
            remove(lru);
            return lru;
        }
    }
    Map<Integer, Node> keyNode;
    Map<Integer, DLL> freqMap;
    int capacity;
    int minFreq;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyNode = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
    }
    public int get(int key) {
        if (!keyNode.containsKey(key)) return -1;
        Node node = keyNode.get(key);
        update(node);
        return node.value;
    }
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (keyNode.containsKey(key)) {
            Node node = keyNode.get(key);
            node.value = value;
            update(node);
            return;
        }
        if (keyNode.size() == capacity) {
            DLL list = freqMap.get(minFreq);
            Node lru = list.removeLRU();
            keyNode.remove(lru.key);
        }
        Node newNode = new Node(key, value);
        minFreq = 1;
        DLL list = freqMap.getOrDefault(1, new DLL());
        list.addFront(newNode);
        freqMap.put(1, list);
        keyNode.put(key, newNode);
    }
    private void update(Node node) {
        int freq = node.freq;
        DLL list = freqMap.get(freq);
        list.remove(node);
        if (freq == minFreq && list.size == 0) {
            minFreq++;
        }
        node.freq++;
        DLL newList = freqMap.getOrDefault(node.freq, new DLL());
        newList.addFront(node);
        freqMap.put(node.freq, newList);
    }
}
