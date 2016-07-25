package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;
import java.util.Date;

public class QueryRecord extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/**
     * 查询参数
     */
    private String parameter;

    /**
     * 查询时间
     */
    private Date queryTime;

    /**
     * 该报告的拥有者
     */
    private Integer userId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 版本号
     */
    private Integer dataVersion;

    /**
     * 查询结果
     */
    private String result;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}