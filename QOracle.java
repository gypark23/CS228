import java.util.Arrays;

public class QOracle {

    private String code;
    private float[][] bernfinalop;
    private float[][] archifinalop;

    public QOracle() {

    }

    // receives a 3-bit code (number from 0-7). Based on that
    // 3-bit code, construct the oracle. For each 1 in the 3-bit code.
    // a C-NOT is connected to the response, where the qubit corresponding to
    // the 1 is the control, and the response is the target.
    public void setBernVaz(int code) 
    {
        if(code > 7)
        {
            System.out.println("setBernVaz over 8");
            return;
        }

        this.code = String.format("%3s", Integer.toBinaryString(code)).replace(' ', '0');
        
        float[][] identity = {{1, 0}, {0, 1}};
        bernfinalop = ParentQubit.tensorProduct(ParentQubit.tensorProduct(identity, identity), ParentQubit.tensorProduct(identity, identity));
        float[][] firstop = null;
        float[][] secondop = null;
        float[][] thirdop = null;

        if(this.code.charAt(0) == '1')
        {
            firstop = new float[][] {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        , {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
                
            bernfinalop = firstop;
        }

        if(this.code.charAt(1) == '1')
        {
            float[][] cnidot = {{1, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 0}};
            secondop = ParentQubit.tensorProduct(identity, cnidot);
            bernfinalop = ParentQubit.multiplyMatrix(bernfinalop, secondop);
            }

        if(this.code.charAt(2) == '1')
        {
            float[][] smallidentity = {{1, 0}, {0, 1}};
            float[][] cnot = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
            thirdop = ParentQubit.tensorProduct(ParentQubit.tensorProduct(smallidentity, smallidentity), cnot);
            bernfinalop = ParentQubit.multiplyMatrix(bernfinalop, thirdop);
        }
    }

    // NQubit is a 4-qubit input, with the first three qubits being the "guess" and 
    // the last qubit being the response. Apply the oracle. No return value is 
    // necessary because you modify the state of the input.
    public void probeBernVaz(NQubit nq) {

        float[] values = nq.multiplyMatrix(bernfinalop);
        nq.setValues(values);

    }

    // receives a set of 3-bit codes (number from 0-7). Based on that 
    // 3-bit code, construct the oracle. For each 3-bit code, the last 
    // bit of the input gets flipped. Think carefully about what the matrix looks 
    // like in the absence of any codes, and then think about how each individual 
    // code modifies that starting matrix. Once you have figured it out on paper, 
    // then you can work on how to implement that in code.
    public void setArchimedes(int[] codes) {
        float[][] identity = {{1, 0}, {0, 1}};
        archifinalop = ParentQubit.tensorProduct(ParentQubit.tensorProduct(identity, identity), ParentQubit.tensorProduct(identity, identity));
        if(codes == null)
            return;

        for(int i = 0; i < codes.length; i++)
        {
            int code = codes[i];
            archifinalop[2 * code][2 * code] = -1;
            archifinalop[2 * code][2 * code + 1] = 0;
            archifinalop[2 * code + 1][2 * code] = 0;
            archifinalop[2 * code + 1][2 * code + 1] = -1;
        }

        //System.out.println(Arrays.deepToString(archifinalop));
    }

    // NQubit is a 4-qubit input, with the first three qubits being the "guess" and
    // the last qubit being the response. Apply the oracle. No return value is
    // necessary because you modify the state of the input.
    public void probeArchimedes(NQubit nq) {
        float[] values = nq.multiplyMatrix(archifinalop);
        nq.setValues(values);
    }
}
