export default angular.module('wanda.app')
    .controller('trafficPeccancyResultCtrl', ['$scope', '$location', function($scope, $location) {
        $scope.ar_item = {
            '1': '未处理违章',
            '2': '共扣分',
            '3': '共罚款(元)'
        };
        $scope.ar_car_item = [
            {
                id: 1,
                name: '沪 AB8888',
                info: {
                '1': '2',
                '2': '6',
                '3': '400'
                }
            },
            {
                id: '2',
                name: '沪 AB8889',
                info: {
                '1': '3',
                '2': '8',
                '3': '600'
                }
            }
        ];
        $scope.showDetail = function(id){
            if(!id)return;
            $location.path('/trafficPeccancy/detail/' + id);
        };
        $scope.addCar = function(){
            $location.path('/trafficPeccancy/add');
        };
    }]);
