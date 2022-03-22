public class TestQubits {
        public static void main(String[] args)
    {
        System.out.println("Single Constructor");
        NQubit single = new NQubit(1);
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
        NQubit doubleq = new NQubit(2);
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

        System.out.println("Quad Constructor");
        NQubit quadq = new NQubit(4);
        System.out.println(quadq.toBraKet());
        System.out.println("mergeQubits");
        merged = doubleq.mergeQubits(quadq);
        //System.out.println(merged.toBraKet());
        System.out.println("notGate");
        doubleq.applyNotGate();
        System.out.println(quadq.toBraKet());
        System.out.println("notGate 0");
        doubleq.applyNotGate(0);
        System.out.println(quadq.toBraKet());
        System.out.println("HGate");
        doubleq.applyHGate();
        System.out.println(quadq.toBraKet());
        System.out.println("HGate 0");
        doubleq.applyHGate(0);
        System.out.println(quadq.toBraKet());
        System.out.println("SwapGate");
        doubleq.applySwapGate(0, 1);
        System.out.println(quadq.toBraKet());

        System.out.println("Same Entangle");
        DoubleQubit entang = new DoubleQubit();
        System.out.println(quadq.toBraKet());
        QOracle en = new QOracle();
        QCircuit.sameEntangle(entang);

        NQubit bernaz = new NQubit(4);
        float[] init = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bernaz.setValues(init);
        System.out.println("Before Bern Vaz");
        System.out.println(bernaz.toBraKet());
        QOracle bo = new QOracle();
        bo.setBernVaz(2);
        QCircuit.bernvaz(bernaz, bo);
        System.out.println("After Bern Vaz");
        System.out.println(bernaz.toBraKet());
        NQubit archi = new NQubit(4);
        archi.setValues(init);
        System.out.println("Before Archi");
        System.out.println(archi.toBraKet());
        QOracle ao = new QOracle();
        int[] empty = new int[] { 1, 2, 3, 4 };
        ao.setArchimedes(empty);
        System.out.println("After Archi");
        QCircuit.archimedes(archi, ao);
        System.out.println(archi.toBraKet());


    }
}
