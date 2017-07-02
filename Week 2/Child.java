public class Child extends Parent {
        public Child(int c1, int d1){
        super(c1,d1);
    }

    public static void main(String[] args) {
        Parent pa = new Parent(1,2);
        pa.print();
        Child ch = new Child(5,6);
        ch.print();
    }

}