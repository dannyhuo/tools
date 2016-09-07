package hq.lib.io;

import java.io.File;
import java.io.IOException;

/**
 * Execute the windows command!
 * 
 * @author Danny.Huo
 * @version 1.0
 *
 */
public class CmdUtil {

	/**
	 * Copy a file
	 * 
	 * @param source
	 *            The source file path
	 * @param destination
	 *            The destination file path
	 * @throws IOException
	 *             An IOException
	 */
	public static void copy(String source, String destination)
			throws IOException {
		StringBuilder cmd = new StringBuilder("cmd /c copy ");
		cmd.append(source);
		cmd.append(" ");
		cmd.append(destination);
		Process tmpProcess = Runtime.getRuntime().exec(cmd.toString());
		try {
			tmpProcess.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Copy a file or directory
	 * 
	 * @param source
	 *            The source path
	 * @param destination
	 *            The destination path
	 * @param isDir
	 *            Is a directory or not<br>
	 *            Is true can copy a directory<br>
	 *            Is false can copy a file
	 * @throws IOException
	 *             An IOException
	 */
	public static void copy(String source, String destination, boolean isDir)
			throws IOException {
		if (isDir) {
			StringBuilder cmd = new StringBuilder("cmd /c xcopy \"");
			cmd.append(source);
			cmd.append("\" \"");
			cmd.append(destination);
			cmd.append("\" /e");
			Runtime.getRuntime().exec(cmd.toString());
		} else {
			copy(source, destination);
		}
	}

	/**
	 * Move the file to the destination
	 * 
	 * @param source
	 * @param destination
	 * @return
	 * @throws Exception
	 */
	public static boolean move(String source, String destination)
			throws Exception {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuilder cmd = new StringBuilder("mv \"");
		cmd.append(source);
		cmd.append("\" \"");
		cmd.append(destination);
		cmd.append("\"");
		if (osName.indexOf("linux") != -1) {
			String[] linuxCmd = new String[] { "/bin/sh", "-c", cmd.toString() };
			Process tmpProcess = Runtime.getRuntime().exec(linuxCmd);
			tmpProcess.waitFor();
		} else if (osName.indexOf("win") != -1) {
			cmd.replace(0, 2, "cmd /c move");
			String command = cmd.toString();
			Process tmpProcess = Runtime.getRuntime().exec(command);
			tmpProcess.waitFor();
		}
		return true;
	}

	/**
	 * delete the file or the directory
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean remove(String path) throws IOException,
			InterruptedException {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuilder cmd = new StringBuilder("rm ");
		boolean isDir = new File(path).isDirectory();
		if (osName.indexOf("linux") != -1) {
			cmd.append(isDir ? "-rf " : "-f ");
			cmd.append("\"");
			cmd.append(path);
			cmd.append("\"");
			String[] linuxCmd = new String[] { "/bin/sh", "-c", cmd.toString() };
			Process tmpProcess = Runtime.getRuntime().exec(linuxCmd);
			tmpProcess.waitFor();
		} else if (osName.indexOf("win") != -1) {
			cmd.replace(0, 2, "cmd /c del /q/a/f/s ");// 删除目录下文件
			cmd.append("\"");
			cmd.append(path);
			cmd.append("\"");
			String command = cmd.toString();
			Process tmpProcess = Runtime.getRuntime().exec(command);
			tmpProcess.waitFor();

			if (isDir) {
				cmd = new StringBuilder("cmd /c rd \"");// 删除目录
				cmd.append(path);
				cmd.append("\"");
				command = cmd.toString();
				tmpProcess = Runtime.getRuntime().exec(command);
				tmpProcess.waitFor();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		byte b = -128;
		System.out.println(Integer.toBinaryString(b));
		byte a = -127;
		System.out.println(Byte.toString(a));
	}
}
