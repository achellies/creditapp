export default angular.module('wanda.app')
    .controller('myReportsController', ['$scope', function($scope) {
        $scope.personalModel = [{
            reportType: 'personal',
            reportTitle: '个人报告',
            queryDate: '2015-07-01 12:30'
        }, {
            reportType: 'personal',
            reportTitle: '个人报告',
            queryDate: '2015-07-01 12:30'
        }, {
            reportType: 'centralBank',
            reportTitle: '人行报告',
            queryDate: '2015-06-02 12:30'
        }];
        $scope.companyModel = [{
            companyName: '华为技术有限公司',
            companyCorporation: '任正非',
            queryDate: '2015-07-01 12:30'
        }, {
            companyName: '联系计算机有限公司',
            companyCorporation: '柳传志',
            queryDate: '2015-07-02 12:30'
        }];
        $scope.showDetail = function() {
            console.log('show report detail...');
        };
    }]);
