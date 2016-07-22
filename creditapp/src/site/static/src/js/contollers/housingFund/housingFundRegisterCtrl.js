export default angular.module('wanda.app')
    .controller('housingFundRegisterCtrl', ['$scope', '$sce', function($scope, $sce) {
        $scope.stepModel = [{
            title: '获取公积金账号',
            description: $sce.trustAsHtml('方式一：询问公司的人事或行政了解自己的公积金账号。<br>方式二：凭身份证到当地公积金或社保中心查询公积金账号。')
        }, {
            title: '获取公积金账户用户名和密码',
            description: $sce.trustAsHtml('直接到当地公积金或社保中心网站进行注册，或者找回密码。')
        }];
    }]);
