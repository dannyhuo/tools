package com.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JPanel;


public class Main extends BaseForm implements ActionListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7914235966029781011L;
	
	//private JPanel myPanel = null;
	private MenuBar menuBar = null;
	private Menu menu_01 = null;
	private MenuItem menu_01_01 = null;
	private MenuItem menu_01_02 = null;
	private MenuItem menu_01_03 = null;
	private MenuItem menu_01_04 = null;
	private MenuItem menu_01_05 = null;
	
	private Menu menu_02 = null;
	private MenuItem menu_02_01 = null;
	private MenuItem menu_02_02 = null;
	
	private Menu menu_03 = null;
	private MenuItem menu_03_01 = null;
	
	private Menu helpMenu = null;
	
	private JPanel curPanel = null;
	
	public Main(){
		super("主窗口",520,420,true);
		this.setCenter();
		this.initForm();
		this.setVisible(true);
	}
	
	public void initForm(){
		this.menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		//this.myPanel = new JPanel();
		//文件菜单及其菜单项
		this.menu_01 = new Menu("文件");
		this.menuBar.add(this.menu_01);
		this.menu_01_01 = new MenuItem("加密(E)");
		this.menu_01_02 = new MenuItem("解密(D)");
		this.menu_01_03 = new MenuItem("同步(S)");
		this.menu_01_04 = new MenuItem("差分(L)");
		this.menu_01_05 = new MenuItem("退出(Q)");
		this.menu_01_01.addActionListener(this);
		this.menu_01_02.addActionListener(this);
		this.menu_01_03.addActionListener(this);
		this.menu_01_04.addActionListener(this);
		this.menu_01_05.addActionListener(this);
		this.menu_01.add(this.menu_01_01);
		this.menu_01.add(this.menu_01_02);
		this.menu_01.add(this.menu_01_03);
		this.menu_01.add(this.menu_01_04);
		this.menu_01.add(this.menu_01_05);
		
		//字符菜单及其菜单项
		this.menu_02 = new Menu("字符");
		this.menuBar.add(this.menu_02);
		this.menu_02_01 = new MenuItem("加密(E)");
		this.menu_02_02 = new MenuItem("解密(D)");
		this.menu_02_01.addActionListener(this);
		this.menu_02_02.addActionListener(this);
		this.menu_02.add(this.menu_02_01);
		this.menu_02.add(this.menu_02_02);
		
		//双色球菜单
		this.menu_03 = new Menu("双色球");
		this.menuBar.add(this.menu_03);
		this.menu_03_01 = new MenuItem("摇号");
		this.menu_03_01.addActionListener(this);
		this.menu_03.add(this.menu_03_01);
		
		//帮助菜单
		this.helpMenu = new Menu("帮助");
		this.menuBar.setHelpMenu(this.helpMenu);
		this.helpMenu.addActionListener(this);
		
		//控制面板
		//this.myPanel.setBackground(Color.black);
		//this.add(myPanel);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.menu_01_01){
			if(this.curPanel != null){
				this.remove(this.curPanel);
			}
			this.curPanel = new Encript();
			this.add(this.curPanel);
			this.setVisible(true);
			System.out.println("加密");
		}else if(e.getSource() == this.menu_01_02){
			if(this.curPanel != null){
				this.remove(this.curPanel);
			}
			this.curPanel = new Decript();
			this.add(this.curPanel);
			this.setVisible(true);
			System.out.println("解密");
		}else if(e.getSource() == this.menu_01_03){
			System.out.println("同步");
		}else if(e.getSource() == this.menu_01_04){
			System.out.println("差分");
		}else if(e.getSource() == this.menu_01_05){
			System.out.println("退出");
			System.exit(-1);
		}else if(e.getSource() == this.menu_02_01){
			//字符－加密
			if(this.curPanel != null){
				this.remove(this.curPanel);
			}
			this.curPanel = new com.gui.str.Encript();
			this.add(this.curPanel);
			this.setVisible(true);
		}else if(e.getSource() == this.menu_02_02){
			//		字符－加密
			if(this.curPanel != null){
				this.remove(this.curPanel);
			}
			this.curPanel = new com.gui.str.Decript();
			this.add(this.curPanel);
			this.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		Integer a = null;
		String objectType = "PLACE";
		Integer allCount = 0;
		Integer count = "ALL".equalsIgnoreCase(objectType) ? allCount : a;
		System.out.println(count);
		new Main();
	}
}
