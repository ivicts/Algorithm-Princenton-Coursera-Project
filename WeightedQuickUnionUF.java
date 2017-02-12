public class WeightedQuickUnionUF{
   private int id[]; 
   private int sz[];
   private int count;
       
   public WeightedQuickUnionUF (int N)
   {
       count = N;
       id = new int[N];
       sz = new int[N];
       for (int i=0;i<N;i++){
           id[i] = i;
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
        
        count--;
    }
    
    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
        uf.union(0, 2);
        uf.union(8, 4);
        System.out.println(uf.root(4) == 8);
        uf.union(0, 4);
        System.out.println(uf.root(4) == 0);
        System.out.println(uf.root(8) == 0);
        uf.union(0, 6);
        System.out.println(uf.root(6) == 0);
        uf.union(1, 9);
        uf.union(1, 2);
        System.out.println(uf.root(2) == 0);
    }
 }