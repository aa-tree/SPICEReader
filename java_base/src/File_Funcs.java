package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class File_Funcs {

	
	public static String[] Generate_TMP_Folder_Return_Path() 
	{
	
		String [] out_id_andpath = new String[2];
/*		
		String [] paths;
		paths=File_ReturnPaths();
*/		
		String random_name = Long.toHexString(Double.doubleToLongBits(Math.random()));
		
	    File dir_path = new File(SPICEReader.Global_Fortran_Path+"tmp/"+random_name);
	    while (dir_path.exists() || dir_path.isDirectory()) {
			random_name = Long.toHexString(Double.doubleToLongBits(Math.random()));
			dir_path = new File(SPICEReader.Global_Fortran_Path+"tmp/"+random_name);
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

	public static String File_ReplacePattern(String source_file_path, String [] text_tosearch, String destination_file_path, 
			String [] text_final)
	{
		
		String return_status;
		return_status="Failed.";
		
		File source_file=new File(source_file_path);
/*
 * Destination Name:		
 */
		
		File dest_file=new File(destination_file_path);
		
		
		String temp_fr, temp_fr_fullfile;
		try {
		
			FileReader fr = new FileReader(source_file);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw= new FileWriter(dest_file);
			
			temp_fr_fullfile="";
			while ((temp_fr= br.readLine())!=null)
			{
		
				for (int i=0; i<text_tosearch.length;i++)
				{
					temp_fr=temp_fr.replace(text_tosearch[i], text_final[i]);
				}
				
				temp_fr_fullfile+=temp_fr+"\n";
				
				

			}
			
			fw.write(temp_fr_fullfile);
			br.close();
			fw.close();
			return_status=null;
			
		}
		catch (Exception e)
		{
			return_status=e.getMessage();
		}
		
		
		return return_status;

		
	}
	
	public static boolean File_WriteStringtoFile(String filename, String path, String Content)
	{
		boolean status=false;

		String file_fullpath=path+"/"+filename;

		try
		{
			File dest_file=new File(file_fullpath);
			FileWriter fw= new FileWriter(dest_file);
			
			fw.write(Content);
			fw.close();
			status=true;

		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Warning("File_WriteStringtoFile", e.getMessage());
		}
		
		
		return status;
	}
}
