package com.wanda.creditapp.controller.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.requestmodel.user.FindCarInfoModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.remote.domain.uap.UapResponseModel;
import com.wanda.creditapp.remote.service.IUapUserManageService;
import com.wanda.creditapp.user.domain.CarInfoDomain;
import com.wanda.creditapp.user.service.ICarInfoService;

/**
 * 车辆信息
 */
@Controller
public class CarInfoController extends BaseController{

	 @Autowired
	 private ICarInfoService carInfoService;
	
	 @Autowired
	 private IUapUserManageService uapUserManageService;
	
	
	 /**
	  * 添加车辆
	  */
	 @RequestMapping(value="/car/info/save", method = RequestMethod.POST)
	 @ResponseBody
	 public Object saveCar(CarInfoDomain carInfoDomain) {
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.saveCarInfo(carInfoDomain);
		// 判断返回结果
		if (CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			//保存本地
			int result = this.carInfoService.saveCarInfo(carInfoDomain);
			if(result != 1){
				return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
			}
		} else {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
     }
	
	 
	 
	 /**
	  * 查询车辆信息
	  */
	 @SuppressWarnings("null")
	 @RequestMapping(value="/car/info/query", method = RequestMethod.POST)
	 @ResponseBody
	 public Object queryCarInfo(@Validated FindCarInfoModel findCarInfoModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		//本地查询车辆信息
		CarInfoDomain carInfoDomain = new CarInfoDomain();
		carInfoDomain.setName(findCarInfoModel.getName());
		carInfoDomain.setCardNo(findCarInfoModel.getCardNo());
		carInfoDomain.setLicenseNo(findCarInfoModel.getLicenseNo());
		carInfoDomain.setCarType(findCarInfoModel.getCarType());
		//可以为空
		carInfoDomain.setVin_17(findCarInfoModel.getVin_17());
		carInfoDomain.setRegisterTime(findCarInfoModel.getRegistTime());
		carInfoDomain.setCarDetail(findCarInfoModel.getCarDetail());
		carInfoDomain.setCarStatus(findCarInfoModel.getCarStatus());
		List<CarInfoDomain> list = this.carInfoService.queryCarInfo(carInfoDomain);
		if(list == null && list.size()<1){
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
		}
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.queryCarInfo(findCarInfoModel);
		// 判断返回结果
		if (!CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		} 
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
     }
	 
	
}
