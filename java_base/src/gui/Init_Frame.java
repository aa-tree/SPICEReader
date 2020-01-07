package src.gui;
import javax.swing.*;
import java.awt.*;


public class Init_Frame extends JFrame
{
	
	
	public Init_Frame(String title)
	{
		Set_FrameSizeandTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public Init_Frame(String title, Integer sizeRatio)
	{
		Set_FrameSizeandTitle(title, sizeRatio);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	protected void Set_FrameSizeandTitle(String title)
	{
		
		setTitle(title);
		
		Toolkit toolkit = getToolkit();
		Dimension screensize= toolkit.getScreenSize();
		setSize(screensize.width, screensize.height);
		
		
	}
	
	protected void Set_FrameSizeandTitle(String title, Integer sizeRatio)
	{
		
		setTitle(title);
		Toolkit toolkit = getToolkit();
		Dimension screensize= toolkit.getScreenSize();
		if(sizeRatio!=0)
		{
			
			
			setSize(screensize.width/sizeRatio, screensize.height/sizeRatio);
				
		}
		else
		{
			setSize(screensize.width/2, screensize.height/2);
		}
		
		
	}
	
}


