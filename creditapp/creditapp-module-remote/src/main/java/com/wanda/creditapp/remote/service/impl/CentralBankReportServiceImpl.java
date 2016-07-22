package com.wanda.creditapp.remote.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CentralBankReportModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICentralBankReportService;
import com.wanda.creditapp.remote.service.IProductService;

/**
 * 人行个人报告,需要输入央行征信网站的账号密码及验证码;申请人姓名、身份证号、手机号从当前登录用户获取;
 * 先输入上述参数提交采集请求获取request_id,再根据此request_id获取人行个人报告
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.centralBankReportService)
public class CentralBankReportServiceImpl implements ICentralBankReportService{
	
	@Autowired
	@Qualifier(ProductConstant.productPCB265Service)
	private IProductService productPCB265Service;
	
	@Autowired
	@Qualifier(ProductConstant.productPCQ015Service)
	private IProductService productPCQ015Service;
	

	@Override
	public ProductResponseModel queryPersonalReport(CentralBankReportModel model) throws CreditAppException{
		//TODO get current user from context and inject params to model
//		User user = UserContext.currentUser();
//		inputParams.put(ProductConstant.name,user.getName());
//		inputParams.put(ProductConstant.cardNo,user.getCardNo());
//		inputParams.put(ProductConstant.mobile,user.getMobile());
		ProductResponseModel gatherRequestResult = productPCB265Service.productInvoke(model);
		if(gatherRequestResult!=null){
			Map<String,Object> retdata = gatherRequestResult.getRetdata();
			String request_id = (String)retdata.get(ProductConstant.request_id);
			CentralBankReportModel personalModel = new CentralBankReportModel();
			personalModel.setRequest_id(request_id);
			ProductResponseModel result = productPCQ015Service.productInvoke(personalModel);
			return result;
		}else{
			throw new CreditAppException(ExceptionConstant.innerException.getErrorCode(),ExceptionConstant.innerException.getErrorMessage());
		}
		
		
	}

}
