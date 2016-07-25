package com.wanda.creditapp.controller.product;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CarInfoModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.remote.domain.CarInfoDomain;
import com.wanda.creditapp.remote.service.ICarInfoService;

@Controller
public class CarInfoController extends BaseController{

	private static final Logger logger = Logger.getLogger(CarInfoController.class);
	
	@Autowired
	private ICarInfoService carInfoService;
	
	
	/**
	 * 新增车辆
	 */
	@RequestMapping(value="car/save", method=RequestMethod.POST)
	@ResponseBody
    public DataResponse saveCarInfo(@Validated @RequestBody CarInfoModel carInfoModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		try{
			CarInfoDomain carInfoDomain = new CarInfoDomain();
			carInfoDomain.setName(carInfoModel.getName());
			carInfoDomain.setCardNo(carInfoModel.getCardNo());
			carInfoDomain.setLicenseNo(carInfoModel.getLicenseNo());
			carInfoDomain.setCarType(carInfoModel.getCarType());
			//非空的话,插入
			if(StringUtils.isNotEmpty(carInfoModel.getVin_17())){
				carInfoDomain.setVin_17(carInfoModel.getVin_17());
			}
			if(StringUtils.isNotEmpty(carInfoModel.getVin_17())){
				carInfoDomain.setRegisterTime(carInfoModel.getRegistTime());
			}
			if(StringUtils.isNotEmpty(carInfoModel.getVin_17())){
				carInfoDomain.setCarDetail(carInfoModel.getCarDetail());
			}
			if(StringUtils.isNotEmpty(carInfoModel.getVin_17())){
				carInfoDomain.setCarStatus(carInfoModel.getCarStatus());
			}
			this.carInfoService.saveCarInfo(carInfoDomain);
		}catch(CreditAppException e){
			logger.error("DissentController.saveCarInfo---新增车辆失败!", e);
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
    }
	
	
	
}
