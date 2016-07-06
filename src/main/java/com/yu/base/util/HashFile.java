package com.yu.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class HashFile {  
	
	public static final String MD5 = "MD5";
	
	public static final String SHA1 = "SHA1";
	
	public static final String SHA_256 = "SHA-256";
	
	public static final String SHA_384 = "SHA-384";
	
	public static final String SHA_512 = "SHA-512";
	
    public static char[] hexChar = {'0', '1', '2', '3',  
                                   '4', '5', '6', '7',  
                                   '8', '9', 'a', 'b',  
                                   'c', 'd', 'e', 'f'};  
    public static void main(String[] args) throws  
            Exception {  
        String fileName = "F:\\会计核算系统\\20150101-20150107\\20150101-20150107.zip";  
        File file = new File(fileName);
        System.out.println(file.getName());
        String hashType = "MD5";  
        System.out.println(hashType + " == " +  
        		getHashByFilePath(fileName, hashType));  
        hashType = "SHA1";  
        System.out.println(hashType + " == " +  
        		getHashByFilePath(fileName, hashType));  
        hashType = "SHA-256";  
        System.out.println(hashType + " == " +  
        		getHashByFilePath(fileName, hashType));  
        hashType = "SHA-384";  
        System.out.println(hashType + " == " +  
        		getHashByFilePath(fileName, hashType));  
        hashType = "SHA-512";  
        System.out.println(hashType + " == " +  
        		getHashByFilePath(fileName, hashType));  
  
    }  
  
    public static String getHashByFilePath(String filePath, String hashType) throws  
            Exception {  
        InputStream fis = new FileInputStream(filePath);  
        byte[] buffer = new byte[1024];  
        MessageDigest md5 = MessageDigest.getInstance(hashType);  
        int numRead = 0;  
        while ((numRead = fis.read(buffer)) > 0) {  
            md5.update(buffer, 0, numRead);  
        }  
        fis.close();  
        return toHexString(md5.digest());  
    }  
    
    public static String getHashByString(String String, String hashType) throws Exception{
    	MessageDigest md5 = MessageDigest.getInstance(hashType); 
    	md5.update(String.getBytes("iso-8859-1"), 0, String.length());
    	return toHexString(md5.digest());
    }
  
    public static String toHexString(byte[] b) {  
        StringBuilder sb = new StringBuilder(b.length * 2);  
        for (int i = 0; i < b.length; i++) {  
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);  
            sb.append(hexChar[b[i] & 0x0f]);  
        }  
        return sb.toString();  
    }  
}  
