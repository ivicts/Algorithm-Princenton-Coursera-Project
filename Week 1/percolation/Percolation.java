import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   // grid size, open sites, connected sites
   // Index 1 to N*N , not 0 to N-1
   private int N;
   private boolean grid[][];
   private int count;
   public WeightedQuickUnionUF open_sites_percolates;
   public WeightedQuickUnionUF virtual_nodes;
   // create n-by-n grid, with all sites blocked
   public Percolation(int N) {
       this.N = N;
       if (N<=0){
            throw new IllegalArgumentException("n must be greater than 0");
       }
       
       grid = new boolean[N][N];
       for (int i=1; i<N;i++){
           for (int j=1; j<N;j++){
               grid[i][j] = false; // false = blocked
           }
       }
       count = 0;
       open_sites_percolates = new WeightedQuickUnionUF(N*N+2); //plus virtual nodes
       for (int i=1;i<=N;i++){
           open_sites_percolates.union(0,i);         // 0 is virtual top nodes
           open_sites_percolates.union(N*N+1,N*N-i); // N*N+1 is virtual bottom nodes
       }
      // virtual_nodes = new WeightedQuickUnionUF(2*N+2);
   }
   public void open(int row, int col){    // open site (row, col) if it is not open already
       // row , col = 1 to N, but the grid index is 0,...,N-1
       isValid(row,col);
       if (grid[row-1][col-1] == false){
           grid[row-1][col-1] = true;
           count++;
       }
       //this index is 1 to N*N
       int index = xyTo1D(row,col);
       int index_left = xyTo1D(row,col-1);
       int index_right = xyTo1D(row,col+1);
       int index_up = xyTo1D(row-1,col);
       int index_down = xyTo1D(row+1,col);
       
       //Left
       if ((index % N) != 1 && isOpen(row,col-1)){
           open_sites_percolates.union(index,index_left);
       }      
       //Right
       if ((index % N) != 0 && isOpen(row,col+1)){
           open_sites_percolates.union(index,index_right);
       }
       //Up
       if (index> N && isOpen(row-1,col)){
           open_sites_percolates.union(index,index_up);
       }
       //Down
       if (index<= N*(N-1) && isOpen(row+1,col)){
           open_sites_percolates.union(index,index_down);
       }    
       
   } 
   public boolean isOpen(int row, int col){  // is site (row, col) open?
       isValid(row,col);
       return grid[row-1][col-1];
   }
   public boolean isFull(int row, int col){  // is site (row, col) full?
       isValid(row,col);
       int index = xyTo1D(row,col);
       return (isOpen(row,col) && open_sites_percolates.connected(0,index));
   }
   
   public int numberOfOpenSites(){       // number of open sites
       return count;
   }
   // does the system percolate?
   public boolean percolates(){            
       if (N ==1){                         //Corner Case
           return isOpen(1,1);
       }
       return open_sites_percolates.connected(0,N*N+1);
   }
   // mapping 2-coordinates to 1-coordinates
   private int xyTo1D (int row, int col){    
       return (row-1)*N+col;
   }
   // is the index valid?
   private void isValid (int row, int col)   {
       if (row < 1 || col< 1 || row > N || col> N) {
           throw new IndexOutOfBoundsException();
       }
   }
   // test client (optional)
   public static void main(String[] args)  {
       Percolation perc = new Percolation(10);
       perc.open(1,1);
       perc.open(1,2);
       System.out.println(perc.open_sites_percolates.connected(1,2));
       
   }
  
}