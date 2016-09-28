package com.gui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2492143040823283426L;

	public BaseForm(){
		super();
	}
	
	public BaseForm(boolean exitOnClose){
		super();
		if(exitOnClose){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	public BaseForm(String title,int width,int height){
		super();
		this.setVisible(true);
		this.setSize(width,height);
		this.setTitle(title);
		Image icon = Toolkit.getDefaultToolkit().createImage("images/logo_02.png");
		this.setIconImage(icon);
	}
	
	public BaseForm(String title,int width,int height,boolean exitOnClose){
		super();
		this.setVisible(true);
		this.setSize(width,height);
		this.setTitle(title);
		Image icon = Toolkit.getDefaultToolkit().createImage("images/logo_02.png");
		this.setIconImage(icon);
		if(exitOnClose){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	public void setCenter(){
		this.setLocationRelativeTo(null);
	}
	
	public void setCanResize(boolean can){
		this.setResizable(can);
	}
	
	public static void fileChooser(JTextField path, JPanel p) {
		JFileChooser chooser = new JFileChooser();
		/*String[] s = { "txt", "java", "html" };
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt & java & html", s);*/
		//chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(p);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
}
