package com.wanda.creditapp.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wamda.creditapp.tool.service.FileUploadService;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.util.Base64Util;

@Controller
@RequestMapping("portrait")
public class PortraitController {
	
	private static final Logger log = Logger.getLogger(PortraitController.class);
	
	@Autowired
	@Qualifier("portraitService")
	private FileUploadService portraitService;
	
	@ResponseBody
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public DataResponse upload(String base64Image,String fileType){
		DataResponse response = new DataResponse();		
		try{
			portraitService.upload(base64Image,fileType);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
		}catch(CreditAppException e){
			log.error("upload portrait occur an exception:",e);
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(e.getErrMsg());
		}		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/download")
	public DataResponse download(){
		DataResponse response = new DataResponse();
		try{
			File file = portraitService.download();
			String base64Image = Base64Util.fileToBase64(file);
			response.getDataMap().put("base64Image", base64Image);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
		}catch(CreditAppException e){
			log.error("download portrait occur an CreditAppException:", e);
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
		}
		return response;
	}
	
	@RequestMapping(value="/uploadFile")
	public String uploadFile(){
		return "portrait/uploadFile";
	}

}
