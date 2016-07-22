export default angular.module('wanda.app')
    .controller('trafficPeccancyAddController', ['$scope', '$location', function($scope, $location) {
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
        $scope.carTypes = [{
            typeID: 10001,
            typeName: '小型车'
        }, {
            typeID: 10002,
            typeName: '中型车'
        }, {
            typeID: 10003,
            typeName: '大型车'
        }];
        $scope.validate = function() {
            return !this.checked;
        };
        $scope.submitAdd = function() {
            console.log('submit clicked!');
            $location.path('/trafficPeccancy/result');
        };
        $scope.howToRegister = function() {
            console.log('how To Register...');
        };
    }]);
