package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Toolkit_Funcs {
	
	public static boolean Body_Present(Integer body)
	{
		boolean present;
		present=false;
		
		File eph_list = new File(SPICEReader.Global_Fortran_Path+"/eph/list_of_bodies.csv");

		String read_line, id_string;
		id_string=body.toString();
		String [] csv_read = new String[4];

		try
		{
			FileReader fr = new FileReader(eph_list);
			BufferedReader br = new BufferedReader(fr);

			while ((read_line= br.readLine())!=null)
			{
		
				csv_read=read_line.split(",");
				
				if(id_string.equals(csv_read[0].toString()))
				{
					present=true;
				}

			}
			
			br.close();

	
		}
		catch (Exception e)
		{
		}
		
		
		return present;
	}
	
	public static Integer [] Body_Present(String body)
	{
		
		Integer [] result_out=new Integer[2];
		result_out[0]=0;
		result_out[1]=-1;
		
		File eph_list = new File(SPICEReader.Global_Fortran_Path+"/eph/list_of_bodies.csv");

		String read_line;
		String [] csv_read = new String[4];
		
		try
		{
			FileReader fr = new FileReader(eph_list);
			BufferedReader br = new BufferedReader(fr);

			while ((read_line= br.readLine())!=null)
			{
		
				csv_read=read_line.split(",");
				String body_fromFile;
				body_fromFile=csv_read[1].toString();
				body=body.toUpperCase();
				body=body.trim();

				if(body.equals(body_fromFile))
				{
					result_out[0]=1;
					result_out[1]=Integer.parseInt(csv_read[0]);


				}

			}
			
			br.close();
	
		}
		catch (Exception e)
		{
			
		}
		
		return result_out;
	}

	public static String Time_GetFormat(String time)
	{
		String time_format;
		time_format=null;
		
		Double time_p;
		boolean time_isdouble = false;
		try
		{
			time_p=Double.parseDouble(time);
			time_format="EPH_TIME";
			time_isdouble=true;
		}
		catch (NumberFormatException e)
		{
			time_isdouble=false;
		}
		
		
		try 
		{
			
		
		if(!time_isdouble)
		{
			//Assuming Time is in the format YYYY/MM/DD HH:MM:SS (24Hour Clock) 
			
			String [] time_date;
			time=time.trim();
			time_date=time.split(" ");
			
			
			if(time_date.length==2)
			{
				time_format="JULIAN_DATE";
			}
			else
			{
			}
			
			
		}
		
			
		}
		catch (Exception e)
		{
			
		}
		
			
		return time_format;
		
		
		
	}
}
