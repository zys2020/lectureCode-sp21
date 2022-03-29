package lec14_disjoint_sets;

public class UnionFind {
    private int[] parent;
    private int[] treeSize;

    /**
     * Creates a UnionFind data structure holding n vertices.
     * Initially, all vertices are in disjoint sets.
     */
    public UnionFind(int n) {
        this.parent = new int[n];
        this.treeSize = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            treeSize[i] = 0;
        }
    }

    /**
     * Throws an exception if v1 is not a valid index.
     */
    private void validate(int vertex) throws Exception {
        if (vertex < 0 || vertex > parent.length) {
            throw new Exception("Invalid index");
        }
    }

    /**
     * Returns the size of the set v1 belongs to.
     */
    public int sizeOf(int v1) {
        return this.treeSize[parent(v1)];
    }

    /**
     * Returns the parent of v1. If v1 is the root of a tree, returns the
     * negative size of the tree for which v1 is the root.
     */
    public int parent(int v1) {
        if (parent[v1] < 0) {
            return -treeSize[v1];
        }
        return parent[v1];
    }

    /**
     * Returns true if nodes v1 and v2 are connected.
     */
    public boolean connected(int v1, int v2) {
        return parent(v1) == parent(v2);
    }

    /**
     * Connects two elements v1 and v2 together. v1 and v2 can be any valid
     * elements, and a union-by-size heuristic is used. If the sizes of the sets
     * are equal, tie break by connecting v1's root to v2's root. Unioning a
     * vertex with itself or vertices that are already connected should not
     * change the sets but may alter the internal structure of the data.
     */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        int s, l;
        if (treeSize[i] > treeSize[j]) {
            s = j;
            l = i;
        } else {
            s = i;
            l = j;
        }
        parent[l] = s;
        treeSize[s] += 1;
        int v;
        if (l == i) {
            v = v1;
        } else {
            v = v2;
        }
        while (v != s) {
            parent[v] = s;
            v = parent(v);
        }
    }

    /**
     * Returns the root of the set V belongs to.
     * Path-compression is employed allowing for fast search-time.
     */
    public int find(int vertex) {
        int p = vertex;
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }

    public static void main(String[] args) {
        UnionFind union = new UnionFind(10);
        union.union(1, 2);
        union.union(3, 4);
        union.union(5, 9);
        union.union(1, 9);
        union.connected(2, 9);
    }

}
