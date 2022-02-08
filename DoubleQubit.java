import java.text.DecimalFormat;


public class DoubleQubit extends ParentQubit
{

    public DoubleQubit() {        
        super(2);
        //TODO Auto-generated constructor stub
    }

    
    @Override
    ParentQubit mergeQubits(ParentQubit pq)
    {
        return (NQubit)this.mergeQubits(pq);
    }

    @Override
    public String toBraKet()
    {


        char firstphase = (this.getPhase(1) == 1)? '+' : '-';        
        char secondphase = (this.getPhase(2) == 1)? '+' : '-';        
        char thirdphase = (this.getPhase(3) == 1)? '+' : '-';  

        float a = Math.abs(probToValue(this.getValue(0)));
        float b = Math.abs(probToValue(this.getValue(1)));
        float c = Math.abs(probToValue(this.getValue(2)));
        float d = Math.abs(probToValue(this.getValue(3)));

        if(Math.abs(Math.abs(a) - 1.00) < 0.01)
        {
            return "|00>";
        }
        else if(Math.abs(Math.abs(b) - 1.00) < 0.01)
        {
            if(getPhase(1) == -1)
                return "- |01>";
            else
                return "|01>";
        }
        else if(Math.abs(Math.abs(c) - 1.00) < 0.01)
        {
            if(getPhase(2) == -1)
                return "- |10>";
            else
                return "|10>";
        }
        else if(Math.abs(Math.abs(d) - 1.00) < 0.01)
        {
            if(getPhase(3) == -1)
                return "- |11>";
            else
                return "|11>";
        }


        DecimalFormat df = new DecimalFormat("0.##");	

        String astring, bstring, cstring, dstring;
        boolean isFirst = true;

        if(a == 0)
        {
            astring = "";
        }
        else
        {
            astring = df.format(a) + "|00> ";
            isFirst = false;
        }
        if(b == 0)
        {
            bstring = "";
        }
        else
        {
            bstring = firstphase + " " + df.format(b) + "|01> ";
            if(isFirst)
            {
                bstring = df.format(b) + "|01> ";
            }
            isFirst = false;
        }
        if(c == 0)
        {
            cstring = "";
        }
        else
        {
            cstring = secondphase + " " + df.format(c) + "|10> ";
            if(isFirst)
            {
                cstring = df.format(c) + "|10> ";
            }
            isFirst = false;
        }
        if(d == 0)
        {
            dstring = "";
        }
        else
        {
            dstring = thirdphase + " " + df.format(d) + "|11> ";
            if(isFirst)
            {
                dstring = df.format(d) + "|11> ";
            }
        }

        return astring + bstring + cstring + dstring;
    }

    @Override
    public void applyNotGate()
    {
        float[][] notGate = {{0, 0, 0, 1}, {0, 0, 1, 0}, {0, 1, 0, 0}, {1, 0, 0, 0}};
        float[] afterNot = new float[4];

        for(int i = 0; i < 4; i++)
        {
            float sum = 0;
            for(int j = 0; j < 4; j++)
            {
                sum += notGate[i][j] * probToValue(this.getValue(j));
            }
            afterNot[i] = valueToProb(sum);
        }

        this.setValues(afterNot);
    }
    
    // apply a not gate to the qubit in position qb, where numbering starts at 0 
    // only do so if qb = 0
    public void applyNotGate(int qb)
    {
        float[][] notGate = new float[4][4];
        float[] afterNot = new float[4];
        if(qb == 0)
        {
            notGate = new float[][] {{0, 0, 1, 0}, {0, 0, 0, 1}, {1, 0, 0, 0}, {0, 1, 0, 0}};
        }
        else if(qb == 1)
        {
            notGate = new float[][] {{0, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
        }
        else
        {
            System.out.println("invalid qb");
            return;
        }
        
        for(int i = 0; i < 4; i++)
        {
            float sum = 0;
            for(int j = 0; j < 4; j++)
            {
                sum += notGate[i][j] * probToValue(this.getValue(j));
            }
            afterNot[i] = valueToProb(sum);
        }

        this.setValues(afterNot);
    }
    
    // apply an H gate to each qubit
    @Override
    public void applyHGate()
    {
        float val = 0.5f;
        float[][] hgate = {{val, val, val, val}, {val, -val, val, -val}, {val, val, -val, -val}, {val, -val, -val, val}};
        
        float[] afterHGate = new float[4]; 
        
        for(int i = 0; i < 4; i++)
        {
            float sum = 0;
            for(int j = 0; j < 4; j++)
            {
                sum += hgate[i][j] * probToValue(this.getValue(j));
            }
            afterHGate[i] = valueToProb(sum);
        }
        
        this.setValues(afterHGate);
    }
    
    // apply an H gate to the qubit in position qb, where numbering starts at 0 
    // only do so if qb = 0
    public void applyHGate(int qb)
    {
        float val = 1/(float)Math.sqrt(2);
        float[][] hGate = new float[4][4];
        float[] afterHGate = new float[4];
        if(qb == 0)
        {
            hGate = new float[][] {{val, 0, val, 0}, {0, val, 0, val}, {val, 0, -val, 0}, {0, val, 0, -val}};
        }
        else if(qb == 1)
        {
            hGate = new float[][] {{val, val, 0, 0}, {val, -val, 0, 0}, {0, 0, val, val}, {0, 0, val, -val}};
        }
        else
        {
            System.out.println("invalid qb");
            return;
        }

        
        for(int i = 0; i < 4; i++)
        {
            float sum = 0;
            for(int j = 0; j < 4; j++)
            {
                sum += hGate[i][j] * probToValue(this.getValue(j));
            }
            afterHGate[i] = valueToProb(sum);
        }

        this.setValues(afterHGate);
    }

    public void applySwapGate(int qubit1, int qubit2)
    {
        if(!((qubit1 == 0) && (qubit2 == 1) || (qubit1 == 1) && (qubit2 == 0)))
            return;

        float[][] swapGate = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};
        float[] afterSwap = new float[4];

        for(int i = 0; i < 4; i++)
        {
            float sum = 0;
            for(int j = 0; j < 4; j++)
            {
                sum += swapGate[i][j] * probToValue(this.getValue(j));
            }
            afterSwap[i] = valueToProb(sum);
        }

        this.setValues(afterSwap);
    }
    
}
