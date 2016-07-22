export default angular.module('wanda.app')
    .controller('commonResultController', function($scope, $routeParams) {
        $scope.result = "success";
        $scope.main = '提交成功'
        $scope.alt = '请收到贷款审核通知后再进行下一步。如有疑问，请联系您的销售人员'
        $scope.btn = "返回首页";

    });
