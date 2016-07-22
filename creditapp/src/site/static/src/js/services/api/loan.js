/**
 * 分期api服务
 * @param apiBaseService {apiBaseService} 基础api服务
 * @param $q {Promise} 工具
 */

function loanService(apiBaseService, $q) {
    var handleSuccess = apiBaseService.handleSuccess;
    var handleError = apiBaseService.handleError;

    /**
     * 获取分期详情
     * @param serialNo {String} 贷款编号
     * @return {Promise}
     */

    this.getLoan = function(serialNo, productId) {
        // $.showBlockLoading('加载中...');
        return apiBaseService.Get('user/loan?serialNo=' + serialNo + '&productId=' + productId)
            .then(handleSuccess, handleError);
    }

    /**
     * 获取已申请分期产品列表
     * @param params {Object}
     * @return {Promise}
     */
    this.getLoanList = function() {
        return apiBaseService.Get('user/loans')
            .then(handleSuccess, handleError);
    }

    /**
     * 获取产品详情信息
     * @param productId {String} 产品id
     * @return {Promise}
     */
    this.getLoanProduct = function(productId) {
        return apiBaseService.Get('product?productId=' + productId)
            .then(handleSuccess, handleError);
    }

    /**
     * 获取商户信息
     * @param merchantid {String} 商户id
     * @return {Promise}
     */

    this.getMerchant = function(code) {
        return apiBaseService.Get('merchant?code=' + code)
            .then(handleSuccess, handleError);
    }

    /**
     * 提交分期申请
     * @param serialNo {String} 贷款编号
     * @return {Promise}
     */
    this.applyLoan = function(serialNo, password) {
        var postData = {
            serialNo: serialNo,
            password: password
        }
        return apiBaseService.Post('user/loan', postData)
            .then(handleSuccess, handleError);
    }

    /**
     * 支付分期
     * @param serialNo {String} 贷款编号
     * @param productId {String} 产品id
     * @param index {String} 第几笔
     * @param payAmount {String} 支付金额
     * @return {Promise}
     */
    this.payLoan = function(serialNo, productId, index, password, payAmount) {
        var postData = {
            "serialNo": serialNo,
            "index": index,
            "password": password,
            "payAmount": payAmount,
            "productId": productId
        }
        
        return apiBaseService.Put('user/loan', postData)
            .then(handleSuccess, handleError);
    }
};

export default [{
    name: 'loanService',
    dependences: ['apiBaseService', '$q'],
    fn: loanService
}];
