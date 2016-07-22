//api/v1/risk   api/v1/banklimit
function commonApiService(apiBaseService, $q) {
    //返回公共API
    return ({
        getQuestionsItems: getQuestionsItems,
        getBanklimit: getBanklimit,
        postPhoneVerifyCode: postPhoneVerifyCode,
        getIssuebank: getIssuebank,
        postOcrIdcard: postOcrIdcard
    });

    function getIssuebank(data) {
        data.v = "1";
        var request = apiBaseService.Get('bankcard/issuebank', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }

    function postPhoneVerifyCode(data) {
        data.v = "1";
        var request = apiBaseService.Post('verifycode', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handleErrorOld));
    }

    function getQuestionsItems() {
        var request = apiBaseService.Get('risk/questions', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    function getBanklimit() {
        var request = apiBaseService.Get('banklimit', {});
        return (request.then(apiBaseService.handleSuccess, apiBaseService.handleError));
    }

    function postOcrIdcard(data) {
        data.v = "1";
        var request = apiBaseService.Post('ocr/idcard', data);
        return (request.then(apiBaseService.handleSuccessOld, apiBaseService.handlerErrorOld));
    }
}

/**
 * 
 */
export default [{
    name: 'commonApiService',
    dependences: ['apiBaseService', '$q'],
    fn: commonApiService
}]
