import java.util.Arrays;

public class QCircuit {
    
    // no return value is necessary because you modify the input dq. 
    public static void sameEntangle(DoubleQubit dq)
    {
        float[][] cnot = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
        dq.applyHGate(0);
        dq.setValues(dq.multiplyMatrix(cnot));
    } 

    // You can assume that nq has 4 bits and has been initialized as expected 
    // for the bernvaz algorithm (3 white, 1 black). qo has already been initialized. 
    // implement the algorithm. 
    // no return value is necessary because you modify the input nq. 
    public static void bernvaz(NQubit nq, QOracle qo) 
    {
        nq.applyHGate();
        qo.probeBernVaz(nq);
        nq.applyHGate();
    }

    // You can assume that nq has 4 bits and has been initialized as expected 
    // for the archimedes algorithm (3 white, 1 black). qo has already been initialized. 
    // implement the algorithm. 
    // no return value is necessary because you modify the input nq. 
    public static void archimedes(NQubit nq, QOracle qo) 
    {
        nq.applyHGate();
        //System.out.println(nq.toBraKet());
        //System.out.println(Arrays.toString(nq.getValues()));
        qo.probeArchimedes(nq);
        //System.out.println(Arrays.toString(nq.getValues()));
        nq.applyHGate(0);
        nq.applyHGate(1);
        nq.applyHGate(2);
        //System.out.println(Arrays.toString(nq.getValues()));
    }


    public static void main(String[] args)
    {
        NQubit bernaz = new NQubit(4);
        float[] init = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bernaz.setValues(init);
        System.out.println(bernaz.toBraKet());
        QOracle bo = new QOracle();
        bo.setBernVaz(2);
        QCircuit.bernvaz(bernaz, bo);
        System.out.println(bernaz.toBraKet());
        NQubit archi = new NQubit(4);
        archi.setValues(init);
        System.out.println(archi.toBraKet());
        QOracle ao = new QOracle();
        int[] empty = new int[] { 1, 2, 3, 4 };
        ao.setArchimedes(empty);
        QCircuit.archimedes(archi, ao);
        System.out.println(archi.toBraKet());
    }
}
