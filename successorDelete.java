/* Successor with delete. 
 * Given a set of N integers S={0,1,...,N?1} and a sequence of requests of the following form:
 *  - Remove x from S
 *  - Find the successor of x: the smallest y in S such that y>=x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 */

public class successorDelete {
    private int N;
    private WeightedQuickUnionLargestUFL ufl;
    private boolean data[];
    
    public successorDelete(int N) {
       this.N = N; // To make class variable so that it can be used by another method.
        data = new boolean[N];
        for(int i=0;i<N;i++){
            data[i] = true;
        }
        ufl = new WeightedQuickUnionLargestUFL(N);
       
    }
    
    public void remove(int x){
        data[x] = false;
        if (x>0 && !data[x-1]){
            ufl.union(x,x-1);
        }
        if (x < N-1 && !data[x+1]) {
            ufl.union(x,x+1);
        }             
    }
    
    public int successor (int x) {
        System.out.println(N);
        if (data[x]){
            return x;
        }
        
        int suc = ufl.find(x) + 1;
        if (suc >N-1){
            System.out.println(suc);
            System.out.println(N-1);
            System.out.println("There is no successor that can be found");
            return -1;
        } else {
            return suc;
        }
        
    }
    
    public static void main(String[] args) {
        successorDelete sd = new successorDelete(10);
        sd.remove(2);
        System.out.println(sd.successor(2) == 3);
        sd.remove(3);
        System.out.println(sd.successor(2) == 4);
        System.out.println(sd.successor(8) == 8);
        sd.remove(8);
        System.out.println(sd.successor(8) == 9);
        sd.remove(9);
        System.out.println(sd.successor(8) == -1);
        sd.remove(5);
        sd.remove(4);
        System.out.println(sd.successor(3) == 6);
    }
    
}