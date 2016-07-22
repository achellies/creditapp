export default angular.module('wanda.app')
    .controller('centralBankQueryController', ['$scope', '$location', function($scope, $location) {
        $scope.validate = function() {
            return !this.checked;
        };
        $scope.submitQuery = function() {
            console.log('submit clicked!');
            $location.path("/centralBank/result");
        };
        $scope.howToRegister = function() {
            $location.path("/centralBank/register");
        };
    }]);
