 export default angular.module('wanda.app')
     .controller('PasswordController', function($document, $scope, $routeParams, $location, $timeout, $window) {
         $scope._inputpwd = "";

         var $passwordOrigin = $('.password-origin'),
             inputs = [$passwordOrigin],
             currentInput = 0,
             passwordgrids = [
                 $passwordOrigin.find('.password-input')
             ],
             passwordBuffers = [
                 []
             ],
             _hisInput = "",
             _lockInput = false;
         $document.ready(function() {

             if (!(buyflow && buyflow.productId && buyflow.cardId && buyflow.amount)) {
                 $timeout(function() {
                     alert("订单不存在,回到首页");
                     $location.path('/productlist');
                 }, 0);
             }
             var browserName = navigator.userAgent.toLowerCase();
             if (/iphone|ipad|ipod/.test(browserName)) {

             } else if (/android/.test(browserName)) {
                 focusInput(0);
             }
             // focusInput(0);
             $passwordOrigin.on('click', function() {
                 focusInput(0);
             });

         });

         var submit = function() {
                 if (passwordBuffers[0].length === 6) {
                     var _password = passwordBuffers[0].join('');
                     $scope.Password.password = _password;
                 }
                 if (!_lockInput) {
                     _lockInput = true;
                     var body = {
                         productId: buyflow.productId,
                         amount: buyflow.amount,
                         cardId: buyflow.cardId,
                         password: $scope.Password.password,
                         orderChannel: "wandaGroupApp"
                     }
                     userService.addOrder(body).then(function(responseData) {
                         gotoSuccess();
                     }, function(errorMessage) {
                         var errorCode = errorMessage.errorCode;
                         var errorMessage = errorMessage.errorMessage;
                         switch (errorCode) {
                             //重新支付
                             case 10019:
                                 $.showDialog({
                                     title: '',
                                     button: ['关闭'],
                                     content: errorMessage
                                 }, null, function() {
                                     focusInput(0);

                                 });
                                 // $.showMsg(errorMessage);
                                 clearInput();
                                 blurInput(0);
                                 break;
                             case 10020:
                             case 10021:
                             case 10022:
                             case 10023:
                             case 10024:
                             case 10025:
                             case 10028:
                             case 10030:
                             case 10031:
                                 //支付失败
                                 gotoError(errorMessage);
                                 break;
                             case 10026:
                                 //支付成功,等待结果
                                 gotoWaiting(errorMessage);
                                 break;
                         }
                     });

                 }
             }, //处理输入
             handleInput = function(curInput, keyCode) {
                 var curPwd = passwordBuffers[curInput];
                 var inputFinish = (curPwd.length >= 6);
                 if (keyCode == 8) {
                     //on delete
                     curPwd.pop();
                 } else {
                     //on input
                     if (!inputFinish) {
                         var num = keyCode - 48;
                         curPwd.push(num);
                     }
                 }
                 handleDisplay(curInput);
                 checkFinish();
             },
             //判断完成输入
             checkFinish = function() {
                 $scope.PasswordFinish = (passwordBuffers[0].length == 6);
                 if ($scope.PasswordFinish) {
                     submit();
                 }
             },
             //处理显示
             handleDisplay = function(type) {
                 var i = 0;
                 var curPwdLength = passwordBuffers[type].length;
                 for (; i < curPwdLength; i++) {
                     $(passwordgrids[type][i]).addClass('active');
                 }
                 for (; i < 6; i++) {
                     $(passwordgrids[type][i]).removeClass('active');
                 }
             },
             clearInput = function() {
                 _lockInput = false;
                 passwordBuffers[0] = [];
                 handleDisplay(0);
                 _hisInput = "";
                 $('#csrf').val("");
             },
             //切换输入
             focusInput = function(curInput) {
                 $("#csrf").focus();
                 currentInput = curInput;
                 inputs[curInput].addClass('active');
                 _lockInput = false;
                 // inputs[Math.abs(curInput - 1)].removeClass('active');
             },
             blurInput = function(curInput) {
                 $("#csrf").blur();
                 inputs[curInput].removeClass('active');
                 _lockInput = false;
             },
             gotoError = function(msg) {
                 $timeout(function() {
                     $location.path('/error').replace().search("msg", msg);
                 }, 0);
             },
             gotoSuccess = function() {
                 dataShareServiceGlobal.clear('_wd_buyflow');
                 dataShareServiceGlobal.clear('_wd_selecedcard');
                 $timeout(function() {
                     $location.path('/success').replace();
                 }, 0);

             },
             gotoWaiting = function(msg) {
                 dataShareServiceGlobal.clear('_wd_buyflow');
                 dataShareServiceGlobal.clear('_wd_selecedcard');
                 $timeout(function() {
                     $location.path('/waiting').replace('').search("msg", msg);
                 }, 0);
             },
             gotoHome = function() {
                 dataShareServiceGlobal.clear('_wd_buyflow');
                 dataShareServiceGlobal.clear('_wd_selecedcard');
                 $timeout(function() {
                     $location.path('/productlist').replace()
                 }, 0);
             };


         //隐藏输入框,仅用于获得焦点
         $scope.keyUp = function() {
             if ($(".ui-dialog").length == 0) {
                 var curInput = $scope._inputpwd;
                 var _curInput = "";
                 $scope._inputpwd = _curInput = curInput.replace(/\D/g, '');
                 if (_hisInput.length > _curInput.length) {

                     handleInput(currentInput, 8);
                 } else if (_hisInput.length < _curInput.length) {
                     var charCode = _curInput.charCodeAt(_curInput.length - 1);

                     handleInput(currentInput, charCode);
                 } else {
                     console.log("输入非数字");
                 }
                 _hisInput = _curInput;
             }
         };

         $scope.Password = { password: '' };
         $scope.PasswordFinish = false;
         $scope.submit = submit;
     });
