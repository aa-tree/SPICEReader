/*
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/SPICEReader/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
*/

package src;

public class SPICEReader
{
	public static String Global_Java_Path;
	public static String Global_Fortran_Path;
	public static boolean Global_Silent;

	
  public static void main (String args[])
  {
	  Set_Global_Vars();
	  

	  if(Process_Args.Args_INIT(args))
	  {
		  String paths[];

		  //Position_Funcs.Get_PositionVector_WRT_Observer("A", "b", "C");
		  
		  
		  Process_Args.Args_Process_Rest(args);
	  }
	  else
	  {
		  Get_StateVectors get_sv = new Get_StateVectors();

	  }

  }
  
  public static void Set_Global_Vars()
  {
	  String [] paths;
	  
	  paths=File_Funcs.File_ReturnPaths();
	  
	  if(paths!=null)
	  {
		  if(paths[0]!="" && paths[1]!="")
		  {
			  Global_Java_Path=paths[0];
			  Global_Fortran_Path=paths[1];
		  }
		  else
		  {
			  Error_Funcs.ThrowError_Fatal("SPICEReader.Set_Global_Vars", "Empty default paths.", null);
		  }
	  }
	  else
		  
	  {
		  Error_Funcs.ThrowError_Fatal("SPICEReader.Set_Global_Vars", "Can't get default paths.", null);
	  }
  }




}
