export default angular.module('wanda.app')
    .controller('personalQueryController', ['$scope', '$location', function($scope, $location) {
        $scope.userName = '金正恩';
        $scope.userCardID = '340322****0808';
        $scope.CAR_INDEX = 2;
        $scope.contentList = [{
            title: '全选',
            type: 'all',
            selected: true
        }, {
            title: '高等学历',
            type: 'all1',
            selected: true
        }, {
            title: '车辆信息',
            type: 'all1',
            selected: true
        }, {
            title: '驾驶证',
            type: 'all1',
            selected: true
        }, {
            title: '高管信息',
            type: 'all1',
            selected: true
        }, {
            title: '法人信息',
            type: 'all1',
            selected: true
        }, {
            title: '司法执行',
            type: 'all1',
            selected: true
        }, {
            title: '失信信息',
            type: 'all1',
            selected: true
        }, {
            title: '判决文书',
            type: 'all1',
            selected: true
        }, {
            title: '司法案件',
            type: 'all1',
            selected: true
        }];
        $scope.carInfo = {
            carNumber: '',
            citySelected: '',
            carTypeSelected: ''
        };
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

        function fetchQueryParams() {
            var queryParams = {};
            queryParams.userName = $scope.userName;
            queryParams.userCardID = $scope.userCardID;

            var contentToQuery = [];
            $scope.contentList.forEach(function(content) {
                if (content.selected) {
                    contentToQuery.push(content.type);
                }
            });
            queryParams.contents = contentToQuery;
            if ($scope.contentList[$scope.CAR_INDEX].selected) {
                queryParams.carInfo = {
                    carNumber: $scope.carInfo.carNumber,
                    carCity: $scope.carInfo.citySelected.cityID,
                    carType: $scope.carInfo.carTypeSelected.typeID
                };
            }
            return queryParams;
        };
        $scope.submitQuery = function() {
            console.log('submit clicked!');
            console.log(JSON.stringify(fetchQueryParams()));
            $location.path('/personal/result');
        };
    }]);
