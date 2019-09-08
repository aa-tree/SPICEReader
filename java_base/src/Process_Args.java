/*
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/SPICEReader/
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
 		boolean emptyarg=true;
		
		if(args.length>0)
		{
			
			for(i=0;i<args.length;i++)
			{
				/**
				 * Process the general arguments first
				 * **/
				 
				if(args[i].toLowerCase().equals("--silent"))
				{
					/** Set value of global variable 'Global_Silent'.**/ 
					SPICEReader.Global_Silent=true;
				}
				
				if(args[i].toLowerCase().equals("--version"))
				{
					/** Set value of global variable 'Global_Silent'.**/ 
					System.out.println("SPICEReader v. 1.0");
					System.exit(0);
				}
				
				if(args[i].toLowerCase().equals("--help"))
				{
					try {
						String help_filepath, help_output;
						help_filepath= SPICEReader.Global_Java_Path+"/txt/help";
						help_output=File_Funcs.File_ReadFile_ReturnString(help_filepath);
						System.out.println(help_output);
						System.exit(0);
					} catch (Exception e) {
						Error_Funcs.ThrowError_Warning("Process_Args.Process_Rest", "Error printing help content."+e.getMessage());
					}
				}
			}
			for (i=0; i<args.length; i++)
			{
				
				if(args[i].toLowerCase().equals("search"))
				{
					Process_Search_Inputs(i,args);
					emptyarg=false;

				}
				
				if (args[i].toLowerCase().equals("state"))
				{
					/** Process arguments for getting state vector.**/
					Process_State_Inputs(i, args);
					emptyarg=false;
				}
				
				if( args[i].toLowerCase().equals("time"))
				{
					Process_Time_Inputs(i, args);
					emptyarg=false;
				}
						
			}
		}
		else
		{
			/** Print help if no arguments given. **/
			
				try {
					String help_filepath, help_output;
					help_filepath= SPICEReader.Global_Java_Path+"/txt/help";
					help_output=File_Funcs.File_ReadFile_ReturnString(help_filepath);
					System.out.println(help_output);
					System.exit(0);
				} catch (Exception e) {
					Error_Funcs.ThrowError_Warning("Process_Args.Process_Rest", "Error printing help content. "+e.getMessage());
				}
			
		}
		
		if(emptyarg)
		{

			try {
				String help_filepath, help_output;
				help_filepath= SPICEReader.Global_Java_Path+"/txt/help";
				help_output=File_Funcs.File_ReadFile_ReturnString(help_filepath);
				System.out.println(help_output);
				System.exit(0);
			} catch (Exception e) {
				Error_Funcs.ThrowError_Warning("Process_Args.Process_Rest", "Error printing help content. "+e.getMessage());
			}
		
		}
	}
	
		
	protected static void Process_State_Inputs(Integer state_pos, String [] args)
	{
		/* 'state_pos' is the value of the argument "state" in the array args.*/
		boolean valid_input;
		
		valid_input=false;
		
		try
		{
			/* Firstly, assume that the input is for getting state vector for a single body. */
			String [] output_vars = new String [2];
			output_vars[0]="SCREEN";
			output_vars[1]="";
			String body, obs, time, time_format;

			if(args[state_pos+1].toLowerCase().equals("-body") && args[state_pos+3].toLowerCase().equals("-obs") && 
					args[state_pos+5].toLowerCase().equals("-time") && args[state_pos+6].toLowerCase().equals("--format"))
			{
			
				
				/* The variable output_vars holds two values - 
				 * 1) The target output location [Screen or File] 
				 * 2) The path to the file if the user wants the output in a file. 
				 */
				  
				output_vars[0]="SCREEN";
				output_vars[1]="";
					
				body=args[state_pos+2];
				obs=args[state_pos+4];
				
				/* Time format could be either Julian date (with an input 'JULIAN') 
				 * or ephemeris time (with an input 'EPH').
				 */
				
				time_format=args[state_pos+7].toUpperCase();
				time=args[state_pos+8];
				if(args.length>state_pos+9)
				{
					if(args[state_pos+9].toLowerCase().contentEquals("output"))
					{
						output_vars[0]="FILE";
						if(args.length>state_pos+10)
						{
							output_vars[1]=args[state_pos+10];
						}
						else
						{
							Error_Funcs.ThrowError_Input("Output file name not specified.");
						}
					}
					else
					{
						time=time+" "+ args[state_pos+9];
						if(args.length>state_pos+10)
						{
							if(args[state_pos+10].toLowerCase().contentEquals("output"))
							{
								output_vars[0]="FILE";
								if(args.length>state_pos+11)
								{
									output_vars[1]=args[state_pos+11];
								}
								else
								{
									Error_Funcs.ThrowError_Input("Output file name not specified.");
								}
							}
						}
						
						
					}
					
				}
				
				
				boolean body_isint, obs_isint,timeparsed=false;
				Integer body_id, obs_id;
				//String time_format;
				body_id=-1;
				obs_id=-1;
				
				try 
				{
					/* Check if the user has give the name of the body or its ID. */
					
					body_id=Integer.parseInt(body);
					body_isint=true;
				}
				catch (NumberFormatException e)
				{
					body_isint=false;
				}
				
				try 
				{
					/* Check if the user has give the name of the body or its ID. */
					
					obs_id=Integer.parseInt(obs);
					obs_isint=true;
				}
				catch (NumberFormatException e)
				{
					obs_isint=false;
				}
				
				
				/* Check if the body and the observer are present in the JPL's SPICELIB's ephemeris. */
				
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
						body=result_body[1].toString();
					}
					
					if(result_obs[0]==1)
					{
						obs_present=true;
						obs=result_obs[1].toString();
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
					
					Position_Funcs.Get_PositionVector_WRT_Observer(body, obs, time, time_format, output_vars);
				}
				
				
				
				
			}
			/* Check in-case the input arguments are for batch processing of state vectors. */
			
			else if (args[state_pos+1].toLowerCase().equals("-input") && args[state_pos+3].toLowerCase().equals("-time")
					&& args[state_pos+4].toLowerCase().equals("--format"))
			{
				valid_input=false;
				String input_file, output_file;
				output_file=null;
				time=null;
				time_format=args[state_pos+5].toUpperCase();
				input_file=args[state_pos+2].trim();
				
				String [] check_ipfile_exists=File_Funcs.File_ExistsorNot(input_file);
				if(check_ipfile_exists[0].contentEquals("FALSE"))
				{
					Error_Funcs.ThrowError_Input("Input file does not exist.");
				}
				
				if (check_ipfile_exists[1].contentEquals("TRUE")) 
				{
					Error_Funcs.ThrowError_Input("Input file is a directory.");
				}

				if(args[state_pos+5].toUpperCase().equals("JULIAN"))
				{
					time=args[state_pos+6].trim();
					time_format="JULIAN";
					
					if(!args[state_pos+7].toLowerCase().equals("output"))
					{
						time=time+" "+args[state_pos+7].trim();
					}
					else
					{
						Error_Funcs.ThrowError_Input("Wrong Julian date/time format. The format is supposed to be YYYY-MM-DD HH:MM:SS.");

					}
					
				}
				else if(args[state_pos+5].toUpperCase().equals("EPH"))
				{
					time=args[state_pos+6].trim();
					time_format="EPH";

				}
				else
				{
					Error_Funcs.ThrowError_Input("Can't understand input.");

				}

				if (args[state_pos+7].toLowerCase().equals("output") && args[state_pos+8].toLowerCase().equals("-file"))
				{
									
					output_file=args[state_pos+9].trim();
					valid_input=true;
				}
				else if(args[state_pos+8].toLowerCase().equals("output") && args[state_pos+9].toLowerCase().equals("-file"))
				{
					output_file=args[state_pos+10].trim();
					valid_input=true;

				}
				else
				{
					Error_Funcs.ThrowError_Input("Output file not specified.");

				}
				
				if(valid_input)
				{
					String [] [] out_bodyobs;
					out_bodyobs=Position_Funcs.Process_Input_BatchFile(input_file, time, output_file);
					output_vars[0]="FILE";
					output_vars[1]=output_file;
					
					Position_Funcs.Get_PositionVector_WRT_ObserverBatch(out_bodyobs, time, time_format, output_vars);
				}
				else
				{
					Error_Funcs.ThrowError_Input("Invalid Input.");
				}
				
			}
			else
			{
				Error_Funcs.ThrowError_Input("Can't understand input.");
			}
		}
		
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Input("Can't understand input.");

		}
	
		
	}
	
	protected static void Process_Time_Inputs(Integer time_pos, String [] args)
	{
		try
		{
		
			if(args[time_pos+1].toLowerCase().equals("-convert") && args.length>=time_pos+3)
			{
				
				String time_input;
				time_input="";
				for(int i=time_pos+2;i<args.length;i++)
				{
					time_input+= " "+args[i].toString();
				}
				Time_Funcs.Convert_Time(time_input);
				
				
					
			}
			else
			{
				Error_Funcs.ThrowError_Input("Wrong format for time function.");
				
			}
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Input("Wrong format for time function.");

		}
		
	}

	protected static void Process_Search_Inputs(Integer search_pos, String [] args)
	{
		if(args.length>=search_pos)
		{
			String body_tofind=args[search_pos+1].trim();
			Integer [] body_search;
			body_search= Toolkit_Funcs.Body_Present(body_tofind);
			
			if(body_search!=null && body_search.length==2)
			{
				if(body_search[0]==1)
				{
					System.out.println("Body \""+ body_tofind+"\" found. Toolkit ID="+body_search[1].toString()+".");
					System.exit(0);
				}
				else
				{
					System.out.println("Body \""+ body_tofind+"\" not found.");
					System.exit(0);
				}
				
			}
			else
			{
				Error_Funcs.ThrowError_Fatal("Process_Args.Process_Search_Inputs", "Something went wrong. No more details.", null);
			}
				
				
				
				
				
				
				
			
		}
		
	}
}
