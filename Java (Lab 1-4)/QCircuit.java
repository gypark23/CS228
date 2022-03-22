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
        //System.out.println(Arrays.toString(nq.getValues()));
        nq.applyHGate();
        qo.probeArchimedes(nq);
        nq.applyHGate(0);
        nq.applyHGate(1);
        nq.applyHGate(2);
        //System.out.println(Arrays.toString(nq.getValues()));
    }
}
