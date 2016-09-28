package hq.lib.nio;

import hq.lib.io.CmdUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio {
	
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			//new TestNio().testNio();
			//new TestNio().testIo();
			CmdUtil.copy("E:\\install packages\\CentOS-6.5-i386-bin-DVD1.iso", "E:\\CentOS-6.5-i386-bin-DVD1.back.iso");
			System.out.println(System.currentTimeMillis() - start);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//1.139768,125111   104541 94525
	public void testNio() throws IOException{
		String source = "E:\\install packages\\CentOS-6.5-i386-bin-DVD1.iso";
		String back = "E:\\CentOS-6.5-i386-bin-DVD1.back.iso";
		FileInputStream in = new FileInputStream(source);
		FileOutputStream out = new FileOutputStream(back);
		FileChannel outChannel = out.getChannel();
		FileChannel inChanel = in.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//Charset charSet = Charset.forName("utf-8");
		//CharsetDecoder decoder = charSet.newDecoder();
		//CharsetEncoder encoder = charSet.newEncoder();
		while(true){
			/*CharBuffer cb = decoder.decode(buffer);
			ByteBuffer bb = encoder.encode(cb);*/
			int t = inChanel.read(buffer);
			if(t == -1){
				break;
			}
			buffer.flip();
			outChannel.write(buffer);
			
			buffer.clear();
		}
		
		outChannel.close();
		inChanel.close();
		out.close();
		in.close();
	}
	
	//84241, 114541 144523 98610
	public void testIo() throws IOException{
		String source = "E:\\install packages\\CentOS-6.5-i386-bin-DVD1.iso";
		String back = "E:\\CentOS-6.5-i386-bin-DVD1.back.iso";
		FileInputStream in = new FileInputStream(source);
		FileOutputStream out = new FileOutputStream(back);
		byte[] cache = new byte[1024];
		int n = -1;
		while(-1 != (n = in.read(cache))){
			out.write(cache, 0, n);
		}
		out.close();
		in.close();
	}

}
