package com.wanda.creditapp.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.response.DataResponse;

public class BaseController {

	//private static final String ERROR_KEY_PREFIX = "VALID_ERR_";

	private static final Logger logger = Logger.getLogger(BaseController.class);

	// 校验时的错误信息
	public DataResponse buildRspWithErrors(BindingResult bindingResult) {
		DataResponse dr = new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_VALIDATE_FAIL);
//		List<FieldError> errors = bindingResult.getFieldErrors();
//		FieldError err;
//		for (int i = 0; i < errors.size(); i++) {
//			err = errors.get(i);
//			logger.error("ModelName:" + err.getObjectName() + "\tFieldName:" + err.getField() + "\tFieldValue:" + err.getRejectedValue() + "\tMessage:" + err.getDefaultMessage());
//			dr.put(ERROR_KEY_PREFIX + i, "[" + err.getField() + "]" + err.getDefaultMessage());
//		}
//		return ResponseEntity.badRequest().body(dr);
		
         List<FieldError> allErrors = bindingResult.getFieldErrors();
         List<String> msgs = new ArrayList<String>();
         FieldError err;
         for (int i = 0; i < allErrors.size(); i++) {
        	 err = allErrors.get(i);
        	 msgs.add("["+err.getField()+"]"+err.getDefaultMessage());
		}
         logger.error("校验时的错误信息----"+StringUtils.join(msgs, '|'));
         dr.put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, StringUtils.join(msgs, '|'));
         
         return dr;
	}

}
