package com.file.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	/**
	 * 判空串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullStr(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * close file input stream and output stream
	 * @param fis
	 * @param fos
	 */
	public static void closeStream(FileInputStream fis, FileOutputStream fos){
		closeInputStream(fis);
		closeOutputStream(fos);
	}
	
	/**
	 * close file input stream
	 * @param fis
	 */
	public static void closeInputStream(FileInputStream fis){
		if(null != fis){
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * close file output stream
	 * @param fos
	 */
	public static void closeOutputStream(FileOutputStream fos){
		if(null != fos){
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 拷贝文件 单个文件拷贝到指定的目录
	 * 
	 * @param sourcePath
	 *          ：源文件路径
	 * @param desDir
	 *          ：(destination path)目标路径,是一个目录或完整的文件路径
	 */
	public static void copyFile(String sourcePath, String desPath) {
		if (!isNullStr(sourcePath) && !isNullStr(desPath)) {
			File sourceFile = new File(sourcePath);
			File desFile = new File(desPath);
			if(desFile.isDirectory()){
				String fileName = sourcePath.substring(sourcePath.lastIndexOf("/"));
				desFile = new File(desPath + "/" + fileName);
			}
			copyFile(sourceFile,desFile);
		}
	}

	/**
	 * 将源文件拷贝到目标文件
	 * @param sourceFile 源文件
	 * @param desFile 目标文件
	 */
	public static void copyFile(File sourceFile, File desFile) {
		if(null != sourceFile && null != desFile && sourceFile.canRead()){
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(sourceFile);
				fos = new FileOutputStream(desFile);
				byte[] cache = new byte[1024];
				int n = 0;
				while((n = fis.read(cache)) != -1){
					fos.write(cache, 0, n);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeStream(fis,fos);
			}
		}
	}

	/**
	 * 拷贝目录及其子目录和文件 将sourceFolder目录及其目录中的文件 和子目录拷贝到指定的目录deFolder
	 * 
	 * @param sourceFolder
	 *          ：源目录
	 * @param deFolder
	 *          ：目录目录
	 */
	public static void cpDir(String sourceDir, String desDir){
		if (!isNullStr(sourceDir) && !isNullStr(desDir)) {
			File sourceFiles = new File(sourceDir);
			File desFile = new File(desDir);
			cpDir(sourceFiles,desFile);
			/*if (sourceFiles.isDirectory()) {
				// 目录目录是否为一个目录文件
				if (new File(desDir).isDirectory()) {
					// 是一个目录文件，递归拷贝目录文件及其子目录
					File[] files = sourceFiles.listFiles();
					for(int i = 0; i < files.length; i++){
						if(files[i].isFile()){
							copyFile(files[i],new File(desDir+files[i].getName()));
						}
					}
				} else {
					// 目标目录不是一个目录文件，抛出异常

				}
			} else {
				// 不是目录文件，抛出异常

			}*/
		}
	}
	
	public static void cpDir(File sourceDir, File desDir){
		if(null != sourceDir && sourceDir.isDirectory()
				&& null != desDir && desDir.isDirectory()){
			String[] names = sourceDir.list();
			File curTmpFile = null;
			if(null != names){
				for(int i = 0; i < names.length; i++){
					curTmpFile = new File(sourceDir + "/" + names[i]);
					if(curTmpFile.isDirectory()){
						mkDir(desDir + "/" + names[i]);
						cpDir(sourceDir + "/" + names[i], desDir + "/" + names[i]);
					}else{
						copyFile(curTmpFile,new File(desDir + "/" + names[i]));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		/*String folder1 = "/usr";
		String desDir = "/home/danny/desDir";
		cpDir(folder1, desDir);*/
		System.out.println(readTextFile("/home/danny/Downloads/com/file/tool/FileUtil.java"));
	}

	/**
	 * 文件同步
	 * 
	 * @param sourceFolder
	 *          ：源目录
	 * @param destinationFolder
	 *          ：目标目录
	 */
	public static void syncFiles(String sourceFolder, String destinationFolder) {

	}

	/**
	 * 读取二进制文件
	 * 
	 * @param filePath
	 *          ：二进制文件路径
	 * @return：返回文件的字节数组
	 */
	public static byte[] readBinaryFile(String filePath) {
		return null;
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *          ：文本文件的路径
	 * @return：返回读取的内容
	 */
	public static String readTextFile(String filePath) {
		FileReader in;
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		try {
			in = new FileReader(filePath);
			br = new BufferedReader(in);
			String tmp = "";
			while(null != (tmp = br.readLine())){
				sb.append(tmp + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 写文件
	 * 
	 * @param fileBytes
	 *          ：要写的文件的字节数组
	 */
	public static void writeFile(byte[] fileBytes) {

	}

	public static byte[] ecrptHeader(String suffix) {
		byte[] sufByte = suffix.getBytes();// 后缀
		byte[] ecrptDate = (System.currentTimeMillis() + "").getBytes();// 日期
		Byte lenByte = new Byte(sufByte.length + "");
		byte[] header = new byte[1024];
		header[0] = lenByte.byteValue();
		int i;
		for (i = 0; i < sufByte.length; i++) {
			header[i + 1] = sufByte[i];
		}
		header[i + 1] = new Byte(ecrptDate.length + "").byteValue();
		i += 2;
		for (int j = 0; j < ecrptDate.length; j++, i++) {
			header[i] = ecrptDate[j];
		}
		return encrept(header, 0, header.length);
	}

	public static String[] dcrptHeader(byte[] bytes) {
		bytes = decrept(bytes, 0, bytes.length);
		int sufLen = bytes[0];
		byte[] suffix = new byte[sufLen];
		int cur;
		for (cur = 0; cur < sufLen; cur++) {
			suffix[cur] = bytes[cur + 1];
		}
		String[] res = new String[3];
		res[0] = new String(suffix);
		cur++;
		int ecrptDateLen = bytes[cur];
		byte[] ecrptDate = new byte[ecrptDateLen];
		cur++;
		for (int j = 0; j < ecrptDateLen; j++, cur++) {
			ecrptDate[j] = bytes[cur];
		}
		res[1] = new String(ecrptDate);
		res[2] = cur + "";
		return res;
	}

	public static void dcrpt(String path, String path2) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String suffix = path.substring(path.lastIndexOf(".") + 1);
		try {
			fis = new FileInputStream(path);
			byte[] bytes = new byte[1024];
			int n = 0;
			n = fis.read(bytes);
			String[] header = dcrptHeader(bytes);
			path2 = path2.replace(suffix, header[0]);
			fos = new FileOutputStream(path2);
			// fos.write(decrept(bytes, Integer.parseInt(header[2]), n));
			while ((n = fis.read(bytes)) != -1) {
				fos.write(decrept(bytes, 0, n));
				fos.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ecrpt(String path, String path2) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String suffix = path.substring(path.lastIndexOf(".") + 1);
		path2 = path2.replace(suffix, "erc");
		try {
			fis = new FileInputStream(path);
			fos = new FileOutputStream(path2);
			byte[] bytes = new byte[1024];
			fos.write(ecrptHeader(suffix));
			int n = 0;
			while ((n = fis.read(bytes)) != -1) {
				fos.write(encrept(bytes, 0, n));
				fos.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] encrept(byte[] bytes, int off, int len) {
		byte[] retBytes = new byte[len - off];
		for (int i = 0, j = off; i < retBytes.length; i++, j++) {
			retBytes[i] = (++bytes[j]);
		}
		return retBytes;
	}

	public static byte[] decrept(byte[] bytes, int off, int len) {
		byte[] retBytes = new byte[len - off];
		for (int i = 0, j = off; i < retBytes.length; i++, j++) {
			retBytes[i] = (--bytes[j]);
		}
		return retBytes;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param name 目录名
	 * @return 返回创建成功与否
	 */
	public static boolean mkDir(String name) {
		boolean res = false;
		File dirFile = new File(name);
		if (!dirFile.exists()) {
			res = dirFile.mkdir();
		}
		return res;
	}
}
