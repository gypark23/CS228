public class NQubit extends ParentQubit {

    private float[] multiplyMatrix(float[][] m)
    {
        int dimension = this.getValues().length;
        float[] returnv = new float[dimension];

        for(int i = 0; i < dimension; i++)
        {
            float sum = 0;
            for(int j = 0; j < dimension; j++)
            {
                sum += m[i][j] * probToValue(this.getValue(j));
            }
            returnv[i] = valueToProb(sum);
        }

        return returnv;
    }


    // Constructor: initialize all bits to 0
    public NQubit(int numqubits) {
        super(numqubits);
        //TODO Auto-generated constructor stub
    }

    // this merges two sets of qubits and returns a new one that has 
    // a number of qubits that is the sum of the two.       
    @Override
    ParentQubit mergeQubits(ParentQubit pq) {
        
        ParentQubit mergedq = new NQubit(this.getNumQubits() + pq.getNumQubits());
        float[] merged = new float[this.getNumQubits() * pq.getNumQubits()];
        for(int i = 0; i < this.getValues().length; i++)
        {
            for(int j = 0; j < pq.getValues().length; j++)
            {
                merged[i*pq.getValues().length + j] = this.getValue(i) * pq.getValue(j);
            }
        }
        mergedq.setValues(merged);
        return mergedq;
    }

    // this prints out the state in bra-ket notation, like last week
    @Override
    String toBraKet() {
        // TODO Auto-generated method stub
        return null;
    }

    // apply a not gate to every qubit
    @Override
    void applyNotGate() {
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
        this.setValues(afterNot);
        
    }
    
    // apply a not gate to the qubit in position qb, where numbering starts at 0
    @Override
    void applyNotGate(int qb) {
        // TODO Auto-generated method stub
        
    }

    // apply an H gate to each qubit
    @Override
    void applyHGate() {
        // TODO Auto-generated method stub
        
    }

    // apply an H gate to the qubit in position qb, where numbering starts at 0
    @Override
    void applyHGate(int qb) {
        // TODO Auto-generated method stub
        
    }

    // apply a swap gate between qubit1 and qubit2, where numbering starts at 0
    @Override
    void applySwapGate(int qubit1, int qubit2) {
        // TODO Auto-generated method stub
        
    }

    
}
