export default angular.module('wanda.app')
    .controller('housingFundResultCtrl', ['$scope', '$routeParams', '$location', function($scope, $routeParams, $location) {
        $scope.ar_year = [
            '2016年',
            '2015年',
            '2014年'
        ];
        $scope.showDetail = function(year){
            if(!year)return;
            $location.path('/housingFund/detail/' + year);
        }
    }]);
