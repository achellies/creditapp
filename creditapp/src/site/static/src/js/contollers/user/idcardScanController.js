/*
 angular pageController for page<idcard-scan.html>
 */
export default angular.module('wanda.app')
    .controller('idcardScanController', ['$scope', '$routeParams', '$location', 'userApiService', 'commonApiService', 'businessComponentsService', function($scope, $routeParams, $location, userApiService, commonApiService, businessComponentsService) {
        'use strict';
        var serialNo = $routeParams.serialno,
            img_header = 'data:image/jpg;base64,';
        var postIdcard = function(postData) {
                return userApiService.postIdcard(postData).then(function(responseData) {
                    var data = responseData.result;
                    return data;
                })
            },
            updateProcess = function(idcardResult) {
                var postData = { serialNo: serialNo, finishStep: 'idcard' };
                return userApiService.putProcess(postData).then(function(responseData) {
                    var data = responseData.result;
                    var process = businessComponentsService.resolveCreditProcess(serialNo, data.nextStep);
                    $location.path(process.currentPath);
                });
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
        $scope.fnScanPositiveCallBack = function(imageBase64) {
            if (imageBase64.length > 200) {
                $scope.frontImgDataUrl = img_header + imageBase64;
            } else {
                $scope.frontImgDataUrl = imageBase64;
            }
            $scope.$apply();
        };
        $scope.fnScanNegativeCallBack = function(imageBase64) {
            if (imageBase64.length > 200) {
                $scope.backImgDataUrl = img_header + imageBase64;
            } else {
                $scope.backImgDataUrl = imageBase64;
            }
            $scope.$apply();
        };
        $scope.callNative_Idcard = function(cardSide) {
            if (window.wdCordova && window.wdCordova.getIdCard) {
                window.wdCordova.getIdCard(
                    function(data) {
                        var data = JSON.parse(data);
                        if (data.status = 'success') {
                            if (cardSide == "front") {
                                $scope.fnScanPositiveCallBack(data.image);
                            }
                            if (cardSide == "back") {
                                $scope.fnScanNegativeCallBack(data.image);
                            }
                        }
                    },
                    function(data) {
                        $.showMsg(data);
                    }, [{
                        pictureSourceType: "camera",
                        cardSide: cardSide
                    }]);
            }
        };

        //frontImg Watch
        $scope.$watch('frontImgDataUrl', function(newVal, oldVal, scope) {
            if (newVal && newVal.indexOf('base64') > 0) {
                $.showBlockLoading('处理中请稍后...');
                var postData = { "idcardImage": newVal.replace(img_header, "") };
                //var postData = { "idcardImage": "" };
                //提交身份证正面
                commonApiService.postOcrIdcard(postData).then(
                    function(responseData) {
                        var data = responseData.result;
                        if (data) {
                            $scope.realName = data.name;
                            $scope.idCardNo = data.idCardNumber;
                        }
                    },
                    function(responseData) {
                        if (responseData) {
                            $.showMsg(responseData.errorMessage);
                        } else {
                            $.showMsg("身份证正面解析失败请重拍");
                        }
                        $.hideBlockLoading();
                    });
            }
        });
        //backImg Watch
        $scope.$watch('backImgDataUrl', function(newVal, oldVal, scope) {
            if (newVal && newVal.indexOf('base64') > 0) {
                $.showBlockLoading('处理中请稍后...');
                var postData = { "idcardImage": newVal.replace(img_header, "") };
                //var postData = { "idcardImage": "" };
                //提交身份证正面
                commonApiService.postOcrIdcard(postData).then(
                    function(responseData) {
                        var data = responseData.result;
                        if (data) {
                            $scope.validDate = data.validDate;
                        }
                    },
                    function(responseData) {
                        if (responseData) {
                            $.showMsg(responseData.errorMessage);
                        } else {
                            $.showMsg("身份证反面解析失败请重拍");
                        }
                        $.hideBlockLoading();
                    });
            }
        });
        //idCardno Watch
        $scope.$watch('idCardNo', function(newVal, oldVal, scope) {
            if (newVal) {
                var idCardReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
                if (!idCardReg.test($scope.idCardNo)) {
                    $scope.idCardNo = '';
                }
            }
        });


        $scope.idcard_submit = function() {
            $.showBlockLoading('处理中请稍候...');
            var postData = {
                realName: $scope.realName,
                idCard: $scope.idCardNo,
                validDate: $scope.validDate
            };
            postIdcard(postData)
                .then(updateProcess)
                .then(function(success) {}, errorDeal)
                .catch(errorDeal);
        }
    }]);
