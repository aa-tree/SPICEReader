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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Get_StateVectors {
	
	public Get_StateVectors()
	{
		  Init_Frame frame_1 = new Init_Frame("SPICEReaderUI - Get State Vectors");
		  frame_1.setVisible(true);
		  Populate_Frame(frame_1);
		  
	
	}
	
	protected void Populate_Frame(Init_Frame frame_1)
	{
		  JPanel panel_one = new JPanel();
		  panel_one.setLayout(new BoxLayout(panel_one, BoxLayout.Y_AXIS));
		  
		  JLabel lbl_heading = new JLabel();
		  //lbl_heading.left
		  lbl_heading.setText("Get State Vectors from SPICELib");
		  lbl_heading.setFont(new Font("Times New Roman", Font.BOLD, 16));
		  
		 		  
		  JLabel lbl_1= new JLabel();
		  lbl_1.setText("Get Vector for:");
		  
		  String [][] bodies_list;
		  bodies_list=Common_Funcs.Read_Model_ReturnBodies_Sc();
		  
		  DefaultListModel <String> model1 = new DefaultListModel<>();  
		  
		  
		  
		  for(int i=0;i<bodies_list.length;i++)
		  {
			  model1.add(i, bodies_list[i][0]+"("+bodies_list[i][1]+")");
			  
			  
		  }
		  
		  
		  JList<String> list_bodies = new JList<>(model1);
		  list_bodies.setVisibleRowCount(6);
		  JScrollPane list_bodies_sp = new JScrollPane(list_bodies);

		  JLabel lbl_datePick= new JLabel("Pick a date and Time:");

		  
		  JPanel panel_two= new JPanel();
		  panel_two.setLayout(new BoxLayout(panel_two, BoxLayout.X_AXIS));
		  
		  JLabel lbl_datePick_2= new JLabel("DD/MM/YYYY");
		  
		  
		  
		  
		  JTextField data_Field =new JTextField();
		  JTextField Time_Field = new JTextField();
		  data_Field.setInputVerifier(new InputVerifier_Date());
		  
		  
		  Time_Field.setInputVerifier(new InputVerifier_Time());
		  
		  
		  
		  panel_two.add(new JLabel("Date (DD/MM/YYYY)"));
		  panel_two.add(Box.createRigidArea(new Dimension(10,10)));
		  panel_two.add(data_Field);
		  panel_two.add(new JLabel("Time (24Hr) (HH:MM:SS)"));
		  panel_two.add(Time_Field);

		  JList<String> list_bodies_obs = new JList<>(model1);
		  list_bodies_obs.setVisibleRowCount(8);
		  JScrollPane list_bodies_obs_sp = new JScrollPane(list_bodies_obs);



		  			 
		   
		  JButton btn_getstate=new JButton("Get State");
		  btn_getstate.addActionListener(new ActionListener () {
				@Override

			  public void actionPerformed(ActionEvent e) {
				  String action_data;
				  action_data= list_bodies.getSelectedValue()+"##";
				  action_data += list_bodies_obs.getSelectedValue()+"##";
				  
				  action_data += data_Field.getText()+"##";
				  action_data += Time_Field.getText();
				  Get_State_VerifyInput(action_data);

			  }
			  
			  
		  });		  
		  
		  
		  panel_one.setBorder(new EmptyBorder(10, 10, 10, 10));
		  panel_one.add(lbl_heading);
		  panel_one.add(Box.createRigidArea(new Dimension(10,10)));
		  panel_one.add(lbl_1);
		  panel_one.add(list_bodies_sp);
		  panel_one.add(lbl_datePick);
		  panel_one.add(panel_two);
		  panel_one.add(new JLabel("Frame: J2000."));
		  panel_one.add(new JLabel("Select the observing body:"));
		  panel_one.add(list_bodies_obs_sp);
		  panel_one.add(btn_getstate);
		  
		  
		  frame_1.add(panel_one);
		  //frame_1.add(panel_two);
		  

	}


	
	public void Get_State_VerifyInput(String action_received)
	{
		String date;
		
		Integer i;
		
		System.out.println(action_received);


	}

	

}
