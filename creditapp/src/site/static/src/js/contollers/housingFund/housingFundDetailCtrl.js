export default angular.module('wanda.app')
    .controller('housingFundDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
        $scope.ar_item = [
            {
                date: '2015-09-08',
                project: '汇缴2015年8月公积金',
                amount: 1200,
                balance: 4200
            },{
                date: '2015-10-08',
                project: '汇缴2015年9月公积金',
                amount: 1200,
                balance: 5400
            }
        ];
    }]);
