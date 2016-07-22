//api/v1/user/ 
function userApiService(apiBaseService, $q) {
    //返回公共API
    return ({
        getRiskStatus: getRiskStatus,
        setRiskStatus: setRiskStatus,
        getPassword: getPassword,
        setPassword: setPassword,
        getOrder: getOrder,
        addOrder: addOrder,
        bankcard: {
            getBankcard: getBankcard,
            getBankcards: getBankcards,
            addBankcard: addBankcard,
            postBankcardVerifyCode: postBankcardVerifyCode,
            postSetDefaultBankcard: postSetDefaultBankcard
        },
        repayment: {
            getRepayment: getRepayment,
            getRepayments: getRepayments,
            postRepayment: postRepayment
        },
        getPosition: getPosition,
        validUser: validUser,
        getIdcard: getIdcard,
        postIdcard: postIdcard,
        getProcess: getProcess,
        createProcess: createProcess,
        putProcess: putProcess,
        deleteProcess: deleteProcess,
        postFace: postFace,
        getContactInfo: getContactInfo,
        postContactInfo: postContactInfo,
        getPasswordSet: getPasswordSet
    });

    //风险
    function getRiskStatus() {
        var request = apiBaseService.Get('user/riskstatus', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    function setRiskStatus(data) {
        var request = apiBaseService.Post('user/riskstatus', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }
    //订单
    function getOrder(data) {
        var request = apiBaseService.Get('user/order', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    function addOrder(data) {
        var request = apiBaseService.Post('user/order', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }
    //密码
    function getPassword() {
        var request = apiBaseService.Get('user/password', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //设置密码
    function setPassword(data) {
        var request = apiBaseService.Post('user/password', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //获取单张银行卡信息
    function getBankcard(data) {
        data.v = "1";
        var request = apiBaseService.Get('user/bankcard/select', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }

    //银行卡
    function getBankcards(data) {
        data.v = "1";
        var request = apiBaseService.Get('user/bankcards', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //新增银行卡
    function addBankcard(data) {
        data.v = "1";
        var request = apiBaseService.Post('user/bankcard/add', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //通过银行卡获取验证码
    function postBankcardVerifyCode(data) {
        var request = apiBaseService.Post('user/bankcard/verifycode', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //设置默认卡
    function postSetDefaultBankcard(data) {
        data.v = "1";
        var request = apiBaseService.Post('user/bankcard/select', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //持仓
    function getPosition() {
        var request = apiBaseService.Get('user/position', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }
    // 验证用户完整性
    function validUser() {
        var request = apiBaseService.Get('user/info', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }
    //获取流程
    function getProcess(data) {
        var request = apiBaseService.Get('user/process', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //获取流程
    function createProcess(data) {
        var request = apiBaseService.Post('user/process', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //更改流程
    function putProcess(data) {
        var request = apiBaseService.Put('user/process', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //撤销流程
    function deleteProcess(data) {
        var request = apiBaseService.Delete('user/process', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }
    // 复用原先项目的API，响应结果与新API不一致
    // 获取身份证信息
    function getIdcard(data) {
        data.v = "1";
        var request = apiBaseService.Get('user/idcard', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //提交身份证信息
    function postIdcard(data) {
        data.v = "1";
        var request = apiBaseService.Post('user/idcard', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //提交人脸识别照片
    function postFace(data) {
        data.v = "1";
        var request = apiBaseService.Post('user/face', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }

    //获取联系人信息
    function getContactInfo(data) {
        data.v = "1";
        var request = apiBaseService.Get('user/extra', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //提交联系人信息
    function postContactInfo(data) {
        data.v = "1";
        var request = apiBaseService.Post('user/extra', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }
    //是否设置过交易密码
    function getPasswordSet(data) {
        data.v = "1";
        var request = apiBaseService.Get('user/password/trade', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }

    //获取还款详情
    function getRepayment(data) {
        var request = apiBaseService.Get('user/repayment', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //获取还款计划
    function getRepayments(data) {
        var request = apiBaseService.Get('user/repayments', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    //提前还款
    function postRepayment(data) {
        var request = apiBaseService.Post('user/repayment', data);
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

}
/**
 * export wd-services module
 */
export default [{
    name: 'userApiService',
    dependences: ['apiBaseService', '$q'],
    fn: userApiService
}];
