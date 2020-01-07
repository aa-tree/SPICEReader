package src.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Home_screen {
	
	private Init_Frame frame_home_common;
	public Home_screen(Init_Frame loading_frame)
	{
		Init_Frame frame_home = new Init_Frame("SPICEReaderUI - Home Screen", 2);
		frame_home_common=frame_home;
		
		loading_frame.dispose();
		Populate_Frame(frame_home);
		frame_home.setLocationRelativeTo(null);  //

		frame_home.setVisible(true);

	}
	protected void Populate_Frame(Init_Frame frame_1)
	{
		JPanel panel_one = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel_one, BoxLayout.Y_AXIS);
        
        panel_one.setBorder(new EmptyBorder(40, 40, 40, 40));
        panel_one.setLayout(boxlayout);

        
        
        
		JLabel lbl_choose_option= new JLabel("Choose an Option:");
		lbl_choose_option.setAlignmentX(Component.CENTER_ALIGNMENT);
		//JLabel get_state_vector = new JLabel();
		
		//get_state_vector.setText("Get State Vectors");
		
		JButton get_state_button = new JButton();
		get_state_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		get_state_button.setText("Get State Vectors");
		
		
		
		get_state_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OnClick_Get_StateVector();				
			}
			
		});
	
		//get_state_button.setPreferredSize(new Dimension(100, 40));
		
		
		//panel_one.add(lbl_heading);
		
		
		//panel_one.add(get_state_vector);
		panel_one.add(lbl_choose_option);
		panel_one.add(Box.createRigidArea(new Dimension(10, 30)));
		panel_one.add(get_state_button);
		frame_1.add(panel_one);

	}
	
	
	public void OnClick_Get_StateVector()
	{
		Get_StateVectors go_getstate= new Get_StateVectors();
		
		if(frame_home_common!=null)
		{
			frame_home_common.dispose();
		}
		
	}
}
