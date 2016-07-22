export default angular.module('wanda.app')
    .controller('personalDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
        $scope.ar_nav_bar = [
            {key: '1', value: "高等学历"},
            {key: '2', value: "司法执行"},
            {key: '3', value: "判决文书"},
            {key: '4', value: "司法案件"},
            {key: '5', value: "高管信息"},
            {key: '6', value: "法人信息"},
            {key: '7', value: "股东信息"},
            {key: '8', value: "驾驶证信息"},
            {key: '9', value: "机动车信息"}
        ];
        $scope.ar_education_info = [
            {key: '1', value: "最高学历"},
            {key: '2', value: "学校名称"},
            {key: '3', value: "所学专业"},
            {key: '4', value: "毕业时间"}
        ];
        $scope.ar_education_section = [
            {key: '1', value: "学历证编号"},
            {key: '2', value: "学习类型"},
            {key: '3', value: "学历类别"},
            {key: '4', value: "毕业结论"},
            {key: '5', value: "学校类型"},
            {key: '6', value: "办学性质"},
            {key: '7', value: "办学层次"},
            {key: '8', value: "是否211院校"},
            {key: '9', value: "是否国家重点学科"},
            {key: '10', value: "理科录取批次"},
            {key: '11', value: "文科录取批次"},
            {key: '12', value: "学校所在地"},
            {key: '13', value: "学校创建时间"},
            {}
        ];
        $scope.current_nav = $routeParams.type ? $routeParams.type : '1';
        $scope.ar_education = {};
        $scope.selectTab = function(nav_bar_key){
            $scope.current_nav = nav_bar_key;
        };
    }]);
