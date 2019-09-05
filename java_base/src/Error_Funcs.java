package src;

public class Error_Funcs {

	
	public static void ThrowError_Input(String Err_Message)
	{
		System.out.println("Input Error: "+Err_Message);
		System.exit(0);
	}
	

	
	public static void ThrowError_Fatal(String FunctionName, String Err_Message, StackTraceElement[] stack)
	{
		System.out.println("Error in "+FunctionName);
		System.out.println("Message: "+Err_Message);
		System.exit(0);
	}
	
	public static void ThrowError_Warning(String FunctionName, String Err_Message)
	{
		System.out.println("Warning! Culprit: "+FunctionName);
		System.out.println("Message: "+Err_Message);
	}
}

