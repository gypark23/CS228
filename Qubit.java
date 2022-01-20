import java.text.DecimalFormat;

public class Qubit{
	private float value; // either 0 (white) or 1 (black)
	private int phase; // either 1 or -1

	/* Default Constructor
	 * Constructor without input arguments
	 * Initialize value to white or |0>
	 * 
	 * Initialize to zero, positive phase
	 */
	public Qubit()
	{
		this.value = 0;
		this.phase = 1;
	}

	/* Constructor with input arguments
	 * Initialize value to inputted value
	 * 
	 * check sign of float for phase 
	 * allow any number between -1 and 1, inclusive
	 */
	public Qubit(float v)
	{
		if(Math.abs(v) > 1)
		{
			System.out.println("error: float beyond range of -1 to 1");
		}
		else
		{
			this.value = Math.abs(v);
			this.phase = (v >= 0) ? 1 : -1;
		}
	}

	/* Constructor with input arguments
	 * allow other ways of specifying the starting value
	 * initialize: "White" is false, "Black" is true
	 * 
	 * set phase to positive
	 */
	public Qubit(String v)
	{

		if(v.equals("White"))
			this.value = 0;
		if(v.equals("Black"))
			this.value = 1;

		this.phase = 1;
	}


	/* These are standard "setters" and "getters" except that we
	 * are supporting two types for the setter. Fill these in.
	 * 
	 * inspect sign of float for phase
	 */
	public void setValue(float v)
	{  
		if(Math.abs(v) > 1)
		{
			System.out.println("error: float beyond range of -1 to 1");
		}
		else
		{
			this.value = Math.abs(v);
			this.phase = (v >= 0) ? 1 : -1;
		}
	}

	// set phase to positive
	public void setValue(String v)
	{  
		if(v.equals("White"))
			this.setValue(0);
		else if(v.equals("Black"))
			this.setValue(1);
		else
		{
			System.out.println("error: wrong string input");
			return;
		}
		this.phase = 1;
	}

	// return probability of measuring 1 multiplied by the phase
	public float getValue()
	{  
		return this.value * this.phase;
	}

	// assume -1 for negative phase, 1 for positive phase
	public void setPhase(int phase)
	{
		if(Math.abs(phase) != 1)
		{
			System.out.println("error: wrong value of phase");
			return;
		}
		this.phase = phase;
	}

	// return -1 for negative phase, 1 for positive phase
	public int getPhase()
	{
		return this.phase;
	}



	/* not
	 * Perform a not gate on the qubit
	 * In week 1, this is only required to flip between 0 and 1
	 * Implement this without a conditional - figure out a 
	 * mathematical calculation that will work for either 0 or 1
	 * 
	 * make sure your implementation works for superposition and phase 
	 * for now, we will assume that not does not affect the phase
	 */
	public void not()
	{
		float current = this.getValue();
		float flip = -1 * current + 1;

		this.setValue(flip);
	}

	/* These are methods we implement so that we can use Qubit with
	 * standard operations - like System.out.println and comparison 
	 * These are critical for grading, so don't change them!!!
	 */

	// support the following inputs: 0 with positive phase, 1 with positive phase, 
	// superposition of 50\%, superposition of 50\% with negative phase
	public void hgate()
	{

	}

	// make sure your implementation works for superposition and phase 
	// Phase is swapped just as the rest of the state is swapped.
	public void swap(Qubit q2)
	{
		float tempval = this.getValue();

		this.setValue(q2.getValue());

		q2.setValue(tempval);
	}

	// support only 0 and 1 inputs, positive phase 
	// assume that "this" is control, and q2 is the target.
	public void cnot(Qubit q2)
	{
		if(this.getValue() != 0 && this.getValue() != 1)
		{
			System.out.println("error: control qubit not 0 or 1 or not positive phase");
			return;
		}
		if(q2.getValue() != 0 && q2.getValue() != 1)
		{
			System.out.println("error: target qubit not 0 or 1 or not positive phase");
			return;
		}

		if(this.value == 1)
			q2.not();
	}

	// this performs measurement to determine whether the ``color'' is white or 
	// black, or the value is 0 or 1. This both outputs the measured value 
	// and sets the value in the Qubit to that result, collapsing any superposition 
	// that might have been present.
	public int measureValue()
	{
		this.setPhase(1);
		if(Math.random() <= this.value)
			return 1;
		else
			return 0;
	}

	public String toBraKet()
	{
		Float a = (float)Math.sqrt(1 - this.value);
		Float b = (float)Math.sqrt(this.value);

		if(a == 0)
			return "|1>";
		if(b == 0)
			return "|0>";

		DecimalFormat df = new DecimalFormat("0.##");
			
		return df.format(a) + "|0> + " + df.format(b) + "|1>";
	}

	public String toString()
	{
	       // we put the "" before value to make it a String.	
		return "" + value;
       	} 

	public static int compare (Object obj1, Object obj2)
	{
		// first cast to Qubits - we assume we're comparing Qubits
		Qubit q1 = (Qubit) obj1;
		Qubit q2 = (Qubit) obj2;

		// if they are equal within a certain precision
		// because they are floats, we must put in a fudge factor
		if (((q1.getValue() - q2.getValue()) < 0.01) &&
		    ((q2.getValue() - q1.getValue()) < 0.01) ) 
		{
			// obj1 == obj2
			return 0;
		}
		else if (q1.getValue() > q2.getValue())
		{
			// obj1 > obj2
			return 1;
		}
		else // if (q1.getValue() < q2.getValue())
		{
			// obj1 < obj2
			return -1;
		}
		   
	}

} // end of class


