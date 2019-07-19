package src;
import javax.swing.*;
import java.awt.*;


public class Init_Frame extends JFrame
{
	
	
	public Init_Frame(String title)
	{
		Set_FrameSizeandTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	protected void Set_FrameSizeandTitle(String title)
	{
		
		setTitle(title);
		
		Toolkit toolkit = getToolkit();
		Dimension screensize= toolkit.getScreenSize();
		setSize(screensize.width, screensize.height);
		
		
	}
	
}


