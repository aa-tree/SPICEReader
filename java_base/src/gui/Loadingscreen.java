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


package src.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import src.gui.Init_Frame;

public class Loadingscreen{

	public Loadingscreen()
	{

		Init_Frame frame_1 = new Init_Frame("SPICEReaderUI - Loading", 3);
		Populate_Frame(frame_1);
		frame_1.setLocationRelativeTo(null);  //

		frame_1.setVisible(true);


		Home_screen home_screen= new Home_screen(frame_1);


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
		
		
		
		BufferedImage myPicture;
		JLabel picLabel=null;
 		try {
			
			myPicture = ImageIO.read(new File("src/gui/img/logo_start.jpg"));
			picLabel = new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
		}
		
		//panel_one.add(sep_vertical, BorderLayout.EAST);
		panel_one.add(lbl_heading, BorderLayout.NORTH);
		panel_one.add(picLabel, BorderLayout.CENTER);
		panel_one.add(progressBar, BorderLayout.SOUTH);
		//frame_1.setUndecorated(true);
		frame_1.add(panel_one);
		
		//frame_1.pack();

	}



}
