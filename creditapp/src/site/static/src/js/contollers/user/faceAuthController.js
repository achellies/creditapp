/**
 *  angular pageController for page<userCredit.html--/user/faceauth >
 */
export default angular.module('wanda.app')
    .controller('faceAuthController', ['$scope', '$routeParams', '$http', '$location', '$timeout', 'userApiService', 'businessComponentsService', function($scope, $routeParams, $http, $location, $timeout, userApiService, businessComponentsService) {
        'use strict';
        var serialNo = $routeParams.serialno;
        var img_header = 'data:image/jpg;base64,';
        $scope.flag_face_error = false;
        var postFace = function(postData) {
                return userApiService.postFace(postData).then(function(responseData) {
                    var data = responseData.result;
                    return data;
                });
            },
            postFaceErrorDeal = function(data) {
                if (data && data.errorMessage) {
                    if (data.errorCode == 4001) {
                        $scope.flag_face_error = true;
                        $scope.retryWarning = data.retryRemains;
                    } else if (data.errorCode == 4041) {
                        $.showMsg("账号已锁定");
                        // window.location = GLB_CTX + "/page/user/locked";
                    }
                } else {
                    $.showMsg("识别失败请重试");
                }
            },
            updateProcess = function(faceResult) {
                var postData = { serialNo: serialNo, finishStep: 'face' };
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

        $scope.livebody_submit = function() {
            $.showBlockLoading('处理中请稍候...');
            window.wdCordova.LiveCheck(
                function(data) {
                    var result = JSON.parse(data);
                    if (result.status == "success") {
                        var postData = {
                            liveBodyImage: result.image
                        }
                        postFace(postData)
                            .then(updateProcess, postFaceErrorDeal)
                    } else {
                        $.hideBlockLoading();
                        $.showMsg("识别失败请重试");
                    }
                },
                function(data) {
                    $.hideBlockLoading();
                    $.showMsg("识别失败请重试");
                }, [{}]
            );
        };


    }]);
