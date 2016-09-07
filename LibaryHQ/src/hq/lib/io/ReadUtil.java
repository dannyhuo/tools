package hq.lib.io;

import hq.lib.interfaces.DoLiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * For read file
 * @author Danny.Huo
 * @version 1.0
 * 
 */
public class ReadUtil {
	
	/**
	 * Read a file if file is exists.
	 * @param path The file path
	 * @param doer Do a line
	 * @param startRow Read from the row start
	 * @param endRow  Read to the row end
	 * @param charset Charset
	 */
	public static void read(String path, DoLiner doer, String charset, int startRow, int endRow){
		File file = new File(path);
		if(file.exists()){
			int count = 0;
			InputStreamReader in = null;
			BufferedReader reader = null;
			try {
				in = new InputStreamReader(new FileInputStream(file), charset);
				reader = new BufferedReader(in);
				if(endRow > 0){
					while(reader.ready() && count < endRow){
						String line = reader.readLine();
						count++;
						if(count >= startRow){
							doer.accept(line);
						}
					}
				}else{
					while(reader.ready()){
						String line = reader.readLine();
						count++;
						if(count >= startRow){
							doer.accept(line);
						}
					}
				}
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					reader.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Read a file if file is exists. <br>
	 * Read all rows.
	 * @param path The file path
	 * @param doer Do a line
	 * @param charset The encoding
	 */
	public static void read(String path, DoLiner doer, String charset){
		read(path, doer, charset, 1, -1);
	}
	
	/**
	 * Read a file as default utf-8 encoding
	 * @param path The file path
	 * @param doer Do a line
	 */
	public static void read(String path, DoLiner doer){
		read(path, doer, "utf-8", 1, -1);
	}
	
	/**
	 * Read a file as default utf-8 encoding
	 * @param path The file path
	 * @param doer Do a line
	 * @param startRow Read from the row start
	 * @param endRow  Read to the row end
	 */
	public static void read(String path, DoLiner doer, int startRow, int endRow){
		read(path, doer, "utf-8", startRow, endRow);
	}
	
	/**
	 * Read some file
	 * @param paths The file paths
	 * @param doer Do a line
	 * @param charset The encoding style
	 */
	public static void read(String[] paths, DoLiner doer, String charset){
		if(null != paths){
			int len = paths.length;
			for(int i = 0; i < len; i++){
				read(paths[i], doer, charset);
			}
		}
	}
	
	/**
	 * Read some file as default utf-8 encoding
	 * @param paths The file paths
	 * @param doer Do a line
	 */
	public static void read(String[] paths, DoLiner doer){
		read(paths, doer, "utf-8");
	}
}
