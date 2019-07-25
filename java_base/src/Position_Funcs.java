package src;

public class Position_Funcs {
	
	public static String [] Get_PositionVector_WRT_Observer(String target, String observer, String time)
	{
		String [] position=new String[6];
		
		position=null;
		
		if(target!=null && observer!=null && time!=null)
		{
			String[] file_paths;
			
			file_paths=File_Funcs.Generate_TMP_Folder_Return_Path();
			Setup_Files_PositionVector(file_paths, target, observer, time);
			System.out.println(Common_Funcs.Program_CompileandRun(file_paths));
			
		}
		else
		{
			Error_Funcs.ThrowError_Input("Error.");
		}
			
		
		return position;
		
		
	}
	
	public static boolean Setup_Files_PositionVector(String [] paths, String target, String observer, String time)
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

		String time_format;
		
		time_format= Toolkit_Funcs.Time_GetFormat(time);
		
		f_search[3]="%_TIME_CONVERSION_F%";

		if(time_format=="JULIAN_DATE")
		{
			f_replace[3]="CALL STR2ET ( '"+time+"', ET)";
		}
		else
		{
			f_replace[3]="ET ="+time;
		}
		
		File_Funcs.File_ReplacePattern(fortran_path, f_search, fortran_destination, f_replace);
		
		String [] f_search2 = new String[1];
		String [] f_replace2 = new String[1];
		
		f_search2[0]="%_TEST_MMAX_VALUE%";
		f_replace2[0]="2";
		
		File_Funcs.File_ReplacePattern(common_dat, f_search2, common_dat_dest, f_replace2);
		
		f_search2[0]="%_TEST_ID_VALUE%";
		f_replace2[0]=paths[0];
		
		File_Funcs.File_ReplacePattern(make_sh, f_search2, make_sh_dest, f_replace2);

	
		return status;
	}
	
	
	

}
