package com.wanda.creditapp.controller.dissent;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.user.DissentModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.user.domain.DissentDomain;
import com.wanda.creditapp.user.service.IDissentService;

/**
 * 异议
 */
@Controller
public class DissentController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(DissentController.class);
	
	@Autowired
	private IDissentService dissentService;
	
	
	/**
	 * 新增异议
	 */
	@RequestMapping(value="/car/dissent/save",method=RequestMethod.POST)
	@ResponseBody
    public DataResponse saveDissent(@Validated @RequestBody DissentModel dissentModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		try{
			DissentDomain dissentDomain = new DissentDomain();
			dissentDomain.setDissentContent(dissentModel.getDissentContent());
			dissentDomain.setUserId(dissentModel.getUserId());
			dissentDomain.setRecordId(dissentModel.getRecordId());
			this.dissentService.saveDissent(dissentDomain);
		}catch(CreditAppException e){
			logger.error("DissentController.saveDissent---新增异议失败!", e);
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
    }
	
	
    
    /**
     * 查看所有异议
     */
	@RequestMapping(value="/car/dissent/query/all", method=RequestMethod.POST)
	@ResponseBody
	public DataResponse queryAllCarDissent(){
		DataResponse response = new DataResponse();
		try{
			List<DissentDomain> list = this.dissentService.queryAllCarDissent();
			response = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
			response.put(ProductConstant.responseList, list);
		}catch(CreditAppException e){
			logger.error("DissentController.queryAllCarDissent---查询所有异议失败!", e);
			response = new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return response;
	}
    
    
    /**
     * 异议详细查看
     */
	 @RequestMapping(value = "/car/dissent/query/{recordId}", method = RequestMethod.POST)
	 @ResponseBody
     public DataResponse queryCarDissentByRecordId( @PathVariable("recordId") Integer recordId) {
		DataResponse response = new DataResponse();
		try{
			DissentDomain dissentDomain = this.dissentService.queryCarDissentByRecordId(recordId);
			response = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
			response.put(ProductConstant.responseDomain, dissentDomain);
		}catch(CreditAppException e){
			logger.error("DissentController.queryCarDissentByRecordId---查询异议详情失败!", e);
			response = new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return response;
     }
    
    
}
