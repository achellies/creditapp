package com.wanda.creditapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.response.DataResponse;

@Controller
public class IndexController {

	@RequestMapping("/")
	@ResponseBody
	public DataResponse index() {
		// 创建响应结果方式1
		// DataResponse dr=new DataResponse();
		// dr.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
		// dr.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());

		// 创建响应结果方式2
		 DataResponse dr=new DataResponse(ResponseConstant.RESPONSE_SUCCESS);

		dr.put("a", "a");
		dr.put("b", "b");
		return dr;
	}

	@RequestMapping("/h")
	@ResponseBody
	public DataResponse h() {
		DataResponse br = new DataResponse();
		br.setResultCode("11111");
		br.setResultMessage("hahahaha ");
		return br;
	}
}
