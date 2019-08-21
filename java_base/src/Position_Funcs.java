package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Position_Funcs {
	
	public static boolean Get_PositionVector_WRT_Observer(String target, String observer, String time, String time_format, String [] output_vars)
	{
		
		if(target!=null && observer!=null && time!=null && time_format!=null)
		{
			String[] file_paths;
			
			file_paths=File_Funcs.Generate_TMP_Folder_Return_Path();
			Setup_Files_PositionVector(file_paths, target, observer, time, time_format);
			String output_program= Common_Funcs.Program_CompileandRun(file_paths);
			
			if(!SPICEReader.Global_Silent)
			{
				System.out.println("Output folder: "+file_paths[1]+"/"
						+System.getProperty("line.separator"));
			}
			
			Common_Funcs.Output_GenerateCSV("POSITION_SOLO", output_program, output_vars[1], file_paths[1], 
					"Position_Funcs.Get_PositionVector_WRT_Observer");
			
			return true;
		}
		else
		{
			Error_Funcs.ThrowError_Input("Error.");
			return false;
		}
			
		
		
		
		
	}
	
	public static boolean Get_PositionVector_WRT_ObserverBatch(String [] [] bodies, String time, String time_format, String [] output_vars)
	{
		if(bodies!=null && bodies[0].length>0 && bodies[0].length==bodies[1].length)
		{
			String[] file_paths;
			
			file_paths=File_Funcs.Generate_TMP_Folder_Return_Path();
			Setup_Files_PositionVector_Batch(file_paths, bodies, time, time_format);
			
			String output_program= Common_Funcs.Program_CompileandRun(file_paths);

			if(!SPICEReader.Global_Silent)
			{
				System.out.println("Output folder: "+file_paths[1]+"/"
						+System.getProperty("line.separator"));
			}
			
			Common_Funcs.Output_GenerateCSV("POSITION_BATCH",output_program, output_vars[1], file_paths[1], 
					"Position_Funcs.Get_PositionVector_WRT_Observer");
			
			return true;
		}
	
		else
		{
			Error_Funcs.ThrowError_Input("Error. Something went wrong.");
			return false;
		}
		
			
		
	}
	
	
	public static boolean Setup_Files_PositionVector(String [] paths, String target, String observer, String time, String time_format)
	{
		boolean status;
		status=false;
		
		String model_filecontent;
		model_filecontent=target+System.getProperty("line.separator")
		+observer+System.getProperty("line.separator");
		
		File_Funcs.File_WriteStringtoFile("model.dat", paths[1], model_filecontent);
		
		String template_path = SPICEReader.Global_Java_Path+"/templates/";
		String fortran_path,fortran_destination;
		String common_dat,common_dat_dest, make_sh, make_sh_dest;

		
		fortran_path=template_path+"Get_PositionVector_WRT_Observer.f";
		fortran_destination=paths[1]+"/Get_PositionVector_WRT_Observer.f";
		common_dat=template_path+"common/common.dat";
		make_sh=template_path+"common/make.sh";
		common_dat_dest=SPICEReader.Global_Fortran_Path+"/code/common.dat";
		make_sh_dest=paths[1]+"/make.sh";

		String [] f_search = new String[4];
		String [] f_replace = new String[4];
		
				
		f_search[0]="%_PROGRAMNAME%";
		f_replace[0]="PROGRAM_"+paths[0];
		
		f_search[1]="%_IBC_VALUE%";
		f_replace[1]=observer;

		f_search[2]="%_TARGET_VALUE%";
		f_replace[2]=target;


		f_search[3]="%_TIME_CONVERSION_F%";
		f_replace[3]="";

		if(time_format.trim().contentEquals("JULIAN"))
		{
			f_replace[3]="CALL STR2ET ( '"+time+"', ET)";
		}
		else if(time_format.trim().contentEquals("EPH"))
		{
			f_replace[3]="ET ="+time;
		}
		
		String replace_status=null;
		replace_status= File_Funcs.File_ReplacePattern(fortran_path, f_search, fortran_destination, f_replace);
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
		String [] f_search2 = new String[1];
		String [] f_replace2 = new String[1];
		
		f_search2[0]="%_TEST_MMAX_VALUE%";
		f_replace2[0]="2";
		replace_status=null;
		replace_status=File_Funcs.File_ReplacePattern(common_dat, f_search2, common_dat_dest, f_replace2);
		
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
		f_search2[0]="%_TEST_ID_VALUE%";
		f_replace2[0]=paths[0];
		replace_status=null;
		
		replace_status= File_Funcs.File_ReplacePattern(make_sh, f_search2, make_sh_dest, f_replace2);
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
	
		return status;
	}
	
	public static boolean Setup_Files_PositionVector_Batch(String [] paths, String [] [] bodies, String time, String time_format)
	{
		boolean status;
		status=false;
		String model_filecontent="";
		Integer Maxlength=2*bodies[0].length;
		boolean body_duplicate;
		ArrayList<String> singlearray_model = new ArrayList<String>();

		for (int i=0;i<bodies[0].length;i++)
		{
			body_duplicate=false;
			for (int j=0; j<singlearray_model.size(); j++)
			{
				if(singlearray_model.get(j).contentEquals(bodies[i][0].trim()))
				{
					body_duplicate=true;
				}
					
			}
			
			if(!body_duplicate)
			{
				singlearray_model.add(bodies[i][0].trim());
			}
		}
		
		for (int i=0;i<bodies[1].length;i++)
		{
			body_duplicate=false;
			for (int j=0; j<singlearray_model.size(); j++)
			{
				if(singlearray_model.get(j).contentEquals(bodies[i][1].trim()))
				{
					body_duplicate=true;
				}
					
			}
			
			if(!body_duplicate)
			{
				singlearray_model.add(bodies[i][1].trim());
			}
		}
		
		/** Add the Arraylist components to model.dat file string. **/
		
		for(int i=0;i<singlearray_model.size();i++)
		{
			model_filecontent=model_filecontent+singlearray_model.get(i)+System.getProperty("line.separator");
		}
		
		File_Funcs.File_WriteStringtoFile("model.dat", paths[1], model_filecontent);

		String template_path = SPICEReader.Global_Java_Path+"/templates/";
		String fortran_path,fortran_destination;
		String common_dat,common_dat_dest, make_sh, make_sh_dest;

		
		fortran_path=template_path+"Get_PositionVector_WRT_Observer_Batch.f";
		fortran_destination=paths[1]+"/Get_PositionVector_WRT_Observer_Batch.f";
		common_dat=template_path+"common/common.dat";
		make_sh=template_path+"common/make.sh";
		common_dat_dest=SPICEReader.Global_Fortran_Path+"/code/common.dat";
		make_sh_dest=paths[1]+"/make.sh";

		String [] f_search = new String[4];
		String [] f_replace = new String[4];
		
		f_search[0]="%_PROGRAMNAME%";
		f_replace[0]="PROGRAM_"+paths[0];
		
		f_search[1]="%_IBC_VALUE%";
		f_replace[1]="0";

		f_search[2]="%_POSITION_COMMANDS_BATCH%";
		f_replace[2]="";
		
		/**GET_POS_WRT_BODYID(ET, I_ID, I_BODY, O_POS)**/
		
		for (int i=0;i<bodies[0].length;i++)
		{
			f_replace[2]=f_replace[2]+"        CALL GET_POS_WRT_BODYID(ET,"+bodies[i][0].trim()
					+","+bodies[i][1].trim()+", O_POS)"+System.getProperty("line.separator");
			f_replace[2]=f_replace[2]+"        WRITE(*,*) '"+bodies[i][0].trim()+"'"+System.getProperty("line.separator");
			f_replace[2]=f_replace[2]+"        DO I=1,6"+System.getProperty("line.separator");
			f_replace[2]=f_replace[2]+"        WRITE(*,*) O_POS(I)"+System.getProperty("line.separator");
			f_replace[2]=f_replace[2]+"        END DO"+System.getProperty("line.separator");
		
		}
		

		f_search[3]="%_TIME_CONVERSION_F%";
		f_replace[3]="";

		if(time_format.trim().contentEquals("JULIAN"))
		{
			f_replace[3]="CALL STR2ET ( '"+time+"', ET)";
		}
		else if(time_format.trim().contentEquals("EPH"))
		{
			f_replace[3]="ET ="+time;
		}
		
		String replace_status=null;
		replace_status= File_Funcs.File_ReplacePattern(fortran_path, f_search, fortran_destination, f_replace);
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
		String [] f_search2 = new String[1];
		String [] f_replace2 = new String[1];
		
		f_search2[0]="%_TEST_MMAX_VALUE%";
		f_replace2[0]="2";
		replace_status=null;
		replace_status=File_Funcs.File_ReplacePattern(common_dat, f_search2, common_dat_dest, f_replace2);
		
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
		f_search2[0]="%_TEST_ID_VALUE%";
		f_replace2[0]=paths[0];
		replace_status=null;
		
		replace_status= File_Funcs.File_ReplacePattern(make_sh, f_search2, make_sh_dest, f_replace2);
		if(replace_status!=null)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.File_ReplacePattern",replace_status, null);
		}
		
	

		
		return status;
	}
	
	
	
	public static String[] [] Process_Input_BatchFile(String input_file, String time, String output_file)
	{
		String [] [] body_obs_list=null;
		ArrayList<String> temp_obs = new ArrayList<String>();
		ArrayList<String> temp_body = new ArrayList<String>();
		try
		{
			FileReader fr= new FileReader(input_file);
			BufferedReader br= new BufferedReader(fr);
			String temp_line;
			String [] array_line;
			
			while((temp_line=br.readLine())!=null)
			{
				String obs, body;
				array_line=temp_line.split(",");
				if(array_line.length==2)
				{
					body=array_line[0].trim();
					obs=array_line[1].trim();
					
					if(body!=null && body!="" && obs!=null &&obs!="")
					{
						Integer[] body_id, obs_id;
						Integer temp_intid;
						String final_bodyid="";
						String final_obs_id="";
						boolean bodypresent=false;
						boolean obspresent=false;

						
						String id_or_name, obs_orname;
						id_or_name= Common_Funcs.Is_Input_IDorName(body);
						obs_orname=Common_Funcs.Is_Input_IDorName(body);
						
						if(id_or_name.contentEquals("INT"))
						{
							temp_intid=Integer.parseInt(body);
							bodypresent=Toolkit_Funcs.Body_Present(temp_intid);
							if(bodypresent)
							{
								final_bodyid=body;	
							}
							else
							{
								final_bodyid="";
							}
							
														
						}
						if (id_or_name.contentEquals("STRING"))
						{
							body_id=Toolkit_Funcs.Body_Present(body);
							if(body_id[0]==1)
							{
								bodypresent=true;
								final_bodyid=body_id[1].toString();
							}
							else
							{
								bodypresent=false;
								final_bodyid="";
							}
							

						}
						
						if(obs_orname.contentEquals("INT"))
						{
							temp_intid=Integer.parseInt(obs);
							obspresent=Toolkit_Funcs.Body_Present(temp_intid);
							if(obspresent)
							{
								final_obs_id=obs;	
							}
							else
							{
								final_obs_id="";
							}
							
														
						}
						if (id_or_name.contentEquals("STRING"))
						{
							obs_id=Toolkit_Funcs.Body_Present(obs);
							if(obs_id[0]==1)
							{
								obspresent=true;
								final_obs_id=obs_id[1].toString();
							}
							else
							{
								obspresent=false;
								final_obs_id="";
							}
							

						}
						
						if(bodypresent && obspresent && final_obs_id!="" && final_bodyid!="")
						{
							
							temp_obs.add(final_obs_id);
							temp_body.add(final_bodyid);
							
						}

					
						
					}
						
				}
				else
				{
					Error_Funcs.ThrowError_Input("Invalid Input file format.");
				}
			}
			
			
			
			if(temp_body.size()>0 && temp_obs.size()>0)
			{
				body_obs_list=new String[temp_body.size()][2];
				
				for(int i=0;i<temp_body.size();i++)
				{
					body_obs_list[i][0]=temp_body.get(i).toString();
					body_obs_list[i][1]=temp_obs.get(i).toString();
				}
				
			}
			else
			{
				Error_Funcs.ThrowError_Input("No body in the input file found in the ephemeris.");
			}
			
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Fatal("Position_Funcs.Process_Input_BatchFile", e.getMessage(),e.getStackTrace());
		}
		
		return body_obs_list;
		
	}
	
	

}
