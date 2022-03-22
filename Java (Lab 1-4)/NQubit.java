import java.text.DecimalFormat;
//import java.util.Arrays;
import java.util.Arrays;

public class NQubit extends ParentQubit {

    // Constructor: initialize all bits to 0
    public NQubit(int numqubits) {
        super(numqubits);
        //TODO Auto-generated constructor stub
    }

    // this merges two sets of qubits and returns a new one that has 
    // a number of qubits that is the sum of the two.       
    @Override
    ParentQubit mergeQubits(ParentQubit pq) {

        System.out.println(this.toBraKet());
        System.out.println(pq.toBraKet());
        
        ParentQubit mergedq = new NQubit(this.getNumQubits() + pq.getNumQubits());
        float[] merged = new float[mergedq.getValues().length];
        for(int i = 0; i < this.getValues().length; i++)
        {
            for(int j = 0; j < pq.getValues().length; j++)
            {
                merged[i*pq.getValues().length + j] = this.getValue(i) * pq.getValue(j);
            }
        }
        mergedq.setValues(merged);

        //System.out.println(mergedq.toBraKet());

        return mergedq;
    }

    // this prints out the state in bra-ket notation, like last week
    @Override
    String toBraKet() {

        String[] binary = new String[this.getValues().length];
        String formatting = "%" + Integer.toString(this.getNumQubits()) + "s";
        String returnv = "";
        DecimalFormat df = new DecimalFormat("0.##");	
        boolean firstRun = true;

        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = String.format(formatting, Integer.toBinaryString(i)).replace(' ', '0');
        }

        for(int i = 0; i < binary.length; i++)
        {
            //System.out.println("test: " + this.getValue(i));
            char phase = (this.getPhase(i) > 0)? '+' : '-';
            //value == 1
            if(Math.abs(Float.parseFloat(df.format(this.getValue(i)))) == 1.00)
            {
                if(this.getPhase(0) > 0)
                {
                    return returnv + "|" + binary[i] + "> ";
                }
                else
                {
                    return returnv + "- |" + binary[i] + "> ";
                }
            }
            //first run, value > 0.01
            else if(firstRun && (Math.round(this.getValue(i) * 100.0) / 100.0) != 0)
            {
                if(this.getPhase(i) > 0)
                {
                    returnv += df.format(Math.abs(probToValue(this.getValue(i)))) + "|" + binary[i] + "> ";
                }
                else
                {
                    returnv += "-" + df.format(Math.abs(probToValue(this.getValue(i)))) + "|" + binary[i] + "> ";
                }
                firstRun = false;
            }
            else if((Math.round(this.getValue(i) * 100.0) / 100.0) != 0)
            {
                if(firstRun)
                {
                    if(this.getPhase(0) > 0)
                        {
                            returnv += df.format(Math.abs(probToValue(this.getValue(i)))) + "|" + binary[i] + "> ";
                        }
                    else
                        {
                            returnv += phase + df.format(Math.abs(probToValue(this.getValue(i)))) + "|" + binary[i] + "> ";
                        }
                    firstRun = false;
                }
                else
                {
                    returnv += phase + " " + df.format(Math.abs(probToValue(this.getValue(i)))) + "|" + binary[i] + "> ";
                }
                //System.out.println("Real value: " + probToValue(this.getValue(i)));
            }
        }

        return returnv;
    }

    // apply a not gate to every qubit
    @Override
    void applyNotGate() {

    //System.out.println(this.toBraKet());
        int dimension = this.getValues().length;
        float[][] notGate = new float[dimension][dimension];
        for(int i = 0; i < dimension; i++)
        {
            for(int j = 0; j < dimension; j++)
            {
                if(i + j == dimension - 1)
                {
                    notGate[i][j] = 1;
                }
                else
                {
                    notGate[i][j] = 0;
                }
            }
        }
        float[] afterNot = multiplyMatrix(notGate);
        //System.out.println(Arrays.toString(afterNot));
        this.setValues(afterNot);
        //System.out.println(this.toBraKet());
    }
    
    // apply a not gate to the qubit in position qb, where numbering starts at 0
    @Override
    void applyNotGate(int qb) {
        
        float[][] notgate = {{0, 1}, {1, 0}};
        float[][] notgateall = {{0, 1}, {1, 0}};
        float[][] identity = {{1, 0}, {0, 1}};

        if(qb != 0)
            notgateall = new float[][] {{1, 0}, {0, 1}};

        for(int i = 1; i < this.getNumQubits(); i++)
        {
            if(qb == i)
            {
                notgateall = tensorProduct(notgateall, notgate);
            }
            else
            {
                notgateall = tensorProduct(notgateall, identity);
            }
        }

        float[] afterNot = multiplyMatrix(notgateall);

        this.setValues(afterNot);
    }

    // apply an H gate to each qubit
    @Override
    void applyHGate() {

        float val = 1/(float)Math.sqrt(2);
        float[][] hgate = {{val, val}, {val, -val}};
        float[][] hgateall = {{val, val}, {val, -val}};

        for(int i = 1; i < this.getNumQubits(); i++)
        {
            hgateall = tensorProduct(hgateall, hgate);
        }
        //System.out.println(Arrays.deepToString(hgateall));

        float[] afterHGate = multiplyMatrix(hgateall);
        this.setValues(afterHGate);
    }

    // apply an H gate to the qubit in position qb, where numbering starts at 0
    @Override
    void applyHGate(int qb) {

        float val = 1/(float)Math.sqrt(2);
        float[][] hgate = {{val, val}, {val, -val}};
        float[][] hgateall = {{val, val}, {val, -val}};
        float[][] identity = {{1, 0}, {0, 1}};

        if(qb != 0)
            hgateall = new float[][] {{1, 0}, {0, 1}};

        for(int i = 1; i < this.getNumQubits(); i++)
        {
            if(qb == i)
            {
                hgateall = tensorProduct(hgateall, hgate);
            }
            else
            {
                hgateall = tensorProduct(hgateall, identity);
            }
        }

        

        float[] afterHGate = multiplyMatrix(hgateall);
        this.setValues(afterHGate);
    }

    // apply a swap gate between qubit1 and qubit2, where numbering starts at 0
    @Override
    void applySwapGate(int qubit1, int qubit2) {

        String[] binary = new String[this.getValues().length];
        String[] swappedBinary = new String[this.getValues().length];
        String formatting = "%" + Integer.toString(this.getNumQubits()) + "s";
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = String.format(formatting, Integer.toBinaryString(i)).replace(' ', '0');
            //System.out.println(binary[i]);
        }
        for(int i = 0; i < binary.length; i++)
        {
            char ch[] = binary[i].toCharArray();
            char temp = ch[qubit1];
            ch[qubit1] = ch[qubit2];
            ch[qubit2] = temp;
            swappedBinary[i] = new String(ch);
            //System.out.println(swappedBinary[i]);
        }

        float[] vals = new float[this.getValues().length];

        for(int i = 0; i < binary.length; i++)
        {
            for(int j = 0; j < binary.length; j++)
            {
                if(binary[i].compareTo(swappedBinary[j]) == 0)
                {
                    //System.out.println("i j " + i + "" + j);
                    vals[j] = this.getValue(i);
                    break;
                }
            }
        }

        /*for(int i = 0; i < vals.length; i++)
        {
            System.out.println("i :" + i + "val : " + vals[i]);
        }
*/
        this.setValues(vals);

    }

    
}
