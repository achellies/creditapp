export default angular.module('wanda.app')
    .controller('personalResultCtrl', ['$scope', '$routeParams', '$location', function($scope, $routeParams, $location) {
        var ar_title = [
            {key: '1', value: "高等学历", icon: '../../imgs/personal/personal_1xueli.png'},
            {key: '2', value: "司法执行", icon: '../../imgs/personal/personal_2sifa.png'},
            {key: '3', value: "判决文书", icon: '../../imgs/personal/personal_3panjue.png'},
            {key: '4', value: "司法案件", icon: '../../imgs/personal/personal_4sifa.png'},
            {key: '5', value: "商管信息", icon: '../../imgs/personal/personal_5gaoguan.png'},
            {key: '6', value: "法人信息", icon: '../../imgs/personal/personal_6faren.png'},
            {key: '7', value: "股东信息", icon: '../../imgs/personal/personal_7gudong.png'},
            {key: '8', value: "驾驶证信息", icon: '../../imgs/personal/personal_8jiashi.png'},
            {key: '9', value: "机动车信息", icon: '../../imgs/personal/personal_9jidong.png'}
        ];
        // 图标补齐
        var i = ar_title.length % 4;
        while(i && 4 - i){
            ar_title.push({});
            i++;
        }
        $scope.ar_title = ar_title;
        $scope.showDetail = function(id){
            if(!id)return;
            $location.path("/personal/detail/" + id);
        };
    }]);
