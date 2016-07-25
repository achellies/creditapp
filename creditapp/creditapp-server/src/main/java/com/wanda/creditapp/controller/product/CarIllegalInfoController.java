package com.wanda.creditapp.controller.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CarIllegalInfoModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICarIllegalInfoService;


@Controller
public class CarIllegalInfoController extends BaseController{

	private static final Logger logger = Logger.getLogger(CarIllegalInfoController.class);
	
	@Autowired
	private ICarIllegalInfoService carIllegalInfoService;
	
	/**
	 * 车辆违章信息
	 */
	@RequestMapping(value="car/illegal/query", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public DataResponse queryPersonalReport(@Validated @RequestBody CarIllegalInfoModel carIllegalInfoModel,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return buildRspWithErrors(bindingResult);
		}
		DataResponse response ;
		try{
			List<ProductResponseModel> responseList = carIllegalInfoService.queryCarIllegalInfo(carIllegalInfoModel);
			response = new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
			response.put(ProductConstant.responseList, responseList);
		}catch(CreditAppException e){
			logger.error("查询违章车辆信息异常!", e);
			response = new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return response;
	}
	
}
