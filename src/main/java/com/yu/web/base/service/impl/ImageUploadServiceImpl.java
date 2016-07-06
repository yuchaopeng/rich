package com.yu.web.base.service.impl;

import java.io.File;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.ApplicationUtils;
import com.yu.base.util.FileUtil;
import com.yu.web.base.mapper.ImageUploadMapper;
import com.yu.web.base.model.ImageUpload;
import com.yu.web.base.service.ImageUploadService;
import com.yu.web.base.service.SequenceService;

@Service
public class ImageUploadServiceImpl extends GenericServiceImpl<ImageUpload, Long> implements ImageUploadService {

	@Resource
	private ImageUploadMapper imageUploadMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	@Value("#{propertiesSetting['upload.lottery.photo.path']}")
	private String imageUploadPath;
	
	public ImageUpload imageUpload(MultipartFile multipartFile) throws Exception{
		String realName = multipartFile.getOriginalFilename();
		int id = sequenceService.get("ImageUpload");
		String imageSuffix = getImageSuffix(realName);
		String saveRootPath = getSaveRootPath();
		String newImageName = ApplicationUtils.randomUUID();
		String savePath = saveRootPath+newImageName+imageSuffix;
		ImageUpload imageUpload = null;
		try {
			FileUtil.writeFile(multipartFile.getInputStream(), savePath);
			imageUpload = new ImageUpload();
			imageUpload.setId(Long.valueOf(id));
			imageUpload.setRealName(realName);
			imageUpload.setSavePath(savePath);
			
			insert(imageUpload);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		return imageUpload;
	}
	
	private String getSaveRootPath(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		return imageUploadPath+year+File.separator+month+File.separator+day+File.separator;
	}
	
	private String getImageSuffix(String realName){
		String suffix = realName.substring(realName.lastIndexOf("."));
		return suffix;
	}
	
	
	@Override
	public GenericMapper<ImageUpload, Long> getMapper() {
		return imageUploadMapper;
	}
}