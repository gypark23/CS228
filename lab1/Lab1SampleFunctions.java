
public class Lab1SampleFunctions{

	/**** up here are example functions ****/

	/* print_stuff: prints several variables of different types
	 * purpose is to illustrate printf and the types C provides 
	 * void means it doesn't return a value - it does something instead
	 * inputs:
	 *    int ival - integer value that will be printed out as an integer
	 *    float fval - floating-point value that will be printed out as a float
	 *    char cval - char value that will be printed out as a character
	 *    String sval - String that will be printed out
	 */
	public void printStuff(int ival, float fval, char cval, String sval)
	{
		System.out.println("You can use + operator on Strings to print"
			+ " multiple types: " + ival + ", " + fval + ", "
			+ cval + ", " + sval);
	}

	/* ReturnAValue: purpose is to show how to declare values,
	 * calculate a result, and return that result to the caller 
	 * inputs:
	 * 	int ival - integer value we will multiply by 5
	 * outputs:
	 *      int result - the original input multiplied by 5
	 */
	public int returnAValue(int ival)
	{
		int result = ival*5;
		return result;
	}

	/* fact : compute Factorial of given number 
	 * This illustrates how to write recursive code in C 
	 * inputs:
	 * 	int - we will calculate n!
	 * outputs:
	 * 	int - n!
	 */
	public int factorial(int n)
	{
	  // base case
	  if (n==0) 
	  {
	    return 1;
	  } 
	  else 
	  {
	    // call the recursive case
	    int smaller_result = factorial(n-1);
	    // modify the result from recursive case and return our result
	    return n * smaller_result;
	  }
	}	

	/* add_ages: compute the sum of the ages of two Puppy objects
	 * inputs:
	 *   Puppy p1 - the first puppy
	 *   Puppy p2 - the second puppy
	 * outputs:
	 *   int result: the sum of the ages of p1 and p2
	 */
	public int sumAges(Puppy p1, Puppy p2)
	{
		int a1 = p1.getAge();
		int a2 = p2.getAge();
		return a1 + a2;
	}

	/***** TODO: Below here is your assignment *****/

	/* dogYears: compute the dog's age in "dog years" - multipled by 7
	 * inputs:
	 *   Puppy p1 - the first puppy
	 * outputs:
	 *   int result: the product of p1's age times 7
	 */
	public int dogYears(Puppy p1)
	{
		// TODO: Remove WriteLine and fill in proper code
		int result;
		result = p1.getAge() * 7;
		return result;
	}

} // end of class Lab1SampleFunctions
