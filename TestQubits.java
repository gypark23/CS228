public class TestQubits {
        public static void main(String[] args)
    {
        System.out.println("Single Constructor");
        SingleQubit single = new SingleQubit();
        System.out.println(single.toBraKet());
        System.out.println("mergeQubits");
        ParentQubit merged = single.mergeQubits(single);
        System.out.println(merged.toBraKet());
        System.out.println("notGate");
        single.applyNotGate();
        System.out.println(single.toBraKet());
        System.out.println("notGate 0");
        single.applyNotGate(0);
        System.out.println(single.toBraKet());
        System.out.println("HGate");
        single.applyHGate();
        System.out.println(single.toBraKet());
        System.out.println("HGate 0");
        single.applyHGate(0);
        System.out.println(single.toBraKet());
        System.out.println("SwapGate");
        single.applySwapGate(0, 1);
        System.out.println(single.toBraKet());

        System.out.println("SetValue");
        single.setValue(0, 1);
        System.out.println(single.toBraKet());
        System.out.println("GetValue");
        System.out.println(single.getValue(0));

        float val[] = {1, 0};
        System.out.println("SetValues");
        single.setValues(val);
        System.out.println(single.toBraKet());
        System.out.println("GetValues");
        System.out.println(single.getValues()[0] + single.getValues()[1]);

        System.out.println("Double Constructor");
        DoubleQubit doubleq = new DoubleQubit();
        System.out.println(doubleq.toBraKet());
        System.out.println("mergeQubits");
        merged = doubleq.mergeQubits(doubleq);
        //System.out.println(merged.toBraKet());
        System.out.println("notGate");
        doubleq.applyNotGate();
        System.out.println(doubleq.toBraKet());
        System.out.println("notGate 0");
        doubleq.applyNotGate(0);
        System.out.println(doubleq.toBraKet());
        System.out.println("HGate");
        doubleq.applyHGate();
        System.out.println(doubleq.toBraKet());
        System.out.println("HGate 0");
        doubleq.applyHGate(0);
        System.out.println(doubleq.toBraKet());
        System.out.println("SwapGate");
        doubleq.applySwapGate(0, 1);
        System.out.println(doubleq.toBraKet());

    }
}
