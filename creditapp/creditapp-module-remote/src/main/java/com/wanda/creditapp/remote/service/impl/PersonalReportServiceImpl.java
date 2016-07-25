package com.wanda.creditapp.remote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.PersonalReportModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.IPersonalReportService;
import com.wanda.creditapp.remote.service.IProductService;

/**
 * 个人报告查询，目前需要调用个人学历查询(P_C_B004)，个人投资核查(P_C_B005)，公检法负面信息排查(P_B_B007)，驾驶证信息查询(P_C_B110)及车辆信息查询(P_C_B111)；
 * 日后如要新增调用的产品,在本类下新增属性IProductService，将新增产品的实现注入，并在buildProductIdServiceMap()方法中加一行代码
 * productIdServiceMap.put(productId, productService);
 * @author xuxiaobin5
 *
 */
@Service
public class PersonalReportServiceImpl implements IPersonalReportService{
	
	private static final Logger log = Logger.getLogger(PersonalReportServiceImpl.class);
	
	@Value("${core_pool_size}")
	private int corePoolSize;
	
	@Value("${max_pool_size}")
	private int maxPoolSize;
	
	@Value("${keep_alive_time}")
	private long keepAliveTime;
	
	@Value("${timeout}")
	private int timeout;
	
	@Autowired
	@Qualifier(ProductConstant.productPCB004Service)
	private IProductService productPCB004Servcie;
	
	@Autowired
	@Qualifier(ProductConstant.productPCB005Service)
	private IProductService productPCB005Service;
	
	@Autowired
	@Qualifier(ProductConstant.productPBB007Service)
	private IProductService productPBB007Service;
	
	@Autowired
	@Qualifier(ProductConstant.productPCB110Service)
	private IProductService productPCB110Service;
	
	@Autowired
	@Qualifier(ProductConstant.productPCB111Service)
	private IProductService productPCB111Service;
	
	private Map<String,IProductService> productIdServiceMap;
	
	private static Integer flag = 0;

	@Override
	public List<ProductResponseModel> queryPersonalReport(PersonalReportModel model) throws CreditAppException {
		List<ProductResponseModel> modelList = new ArrayList<ProductResponseModel>();
		if(flag==0){
			synchronized(flag){
				if(flag==0){
					productIdServiceMap = buildProductIdServcieMap();
					flag++;
				}
			}	
		}
		ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		CompletionService<ProductResponseModel> completionService = new ExecutorCompletionService<ProductResponseModel>(pool);
		int nThread = 0;
		for(String productId:model.getProductIdList()){
			IProductService productService = productIdServiceMap.get(productId);
			if(productService != null){
				Callable<ProductResponseModel> callable = new RemoteTask(productService,model,productId);
				completionService.submit(callable);
				nThread++;
			}else{
				log.error("can find IProductService where productId is:" + productId);
			}
		}
		long timeLimit = System.currentTimeMillis() + 1000*timeout;
		while(nThread>0){
			if(System.currentTimeMillis() > timeLimit){
				pool.shutdown();
				break;
			}
			Future<ProductResponseModel> future = completionService.poll();
			if(future!=null){
				try {
					ProductResponseModel result = future.get();
					modelList.add(result);
				} catch (InterruptedException | ExecutionException e) {
					log.error("fetch result occur an Exception", e);
				}finally{
					nThread--;
				}
			}
		}
		pool.shutdown();
		return modelList;
	}
	
	private Map<String,IProductService> buildProductIdServcieMap(){
		Map<String,IProductService> productIdServiceMap = new HashMap<String,IProductService>();
		productIdServiceMap.put(ProductConstant.P_C_B004, productPCB004Servcie);
		productIdServiceMap.put(ProductConstant.P_C_B005, productPCB005Service);
		productIdServiceMap.put(ProductConstant.P_B_B007, productPBB007Service);
		productIdServiceMap.put(ProductConstant.P_C_B110, productPCB110Service);
		productIdServiceMap.put(ProductConstant.P_C_B111, productPCB111Service);
		return productIdServiceMap;
	}
	
	class RemoteTask implements Callable<ProductResponseModel>{
		
		private IProductService productService;
		
		private ProductModel productModel;
		
		private String productId;
		
		public RemoteTask(IProductService productService,ProductModel productModel,String productId){
			this.productModel = productModel;
			this.productService = productService;
			this.productId = productId;
		}

		@Override
		public ProductResponseModel call() throws Exception {
			ProductResponseModel result = productService.productInvoke(productModel);
			result.setProd_id(productId);
			return result;
		}
		
	}

}
