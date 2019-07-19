/*
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
*/

package src;

public class SPICEReader
{

  public static void main (String args[])
  {


	  if(Process_Args.Args_INIT(args))
	  {
		  System.out.println("CLI Requested.");
		  System.out.println(File_Funcs.Generate_TMP_Folder_Return_Path());
		  Process_Args.Args_Process_Rest(args);
	  }
	  else
	  {
		  Get_StateVectors get_sv = new Get_StateVectors();

	  }

  }




}
