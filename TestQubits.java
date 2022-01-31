public class TestQubits {
        public static void main(String[] args)
    {
        System.out.println("Constructor");
        SingleQubit single = new SingleQubit();
        System.out.println(single.toBraKet());
        System.out.println("mergeQubits");
        ParentQubit merged = single.mergeQubits(single);
        System.out.println(merged.toBraKet());
    }
}
