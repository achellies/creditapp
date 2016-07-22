export default angular.module('wanda.app')
    .controller('contactInfoController', ['$scope', '$routeParams', '$location', '$http', '$timeout', 'userApiService', 'businessComponentsService', function($scope, $routeParams, $location, $http, $timeout, userApiService, businessComponentsService) {
        'use strict';
        var serialNo = $routeParams.serialno;
        var getContactInfo = function() {
                userApiService.getContactInfo({}).then(function(responseData) {
                    var data = responseData.result;
                    $scope.my_phone = data.userMobile;
                    $scope.my_address = data.homeAddress;
                    $scope.my_contact = data.contactName;
                    $scope.my_contact_relation = data.contactRelationship;
                    $scope.my_contact_phone = data.contactPhoneno;
                    $scope.addressCode = data.addressCode;
                    $scope.addressProvince = data.addressProvince;
                    $scope.addressCity = data.addressCity;
                    $scope.addressDistrict = data.addressDistrict;
                    $scope.cityShow = data.addressProvince + data.addressCity + data.addressDistrict;
                });
            },
            mobileNoVerify = function(model) {
                'use strict';
                var phone_no_pattern = /^1\d{10}$/; //电话号码格式
                var phone_no = model.phoneNo;
                var test_result = phone_no_pattern.test(phone_no);
                // 决定显示逻辑
                if (test_result) {
                    model.errorMessage = ""; //清除错误信息
                } else {
                    if (phone_no.length > 0) {
                        model.errorMessage = "手机号不正确";
                    } else {
                        model.errorMessage = "请输入手机号";
                    }
                }
                model.errorShow = !test_result;
            },
            postContactInfo = function() {
                var postData = {
                    phoneNo: $scope.my_phone,
                    homeAddress: $scope.my_address,
                    contactName: $scope.my_contact,
                    contactRelationship: $scope.my_contact_relation,
                    contactPhoneno: $scope.my_contact_phone,
                    addressCode: $scope.addressCode,
                    addressProvince: $scope.addressProvince,
                    addressCity: $scope.addressCity,
                    addressDistrict: $scope.addressDistrict
                }
                return userApiService.postContactInfo(postData).then(function(responseData) {
                    var data = responseData.result;
                    return data;
                });
            },
            updateProcess = function() {
                var postData = { serialNo: serialNo, finishStep: 'contactInfo' };
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
            };

        $scope.buttonVisible = true;
        $scope.addresspcr = { cityCode: '' };
        $scope.options = [
            { id: '1', name: '亲属' },
            { id: '2', name: '朋友' },
            { id: '3', name: '同事' },
            { id: '0', name: '其他' }
        ];
        $scope.phone_error = false;

        getContactInfo();

        $scope.callNative_SelectCity = function() {
            console.log("打开地址选择");
            window.wdCordova.SelectCity(function(data) {
                var data = JSON.parse(data);
                if (data.status == 'success') {
                    $scope.addressCode = data.areaCode;
                    $scope.addressProvince = data.provinceName;
                    $scope.addressCity = data.cityName;
                    $scope.addressDistrict = data.areaName;
                    $scope.cityShow = data.provinceName + data.cityName + data.areaName;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }
            }, function(data) {
                $.showMsg("省市区选择发生错误");
            }, [{ currentCity: $scope.addresspcr.cityCode }]);
        }

        $scope.contactinfo_submit = function() {
            $.showBlockLoading('处理中请稍候...');
            var postData = {

                phoneNo: $scope.my_phone,
                homeAddress: $scope.my_address,
                contactName: $scope.my_contact,
                contactRelationship: $scope.my_contact_relation,
                contactPhoneno: $scope.my_contact_phone
            }


            postContactInfo(postData)
                .then(updateProcess)
                .then(function(success) {}, errorDeal)
                .catch(errorDeal);
        };

        $scope.address_change = function() {
            if ($scope.my_address.length > 50) {
                $scope.my_address = $scope.my_address.substring(0, 50);
            }
        };

        $scope.my_contact_change = function() {
            if ($scope.my_contact.length > 10) {
                $scope.my_contact = $scope.my_contact.substring(0, 10);
            }
        };

        $scope.my_phone_change = function() {
            var phone = {
                "phoneNo": $scope.my_phone,
                "errorMessage": $scope.my_phone_error_msg,
                "errorShow": null
            };
            mobileNoVerify(phone);
            $scope.my_phone_error_msg = phone.errorMessage;
            $scope.phone_error = phone.errorShow;
        };

        $scope.my_contact_phone_change = function() {
            var phone = {
                "phoneNo": $scope.my_contact_phone,
                "errorMessage": $scope.my_contact_phone_error_msg,
                "errorShow": null
            };
            mobileNoVerify(phone);
            $scope.my_contact_phone_error_msg = phone.errorMessage;
            $scope.phone_error = phone.errorShow;
        }
    }]);
