package src.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import src.Init_Frame;

public class Loadingscreen{

	public Loadingscreen()
	{

		Init_Frame frame_1 = new Init_Frame("SPICEReaderUI - Loading", 3);
		frame_1.setVisible(true);
		Populate_Frame(frame_1);






	}

	protected void Populate_Frame(Init_Frame frame_1)
	{
		JPanel panel_one = new JPanel();
		panel_one.setLayout(new BorderLayout());

		JLabel lbl_heading = new JLabel();
		
		lbl_heading.setText("SpiceLib Reader");
		lbl_heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		JSeparator sep_vertical = new JSeparator(); 
		JSeparator sep_horizontal = new JSeparator();
		sep_vertical.setOrientation(SwingConstants.VERTICAL);
		sep_vertical.setSize(400, 1000);
		sep_horizontal.setOrientation(SwingConstants.HORIZONTAL);
		sep_horizontal.setSize(1000, 400);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		
		
		
		
		//panel_one.add(sep_vertical, BorderLayout.WEST);
		//panel_one.add(sep_horizontal, BorderLayout.NORTH);
		//panel_one.add(sep_vertical, BorderLayout.EAST);
		panel_one.add(lbl_heading, BorderLayout.CENTER);
		panel_one.add(progressBar, BorderLayout.SOUTH);
		//frame_1.setUndecorated(true);
		frame_1.add(panel_one);
		
		frame_1.pack();

	}



}
