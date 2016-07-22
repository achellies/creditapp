package com.wanda.creditapp.user.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wamda.creditapp.tool.service.AbstractFileUploadService;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.UUIDUtil;

@Service("portraitService")
public class PortraitServiceImpl extends AbstractFileUploadService{
	
	@Value("${portrait_path}")
	private String portraitPath;

	@Override
	protected String generateFilePath(MultipartFile multipartFile) {
		String originalFilename = multipartFile.getOriginalFilename();
		String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		return portraitPath + UUIDUtil.getUuid() + fileSuffix;
	}

	@Override
	protected String fetchFilePath() {
		//TODO User user = UserContext.currentUser();String filePath = user.getFilePath();
		
		return portraitPath+"bank.png";
	}

	@Override
	protected String generateFilePath(String fileType) {
		return portraitPath + UUIDUtil.getUuid() + "." + fileType;
	}

	
	
}
