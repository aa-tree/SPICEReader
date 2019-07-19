package src;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputVerifier_Date extends InputVerifier{

	@Override
	public boolean verify(JComponent arg0) {
		
		Boolean v_status;
		v_status=false;
		String text_date = ((JTextField) arg0).getText();
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
				JOptionPane.showMessageDialog(arg0, "Please enter a valid date in DD/MM/YYYY format!");
				
			}
			
			return v_status;
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(arg0, "Error:"+e.getMessage());
			return false;	
		}
		
	}
	
	 

}
