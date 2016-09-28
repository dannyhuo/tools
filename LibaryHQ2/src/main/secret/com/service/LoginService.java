package com.service;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.common.Constant;

public class LoginService {
	public boolean login(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(Constant.USER_DATA_URI);
			NodeList n = doc.getChildNodes().item(0).getChildNodes();
			int len = n.getLength();
			for(int i = 0; i < len; i++){
				Node item = n.item(i);
				String content = item.getTextContent();
				String nodeName = item.getNodeName();
				String nodeValue = item.getNodeValue();
				short nodeType = item.getNodeType();
				NamedNodeMap attr = item.getAttributes();
				String s = "";
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args){
		new LoginService().login();
	}
}
