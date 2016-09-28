package hq.lib.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * For write file
 * @author Danny.Huo
 * @version 1.0
 *
 */
public class WriteUtil {
	
	/**
	 * Prepare before write a file<br>
	 * If file exists delete<br>
	 * If the sub directory is not exists, create it.
	 * @param path The file path
	 * @return {boolean}
	 * <p>Prepare okay or not
	 */
	public static boolean prepare(String path){
		boolean okay = false;
		Path fileName = Paths.get(path);
        Path parentPath = fileName.getParent();
        try {
            if (Files.notExists(parentPath)) {
                Files.createDirectories(parentPath);
            }
            Files.deleteIfExists(fileName);
            okay = true;
        } catch (IOException e) {
            System.out.print(e);
            e.printStackTrace();
        }
        return okay;
	}
	
	/**
	 * Write a line to a file.
	 * @param path The file path.
	 * @param line The write content.
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	private static boolean write(String path, String line, boolean append){
		boolean okay = false;
		File file = new File(path);
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			out = new FileWriter(file, append);
			writer = new BufferedWriter(out);
			writer.write(line);
			writer.newLine();
			okay = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return okay;
	}
	
	/**
	 * Write a line to a file.
	 * @param path The file path.
	 * @param line The write content.
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	public static boolean write(String path, String line){
		return write(path, line, false);
	}
	
	/**
	 * Append a line to a file
	 * @param path The file path
	 * @param line The will append content
	 * @return {boolean}<br>
	 * Append okay or not
	 */
	public static boolean append(String path, String line){
		return write(path, line, true);
	}
	
	/**
	 * Write some line that stored at an array to a file.
	 * @param path The file path.
	 * @param lines The lines
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	private static boolean write(String path, String[] lines, boolean append){
		boolean okay = false;
		File file = new File(path);
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			out = new FileWriter(file, append);
			writer = new BufferedWriter(out);
			for(int i = 0; i < lines.length; i++){
				writer.write(lines[i]);
				writer.newLine();
			}
			writer.flush();
			okay = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return okay;
	}
	
	/**
	 * Write some line that stored at an array to a file.
	 * @param path The file path.
	 * @param lines The lines
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	public static boolean write(String path, String[] lines){
		return write(path, lines, false);
	}
	
	/**
	 * Append some line that stored at an array to a file.
	 * @param path The file path.
	 * @param lines The lines
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	public static boolean append(String path, String[] lines){
		return write(path, lines, true);
	}
	
	
	/**
	 * Write some line that stored at List to a file.
	 * @param path The file path.
	 * @param lines The lines
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	private static boolean write(String path, List<String> content, boolean append){
		boolean okay = false;
		File file = new File(path);
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			out = new FileWriter(file, append);
			writer = new BufferedWriter(out);
			int size = content.size();
			for(int i = 0; i < size; i++){
				writer.write(content.get(i));
				writer.newLine();
			}
			writer.flush();
			okay = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return okay;
	}
	
	/**
	 * Write some line that stored at List to a file.
	 * @param path The file path.
	 * @param content The content
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	public static boolean write(String path, List<String> content){
		return write(path, content, false);
	}
	
	/**
	 * Append some line that stored at List to a file.
	 * @param path The file path.
	 * @param content The content
	 * @return {boolean}<br>
	 * Return is write okay or not.
	 */
	public static boolean append(String path, List<String> content){
		return write(path, content, true);
	}
	
	
	/**
	 * Write some line that stored at HashSet to a file
	 * @param path The file path
	 * @param content The to be writing content
	 * @return {boolean}<br>
	 * Write okay or not
	 */
	private static boolean write(String path, Set<String> content, boolean append){
		boolean okay = false;
		File file = new File(path);
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			out = new FileWriter(file);
			writer = new BufferedWriter(out);
			if(null != content){
				Iterator<String> iter = content.iterator();
				while(iter.hasNext()){
					String line = iter.next();
					writer.write(line);
					writer.newLine();
				}
				writer.flush();
				okay = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return okay;
	}
	
	/**
	 * Write some line that stored at HashSet to a file
	 * @param path The file path
	 * @param content The to be writing content
	 * @return {boolean}
	 * <p>Write okay or not
	 */
	public static boolean write(String path, Set<String> content){
		return write(path, content, false);
	}
	
	/**
	 * Append some line that stored at HashSet to a file
	 * @param path The file path
	 * @param content The to be writing content
	 * @return {boolean}
	 * <p>Append okay or not
	 */
	public static boolean append(String path, Set<String> content){
		return write(path, content, true);
	}
	
	/**
	 * Write or append some bytes to a file
	 * @param path The file path
	 * @param bytes The bytes array
	 * @param append Is append or not
	 * @return{boolean}<br>
	 * Write or append okay or not
	 */
	private static boolean write(String path, byte[] bytes, boolean append){
		boolean okay = false;
		File file = new File(path);
		FileOutputStream fos = null;
		FileChannel chanel = null;
		try {
			fos = new FileOutputStream(file, append);
			chanel = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			chanel.write(buffer);
			okay = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				chanel.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return okay;
	}
	
	/**
	 * Write some byte to a file
	 * @param path The file path
	 * @param bytes Byte array
	 * @return {boolean}<br>
	 * Write okay or not
	 */
	public static boolean write(String path, byte[] bytes){
		return write(path, bytes, false);
	}
	
	/**
	 * Append some byte to a file
	 * @param path The file path
	 * @param bytes Byte array
	 * @return {boolean}<br>
	 * Append okay or not
	 */
	public static boolean append(String path, byte[] bytes){
		return write(path, bytes, true);
	}
	
}
