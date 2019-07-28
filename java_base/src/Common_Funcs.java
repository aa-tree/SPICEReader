package src;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Common_Funcs {
	
	public static String [] Read_Model_ReturnBodies() {

		String[] model_bodies;
		ArrayList<String> model_bodies_al = new ArrayList<>();

        String file_path="../fortran_src/eph/list_of_bodies.csv";
        String line = "";
        
		BufferedReader br=null; 
		
		Integer length_model=0;
		Integer i;
		
		try 
		
		{

            br = new BufferedReader(new FileReader(file_path));
            while ((line = br.readLine()) != null) 
            
            {

                String[] temp_read_line = line.split(",");

                model_bodies_al.add(temp_read_line[1]);


            }
            
            length_model=model_bodies_al.size();
            
            if(length_model>0)
            {
            	model_bodies=new String[length_model];
            	
            	for (i=0;i<length_model;i++)
            	{
            		model_bodies[i]=model_bodies_al.get(i);
            	}
            	
            }
            else
            {
            	model_bodies=null;
            }

        } 
		
		catch (FileNotFoundException e) 
		
		{
			model_bodies=null;

            e.printStackTrace();
        } 
		catch (IOException e) 
		
		{
			model_bodies=null;

            e.printStackTrace();
        }
		finally 
		{
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                	model_bodies=null;

                    e.printStackTrace();
                }
		
            }
		 }
		return(model_bodies);	
	}

	public static String [] [] Read_Model_ReturnBodies_Sc() {

		String[][] model_bodies;
		ArrayList<String> model_bodies_al = new ArrayList<>();
		ArrayList<String> model_bodies_ID = new ArrayList<>();


        String file_path="../fortran_src/eph/list_of_bodies.csv";
        
        File file_toread = new File(file_path);
        
        String line = "";
        
		
		Integer length_model=0;
		Integer i;
	    Scanner sc;
	     

		
		try 
		
		{
			sc = new Scanner(file_toread);
	    	while (sc.hasNextLine()) 
            
            {
	    		line = sc.nextLine();
                // use comma as separator
                String[] temp_read_line = line.split(",");

                model_bodies_al.add(temp_read_line[1]);
                model_bodies_ID.add(temp_read_line[0]);
                //System.out.println(i+ model_bodies[i]);


            }
            
            length_model=model_bodies_al.size();
            
            if(length_model>0)
            {
            	model_bodies=new String [length_model] [2];
            	
            	for (i=0;i<length_model;i++)
            	{
            		model_bodies[i][0]=model_bodies_al.get(i);
            		model_bodies[i][1]=model_bodies_ID.get(i);
            	}
            	
            }
            else
            {
            	model_bodies=null;
            }
            sc.close();


        } 
		
		catch (Exception e) 
		
		{
			model_bodies=null;

            e.printStackTrace();
        } 

		finally 
		{
		}
		
		return(model_bodies);	
	}
	
	
	public static String Program_CompileandRun(String[] paths)
	{
		String result_compile=null;
		String executionresult=null;
		
		boolean compiled=false;
		try
		{
			String []command= {"sh","make.sh"};
			ProcessBuilder builder = new ProcessBuilder();
			builder.directory(new File(paths[1]));
			builder.command(command);
			Process process_1= builder.start();
	        int errCode = process_1.waitFor();			
			
	        StringBuilder sb = new StringBuilder();
	        InputStream is= process_1.getInputStream();
	        BufferedReader br=null;
	        
	        br=new BufferedReader(new InputStreamReader(is));
	        String read_line=null;
	        
	        while ((read_line=br.readLine())!=null)
	        {
	        	sb.append(read_line+System.getProperty("line.separator"));
	        }
	        
	        br.close();
	        result_compile=sb.toString();
	        
	        compiled=true;

		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Fatal("Common_Funcs.Program_CompileandRun", e.getMessage());
		}
		
		if(compiled)
		{
			try
			{
				/* Run the program.*/
				ProcessBuilder builder = new ProcessBuilder();
				builder.directory(new File(paths[1]));
				String command2= "./"+paths[0]+".exe";
				builder.command(command2);

				Process process_2= builder.start();
		        process_2.waitFor();
		        
		        StringBuilder sb = new StringBuilder();
		        InputStream is= process_2.getInputStream();
		        
		        
		        BufferedReader br=new BufferedReader(new InputStreamReader(is));
		        String read_line=null;
		        
		        while ((read_line=br.readLine())!=null)
		        {
		        	sb.append(read_line+System.getProperty("line.separator"));
		        }
		        
		        br.close();
		        executionresult=sb.toString();
		        
		        return executionresult;
		        
			}
			catch (Exception e)
			{
				Error_Funcs.ThrowError_Fatal("Common_Funcs.Program_CompileandRun", e.getMessage());

			}
			

		}
				
		
		return executionresult;
	}
	
	public static void Output_GenerateCSV(String t_output, String file_name, String path_tofile, String ref_func)
	{
		String [] o_toparse = t_output.split(System.getProperty("line.separator"));
		String ret_output=o_toparse[0].trim();
		for(int i=1; i<o_toparse.length; i++)
		{
			ret_output=ret_output+","+o_toparse[i].trim();
		}
		
		if(file_name.trim().contentEquals("") || path_tofile.trim().contentEquals(""))
		{
			
			System.out.println(ret_output);
		}
		
		if(!file_name.trim().contentEquals("") && !path_tofile.trim().contentEquals(""))
		{
			try
			{
				
				File_Funcs.File_WriteStringtoFile(file_name, path_tofile, ret_output);					
			}
			catch (Exception e)
			{
				Error_Funcs.ThrowError_Warning("Common_Funcs.Output_GenerateCSV", 
						"Cannnot write to output file. Instead printing output to screen. Referring function: "+
				ref_func+". Error details: "
				+e.getMessage());
			}
		}
		
		
		
		
		
		
	}
}
