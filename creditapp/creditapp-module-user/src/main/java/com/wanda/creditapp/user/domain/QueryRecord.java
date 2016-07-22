package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class QueryRecord extends BaseDomain {
    /**
     * 序列号
     */
    private String serialno;

    /**
     * 产品类型
     */
    private String producttype;

    /**
     * 输入参数
     */
    private String parameter;

    /**
     * 用户ID
     */
    private Integer userid;

    private Integer version;

    /**
     * 返回结果
     */
    private String response;

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno == null ? null : serialno.trim();
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }
}