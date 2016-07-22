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
        $scope.showDetail = function(id){
            if(!id)return;
            $location.path('/housingFund/result/' + id);
        };
        $scope.addAccount = function(){
            $location.path('/housingFund/add');
        };
    }]);