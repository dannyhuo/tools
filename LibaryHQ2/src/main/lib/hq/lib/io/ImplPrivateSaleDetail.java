package vst.privatesale.detailpage;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;

import interactionImpl.Operate;
import tools.Tool;
import vst.frontend.search.FrontendSearchObjects;
import access.AccessExcel;

public class ImplPrivateSaleDetail {
	public final static long TIME_OUT = 30;
    public final static String XPATH = "xpath";
    public final static String CLASSNAME = "className";
    public final static String ID = "id";
    public final static String LINKTEXT = "linktext";
    public final static String NAME = "name";
    public final static int DEFVALUE = 0;
	AccessExcel accessExcel = new AccessExcel();
	FrontendSearchObjects obj = new FrontendSearchObjects();
    Tool tool = new Tool();
    
    Operate driver;

   	public ImplPrivateSaleDetail(String type,String browser,String url){
   		driver = new Operate(type,browser,url);	
   	}
   	
   	public void openPageAndMaximize(String url, String browser) {
		driver.navigateTo(url);
		driver.windowMaximize(browser);
	}
   	/**
   	 * 鍓嶅彴鐧诲綍
   	 * @param userame
   	 * @param password
   	 */
   	public void frontlogin(String userame,String password){
//   	鐐瑰嚮璇风櫥褰�
		driver.waitForAllElementLoad(XPATH,".//*[@id='top_login']", DEFVALUE, TIME_OUT);
   		driver.click(XPATH, ".//*[@id='top_login']", DEFVALUE);
//   	杈撳叆鐢ㄦ埛鍚�
		driver.waitForAllElementLoad(NAME, "username", DEFVALUE, TIME_OUT);
		driver.input(NAME, "username", DEFVALUE, userame);
//		杈撳叆瀵嗙爜
		driver.waitForAllElementLoad(NAME, "password", DEFVALUE, TIME_OUT);
		driver.input(NAME, "password", DEFVALUE, password);		
		if(driver.isElementPresent(NAME, "", DEFVALUE)){
			driver.excuteJS("$.ajax({type:'GET',url:'http://login.lvmama.com/nsso/ajax/getAuthenticationCodeForInternalTest.do',dataType:'json',async:false,success: function(data){document.getElementsByName(\"verifycode\")[0].value=data.errorText;}});");
		}
//		鐐瑰嚮鐧诲綍
		driver.click(ID, "loginButton", DEFVALUE);
   	}
   	
   	/**
   	 * 鏈櫥褰曪紝鎻愮ず鐧诲綍妗嗙櫥闄�
   	 * @param userame
   	 * @param password
   	 */
   	public void Login(String userame,String password){
//   	鐐瑰嚮鐢ㄦ埛鍚嶇櫥闄唗ab
		driver.waitForAllElementLoad(XPATH,"//li[text()='鐢ㄦ埛鍚嶇櫥褰�']", DEFVALUE, TIME_OUT);
   		driver.click(XPATH, "//li[text()='鐢ㄦ埛鍚嶇櫥褰�']", DEFVALUE);
//   	杈撳叆鐢ㄦ埛鍚�
		driver.waitForAllElementLoad(NAME, "mobileLoginText2", DEFVALUE, TIME_OUT);
		driver.input(NAME, "mobileLoginText2", DEFVALUE, userame);
//		杈撳叆瀵嗙爜
		driver.waitForAllElementLoad(XPATH, ".//*[@id='userpassword']", DEFVALUE, TIME_OUT);
		driver.input(XPATH, ".//*[@id='userpassword']", DEFVALUE, password);		
//		鐐瑰嚮鐧诲綍
		driver.waitForAllElementLoad(ID, "user_login", DEFVALUE, TIME_OUT);
		driver.click(ID, "user_login", DEFVALUE);
   	}
   	
   	/**
   	 * 鍚庡彴鐧诲綍
   	 * @param userName
   	 * @param passWord
   	 */
   	private void backlogin(String userName, String passWord) {
   		driver.navigateTo("http://super.lvmama.com/pet_back/login.do");
		driver.windowMaximize();
//		鐢ㄦ埛鍚�
		driver.waitForAllElementLoad(XPATH, "//input[@name='user.userName']", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//input[@name='user.userName']", DEFVALUE, userName);
//		瀵嗙爜
		driver.waitForAllElementLoad(XPATH, "//input[@name='user.password']", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//input[@name='user.password']", DEFVALUE, passWord);
//		鐐瑰嚮鐧诲綍
		driver.waitForAllElementLoad(XPATH, ".//*[@id='submitBtn']", DEFVALUE, TIME_OUT);
		driver.click(XPATH,".//*[@id='submitBtn']", DEFVALUE);	
	}
   	
 	public int selectDate(){
 		int leftNum=-1;
		OK:
		for(int k=1;k<=8;k++){
			int calMonthNum=driver.getTotalElementList2("//div[@class='calwrap clearfix']/div");//鏈堟暟
			for(int m=1;m<=calMonthNum;m++){
				driver.waitForAllElementLoad(XPATH, "//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody", DEFVALUE, TIME_OUT);
				int tr = driver.getTotalElementList("//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody", "tr");
				
				for(int i=1;i<=tr;i++){
					int td = driver.getTotalElementList("//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody/tr["+tr+"]", "td");			
					
					for(int j=1;j<=td;j++){	
						if(driver.isElementPresent(XPATH, "//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div/div[@class='calinfo']", DEFVALUE)){
							String text = driver.getText(XPATH, "//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div/div[@class='calinfo']", DEFVALUE);
							
							if(text.contains("鍏呰冻")||text.contains("浣�")){
								driver.waitForAllElementLoad(XPATH, "//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div", DEFVALUE, TIME_OUT);
								driver.excuteJS("arguments[0].click()", driver.getTotalElementList3("//div[@class='calwrap clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div").get(0));
								break OK;
							}
						}
					}					
				}
			}
			if(driver.isElementPresent(XPATH, "//span[@class='month-next']", DEFVALUE)){
				driver.click(XPATH, "//span[@class='month-next']", DEFVALUE);
			}
		}
		return leftNum;
	}
 	public int selectDate1(){
 		int leftNum=-1;
		OK:
		for(int k=1;k<=8;k++){
			int calMonthNum=driver.getTotalElementList2("//div[@class='calwarp clearfix']/div");//鏈堟暟
			for(int m=1;m<=calMonthNum;m++){
				driver.waitForAllElementLoad(XPATH, "//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody", DEFVALUE, TIME_OUT);
				int tr = driver.getTotalElementList("//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody", "tr");
				
				for(int i=1;i<=tr;i++){
					int td = driver.getTotalElementList("//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody/tr["+tr+"]", "td");			
					
					for(int j=1;j<=td;j++){	
						if(driver.isElementPresent(XPATH, "//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div/span[@class='calinfo']", DEFVALUE)){
							String text = driver.getText(XPATH, "//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div/span[@class='calinfo']", DEFVALUE);
							
							if(text.contains("鍏呰冻")||text.contains("浣�")){
								driver.waitForAllElementLoad(XPATH, "//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div", DEFVALUE, TIME_OUT);
								driver.excuteJS("arguments[0].click()", driver.getTotalElementList3("//div[@class='calwarp clearfix']/div["+m+"]/div[2]/table/tbody/tr["+i+"]/td["+j+"]/div").get(0));
								break OK;
							}
						}
					}					
				}
			}
			if(driver.isElementPresent(XPATH, "//span[@class='month-next']", DEFVALUE)){
				driver.click(XPATH, "//span[@class='month-next']", DEFVALUE);
			}
		}
		return leftNum;
	}
   	/**
   	 *鐗瑰崠浼氾細缁勫悎濂楅绁� 
   	 */
   	public boolean case04942(){
   		driver.waitForAllElementLoad(LINKTEXT,"鏌ョ湅鍏ㄩ儴鏅尯", DEFVALUE, TIME_OUT);
   		driver.click(LINKTEXT,"鏌ョ湅鍏ㄩ儴鏅尯", DEFVALUE);
   		if(driver.isElementPresent(LINKTEXT,"鏀惰捣鍏ㄩ儴鏅尯", DEFVALUE)){   			
   			System.out.println("楠岃瘉閫氳繃");
   			return true;
   		}else{
   			return false;
   		}
   	}
  
 	/**
   	 *缁勫悎濂楅绁�:浜у搧璇︽儏椤甸璁㈤』鐭ユ樉绀鸿浜у搧鐨勬墍鏈夊椁�
   	 */
   	public boolean case04940(){
   		driver.waitForAllElementLoad(ID,"policy", DEFVALUE, TIME_OUT);
   		int num = driver.getTotalElementList2("//div[@class='dcontent policy']//li");
   		if(num == 2){   			
   			System.out.println("棰勮椤荤煡宸叉樉绀烘墍鏈夊椁�");
   			return true;
   		}else{
   			System.out.println("棰勮椤荤煡鏈樉绀烘墍鏈夊椁�");
   			return false;
   		}
   	}
   	
 	/**
   	 *缁勫悎濂楅绁�:浜у搧璇︽儏椤佃垂鐢ㄨ鏄庢樉绀鸿浜у搧鐨勬墍鏈夊椁�
   	 */
   	public boolean case04938(){
   		driver.waitForAllElementLoad(ID,"feiyong", DEFVALUE, TIME_OUT);
   		int num = driver.getTotalElementList2("//div[@class='feiyong_all detail-info']//li");
   		if(num == 2){   			
   			System.out.println("璐圭敤璇存槑宸叉樉绀烘墍鏈夊椁�");
   			return true;
   		}else{
   			System.out.println("璐圭敤璇存槑鏈樉绀烘墍鏈夊椁�");
   			return false;
   		}
   	}
   	/**
   	 *缁勫悎濂楅绁�:浜у搧璇︽儏椤靛寘鍚垂鐢ㄥ寘鍚�侀瀹氶』鐭ャ�佹椿鍔ㄤ粙缁嶃�佹櫙鍖轰粙缁嶃�佷氦閫氭寚鍗椼�侀┐鍙嬬偣璇�
   	 */
   	public boolean case04937(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='destorder']/div/ul/li[1]", DEFVALUE, TIME_OUT);
   		String text = "";
   		for(int i = 1;i<7;i++){
   			String str = driver.getText(XPATH, "//div[@id='destorder']/div/ul/li["+i+"]", DEFVALUE);
   			text +=str+"銆�"; 
   			System.out.println(text);
   		}
   		text = text.substring(0, text.length()-8);
   		System.out.println(text);
   		if(text.equals("璐圭敤璇存槑銆侀瀹氶』鐭ャ�佹椿鍔ㄤ粙缁嶃�佹櫙鍖轰粙缁嶃�佷氦閫氭寚鍗椼�侀┐鍙嬬偣璇�")){   			
   			System.out.println("楠岃瘉閫氳繃");
   			return true;
   		}else{
   			System.out.println("楠岃瘉澶辫触");
   			return false;
   		}
   	}
	/**
   	 *缁勫悎濂楅绁�:浠锋牸鏃ュ巻琛ㄧ炕椤垫寜閽紝鑷敱鍒囨崲锛涚偣鍑诲悜涓婄殑绠ご锛岀炕椤靛埌涓婁竴涓湀锛涚偣鍑诲悜涓嬬殑绠ご锛岀炕椤靛埌涓嬩竴涓湀 	
   	 */
   	public boolean case04932(){
   		driver.waitForAllElementLoad(XPATH,"//div[@class='ui-calendar']", DEFVALUE, TIME_OUT);
   		if(driver.isElementPresent(XPATH, "//div[@class='ui-calendar']/span[2]", DEFVALUE)){
   			driver.click(XPATH, "//div[@class='ui-calendar']/span[2]", DEFVALUE);
   			String str = driver.getText(XPATH, "//div[@class='caltitle']/span", DEFVALUE);
   			if(str.contains("9鏈�")){
   				System.out.println("宸茬炕鍒颁笅鏈�");
   				return true;
   			}else{
   				System.out.println("鏈炕鍒颁笅鏈�");
   				return false;
   			}  			
   		}
   		System.out.println("鏃犱笅鏈堟寜閽�");
   		return false;
   		
   	}
   	
   	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 閫夋嫨鍑哄彂鏃ユ湡锛岀偣鍑荤珛鍗虫姠璐紝杩涘叆涓嬪崟椤�
   	 */
   	public boolean case04934(){
   		driver.waitForAllElementLoad(XPATH,"//div[@class='ui-calendar']", DEFVALUE, TIME_OUT);
   		selectDate();//閫夋嫨鍑烘父鏃ユ湡   		
   		if(driver.isElementPresent(XPATH, "//span[@class='product_btn fl']", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH, "//span[@class='product_btn fl']", DEFVALUE, TIME_OUT);
			driver.click(XPATH, "//span[@class='product_btn fl']", DEFVALUE);//鐐瑰嚮绔嬪嵆鎶㈣喘鎸夐挳
		}
   		driver.waitForAllElementLoad(XPATH, "//div[@class='user-tab-box']/ul/li[2]", DEFVALUE, TIME_OUT);
   		driver.click(XPATH, "//div[@class='user-tab-box']/ul/li[2]", DEFVALUE);
   		driver.waitForAllElementLoad(ID, "mobileLoginText2", DEFVALUE, TIME_OUT);
		driver.input(ID, "mobileLoginText2", DEFVALUE, "loadtest001190");
		driver.waitForAllElementLoad(ID, "userpassword", DEFVALUE, TIME_OUT);
		driver.input(ID, "userpassword", DEFVALUE, "111111");	
		driver.click(ID, "user_login", DEFVALUE);
		
		driver.waitForAllElementLoad(XPATH, "//div[@class='order_box border_3']/dl[1]/dt", DEFVALUE, DEFVALUE);
        String str = driver.getText(XPATH, "//div[@class='order_box border_3']/dl[1]/dt", DEFVALUE);
   		if(str.equals("娓哥帺鏃ユ湡")){
   				System.out.println("宸茶繘鍏ヤ笅鍗曢〉");
   				return true;
   			}else{
   				System.out.println("鏈繘鍏ヤ笅鍗曢〉");
   				return false;
   			}  				
   	}
   	
   	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉: 鏄剧ず璇︽儏鐨勬櫙鍖哄湴鍧�
   	 */
   	public boolean case04927(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='buyPlace']/dl[1]/dd", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/dl[1]/dd", DEFVALUE);
   		if(str != null && str !=""){
   				System.out.println("鏅尯鍦板潃宸叉樉绀�");
   				return true;
   			}else{
   				System.out.println("鏅尯鍦板潃鏈樉绀�");
   				return false;
   			}  			
    }
   	
 	/**
   	 *缁勫悎濂楅绁�: 浼樻儬鏍囩鏄剧ず鐐硅瘎杩旂幇銆佹墜鏈鸿繑鐜般�佹父璁拌繑鐜颁笁涓爣绛�
   	 */
   	public boolean case04926(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='buyPlace']/div[5]/span]", DEFVALUE, TIME_OUT);
   		String str = "";
   		for(int i = 1 ;i<=3;i++){
   			if(driver.isElementPresent(XPATH, "//div[@class='zIndex5']", DEFVALUE)){//濡傛灉鏈変績閿�锛屽垯浠庣浜斾釜div寮�濮嬶紝鍚﹀垯浠庣鍥涗釜寮�濮�
   				if(driver.isElementPresent(XPATH, "//div[@id='buyPlace']/div[5]/span["+i+"]/em", DEFVALUE)){
   					str += driver.getText(XPATH, "//div[@id='buyPlace']/div[5]/span["+i+"]/em", DEFVALUE);//鑾峰彇浼樻儬鏍囩 
   					System.out.println(str);
   				}   				  
   			}else{
   				if(driver.isElementPresent(XPATH, "//div[@id='buyPlace']/div[4]/span["+i+"]/em", DEFVALUE)){
   					str = driver.getText(XPATH, "//div[@id='buyPlace']/div[4]/span["+i+"]/em", DEFVALUE);//鑾峰彇浼樻儬鏍囩 
   				}
   			}			 				 	   	 		
   		}
   		if(str.contains("鐐硅瘎") || str.contains("娓歌杩旂幇") || str.contains("鎵嬫満杩旂幇")){
			System.out.println("浼樻儬鏍囩鏄剧ず姝ｅ父");
			return true;
		}else if(str == ""){
			System.out.println("鍚庡彴鏈缃紭鎯犳爣绛�");
			return true;
		}else{
			System.out.println("浼樻儬鏍囩鏄剧ず涓嶆甯�");
			return false;
		}
	}
   	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 宸叉姠鏄剧ず鏁伴噺锛� 鍥㈣喘鏄剧ず鍘嗗彶绱閿�閲忥紝绉掓潃鏄剧ず褰撴娲诲姩閿�閲忥紝娲诲姩鏈紑濮嬫樉绀�0	
   	 */
   	public boolean case04923(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/div[3]//em", DEFVALUE);
   		int num = Integer.parseInt(str);
   		if(num == 0){
   			System.out.println("娲诲姩鏈紑濮�");
   			return true;
   		}else if(num > 0){
			System.out.println("娲诲姩宸插紑濮�");
			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
 	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 璧蜂环鍗曚綅锛氳捣/浜烘垨鑰呰捣/浠�	
   	 */
   	public boolean case04917(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/div[2]/div/span[2]", DEFVALUE);
   		if(str.contains("璧�/浜�") || str.contains("璧�/浠�") ){
   			System.out.println("鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
   	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 鐗瑰崠鏍囩锛氱/鍥紝绉掓潃鏄剧ず绉掞紝鍥㈣喘鏄剧ず鍥�
   	 */
   	public boolean case04916(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String className = driver.getgetAttributeValue("//div[@id='buyPlace']/div[2]/div/i", "class");
   		if(className.contains("tuangou")){
   			System.out.println("鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
  	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 鏄剧ず浜у搧鐨勫ソ璇勭巼锛屾爣绛�
   	 */
   	public boolean case04914(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String favorableRate = driver.getText(XPATH, "//div[@id='buyPlace']/div[1]//span", DEFVALUE);
   		String favorable = driver.getText(XPATH, "//div[@id='buyPlace']/div[1]//a", DEFVALUE);
   		if(favorable.contains("濂借瘎") && favorableRate.endsWith("%")){
   			System.out.println("濂借瘎鐜囨樉绀烘甯�");
   			return true;
   		}else{
   			System.out.println("濂借瘎鐜囨樉绀轰笉姝ｅ父");
			return false;
   		}  			
    }
   	
   	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 鏄剧ず浜у搧缂栧彿	
   	 */
   	public boolean case04913(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(CLASSNAME, "product_info1", DEFVALUE);
   		System.out.println(str);
   		if(str.contains("浜у搧缂栧彿锛�667139")){
   			System.out.println("浜у搧缂栧彿鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("浜у搧缂栧彿鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
  	/**
   	 *缁勫悎濂楅绁ㄨ鎯呴〉锛� 鏄剧ず鍝佺被鏍囩锛屽搧绫诲悓鐗瑰崠绾夸笂锛屽搧绫绘灇涓撅細闂ㄧエ
   	 */
   	public boolean case04910(){
   		driver.waitForAllElementLoad(ID,"//div[@class='wrap']", DEFVALUE, TIME_OUT);   	
   		if(driver.isElementPresent(XPATH, "//div[@class='wrap']/div[2]/i", DEFVALUE)){
   			System.out.println("鍝佺被鏍囩鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鍝佺被鏍囩鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
  	/**
   	 *鍗曢棬绁ㄨ鎯呴〉:浜у搧璇︽儏椤靛寘鍚細璐圭敤鍖呭惈銆侀璁㈤』鐭ャ�佹椿鍔ㄤ粙缁嶃�佹櫙鍖轰粙缁嶃�佷氦閫氭寚鍗椼�侀┐鍙嬬偣璇�
   	 */
   	public boolean case04903(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='destorder']/div/ul/li[1]", DEFVALUE, TIME_OUT);
   		String text = "";
   		for(int i = 1;i<7;i++){
   			String str = driver.getText(XPATH, "//div[@id='destorder']/div/ul/li["+i+"]", DEFVALUE);
   			text +=str+"銆�"; 
   			System.out.println(text);
   		}
   		text = text.substring(0, text.length()-9);
   		System.out.println(text);
   		if(text.equals("璐圭敤璇存槑銆侀瀹氶』鐭ャ�佹椿鍔ㄤ粙缁嶃�佹櫙鍖轰粙缁嶃�佷氦閫氭寚鍗椼�侀┐鍙嬬偣璇�")){   			
   			System.out.println("楠岃瘉閫氳繃");
   			return true;
   		}else{
   			System.out.println("楠岃瘉澶辫触");
   			return false;
   		}
   	}
   	
   	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 閫夋嫨鍑哄彂鏃ユ湡锛岀偣鍑荤珛鍗虫姠璐紝杩涘叆涓嬪崟椤�
   	 */
   	public boolean case04900(){
   		driver.waitForAllElementLoad(XPATH,"//div[@class='ui-calendar']", DEFVALUE, TIME_OUT);
   		selectDate();//閫夋嫨鍑烘父鏃ユ湡   		
   		if(driver.isElementPresent(XPATH, "//span[@class='product_btn fl']", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH, "//span[@class='product_btn fl']", DEFVALUE, TIME_OUT);
			driver.click(XPATH, "//span[@class='product_btn fl']", DEFVALUE);//鐐瑰嚮绔嬪嵆鎶㈣喘鎸夐挳
		}
   		driver.waitForAllElementLoad(XPATH, "//div[@class='user-tab-box']/ul/li[2]", DEFVALUE, TIME_OUT);
   		driver.click(XPATH, "//div[@class='user-tab-box']/ul/li[2]", DEFVALUE);
   		driver.waitForAllElementLoad(ID, "mobileLoginText2", DEFVALUE, TIME_OUT);
		driver.input(ID, "mobileLoginText2", DEFVALUE, "loadtest001190");
		driver.waitForAllElementLoad(ID, "userpassword", DEFVALUE, TIME_OUT);
		driver.input(ID, "userpassword", DEFVALUE, "111111");	
		driver.click(ID, "user_login", DEFVALUE);
		
		driver.waitForAllElementLoad(XPATH, "//div[@class='order_box border_3']/dl[1]/dt", DEFVALUE, DEFVALUE);
        String str = driver.getText(XPATH, "//div[@class='order_box border_3']/dl[1]/dt", DEFVALUE);
   		if(str.equals("娓哥帺鏃ユ湡")){
   				System.out.println("宸茶繘鍏ヤ笅鍗曢〉");
   				return true;
   			}else{
   				System.out.println("鏈繘鍏ヤ笅鍗曢〉");
   				return false;
   			}  				
   	}
   	
	/**
   	 *鍗曢棬绁�:浠锋牸鏃ュ巻琛ㄧ炕椤垫寜閽紝鑷敱鍒囨崲锛涚偣鍑诲悜涓婄殑绠ご锛岀炕椤靛埌涓婁竴涓湀锛涚偣鍑诲悜涓嬬殑绠ご锛岀炕椤靛埌涓嬩竴涓湀 	
   	 */
   	public boolean case04898(){
   		driver.waitForAllElementLoad(XPATH,"//div[@class='ui-calendar']", DEFVALUE, TIME_OUT);
   		if(driver.isElementPresent(XPATH, "//div[@class='ui-calendar']/span[2]", DEFVALUE)){
   			driver.click(XPATH, "//div[@class='ui-calendar']/span[2]", DEFVALUE);
   			String str = driver.getText(XPATH, "//div[@class='caltitle']/span", DEFVALUE);
   			if(str.contains("9鏈�")){
   				System.out.println("宸茬炕鍒颁笅鏈�");
   				return true;
   			}else{
   				System.out.println("鏈炕鍒颁笅鏈�");
   				return false;
   			}  			
   		}
   		System.out.println("鏃犱笅鏈堟寜閽�");
   		return false;
   		
   	}
   	
	/**
   	 *鍗曢棬绁�: 鏄剧ず璇︽儏鐨勬櫙鍖哄湴鍧�
   	 */
   	public boolean case04893(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='buyPlace']/dl[1]/dd", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/dl[1]/dd", DEFVALUE);
   		if(str != null && str !=""){
   				System.out.println("鏅尯鍦板潃宸叉樉绀�");
   				return true;
   			}else{
   				System.out.println("鏅尯鍦板潃鏈樉绀�");
   				return false;
   			}  			
    }
   	
   	/**
   	 *鍗曢棬绁�: 浼樻儬鏍囩鏄剧ず鐐硅瘎杩旂幇銆佹墜鏈鸿繑鐜般�佹父璁拌繑鐜颁笁涓爣绛�
   	 */
   	public boolean case04892(){
   		driver.waitForAllElementLoad(XPATH,"//div[@id='buyPlace']/div[5]/span]", DEFVALUE, TIME_OUT);
   		String str = "";
   		for(int i = 1 ;i<=3;i++){
   			if(driver.isElementPresent(XPATH, "//div[@class='zIndex5']", DEFVALUE)){//濡傛灉鏈変績閿�锛屽垯浠庣浜斾釜div寮�濮嬶紝鍚﹀垯浠庣鍥涗釜寮�濮�
   				if(driver.isElementPresent(XPATH, "//div[@id='buyPlace']/div[5]/span["+i+"]/em", DEFVALUE)){
   					str += driver.getText(XPATH, "//div[@id='buyPlace']/div[5]/span["+i+"]/em", DEFVALUE);//鑾峰彇浼樻儬鏍囩 
   					System.out.println(str);
   				}   				  
   			}else{
   				if(driver.isElementPresent(XPATH, "//div[@id='buyPlace']/div[4]/span["+i+"]/em", DEFVALUE)){
   					str = driver.getText(XPATH, "//div[@id='buyPlace']/div[4]/span["+i+"]/em", DEFVALUE);//鑾峰彇浼樻儬鏍囩 
   				}
   			}			 				 	   	 		
   		}
   		if(str.contains("鐐硅瘎") || str.contains("娓歌杩旂幇") || str.contains("鎵嬫満杩旂幇")){
			System.out.println("浼樻儬鏍囩鏄剧ず姝ｅ父");
			return true;
		}else if(str == ""){
			System.out.println("鍚庡彴鏈缃紭鎯犳爣绛�");
			return true;
		}else{
			System.out.println("浼樻儬鏍囩鏄剧ず涓嶆甯�");
			return false;
		}
	}
 	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 宸叉姠鏄剧ず鏁伴噺锛� 鍥㈣喘鏄剧ず鍘嗗彶绱閿�閲忥紝绉掓潃鏄剧ず褰撴娲诲姩閿�閲忥紝娲诲姩鏈紑濮嬫樉绀�0	
   	 */
   	public boolean case04889(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/div[3]//em", DEFVALUE);
   		int num = Integer.parseInt(str);
   		if(num == 0){
   			System.out.println("娲诲姩鏈紑濮�");
   			return true;
   		}else if(num > 0){
			System.out.println("娲诲姩宸插紑濮�");
			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 璧蜂环鍗曚綅锛氳捣/浜烘垨鑰呰捣/浠�	
   	 */
   	public boolean case04883(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(XPATH, "//div[@id='buyPlace']/div[2]/div/span[2]", DEFVALUE);
   		if(str.contains("璧�/浜�") || str.contains("璧�/浠�") ){
   			System.out.println("鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 鐗瑰崠鏍囩锛氱/鍥紝绉掓潃鏄剧ず绉掞紝鍥㈣喘鏄剧ず鍥�
   	 */
   	public boolean case04881(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String className = driver.getgetAttributeValue("//div[@id='buyPlace']/div[2]/div/i", "class");
   		if(className.contains("tuangou")){
   			System.out.println("鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 鏄剧ず浜у搧鐨勫ソ璇勭巼锛屾爣绛�
   	 */
   	public boolean case04880(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String favorableRate = driver.getText(XPATH, "//div[@id='buyPlace']/div[1]//span", DEFVALUE);
   		String favorable = driver.getText(XPATH, "//div[@id='buyPlace']/div[1]//a", DEFVALUE);
   		if(favorable.contains("濂借瘎") && favorableRate.endsWith("%")){
   			System.out.println("濂借瘎鐜囨樉绀烘甯�");
   			return true;
   		}else{
   			System.out.println("濂借瘎鐜囨樉绀轰笉姝ｅ父");
			return false;
   		}  			
    }   	
   	
  	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 鏄剧ず浜у搧缂栧彿	
   	 */
   	public boolean case04879(){
   		driver.waitForAllElementLoad(ID,"//div[@id='buyPlace']", DEFVALUE, TIME_OUT);
   		String str = driver.getText(CLASSNAME, "product_info1", DEFVALUE);
   		System.out.println(str);
   		if(str.contains("浜у搧缂栧彿锛�183694")){
   			System.out.println("浜у搧缂栧彿鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("浜у搧缂栧彿鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
	/**
   	 *鍗曢棬绁ㄨ鎯呴〉锛� 鏄剧ず鍝佺被鏍囩锛屽搧绫诲悓鐗瑰崠绾夸笂锛屽搧绫绘灇涓撅細闂ㄧエ
   	 */
   	public boolean case04876(){
   		driver.waitForAllElementLoad(ID,"//div[@class='wrap']", DEFVALUE, TIME_OUT);   	
   		if(driver.isElementPresent(XPATH, "//div[@class='wrap']/div[2]/i", DEFVALUE)){
   			System.out.println("鍝佺被鏍囩鏄剧ず姝ｅ父");
   			return true;
   		}else{
   			System.out.println("鍝佺被鏍囩鏄剧ず涓嶆甯�");
			return false;
   		}  			
    }
   	
   	/**
   	 * 绾胯矾璇︽儏椤碉細鍙備笌绉掓潃鎴栬�呭洟璐椿鍔�
   	 * @return
   	 * @throws Exception 
   	 */
	public boolean case03843() throws Exception {
		Thread.sleep(1000);
		driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-hot-event-cpList']", DEFVALUE, TIME_OUT);
		int Num = driver.getTotalElementList("//ul[@class='tm-hot-event-cpList']", "li");//鎵�鏈夋悳绱㈠搧绫�
		for(int i=1;i<=Num;i++){
			//鑾峰彇鍥㈣喘鎴栫鏉�鏍囩
			driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-hot-event-cpList']/li["+i+"]//div[@class='hot-event-cpTips']//em", DEFVALUE, TIME_OUT);
			String size = driver.getText(XPATH, "//ul[@class='tm-hot-event-cpList']/li["+i+"]//div[@class='hot-event-cpTips']//em", DEFVALUE);
			if(size.contains("鍥㈣喘")||size.contains("绉掓潃")){
				driver.click(XPATH, "//ul[@class='tm-hot-event-cpList']/li["+i+"]//h4/a", DEFVALUE);
				String title =driver.getTitle();
				if(!title.contains("404")&&!title.contains("灏忛┐鎵句笉鍒板濡�")){
					System.out.println("姝ｅ父");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鏄剧ず璇ヤ骇鍝佺殑浜у搧鍥剧墖锛堝浘搴撲腑鍥撅級锛岄粯璁ゆ樉绀虹涓�寮犱负澶у浘锛屽叾浣欐寜椤哄簭缂╃暐鍥炬樉绀猴紝鐐瑰嚮灏忓浘澶у浘鑱斿姩鍒囨崲锛岀偣鍑诲悜涓婂悜涓嬬澶村彲涓婁笅婊氬姩
   	 * @return
   	 */
	public boolean case03844() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
//		鍏夋爣绉诲姩鍒板ぇ鍥�
		driver.switchToNewWindow();
		driver.waitForAllElementLoad(XPATH,"//div[@class='product-pic']/ul/li[1]/img", DEFVALUE, TIME_OUT);
		driver.moveToElement(XPATH, "//div[@class='product-pic']/ul/li[1]/img");
//		鐐瑰嚮鍚戝彸缈婚〉
		driver.click(XPATH, "//a[@class='next js_btnR']", DEFVALUE);
//		鐐瑰嚮鍚戝乏缈婚〉
		driver.click(XPATH, "//a[@class='previous js_btnL']", DEFVALUE);
		String display1 = driver.getAttributeValue(XPATH, "//div[@class='product-pic']/ul/li[1]", "style");
//		鐐瑰嚮涓�涓皬鍥�
		driver.click(XPATH, "//ul[@class='pa']/li[4]/img", DEFVALUE);
		String display2 = driver.getAttributeValue(XPATH, "//div[@class='product-pic']/ul/li[4]", "style");
//		鐐瑰嚮灏忓浘鍚戝彸缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='r productImg-btn js_btnR']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='r productImg-btn js_btnR']", DEFVALUE);
//		鐐瑰嚮灏忓浘鍚戝乏缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='l productImg-btn js_btnL']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='l productImg-btn js_btnL']", DEFVALUE);
		if(!display1.contains("display: none")&&!display2.contains("display: none")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鏄剧ず鍝佺被鏍囩锛屽搧绫诲悓鐗瑰崠绾夸笂锛屽搧绫绘灇涓撅細鍛ㄨ竟娓搞�佸浗鍐呮父銆佸嚭澧冩父
   	 * @return
   	 */
	public boolean case03867() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鑾峰彇鍝佺被绫诲瀷
		driver.waitForAllElementLoad(XPATH,"//div[@class='main-detail clearfix']/div[1]", DEFVALUE, TIME_OUT);
		String sign = driver.getAttributeValue(XPATH, "//div[@class='main-detail clearfix']/div[1]", "class");
		if(sign.contains("sign ambitus")||sign.contains("sign outbound")||sign.contains("sign domestic")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鏄剧ず浜у搧缂栧彿
   	 * @return
   	 */
	public boolean case03869() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
		String url = driver.getCurrentUrl();
//		鑾峰彇浜у搧缂栧彿
		driver.waitForAllElementLoad(XPATH,"//div[@class='product_info1']", DEFVALUE, TIME_OUT);
		String content = driver.getText(XPATH, "//div[@class='product_info1']", DEFVALUE);
		String[] num = content.split("浜у搧缂栧彿锛�");
		if(url.contains(num[1])){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鏄剧ず浜у搧鐨勫ソ璇勭巼锛屾爣绛�
   	 * @return
   	 */
	public boolean case03870() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
		String content = driver.getPageSource();
//		濂借瘎鐜囧瓧浣撻鑹�
		driver.waitForAllElementLoad(XPATH,"//div[@class='product_info1']/p/span", DEFVALUE, TIME_OUT);
		String colour = driver.getAttributeValue(XPATH, "//div[@class='product_info1']/p/span", "class");
		if(driver.isElementPresent(XPATH, "//p[@class='nchtitle']/span[1]", DEFVALUE)){
//			鏍囩
			String tag = driver.getAttributeValue(XPATH, "//p[@class='nchtitle']/span[1]", "class");
			if(content.contains("濂借瘎")&&tag.contains("tagsback tagsback-orange")&&colour.contains("c_f60")){
				System.out.println("姝ｅ父");
				return true;
			}
		}
		if(content.contains("濂借瘎")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鐗瑰崠鏍囩锛氱/鍥紝绉掓潃鏄剧ず绉掞紝鍥㈣喘鏄剧ず鍥�
   	 * @return
   	 */
	public boolean case03871() {
		String dfn = null;
		driver.waitForAllElementLoad(XPATH,"//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]", DEFVALUE, TIME_OUT);
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE)){
			dfn = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE);
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
			dfn = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//div[@class='tm-qx-tipsBox tm-zb-Tips-on']/em", DEFVALUE);
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		濂借瘎鐜囧瓧浣撻鑹�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
		if(dfn.contains("绉掋��鏉�")||dfn.contains("鐖嗐��娆�")||dfn.contains("灏俱��鍝�")&&tip.contains("miaosha")){
			System.out.println("姝ｅ父");
			return true;
		}
		if((dfn.contains("鐖嗐��娆�")||dfn.contains("鍥€��璐�"))&&tip.contains("tuangou")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鏄剧ず浜у搧鏈�浣庡彲鍞�佹湁鏁堝椁愮殑璧蜂环
   	 * @return
   	 */
	public boolean case03872() {
		String price = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/em/b[1]", DEFVALUE, TIME_OUT);
		String pricelow = driver.getText(XPATH, "//span[@class='nchline-price-style']/em/b[1]", DEFVALUE);
//		鎵�鏈夊椁愪环鏍�
		if(driver.isElementPresent(XPATH, ".//*[@id='routePackageId']", DEFVALUE)){
			price = driver.getText(XPATH, ".//*[@id='routePackageId']", DEFVALUE);
		}else{
			price = driver.getText(XPATH, ".//*[@id='packageDataId']/div", DEFVALUE);
		}
		if(price.contains(pricelow)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鎶樻墸锛岄攢鍞环/甯傚満浠凤紝淇濈暀灏忔暟鐐瑰悗涓�浣嶏紙鍚戜笂鍙栨暣锛夛紝鍗曚綅锛氭姌锛涘鏃犲競鍦轰环锛屾姌鎵ｄ笉鏄剧ず銆�
   	 * @return
   	 */
	public boolean case03874() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鎶樻墸
		driver.waitForAllElementLoad(XPATH,"//b[@class='nchline-price-discount']", DEFVALUE, TIME_OUT);
		String discount = driver.getText(XPATH, "//b[@class='nchline-price-discount']", DEFVALUE);
		String[] dis = discount.split("鎶�");
		double a = Double.parseDouble(dis[0]);
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/em/b[1]", DEFVALUE, TIME_OUT);
		String pricelow = driver.getText(XPATH, "//span[@class='nchline-price-style']/em/b[1]", DEFVALUE);
		double low = Double.parseDouble(pricelow);
//		甯傚満浠�
		driver.waitForAllElementLoad(XPATH,"//b[@class='nchline-price-through']/i", DEFVALUE, TIME_OUT);
		String pricehigh = driver.getText(XPATH, "//b[@class='nchline-price-through']/i", DEFVALUE);
		double high = Double.parseDouble(pricehigh);
		double result = (low/high)*10;
		result = (double)Math.round(result*10)/10;
		if(a==result){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 璐拱鎸夐挳
   	 * @return
   	 */
	public boolean case04046() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮绔嬪嵆鎶㈣喘
		if(driver.isElementPresent(XPATH, "//a[text()='绔嬪嵆鎶㈣喘']", DEFVALUE)){
			driver.click(XPATH, "//a[text()='绔嬪嵆鎶㈣喘']", DEFVALUE);
		}
//		鐐瑰嚮鍗冲皢寮�鎶�
		if(driver.isElementPresent(XPATH, "//a[text()='鍗冲皢寮�鎶�']", DEFVALUE)){
			driver.click(XPATH, "//a[text()='鍗冲皢寮�鎶�']", DEFVALUE);
		}
//		浜岀淮鐮�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-two-bar-codes']/i", DEFVALUE, TIME_OUT);
		String css = driver.getAttributeValue(XPATH, "//span[@class='nchline-two-bar-codes']/i", "class");
		driver.moveToElement(XPATH, "//span[@class='nchline-two-bar-codes']/i");
//		APP鎵爜棰勮
		driver.waitForAllElementLoad(XPATH,"//div[@class='nchline-two-bar-codes-content']/p", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//div[@class='nchline-two-bar-codes-content']/p", DEFVALUE);
		if(css.contains("tip-icon tip-icon-codes")&&con.contains("APP鎵爜棰勮")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鍊掕鏃舵樉绀猴紝鏃堕棿鏍煎紡锛歑X澶X灏忔椂XX鍒哫X绉�
   	 * @return
   	 */
	public boolean case04695() {
		String day = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
//			绗竴涓骇鍝佺殑鍊掕鏃跺ぉ鏁�
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//span[@class='countdown effect']/i[1]", DEFVALUE);
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			绗簩涓骇鍝佺殑鍊掕鏃跺ぉ鏁�
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//span[@class='countdown effect']/i[1]", DEFVALUE);
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		double dayn = Double.parseDouble(day);
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
		if(tip.contains("miaosha")||(dayn<15&&tip.contains("tuangou"))){
//			璇︽儏椤靛�掕鏃�
			driver.waitForAllElementLoad(XPATH,"//span[@class='js-count-down']", DEFVALUE, TIME_OUT);
			String countdown = driver.getText(XPATH, "//span[@class='js-count-down']", DEFVALUE);
			if(countdown.contains("澶�")&&countdown.contains("鏃�")&&countdown.contains("鍒�")&&countdown.contains("绉�")){
				System.out.println("姝ｅ父");
				return true;
			}
			else{
				return false;
			}
		}
		String pagesource = driver.getPageSource();
		if(!pagesource.contains("璺濈缁撴潫")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 宸叉姠鏄剧ず鏁伴噺锛� 鍥㈣喘鏄剧ず鍘嗗彶绱閿�閲忥紝绉掓潃鏄剧ず褰撴娲诲姩閿�閲忥紝娲诲姩鏈紑濮嬫樉绀�0
   	 * @return
   	 */
	public boolean case04697() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
//		閿�鍞儏鍐�
		driver.waitForAllElementLoad(XPATH,"//p[@class='nchline-count-down-explain']", DEFVALUE, TIME_OUT);
		String sale = driver.getText(XPATH, "//p[@class='nchline-count-down-explain']", DEFVALUE);
		if(tip.contains("miaosha")&&sale.contains("宸插敭鍑�")){
			return true;
		}
		if(tip.contains("tuangou")&&sale.contains("宸插敭")){
			return true;
		}
		System.out.println("寮傚父");
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鏄剧ず鍓╀綑搴撳瓨
   	 * @return
   	 */
	public boolean case04698() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
//		閿�鍞儏鍐�
		driver.waitForAllElementLoad(XPATH,"//p[@class='nchline-count-down-explain']", DEFVALUE, TIME_OUT);
		String sale = driver.getText(XPATH, "//p[@class='nchline-count-down-explain']", DEFVALUE);
		if(tip.contains("miaosha")&&sale.contains("鍓╀綑")){
			System.out.println("姝ｅ父");
			return true;
		}
		if(tip.contains("tuangou")&&sale.contains("宸插敭")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鍥㈣喘娲诲姩鏃跺�掕鏃跺皬浜�15澶╋紝鏄剧ず鍊掕鏃跺強宸插敭鍑洪攢閲忥紱鍊掕鏃跺ぇ浜�15澶╋紝鍊掕鏃朵笉鏄剧ず
   	 * @return
   	 */
	public boolean case04724() {
		int Num = driver.getTotalElementList("//ul[@class='tm-hot-event-cpList']", "li");
		for(int i=1;i<=Num;i++){
//			鐗瑰崠鏍囩
			driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-hot-event-cpList']/li['+i+']/div[1]/div[1]/em", DEFVALUE, TIME_OUT);
			String tuan = driver.getText(XPATH, "//ul[@class='tm-hot-event-cpList']/li['+i+']/div[1]/div[1]/em", DEFVALUE);
			if(tuan.contains("鍥㈣喘")){
//				鍊掕鏃跺ぉ
				driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-hot-event-cpList']/li['+i+']//span[@class='countdown effect']/i[1]", DEFVALUE, TIME_OUT);
				String day = driver.getText(XPATH, "//ul[@class='tm-hot-event-cpList']/li['+i+']//span[@class='countdown effect']/i[1]", DEFVALUE);
				double dayn = Double.parseDouble(day);
//				鐐瑰嚮浜у搧鍚嶇О
				driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-hot-event-cpList']/li['+i+']/div[2]/h4/a", DEFVALUE, TIME_OUT);
				driver.click(XPATH, "//ul[@class='tm-hot-event-cpList']/li['+i+']/div[2]/h4/a", DEFVALUE);
				driver.switchToNewWindow();
//				浜у搧淇℃伅
				driver.waitForAllElementLoad(XPATH,"//div[@class='detail']", DEFVALUE, TIME_OUT);
				String content = driver.getText(XPATH, "//div[@class='detail']", DEFVALUE);
				if(dayn>=15&&content.contains("宸插敭")){
					System.out.println("姝ｅ父");
					return true;
				}
				else if(dayn<15&&content.contains("宸插敭")&&content.contains("澶�")&&content.contains("鏃�")&&content.contains("鍒�")&&content.contains("绉�")){
					System.out.println("姝ｅ父");
					return true;
				}else{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鍑哄彂鍦伴粯璁ゆ樉绀轰富鍑哄彂鍦帮紝鐐瑰嚮涓嬫媺鍒楄〃鏄剧ず鍏ㄩ儴鍑哄彂鍦板悕绉帮紝鎸夌収鑻辨枃瀛楁瘝鎺掑簭锛岃嚜鐢卞垏鎹�
   	 * @return
   	 */
	public boolean case04735() {
		String place = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鍑哄彂鍦�
		driver.waitForAllElementLoad(XPATH,"//span[@class='product_top_cfd_title']", DEFVALUE, TIME_OUT);
		String base = driver.getText(XPATH, "//span[@class='product_top_cfd_title']", DEFVALUE);
		driver.moveToElement(XPATH, "//span[@class='product_top_cfd_title']");
		if(base.contains("涓婃捣")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 浠锋牸鏃ュ巻琛ㄤ笂鐨勫�掕鏃舵樉绀猴紝鏃堕棿鏍煎紡锛歑X澶X灏忔椂XX鍒哫X绉掞紱鏄剧ず鍔熻兘鍚屽ご閮ㄥ�掕鏃舵樉绀轰竴鏍�
   	 * @return
	 * @throws Exception 
   	 */
	public boolean case04736() throws Exception {
		String day = null;
		String tip = null;
		Thread.sleep(1000);
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
//			鐗瑰崠鏍囩
			driver.waitForAllElementLoad(XPATH,"//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE, TIME_OUT);
			tip = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE);
//			绗竴涓骇鍝佺殑鍊掕鏃跺ぉ鏁�
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//span[@class='countdown effect']/i[1]", DEFVALUE);
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐗瑰崠鏍囩
			driver.waitForAllElementLoad(XPATH,"//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE, TIME_OUT);
			tip = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//div[@class='tm-qx-tipsBox tm-zb-Tips']/dfn", DEFVALUE);
//			绗簩涓骇鍝佺殑鍊掕鏃跺ぉ鏁�
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//span[@class='countdown effect']/i[1]", DEFVALUE);
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		double dayn = Double.parseDouble(day);
		driver.switchToNewWindow();
		if((dayn<15)||(dayn>=15)&&(tip.contains("绉掋��鏉�")||tip.contains("鐖嗐��娆�"))){
//			璇︽儏椤靛ご閮ㄥ�掕鏃�
			driver.waitForAllElementLoad(XPATH,"//div[@class='nchline-count-down clearfix']/p[@class='nchline-count-down-clock']", DEFVALUE, TIME_OUT);
			String time1 = driver.getText(XPATH, "//div[@class='nchline-count-down clearfix']/p[@class='nchline-count-down-clock']", DEFVALUE);
//			璇︽儏椤典粙涓棩鍘嗚〃涓婂�掕鏃�
//			driver.waitForAllElementLoad(XPATH,".//*[@id='nchline-buy']//p[@class='nchline-count-down-clock']", DEFVALUE, TIME_OUT);
			String time2 = driver.getText(XPATH, ".//*[@id='nchline-buy']//p[@class='nchline-count-down-clock']", DEFVALUE);
			if(time1.contains(time2)){
				System.out.println("姝ｅ父");
				return true;
			}
			else{
				return false;
			}
		}
		String pagesource = driver.getText(XPATH, ".//*[@id='nchline-buy']", DEFVALUE);
		if(!pagesource.contains("璺濈缁撴潫")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 浠锋牸鏃ュ巻琛ㄩ粯璁ゆ樉绀哄綋鍓嶄骇鍝侊紙鍖呭惈鎵�鏈夊椁愶級鏈�杩戝彲鍞棩鏈熷紑濮�8澶╃殑鏃堕棿浠锋牸琛紝鏄剧ず鍐呭鍖呮嫭璧蜂环銆佽绋嬨�佸簱瀛樸�佷績閿�锛屽敭缃勭殑鏃ユ湡璧蜂环淇濈暀鏄剧ず
   	 * @return
   	 */
	public boolean case04747() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		浠锋牸鏃ュ巻榛樿鏄剧ず8澶╂椂闂翠环鏍艰〃
		driver.waitForAllElementLoad(XPATH,"//ul[@id='dayPrice']", DEFVALUE, TIME_OUT);
		int Num = driver.getTotalElementList("//ul[@id='dayPrice']", "li");
//		琛岀▼A鎴朆
		driver.waitForAllElementLoad(XPATH,"//i[@class='tip-icon tip-icon-trip']", DEFVALUE, TIME_OUT);
		String xc = driver.getText(XPATH, "//i[@class='tip-icon tip-icon-trip']", DEFVALUE);
		if(Num==8&&(xc.contains("A")||xc.contains("B"))){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鐐瑰嚮浠锋牸鏃ュ巻锛屽脊鍑哄弻鏈堟椂闂翠环鏍艰〃锛岀偣鍑绘棩鏈燂紝鍙栬鏃ユ湡鍓嶄笁澶╀互鍙婂悗鍥涘ぉ鏇挎崲妯悜鏃ュ巻鐨勬棩鏈燂紝濂楅鍐呭瀵瑰簲杩囨护
   	 * @return
   	 */
	public boolean case04748() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮浠锋牸鏃ュ巻
		driver.waitForAllElementLoad(XPATH,"//div[@class='ft-pc js-pop-calender']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='ft-pc js-pop-calender']", DEFVALUE);
//		鐐瑰嚮涓�涓棩鏈�
		driver.waitForAllElementLoad(XPATH,"//div[@class='calmonth'][2]//span[@class='calday'][text()='17']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='calmonth'][2]//span[@class='calday'][text()='17']", DEFVALUE);
//		榛樿鏄剧ず鐨勭鍥涘ぉ鏃ユ湡
		driver.waitForAllElementLoad(XPATH,".//*[@id='priceli_3']", DEFVALUE, TIME_OUT);
		String act = driver.getAttributeValue(XPATH, ".//*[@id='priceli_3']", "class");
//		鐐瑰嚮鏃ユ湡鍚戝乏缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='slider-btn-left']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='slider-btn-left']", DEFVALUE);
//		鐐瑰嚮鏃ユ湡鍚戝彸缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='slider-btn-right']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='slider-btn-right']", DEFVALUE);
		if(act.contains("active")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鑷姩鎵撳寘浜у搧鏄剧ず濂楅鍐呭锛氬椁怚D
   	 * @return
   	 */
	public boolean case04759() {
		String pack = null;
///		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鎵�鏈夊椁愪俊鎭�
		if(driver.isElementPresent(XPATH, ".//*[@id='routePackageId']/div", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH,".//*[@id='routePackageId']/div", DEFVALUE, TIME_OUT);
			pack = driver.getText(XPATH, ".//*[@id='routePackageId']/div", DEFVALUE);
		}
		if(driver.isElementPresent(XPATH, ".//*[@id='packageDataId']/div", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH,".//*[@id='packageDataId']/div", DEFVALUE, TIME_OUT);
			pack = driver.getText(XPATH, ".//*[@id='packageDataId']/div", DEFVALUE);
		}
		if(pack.contains("濂楅ID")||pack.contains("濂楅id")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細 鑷姩鎵撳寘浜у搧鏄剧ず濂楅鍐呭锛氬椁愬悕绉版樉绀鸿绋�+濂楅鍚嶇О锛堝:A琛岀▼ 涓変簹浜斿ぉ鍥涙櫄鑷敱琛岋級
   	 * @return
   	 */
	public boolean case04760() {
		String pack = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鎵�鏈夊椁愪俊鎭�
		if(driver.isElementPresent(XPATH, ".//*[@id='routePackageId']/div", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH,".//*[@id='routePackageId']/div", DEFVALUE, TIME_OUT);
			pack = driver.getText(XPATH, ".//*[@id='routePackageId']/div", DEFVALUE);
		}
		if(driver.isElementPresent(XPATH, ".//*[@id='packageDataId']/div", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH,".//*[@id='packageDataId']/div", DEFVALUE, TIME_OUT);
			pack = driver.getText(XPATH, ".//*[@id='packageDataId']/div", DEFVALUE);
		}
		if(pack.contains("+A琛岀▼")||pack.contains("+B琛岀▼")){
			System.out.println("姝ｅ父");
			return true;
		}
		else if(pack.contains("闂ㄧエ")&&pack.contains("閰掑簵")&&pack.contains("浜ら��")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細浜у搧璇︽儏锛氫寒鐐癸紙浜у搧缁忕悊鎺ㄨ崘锛�+浜у搧璇︽儏
   	 * @return
   	 */
	public boolean case04797() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		浜у搧璇︽儏-浜偣
		driver.waitForAllElementLoad(XPATH,"//div[@class='nchline-hight-lights']", DEFVALUE, TIME_OUT);
		String pack = driver.getText(XPATH, "//div[@class='nchline-hight-lights']", DEFVALUE);
		if(pack.contains("浜偣")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細琛岀▼浠嬬粛锛氳绋嬶紙澶氳绋婽AB鍒囨崲锛夛紝鏄剧ず浜ら�氫俊鎭紙澶氳埅鐝璗AB鍒囨崲锛屽锛氫氦閫�1銆佷氦閫�2....锛�
   	 * @return
   	 */
	public boolean case04798() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮琛岀▼浠嬬粛
		driver.waitForAllElementLoad(XPATH,"//a[text()='琛岀▼浠嬬粛']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='琛岀▼浠嬬粛']", DEFVALUE);
		driver.waitForAllElementLoad(XPATH,".//*[@id='J_scrollnav1']/li[1]", DEFVALUE, TIME_OUT);
		String traffic = driver.getText(XPATH, ".//*[@id='J_scrollnav1']/li[1]", DEFVALUE); 
//		琛岀▼鏁�
		driver.waitForAllElementLoad(XPATH,"//ul[@class='instance_tab fl']", DEFVALUE, TIME_OUT);
		int num1 = driver.getTotalElementList("//ul[@class='instance_tab fl']", "li");
		for(int i=1; i<=num1; i++){
			driver.click(XPATH, "//ul[@class='instance_tab fl']/li['+i+']", DEFVALUE);
			if(traffic.contains("浜ら��")){
				int num2 = driver.getTotalElementList(".//*[@id='traffic-type']/ul", "li");
				for(int j=1; j<=num2; j++){
					driver.click(XPATH, ".//*[@id='traffic-type']/ul/li['+j+']", DEFVALUE);
					System.out.println("姝ｅ父");
					return true;
				}
			}
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細浜偣鏍峰紡鏈�澶氬睍绀轰笁琛岋紝鍏朵綑婊氬姩鏄剧ず
   	 * @return
   	 */
	public boolean case04800() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		浜у搧璇︽儏-浜偣
		driver.waitForAllElementLoad(XPATH,"//div[@class='nchline-hight-lights']/div", DEFVALUE, TIME_OUT);
		String css = driver.getAttributeValue(XPATH, "//div[@class='nchline-hight-lights']/div", "class");
		if(css.contains("nchline-height-lights-con")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細璐圭敤璇存槑锛氳垂鐢ㄥ寘鍚�佽垂鐢ㄤ笉鍖呭惈
   	 * @return
   	 */
	public boolean case04803() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮璐圭敤璇存槑
		driver.waitForAllElementLoad(XPATH,"//a[text()='璐圭敤璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='璐圭敤璇存槑']", DEFVALUE);
//		璐圭敤璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH,"//div[@id='product-cost']//div[@class='detail-instance-body']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//div[@id='product-cost']//div[@class='detail-instance-body']", DEFVALUE);
		if(con.contains("璐圭敤鍖呭惈")||con.contains("璐圭敤涓嶅寘鍚�")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鍒嗘湡璇存槑锛氬浐瀹氭枃鏈紝鍚屽墠鍙拌鎯呴〉
   	 * @return
   	 */
	public boolean case04804() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮鍒嗘湡璇存槑
		driver.waitForAllElementLoad(XPATH,"//a[text()='鍒嗘湡璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鍒嗘湡璇存槑']", DEFVALUE);
//		鍒嗘湡璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-fenqi']", DEFVALUE, TIME_OUT);
		String con1 = driver.getText(XPATH, ".//*[@id='product-fenqi']", DEFVALUE);
		driver.navigateTo("http://www.lvmama.com/tuangou");
//		鐐瑰嚮绗笁涓骇鍝�
		if(driver.isElementPresent(XPATH, "//ul[@class='tm-qx-cpList']/li[3]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li[3]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗洓涓骇鍝�
			driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li[4]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮鍒嗘湡璇存槑
		driver.waitForAllElementLoad(XPATH,"//a[text()='鍒嗘湡璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鍒嗘湡璇存槑']", DEFVALUE);
//		鍒嗘湡璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-fenqi']", DEFVALUE, TIME_OUT);
		String con2 = driver.getText(XPATH, ".//*[@id='product-fenqi']", DEFVALUE);
		if(con1.contains(con2)||con2.contains(con1)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細棰勫畾椤荤煡锛氳喘鐗╄鏄庛�佹帹鑽愰」鐩�佸嚭琛岃绀哄強璇存槑銆侀��鏀硅鏄�
   	 * @return
   	 */
	public boolean case04805() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮棰勮椤荤煡
		driver.waitForAllElementLoad(XPATH,"//a[text()='棰勮椤荤煡']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE);
//		棰勮椤荤煡鍐呭
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-preorder-note']//div[@class='detail-instance-body preorder-notice']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-preorder-note']//div[@class='detail-instance-body preorder-notice']", DEFVALUE);
		if(con.contains("璐墿璇存槑")||con.contains("鎺ㄨ崘椤圭洰")||con.contains("鍑鸿璀︾ず鍙婅鏄�")||con.contains("閫�鏀硅鏄�")||con.contains("琛屽墠椤荤煡")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鍑鸿璀︾ず鍙婅鏄庨珮380PX锛岃秴鍑哄垯闇�鐐瑰嚮鏌ョ湅鍏ㄩ儴鍑鸿璀︾ず鍙婅鏄�
   	 * @return
   	 */
	public boolean case04806() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮棰勮椤荤煡
		driver.waitForAllElementLoad(XPATH,"//a[text()='棰勮椤荤煡']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE);
//		棰勮椤荤煡鍐呭
		if(driver.isElementPresent(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/../..", DEFVALUE)){
			String css1 = driver.getAttributeValue(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/../..", "class");//甯﹀皬鏍囬
			String css2 = driver.getAttributeValue(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/following-sibling::div", "class");//涓嶅甫灏忔爣棰�
			if(css1.contains("detail-out-shell")&&css2.contains("detail")){
//				鐐瑰嚮鏌ョ湅鍏ㄩ儴鍑鸿璀︾ず鍜岃鏄�
				if(driver.isElementPresent(XPATH, "//span[@class='js-check-all']", DEFVALUE)){
					driver.click(XPATH, "//span[@class='js-check-all']", DEFVALUE);
				}
				System.out.println("姝ｅ父");
				return true;
			}
		}
		if(driver.isElementPresent(XPATH, "//*[text()='琛屽墠椤荤煡']/../..", DEFVALUE)){
			String css1 = driver.getAttributeValue(XPATH, "//*[text()='琛屽墠椤荤煡']/../..", "class");//甯﹀皬鏍囬
			String css2 = driver.getAttributeValue(XPATH, "//*[text()='琛屽墠椤荤煡']/following-sibling::div", "class");//涓嶅甫灏忔爣棰�
			if(css1.contains("detail-out-shell")&&css2.contains("detail")){
//				鐐瑰嚮鏌ョ湅鍏ㄩ儴琛屽墠椤荤煡
				if(driver.isElementPresent(XPATH, "//span[@class='js-check-all2']", DEFVALUE)){
					driver.click(XPATH, "//span[@class='js-check-all2']", DEFVALUE);
				}
				System.out.println("姝ｅ父");
				return true;
			}
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細绛捐瘉绛炬敞锛氬悓绾夸笂
   	 * @return
   	 */
	public boolean case04807() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮绛捐瘉/绛炬敞
		driver.waitForAllElementLoad(XPATH,"//a[text()='绛捐瘉/绛炬敞']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='绛捐瘉/绛炬敞']", DEFVALUE);
//		绛捐瘉鏍囬
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-visa']//div[@class='instance-title clearfix']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-visa']//div[@class='instance-title clearfix']", DEFVALUE);
		if(con.contains("绛捐瘉/绛炬敞")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細鐩镐技浜у搧鎺ㄨ崘鍚岀嚎涓�
   	 * @return
   	 */
	public boolean case04808() {
//		鐐瑰嚮鐩镐技浜у搧
		driver.waitForAllElementLoad(XPATH,"//a[text()='鐩镐技浜у搧']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鐩镐技浜у搧']", DEFVALUE);
//		鐩镐技浜у搧鏍囬
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-similar']//ul[@class='title-tab clearfix']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-similar']//ul[@class='title-tab clearfix']", DEFVALUE);
		if(con.contains("鐩镐技浜у搧")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绾胯矾璇︽儏椤碉細椹村弸鐐硅瘎鍚岀嚎涓�
   	 * @return
   	 */
	public boolean case04809() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮椹村弸鐐硅瘎
		driver.waitForAllElementLoad(XPATH,"//a[text()='椹村弸鐐硅瘎']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='椹村弸鐐硅瘎']", DEFVALUE);
//		椹村弸鐐硅瘎鏍囬
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-customer-review']/div[@class='instance-title clearfix']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-customer-review']/div[@class='instance-title clearfix']", DEFVALUE);
		if(con.contains("椹村弸鐐硅瘎")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鍙備笌绉掓潃鎴栬�呭洟璐椿鍔�
   	 * @return
   	 */
	public boolean case04811() {
//		鎵�鏈変骇鍝佺壒鍗栨爣绛炬暟
		int Num = driver.getTotalElementList("//ul[@class='tm-qx-cpList']", "li");
		for(int i=1;i<=Num;i++){
//			鐗瑰崠鏍囩
			driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-qx-cpList']/li['+i+']/div[@class='tm-qx-picBox']/div/dfn", DEFVALUE, TIME_OUT);
			String con = driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li['+i+']/div[@class='tm-qx-picBox']/div/dfn", DEFVALUE);
			if(con.contains("绉掋��鏉�")||con.contains("鍥€��璐�")||con.contains("鐖嗐��娆�")){
//				鐐瑰嚮浜у搧閾炬帴
				driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-qx-cpList']/li['+i+']//a[@class='tm-qx-ljqgBtn']", DEFVALUE, TIME_OUT);
				driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li['+i+']//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
				driver.switchToNewWindow();
				String title = driver.getTitle();
				if(!title.contains("404")&&!title.contains("灏忛┐鎵句笉鍒板濡�")){
					System.out.println("姝ｅ父");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず璇ヤ骇鍝佺殑浜у搧鍥剧墖锛堝浘搴撲腑鍥撅級锛岄粯璁ゆ樉绀虹涓�寮犱负澶у浘锛屽叾浣欐寜椤哄簭缂╃暐鍥炬樉绀猴紝鐐瑰嚮灏忓浘澶у浘鑱斿姩鍒囨崲锛岀偣鍑诲悜涓婂悜涓嬬澶村彲涓婁笅婊氬姩
   	 * @return
   	 */
	public boolean case04812() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, ".//*[@id='list']//li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
//		鍏夋爣绉诲姩鍒板ぇ鍥�
		driver.switchToNewWindow();
		driver.waitForAllElementLoad(XPATH,"//div[@class='product-pic']/ul/li[1]/img", DEFVALUE, TIME_OUT);
		driver.moveToElement(XPATH, "//div[@class='product-pic']/ul/li[1]/img");
//		鐐瑰嚮鍚戝彸缈婚〉
		driver.click(XPATH, "//a[@class='next js_btnR']", DEFVALUE);
//		鐐瑰嚮鍚戝乏缈婚〉
		driver.click(XPATH, "//a[@class='previous js_btnL']", DEFVALUE);
		String display1 = driver.getAttributeValue(XPATH, "//div[@class='product-pic']/ul/li[1]", "style");
//		鐐瑰嚮涓�涓皬鍥�
		driver.click(XPATH, "//ul[@class='pa']/li[4]/img", DEFVALUE);
		String display2 = driver.getAttributeValue(XPATH, "//div[@class='product-pic']/ul/li[4]", "style");
//		鐐瑰嚮灏忓浘鍚戝彸缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='r productImg-btn js_btnR']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='r productImg-btn js_btnR']", DEFVALUE);
//		鐐瑰嚮灏忓浘鍚戝乏缈婚〉
		driver.waitForAllElementLoad(XPATH,"//a[@class='l productImg-btn js_btnL']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='l productImg-btn js_btnL']", DEFVALUE);
		if(!display1.contains("display: none")&&!display2.contains("display: none")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず鍝佺被鏍囩锛屽搧绫诲悓鐗瑰崠绾夸笂锛屽搧绫绘灇涓撅細閭疆
   	 * @return
   	 */
	public boolean case04815() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, ".//*[@id='list']//li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鑾峰彇鍝佺被绫诲瀷
		driver.waitForAllElementLoad(XPATH,"//div[@class='main-detail clearfix']/div[1]", DEFVALUE, TIME_OUT);
		String sign = driver.getAttributeValue(XPATH, "//div[@class='main-detail clearfix']/div[1]", "class");
		if(sign.contains("sign domestic nchflag-icon")||sign.contains("sign liner")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず浜у搧缂栧彿
   	 * @return
   	 */
	public boolean case04817() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, ".//*[@id='list']//li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
		String url = driver.getCurrentUrl();
//		鑾峰彇浜у搧缂栧彿
		driver.waitForAllElementLoad(XPATH,"//div[@class='product_info1']", DEFVALUE, TIME_OUT);
		String content = driver.getText(XPATH, "//div[@class='product_info1']", DEFVALUE);
		String[] num = content.split("浜у搧缂栧彿锛�");
		if(url.contains(num[1])){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず浜у搧鐨勫ソ璇勭巼锛屾爣绛�
   	 * @return
   	 */
	public boolean case04819() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, ".//*[@id='list']//li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		濂借瘎鐜�
		driver.waitForAllElementLoad(XPATH,"//div[@class='product_info1']/p/span", DEFVALUE, TIME_OUT);
		String css = driver.getAttributeValue(XPATH, "//div[@class='product_info1']/p/span" , "class");
		if(css.contains("c_f60")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鐗瑰崠鏍囩锛氱/鍥紝绉掓潃鏄剧ず绉掞紝鍥㈣喘鏄剧ず鍥�
   	 * @return
   	 */
	public boolean case04820() {
		String tip = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
//			绗竴涓骇鍝佺壒鍗栨爣绛�
			tip = driver.getText(XPATH, ".//*[@id='list']//li[1]/div[@class='tm-qx-picBox']/div/dfn", DEFVALUE);
			driver.click(XPATH, ".//*[@id='list']//li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			绗簩涓骇鍝佺壒鍗栨爣绛�
			tip = driver.getText(XPATH, ".//*[@id='list']//li[2]/div[@class='tm-qx-picBox']/div/dfn", DEFVALUE);
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, ".//*[@id='list']//li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		璇︽儏椤电壒鍗栨爣绛�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String css = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i" , "class");
		if((tip.contains("鐖嗐��娆�")||tip.contains("鍥€��璐�"))&&css.contains("tuangou")){
			System.out.println("姝ｅ父");
			return true;
		}
		if(tip.contains("绉掋��鏉�")&&css.contains("miaosha")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず浜у搧鏈�浣庡彲鍞�佹湁鏁堝椁愮殑璧蜂环
   	 * @return
   	 */
	public boolean case04821() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/em/b[1]", DEFVALUE, TIME_OUT);
		String pricelow = driver.getText(XPATH, "//span[@class='nchline-price-style']/em/b[1]", DEFVALUE);
//		缃簳
		driver.scrollToBottom();
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-detail-header']/div/span/b", DEFVALUE, TIME_OUT);
		String price = driver.getText(XPATH, ".//*[@id='product-detail-header']/div/span/b", DEFVALUE);
		if(price.contains(pricelow)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず浜у搧鏈�浣庡彲鍞�佹湁鏁堝椁愮殑璧蜂环
   	 * @return
   	 */
	public boolean case04825() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鎶樻墸
		driver.waitForAllElementLoad(XPATH,"//b[@class='nchline-price-discount']", DEFVALUE, TIME_OUT);
		String discount = driver.getText(XPATH, "//b[@class='nchline-price-discount']", DEFVALUE);
		String[] dis = discount.split("鎶�");
		double a = Double.parseDouble(dis[0]);
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/em/b[1]", DEFVALUE, TIME_OUT);
		String pricelow = driver.getText(XPATH, "//span[@class='nchline-price-style']/em/b[1]", DEFVALUE);
		double low = Double.parseDouble(pricelow);
//		鏈�浣庤捣浠�
		driver.waitForAllElementLoad(XPATH,"//b[@class='nchline-price-through']/i", DEFVALUE, TIME_OUT);
		String pricehigh = driver.getText(XPATH, "//b[@class='nchline-price-through']/i", DEFVALUE);
		double high = Double.parseDouble(pricehigh);
		double result = (low/high)*10;
		result = (double)Math.round(result*10)/10;
		if(a==result){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 鐐瑰嚮璐拱鎸夐挳---杩涘叆涓嬪崟椤�
   	 * @return
   	 */
	public boolean case04831(String userName,String passWord) {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
		frontlogin(userName,passWord);
//		鐐瑰嚮绔嬪嵆鎶㈣喘
		driver.waitForAllElementLoad(XPATH,"//a[@class='price-buy price-buy-shooting']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@class='price-buy price-buy-shooting']", DEFVALUE);
//		鑾峰彇缃戦〉澶村悕
		String title = driver.getTitle();
		if(title.contains("濉啓璁㈠崟淇℃伅")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 鍊掕鏃舵樉绀猴紝鏃堕棿鏍煎紡锛歑X澶X灏忔椂XX鍒哫X绉�
   	 * @return
   	 */
	public boolean case04834() {
		String day = null;
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//span[@class='countdown effect']/i[1]", DEFVALUE);
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
			day = driver.getText(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//span[@class='countdown effect']/i[1]", DEFVALUE);
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		double dayn = Double.parseDouble(day);
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
		if(tip.contains("miaosha")||(dayn<15&&tip.contains("tuangou"))){
//			璇︽儏椤靛�掕鏃�
			driver.waitForAllElementLoad(XPATH,"//span[@class='js-count-down']", DEFVALUE, TIME_OUT);
			String countdown = driver.getText(XPATH, "//span[@class='js-count-down']", DEFVALUE);
			if(countdown.contains("澶�")&&countdown.contains("鏃�")&&countdown.contains("鍒�")&&countdown.contains("绉�")){
				System.out.println("姝ｅ父");
				return true;
			}
			else{
				return false;
			}
		}
		String pagesource = driver.getPageSource();
		if(!pagesource.contains("璺濈缁撴潫")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 鍥㈣喘娲诲姩鏃跺�掕鏃跺皬浜�15澶╋紝鏄剧ず鍊掕鏃跺強宸插敭鍑洪攢閲忥紱鍊掕鏃跺ぇ浜�15澶╋紝鍊掕鏃朵笉鏄剧ず
   	 * @return
   	 */
	public boolean case04836() {
		int Num = driver.getTotalElementList("//ul[@class='tm-qx-cpList']", "li");
		for(int i=1;i<=Num;i++){
//			鐗瑰崠鏍囩
			driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-qx-cpList']/li["+i+"]//div[@class='tm-qx-tipsBox tm-yl-Tips']/dfn", DEFVALUE, TIME_OUT);
			String tuan = driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//div[@class='tm-qx-tipsBox tm-yl-Tips']/dfn", DEFVALUE);
			if(tuan.contains("鐖嗐��娆�")||tuan.contains("鍥€��璐�")){
//				鍊掕鏃跺ぉ
				driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-qx-cpList']/li["+i+"]//span[@class='countdown effect']/i[1]", DEFVALUE, TIME_OUT);
				String shijian = driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//span[@class='countdown effect']", DEFVALUE);
				if(shijian.contains("宸茬粨鏉�")){
					continue;
				}
				String day = driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//span[@class='countdown effect']/i[1]", DEFVALUE);
				double dayn = Double.parseDouble(day);
//				鐐瑰嚮浜у搧鍚嶇О
				driver.waitForAllElementLoad(XPATH,"//ul[@class='tm-qx-cpList']/li["+i+"]//a[@class='tm-qx-ljqgBtn']", DEFVALUE, TIME_OUT);
				driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
				driver.switchToNewWindow();
//				浜у搧淇℃伅
				driver.waitForAllElementLoad(XPATH,"//div[@class='detail jsviewmore']", DEFVALUE, TIME_OUT);
				String content = driver.getText(XPATH, "//div[@class='detail jsviewmore']", DEFVALUE);
				if(dayn>=15&&content.contains("宸插敭")){
					System.out.println("姝ｅ父");
					return true;
				}
				else if(dayn<15&&content.contains("宸插敭")&&content.contains("澶�")&&content.contains("鏃�")&&content.contains("鍒�")&&content.contains("绉�")){
					System.out.println("姝ｅ父");
					return true;
				}else{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 宸叉姠鏄剧ず鏁伴噺锛� 鍥㈣喘鏄剧ず鍘嗗彶绱閿�閲忥紝绉掓潃鏄剧ず褰撴娲诲姩閿�閲忥紝娲诲姩鏈紑濮嬫樉绀�0
   	 * @return
   	 */
	public boolean case04845() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
//		閿�鍞儏鍐�
		driver.waitForAllElementLoad(XPATH,"//p[@class='nchline-count-down-explain']", DEFVALUE, TIME_OUT);
		String sale = driver.getText(XPATH, "//p[@class='nchline-count-down-explain']", DEFVALUE);
		if(tip.contains("miaosha")&&sale.contains("宸插敭鍑�")){
			return true;
		}
		if(tip.contains("tuangou")&&sale.contains("宸插敭")){
			return true;
		}
		System.out.println("寮傚父");
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鏄剧ず鍓╀綑搴撳瓨
   	 * @return
   	 */
	public boolean case04846() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐗瑰崠鏍囩
		driver.waitForAllElementLoad(XPATH,"//span[@class='nchline-price-style']/i", DEFVALUE, TIME_OUT);
		String tip = driver.getAttributeValue(XPATH, "//span[@class='nchline-price-style']/i", "class");
//		閿�鍞儏鍐�
		driver.waitForAllElementLoad(XPATH,"//p[@class='nchline-count-down-explain']", DEFVALUE, TIME_OUT);
		String sale = driver.getText(XPATH, "//p[@class='nchline-count-down-explain']", DEFVALUE);
		if(tip.contains("miaosha")&&sale.contains("鍓╀綑")){
			System.out.println("姝ｅ父");
			return true;
		}
		if(tip.contains("tuangou")&&sale.contains("宸插敭")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 鍑哄彂娓彛鏄剧ず鍚庡彴璁剧疆鐨勫嚭鍙戞腐鍙�
   	 * @return
   	 */
	public boolean case04850(String userName,String passWord) {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鍑哄彂娓彛
		driver.waitForAllElementLoad(XPATH,"//dt[text()='鍑哄彂娓彛锛�']/following-sibling::dd", DEFVALUE, TIME_OUT);
		String sale = driver.getText(XPATH, "//dt[text()='鍑哄彂娓彛锛�']/following-sibling::dd", DEFVALUE);
		String title = driver.getCurrentUrl();
		String tit[] = null;
		if(title.contains("deal")){
			tit = title.split("http://www.lvmama.com/tuangou/deal-");
		}
		if(title.contains("sale")){
			tit = title.split("http://www.lvmama.com/tuangou/sale-");
		}
		backlogin(userName,passWord);
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframe", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframe");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, tit[1]);
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		鐐瑰嚮缂栬緫
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='缂栬緫']", DEFVALUE);
		driver.switchToNewWindow();
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframeMain", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframeMain");
//		鑾峰彇浜у搧鍑哄彂鍦�
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		String starting = driver.getAttributeValue(XPATH, ".//*[@id='district']", "value");
		if(sale.contains(starting)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細 寮�鑸棩鏈熸樉绀哄悗鍙拌缃殑寮�鑸棩鏈熷瓧娈�
   	 * @return
   	 */
	public boolean case04851(String userName,String passWord) {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		寮�琛屾棩鏈�
		driver.waitForAllElementLoad(XPATH,"//dt[text()='寮�鑸棩鏈燂細']/following-sibling::dd", DEFVALUE, TIME_OUT);
		String starttime = driver.getText(XPATH, "//dt[text()='寮�鑸棩鏈燂細']/following-sibling::dd", DEFVALUE);
		String title = driver.getCurrentUrl();
		String tit[] = null;
		if(title.contains("deal")){
			tit = title.split("http://www.lvmama.com/tuangou/deal-");
		}
		if(title.contains("sale")){
			tit = title.split("http://www.lvmama.com/tuangou/sale-");
		}
		backlogin(userName,passWord);
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframe", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframe");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, tit[1]);
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		鐐瑰嚮缂栬緫
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='缂栬緫']", DEFVALUE);
		driver.switchToNewWindow();
//		鐐瑰嚮閿�鍞俊鎭�
		driver.waitForAllElementLoad(XPATH, "//a[text()='閿�鍞俊鎭�']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='閿�鍞俊鎭�']", DEFVALUE);
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframeMain", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframeMain");
//		鐐瑰嚮绠＄悊浠撴埧
		driver.waitForAllElementLoad(XPATH, ".//*[@id='canbinManageTab']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, ".//*[@id='canbinManageTab']", DEFVALUE);
//		鐐瑰嚮鏌ョ湅浠锋牸
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ョ湅浠锋牸']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ョ湅浠锋牸']", DEFVALUE);
//		driver.outIframe();
		driver.selectFrame(XPATH, "//div[@class='dialog-content']//iframe[@class='iframe-view-price']");
//		鑾峰彇鍑哄彂鏃ユ湡
		driver.waitForAllElementLoad(XPATH, "//ul[@class='col departure_date_ul clearfix']/li", DEFVALUE, TIME_OUT);
		String st = driver.getText(XPATH, "//ul[@class='col departure_date_ul clearfix']/li", DEFVALUE);
		if(st.contains(starttime)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細浜偣鏍峰紡鏈�澶氬睍绀轰笁琛岋紝鍏朵綑婊氬姩鏄剧ず
   	 * @return
   	 */
	public boolean case04859() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		浜у搧璇︽儏-浜偣
		driver.waitForAllElementLoad(XPATH,"//div[@class='nchline-hight-lights']/div", DEFVALUE, TIME_OUT);
		String css = driver.getAttributeValue(XPATH, "//div[@class='nchline-hight-lights']/div", "class");
		if(css.contains("nchline-height-lights-con")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細浜у搧璇︽儏鏄剧ず锛氳埍鎴夸粙缁嶏紝閭疆浠嬬粛锛岀編椋燂紝棰勮喘绛�
   	 * @return
   	 */
	public boolean case04861() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		浜у搧璇︽儏
		driver.waitForAllElementLoad(XPATH,".//*[@id='product-detail']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-detail']", DEFVALUE);
		if(con.contains("閾炬帴")||(con.contains("鑸辨埧浠嬬粛")&&con.contains("閭疆浠嬬粛")&&con.contains("缇庨")&&con.contains("濞辫喘"))){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細浜у搧璇︽儏鏄剧ず锛氳埍鎴夸粙缁嶏紝閭疆浠嬬粛锛岀編椋燂紝棰勮喘绛�
   	 * @return
   	 */
	public boolean case04862() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮棰勮椤荤煡
		driver.waitForAllElementLoad(XPATH,"//a[text()='棰勮椤荤煡']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE);
//		棰勮椤荤煡鍐呭
		if(driver.isElementPresent(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/../..", DEFVALUE)){
			String css1 = driver.getAttributeValue(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/../..", "class");//甯﹀皬鏍囬
			String cssa = driver.getAttributeValue(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/..", "class");//甯﹀皬鏍囬
			String css2 = driver.getAttributeValue(XPATH, "//*[text()='鍑鸿璀︾ず鍙婅鏄�']/following-sibling::div[1]", "class");//涓嶅甫灏忔爣棰�
			if(css1.contains("detail-out-shell")||cssa.contains("detail-out-shell")&&css2.contains("detail")){
//				鐐瑰嚮鏌ョ湅鍏ㄩ儴鍑鸿璀︾ず鍜岃鏄�
				if(driver.isElementPresent(XPATH, "//span[@class='js-check-all']", DEFVALUE)){
					driver.click(XPATH, "//span[@class='js-check-all']", DEFVALUE);
				}
				System.out.println("姝ｅ父");
				return true;
			}
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細璐圭敤璇存槑锛氳垂鐢ㄥ寘鍚�佽垂鐢ㄤ笉鍖呭惈
   	 * @return
   	 */
	public boolean case04863() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮璐圭敤璇存槑
		driver.waitForAllElementLoad(XPATH, "//a[text()='璐圭敤璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='璐圭敤璇存槑']", DEFVALUE);
//		璐圭敤璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH, "//h3[text()='璐圭敤璇存槑']/../following-sibling::div", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//h3[text()='璐圭敤璇存槑']/../following-sibling::div", DEFVALUE);
		if(con.contains("璐圭敤鍖呭惈")&&con.contains("璐圭敤涓嶅寘鍚�")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細鍒嗘湡璇存槑锛氬浐瀹氭枃鏈紝鍚屽墠鍙拌鎯呴〉
   	 * @return
	 * @throws Exception 
   	 */
	public boolean case04864() throws Exception {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮鍒嗘湡璇存槑
		driver.waitForAllElementLoad(XPATH, "//a[text()='鍒嗘湡璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鍒嗘湡璇存槑']", DEFVALUE);
//		鍒嗘湡璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-fenqi']//div[@class='detail jsviewmore']", DEFVALUE, TIME_OUT);
		String con1 = driver.getText(XPATH, ".//*[@id='product-fenqi']//div[@class='detail jsviewmore']", DEFVALUE);
		driver.navigateTo("http://www.lvmama.com/tuangou/all/cruise-m0?start=79#list");
		Thread.sleep(1000);
//		鐐瑰嚮绗簩涓骇鍝�
		driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		driver.switchToNewWindow();
//		鍒嗘湡璇存槑鍐呭
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-fenqi']//div[@class='detail jsviewmore']", DEFVALUE, TIME_OUT);
		String con2 = driver.getText(XPATH, ".//*[@id='product-fenqi']//div[@class='detail jsviewmore']", DEFVALUE);
		if(con2.contains(con1)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細棰勫畾椤荤煡锛氭帹鑽愰」鐩�佸嚭琛岃绀哄強璇存槑銆侀��鏀硅鏄�
   	 * @return
   	 */
	public boolean case04865() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮棰勮椤荤煡
		driver.waitForAllElementLoad(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE);
//		棰勮椤荤煡鍐呭
		driver.waitForAllElementLoad(XPATH, "//div[@class='detail-instance-body preorder-notice']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//div[@class='detail-instance-body preorder-notice']", DEFVALUE);
		if(con.contains("鍑鸿璀︾ず鍙婅鏄�")&&con.contains("閫�鏀硅鏄�")||con.contains("鎺ㄨ崘椤圭洰")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細绛捐瘉绛炬敞锛氬悓绾夸笂
   	 * @return
   	 */
	public boolean case04866(String userName,String passWord) {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮绛捐瘉/绛炬敞
		driver.waitForAllElementLoad(XPATH, "//a[text()='绛捐瘉/绛炬敞']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='绛捐瘉/绛炬敞']", DEFVALUE);
//		绛捐瘉/绛炬敞鍐呭
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-visa']//div[@class='detail-instance-body']/p", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, ".//*[@id='product-visa']//div[@class='detail-instance-body']/p", DEFVALUE);
		String title = driver.getCurrentUrl();
		String tit[] = null;
		if(title.contains("deal")){
			tit = title.split("http://www.lvmama.com/tuangou/deal-");
		}
		if(title.contains("sale")){
			tit = title.split("http://www.lvmama.com/tuangou/sale-");
		}
		backlogin(userName,passWord);
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframe", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframe");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, tit[1]);
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		鐐瑰嚮缂栬緫
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='缂栬緫']", DEFVALUE);
		driver.switchToNewWindow();
//		鍒囨崲iframe
		driver.waitForAllElementLoad(ID, "iframeMain", DEFVALUE, TIME_OUT);
		driver.selectFrame(ID, "iframeMain");
//		绛捐瘉鏉愭枡淇℃伅
		driver.waitForAllElementLoad(XPATH, ".//*[@id='tr_1031']/td[2]", DEFVALUE, TIME_OUT);
		String visa = driver.getText(XPATH, ".//*[@id='tr_1031']/td[2]", DEFVALUE);
		if(con.contains(visa)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 閭疆璇︽儏椤碉細椹村弸鐐硅瘎鍚岀嚎涓�
   	 * @return
   	 */
	public boolean case04868() {
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮椹村弸鐐硅瘎
		driver.waitForAllElementLoad(XPATH, "//a[text()='椹村弸鐐硅瘎']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='椹村弸鐐硅瘎']", DEFVALUE);
//		椹村弸鐐硅瘎鍐呭
		driver.waitForAllElementLoad(XPATH, "//div[@id='product-customer-review']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//div[@id='product-customer-review']", DEFVALUE);
		if(con.contains("椹村弸鐐硅瘎")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绉垎鍟嗗煄椤甸潰 锛� 鐐广�愮櫥褰曘�戞寜閽紝鍙互姝ｇ‘寮瑰嚭鐧诲綍妗嗭紝骞剁櫥褰曟垚鍔�
   	 * @return
   	 */
	public boolean case12280(String username,String password) {
//		鐐瑰嚮绾㈠簳鐧藉瓧鐧婚檰
		driver.waitForAllElementLoad(XPATH, "//button[@class='btn btn-pink']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//button[@class='btn btn-pink']", DEFVALUE);
		Login(username,password);
//		鐧婚檰鍚庣敤鎴峰悕
		driver.waitForAllElementLoad(XPATH, "//div[@class='side-box border sidebox1']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//div[@class='side-box border sidebox1']", DEFVALUE);
		if(con.contains(username)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 绉垎鍟嗗煄椤甸潰 锛� 鐐广�愮櫥褰曘�戞寜閽紝鍙互姝ｇ‘寮瑰嚭鐧诲綍妗嗭紝骞剁櫥褰曟垚鍔�
   	 * @return
	 * @throws Exception 
   	 */
	public boolean case12281(String username,String password) throws Exception {
		Thread.sleep(3000);
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮绔嬪嵆鎶㈣喘
		driver.waitForAllElementLoad(XPATH, "//a[text()='绔嬪嵆鎶㈣喘']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='绔嬪嵆鎶㈣喘']", DEFVALUE);
		Login(username,password);
		Thread.sleep(3000);
//		璺宠浆鍒拌鍗曢〉
		String tit = driver.getTitle();
//		鐧婚檰鍚庣敤鎴峰悕
		driver.waitForAllElementLoad(XPATH, "//p[@class='welcome']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//p[@class='welcome']", DEFVALUE);
		if(tit.contains("濉啓璁㈠崟")&&con.contains(username)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
   	 * 鐗瑰崠浼氶棬绁ㄨ鎯呴〉锛� 鐐瑰嚮銆愮珛鍗虫姠璐�戯紝姝ｇ‘寮瑰嚭鐧诲綍妗嗗苟鑳芥纭櫥褰�
   	 * @return
	 * @throws Exception 
   	 */
	public boolean case12282(String username,String password) throws Exception {
		Thread.sleep(3000);
//		鐐瑰嚮绗竴涓骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
		selectDate1();
//		鐐瑰嚮绔嬪嵆鎶㈣喘
		driver.waitForAllElementLoad(XPATH, "//span[text()='绔嬪嵆鎶㈣喘']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//span[text()='绔嬪嵆鎶㈣喘']", DEFVALUE);
		Login(username,password);
		Thread.sleep(3000);
//		璺宠浆鍒拌鍗曢〉
		String tit = driver.getTitle();
//		鐧婚檰鍚庣敤鎴峰悕
		driver.waitForAllElementLoad(XPATH, "//p[@class='welcome']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//p[@class='welcome']", DEFVALUE);
		if(tit.contains("濉啓璁㈠崟")&&con.contains(username)){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細闂ㄧエ浜у搧锛岀偣鍑烩�濅氦閫氭寚鍗椻�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14238() throws Exception{
		driver.waitForAllElementLoad(XPATH, "//ul[@class='tm-qx-cpList']", DEFVALUE, TIME_OUT);
		int num=driver.getTotalElementList2("//ul[@class='tm-qx-cpList']/li");
		
		for (int i = 1; i <= num; i++) {
			driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//h4//a", DEFVALUE);
			driver.switchToNewWindow();
			Thread.sleep(1000);
			driver.scroll("right","200");
			if (driver.isElementPresent(XPATH, "//li[text()='浜ら�氭寚鍗�']", DEFVALUE)
					&&driver.isElementPresent(XPATH, "//h3[text()='浜ら�氭寚鍗�']", DEFVALUE)) {
				break;
			}
			driver.navigateTo("http://www.lvmama.com/tuangou/all/ticket-m0?start=79&sortType=4#list");
			Thread.sleep(1000);
		}
		driver.click(XPATH, "//li[text()='浜ら�氭寚鍗�']", DEFVALUE);
		String text1=driver.getAttributeValue(XPATH, "//li[text()='浜ら�氭寚鍗�']", "class");
		if(text1.equals("active")&&driver.isElementPresent(XPATH, "//h3[text()='浜ら�氭寚鍗�']", DEFVALUE)){
			return true;
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細闂ㄧエ浜у搧锛岀偣鍑烩�濇櫙鍖轰粙缁嶁�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14237() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//li[text()='鏅尯浠嬬粛']", DEFVALUE)) {
			driver.click(XPATH, "//li[text()='鏅尯浠嬬粛']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//li[text()='鏅尯浠嬬粛']", "class");
			if(text.equals("active")&&driver.isElementPresent(XPATH, "//h3[text()='鏅尯浠嬬粛']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細闂ㄧエ浜у搧锛岀偣鍑烩�濇椿鍔ㄤ粙缁嶁�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14236() throws Exception{
		driver.waitForAllElementLoad(XPATH, "//ul[@class='tm-qx-cpList']", DEFVALUE, TIME_OUT);
		int num=driver.getTotalElementList2("//ul[@class='tm-qx-cpList']/li");
		
		for (int i = 1; i <= num; i++) {
//			String text=driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//div[@class='tm-qx-priceBox']/a", DEFVALUE);
//			if (!text.contains("宸插敭缃�")&&!text.contains("鍗冲皢寮�鎶�")) {
				driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//h4//a", DEFVALUE);
				driver.switchToNewWindow();
				Thread.sleep(1000);
				driver.scroll("right","200");
				if (driver.isElementPresent(XPATH, "//li[text()='娲诲姩浠嬬粛']", DEFVALUE)) {
					break;
				}
				driver.navigateTo("http://www.lvmama.com/tuangou/all/ticket-m0?start=79&sortType=4#list");
				Thread.sleep(1000);
//			}
		}
		driver.click(XPATH, "//li[text()='娲诲姩浠嬬粛']", DEFVALUE);
		String text1=driver.getAttributeValue(XPATH, "//li[text()='娲诲姩浠嬬粛']", "class");
		if(text1.equals("active")&&driver.isElementPresent(XPATH, "//h3[@id='activity']", DEFVALUE)){
			return true;
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細闂ㄧエ浜у搧锛岀偣鍑烩�濋瀹氶』鐭モ�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14234() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//li[text()='棰勮椤荤煡']", DEFVALUE)) {
			driver.click(XPATH, "//li[text()='棰勮椤荤煡']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//li[text()='棰勮椤荤煡']", "class");
			if(text.equals("active")&&driver.isElementPresent(XPATH, "//h3[text()='棰勮椤荤煡']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細闂ㄧエ浜у搧锛岀偣鍑烩�濊垂鐢ㄨ鏄庘�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14233() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//li[text()='璐圭敤璇存槑']", DEFVALUE)) {
			driver.click(XPATH, "//li[text()='璐圭敤璇存槑']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//li[text()='璐圭敤璇存槑']", "class");
			if(text.equals("active")&&driver.isElementPresent(XPATH, "//h3[text()='璐圭敤璇存槑']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細绾胯矾鍑哄浜у搧锛岀偣鍑烩�濈璇�/绛炬敞鈥滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14232() throws Exception{
		String text="";
		driver.waitForAllElementLoad(XPATH, "//ul[@class='tm-qx-cpList']", DEFVALUE, TIME_OUT);
		int num=driver.getTotalElementList2("//ul[@class='tm-qx-cpList']/li");
		
		for (int i = 1; i <= num; i++) {
			String str=driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//div[@class='tm-qx-priceBox']/a", DEFVALUE);
			if (!str.contains("宸插敭缃�")&&!str.contains("鍗冲皢寮�鎶�")) {
				driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//h4//a", DEFVALUE);
				driver.switchToNewWindow();
				
				driver.scroll("right","200");
				if (driver.isElementPresent(XPATH, "//a[text()='绛捐瘉/绛炬敞']", DEFVALUE)) {
					break;
				}
				driver.navigateTo("http://www.lvmama.com/tuangou/all/abroad-m0?start=79&sortType=1#list");
				Thread.sleep(1000);
			}
		}
		driver.click(XPATH, "//a[text()='绛捐瘉/绛炬敞']", DEFVALUE);
		int totalCmt=Integer.parseInt(tool.extractNumFromString(driver.getText(ID, "totalCmt", DEFVALUE)));//椹村弸鐐硅瘎鏁�
//	System.out.println(totalCmt);
		if (totalCmt>=2) {
			text=driver.getAttributeValue(XPATH, "//a[text()='绛捐瘉/绛炬敞']", "class");
		}else {
			text=driver.getAttributeValue(XPATH, "//a[text()='棰勮椤荤煡']", "class");
		}
		if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='绛捐瘉/绛炬敞']", DEFVALUE)){
			return true;
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細椹村弸鐐硅瘎---璇ユā鍧椾笅閮借兘姝ｅ父鐐瑰嚮璺宠浆锛屾樉绀烘纭殑鍐呭
	 */
	public boolean case14230() throws Exception{
		checkValidprod();
		String text="";
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//a[text()='椹村弸鐐硅瘎']", DEFVALUE)) {
			driver.click(XPATH, "//a[text()='椹村弸鐐硅瘎']", DEFVALUE);
			int totalCmt=Integer.parseInt(tool.extractNumFromString(driver.getText(ID, "totalCmt", DEFVALUE)));//椹村弸鐐硅瘎鏁�
//			System.out.println(totalCmt);
			if (totalCmt>=2) {
				text=driver.getAttributeValue(XPATH, "//a[text()='椹村弸鐐硅瘎']", "class");
			}else {
				text=driver.getAttributeValue(XPATH, "//a[text()='棰勮椤荤煡']", "class");
			}
			if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='椹村弸鐐硅瘎']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岀偣鍑烩�濋瀹氶』鐭モ�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14226() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE)) {
			driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//a[text()='棰勮椤荤煡']", "class");
			if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='棰勮椤荤煡']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岀偣鍑烩�濆垎鏈熻鏄庘�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14225() throws Exception{
		driver.waitForAllElementLoad(XPATH, "//span[text()='鏇村鍛ㄨ竟娓哥浉鍏充骇鍝�']/../../../ul/li[1]/div[@class='tm-qx-picBox']/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//span[text()='鏇村鍛ㄨ竟娓哥浉鍏充骇鍝�']/../../../ul/li[1]/div[@class='tm-qx-picBox']/a", DEFVALUE);
		driver.switchToNewWindow();
		driver.waitForAllElementLoad(XPATH, "//li/a[text()='鍒嗘湡璇存槑']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//li/a[text()='鍒嗘湡璇存槑']", DEFVALUE);
		String text=driver.getAttributeValue(XPATH, "//li/a[text()='鍒嗘湡璇存槑']", "class");
		if(text.equals("active color-style2")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岀偣鍑烩�濊垂鐢ㄨ鏄庘�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14224() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//a[text()='璐圭敤璇存槑']", DEFVALUE)) {
			driver.click(XPATH, "//a[text()='璐圭敤璇存槑']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//a[text()='璐圭敤璇存槑']", "class");
			if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='璐圭敤璇存槑']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岀偣鍑烩�濊绋嬩粙缁嶁�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14223() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//a[text()='琛岀▼浠嬬粛']", DEFVALUE)) {
			driver.click(XPATH, "//a[text()='琛岀▼浠嬬粛']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//a[text()='琛岀▼浠嬬粛']", "class");
			if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='琛岀▼浠嬬粛']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岀偣鍑烩�濅骇鍝佽鎯呪�滐紝璺宠浆鍒板搴旂殑妯″潡鏄剧ず璇ユā鍧楃殑鍐呭
	 */
	public boolean case14222() throws Exception{
		checkValidprod();
		driver.scroll("right","200");
		if (driver.isElementPresent(XPATH, "//a[text()='浜у搧璇︽儏']", DEFVALUE)) {
			driver.click(XPATH, "//a[text()='浜у搧璇︽儏']", DEFVALUE);
			String text=driver.getAttributeValue(XPATH, "//a[text()='浜у搧璇︽儏']", "class");
			if(text.equals("active color-style2")&&driver.isElementPresent(XPATH, "//h3[text()='浜у搧璇︽儏']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 *  鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岄�夋嫨妯帓鏃ュ巻涓湁浠锋牸鐨勬棩鏈熸垚鍔熻繘鍏ヤ笅鍗曢〉
	 */
	public boolean case14219() throws Exception{
		checkValidprod();
		driver.waitForAllElementLoad(XPATH, "//div[@class='ft-price-box-inner clearfix']/ul[@class='clearfix']", DEFVALUE, TIME_OUT);
		int size=driver.getTotalElementList2("//div[@class='ft-price-box-inner clearfix']/ul[@class='clearfix']/li");
		
		for(int i=1;i<=size;i++){
			String text=driver.getAttributeValue(XPATH, "//div[@class='ft-price-box-inner clearfix']/ul[@class='clearfix']/li["+i+"]", "class");
			if(text.equals("")){
				driver.click(XPATH, "//div[@class='ft-price-box-inner clearfix']/ul[@class='clearfix']/li["+i+"]", DEFVALUE);
				Thread.sleep(2000);
				if(driver.isElementPresent(XPATH, "//table/tbody/tr[1]/td[@class='clearfix']/a[@class='price-buy price-buy-shoot']", DEFVALUE)){
					driver.click(XPATH, "//table/tbody/tr[1]/td[@class='clearfix']/a[@class='price-buy price-buy-shoot']", DEFVALUE);
					if(driver.isElementPresent(XPATH, "//span[text()='1.濉啓璁㈠崟淇℃伅']", DEFVALUE)){
						return true;
					}
				}
				break;
			}
		}
		return false;
	}
	/**
	 * 鑷姩鍖栨祴璇曪細绾胯矾浜у搧锛岄�夋嫨鍙屾湀鏃ュ巻涓湁浠锋牸鐨勬棩鏈燂紝鐐瑰嚮鎶㈣喘锛岃繘鍏ヤ笅鍗曢〉
	 */
	public boolean case14220() throws Exception{
		checkValidprod();
		//鏂扮増椤甸潰---鍒ゆ柇椤甸潰鏄惁瀛樺湪鈥滀环鏍兼棩鍘嗏��
		if(driver.isElementPresent(XPATH, "//div[@class='ft-pc js-pop-calender']", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH, "//div[@class='ft-pc js-pop-calender']", DEFVALUE, TIME_OUT);
			driver.excuteJS("arguments[0].click()", driver.getTotalElementList3("//div[@class='ft-pc js-pop-calender']").get(0));
			//鍒ゆ柇鏄惁寮瑰嚭浠锋牸鏃ュ巻
			if(driver.isElementPresent(XPATH, "//div[@class='ui-calendar ui-calendar-big']", DEFVALUE)){
				selectDate();
			}else{
				System.out.println("閫夋嫨鏃ユ湡澶辫触锛�");
			}		
		}else{
			selectDate();
		}
		//鏀圭増鍚庘�滄姠璐�濇寜閽�
		if(driver.isElementPresent(XPATH, "//table/tbody/tr[1]/td[@class='clearfix']/a[@class='price-buy price-buy-shoot']", DEFVALUE)){
			driver.waitForAllElementLoad(XPATH, "//table/tbody/tr[1]/td[@class='clearfix']/a[@class='price-buy price-buy-shoot']", DEFVALUE, TIME_OUT);
			driver.click(XPATH, "//table/tbody/tr[1]/td[@class='clearfix']/a[@class='price-buy price-buy-shoot']", DEFVALUE);
			if(driver.isElementPresent(XPATH, "//span[text()='1.濉啓璁㈠崟淇℃伅']", DEFVALUE)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 鐗瑰崠浼氳鎯呴〉鈥斺�旀绱㈠嚭鏈夋晥鍙敭浜у搧
	 * @throws Exception
	 */
	public void checkValidprod() throws Exception{
		driver.waitForAllElementLoad(XPATH, "//ul[@class='tm-qx-cpList']", DEFVALUE, TIME_OUT);
		int num=driver.getTotalElementList2("//ul[@class='tm-qx-cpList']/li");
//		System.out.println(num);
		for (int i = 1; i <= num; i++) {
			String text=driver.getText(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//div[@class='tm-qx-priceBox']/a", DEFVALUE);
//			System.out.println(text);
			if (!text.contains("宸插敭缃�")&&!text.contains("鍗冲皢寮�鎶�")) {
				driver.click(XPATH, "//ul[@class='tm-qx-cpList']/li["+i+"]//h4//a", DEFVALUE);
				break;
			}
		}
		driver.switchToNewWindow();
	}
	/**
	 * 鐗瑰崠浼氳鎯呴〉闂ㄧエ棰勫畾椤荤煡-鍏ュ洯椤荤煡-鏈�涓嬫柟鏂板鍏ュ洯鍦扮偣瀛楁
	 */
	public boolean case17712() throws Exception{
//		鐐瑰嚮绗竴涓棬绁ㄧ壒鍗栦骇鍝�
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//a[@class='tm-qx-ljqgBtn']", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鐐瑰嚮棰勮椤荤煡
		driver.waitForAllElementLoad(XPATH, ".//*[@id='destorder']/div/ul/li[text()='棰勮椤荤煡']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, ".//*[@id='destorder']/div/ul/li[text()='棰勮椤荤煡']", DEFVALUE);
//		鍏ュ洯椤荤煡涓殑鍐呭
		driver.waitForAllElementLoad(XPATH, "//h5[text()='鍏ュ洯椤荤煡']/following-sibling::p[1]", DEFVALUE, TIME_OUT);
		String text = driver.getText(XPATH, "//h5[text()='鍏ュ洯椤荤煡']/following-sibling::p[1]", DEFVALUE);
		if(text.contains("鍏ュ洯鍦扮偣")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
	 * 鐗瑰崠浼氳鎯呴〉---褰撳湴娓镐骇鍝佸悗鍙扮被鍒細鍥藉唴-鐭嚎锛屽浗鍐�-闀跨嚎----鍓嶅彴鏄剧ず锛氬浗鍐呮父锛涘悗鍙扮被鍒細鍑哄/娓境鍙�--鍓嶅彴鏄剧ず锛氬嚭澧冩父
	 */
	public boolean case14450(String userName,String passWord) throws Exception{
//		鐐瑰嚮绗竴涓浗鍐呮父鐗瑰崠浜у搧
		if(driver.isElementPresent(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE)){
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		}else{
//			鐐瑰嚮绗簩涓骇鍝�
			driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[2]//h4/a", DEFVALUE);
		}
		driver.switchToNewWindow();
//		鍥藉唴娓镐骇鍝両D
		driver.waitForAllElementLoad(XPATH, "//input[@id='IProductId']", DEFVALUE, TIME_OUT);
		String productIdIn = driver.getAttributeValue(XPATH, "//input[@id='IProductId']", "value");
//		鍏ュ洯椤荤煡涓殑鍐呭
		driver.navigateTo("http://www.lvmama.com/tuangou/all/abroad-m0?start=79#list");
//		鐐瑰嚮绗竴涓嚭澧冩父鐗瑰崠浜у搧
		driver.waitForAllElementLoad(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		
		driver.switchToNewWindow();
//		鍑哄娓镐骇鍝両D
		driver.waitForAllElementLoad(XPATH, "//input[@id='IProductId']", DEFVALUE, TIME_OUT);
		String productIdOut = driver.getAttributeValue(XPATH, "//input[@id='IProductId']", "value");
//		鍚庡彴鐧婚檰
		backlogin(userName,passWord);
		driver.waitForAllElementLoad(XPATH, "//em[text()='VST']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//em[text()='VST']", DEFVALUE);//鐐瑰嚮vst
		driver.waitForAllElementLoad(XPATH, "//a[@permid='3470']/../span", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@permid='3470']/../span", DEFVALUE);//鐐瑰嚮浜у搧绠＄悊
		driver.scrollToElement("//a[@permid='3480']");
		driver.waitForAllElementLoad(XPATH, "//a[@permid='3480']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@permid='3480']", DEFVALUE);//鐐瑰嚮鏍囧噯浜у搧绠＄悊
		driver.selectFrame(XPATH, "//iframe[@class='iframe']");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, productIdIn);
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		鐐瑰嚮缂栬緫
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='缂栬緫']", DEFVALUE);
		driver.switchToNewWindow();
		driver.selectFrame(ID, "iframeMain");
//		浜у搧绫诲瀷
		driver.waitForAllElementLoad(XPATH, "//select[@class='lt-category']/option[2]", DEFVALUE, TIME_OUT);
		String con1 = driver.getText(XPATH, "//select[@class='lt-category']/option[2]", DEFVALUE);
		driver.navigateTo("http://super.lvmama.com/pet_back/index.do");
		driver.excuteJS("javascript:iframeRedirect('/vst_back/prod/product/findProductList.do','3480');");
		driver.selectFrame(ID, "iframe");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, productIdOut);
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		鐐瑰嚮缂栬緫
		driver.waitForAllElementLoad(XPATH, "//a[text()='缂栬緫']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='缂栬緫']", DEFVALUE);
		driver.switchToNewWindow();
		driver.selectFrame(ID, "iframeMain");
//		浜у搧绫诲瀷
		driver.waitForAllElementLoad(XPATH, "//select[@class='lt-category']/option[2]", DEFVALUE, TIME_OUT);
		String con2 = driver.getAttributeValue(XPATH, "//input[@value='FOREIGNLINE'][@type='radio']", "checked");
		if(con1.contains("鍥藉唴-鐭嚎")||con1.contains("鍥藉唴-闀跨嚎")&&con2.contains("")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
	 * 闂ㄧエ鍟嗗搧 鍦ㄥ悗鍙拌缃棤鏁�, 鍒嗛攢宸叉樉绀烘棤鏁堜笉鍙敭, 鍓嶅彴璇︽儏椤垫樉绀哄凡涓嬬嚎
	 */
	public boolean case17713(String userName,String passWord) throws Exception{
//		浜у搧鐘舵��
		driver.waitForAllElementLoad(XPATH, "//span[@class='product_btn product_end_btn fl']", DEFVALUE, TIME_OUT);
		String con = driver.getText(XPATH, "//span[@class='product_btn product_end_btn fl']", DEFVALUE);
//		鍚庡彴鐧婚檰
		backlogin(userName,passWord);
		driver.waitForAllElementLoad(XPATH, "//em[text()='VST']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//em[text()='VST']", DEFVALUE);//鐐瑰嚮vst
		driver.waitForAllElementLoad(XPATH, "//a[@permid='3470']/../span", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@permid='3470']/../span", DEFVALUE);//鐐瑰嚮浜у搧绠＄悊
		driver.scrollToElement("//a[@permid='3480']");
		driver.waitForAllElementLoad(XPATH, "//a[@permid='3480']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[@permid='3480']", DEFVALUE);//鐐瑰嚮鏍囧噯浜у搧绠＄悊
		driver.selectFrame(XPATH, "//iframe[@class='iframe']");
//		杈撳叆浜у搧ID
		driver.waitForAllElementLoad(XPATH,"//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, TIME_OUT);
		driver.input(XPATH, "//span[text()='浜у搧ID']/following-sibling::input", DEFVALUE, "595980");
//		鐐瑰嚮鏌ヨ
		driver.waitForAllElementLoad(XPATH, "//a[text()='鏌ヨ']", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//a[text()='鏌ヨ']", DEFVALUE);
//		浜у搧鐘舵��
		driver.waitForAllElementLoad(XPATH, "//tr[@sensitive='N']/td[4]/span", DEFVALUE, TIME_OUT);
		String text = driver.getText(XPATH, "//tr[@sensitive='N']/td[4]/span", DEFVALUE);
		if(con.contains("宸蹭笅绾�")&&text.contains("鏃犳晥")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
	
	/**
	 * 鐗瑰崠浼氳鎯呴〉锛氱嚎璺拰闂ㄧエ鍘绘帀鐩镐技浜у搧鍔熻兘
	 */
	public boolean case18777() throws Exception{
//		鐐瑰嚮绗竴涓棬绁ㄧ壒鍗栦骇鍝�
		driver.waitForAllElementLoad(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		driver.switchToNewWindow();
//		鍔熻兘妯″潡
		driver.waitForAllElementLoad(XPATH, ".//*[@id='destorder']/div/ul", DEFVALUE, TIME_OUT);
		String conTicket = driver.getText(XPATH, ".//*[@id='destorder']/div/ul", DEFVALUE);
		driver.navigateTo("http://www.lvmama.com/tuangou/all/abroad-m0?start=79#list");
//		鐐瑰嚮绗竴涓嚭澧冩父鐗瑰崠浜у搧
		driver.waitForAllElementLoad(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		driver.switchToNewWindow();
//		鍔熻兘妯″潡
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-detail-header']", DEFVALUE, TIME_OUT);
		String conAbroad = driver.getText(XPATH, ".//*[@id='product-detail-header']", DEFVALUE);
		driver.navigateTo("http://www.lvmama.com/tuangou/all/china-m0?start=79#list");
//		鐐瑰嚮绗竴涓浗鍐呮父鐗瑰崠浜у搧
		driver.waitForAllElementLoad(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		driver.switchToNewWindow();
//		鍔熻兘妯″潡
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-detail-header']", DEFVALUE, TIME_OUT);
		String conChina = driver.getText(XPATH, ".//*[@id='product-detail-header']", DEFVALUE);
		driver.navigateTo("http://www.lvmama.com/tuangou/all/surround-m0?start=79#list");
//		鐐瑰嚮绗竴涓懆杈规父鐗瑰崠浜у搧
		driver.waitForAllElementLoad(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE, TIME_OUT);
		driver.click(XPATH, "//div[@class='tm-leftBox']/div[1]//ul[@class='tm-qx-cpList']/li[1]//h4/a", DEFVALUE);
		driver.switchToNewWindow();
//		鍔熻兘妯″潡
		driver.waitForAllElementLoad(XPATH, ".//*[@id='product-detail-header']", DEFVALUE, TIME_OUT);
		String conSurround = driver.getText(XPATH, ".//*[@id='product-detail-header']", DEFVALUE);
		if(!conTicket.contains("鐩镐技浜у搧")&&!conAbroad.contains("鐩镐技浜у搧")&&!conChina.contains("鐩镐技浜у搧")&&!conSurround.contains("鐩镐技浜у搧")){
			System.out.println("姝ｅ父");
			return true;
		}
		return false;
	}
//zhangxingxing
	//鐗瑰崠浼氬墿浣欏簱瀛�
	public boolean case58317(){
		
		int div=driver.getTotalElementList2("html/body/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/ul/li");
		for(int i=1;i<=div;i++){
			driver.waitForAllElementLoad(XPATH, "/html/body/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/ul/li["+i+"]/div[2]/div[3]/p", DEFVALUE,TIME_OUT);
			String b=driver.getText(XPATH, "/html/body/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/ul/li["+i+"]/div[2]/div[3]/p", DEFVALUE);
			String num=b.split("浠�")[0].trim();
			
			if(num=="0"){
				return false;
			}
			
		}
		System.out.println("ok");
		return true;
	}
	
	/*璺熷洟娓歌鎯呴〉椤甸潰锛屾鏌ヤ骇鍝乮d鏄惁鏄剧ず锛�
	 * zhangxingxing
	 * */
	public boolean case59479() throws Exception{
		driver.openPageAndMaximize("http://dujia.lvmama.com/freetour/227972?losc=222346&ict=i");
		//driver.waitForAllElementLoad(ID, "navigate-list", DEFAULT, TIMEOUT);
		String a=driver.getText(XPATH, "html/body/div[5]/div[3]/div[1]/div[4]/div[2]", DEFVALUE);
		
		//driver.click(XPATH, "//a[text()='棰勮椤荤煡']", DEFAULT);
		if(a!=null){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * 绾胯矾璇︽儏椤电偣鍑绘敹钘忥紝濡傛灉鏄湭鐧诲綍鐘舵�侊紝鍒欒烦杞埌鐧诲綍椤甸潰锛�
	 * zhangxingxing
	 * */
	public boolean case59483() throws Exception{
		driver.openPageAndMaximize("http://dujia.lvmama.com/freetour/227972?losc=222346&ict=i");
		driver.click(XPATH, "//*[@class='product_top_r']/div[2]/p", DEFVALUE);
		driver.waitForAllElementLoad(XPATH, "//*[class='contLeft']", DEFVALUE, TIME_OUT);
		//driver.switchToNewWindow();
		//frontlogin(userName,passWord);
		//driver.outIframe();
		if(driver.isElementPresent(XPATH, "//*[id='fast-login']", DEFVALUE)){
			return true;
		}else{
			return false;
		}
	}
	
	
	/*
	 * 绾胯矾璇︽儏椤电偣鍑绘敹钘忥紝鐧诲綍鐘舵�佷笅锛岃嫢鏄凡鏀惰棌鐘舵�佷笅锛屽垯榧犳爣绉讳笂鍘荤殑鏃跺�欐樉绀轰负鍙栨秷鏀惰棌锛岃嫢娌℃湁鏀惰棌锛岀偣鍑诲彲鏀惰棌锛�
	 * zhangxingxing
	 * */
	public boolean case59701() throws Exception{
		driver.openPageAndMaximize("http://dujia.lvmama.com/freetour/227972?losc=222346&ict=i");
		
		String str=driver.getText(XPATH, "//*[@class='product_top_r']/div[2]/p", DEFVALUE);
		if("宸叉敹钘�".equals(str)){
			driver.moveToElement(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[4]");
			driver.getText(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[4]", DEFVALUE);
			return true;
		}
		else if("鏀惰棌".equals(str)){
			driver.click(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[2]", DEFVALUE);
			driver.getText(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[3]", DEFVALUE);
			return true;
		}else{
			return false;
		}
	}
		/*
		 * 绾胯矾璇︽儏椤电偣鍑绘敹钘忥紝濡傛灉鏄湭鐧诲綍鐘舵�侊紝鍒欒烦杞埌鐧诲綍椤甸潰锛�
		 * zhangxingxing
		 * */
		public boolean case59850() throws Exception{
			driver.openPageAndMaximize("http://dujia.lvmama.com/freetour/227972?losc=222346&ict=i");
			driver.click(XPATH, "//*[@class='product_top_r']/div[2]/p", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//*[class='contLeft']", DEFVALUE, TIME_OUT);
			//driver.switchToNewWindow();
			//frontlogin(userName,passWord);
			//driver.outIframe();
			if(driver.isElementPresent(XPATH, "//*[id='fast-login']", DEFVALUE)){
				return true;
			}else{
				return false;
			}
		}
		
		
		/*
		 * 绾胯矾璇︽儏椤电偣鍑绘敹钘忥紝鐧诲綍鐘舵�佷笅锛岀偣鍑绘敹钘忥紝鏀惰棌鎴愬姛
		 * zhangxingxing
		 * */
		public boolean case59701(String username,String password) throws Exception{
			//Login(username, password);
			driver.click(XPATH, "//a[@id='top_login']", DEFVALUE);//鐐瑰嚮璇风櫥褰�
			driver.waitForAllElementLoad(XPATH, "//div[@class='user-box']/ul/li[1]", DEFVALUE, TIME_OUT);
			driver.click(XPATH, "//input[@id='usernameOrNum']", DEFVALUE);
			driver.input(XPATH, "//input[@id='usernameOrNum']", DEFVALUE, "loadtest001190");
			driver.click(XPATH, "//input[@id='passwordNum']", DEFVALUE);
			driver.input(XPATH, "//input[@id='passwordNum']", DEFVALUE, "111111");
			driver.click(XPATH, "//a[@id='loginButton']", DEFVALUE);
			driver.openPageAndMaximize("http://dujia.lvmama.com/freetour/227972?losc=222346&ict=i");////div[@class='product_top_booking']/dl[4]/dd/span
			driver.waitForAllElementLoad(XPATH, "//div[@class='product_top_r']/div[2]/p/span[2]", DEFVALUE, TIME_OUT);
			//driver.click(XPATH, "//div[@class='product_top_r']/div[2]/p[1]/span[2]", DEFVALUE);
			String str=driver.getText(XPATH, "//div[@class='product_top_r']/div[2]/p[1]/span[3]", DEFVALUE);
			if(str.contains("宸叉敹钘�")){
				System.out.println(str);
				return true;
			}
//			else if("鏀惰棌".equals(str)){
//				driver.click(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[2]", DEFVALUE);
//				driver.getText(XPATH, "//*[@class='product_top_r']/div[2]/p[1]/span[3]", DEFVALUE);
//				return true;
//			}
			else{
				return false;
			}
		}
		/**
	   	 * 绾胯矾璇︽儏椤碉細鏄剧ず浜у搧鐨勫ソ璇勭巼锛屾爣绛�
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case59906() {
			int num=driver.getTotalElementList("//div[@class='theme-section theme-section-sm clearfix']//div[@class='container-tabs']", "a");
			System.out.println(num);
			for(int i=1;i<=num;i++){
				
				//鐐瑰嚮绗竴涓骇鍝�
				////div[@class='theme-section theme-section-sm clearfix']//div[2]/div[1]/ul/li[1]/a[2]
				if(driver.isElementPresent(XPATH, "//div[@class='theme-section theme-section-sm clearfix']//div[@class='container-tabs']/a["+i+"]", DEFVALUE)){
					driver.moveToElement(XPATH, "//div[@class='theme-section theme-section-sm clearfix']//div[@class='container-tabs']/a["+i+"]");
//					driver.click(XPATH, "//div[@class='theme-section theme-section-sm clearfix']//div[@class='container-tabs']/a["+i+"]", DEFVALUE);
					if(driver.isElementPresent(XPATH, "//div[@class='theme-section theme-section-sm clearfix']/div[2]/div["+i+"]/ul/li[1]/a[1]", DEFVALUE)){
						driver.click(XPATH, "//div[@class='theme-section theme-section-sm clearfix']/div[2]/div["+i+"]/ul/li[1]/a[1]", DEFVALUE);
					}
					else{
						continue;
					}
					
				}else{
					System.out.println("false");
				}
				driver.switchToNewWindow();
//				濂借瘎鐜�
				if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
					driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
				}
				
				if(driver.isElementPresent(XPATH, "//div[@class='product_top_price_box']/p[@class='product_top_dp']", DEFVALUE)){
					System.out.println("姝ｅ父");
					return true;
					//driver.getText(XPATH, "//div[@class='product_top_price_box']/p[@class='product_top_dp']", DEFVALUE);
					
					//driver.navigateTo("http://www.lvmama.com/jijiu/?losc=087900&ict=i");
					//continue;
				}else{
					return false;
				}
			    }
			return true;
			}
		
		/**
	   	 *绾胯矾璇︽儏椤碉細 浠锋牸鏃ュ巻琛ㄧ炕椤垫寜閽紝鐐瑰嚮鍚戜笅鐨勭澶达紝缈婚〉鍒颁笅涓�涓湀  	
	   	 *zhangxingxing	
	   	 */
	   	public boolean case60068(){
	   		driver.waitForAllElementLoad(XPATH,"//div[@class='calendar-tabs clearfix']", DEFVALUE, TIME_OUT);
	   		//鍏虫帀寮瑰嚭鏉ョ殑椤甸潰
	   		if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
				driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
			}
	   		int num=driver.getTotalElementList2("//div[@class='calendar-tabs clearfix']");
	   		
	   		if(driver.isElementPresent(XPATH, "//div[@class='calendar-tabs clearfix']//div[@class='calendar-tab active']", DEFVALUE)){
	   			//driver.click(XPATH, "//div[@class='ui-calendar ui-calendar-big']/span[2]", DEFVALUE);
	   			driver.click(XPATH, "//div[@class='calendar-tabs-next disabled']", DEFVALUE);
	   			String str = driver.getText(XPATH, "//div[@class='calendar-tabs clearfix']//div[5]", DEFVALUE);
	   			if(str.contains("2017骞�11鏈�")){
	   				System.out.println("宸茬炕鍒颁笅鏈�");
	   				return true;
	   			}else{
	   				System.out.println("鏈炕鍒颁笅鏈�");
	   				return false;
	   			}  			
	   		}
	   		System.out.println("鏃犱笅鏈堟寜閽�");
	   		return false;
	   	}
	   	
	   	
	   	/**
	   	 *绾胯矾璇︽儏椤�:鐐瑰嚮浜у搧璇︽儏锛岃烦杞埌浜у搧璇︽儏鍐呭锛�	
	   	 *zhangxingxing	
	   	 */
	   	public boolean case60075(){
	   		driver.waitForAllElementLoad(XPATH,"//div[@class='float_nav']", DEFVALUE, TIME_OUT);
	   		//鍏虫帀寮瑰嚭鏉ョ殑椤甸潰
	   		if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
				driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
			}
	   		driver.click(XPATH, "//div[@class='float_nav']//li[1]", DEFVALUE);
	   		String str=driver.getText(XPATH, "//div[@class='detail_content_tit']", DEFVALUE);
	   		if("浜у搧璇︽儏".equals(str)){
	   			System.out.println("ok");
	   			return true;
	   		}
	   		
	   		return false;
	   	}
	   	
	   	/**
	   	 *绾胯矾璇︽儏椤�:鐐瑰嚮琛岀▼浠嬬粛锛岃烦杞埌鐩稿搴旂殑浣嶇疆锛�	
	   	 *zhangxingxing	
	   	 */
	   	public boolean case60076(){
	   		driver.waitForAllElementLoad(XPATH,"//div[@class='float_nav']", DEFVALUE, TIME_OUT);
	   		//鍏虫帀寮瑰嚭鏉ョ殑椤甸潰
	   		if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
				driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
			}
	   		driver.click(XPATH, "//div[@class='float_nav']/ul/li[2]/a", DEFVALUE);
	   		String str=driver.getText(XPATH, "//div[@class='product-modules clearfix']/div[4]/span", DEFVALUE);
	   		if(str.contains("琛岀▼浠嬬粛")){
	   			System.out.println("ok");
	   			return true;
	   		}
	   		
	   		return false;
	   	}
	   	
	   	/**
	   	 * 绾胯矾璇︽儏椤�--鍥藉唴鑷敱琛岋細鐐瑰嚮浜у搧鐨勭珛鍗抽瀹氾紝璺宠浆鍒颁笅鍗曢〉闈紱
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60077() {
			driver.waitForAllElementLoad(XPATH,"//div[@class='float_nav']", DEFVALUE, TIME_OUT);
	   		//鍏虫帀寮瑰嚭鏉ョ殑椤甸潰
	   		if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
				driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
			}
	   		//driver.click(XPATH, "//div[@class='float_nav']//li[2]", DEFVALUE);
	   		//鐧诲綍
	   		//frontlogin(userName,passWord);
	   		//鐐瑰嚮鍑烘父浜烘暟涓嬮潰鐨勭珛鍗抽瀹�
	   		driver.click(XPATH, "//div[@class='product_top_booking']//a[@class='preorder-confirm-button btn btn-xl btn-orange']", DEFVALUE);
	   		driver.waitForAllElementLoad(XPATH, "//li[@class='ui-step1 ui-step-start ui-step-active']/span", DEFVALUE, TIME_OUT);
	   		//鑾峰彇璺宠浆鐨勯〉闈�
	   		String str=driver.getText(XPATH, "//li[@class='ui-step1 ui-step-start ui-step-active']/span", DEFVALUE);
	   		if(str.contains("濉啓璁㈠崟")){
	   			System.out.println("ok");
	   			return true;
	   		}
	   		return false;
		}
		
		/**
	   	 * 閭疆缁勫悎浜у搧璇︽儏椤�--鍑哄彂鍦帮細閭疆缁勫悎浜у搧璇︽儏椤碉紝鏌ョ湅鍑哄彂鍦帮紱
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case58367() {
			int num=driver.getTotalElementList("//div[@class='products-tab special-tit f22 yh']", "a");
			System.out.println(num);
			for(int i=1;i<num;i++){
				driver.moveToElement(XPATH, "//div[@class='products-tab special-tit f22 yh']//a["+i+"]");
				driver.click(XPATH, "//div[@class='products-tab special-tit f22 yh']//a["+i+"]", DEFVALUE);
				if(driver.isElementPresent(XPATH, "//div[@class='products special-products']["+i+"]//ul[1]//li[1]", DEFVALUE)){
					driver.click(XPATH, "//div[@class='products special-products']["+i+"]//ul[1]//li[1]/a[1]", DEFVALUE);
				}
				else{
					System.out.println(false);
					continue;
				}
			}
			driver.switchToNewWindow();
			//driver.isElementPresent(XPATH, "//div[@class='crumbs clearfix']//p[@class='crumbs-link']//a[1]", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='liner-summary-detail']//div[@class='liner-detail-review']//span[2]", DEFVALUE, TIME_OUT);
			String str=driver.getText(XPATH, "//div[@class='liner-summary-detail']//div[@class='liner-detail-review']//span[2]", DEFVALUE);
			System.out.println(str);
			if(str.contains("鍑哄彂鍦板嚭鍙戞腐鍙ｈ繑鍥炲湴")){
				System.out.println("ok");
				//continue;
			}else{
				return false;
			}
	   		return true;
		}
		
		/**
	   	 * 閭疆缁勫悎浜у搧璇︽儏椤�--宸茬櫥褰曟儏鍐典笅锛岀偣鍑绘敹钘忥紝鏀惰棌鎴愬姛锛屾湭鐧诲綍鎯呭喌鍒欒烦杞埌鐧诲綍椤甸潰
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case58307() {
			driver.click(XPATH, ".//*[@id='favorite']", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, ".//*[@id='favorite']", DEFVALUE, TIME_OUT);
			
	   		return true;
		}
		
		
		/**
	   	 * 閭疆璇︽儏椤�--棣栧睆--浠锋牸鏉垮潡--鏌ョ湅鍏ㄩ儴鎴垮瀷
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60669() {
			driver.click(XPATH, "//div[@class='clearfix']//a[@class='lpb-room']", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='clearfix']//a[@class='lpb-room']", DEFVALUE, TIME_OUT);
			driver.click(XPATH, "//div[@class='clearfix']//div[@class='lpb-room-list']//a", DEFVALUE);
			if(driver.isElementPresent(XPATH, ".//*[@id='cabin-ncf']/h4", DEFVALUE)){
				return true;
			}
			
	   		return false;
		}
		
		/**
	   	 * 璇︽儏椤�--棣栧睆--绔嬪嵆棰勮--鈥滃紑濮嬮璁⑩��
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60671() {
			driver.click(XPATH, "//div[@class='liner-summary-detail']//dl[@class='liner-info clearfix']//a", DEFVALUE);
			if(driver.isElementPresent(XPATH, ".//*[@id='cabin-ncf']/h4", DEFVALUE)){
				return true;
			}
			
	   		return false;
		}
		

		/**
	   	 * 璇︽儏椤�--棣栧睆--浜嗚В鏇村--閭疆鍏徃+閭疆鍙�
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60672() {
			driver.click(XPATH, "//div[@class='img_scroll_all']//div[1]//a//p[1]//span", DEFVALUE);
			if(driver.isElementPresent(XPATH, "//div[@class='seo-head-box clearfix']//h3", DEFVALUE)){
				return true;
			}
			
	   		return false;
		}
		
		
		/**
	   	 * 閭疆璇︽儏椤�--棣栧睆--閭疆浠嬬粛--鍚勪釜tab妫�楠岋紝璺宠浆姝ｅ父
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60723() {
			//鐐瑰嚮閭疆浠嬬粛
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[1]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[1]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-introduction']/div[1]/em/b", DEFVALUE)){
				System.out.println("1");
				//return true;
			}
			//鐐瑰嚮琛岀▼浠嬬粛
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[2]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[2]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-route']/div[1]/em/b", DEFVALUE)){
				System.out.println("2");
				//return true;
			}
			//鐐瑰嚮璐圭敤椤荤煡
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[3]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[3]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-fee']/div[1]/em/b", DEFVALUE)){
				System.out.println("3");
				//return true;
			}
			//鐐瑰嚮棰勮椤荤煡
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[4]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[4]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-notice']/div[1]/em/b", DEFVALUE)){
				System.out.println("4");
				//return true;
			}
			//鐐瑰嚮绛捐瘉璇存槑
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[5]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[5]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-visa']/div[1]/em/b", DEFVALUE)){
				System.out.println("5");
				//return true;
			}
			//鐐瑰嚮椹村弸鐐硅瘎
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[6]//a", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[6]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-comment']/div[1]/em/b", DEFVALUE)){
				System.out.println("6");
				//return true;
			}
			//鐐瑰嚮鐩稿叧娓歌
			driver.click(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[7]", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='float_nav']//ul[@id='float_nav']/li[7]//a", DEFVALUE, TIME_OUT);
			if(driver.isElementPresent(XPATH, ".//*[@id='pd-travels']/div[1]/em/", DEFVALUE)){
				System.out.println("7");
				return true;
			}
	   		return false;
	   		
		}
		
		/**
	   	 * 涔版柇璧勬簮棰勬帶鍟嗗搧缁戝畾涓庤В缁戝姛鑳芥祴璇�
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case60705() {
			//鐧诲綍鍚庡彴
			driver.input(XPATH, "//div[@class='input username']//input", DEFVALUE, "admin");
			driver.input(XPATH, "//div[@class='input password']//input", DEFVALUE , "1234567!");
			driver.click(XPATH, "//button[@id='submitBtn']", DEFVALUE);
			//杩涘叆鍚庡彴椤甸潰
			driver.waitForAllElementLoad(XPATH, ".//*[@id='myTab']/li[1]/a", DEFVALUE ,TIME_OUT);
			//鐐瑰嚮浜у搧绠＄悊
			driver.click(XPATH, "//div[@class='sidebar-box']//dl[@class='nav nav-sidebar tab-vst']/dt", DEFVALUE);
			////div[@class='sidebar-box']/dl[3]/dd/dl[5]//span
			driver.click(XPATH, "//div[@class='sidebar-box']/dl[3]/dd/dl[5]//span", DEFVALUE);
//			driver.click(XPATH, "//div[@class='sidebar-box']//dl[@class='nav nav-sidebar tab-vst active']//dd//dl[5]//span", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='sidebar-box']/dl[3]//dd/dl[5]//dd/a[9]", DEFVALUE, TIME_OUT);
			//鐐瑰嚮涔版柇璧勬簮棰勬帶璁剧疆
//			driver.click(XPATH, "//div[@class='sidebar-box']//dl[@class='nav nav-sidebar tab-vst active']//dd//dl[5]//dd//a[11]", DEFVALUE);
			driver.click(XPATH, "//div[@class='sidebar-box']/dl[3]//dd/dl[5]//dd/a[9]", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//iframe[@class='iframe']", DEFVALUE, TIME_OUT);
			//渚涘簲鍟唅d杈撳叆3411鐐瑰嚮鏌ヨ
			driver.selectFrame(XPATH, "//iframe[@class='iframe']");
			driver.waitForAllElementLoad(XPATH, ".//*[@id='supplierId']", DEFVALUE, TIME_OUT);
			driver.input(XPATH, ".//*[@id='supplierId']", DEFVALUE, "3410");//div[@class='row']//div[3]//input
			driver.click(XPATH, "//div[@class='btn-group']//a[1]", DEFVALUE);
			//鐐瑰嚮
			driver.click(XPATH, "//div[@class='resource-table']//table[@class='table table-border']//tbody//tr[1]//td[15]//a[@class='mr5 Binding']", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//div[@class='clearfix']//ul//li[1]", DEFVALUE, TIME_OUT);
			
			//driver.selectFrame(XPATH, "//iframe[@class='iframe']");
			driver.check(XPATH, "//div[@class='tab-pane active unbind-product clearfix']//table[@class='table table-border']//tbody//tr[1]//td[1]//input");
			String str=driver.getText(XPATH, "//div[@class='tab-pane active unbind-product clearfix']//table[@class='table table-border']//tbody//tr[1]//td[5]",DEFVALUE);
			System.out.println("str鐨勫��"+str);
			//鐐瑰嚮纭缁戝畾锛屽鏋滃脊鍑虹殑鏄柊澧炴垚鍔熷垯缁х画涓嬩竴姝ワ紝鍏朵粬鎯呭喌鍒欒繑鍥炲埌椤甸潰閫夋嫨涓�鏉″彲浠ユ柊澧炴垚鍔熺殑鏁版嵁
			driver.click(XPATH, "//div[@class='footer-right']//a", DEFVALUE);
			//Thread.sleep(2000);
			String btn="";
			if(driver.isAlertPresent()){
				btn=driver.getAlertText();
				System.out.println(btn);
			}
			//String btn=driver.getAlertText();
//			driver.acceptAlert();
			if(btn.contains("鏂板鎴愬姛")){
				driver.click(XPATH, "//div[@class='clearfix']//ul//li[2]", DEFVALUE);
				driver.input(XPATH, ".//*[@id='suppGoodsId']", DEFVALUE, str);
				driver.click(XPATH, "//div[@class='row']//a", DEFVALUE);
				//driver.selectFrame(XPATH, "//iframe[@class='iframe']");
				String str2=driver.getText(XPATH, "//div[@class='tab-pane bound-product clearfix active']//table//tbody//tr//td[5]", DEFVALUE);
				System.out.println("str2鐨勫��"+str2);
				if(str.equals("str2")){
					//瑙ｉ櫎缁戝畾
					driver.check(XPATH, "//div[@class='tab-pane bound-product clearfix active']//table[@class='table table-border']//tbody//tr[1]//td[1]");
					driver.click(XPATH, "//div[@class='tab-pane bound-product clearfix active']//div[2]//a", DEFVALUE);
					//杩斿洖鍒版湭缁戝畾鍟嗗搧椤甸潰
					driver.click(XPATH, "//div[@class='clearfix']//ul//li[1]", DEFVALUE);
					driver.input(XPATH, ".//*[@id='suppGoodsId']", DEFVALUE, str);
					driver.click(XPATH, "//div[@class='row']//a", DEFVALUE);
					String str1=driver.getText(XPATH, "//div[@class='tab-pane unbind-product clearfix active']//table//tbody//tr[1]//td[5]",DEFVALUE);
					System.out.println("str1鐨勫��"+str1);
					if(str==str1){
						return true;
					}
					return false;
				}
				else{
					return false;
				}
			}
			else{
				//鑾峰彇鍒楄〃閲岀殑琛屾暟
				int  num=driver.getTableTotalRowNum("//table[@class='table table-border']/tbody/tr");
				for(int i=1;i<=num;i++){
					driver.check(XPATH, "//div[@class='tab-pane active unbind-product clearfix']//table[@class='table table-border']//tbody//tr["+i+"]//td[1]//input");
					String str3=driver.getText(XPATH, "//div[@class='tab-pane active unbind-product clearfix']//table[@class='table table-border']//tbody//tr["+i+"]//td[5]",DEFVALUE);
					driver.click(XPATH, "//div[@class='footer-right']//a", DEFVALUE);
					if(btn.contains("鏂板鎴愬姛")){
						driver.click(XPATH, "//div[@class='clearfix']//ul//li[2]", DEFVALUE);
						driver.input(XPATH, ".//*[@id='suppGoodsId']", DEFVALUE, str);
						driver.click(XPATH, "//div[@class='row']//a", DEFVALUE);
						//driver.selectFrame(XPATH, "//iframe[@class='iframe']");
						String str2=driver.getText(XPATH, "//div[@class='tab-pane bound-product clearfix active']//table//tbody//tr//td[5]", DEFVALUE);
						System.out.println("str2鐨勫��"+str2);
						if(str.equals("str2")){
							//瑙ｉ櫎缁戝畾
							driver.check(XPATH, "//div[@class='tab-pane bound-product clearfix active']//table[@class='table table-border']//tbody//tr[1]//td[1]");
							driver.click(XPATH, "//div[@class='tab-pane bound-product clearfix active']//div[2]//a", DEFVALUE);
							//杩斿洖鍒版湭缁戝畾鍟嗗搧椤甸潰
							driver.click(XPATH, "//div[@class='clearfix']//ul//li[1]", DEFVALUE);
							driver.input(XPATH, ".//*[@id='suppGoodsId']", DEFVALUE, str);
							driver.click(XPATH, "//div[@class='row']//a", DEFVALUE);
							String str1=driver.getText(XPATH, "//div[@class='tab-pane unbind-product clearfix active']//table//tbody//tr[1]//td[5]",DEFVALUE);
							System.out.println("str1鐨勫��"+str1);
							if(str==str1){
								return true;
							}
							return false;
						}
						else{
							return false;
						}
					}
					System.out.println("娌℃湁鍙互缁戝畾鐨勬暟鎹�");
					
				}
				
			}
			
			return true;
		}
		
		/**
	   	 * 鑷姩鍖栧钩鍙�--娴嬭瘯绾夸笂鏁版嵁绠＄悊--楠岃瘉浜у搧璺宠浆鏄惁404
	   	 * zhangxingxing
	   	 * @return
	   	 */
		public boolean case404() {
			//鐧诲綍绂呴亾
			driver.waitForAllElementLoad(XPATH, "//div[@id='login-form']//table//tbody//tr[1]//td", DEFVALUE, TIME_OUT);
			driver.input(XPATH, "//div[@id='login-form']//table//tbody//tr[1]//td//input", DEFVALUE,"zhangxingxing" );
			driver.input(XPATH, "//div[@id='login-form']//table//tbody//tr[2]//td//input", DEFVALUE,"111111");
			driver.click(XPATH, "//button[@id='submit']", DEFVALUE);
			driver.waitForAllElementLoad(XPATH, "//a[@id='menumy']/span", DEFVALUE, TIME_OUT);
			//鐐瑰嚮鑷姩鍖栧钩鍙�
			driver.click(XPATH, "//header[@id='header']//nav[@id='mainmenu']//ul[@class='nav']//li[7]//a", DEFVALUE);
			driver.click(XPATH, "//header[@id='header']//nav[@id='modulemenu']//ul[@class='nav']//li[6]//a",DEFVALUE);
			driver.click(XPATH, ".//*[@id='featurebar']/div[1]/button[1]",DEFVALUE);
			//璁剧疆鍒楄〃閲岀殑鏁版嵁涓�100鏉�
			driver.click(XPATH, ".//*[@id='_recPerPage']", DEFVALUE);
			driver.click(XPATH, ".//*[@id='testdataList']/tfoot/tr/td/div[2]/div/div/ul/li[11]/a",DEFVALUE);
			//鏌ョ湅鍒楄〃閲岀殑鏁版嵁
//			int num=driver.getTableTotalRowNum("//div[@class='outer']/table[@id='testdataList']//tbody//tr");
			int num = driver.getTotalElementList2("//div[@class='outer']/table[@id='testdataList']//tbody//tr");
			System.out.println(num);
			for(int i=0;i<=num;i++){
				String str=driver.getText(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[3]", DEFVALUE);
				if(str.contains("鍒嗛攢")){
						frontlogin("Person_LY", "1234567!");
						driver.click(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE);
						String title=driver.getTitle();
						if(!title.contains("404") && !title.contains("灏忛┐鎵句笉鍒板濡�") && !driver.isElementPresent(XPATH, "//div[contains(@style,'http://pic.lvmama.com/img/v7_404bg.jpg')]",DEFVALUE)){
							continue;
							//return true;
						}
						else {
							String id=driver.getText(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE);
							System.out.println(id);
							//return false;
						}
				}else if(str.contains("闂ㄥ簵")){
					    frontlogin("qa_auto","lvmama888");
					    driver.click(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE); 
					    String title=driver.getTitle();
					    if(!title.contains("404") && !title.contains("灏忛┐鎵句笉鍒板濡�") && !driver.isElementPresent(XPATH, "//div[contains(@style,'http://pic.lvmama.com/img/v7_404bg.jpg')]",DEFVALUE)){
							continue;
					    	//return true;
						}
						else {
							String id=driver.getText(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE);
							System.out.println(id);
							//return false;
						}
				}else{
					driver.click(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE);  
					String title=driver.getTitle();
					if(!title.contains("404") && !title.contains("灏忛┐鎵句笉鍒板濡�") && !driver.isElementPresent(XPATH, "//div[contains(@style,'http://pic.lvmama.com/img/v7_404bg.jpg')]",DEFVALUE)){
						continue;
						//return true;
					}
					else {
						String id=driver.getText(XPATH, "//div[@class='outer']//table[@id='testdataList']//tbody//tr["+(i+1)+"]//td[6]/a", DEFVALUE);
						System.out.println(id);
						//return false;
					}
				}
				     
			}
	   		return true;
		}
		
		
		/**
		 * 鐗瑰崠浼氳鎯呴〉锛氭鏌ラ棬绁ㄤ骇鍝佽捣浠锋槸鍚︿负0,鎴栬�呮槸鍚︾┖
		 * xuxiaofan
		 */

		public boolean case69270() throws Exception {
			
			driver.waitForAllElementLoad(XPATH, "//div[@class='price-box']/div[1]/span[1]/em", DEFVALUE, TIME_OUT);
			String price=driver.getText(XPATH, "//div[@class='price-box']/div[1]/span[1]/em", DEFVALUE);
			
			driver.waitForAllElementLoad(XPATH, "//div[@class='product_info1']", DEFVALUE, TIME_OUT);
			String id =driver.getText(XPATH, "//div[@class='product_info1']", DEFVALUE);
			if( (price.equals("0"))  || ( price==null && price=="") ){
				System.out.println(id+":璧蜂环涓�0");
			}else{
				System.out.println("姝ｅ父:"+price);
			}
			return false;
		}
		
		/**
		 * 绾胯矾璇︽儏椤碉細璺熷洟娓稿嚭澧冭鎯呴〉
		 * zhaona
		 * case69146
		 */
		public boolean case69146() {
			driver.waitForAllElementLoad(XPATH,"//div[@class='float-nav-main']", DEFVALUE, TIME_OUT);
	   		//鍏虫帀寮瑰嚭鏉ョ殑椤甸潰
	   		if(driver.isElementPresent(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE)){
				driver.click(XPATH, "//div[@class='nova-dialog-close']", DEFVALUE);
			}
	   		//driver.click(XPATH, "//div[@class='float_nav']//li[1]", DEFVALUE);
	   		String str=driver.getText(XPATH, "//div[@class='float-nav-main']//li[1]", DEFVALUE);
	   		if("浜у搧璇︽儏".equals(str)){
	   			System.out.println("ok");
	   			return true;
	   		}
	   		
	   		return false;
		}
		
		/**
		 * 璁块棶鐗瑰崠浼氶閬撻〉url锛岃烦杞埌姝ｇ‘鐨勯〉闈�
		 * xuxiaofan
		 * case69646
		 */
		public boolean case69646(){
			driver.waitForAllElementLoad(XPATH,"//a[@class='current']", DEFVALUE, TIME_OUT);
			String str=driver.getText(XPATH, "//a[@class='current']", DEFVALUE);
			if("鐗瑰崠浼氬垪琛�".equals(str)){
				System.out.println("pass");
				return true;
			}
			return false;
		}
		
		/**
		 * 鐗瑰崠浼氬垪琛ㄩ〉锛� 鍑烘父绫诲瀷鍖呭惈锛氳嚜鐢辫锛岃窡鍥㈡父锛屽綋鍦版父锛岄棬绁紝閰掑簵锛岄偖杞�
		 * xuxiaofan
		 * case69923
		 */
		public boolean case69923(){
			driver.waitForAllElementLoad(XPATH,"//div[text()='鍑烘父绫诲瀷']/../dl//a", DEFVALUE, TIME_OUT);
			List<WebElement> str = driver.getElementsBy(XPATH, "//div[text()='鍑烘父绫诲瀷']/../dl//a");
			if("涓嶉檺".equals(str.get(0).getText())){
				System.out.println("pass");
			}else {
				System.out.println(str.get(0).getText()+":娌℃湁浜у搧");		
			}
			
			if("鑷敱琛�".equals(str.get(1).getText())){
				System.out.println("pass");
			}else{
				System.out.println(str.get(1).getText()+"娌℃湁浜у搧");
				
			}
			if("璺熷洟娓�".equals(str.get(2).getText())){
				System.out.println("pass");
			}else{
				System.out.println(str.get(2).getText()+"娌℃湁浜у搧");
			}
			if("褰撳湴娓�".equals(str.get(3).getText())){
				System.out.println("pass");

			}else{
				System.out.println(str.get(3).getText()+"娌℃湁浜у搧");
			}
			if("闂ㄧエ".equals(str.get(4).getText())){
				System.out.println("pass");
			}else{
				System.out.println(str.get(4).getText()+"娌℃湁浜у搧");
			}
			if("閰掑簵".equals(str.get(5).getText())){
				System.out.println("pass");
			}else{
				System.out.println(str.get(5).getText()+"娌℃湁浜у搧");
			}
			if("閭疆".equals(str.get(6).getText())){
				System.out.println("pass");
			}else{
				System.out.println(str.get(6).getText()+"娌℃湁浜у搧");
			}
			return false;
		}
		
		/**
		 * 鐗瑰崠浼氬垪琛ㄩ〉锛� 鐩殑鍦板寘鍚細鐑棬銆佸浗鍐呫�佸嚭澧冿紙鏄剧ず26涓嫳鏂囧瓧姣嶄笅娆″啓锛�
		 * xuxiaofan
		 * case70108
		 */
		
		public boolean case70108(){
			driver.waitForAllElementLoad(XPATH,"//div[text()='鐩�傜殑鈥傚湴']/../dl//a", DEFVALUE, TIME_OUT);
			List<WebElement> str = driver.getElementsBy(XPATH, "//div[text()='鐩�傜殑鈥傚湴']/../dl//a");
			if("鐑棬".equals(str.get(0).getText())){
				System.out.println("pass");
			}
			if("鍥藉唴".equals(str.get(1).getText())){
				System.out.println("pass");
			}
			if("鍑哄".equals(str.get(2).getText())){
				System.out.println("pass");
			}
			return false;
		}
		
		/**
		 * 鐗瑰崠浼氬垪琛ㄩ〉锛氬嚭娓告椂闂村寘鍚�1-12涓湀
		 * xuxiaofan
		 * case70139
		 */
        public boolean case70139(){
        	
        	int num =driver.getTotalElementList2("//div[text()='鍑烘父鏃堕棿']/../dl//a");
        	for(int i=1;i<num;i++){
        		driver.waitForAllElementLoad(XPATH,"//div[text()='鍑烘父鏃堕棿']/../dl//a", DEFVALUE, TIME_OUT);
        		String str=driver.getText(XPATH, "//div[text()='鍑烘父鏃堕棿']/../dl//dd/span["+i+"]", DEFVALUE);
        	if(str.contains("涓嶉檺")||str.contains("01鏈�")|| str.contains("02鏈�")
        	|| str.contains("03鏈�")||str.contains("04鏈�")|| str.contains("05鏈�")
        	|| str.contains("06鏈�")|| str.contains("07鏈�")|| str.contains("08鏈�")
        	|| str.contains("09鏈�")|| str.contains("10鏈�")|| str.contains("11鏈�")
        	|| str.contains("12鏈�")){
        		System.out.println(num);
        		System.out.println("姝ｅ父");
        		return true;
        	}else{
        		System.out.println("寮傚父");
        	}
        	}
        	return false;
        }
        
        /**
		 *鐗瑰崠浼氬垪琛ㄩ〉锛� 涓�椤典竴琛屾樉绀�4涓骇鍝侊紝涓�鍏卞睍绀�8琛岋紝涓�鍏�32涓骇鍝�
		 * xuxiaofan
		 * case69903
		 */
        public boolean case69903(){
        	driver.waitForAllElementLoad(XPATH, "//div[@class='pro']/ul/li", DEFVALUE, TIME_OUT);
        	int num =driver.getTotalElementList2("//div[@class='pro']/ul/li");
        	if(num>=32){
        		System.out.println("姣忛〉鏄剧ず32涓骇鍝�");
        		return true;
        	}else if(num<32){
        		System.out.println("鏈〉涓�鍏�"+num+"涓骇鍝�");
        		return true;
        	}
        	
        	return false;
        }
        
        /**
    	 * 特卖会列表页：出游天数
    	 * xuxiaofan
    	 * case70395
    	 */
        public boolean case70395(){
			driver.waitForAllElementLoad(XPATH,"//div[text()='出游天数']/../dl//a", DEFVALUE, TIME_OUT);
			List<WebElement> str = driver.getElementsBy(XPATH, "//div[text()='出游天数']/../dl//a");
	 		if("1天".equals(str.get(0).getText())){
				System.out.println("pass");
			}
			if("2天".equals(str.get(1).getText())){
				System.out.println("pass");
			}
			if("3天".equals(str.get(2).getText())){
				System.out.println("pass");
			}
			return false;
		}
        /**
    	 *频道出境游二级导航：出游特卖
    	 * xuxiaofan
    	 *  82227	
    	 */
        public boolean case82227(){
        driver.waitForAllElementLoad(XPATH, "/html/body/div[3]/div/ul/li[2]/a", DEFVALUE, TIME_OUT);	
        driver.click(XPATH, "/html/body/div[3]/div/ul/li[2]/a",DEFVALUE);
      //  driver.moveToElement(XPATH, "/html/body/div[3]/div/ul/li[2]/a");
        driver.click(XPATH, "/html/body/div[4]/div/ul/li[2]/div/p/a[6]",DEFVALUE);
        driver.switchToNewWindow();
        String str=  driver.getCurrentUrl();
        if(str.contains("1111")){
        	driver.refresh();
        	System.out.println("pass");
        }else{
        	System.out.println("404");
        }
        	return false;
        }
        /**
         * sunpeng
         * 特卖会详情页：检查线路产品的ID
         */
        public boolean case69202() throws Exception{
             driver.waitForAllElementLoad( XPATH, "//div[@class='product_info1']" , DEFVALUE, TIME_OUT);
            String id = driver.getText( XPATH, "//div[@class='product_info1']" , DEFVALUE);
             if(id!=null && id!=""){
                
                System. out.println("正常" );
                 return true ;
            } else{
                System. out.println("错误" );
            }
             return false ;
            
        }
        /**
         * sunpeng
         * 特卖会频道页检查
         */
        public boolean case69855() throws Exception{
//          左侧导航抓取
             driver.waitForAllElementLoad( XPATH, "//div[@class='nav-left']/div/ul/li/h3" , DEFVALUE, TIME_OUT );
            String text = driver.getText( XPATH, "//div[@class='nav-left']/div/ul/li/h3" , DEFVALUE);
             if(text.contains("限时抢购" )){
                System. out.println(text);
                 return true ;
            }
             return false ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -头部banner- 图片检查
         */
        public boolean case70068() throws Exception{
//          头部banner图片导航抓取
             for(int i = 1;i<=4;i++) {
                 driver.waitForAllElementLoad( XPATH, "//*[@id='js_allyes_1']/li[" +i+"]/a/img" , DEFVALUE, TIME_OUT );
                String text = driver.getAttributeValue(XPATH, "//*[@id='js_allyes_1']/li[" +i+"]/a/img" , "src");
//              String text = driver.getText(XPATH, "//*[@id='js_allyes_1']/li[1]/a/img", DEFVALUE);
                System. out.println(text);
                 if(text.equals("http://pic.lvmama.com/img/cmt/img_120_60.jpg" )||text.isEmpty()){
                     return false ;
                }
            }
            
             return true ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -头部banner- 链接跳转losc码是不是241819
         */
        public boolean case70197() throws Exception{
//          头部banner url抓取

             for(int i = 1;i<=10;i++) {
            
                 if(driver .isElementPresent(XPATH, "//*[@id='js_allyes_1']/li[" +i+"]/a/img" , DEFVALUE)) {
                     driver.waitForAllElementLoad( XPATH, "//*[@id='js_allyes_1']/li[" +i+"]/a/img" , DEFVALUE, TIME_OUT );
//                  driver.waitForAllElementLoad(XPATH, "//*[@id='js_allyes_1']/li[1]/a/img", DEFVALUE, TIME_OUT);
//                  String text = driver.getAttributeValue(XPATH, "//*[@id='js_allyes_1']/li["+i+"]/a/ img", "src");
                     driver.click( XPATH, "//*[@id='js_allyes_1']/li[" +i+"]/a/img" , DEFVALUE);
                     driver.switchToNewWindow();
                    String strURL = driver.getCurrentUrl();
//                  driver.closeChildWindow();
//                  System.out.println(text);
                     if(!strURL.contains("losc=241819" )){
                         return false ;
                    }
                     driver.switchBwoser(1);
                } else {
                     break;
                }

            }
            
                 return true ;
        }
        
        /**
         * sunpeng
         * 特卖会频道页 -热门活动- 链接跳转losc是不是241820
         */
        public boolean case70220() throws Exception{
//          热门活动url抓取
             for(int i = 2;i<=10;i++) {      
                 if(driver .isElementPresent(XPATH, "html/body/div[5]/div[2]/div[1]/div[" +i+"]/a" , DEFVALUE)) {
                     driver.waitForAllElementLoad( XPATH, "html/body/div[5]/div[2]/div[1]/div[" +i+"]/a" , DEFVALUE, TIME_OUT );
                     driver.click( XPATH, "html/body/div[5]/div[2]/div[1]/div[" +i+"]/a" , DEFVALUE);
                     driver.switchToNewWindow();
                    String strURL = driver.getCurrentUrl();
//                  driver.closeChildWindow();
                     if(!strURL.contains("losc=241820" )){
                         return false ;
                        }
                     driver.switchBwoser(1);
                } else {
                     break;
                }
            }       
                 return true ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -限时抢购- 文案确认
         */
        public boolean case70358() throws Exception{
//          限时抢购文案抓取    
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[2]/div[1]/h3" , DEFVALUE, TIME_OUT );
            String text = driver.getText( XPATH, "//div[5]/div[2]/div[2]/div[1]/h3" , DEFVALUE);
            if(!text.contains("限时抢购" )){
                 return false ;
                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[2]/div[1]/em" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[2]/div[2]/div[1]/em" , DEFVALUE);
            if(text1.contains("天天特价，限量狂欢" )){
            	return true ;
                }          
            return false ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -超级甩尾- 文案确认
         */
        public boolean case70360() throws Exception{
//          限时抢购文案抓取    
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[3]/div[1]/h3" , DEFVALUE, TIME_OUT );
            String text = driver.getText( XPATH, "//div[5]/div[2]/div[3]/div[1]/h3" , DEFVALUE);
            if(!text.contains("超级甩尾" )){
                 return false ;
                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[3]/div[1]/em" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[2]/div[3]/div[1]/em" , DEFVALUE);
            if(text1.contains("尾货特价，捡到就走" )){
            	return true ;
                }          
            return false ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -多订多惠- 文案确认
         */
        public boolean case70361() throws Exception{
//          限时抢购文案抓取    
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[4]/div[1]/h3" , DEFVALUE, TIME_OUT );
            String text = driver.getText( XPATH, "//div[5]/div[2]/div[4]/div[1]/h3" , DEFVALUE);
            if(!text.contains("多订多惠" )){
                 return false ;
                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[4]/div[1]/em" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[2]/div[4]/div[1]/em" , DEFVALUE);
            if(text1.contains("结伴出行，可享减免" )){
            	return true ;
                }          
            return false ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -早订早惠- 文案确认
         */
        public boolean case70362() throws Exception{
//          早订早惠文案抓取    
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE, TIME_OUT );
            String text = driver.getText( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE);
            if(!text.contains("早订早惠" )){
                 return false ;
                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[5]/div[1]/em" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[2]/div[5]/div[1]/em" , DEFVALUE);
            if(text1.contains("提前下单，立享优惠" )){
            	return true ;
                }          
            return false ;
        }

        
        /**
         * sunpeng
         * 特卖会频道页 -小编推荐- 文案确认
         */
        public boolean case70363() throws Exception{
//          早订早惠文案抓取    
//            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE, TIME_OUT );
//            String text = driver.getText( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE);
//            if(!text.contains("小编推荐" )){
//                 return false ;
//                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[3]/div/h3" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[3]/div/h3" , DEFVALUE);
            if(text1.contains("小编推荐" )){
            	return true ;
                }          
            return false ;
        }
        /**
         * sunpeng
         * 特卖会频道页 -特价机票- 文案确认
         */
        public boolean case70364() throws Exception{
//          早订早惠文案抓取    
//            driver.waitForAllElementLoad( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE, TIME_OUT );
//            String text = driver.getText( XPATH, "//div[5]/div[2]/div[5]/div[1]/h3" , DEFVALUE);
//            if(!text.contains("小编推荐" )){
//                 return false ;
//                 }      
            driver.waitForAllElementLoad( XPATH, "//div[5]/div[5]/div[1]/div[1]/h3" , DEFVALUE, TIME_OUT );
            String text1 = driver.getText( XPATH, "//div[5]/div[5]/div[1]/div[1]/h3" , DEFVALUE);
            if(text1.contains("特价机票" )){
            	return true ;
                }          
            return false ;
        }

        
   

        /**
         * 列表页筛选无结果文案提示 根据筛选条件，若无产品 则提示相关文案
         * xuxiaofan
         *  88794	
         */
        public boolean case88794(){
        	//点击品类（门票） 、出游天数（2天）、活动类型（超级甩尾） 来筛选
        	if(driver.isElementPresent(XPATH, "//div[@class='tip-content']", DEFVALUE))
        	{
        		System.out.println("pass");
					return true;
				}else {
					System.out.println("没有符合的产品");
					
					return false;
				}
        	
        }
        /**
         * 特卖列表页增加距离结束的筛选
         * xuxiaofan
         *  92816	
         */
        public boolean case92816(){
        	driver.waitForAllElementLoad( XPATH, "//div[4]/div/div[2]/div/div[8]/div[1]/div[5]/span" , DEFVALUE, TIME_OUT );
        	String text = driver.getText( XPATH, "//div[4]/div/div[2]/div/div[8]/div[1]/div[5]/span" , DEFVALUE);
        	if(text.contains("距离结束")){
        		System.out.println("pass");
        		return true;
        	}
        	return false;
        }
        /**
         * 目的地增加：港澳台 筛选栏位
         * xuxiaofan
         *  88727	
         */
        public boolean case88727(){
        	driver.waitForAllElementLoad( XPATH, "//div[4]/div/div[2]/div/div[8]/div[1]/div[5]/span" , DEFVALUE, TIME_OUT );
        	String text = driver.getText( XPATH, "//div[text()='目 的 地']/../dl//a" , DEFVALUE);
        	if(text.equals("港澳台")){
        	System.out.println("pass");
        		return true;
        	}
        	return false;
        }

}


