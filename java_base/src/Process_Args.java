/*
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
*/

package src;

public class Process_Args 

{
	
	public static  boolean Args_INIT(String [] args)
	{
		boolean CLI; 
		CLI=true;
		
		if(args.length>0)
		{
			
			if(args[0].equals("gui"))
			{
				CLI=false;
			}
			else
			{
				CLI=true;
			}

		}
		return CLI;
		
	}
	
	public static void Args_Process_Rest(String [] args)
	{
		Integer i;
		
		if(args.length>0)
		{
			for (i=0; i<args.length; i++)
			{
				if (args[i].equals("state"))
				{
					Get_State(i, args);
				}
			}
		}
	}
	
	protected static void Get_State(Integer state_pos, String [] args)
	{
		boolean valid_input;
		
		valid_input=false;
		
		if(args[state_pos+1].equals("-body") && args[state_pos+3].equals("-obs") && 
				args[state_pos+5].equals("-time"))
		{
		
			String body, obs, time;
			body=args[state_pos+2];
			obs=args[state_pos+4];
			time=args[state_pos+6];
			
			
			Position_Funcs.Get_PositionVector_WRT_Observer(body, obs, time);
		}
		
	}
	
	//protected void Read_Rest_Args


}
