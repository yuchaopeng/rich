package com.yu.web.base.service;

import org.springframework.web.multipart.MultipartFile;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.ImageUpload;

public interface ImageUploadService extends GenericService<ImageUpload, Long> {
	ImageUpload imageUpload(MultipartFile multipartFile) throws Exception;
}
