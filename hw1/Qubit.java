
public class Qubit{
	private float value; // either 0 (white) or 1 (black)

	/* Default Constructor
	 * Constructor without input arguments
	 * Initialize value to white or |0>
	 */
	public Qubit(){
		value = 0;
	}

	/* Constructor with input arguments
	 * Initialize value to inputted value
	 */
	public Qubit(float v)
	{
		this.value = v;
	}

	/* Constructor with input arguments
	 * allow other ways of specifying the starting value
	 * initialize: "White" is false, "Black" is true
	 */
	public Qubit(String v)
	{
		/* TODO - fill in this code */
		System.out.println("This method not yet implemented");
	}


	/* These are standard "setters" and "getters" except that we
	 * are supporting two types for the setter. Fill these in.
	 */
	public void setValue(float v)
	{  
		/* TODO - fill in this code */
		System.out.println("This method not yet implemented");
	}

	public void setValue(String v)
	{  
		/* TODO - fill in this code */
		System.out.println("This method not yet implemented");
	}

	public float getValue()
	{  
		/* TODO - fill in this code */
		System.out.println("This method not yet implemented");
		return -1;
	}

	/* not
	 * Perform a not gate on the qubit
	 * In week 1, this is only required to flip between 0 and 1
	 * Implement this without a conditional - figure out a 
	 * mathematical calculation that will work for either 0 or 1
	 */
	public void not()
	{
		/* TODO - fill in this code */
		System.out.println("This method not yet implemented");
			
	}

	/* These are methods we implement so that we can use Qubit with
	 * standard operations - like System.out.println and comparison 
	 * These are critical for grading, so don't change them!!!
	 */
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


