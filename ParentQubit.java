abstract class ParentQubit
{
    private float[] values;

    // Constructor: initialize all bits to 0
    public ParentQubit(int numqubits)
    {
        values = new float[numqubits * 2];
        for(int i = 0; i < numqubits * 2; i++)
        {
            values[i] = 0;
        }
    }
    // The value in v is the probability of the ith value measuring that combination. 
    // Combinations are always ordered in increasing order from 0 to (2^numqubits)-1. 
    // Values are negative if the phase should be negative.
    public void setValue(float v, int i)
    {
        if(v < 0)
            values[i] = -(float)(Math.sqrt(Math.abs(v)));       
        else
            values[i] = (float)Math.sqrt(v);
    }

    // v is the length equal to the number of qubit combinations (2^numqubits). 
    // The value in v[i] is the probability of measuring that combination. 
    // Combinations are always ordered in increasing order from 0 to (2^numqubits)-1. 
    // Values are negative if the phase should be negative.
    public void setValues(float[] v)
    {
        if(v.length != values.length)
        {
            System.out.println("v length incorrect");
            return;
        }

        for(int i = 0; i < v.length; i++)
            this.setValue(v[i], i);
    }

    public float getValue(int i)
    {
        return values[i];
    }


    public float[] getValues()
    {
        return values;
    }

    public void setPhase(int p, int i)
    {
        values[i] *= (p >= 0)? 1 : -1;
    }

    public void setPhases(int[] p)
    {
        if(p.length != values.length)
        {
            System.out.println("p length incorrect");
            return;
        }

        for(int i = 0; i < p.length; i++)
            this.setPhase(p[i], i);
    }
    public int getPhase(int i)
    {
        return (values[i] >= 0)? 1 : -1;
    }

    // this returns the number of qubits this object represents
    public int getNumQubits()
    {
        return values.length/2;
    }
    
    abstract ParentQubit mergeQubits(ParentQubit pq);

    abstract String toBraKet();

    abstract void applyNotGate();

    abstract void applyHGate();

    abstract void applyHGate(int qb);

    abstract void applySwapGate(int qubit1, int qubit2);
}