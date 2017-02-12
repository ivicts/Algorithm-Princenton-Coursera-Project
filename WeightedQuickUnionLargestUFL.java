public class WeightedQuickUnionLargestUFL{
   private int id[]; 
   private int sz[];
   private int largest[];
   private int count;
       
   public WeightedQuickUnionLargestUFL (int N)
   {
       count = N;
       id = new int[N];
       sz = new int[N];
       largest = new int[N];
       for (int i=0;i<N;i++){
           id[i] = i;
           largest[i] = i;
           sz[i] = 1;
       }
   }
       
    public int count ()
    { return count; }
    
    private int root(int p)
    {
        while (id[p]!=p) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }
    
    public int find(int p)
    {
        return largest[root(p)];
    }
    
    public boolean connected (int p, int q)
    { return root(p) == root(q); }
    
    public void union(int p, int q)
    {
        int rootp = root(p);
        int rootq = root(q);
        
        if (rootp == rootq){
            return;
        }
        
        if (sz[rootp] < sz[rootq])
        {
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];
            
        } else {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];;
        }
        
        if (largest[rootp] > largest[rootq])
        {
            largest[rootq] = largest[rootp];
        } else {
            largest[rootp] = largest[rootq];
        }
        
        
        count--;
    }
    
    public static void main(String[] args) {
        WeightedQuickUnionLargestUFL ufl = new WeightedQuickUnionLargestUFL(10);
        ufl.union(0, 2);
        ufl.union(8, 4);
        System.out.println(ufl.find(0) == 2);
        System.out.println(ufl.find(4) == 8);
        ufl.union(0, 4);
        System.out.println(ufl.find(0) == 8);
        System.out.println(ufl.find(2));
        System.out.println(ufl.root(2));
        System.out.println(ufl.find(0));
        System.out.println(ufl.find(2) == 8);
        ufl.union(0, 6);
        System.out.println(ufl.find(6) == 8);
        ufl.union(1, 9);
        ufl.union(1, 2);
        System.out.println(ufl.find(4) == 9);
    }
 }