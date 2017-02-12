import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   // grid size, open sites, connected sites
   int gridSize;
   int boolean[][];
   WeightedQuickUnionUF connected_sites;
   public Percolation(int N)                // create n-by-n grid, with all sites blocked
   {
       this.N = N;
       data = new boolean[N][N];
       for (int i; i<N;i++){
           for (int j;
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   // First, it should validate the indices of the site that it receives. 
   // Second, it should somehow mark the site as open. 
   // Third, it should perform some sequence of WeightedQuickUnionUF operations that links the site in question to its open neighbors
   {}
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {}
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {}
   public     int numberOfOpenSites()       // number of open sites
   {}
   public boolean percolates()              // does the system percolate?
   {}
    private int xyTo1D (int row, int col)    // mapping 2-coordinates to 1-coordinates
   {
       return (row-1)*n+col;
   }
   private boolean isValid (int index)   // is the index valid?
   {}
   public static void main(String[] args)   // test client (optional)
   {
       
       System.out.println(xyTo1D(5,5)
   }
  
}