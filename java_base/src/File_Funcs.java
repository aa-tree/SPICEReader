package src;

import java.io.File;

public class File_Funcs {

	
	public static String[] Generate_TMP_Folder_Return_Path() 
	{
	
		String [] out_id_andpath = new String[2];
		
		String [] paths;
		paths=File_ReturnPaths();
		
		String random_name = Long.toHexString(Double.doubleToLongBits(Math.random()));
		
	    File dir_path = new File(paths[1]+"tmp/"+random_name);
	    while (dir_path.exists() || dir_path.isDirectory()) {
			random_name = Long.toHexString(Double.doubleToLongBits(Math.random()));
			dir_path = new File(paths[1]+"tmp/"+random_name);
	    	}
	    
	    out_id_andpath[0]=random_name;
	    out_id_andpath[1]=dir_path.getAbsolutePath();
	    
	    boolean dir_creation= dir_path.mkdir();
	    
	    if(dir_creation)
	    {
	    	return out_id_andpath;
	    }
	    else
	    	
	    {
			Error_Funcs.ThrowError_Fatal("Generate_TMP_Folder_Return_Path", "Can't create directory in /fortran_src/tmp folder.");

	    }
		
	    
	    return null;
		
		
		
		
		
	}
	
	
	public static String [] File_ReturnPaths()
	{
		String [] paths= new String[2];
		
		paths[0]="";
		paths[1]="";
		
		try {
		
		String current_folder_path;
		current_folder_path=System.getProperty("user.dir");
		paths[0]=current_folder_path;
		
		
		String[] primary_folder_path_java, primary_folder_path_fortran;
		primary_folder_path_java=current_folder_path.split("/");
		
		primary_folder_path_fortran=primary_folder_path_java;
		primary_folder_path_fortran[primary_folder_path_fortran.length-1]="fortran_src";
		
		
		
		for(int i=0; i<primary_folder_path_fortran.length;i++)
		{
			if(primary_folder_path_fortran[i].trim() !=null || primary_folder_path_fortran[i].trim() !="")
			{
				paths[1] +=primary_folder_path_fortran[i].trim()+"/";
			}
			 
		}
		
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Fatal("File_ReturnPaths", e.getMessage());
		}

		return paths;
	}
}
