package com.yu.web.base.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yu.web.base.model.ImageUpload;
import com.yu.web.base.service.ImageUploadService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	private Logger log = Logger.getLogger(ImageController.class);
	
	@Resource
	private ImageUploadService imageUploadService;
	
	@RequestMapping(value="/getImage")
	public void getImage(@RequestParam("imageId") Long imageId,HttpServletResponse response) throws Exception {
		log.info("获取图片："+imageId);
		ImageUpload image = imageUploadService.selectById(imageId);
		if(image == null){
			log.info("图片不存在："+imageId);
			throw new Exception("图片不存在.");
		}
		OutputStream out = null;
		InputStream imageFile = null;
		try{
			String savePath = image.getSavePath();
			log.info("获取到图片路径："+savePath);
			imageFile = new FileInputStream(savePath); // 以byte流的方式打开文件
			int imageFileSize = imageFile.available(); // 得到文件大小
			log.info("获取到图片大小："+imageFileSize);
			
			out = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			response.setContentType("image/*"); // 设置返回的文件类型
			
			byte buffer[] = new byte[1024];
			int count = 0;
	        while ((count = imageFile.read(buffer)) >= 0) {
	            out.write(buffer, 0, count);
	        }
	        
		} catch (IOException e) {
			PrintWriter writer = response.getWriter(); // 得到向客户端输出文本的对象
			response.setContentType("text/html;charset=utf-8");
			writer.write("无法打开图片.");
			writer.close();
			log.error(e);
		} finally {
			if(out != null){
				out.close();
			}
			if(imageFile != null){
				imageFile.close();
			}
		}
	}
}
