package general;

public class FinalizeExample {
    public static void main(String[] args) {
        FinalizeExample fe = new FinalizeExample();
        fe = null;
        System.gc();
    }

    @Override
    public void finalize() {
        System.out.println("Finalized!");
    }
}
