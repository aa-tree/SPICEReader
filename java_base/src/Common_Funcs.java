package src;
import java.io.*;
import java.util.ArrayList;
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

                // use comma as separator
                String[] temp_read_line = line.split(",");

                model_bodies_al.add(temp_read_line[1]);
                //System.out.println(i+ model_bodies[i]);


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
}
