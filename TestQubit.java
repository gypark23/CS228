public class TestQubit {

	public static int TestConstructor(Qubit start, Qubit expected, String[] args)
	{
		String val;

		if (args.length < 4) {
			System.out.println("Too few arguments for " +
					"TestsetValueFloat: " + args.length);
			System.out.println("Missing value input");
			System.out.println("Test FAILED");
			return 0;
		}

		val = args[3];
		Qubit construct = new Qubit(val);

		if (Qubit.compare(construct, expected) == 0) {
			System.out.println("Qubit Constructor(" + val +
					"): Success!");
			return 1;
		} else {
			System.out.println("Qubit Constructor(" + val +
					"): FAIL!");
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + start);
			return 0;
		}
	}

	/*
	 * TestsetValue
	 * receives user input for testing one call of setValue
	 * on a Qubit with a float input
	 *
	 * inputs:
	 * Qubit start - the starting state of a sprite to test on
	 * Qubit expected - the expected end state of the sprite
	 * string[] args - it contains {"float"}
	 * outputs:
	 * returns 1 for success, 0 for failure
	 * also prints results to the console
	 */
	public static int TestsetValueFloat(Qubit start, Qubit expected,
			String[] args) {
		float val;
		// make sure the degrees input is there
		if (args.length < 4) {
			System.out.println("Too few arguments for " +
					"TestsetValueFloat: " + args.length);
			System.out.println("Missing value input");
			System.out.println("Test FAILED");
			return 0;
		}

		// read the command-line argument
		val = Float.parseFloat(args[3]);
		// perform the operation
		start.setValue(val);

		// check the result and report
		if (Qubit.compare(start, expected) == 0) {
			System.out.println("Qubit setValue(" + val +
					"): Success!");
			return 1;
		} else {
			System.out.println("Qubit setValue(" + val +
					"): FAIL!");
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + start);
			return 0;
		}
	}

	public static int TestsetValueString(Qubit start, Qubit expected,
			String[] args) {
		String val;
		// make sure the degrees input is there
		if (args.length < 3) {
			System.out.println("Too few arguments for " +
					"TestsetValueFloat: " + args.length);
			System.out.println("Missing value input");
			System.out.println("Test FAILED");
			return 0;
		}
		// read the command-line argument
		val = args[3];
		// perform the operation
		start.setValue(val);

		// check the result and report
		if (Qubit.compare(start, expected) == 0) {
			System.out.println("Qubit setValue(" + val +
					"): Success!");
			return 1;
		} else {
			System.out.println("Qubit setValue(" + val +
					"): FAIL!");
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + start);
			return 0;
		}
	}

	public static int TestgetValue(Qubit start, Qubit expected, String[] args)
	{
		if (args.length < 4) {
			System.out.println("Too few arguments for " +
					"TestNot: " + args.length);
			System.out.println("Missing value input");
			System.out.println("Test FAILED");
			return 0;
		}
		
		float getval = start.getValue();
		float expectedval = Float.parseFloat(args[3]);

		if (Qubit.compare(start, expected) == 0 && getval == expectedval) {
			System.out.println("Qubit getVal(): Success!");
			return 1;
		} else {
			System.out.println("Qubit getVal(): FAIL!");
			System.out.println("Expected: " + expectedval);
			System.out.println("Actual: " + getval);
			return 0;
		}

	}

	public static int TestNot(Qubit start, Qubit expected, String[] args) {
		// make sure the degrees input is there
		if (args.length < 3) {
			System.out.println("Too few arguments for " +
					"TestNot: " + args.length);
			System.out.println("Missing value input");
			System.out.println("Test FAILED");
			return 0;
		}

		// read the command-line argument
		// perform the operation
		start.not();
		// check the result and report
		if (Qubit.compare(start, expected) == 0) {
			System.out.println("Qubit setNot(): Success!");
			return 1;
		} else {
			System.out.println("Qubit setValue(): FAIL!");
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + start);
			return 0;
		}
	}

	public static void main(String[] args) {
		int testNumber = 2;
		Qubit testQubit = new Qubit();
		Qubit expectedQubit = new Qubit();
		// make sure there are enough arguments
		if (args.length < 3) {
			System.out.println("Too few arguments: " + args.length);
			System.out.println("Usage: TestQubit.exe start_state "
					+ "expected_end_state testNumber inputs "
					+ "expected_ret_val");
			System.exit(0);
		}

		// get the starting state, ending state, and test number
		testQubit.setValue(Float.parseFloat(args[0]));
		expectedQubit.setValue(Float.parseFloat(args[1]));
		testNumber = Integer.parseInt(args[2]);

		// use the testnumber to choose a test function
		switch (testNumber) {
			case (0):
				TestConstructor(testQubit, expectedQubit, args);
				break;
			case (1):
				TestsetValueFloat(testQubit, expectedQubit, args);
				break;
			case (2):
				TestsetValueString(testQubit, expectedQubit, args);
				break;
			case (3):
				TestgetValue(testQubit, expectedQubit, args);
				break;
			case (4):
				TestNot(testQubit, expectedQubit, args);
				break;
			default:
				System.out.println("Test " + testNumber + " not supported");
		}
	}
}
