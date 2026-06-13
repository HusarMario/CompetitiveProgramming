package Algorithms.DataStructures;

// Data structure used to efficiently maintain and merge disjoint (non-overlapping) set.
public class DisjointSetUnion {

    // Stores the parent of each node. A root node points to itself.
    private final int[] parent;

    // DSU initialization - every node starts in its own set.
    public DisjointSetUnion(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // Finds root of the set.
    public int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    // Merges sets containing two specified nodes.
    public void union(int firstNode, int secondNode) {
        int firstRoot = find(firstNode);
        int secondRoot = find(secondNode);

        if (firstRoot != secondRoot) {
            parent[secondRoot] = firstRoot;
        }
    }
}
