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
					Process_State_Inputs(i, args);
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
	
	
	protected static void Process_State_Inputs(Integer state_pos, String [] args)
	{
		boolean valid_input;
		
		valid_input=false;
		
		if(args[state_pos+1].equals("-body") && args[state_pos+3].equals("-obs") && 
				args[state_pos+5].equals("-time"))
		{
		
			String body, obs, time;
			body=args[state_pos+2];
			obs=args[state_pos+4];
			time=args[state_pos+6]+" "+args[state_pos+7];
			
			boolean body_isint, obs_isint,timeparsed=false;
			Integer body_id, obs_id;
			String time_format;
			body_id=-1;
			obs_id=-1;
			
			try 
			{
				body_id=Integer.parseInt(body);
				body_isint=true;
			}
			catch (NumberFormatException e)
			{
				body_isint=false;
			}
			
			try 
			{
				obs_id=Integer.parseInt(obs);
				obs_isint=true;
			}
			catch (NumberFormatException e)
			{
				obs_isint=false;
			}
			
			
			
			boolean obs_present, body_present;
			obs_present=false;
			body_present=false;
			
			Integer [] result_obs = { 0,-1};
			Integer [] result_body = { 0,-1};
			
			
			
			if(body_isint && obs_isint)
			{
				body_present=Toolkit_Funcs.Body_Present(body_id);
				obs_present=Toolkit_Funcs.Body_Present(obs_id);
			}
			else
			{
				result_body=Toolkit_Funcs.Body_Present(body);
				result_obs=Toolkit_Funcs.Body_Present(obs);
				
				if(result_body[0]==1)
				{
					body_present=true;
				}
				
				if(result_obs[0]==1)
				{
					obs_present=true;
				}

			}
			
			
			if(!body_present&&body_isint || !obs_present&&obs_isint)
			{
				Error_Funcs.ThrowError_Input("Body "+ body_id.toString()+" is not present in the Ephemerides");
			}
			
			if(!body_present&&!body_isint || !obs_present&&!obs_isint)
			{
				Error_Funcs.ThrowError_Input("Body "+ body_id+" is not present in the Ephemerides");
			}
			
			if(body_present && obs_present)
			{
				Position_Funcs.Get_PositionVector_WRT_Observer(body, obs, time);
			}
			
			
			
			
		}
		
	}
	
	//protected void Read_Rest_Args


}
