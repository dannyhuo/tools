package com.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.file.tool.FileUtil;

public class Ecript extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -189607008666426855L;
	
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
	private JLabel ecrTimesLabel = null;
	private JComboBox ecrTimes = null;
	
	//行--04
	private JLabel ecrStyleLabel = null;
	private JRadioButton jcbStyle1 = null;//密钥
	private JRadioButton jcbStyle2 = null;//原文
	private JRadioButton jcbStyle3 = null;//原文-压缩
	private JRadioButton jcbStyle4 = null;//密钥-压缩
	
	
	//行--05
	private JLabel rnmStyleLebel = null;
	private JRadioButton rnmNoChangeSuffix = null;//后缀不变
	private JRadioButton rnmChangeSuffix = null;//后缀改变
	
	private JButton btnEncript = null;
	private JButton btnBack = null;
	
	public Ecript(){
		this.setLayout(null);
		this.initMyPanel();
		this.setVisible(true);
	}
	
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
		this.toPathLabel = new JLabel("加密到：");
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
		
		//加密后路径文本框
		this.toPath = new JTextField();
		this.toPath.setSize(190,23);
		this.toPath.setLocation(200,63);
		this.toPath.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(this.toPath);
		
		//加密后路径选择器按钮
		this.btnPreview01 = new JButton("选择");
		this.btnPreview01.setSize(70,23);
		this.btnPreview01.setLocation(410,63);
		this.btnPreview01.setFocusable(false);
		this.add(this.btnPreview01);
		this.btnPreview01.addActionListener(this);
		
		/****************************row 03*****************************/
		//加密次数label
		this.ecrTimesLabel = new JLabel("加密次数：");
		this.ecrTimesLabel.setSize(60,23);
		this.ecrTimesLabel.setLocation(30,113);
		this.add(this.ecrTimesLabel);
		//加密次数复选框
		String[] petStrings = {"1","2","3","4","5","6"};
		this.ecrTimes = new JComboBox(petStrings);
		this.ecrTimes.setSize(80,23);
		this.ecrTimes.setLocation(110,113);
		this.ecrTimes.setFocusable(false);
		this.add(this.ecrTimes);
		
		/****************************row 04*****************************/
		this.ecrStyleLabel = new JLabel("加密方式：");
		this.ecrStyleLabel.setSize(80,23);
		this.ecrStyleLabel.setLocation(30,156);
		this.add(this.ecrStyleLabel);
		this.jcbStyle1 = new JRadioButton("密钥");//密钥
		this.jcbStyle1.setSize(60,23);
		this.jcbStyle1.setLocation(120,156);
		this.jcbStyle1.setBackground(Color.black);
		this.jcbStyle1.setFocusable(false);
		this.add(this.jcbStyle1);
		
		this.jcbStyle2 = new JRadioButton("原文");;//原文
		this.jcbStyle2.setSize(60,23);
		this.jcbStyle2.setLocation(200,156);
		this.jcbStyle2.setBackground(Color.black);
		this.jcbStyle2.setSelected(true);
		this.jcbStyle2.setFocusable(false);
		this.add(this.jcbStyle2);
		
		this.jcbStyle3 = new JRadioButton("原文-压缩");//原文-压缩
		this.jcbStyle3.setSize(90,23);
		this.jcbStyle3.setLocation(280,156);
		this.jcbStyle3.setBackground(Color.black);
		this.jcbStyle3.setFocusable(false);
		this.add(this.jcbStyle3);
		
		this.jcbStyle4 = new JRadioButton("密钥-压缩");//密钥-压缩
		this.jcbStyle4.setSize(90,23);
		this.jcbStyle4.setLocation(390,156);
		this.jcbStyle4.setBackground(Color.black);
		this.jcbStyle4.setFocusable(false);
		this.add(this.jcbStyle4);
		ButtonGroup rdbGrp = new ButtonGroup();
		rdbGrp.add(this.jcbStyle1);
		rdbGrp.add(this.jcbStyle2);
		rdbGrp.add(this.jcbStyle3);
		rdbGrp.add(this.jcbStyle4);
		
		/****************************row 05*****************************/
		this.rnmStyleLebel = new JLabel("命名方式：");
		this.rnmStyleLebel.setSize(80,23);
		this.rnmStyleLebel.setLocation(30,199);
		this.add(this.rnmStyleLebel);
		this.rnmNoChangeSuffix = new JRadioButton("后缀不变");//密钥
		this.rnmNoChangeSuffix.setSize(90,23);
		this.rnmNoChangeSuffix.setLocation(120,199);
		this.rnmNoChangeSuffix.setBackground(Color.black);
		this.rnmNoChangeSuffix.setFocusable(false);
		this.add(this.rnmNoChangeSuffix);
		
		this.rnmChangeSuffix = new JRadioButton("改变后缀");//原文
		this.rnmChangeSuffix.setSize(90,23);
		this.rnmChangeSuffix.setLocation(230,199);
		this.rnmChangeSuffix.setBackground(Color.black);
		this.rnmChangeSuffix.setSelected(true);
		this.rnmChangeSuffix.setFocusable(false);
		this.add(this.rnmChangeSuffix);
		ButtonGroup rdbGrp2 = new ButtonGroup();
		rdbGrp2.add(this.rnmNoChangeSuffix);
		rdbGrp2.add(this.rnmChangeSuffix);
		
		/****************************row 06*****************************/
		//加密按钮
		this.btnEncript = new JButton("加密");
		this.btnEncript.setSize(80,23);
		this.btnEncript.setLocation(400,242);
		this.btnEncript.addActionListener(this);
		this.btnEncript.setFocusable(false);
		this.add(this.btnEncript);
		
		this.btnBack = new JButton("返回");
		this.btnBack.setSize(80,23);
		this.btnBack.setLocation(300,242);
		this.btnBack.addActionListener(this);
		this.btnBack.setFocusable(false);
		this.add(this.btnBack);
	}
	
	public void fileChooser() {
		JFileChooser chooser = new JFileChooser();
		/*String[] s = { "txt", "java", "html" };
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt & java & html", s);*/
		//chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.filePath.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnPreview){
			this.fileChooser();
		}else if(e.getSource() == this.btnPreview01){//选择加密目标目录
			this.fileChooser();
		}else if(e.getSource() == this.btnEncript){
			String path = this.filePath.getText();
			if(!"".equals(path.trim())){
				String folder = path.substring(0,path.lastIndexOf("\\"))+"\\ecr"+System.currentTimeMillis();
				FileUtil.mkDir(folder);
				FileUtil.ecrpt(path, folder+"\\"+path.substring(path.lastIndexOf("\\")));
			}
		}else if(e.getSource().equals(this.btnBack)){//返回按钮
			
		}
	}
}
