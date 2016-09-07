package com.lvmama.comm.pet.po.mobile;

import java.io.Serializable;

/**
 * 标签 /说明
 * @author huoqiang
 *
 */
public class TagMemoPair implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4647464424573088284L;
	
	/**标签名称*/
	private String tagName;
	/**标签说明*/
	private String tagMemo;
	
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagMemo() {
		return tagMemo;
	}
	public void setTagMemo(String tagMemo) {
		this.tagMemo = tagMemo;
	}
}
