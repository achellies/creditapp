export default angular.module('wanda.app')
    .controller('housingFundAddController', ['$scope', '$location', function($scope, $location) {
        $scope.cityModel = [{
            cityID: 10001,
            cityName: '北京'
        }, {
            cityID: 10002,
            cityName: '上海'
        }, {
            cityID: 10003,
            cityName: '南京'
        }];
        $scope.validate = function() {
            return !this.checked;
        };
        $scope.submitAdd = function() {
            console.log('submit clicked!');
            $location.path('/housingFund/list');
        };
        $scope.howToRegister = function() {
            $location.path('/housingFund/register');
        };
    }]);
