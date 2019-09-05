package src;

public class Time_Funcs {

	
	public static boolean Convert_Time(String time_value)
	{
		String[] file_paths;
		String time_type=null;
		
		try
		{
			time_type=Toolkit_Funcs.Time_GetFormat(time_value);
			file_paths=File_Funcs.Generate_TMP_Folder_Return_Path();
			Setup_TimeFiles(time_value, time_type, file_paths); 
			String output_program= Common_Funcs.Program_CompileandRun(file_paths);
			
			if(!SPICEReader.Global_Silent)
			{
				System.out.println("Output folder: "+file_paths[1]+"/"
						+System.getProperty("line.separator"));
			}
			
			System.out.println(output_program);
			return true;
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Fatal("Time_Funcs.Convert_Time", e.getMessage(), e.getStackTrace());
			return false;

		}
		

	}
	
	public static boolean Setup_TimeFiles(String time_value, String time_type, String [] paths) throws Exception 
	{
		boolean status=false;

		/*
		String model_filecontent;
		model_filecontent=target+System.getProperty("line.separator")
		+observer+System.getProperty("line.separator");
		
		File_Funcs.File_WriteStringtoFile("model.dat", paths[1], model_filecontent);
		**/
		
		String template_path = SPICEReader.Global_Java_Path+"/templates/";
		String fortran_path,fortran_destination;
		String common_dat,common_dat_dest, make_sh, make_sh_dest;

		String model_filecontent;
		model_filecontent="0";
		
		File_Funcs.File_WriteStringtoFile("model.dat", paths[1], model_filecontent);

		fortran_path=template_path+"Convert_Time.f";
		fortran_destination=paths[1]+"/Convert_Time.f";
		common_dat=template_path+"common/common.dat";
		make_sh=template_path+"common/make.sh";
		common_dat_dest=SPICEReader.Global_Fortran_Path+"/code/common.dat";
		make_sh_dest=paths[1]+"/make.sh";

		String [] f_search = new String[4];
		String [] f_replace = new String[4];
		
				
		f_search[0]="%_PROGRAMNAME%";
		f_replace[0]="PROGRAM_"+paths[0];
		
		time_type=Toolkit_Funcs.Time_GetFormat(time_value);
		
		if(time_type.trim().contentEquals("EPH_TIME"))
		{
			f_search[1]="%_DEFINE_VARIABLES%";
			f_replace[1]="        ET="+time_value;	
			
			f_search[2]="%_TIME_CONVERSION_F%";
			f_replace[2]="        CALL TIMOUT (ET, 'YYYY-MM-DD HR:MN:SC.####', TIME_C)";

			f_search[3]="%_OUTPUT_COMMANDS%";
			f_replace[3]="TIME_C";

		}
		else
		{
			f_search[1]="%_DEFINE_VARIABLES%";
			f_replace[1]="        TIME_C='"+time_value+"'";
			
			
			f_search[2]="%_TIME_CONVERSION_F%";
			f_replace[2]="        CALL STR2ET ( TIME_C, ET )";

			f_search[3]="%_OUTPUT_COMMANDS%";
			f_replace[3]="ET";
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
		f_replace2[0]="0";
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
}
