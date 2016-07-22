export default angular.module('wanda.app')
    .controller('centralBankRegisterCtrl', ['$scope', function($scope) {
        $scope.stepModel = [{
            title: '注册',
            description: '登录到人行征信网站http://www.pbccrc.org.cn并注册。'
        }, {
            title: '登录并验证',
            description: '用注册账号登录人行征信网站，并回答问题进行身份验证。'
        }, {
            title: '提交申请并等待',
            description: '提交查询申请，并等待人行征信网站反馈的查询验证码，通常24小时内会有反馈。'
        }, {
            title: '获取查询验证',
            description: '获取人行征信网站反馈的查询验证码。这样就获得了央行征信查询网站的用户名、密码、查询验证码。'
        }];
    }]);
