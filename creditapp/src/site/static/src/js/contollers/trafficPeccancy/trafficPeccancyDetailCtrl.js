export default angular.module('wanda.app')
    .controller('trafficPeccancyDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
        $scope.ar_item = {
            '1': '未处理违章',
            '2': '共扣分',
            '3': '共罚款(元)'
        };
        $scope.car_item = {
                id: 1,
                name: '沪 AB8888',
                info: {
                    '1': '2',
                    '2': '6',
                    '3': '400'
                },
                time: '2016-06-07'
        };
        $scope.ar_item_detail = [
            {
                'time': '2011-07-15 08:51:03',
                'place': '上海浦东新区蓝村路口',
                'reason': '违反规定停放车辆',
                'points': 3,
                'fine': 200
            },
            {
                'time': '2011-07-15 08:51:03',
                'place': '上海浦东新区蓝村路口',
                'reason': '违反规定停放车辆',
                'points': 3,
                'fine': 200
            }
        ];
    }]);
