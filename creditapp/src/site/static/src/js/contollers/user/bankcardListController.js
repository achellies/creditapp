export default angular.module('wanda.app')
    .controller('bankcardListController', ['$location', '$scope', 'userApiService', function($location, $scope, userApiService) {
        $.showBlockLoading('处理中请稍后...');

        userApiService.bankcard.getBankcards({}).then(function(responseData) {
            var data = responseData.result;
            if (data.length == 0) {
                $location.path('/user/bankcardnew');
            }
            $scope.boundCards = data;
        }, function(error) {
            $.showMsg(data.errorMessage);
        });


        $scope.SelectBankcard = function(cardId) {
            //set user default bankcard
            userApiService.bankcard.postSetDefaultBankcard({ bankcardId: cardId })
                .then(function(responseData) {
                    history.go(-1);
                }, function(error) {
                    $.showMsg(error.errorMessage);
                });

        }
    }]);
