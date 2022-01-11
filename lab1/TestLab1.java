public class TestLab1
{
	/* TestprintStuff
	 * receives user input for testing one call of printStuff
	 * it first checks to make sure the input is well formed.
	 *
	 * inputs:
	 *  String[] args - it contains {"0", "int", "float", "char", "String"}
	 * outputs:
 	 *  no return value. Prints to the screen through function call
	 */
	public static void TestprintStuff(String[] args)
	{
		int i;
		float f;
		if (args.length < 5)	
		{
			System.out.println("Too few arguments for TestprintStuff: "
				+args.length);
			System.out.println("Usage: TestLab1.exe 0 int float char String");
			System.exit(0);
		}

			i = Integer.parseInt(args[1]);
			f = Float.parseFloat(args[2]);
		
		Lab1SampleFunctions lsf = new Lab1SampleFunctions();
		lsf.printStuff(i, f, args[3].charAt(0), args[4]);
	}

	/* TestreturnAValue
	 * receives user input for testing one call of returnAValue
	 * it first checks to make sure the input is well formed.
	 *
	 * inputs:
	 *  String[] args - it contains {"1", "int", "int"}
	 * outputs:
 	 *  int - returns 1 if success, 0 if failure
	 */
	public static int TestreturnAValue(String[] args)
	{
		int input, expectedOutput, output;
		if (args.length < 3)	
		{
			System.out.println("Too few arguments for TestreturnAValue: "
				+args.length);
			System.out.println("Usage: TestLab1.exe 1 input expectedOutput");
			System.exit(0);
		}

			input = Integer.parseInt(args[1]);
			expectedOutput = Integer.parseInt(args[2]);
		
		Lab1SampleFunctions lsf = new Lab1SampleFunctions();
		output = lsf.returnAValue(input);
		if (output == expectedOutput)
		{
			System.out.println("returnAValue("+input+") = "+
				output+" Success!");
			return 1;
		}
		else
		{
			System.out.println("returnAValue("+input+") = "+
				output+". Did not match expected output: "+
				expectedOutput+".  FAIL!");
			return 0;
		}
	}

	/* Testfactorial
	 * receives user input for testing one call of factorial
	 * it first checks to make sure the input is well formed.
	 *
	 * inputs:
	 *  String[] args - it contains {"2", "int", "int"}
	 * outputs:
 	 *  int - returns 1 if success, 0 if failure
	 */
	public static int Testfactorial(String[] args)
	{
		int input, expectedOutput, output;
		if (args.length < 3)	
		{
			System.out.println("Too few arguments for Testfactorial: "
				+args.length);
			System.out.println("Usage: TestLab1.exe 2 input expectedOutput");
			System.exit(0);
		}

			input = Integer.parseInt(args[1]);
			expectedOutput = Integer.parseInt(args[2]);
		
		Lab1SampleFunctions lsf = new Lab1SampleFunctions();
		output = lsf.factorial(input);
		if (output == expectedOutput)
		{
			System.out.println("factorial("+input+") = "+
				output+" Success!");
			return 1;
		}
		else
		{
			System.out.println("factorial("+input+") = "+
				output+". Did not match expected output: "+
				expectedOutput+".  FAIL!");
			return 0;
		}
	}

	/* TestsumAges
	 * receives user input for testing one call of factorial
	 * it first checks to make sure the input is well formed.
	 *
	 * inputs:
	 *  String[] args - it contains {"2", "int", "int", "int"}
	 *      these correspond to two ages and the expected outcome.
	 * outputs:
 	 *  int - returns 1 if success, 0 if failure
	 */
	public static int TestsumAges(String[] args)
	{
		int input1, input2, expectedOutput, output;
		if (args.length < 4)	
		{
			System.out.println("Too few arguments for Testfactorial: "
				+args.length);
			System.out.println("Usage: TestLab1.exe 3 input1 "+
				"input2 expectedOutput");
			System.exit(0);
		}

			input1 = Integer.parseInt(args[1]);
			input2 = Integer.parseInt(args[2]);
			expectedOutput = Integer.parseInt(args[3]);
		
		Lab1SampleFunctions lsf = new Lab1SampleFunctions();

		Puppy p1 = new Puppy(input1, "Puppy1");
		Puppy p2 = new Puppy(input2, "Puppy2");
		output = lsf.sumAges(p1, p2);
		if (output == expectedOutput)
		{
			System.out.println("sumAges("+input1+","+input2+
				") = "+ output+" Success!");
			return 1;
		}
		else
		{
			System.out.println("sumAges("+input1+","+input2+
				") = "+output+
				". Did not match expected output: "+
				expectedOutput+".  FAIL!");
			return 0;
		}
	}

	public static int TestdogYears(String[] args)
	{
		int input, expectedOutput, output;
		if (args.length < 3)	
		{
			System.out.println("Too few arguments for TestdogYears: "
				+args.length);
			System.out.println("Usage: TestLab1.exe 2 input expectedOutput");
			System.exit(0);
		}

		input = Integer.parseInt(args[1]);
		expectedOutput = Integer.parseInt(args[2]);
		
		Lab1SampleFunctions lsf = new Lab1SampleFunctions();
		Puppy pup = new Puppy();
		pup.setAge(input);
		output = lsf.dogYears(pup);

		if (output == expectedOutput)
		{
			System.out.println("dogYears("+input+") = "+
				output+" Success!");
			return 1;
		}
		else
		{
			System.out.println("dogYears("+input+") = "+
				output+". Did not match expected output: "+
				expectedOutput+".  FAIL!");
			return 0;
		}
	}


	public static void main(String[] args)
	{
		int testnumber = 2;
		if (args.length < 2)	
		{
			System.out.println("Too few arguments: "+args.length);
			System.out.println("Usage: TestLab1.exe testnumber argument(s)");
			System.exit(0);
		}

		testnumber = Integer.parseInt(args[0]);

		switch (testnumber)
		{
			case (0):
				TestprintStuff(args);
				break;
			case (1):
				TestreturnAValue(args);
				break;
			case (2):
				Testfactorial(args);
				break;
			case (3):
				TestsumAges(args);
				break;
			case (4):
				TestdogYears(args);
				break;
		}
	}
}

