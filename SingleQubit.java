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
        a = probToValue(this.getValue(0));
        b = probToValue(this.getValue(1));
        c = probToValue(pq.getValue(0));
        d = probToValue(pq.getValue(1));
        
        float[] values = {valueToProb(a*c), valueToProb(a*d), valueToProb(b*c), valueToProb(b*d)};

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
		return df.format(probToValue(this.getValue(0))) + "|0> " + phase + " " + df.format(Math.abs(probToValue(this.getValue(1)))) + "|1>";
    }

    @Override
    public void applyNotGate()
    {
        float a, b;

        a = this.getValue(0);
        b = this.getValue(1);

        float[] appliedMatrix = {b, a};

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

        afterHGate[0] = valueToProb(hgate[0][0] * probToValue(this.getValue(0)) + hgate[0][1] * probToValue(this.getValue(1)));
        afterHGate[1] = valueToProb(hgate[1][0] * probToValue(this.getValue(0)) + hgate[1][1] * probToValue(this.getValue(1)));

        this.setValues(afterHGate);
    }

    // apply an H gate to the qubit in position qb, where numbering starts at 0 
    // only do so if qb = 0
    public void applyHGate(int qb)
    {
        if(qb == 0)
            this.applyHGate();
    }

    public void applySwapGate(int qubit1, int qubit2)
    {
        return;
    }
}