export default angular.module('wanda.app')
    .controller('repaymentPlanController', function($scope, $routeParams, $location, $timeout, userApiService) {
        var serialNo = $routeParams.serialNo;
        //加载欠款详情
        var fnLoadPlanList = function(serialNo) {
                return userApiService.repayment.getRepayments({ serialNo: serialNo })
                    .then(function(responseData) {
                        return responseData.result;
                    });
            },
            //数据binding'
            fnInitPage = function(repaymentPlanList) {
                $scope.planList = repaymentPlanList.paymentList;
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
            }

        fnLoadPlanList(serialNo).then(fnInitPage, errorDeal);

        $scope.toggle = function(period) {
            var curArrow = $('#arrow-' + period);

            if (curArrow.hasClass("up")) {
                //当前已经展开
                $('#arrow-' + period).removeClass("up").addClass("down");
                $('#plan-detail-' + period).removeClass("show").addClass("hide");
            } else {
                //当前未展开
                $('#arrow-' + period).removeClass("down").addClass("up");
                $('#plan-detail-' + period).removeClass("hide").addClass("show");
                console.log($('#plan-detail-' + period).css("display"));
            }

        }

    });
