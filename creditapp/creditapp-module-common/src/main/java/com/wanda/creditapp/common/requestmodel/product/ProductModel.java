package com.wanda.creditapp.common.requestmodel.product;


/**
 * 所有产品调用的父类,通用属性productType表示产品类型(个人产品,企业产品)
 * @author xuxiaobin5
 *
 */
public class ProductModel{
	
	/**页码*/
	private int currentPage;
	/**每页数量(最少5条,最大100条)*/
	private int pageSize;
	/**查询信息种类,见接口文档*/
	private String sourcet;

	public String getSourcet() {
		return sourcet;
	}

	public void setSourcet(String sourcet) {
		this.sourcet = sourcet;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
