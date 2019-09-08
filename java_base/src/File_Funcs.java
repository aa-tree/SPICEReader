package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.lang.Math;
public class File_Funcs {

	
	public static String[] Generate_TMP_Folder_Return_Path() 
	{
	
		String [] out_id_andpath = new String[2];
		String random_name;
		//random_name = Long.toHexString(Double.doubleToLongBits(Math.random()));
		 Random random = new Random();
		random_name="TEST_"+Integer.toString(Math.abs(random.nextInt()));
		
		try 
		{
			Integer counter_tries=0;

			 File dir_path = new File(SPICEReader.Global_Fortran_Path+"tmp/"+random_name);
			    
			    while (dir_path.exists() || dir_path.isDirectory()&&counter_tries<30) {
			    	counter_tries++;
					random_name="TEST_"+Integer.toString(Math.abs(random.nextInt()));
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
					Error_Funcs.ThrowError_Fatal("Generate_TMP_Folder_Return_Path", "Can't create directory in /fortran_src/tmp folder.", null);
				    return null;

			    }
				
			    
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Fatal("File_Funcs.Generate_TMP_Folder_Return_Path", e.getMessage(), null);
		    return null;

		}
	   
		
		
		
		
		
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
			Error_Funcs.ThrowError_Fatal("File_ReturnPaths", e.getMessage(), e.getStackTrace());
		}

		return paths;
	}

	public static String File_ReplacePattern(String source_file_path, String [] text_tosearch, String destination_file_path, 
			String [] text_final)
	{
		
		String return_status;
		return_status="Failed.";
		

		
		
		String temp_fr, temp_fr_fullfile;
		try {
			File source_file=new File(source_file_path);
			/*
			 * Destination Name:		
			 */
					
			File dest_file=new File(destination_file_path);
		
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
				
				temp_fr_fullfile+=temp_fr+System.getProperty("line.separator");
				
				

			}
			
			fw.write(temp_fr_fullfile);
			br.close();
			fw.close();
			return_status=null;
			
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Warning("File_Funcs.File_ReplacePattern", e.getMessage());
			return_status="Failed.";

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

	public static String[] File_ExistsorNot(String path)
	{
		String[] out_ret= {"FALSE","FALSE"};
		File tempFile = new File(path);
		try
		{
			if(tempFile.exists())
			{
				out_ret[0]="TRUE";
				if(tempFile.isDirectory())
				{
					out_ret[1]="TRUE";
					
				}
			}
			
	
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Warning("File_Funcs.File_ExistsorNot", e.getMessage());
		}
		
		return out_ret;
		
	}
	
	public static String File_ReadFile_ReturnString(String file_path) throws Exception, IOException
	{
		String output="";

		String temp_fr;
		File source_file=new File(file_path);
		FileReader fr = new FileReader(source_file);
		BufferedReader br = new BufferedReader(fr);
		while ((temp_fr= br.readLine())!=null)
		{
			output+=temp_fr+System.getProperty("line.separator");
				
		}
		
		return output;
	}
	
	
}
