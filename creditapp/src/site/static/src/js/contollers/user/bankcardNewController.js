export default angular.module('wanda.app')
    .controller('bankcardNewController', ['$scope', '$timeout', '$interval', 'commonApiService', 'userApiService', function($scope, $timeout, $interval, commonApiService, userApiService) {

        var phoneNoReg = /^1\d{10}$/; //phoneNum Pattern
        var bankcardReg = /^(\d{16}|\d{19})$/; //bankcard Pattern
        var phoneNoTimeout; //phoneNum input Timeout event
        var bankcardTimeout; //bankcard input Timeout event
        var support_bank = ["工商银行", "农业银行", "中国银行", "建设银行", "光大银行", "广发银行", "民生银行", "中信银行", "平安银行"];
        var getVerifyCodeInterval = "";
        $scope.isPhoneNoError = false;
        $scope.isBankcardError = false;
        $scope.isPhoneNoInput = false;
        $scope.isBancardNoInput = false;
        $scope.isVerifyCodeInput = false;
        $scope.verifyCodeTimeout = false;
        //on Phone Number Changed Timeout 300

        $scope.timesout = "获取验证码";

        $scope.onPhoneNoChange = function() {
            var newPhoneNo = $scope.phoneNo;
            if (newPhoneNo) {
                if (phoneNoTimeout) $timeout.cancel(phoneNoTimeout);
                phoneNoTimeout = $timeout(function() {
                    $scope.isPhoneNoError = !(phoneNoReg.test(newPhoneNo));
                    if ($scope.isPhoneNoError) {
                        if (newPhoneNo.length > 0) {
                            $scope.phoneNoErrorText = "手机号不正确";
                        } else {
                            $scope.phoneNoErrorText = "请输入手机号";
                        }
                    } else {
                        $scope.isPhoneNoInput = newPhoneNo.length > 0;
                    }

                }, 300);
            } else {
                $scope.isPhoneNoInput = false;
            }
        };
        //on Bankcard Changed Timeout 300
        $scope.onBankcardChange = function() {
            var newBankcard = $scope.bankcardNo;
            if (newBankcard) {
                if (bankcardTimeout) $timeout.cancel(bankcardTimeout);
                bankcardTimeout = $timeout(function() {
                    $scope.isBankcardError = !(bankcardReg.test(newBankcard));
                    if ($scope.isBankcardError) {
                        if (newBankcard.length > 0) {
                            $scope.bankcardErrorText = "银行卡不正确";
                        } else {
                            $scope.bankcardErrorText = "请输入银行卡";
                        }
                        $scope.issueBank = "";
                    } else {
                        $scope.isBancardNoInput = newBankcard.length > 0;
                        commonApiService.getIssuebank({ bankcardNo: $scope.bankcardNo }).then(function(responseData) {
                            var data = responseData.result;
                            if (data && data.issueBank) {
                                $scope.issueBank = data.issueBank;
                            }
                        }, function(error) {
                            $scope.isBankcardError = true;
                            if (error.errorMessage) {
                                $scope.bankcardErrorText = error.errorMessage;
                                $scope.issueBank = "";
                            }
                        })

                    }
                }, 1000);
            } else {
                $scope.isBancardNoInput = false;
            }
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
            if ($scope.isPhoneNoInput && $scope.phoneNo) {
                commonApiService.postPhoneVerifyCode({ mobile: $scope.phoneNo }).then(function(data, status) {
                    $.toast({ content: '验证码已发送', stayTime: 2000 });
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
            var postData = {
                cardNumber: $scope.bankcardNo,
                issueBank: $scope.issueBank,
                phoneNo: $scope.phoneNo,
                verifyCode: $scope.verifyCode
            };
            $.showBlockLoading('处理中请稍后...');
            bbbbb.bankcard.addBankcard(postData).then(function(responseData) {
                var data = responseData.result;
                if (data) {
                    history.go(-1);
                }
            }, function(error) {
                if (error) {
                    $.toast({ content: error.errorMessage, stayTime: 3000 });
                }
                $.hideBlockLoading();
            });
        };

        $scope.supportBank = function() {
            if (support_bank.length == 0) {
                return;
            }
            var linebreak = '<br/>';
            var bankInfo = '';
            var bank;
            for (var i in support_bank) {
                bank = support_bank[i];
                if (i % 3 == 2) {
                    bankInfo += bank;
                    bankInfo += linebreak;
                } else {
                    bankInfo += bank + "、";
                }
            }
            $.showDialog({
                title: '支持的银行卡发卡行',
                content: bankInfo,
                button: ['确定']
            });
        }
    }]);
