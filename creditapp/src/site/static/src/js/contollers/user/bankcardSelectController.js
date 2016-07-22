 export default angular.module('wanda.app')
     .controller('bankcardSelectController',['$location', '$routeParams', '$interval', '$scope', 'userApiService', 'businessComponentsService', function($location, $routeParams, $interval, $scope, userApiService, businessComponentsService) {
         var serialNo = $routeParams.serialno;
         //当前选中卡
         $scope.cardInfo = {
             id: "",
             accountNoEncrypted: "",
             issueBank: ""
         };
         $scope.timesout = "获取验证码";
         var getVerifyCodeInterval,
             getBankcard = function() {
                 userApiService.bankcard.getBankcard({}).then(function(responseData) {
                     var bankcard = responseData.result;
                     if (bankcard && bankcard.id) {
                         $scope.isBankcardSelected = true;
                     }
                     $scope.bankcard = bankcard;
                 });
             },
             updateProcess = function() {
                 var postData = { serialNo: serialNo, finishStep: 'bankcard', cardId: $scope.bankcard.id, verifyCode: $scope.verifyCode };
                 userApiService.putProcess(postData).then(function(responseData) {
                     var data = responseData.result;
                     var process = businessComponentsService.resolveCreditProcess(serialNo, data.nextStep);
                     $location.path(process.currentPath);
                 }, errorDeal);
             },
             errorDeal = function(error) {
                 if (angular.isObject(error)) {
                     if (error.errorCallback) {
                         $.showDialog({
                             title: '',
                             button: ['返回'],
                             content: error.errorMessage
                         }, null, error.errorCallback);
                     } else {
                         if (error.errorMessage) {
                             $.showMsg(error.errorMessage);
                         }
                         if (angular.isString(error)) {
                             $.showMsg(error);
                         }
                     }
                 }
             }
         getBankcard();


         $scope.Linkbankcardlist = function() {
             $location.path('/user/bankcardlist');
         };

         $scope.onVerifyCodeChange = function() {
             var newVerifyCode = $scope.verifyCode;
             if (newVerifyCode) {
                 $scope.isVerifyCodeInput = newVerifyCode.length > 0;
             } else {
                 $scope.isVerifyCodeInput = false;
             }
         };

         $scope.GetVerifyCode = function() {
             if ($scope.isBankcardSelected) {
                 userApiService.bankcard.postBankcardVerifyCode({ cardId: $scope.bankcard.id }).then(function(responseData) {
                     $.toast({ content: '验证码已发送', stayTime: 2000 });
                     var data = responseData.result;
                     if (data) {
                         $scope.verifyCodeTimeout = true;
                         var ttl = parseInt(data.ttl);
                         if (data.verifycode) {
                             $scope.verifyCode = data.verifycode;
                             $scope.isVerifyCodeInput = $scope.verifyCode.length > 0;
                         }
                         var timesoutms = data.ttl * 1000;
                         getVerifyCodeInterval = $interval(function() {
                             if (ttl >= 0) {
                                 $scope.timesout = ttl + 's重新获取';
                                 ttl--;
                             } else {
                                 $scope.timesout = '获取验证码';
                                 $scope.verifyCodeTimeout = false;
                                 $interval.cancel(getVerifyCodeInterval);
                             }
                         }, 1000);
                     }
                 }, function(error) {
                     $.showMsg(error.errorMessage);
                 });
             }
         };

         $scope.submit = function() {
             if (!$scope.bankcard || $scope.bankcard.id == "") {
                 $.showMsg("请选择一张银行卡");
                 return;
             } else if (!$scope.verifyCode) {
                 $.showMsg("请输入验证码");
                 return;
             } else {
                 updateProcess();
             }
         };
     }]);
