package com.wamda.creditapp.tool.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.Base64Util;

public abstract class AbstractFileUploadService implements FileUploadService{
	
	private static final Logger log = Logger.getLogger(AbstractFileUploadService.class);

	@Override
	public String upload(MultipartFile multipartFile) throws CreditAppException {
		String filePath = generateFilePath(multipartFile);
		File file = new File(filePath);
		try{
			multipartFile.transferTo(file);
		}catch(IOException e1){
			throw new CreditAppException("AbstractFileUploadService.upload-IO异常",e1);
		}catch(IllegalStateException e2){
			throw new CreditAppException("AbstractFileUploadService.upload-非法状态异常",e2);
		}
		return filePath;
	}
	
	@Override
	public File download() throws CreditAppException{
		String filePath = fetchFilePath();
		return new File(filePath);
	}
	
	@Override
	public String upload(String base64File,String fileType) throws CreditAppException{
		String filePath = generateFilePath(fileType);
		File file = new File(filePath);
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			log.error("upload file occur an FileNotFoundException:", e);
			throw new CreditAppException(ExceptionConstant.innerException);
		}
		byte[] fileByte = Base64Util.decode(base64File);
		try {
			stream.write(fileByte);
		} catch (IOException e) {
			try {
				stream.close();
			} catch (IOException e1) {
				log.error("close output stream occur an IOException:", e1);
			}
			log.error("write bytes to file occur an IOException:", e);
			throw new CreditAppException(ExceptionConstant.innerException);
		}
		try{
			stream.close();
		}catch(IOException e){
			log.error("close output stream occur an IOException:", e);
			throw new CreditAppException(ExceptionConstant.innerException);
		}
		return filePath;
	}
	
	protected abstract String generateFilePath(String fileType);

	protected abstract String generateFilePath(MultipartFile multipartFile);
	
	protected abstract String fetchFilePath();

}
