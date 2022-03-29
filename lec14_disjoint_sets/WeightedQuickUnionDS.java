package lec14_disjoint_sets;

public class WeightedQuickUnionDS implements DisjointSets {
    private int[] parent;
    private int[] treeSize;

    public WeightedQuickUnionDS(int n) {
        this.parent = new int[n];
        this.treeSize = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            treeSize[i] = 0;
        }

    }

    /**
     * Return the parent node of node p.
     */
    private int find(int p) {
        int r = p;
        while (parent[r] >= 0) {
            r = parent[r];
        }
        return r;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * always link root of smaller tree to larger tree.
     */
    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (treeSize[i] > treeSize[j]) {
            parent[i] = j;
            treeSize[j] += 1;
        } else {
            parent[j] = i;
            treeSize[i] += 1;
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionDS union = new WeightedQuickUnionDS(10);
        union.connect(1, 2);
        union.connect(3, 4);
        union.connect(5, 9);
        union.connect(1, 9);
        union.isConnected(2, 9);
    }
}
