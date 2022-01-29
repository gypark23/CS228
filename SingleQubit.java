import java.text.DecimalFormat;

public class SingleQubit extends ParentQubit
{
    /*
    public SingleQubit()
    {
        this.ParentQubit();
    }
    */

    @Override
    ParentQubit mergeQubits(ParentQubit pq)
    {

        if(pq.getNumQubits() > 1)
        {
            System.out.println("pq not single qubit");
            return null;
        }
        
        values
    }

    @Override
    public String toBraKet()
    {
		if(this.getValue(0) == 0)
		{
			if (this.getPhase(1) == -1)
				return "- 1>";
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

    public void applyNotGate(int qb)
    {

    }
    
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
                afterHGate[i] = sum;
            }
        }

        this.setValues(afterHGate);
    }

    public void applyHGate(int qb)
    {

    }

    public void applySwapGate(int qubit1, int qubit2)
    {
        return;
    }
}