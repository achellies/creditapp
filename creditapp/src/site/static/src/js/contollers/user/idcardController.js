/*
 angular pageController for page<userCredit.html--/user/idcard >
 */
export default angular.module('wanda.app')
    .controller('idcardController', ['idcardInfo', '$scope', '$routeParams', '$location', 'userApiService', 'businessComponentsService', function(idcardInfo, $scope, $routeParams, $location, userApiService, businessComponentsService) {
        'use strict';
        var serialNo = $routeParams.serialno;
        //未设置身份证信息就跳转到拍照页面
        var idcardnotSet = (function(idcardInfo) {
            for (var key in idcardInfo) {
                if (idcardInfo.hasOwnProperty(key)) {
                    return false;
                }
            }
            return true;
        })(idcardInfo);
        if (idcardnotSet) {
            var process = businessComponentsService.resolveCreditProcess(serialNo, 'idcardscan');
            console.log(process.currentPath);
            $location.path(process.currentPath).replace();
        } else {
            $scope.realName = idcardInfo.realName;
            $scope.idCardNo = idcardInfo.idCardNo;
            $scope.validDate = idcardInfo.validDate;
            $scope.frontImgDataUrl = idcardInfo.frontImgDataUrl;
            $scope.backImgDataUrl = idcardInfo.backImgDataUrl;
        }

        var updateProcess = function() {
                var postData = { serialNo: serialNo, finishStep: 'idcard' };
                userApiService.putProcess(postData).then(function(responseData) {
                    var data = responseData.result;
                    var process = businessComponentsService.resolveCreditProcess(serialNo, data.nextStep);
                    $location.path(process.currentPath);
                }, errorDeal);
            },
            errorDeal = function(error) {
                if (angular.isObject(error)) {
                    if (error.errorCallback) {
                        $.showDialog({
                            title: '',
                            button: ['返回'],
                            content: error.errorMessage
                        }, null, error.errorCallback);
                    } else {
                        if (error.errorMessage) {
                            $.showMsg(error.errorMessage);
                        }
                        if (angular.isString(error)) {
                            $.showMsg(error);
                        }
                    }
                }
            };
        $scope.agreement = [{ desc: '征信授权查询协议' }];

        //提交跳转
        $scope.submit = function() {
            updateProcess({});
        }
    }]);
