package com.gui;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5425155844345315393L;

	/**
	 * 文件选择器
	 * @param path
	 * @param p
	 */
	public void fileChooser(JTextField path, JPanel p) {
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
