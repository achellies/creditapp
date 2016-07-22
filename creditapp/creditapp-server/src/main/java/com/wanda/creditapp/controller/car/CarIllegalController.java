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
import com.wanda.creditapp.common.requestmodel.user.FindCarIllegalModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.remote.domain.uap.UapResponseModel;
import com.wanda.creditapp.remote.service.IUapUserManageService;
import com.wanda.creditapp.user.domain.CarIllegalDomain;
import com.wanda.creditapp.user.service.ICarIllegalService;

/**
 * 车辆违章
 */
@Controller
public class CarIllegalController extends BaseController{
	
	@Autowired
	private ICarIllegalService carIllegelService;
	
	@Autowired
	private IUapUserManageService uapUserManageService;
	
	 
	 /**
	  * 车辆违章信息查询 
	  */
	 @SuppressWarnings("null")
	 @RequestMapping(value="/car/illegal/query", method = RequestMethod.POST)
	 @ResponseBody
	 public Object queryIllegalCar(@Validated FindCarIllegalModel findCarIllegalModel, BindingResult bindingResult) {
		// 校验时的错误信息
		if (bindingResult.hasErrors())
			return buildRspWithErrors(bindingResult);
		//本地查询违章车辆信息
		CarIllegalDomain carDomain = new CarIllegalDomain();
		carDomain.setCarNumber(findCarIllegalModel.getCarNumber());
		carDomain.setCarType(findCarIllegalModel.getCarType());
		carDomain.setCarCode(findCarIllegalModel.getCarCode());
		carDomain.setCarEngine(findCarIllegalModel.getCarEngine());
		List<CarIllegalDomain> list = this.carIllegelService.queryIllegalCar(carDomain);
		if(list == null && list.size()<1){
			return new DataResponse(ResponseConstant.RESPONSE_ERRORCODE_NATIVE_FAIL);
		}
		// 响应模型
		UapResponseModel uapResponseModel = this.uapUserManageService.queryIllegalCar(findCarIllegalModel);
		// 判断返回结果
		if (!CommonConstant.UAP_SUCCESS_CODE.equals(uapResponseModel.getRespCode())) {
			return new DataResponse(ResponseConstant.RESPONSE_FAIL);
		}
		return new DataResponse(ResponseConstant.RESPONSE_SUCCESS);
     }
	 
	 
    
}
