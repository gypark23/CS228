abstract class ParentQubit
{
    private float[] values;
    private int numqubits;

    public float[] multiplyMatrix(float[][] m)
    {
        int dimension = this.getValues().length;
        float[] returnv = new float[dimension];

        for(int i = 0; i < dimension; i++)
        {
            float sum = 0;
            for(int j = 0; j < dimension; j++)
            {
                //System.out.println("i " + i + "j " + j);
                sum += m[i][j] * probToValue(this.getValue(j));
            }
            returnv[i] = valueToProb(sum);
        }

        return returnv;
    }

    public static float[][] multiplyMatrix(float[][] m, float[][] n)
    {
        int mrow = m.length;
        int mcol = m[0].length;
        int brow = n.length;
        int bcol = n[0].length;

        float[][] retv = new float[mrow][bcol];
        for (int i = 0; i < mrow; i++) {
            for (int j = 0; j < bcol; j++) {
                retv[i][j] = 0.00000f;
            }
        }

        for (int i = 0; i < mrow; i++) { // aRow
            for (int j = 0; j < bcol; j++) { // bColumn
                for (int k = 0; k < mcol; k++) { // aColumn
                    retv[i][j] += m[i][k] * n[k][j];
                }
            }
        }

        return retv;
    }

    public static float[][] tensorProduct(float a[][], float b[][])
    {
        int n = a.length, m = a[0].length;
		int p = b.length, q = b[0].length;

        float[][] returnv = new float[n * p][m * q];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                for(int k = 0; k < p; k++)
                {
                    for(int l = 0; l < q; l++)
                    {
                        //System.out.println(i + "" + j + "" + k + "" + l);
                        returnv[i * p + k][j * q + l] = a[i][j] * b[k][l];
                        //System.out.println(Arrays.deepToString(returnv));
                    }
                }
            }
        }

        return returnv;
    }



    // Constructor: initialize all bits to 0
    public ParentQubit(int numqubits)
    {
        this.numqubits = numqubits;
        int size = (int)Math.pow(2, numqubits);

        values = new float[size];
        for(int i = 1; i < size; i++)
        {
            values[i] = 0;
        }
        values[0] = 1;
    }

    public float probToValue(float v)
    {
        int sign = (v<0)? -1 : 1;
        return (float)Math.sqrt(Math.abs(v)) * sign;
    }

    public float valueToProb(float v)
    {
        int sign = (v<0)? -1 : 1;
        return (float)Math.pow(v, 2) * sign;
    }

    // The value in v is the probability of the ith value measuring that combination. 
    // Combinations are always ordered in increasing order from 0 to (2^numqubits)-1. 
    // Values are negative if the phase should be negative.
    public void setValue(float v, int i)
    {
        try
        {
            if(v < 0)
                values[i] = -(float)(Math.sqrt(Math.abs(v)));       
            else
                values[i] = (float)Math.sqrt(v);
        }
        catch(Exception e)
        {
            System.out.println("Error, out of bound set value");
        }
    }

    // v is the length equal to the number of qubit combinations (2^numqubits). 
    // The value in v[i] is the probability of measuring that combination. 
    // Combinations are always ordered in increasing order from 0 to (2^numqubits)-1. 
    // Values are negative if the phase should be negative.
    public void setValues(float[] v)
    {
        this.values = v;
        for(int i = 0; i < v.length; i++)
            this.setValue(v[i], i);
    }

    public float getValue(int i)
    {
        float value = 0;
        try
        {
            value = valueToProb(values[i]);
        }
        catch(Exception e)
        {
            System.out.println("Error, out of bound get value");
        }
        return value;
    }


    public float[] getValues()
    {
        float[] probVals = new float[this.values.length];

        for(int i = 0; i < this.values.length; i++)
        {
            probVals[i] = valueToProb(this.values[i]);
        }
        return values;
    }

    public void setPhase(int p, int i)
    {
        values[i] *= (p >= 0)? 1 : -1;
    }

    public void setPhases(int[] p)
    {
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
        return this.numqubits;
    }
    
    // this merges two sets of qubits and returns a new one that has 
    // a number of qubits that is the sum of the two. For example, we could 
    // merge two SingleQubit objects to become one DoubleQubit object. 
    // this is not implemented in ParentQubit but in the subclasses
    abstract ParentQubit mergeQubits(ParentQubit pq);

    // this prints out the state in bra-ket notation, like last week
    abstract String toBraKet();

    // apply a not gate to each qubit
    abstract void applyNotGate();

    // apply a not gate to the qubit in position qb, where numbering starts at 0
    abstract void applyNotGate(int qb); 

    // apply an H gate to each qubit
    abstract void applyHGate();

    // apply an H gate to the qubit in position qb, where numbering starts at 0
    abstract void applyHGate(int qb);

    // apply a swap gate between qubit1 and qubit2, where numbering starts at 0
    abstract void applySwapGate(int qubit1, int qubit2);
}