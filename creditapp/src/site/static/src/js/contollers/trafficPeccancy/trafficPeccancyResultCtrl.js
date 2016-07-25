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
        $scope.ar_car_id = [];
        for(var i in $scope.ar_car_item){
            $scope.ar_car_id.push($scope.ar_car_item[i]['id']);
        }

        $scope.showDetail = function(id){
            if(!id)return;
            $location.path('/trafficPeccancy/detail/' + id);
        };
        $scope.addCar = function(){
            $location.path('/trafficPeccancy/add');
        };
        $scope.deleteCar = function(id){
            var dia = $.showDialog({
                title: '',
                button: ['删除', '不删除'],
                content: "是否删除本条车辆信息"
            }, function (e) {
                var del = e.index == 0;
                if(del){
                    console.log("delete car");
                }
            }, null);
        };

        $scope.showDelete = {};
        $scope.hasDelete = false;
        $scope.swipeRight = function(id){
            $scope.hasDelete = false;
            $scope.showDelete[id] = false;
        };
        $scope.swipeLeft = function(id){
            for(let i of $scope.ar_car_id){
                i != id && ($scope.showDelete[i] = false);
            }
            $scope.hasDelete = true;
            $scope.showDelete[id] = true;
        };
    }]);
