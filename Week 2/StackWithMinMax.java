/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Stacks and Queues
 *
 * Question 2: Stack with max
 *
 * Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation. Assume the elements
 * are reals numbers so that you can compare them.
 */

    import java.util.Stack;
    
    public class StackWithMinMax extends Stack<Float> {
    
        private Stack<Float> maxStack;
        private Stack<Float> minStack;
        
        public StackWithMinMax(){
            maxStack = new Stack<Float>();
            minStack = new Stack<Float>();
        }
        
        public int size() {
            return super.size();}
        
        public boolean isEmpty() {
            return size() == 0;}
    
        public void pushMain(Float item){
            if (super.isEmpty()){
                super.push(item);
                maxStack.push(item);
                minStack.push(item);
            } else {
                super.push(item);
                
            }}
    
         public float popMain(){
            return super.pop();}
        
         public int max(){
             return maxStack.peek();}
        
        public int min(){
             return minStack.peek();}
            
        public static void main(String[] args){
            StackWithMinMax StackMinMax = new StackWithMinMax();
            System.out.println(StackMinMax.isEmpty());
    }
        
    }
    
