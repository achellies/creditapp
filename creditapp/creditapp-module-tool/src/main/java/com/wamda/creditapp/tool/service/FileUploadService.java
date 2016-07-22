package com.wamda.creditapp.tool.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.wanda.creditapp.common.exception.CreditAppException;

/**
 * 该接口用于文件上传,但是不建议直接实现该接口,建议继承AbstractFileUploadService
 * @author xuxiaobin5
 *
 */
public interface FileUploadService {
	
	/**
	 * 
	 * @param multipartFile 从Controller传入的MultipartFile对象
	 * @return 文件的全路径,如/tem/portrait/absc123.jpg
	 * @throws CreditAppException
	 */
	public String upload(MultipartFile multipartFile) throws CreditAppException;
	
	/**
	 * 下载文件
	 * @return
	 * @throws CreditAppException
	 */
	public File download() throws CreditAppException;
	
	/**
	 * 
	 * @param base64File Base64编码的文件
	 * @return 
	 * @throws CreditAppException
	 */
	public String upload(String base64File,String fileType) throws CreditAppException;

	

}
