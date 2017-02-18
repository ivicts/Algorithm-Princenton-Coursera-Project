import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // grid size, open sites, connected sites
    // Index 1 to n*n , not 0 to n-1
    private int n;
    private boolean[][] grid;
    private int opened;
    private WeightedQuickUnionUF openSitesPercolates;
    private WeightedQuickUnionUF openSitesFull;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new boolean[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = false; // false = blocked
            }
        }
        opened = 0;
        openSitesPercolates = new WeightedQuickUnionUF(n * n + 2); //plus virtual nodes
        openSitesFull = new WeightedQuickUnionUF(n * n + 1); //1 Virtual nodes
        for (int i = 1; i <= n; i++) {
            openSitesPercolates.union(0, i); // 0 is virtual top nodes
            openSitesPercolates.union(n * n + 1, n * n + 1 - i); // n*n+1 is virtual bottom nodes
            openSitesFull.union(0, i); // only virtual top nodes
        }
        // virtual_nodes = new WeightedQuickUnionUF(2*n+2);
    }
    public void open(int row, int col) { // open site (row, col) if it is not open already
        // row , col = 1 to n, but the grid index is 0,...,n-1
        isValid(row, col);
        if (!(grid[row - 1][col - 1])) {
            grid[row - 1][col - 1] = true;
            opened++;
        }
        //this index is 1 to n*n
        int index = xyTo1D(row, col);
        int indexLeft = xyTo1D(row, col - 1);
        int indexRight = xyTo1D(row, col + 1);
        int indexUp = xyTo1D(row - 1, col);
        int indexDown = xyTo1D(row + 1, col);

        //Left
        if ((index % n) != 1 && isOpen(row, col - 1)) {
            openSitesPercolates.union(index, indexLeft);
            openSitesFull.union(index, indexLeft);
        }
        //Right
        if ((index % n) != 0 && isOpen(row, col + 1)) {
            openSitesPercolates.union(index, indexRight);
            openSitesFull.union(index, indexRight);
        }
        //Up
        if (index > n && isOpen(row - 1, col)) {
            openSitesPercolates.union(index, indexUp);
            openSitesFull.union(index, indexUp);
        }
        //Down
        if (index <= n * (n - 1) && isOpen(row + 1, col)) {
            openSitesPercolates.union(index, indexDown);
            openSitesFull.union(index, indexDown);
        }

    }
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        isValid(row, col);
        return grid[row - 1][col - 1];
    }
    public boolean isFull(int row, int col) { // is site (row, col) full?
        isValid(row, col);
        int index = xyTo1D(row, col);
        return (isOpen(row, col) && openSitesFull.connected(0, index));
    }

    public int numberOfOpenSites() { // number of open sites
        return opened;
    }
        // does the system percolate?
    public boolean percolates() {
        if (n == 1) { //Corner Case
            return isOpen(1, 1);
        }
        return openSitesPercolates.connected(0, n * n + 1);
    }
        // mapping 2-coordinates to 1-coordinates
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }
        // is the index valid?
    private void isValid(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IndexOutOfBoundsException();
        }
    }
        // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        System.out.println(perc.percolates());
    }
}