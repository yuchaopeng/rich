package com.yu.base.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtil {
	
	public static String readFile(File file){
        InputStream is = null;
        InputStreamReader reader = null;
        StringBuffer result = new StringBuffer();
        try {
            is = new FileInputStream(file);
            reader = new InputStreamReader(is, "utf-8");
            
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                	result.append((char)tempchar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(reader != null){
        		try {
        			reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result.toString();
	}
	
	public static void main(String[] args) {
		File file = new File("F:\\会计核算系统\\20150101-20150107\\39714d1cf45be61fc7bc05ba8441317b\\A_BJ_001\\at_main.txt");
		String s = readFile(file);
		System.out.println(s);
	}
	
	public static String readFile1(File file){
        InputStream is = null;
        ByteArrayOutputStream bytes = null;
        try {
            byte[] buffer = new byte[1024];
            int read = 0;
            is = new FileInputStream(file);
            bytes = new ByteArrayOutputStream();
            read=is.read(buffer);
            while (read!=-1) {
            	bytes.write(buffer,0,read);
            	read=is.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return byteArrayOutputStreamToString(bytes);
	}
	
	private static String byteArrayOutputStreamToString(ByteArrayOutputStream bytes){
		if(bytes != null){
			byte[] b = bytes.toByteArray();
			try {
				bytes.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new String(b);
		}
		return "";
	}
	
	public static void writeFile(String str,String savePath){
		File file = new File(savePath);
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(str.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeFile(InputStream is,String savePath) throws IOException{
		File file = new File(savePath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream(file);
		byte[] b = new byte[1024];
		while((is.read(b)) != -1){
			os.write(b);
		}
		if(os != null){
			os.close();
		}
		if(is != null){
			is.close();
		}
	}
}
