package src.gui.common;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.Error_Funcs;

public class Verify_Funcs {
	
	
	public static boolean IsValidDate(String text_date)
	{
			
			Boolean v_status;
			v_status=false;
			try {
				String [] date_temp=text_date.split("/");
				if(date_temp.length==3)
				{
					Integer dd,mm,yy;
					
					dd=Integer.parseInt( date_temp[0]);
					mm=Integer.parseInt( date_temp[1]);
					yy=Integer.parseInt( date_temp[2]);
					
					if(yy>0 && yy<3000)
					{
						if(mm>0 && mm<12)
						{
							if(mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12)
							{
								if(dd>0 && dd<=31)
								{
									v_status=true;
								}
							}
							else if (mm==4 || mm==6|| mm==9|| mm==11)
							{
								if(dd>0 && dd<=30)
								{
									v_status=true;
								}
							}
							else if(mm==2)
							{
								Boolean is_leapyear = false;
								
								
								if(yy%4 ==0 && yy%100!=0)
								{
									is_leapyear=true;
								}
								
								if(yy%4 ==0 && yy%100==0 && yy%400==0)
								{
									is_leapyear=true;

								}
								
								if(is_leapyear)
								{
									if(dd>0 && dd<30)
									{
										v_status=true;

									}
									
								}
								else
								{
									if(dd>0 && dd<29)
									{
										v_status=true;

									}
									
								}
								
							}
						}	
					}
					
				}
				
				if(!v_status)
				{
					return false;
				}
				
				return v_status;
				
			}
			catch (Exception e)
			{
				Error_Funcs.ThrowError_Warning("src.gui.common.Verify_Funcs.IsValidDate", e.getMessage());
				return false;	
			}
			
		}
	
	public static boolean IsValidTime(String text_time)
	{
		if(text_time!=null && text_time!="")
		{
			
			String [] splittime = text_time.split(":");
			
			if(splittime.length!=3)
			{
				return false;
			}
			else
			{
				for (int i=0; i<splittime.length; i++)
				{
					if(splittime[i]=="" || splittime[i]==null)
					{
						return false;
					}
					
					if(!isString_Int(splittime[i]))
					{
						return false;
					}
					
				}
				
				
				Integer temp_tocheck;
				
				temp_tocheck=Integer.getInteger(splittime[0]);
				
				if(temp_tocheck<0 || temp_tocheck>23)
				{
					return false;
				}
				
				temp_tocheck=Integer.getInteger(splittime[1]);
				
				if(temp_tocheck<0 || temp_tocheck>59)
				{
					return false;
				}
				
				temp_tocheck=Integer.getInteger(splittime[2]);
				
				if(temp_tocheck<0 || temp_tocheck>59)
				{
					return false;
				}
				
				
				return true;
				
				
			}
			
		}
		else
		{
			return false;
		}
		
		
		
	}
	
	
	public static boolean isString_Int(String input)
	{
		Integer output;
		
		try
		{
			output=Integer.getInteger(input);
			return true;
			
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		catch (Exception e)
		{
			Error_Funcs.ThrowError_Warning("src.gui.common.Verify_Funcs.isString_Int", e.getMessage());
			return false;
		}
	}
}
