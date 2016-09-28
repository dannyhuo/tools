package com.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.file.tool.FileUtil;

public class Decript extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4654414160913533094L;



	public Decript(){
		this.setLayout(null);
		this.initMyPanel();
	}
	
	//行 --01
	private JLabel pathLabel = null;
	private JTextField filePath = null;
	private JButton btnPreview = null;
	//行--02
	private JLabel toPathLabel = null;
	private JTextField toPath = null;
	private JButton btnPreview01 = null;
	private JCheckBox jcbDftFolder = null;
	
	//行--03
	private JLabel ecrStyleLabel = null;
	private JRadioButton jcbStyle1 = null;//原文
	private JRadioButton jcbStyle2 = null;//密钥
	private JTextField secretCode = null;
	
	//行--04
	private JButton btnDecript = null;
	private JButton btnBack = null;
	
	//行--05
	private JScrollPane jscrPane = null;
	private JTextArea jtpConsole = null;
	
	
	
	public void initMyPanel(){
		this.setBackground(Color.black);//设置背景色
		/****************************row 01*****************************/
		//路径Label
		this.pathLabel = new JLabel("路  径：");
		this.pathLabel.setSize(60,23);
		this.pathLabel.setLocation(30,20);
		this.add(this.pathLabel);
		//路径文本框
		this.filePath = new JTextField();
		this.filePath.setSize(280,23);
		this.filePath.setLocation(110,20);
		this.filePath.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(this.filePath);
		//浏览按钮
		this.btnPreview = new JButton("浏览…");
		this.btnPreview.setSize(70,23);
		this.btnPreview.setLocation(410,20);
		this.btnPreview.setFocusable(false);
		this.add(this.btnPreview);
		this.btnPreview.addActionListener(this);
		
		/****************************row 02*****************************/
		//加密后的路径Label
		this.toPathLabel = new JLabel("解密到：");
		this.toPathLabel.setSize(60,23);
		this.toPathLabel.setLocation(30,63);
		this.add(this.toPathLabel);
		//是否默认目录
		this.jcbDftFolder = new JCheckBox("默认目录");
		this.jcbDftFolder.setSize(80,23);
		this.jcbDftFolder.setLocation(110,63);
		this.jcbDftFolder.setBackground(Color.black);
		this.jcbDftFolder.setSelected(true);
		this.jcbDftFolder.setFocusable(false);
		this.add(this.jcbDftFolder);
		this.jcbDftFolder.addActionListener(this);
		
		//加密后路径文本框
		this.toPath = new JTextField();
		this.toPath.setSize(190,23);
		this.toPath.setLocation(200,63);
		this.toPath.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.toPath.setEnabled(false);
		this.add(this.toPath);
		
		//加密后路径选择器按钮
		this.btnPreview01 = new JButton("选择");
		this.btnPreview01.setSize(70,23);
		this.btnPreview01.setLocation(410,63);
		this.btnPreview01.setFocusable(false);
		this.btnPreview01.setEnabled(false);
		this.add(this.btnPreview01);
		this.btnPreview01.addActionListener(this);
		
		/****************************row 03*****************************/
		this.ecrStyleLabel = new JLabel("解密方式：");
		this.ecrStyleLabel.setSize(80,23);
		this.ecrStyleLabel.setLocation(30,113);
		this.add(this.ecrStyleLabel);
		this.jcbStyle1 = new JRadioButton("自动");//自动
		this.jcbStyle1.setSize(60,23);
		this.jcbStyle1.setLocation(120,113);
		this.jcbStyle1.setBackground(Color.black);
		this.jcbStyle1.setSelected(true);
		this.jcbStyle1.setFocusable(false);
		this.jcbStyle1.addActionListener(this);
		this.add(this.jcbStyle1);
		
		this.jcbStyle2 = new JRadioButton("密钥");//密钥
		this.jcbStyle2.setSize(60,23);
		this.jcbStyle2.setLocation(200,113);
		this.jcbStyle2.setBackground(Color.black);
		this.jcbStyle2.setFocusable(false);
		this.jcbStyle2.addActionListener(this);
		
		this.add(this.jcbStyle2);
		ButtonGroup rdbGrp = new ButtonGroup();
		rdbGrp.add(this.jcbStyle1);
		rdbGrp.add(this.jcbStyle2);
		
		this.secretCode = new JTextField();
		this.secretCode.setSize(210,23);
		this.secretCode.setLocation(270,113);
		this.secretCode.setBorder(BorderFactory.createLineBorder(Color.black));
		this.secretCode.setEnabled(false);
		this.add(this.secretCode);
		
		
		/****************************row 04*****************************/
		//解密按钮
		this.btnDecript = new JButton("解密");
		this.btnDecript.setSize(80,23);
		this.btnDecript.setLocation(400,156);
		this.btnDecript.addActionListener(this);
		this.btnDecript.setFocusable(false);
		this.add(this.btnDecript);
		
		this.btnBack = new JButton("返回主页");
		this.btnBack.setSize(80,23);
		this.btnBack.setLocation(300,156);
		this.btnBack.addActionListener(this);
		this.btnBack.setFocusable(false);
		this.add(this.btnBack);
		
		/****************************row 05*****************************/
		//控制台
		this.jtpConsole = new JTextArea();
		this.jtpConsole.setEditable(false);
		this.jscrPane = new JScrollPane(this.jtpConsole);
		this.jscrPane.setSize(450,120);
		this.jscrPane.setLocation(30,199);
		this.jscrPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(this.jscrPane);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnPreview){
			
			BaseForm.fileChooser(this.filePath,this);
		}else if(e.getSource() == this.jcbDftFolder){//禁/起用目录目录和选择按钮
			if(this.jcbDftFolder.isSelected()){
				this.toPath.setEnabled(false);
				this.btnPreview01.setEnabled(false);
			}else{
				this.toPath.setEnabled(true);
				this.btnPreview01.setEnabled(true);
			}
		}else if(e.getSource() == this.btnPreview01){//选择加密目标目录
			BaseForm.fileChooser(this.toPath,this);
		}else if(e.getSource() == this.jcbStyle2 || e.getSource() == this.jcbStyle1){
			if(this.jcbStyle2.isSelected()){
				this.secretCode.setEnabled(true);
				this.secretCode.grabFocus();
			}else{
				this.secretCode.setEnabled(false);
			}
		}else if(e.getSource() == this.btnDecript){
			String path = this.filePath.getText();
			if(!"".equals(path.trim())){
				String folder = path.substring(0,path.lastIndexOf("\\"))+"\\ecr"+System.currentTimeMillis();
				FileUtil.mkDir(folder);
				FileUtil.dcrpt(path, folder+"\\"+path.substring(path.lastIndexOf("\\")));
			}
		}else if(e.getSource().equals(this.btnBack)){//返回按钮
			for(int i = 0; i < 50; i++){
				this.jtpConsole.setText(this.jtpConsole.getText() + "你好啊，这是测试数据\n");
			}
		}
	}

}
