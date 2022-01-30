import java.text.DecimalFormat;

public class SingleQubit extends ParentQubit
{

    public SingleQubit() {        
        super(1);
        //TODO Auto-generated constructor stub
    }

    @Override
    ParentQubit mergeQubits(ParentQubit pq)
    {

        if(pq.getNumQubits() > 1)
        {
            System.out.println("pq not single qubit");
            return null;
        }
        
        float a, b, c, d;
        a = (float)Math.sqrt(this.getValue(0));
        b = (float)Math.sqrt(this.getValue(1)) * this.getPhase(1);
        c = (float)Math.sqrt(pq.getValue(0));
        d = (float)Math.sqrt(pq.getValue(1)) * pq.getPhase(1);
        
        float[] values = {a*b, a*c, b*c, b*d};

        ParentQubit merged = new DoubleQubit();
        merged.setValues(values);

        return merged;

    }

    @Override
    public String toBraKet()
    {
		if(this.getValue(0) == 0)
		{
			if (this.getPhase(1) == -1)
				return "- |1>";
			else
				return "|1>";
		}
		if(this.getValue(1) == 0)
			return "|0>";


        char phase = (this.getPhase(1) == 1)? '+' : '-';        
        DecimalFormat df = new DecimalFormat("0.##");	
		return df.format(this.getValue(0)) + "|0> " + phase + " " + df.format(this.getValue(1)) + "|1>";
    }

    @Override
    public void applyNotGate()
    {
        float a, b;

        a = this.getValue(0);
        b = this.getValue(1);

        float[] appliedMatrix = {b, a * this.getPhase(1)};

        this.setValues(appliedMatrix);
    }
    
    // apply a not gate to the qubit in position qb, where numbering starts at 0 
    // only do so if qb = 0
    public void applyNotGate(int qb)
    {
        if(qb == 0)
            this.applyNotGate();
    }
    
    // apply an H gate to each qubit
    @Override
    public void applyHGate()
    {
        float val = 1/(float)Math.sqrt(2);
        float[][] hgate = {{val, val}, {val, -val}};
    
        float[] afterHGate = new float[2]; 
    
        for(int i = 0; i < 2; i++)
        {
            float sum = 0;
            for(int j = 0; j < 2; j++)
            {
                sum += hgate[i][j] * this.getValues()[i];
            }
            afterHGate[i] = sum;
        }

        this.setValues(afterHGate);
    }

    // apply an H gate to the qubit in position qb, where numbering starts at 0 
    // only do so if qb = 0
    public void applyHGate(int qb)
    {
        if(qb == 1)
            this.applyHGate();
    }

    public void applySwapGate(int qubit1, int qubit2)
    {
        return;
    }
}