package src;

public class Position_Funcs {
	
	public static String [] Get_PositionVector_WRT_Observer(String target, String observer, String time)
	{
		String [] position=new String[6];
		
		position=null;
		
		if(target!=null && observer!=null && time!=null)
		{
			String[] file_paths;
			
			file_paths=File_Funcs.Generate_TMP_Folder_Return_Path();
			
		}
		else
		{
			Error_Funcs.ThrowError_Input("Get_PositionVector_WRT_Observer", "Error.");
		}
			
		
		return position;
		
		
	}
	
	
	

}
