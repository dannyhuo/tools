package com.gui.str;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.gui.MyPanel;
//import com.secret.encrypt.Encrypt;

public class Encript extends MyPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2807711490615806803L;
	//行-01
	private JLabel lbFilePath = null;
	private JTextField filePath = null;
	private JButton btnPrivew = null;
	//行-02
	private JLabel lbEncrStyle = null;
	private JRadioButton rbSingle = null;
	private JRadioButton rbFolder = null;
	private JButton btnEncFile = null;
	private JCheckBox cbResOut = null;
	
	//行-03
	private JLabel lbInStr = null;
	private JTextArea jtaEncryptStr = null;
	private JScrollPane jsp1 = null;
	
	//行-04
	private JButton btnEncStr = null;
	
	//行-05
	private JLabel lbOutStr = null;
	private JTextArea jtaDecpheringStr = null;
	private JScrollPane jsp2 = null;

	public Encript() {
		this.setLayout(null);
		this.setBackground(Color.black);
		initForm();
	}

	public void initForm() {
		lbFilePath = new JLabel("<html>路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;径：</html>");
		lbFilePath.setSize(60, 23);
		lbFilePath.setLocation(20,30);
		this.add(lbFilePath);
		
		filePath = new JTextField();
		filePath.setSize(300, 23);
		filePath.setLocation(90, 30);
		filePath.setVisible(true);
		filePath.setBorder(BorderFactory.createEmptyBorder());
		this.add(filePath);

		btnPrivew = new JButton("浏览..");
		btnPrivew.setSize(65, 23);
		btnPrivew.setLocation(400, 30);
		btnPrivew.setFocusable(false);
		btnPrivew.addActionListener(this);
		this.add(btnPrivew);
		
		//行-02
		lbEncrStyle = new JLabel("加密设置：");
		lbEncrStyle.setSize(70,23);
		lbEncrStyle.setLocation(20, 73);
		this.add(lbEncrStyle);
		
		rbSingle = new JRadioButton("单个文件");
		rbSingle.setSize(80,23);
		rbSingle.setLocation(90, 73);
		rbSingle.setBackground(Color.black);
		rbSingle.setFocusable(false);
		rbSingle.setSelected(true);
		this.add(rbSingle);
		
		rbFolder = new JRadioButton("目录文件");
		rbFolder.setSize(80,23);
		rbFolder.setLocation(180, 73);
		rbFolder.setBackground(Color.black);
		rbFolder.setFocusable(false);
		this.add(rbFolder);
		ButtonGroup rdbGrp = new ButtonGroup();
		rdbGrp.add(rbSingle);
		rdbGrp.add(rbFolder);
		
		cbResOut = new JCheckBox("显示结果");
		cbResOut.setSize(80,23);
		cbResOut.setLocation(280,73);
		cbResOut.setBackground(Color.black);
		cbResOut.setFocusable(false);
		cbResOut.setSelected(true);
		this.add(cbResOut);

		btnEncFile = new JButton("加密文件");
		btnEncFile.setSize(100, 23);
		btnEncFile.setLocation(365, 73);
		btnEncFile.setFocusable(false);
		btnEncFile.addActionListener(this);
		this.add(btnEncFile);
		
		//行-03
		lbInStr = new JLabel("输入字符：");
		lbInStr.setSize(80,23);
		lbInStr.setLocation(20,136);
		this.add(lbInStr);
		jtaEncryptStr = new JTextArea(10, 35);
		jtaEncryptStr.setColumns(10);
		jsp1 = new JScrollPane(jtaEncryptStr);
		jsp1.setSize(375, 80);
		jsp1.setLocation(90, 136);
		jsp1.setBorder(BorderFactory.createEmptyBorder());
		this.add(jsp1);

		//行-04
		btnEncStr = new JButton("加密字符");
		btnEncStr.setSize(100,23);
		btnEncStr.setLocation(365,226);
		btnEncStr.setFocusable(false);
		btnEncStr.addActionListener(this);
		this.add(btnEncStr);
		
		//行-05
		lbOutStr = new JLabel("输出结果：");
		lbOutStr.setSize(80,23);
		lbOutStr.setLocation(20,269);
		this.add(lbOutStr);
		jtaDecpheringStr = new JTextArea(10, 35);
		jtaDecpheringStr.setColumns(10);
		jtaDecpheringStr.setEditable(false);
		jsp2 = new JScrollPane(jtaDecpheringStr);
		jsp2.setSize(375, 80);
		jsp2.setLocation(90, 269);
		jsp2.setBorder(BorderFactory.createEmptyBorder());
		this.add(jsp2);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnEncFile){//加密文件
			
		}
		else if(e.getSource() == this.btnEncStr){//加密字符串
			System.out.println("加密字符串");
			String befEncrStr = this.jtaEncryptStr.getText();
			if(!befEncrStr.equals("")){
				//this.jtaDecpheringStr.setText(Encrypt.encryptString(befEncrStr));
			}
		}
	}
}
