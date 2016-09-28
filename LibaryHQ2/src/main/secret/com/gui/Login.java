package com.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.file.tool.ValiCode;
import com.common.*;

public class Login extends BaseForm implements ActionListener,KeyListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1057276582439429819L;
	private String codeStr = "";
	
	private LoginPanel myPanel = null;
	private JLabel userLabel = null;
	private JLabel pwdLabel = null;
	private JLabel checkCodeLabel = null;
	private JTextField userName = null;
	private JPasswordField pwd = null;
	private JTextField checkCode = null;
	private JButton btnLogin = null;
	private JButton btnCancel = null;
	private JLabel jlbNext = null;
	
	public Login(){
		super("登录", 300, 230, true);
		this.setCenter();
		this.setCanResize(false);
		this.initLoginForm();
	}
	
	public void initLoginForm(){
		this.codeStr = Util.buildValCode();
		this.myPanel = new LoginPanel(ValiCode.createValiCode(this.codeStr));
		this.userLabel = new JLabel(Util.setColor("用户名：","#FFFFFF"));
		this.userLabel.setSize(60,23);
		this.userLabel.setLocation(20,20);
		
		this.pwdLabel = new JLabel(Util.setColor("口  令：","#FFFFFF"));
		this.pwdLabel.setSize(60,23);
		this.pwdLabel.setLocation(20,63);
		
		this.checkCodeLabel = new JLabel(Util.setColor("验证码：","#FFFFFF"));
		this.checkCodeLabel.setSize(60,23);
		this.checkCodeLabel.setLocation(20,106);
		
		this.userName = new JTextField();
		this.userName.setSize(180,23);
		this.userName.setLocation(80,20);
		this.userName.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.pwd = new JPasswordField();
		this.pwd.setSize(180,23);
		this.pwd.setLocation(80,63);
		this.pwd.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.checkCode = new JTextField();
		this.checkCode.setSize(60,23);
		this.checkCode.setLocation(80,106);
		this.checkCode.setBorder(BorderFactory.createLineBorder(Color.black));
		this.checkCode.addKeyListener(this);
		
		this.btnLogin = new JButton("登录");
		this.btnLogin.setSize(60,23);
		this.btnLogin.setLocation(80,149);
		this.btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnLogin.addActionListener(this);
		this.btnLogin.addKeyListener(this);
		
		this.btnCancel = new JButton("关闭");
		this.btnCancel.setSize(60,23);
		this.btnCancel.setLocation(160,149);
		this.btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnCancel.addActionListener(this);
		
		this.jlbNext = new JLabel("<html><font style=\"color:white;\"><u>换一张</u></font></html>");
		this.jlbNext.setSize(40,23);
		this.jlbNext.setLocation(230,106);
		this.jlbNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jlbNext.addMouseListener(this);
		
		this.add(myPanel);
		
		this.myPanel.setBackground(Color.black);
		this.myPanel.setLayout(null);
		this.myPanel.add(this.userLabel);
		this.myPanel.add(this.userName);
		this.myPanel.add(this.pwdLabel);
		this.myPanel.add(this.pwd);
		this.myPanel.add(this.checkCodeLabel);
		this.myPanel.add(this.checkCode);
		this.myPanel.add(this.btnLogin);
		this.myPanel.add(this.btnCancel);
		this.myPanel.add(this.jlbNext);
		this.setVisible(true);
	}
	
	public void login(){
		String userName = this.userName.getText().trim();
		String pwd = String.valueOf(this.pwd.getPassword());
		String valCode = this.checkCode.getText();
		if(userName.equalsIgnoreCase("danny") && pwd.equals("123") && this.codeStr.equalsIgnoreCase(valCode)){
			this.dispose();
			new Main();
		}
	}
	
	
	public static void main(String[] args) {
		new Login();
	}
	
	

	public void actionPerformed(ActionEvent actionevent) {
		// TODO Auto-generated method stub
		if(actionevent.getSource() == this.btnLogin){
			this.login();
		}else if(actionevent.getSource() == this.btnCancel){
			System.exit(-1);
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 10){
			this.login();
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.jlbNext){
			//System.out.println("mouse clicked");
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.jlbNext){
			System.out.println("mouse pressed");
			this.codeStr = Util.buildValCode();
			this.myPanel.setImg(ValiCode.createValiCode(this.codeStr));
			this.myPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

class LoginPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6178201326222257682L;

	public LoginPanel(){
		
	}
	public LoginPanel(BufferedImage img){
		this.img = img;
	}
	private BufferedImage img = null;
	
	public void setImg(BufferedImage img){
		this.img = img;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(null != this.img){
			g.drawImage(this.img, 150, 106, 68, 22,this);
			this.img = null;
		}
	}
}
