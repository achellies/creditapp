export default angular.module('wanda.app')
    .controller('housingFundListCtrl', ['$scope', '$location', function($scope, $location) {
        $scope.ar_account_item = [
            {
                id: 1,
                name: '王明亮',
                amount: '12783.50',
                last_date: '2016-05-06'
            },{
                id: 2,
                name: '王明亮',
                amount: '12783.50',
                last_date: '2016-05-06'
            }
        ];
        $scope.ar_account_id = [];
        for(var i in $scope.ar_account_item){
            $scope.ar_account_id.push($scope.ar_account_item[i]['id']);
        }
        $scope.showDetail = function(id){
            if(!id)return;
            $location.path('/housingFund/result/' + id);
        };
        $scope.addAccount = function(){
            $location.path('/housingFund/add');
        };
        $scope.deleteAccount = function(id){
            var dia = $.showDialog({
                title: '',
                button: ['删除', '不删除'],
                content: "是否删除本条公积金账号"
            }, function (e) {
                var del = e.index == 0;
                if(del){
                    console.log("delete account");
                }
            }, null);
        };

        $scope.showDelete = {};
        $scope.hasDelete = false;
        $scope.swipeRight = function(id){
            console.log('right ', id)
            $scope.hasDelete = false;
            $scope.showDelete[id] = false;
        };
        $scope.swipeLeft = function(id){
            console.log('left ', id)
            for(let i of $scope.ar_account_id){
                i != id && ($scope.showDelete[i] = false);
            }
            $scope.hasDelete = true;
            $scope.showDelete[id] = true;
        };
    }]);