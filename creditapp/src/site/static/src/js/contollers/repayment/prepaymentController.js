export default angular.module('wanda.app')
    .controller('prepaymentController', function($scope, $routeParams, $location, $timeout, userApiService) {
        var serialNo = $routeParams.serialNo;
        //加载欠款详情
        var fnLoadRepayment = function(serialNo) {
                return userApiService.repayment.getRepayment({ serialNo: serialNo })
                    .then(function(responseData) {
                        return responseData.result;
                    });
            },
            //数据binding'
            fnInitPage = function(repaymentDetail) {
                $scope.repayment = repaymentDetail;
                if (repaymentDetail.isOverdue) {
                    $scope.allowSubmit = false;
                    $scope.prepayAmount = repaymentDetail.interestFee + repaymentDetail.penaltyFee;
                } else {
                    $scope.allowSubmit = true;
                    $scope.prepayAmount = repaymentDetail.repaymentAmount;
                }
            },
            //异常处理
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
            },
            //逾期时 还款金额修改事件
            onPrepayPrincipalAmountChange = function() {
                var repaymentDetail = $scope.repayment;
                if (repaymentDetail.isOverdue) {
                    $scope.allowSubmit = $scope.prepayPrincipalAmount > 0 && $scope.prepayPrincipalAmount <= repaymentDetail.overdueAmount;
                    $scope.prepayAmount = repaymentDetail.interestFee + repaymentDetail.penaltyFee + $scope.prepayPrincipalAmount;
                }
            },
            //提交
            submitPrepay = function() {
                if (!$scope.onSubmit) {
                    $.showBlockLoading("密码确认中...");
                    $scope.onSubmit = true;
                    window.wdCordova.getTranscationPwd(function(data) {
                        data = JSON.parse(data);
                        $.hideBlockLoading();
                        var postData = {
                            serialNo: serialNo,
                            password: data.password,
                            prepayAmount: $scope.prepayAmount
                        }
                        userApiService.repayment.postRepayment(postData).then(function(responseData) {
                            $.toast({ content: '支付成功...', stayTime: 2000 });
                            $timeout(function() {
                                $location.path("/success").replace();
                            }, 1900);
                        }, function(error) {
                            $.hideBlockLoading();
                            $scope.onSubmit = false;
                            // if (error.errorCode == 4013) {
                            $.showDialog({
                                title: '',
                                button: ['返回'],
                                content: error.errorMessage
                            }, null, null);
                            // }

                        });

                    }, function() {}, [{}]);

                }
            };
        $scope.onPrepayPrincipalAmountChange = onPrepayPrincipalAmountChange;
        $scope.submitPrepay = submitPrepay;
        $scope.onSubmit = false;
        fnLoadRepayment(serialNo)
            .then(fnInitPage, errorDeal)
            .catch(errorDeal);
    });
