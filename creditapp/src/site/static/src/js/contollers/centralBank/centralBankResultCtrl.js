export default angular.module('wanda.app')
    .controller('centralBankResultCtrl', ['$scope', '$location', function($scope, $location) {
        $scope.ar_info_title = [
            {key: '1', value: "信用卡记录", icon: '../../imgs/centralBank/bank_record.png'},
            {key: '2', value: "购房贷款记录", icon: '../../imgs/centralBank/house_fund.png'},
            {key: '3', value: "其他贷款记录", icon: '../../imgs/centralBank/other_record.png'}
        ];
        $scope.ar_num_nav = [
            {key: '1', value: "账户数"},
            {key: '2', value: "未销户"},
            {key: '3', value: "逾期数"},
            {key: '4', value: "严重逾期"}
        ];
        var ar_title = [
            {key: '1', value: "担保记录", icon: '../../imgs/centralBank/protect_record.png'},
            {key: '2', value: "个人查询记录", icon: '../../imgs/centralBank/personal_record.png'},
            {key: '3', value: "企业查询记录", icon: '../../imgs/centralBank/compony_record.png'}
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
            $location.path('/centralBank/detail/' + id);
        };
    }]);
