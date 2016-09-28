package hq.lib.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbSession;

/**
 * 远程文件工具类
 * @author danny.huo
 * @data 2015-02-11
 */
public class RmtFileUtil {
	
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	
	/**
	 * 获取远程samb权限<br>
	 * 带域
	 * @param userName
	 * @param password
	 * @param domain
	 * @param rmpIp
	 * @return {NtlmPasswordAuthentication}
	 * @throws UnknownHostException 
	 * @throws SmbException 
	 */
	public static NtlmPasswordAuthentication getAuthority(String userName, String password, String domain, String rmpIp) throws UnknownHostException, SmbException{
		NtlmPasswordAuthentication authority = null;
		String domainName = domain == null ? rmpIp : domain;
		InetAddress rmtAddress = InetAddress.getByName(rmpIp);
		UniAddress rmtUniAddress = new UniAddress(rmtAddress);
		authority = new NtlmPasswordAuthentication(domainName, userName, password);
		SmbSession.logon(rmtUniAddress, authority);
		return authority;
	}
	
	/**
	 * 获取远程Samb权限<br>
	 * 重载，不带域
	 * @param userName 远程samb用户名
	 * @param password 远程samb密码
	 * @param rmpIp 远程机器ip
	 * @return {NtlmPasswordAuthentication}
	 * @throws UnknownHostException
	 * @throws SmbException
	 */
	public static NtlmPasswordAuthentication getAuthority(String userName, String password, String rmpIp) throws UnknownHostException, SmbException{
		return getAuthority(userName, password, null, rmpIp);
	}
	
	/**
	 * 将远程smb文件或目录拷贝到本地
	 * 如果远程为目录，则本地也只能为目录
	 * @param rmtPath 远程文件或目录
	 * @param localPath 本地要拷贝到的文件或目录
	 * @param auth smb权限
	 * @param bufferSize
	 */
	public static void copyRmt2Local(String rmtPath, String localPath, NtlmPasswordAuthentication auth, int bufferSize){
		SmbFileInputStream smbIn = null;//远程读取流
		FileOutputStream fos = null;
		try {
			//远程读取流
			SmbFile file = new SmbFile(rmtPath, auth);
			smbIn = new SmbFileInputStream(file);
			
			//本地输出流
			File lclFile = new File(localPath);
			fos = new FileOutputStream(lclFile);
			
			if(bufferSize < 1){//输入不合法，默认为512个字节
				bufferSize = DEFAULT_BUFFER_SIZE;
			}
			
			//读取远程，写入本地
			byte[] buffer = new byte[bufferSize];
			int n = -1;
			while(-1 != (n = smbIn.read(buffer))){
				smbIn.read(buffer);
				fos.write(buffer, 0, n);
				fos.flush();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				smbIn.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void copyRmt2Local(String rmtPath, String localPath, NtlmPasswordAuthentication auth){
		copyRmt2Local(rmtPath, localPath, auth, DEFAULT_BUFFER_SIZE);
	}
	
	public static void copyRmt2Local(String rmtIp, String rmtDomain, String userName, String password, String rmtPath, String localPath){
		try {
			NtlmPasswordAuthentication authority = getAuthority(userName, password, rmtDomain, rmtIp);
			copyRmt2Local(rmtPath, localPath, authority, DEFAULT_BUFFER_SIZE);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		} catch (SmbException e) {
			
		}
	}
	
	public static void copyRmt2Local(String rmtIp, String rmtDomain, String userName, String password, String rmtPath, String localPath, int bufferSize){
		try {
			NtlmPasswordAuthentication authority = getAuthority(userName, password, rmtDomain, rmtIp);
			copyRmt2Local(rmtPath, localPath, authority, bufferSize);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		} catch (SmbException e) {
			
		}
	}
	
	
	
	public static void main(String[] args) {
		/*try {
			//getAuthority("felix.wang", "adchina.com2", "mob", "192.168.4.180");
			getAuthority("tomcat", "asp@com", "10.15.186.204");
		} catch (UnknownHostException | SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			getAuthority("tomcat", "asp@com", "10.15.186.204");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(RmtFileUtilEnum.SUCESS);
		copyRmt2Local("10.15.186.204", "10.15.186.204", "tomcat", "asp@com", "smb://10.15.186.204/tag_audience/tagbackup/20150209/720400528.failed", "C:/Users/danny.huo/Desktop/java.read.remote/720400528.failed");
		
		//copyRmt2Local("192.168.4.181", "mob", "felix.wang", "adchina.com2", "smb://192.168.4.181/testReadRmt/mergedoneday.20150201.mob181.dat", "C:/Users/danny.huo/Desktop/java.read.remote/720400528.failed");
	}

}
