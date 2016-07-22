export default angular.module('wanda.app')
    .controller('contactInfoController',['$scope', '$http', '$timeout',function($scope, $http, $timeout) {
        'use strict';
        $scope.buttonVisible = true;
        // $scope.my_contact_relation = c_relation;
        $scope.options = [
            { id: '1', name: '亲属' },
            { id: '2', name: '朋友' },
            { id: '3', name: '同事' },
            { id: '0', name: '其他' }];
        $scope.phone_error = false;


        $scope.contactinfo_submit = function() {
            $.showBlockLoading('处理中请稍候...');
            // $http({
            //     method: 'POST',
            //     url: contact_submit_url,
            //     data: {
            //         "pageSource": "",
            //         "processContext": processContext,
            //         "phoneNo": $scope.my_phone,
            //         "homeAddress": $scope.my_address,
            //         "contactName": $scope.my_contact,
            //         "contactRelationship": $scope.my_contact_relation,
            //         "contactPhoneno": $scope.my_contact_phone
            //     },
            //     headers: { "Content-Type": "application/json;charset=UTF-8" }
            // }).success(function(data) {
            //     console.log(data);
            //     if (data) {
            //         if (!data.nextUrl) {
            //             console.log('close web view');
            //             $.hideBlockLoading();

            //             cordova.exec(function(data) {
            //                 //native_log("关闭页面 成功");
            //             }, function(data) {
            //                 //native_log("关闭页面 失败");
            //             }, "WDPPageControl", "pageControl", [{ action: "close" }]);

            //             return;
            //         }
            //         //navBar.back = data.pageSource
            //         window.location = data.nextUrl;

            //         //backUrl = data.pageSource;
            //         //pageSource = data.nextUrl;
            //     }
            //     $.hideBlockLoading();
            // }).error(function(data) {
            //     if (data) {
            //         $.showMsg(data.errorMessage);
            //     } else {
            //         $.showMsg("提交个人信息失败，请重试。");
            //     }

            //     $.hideBlockLoading();
            // });
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
            wngMobileNoTest(phone);
            $scope.my_phone_error_msg = phone.errorMessage;
            $scope.phone_error = phone.errorShow;
        };

        $scope.my_contact_phone_change = function() {
            var phone = {
                "phoneNo": $scope.my_contact_phone,
                "errorMessage": $scope.my_contact_phone_error_msg,
                "errorShow": null
            };
            wngMobileNoTest(phone);
            $scope.my_contact_phone_error_msg = phone.errorMessage;
            $scope.phone_error = phone.errorShow;
        }
    }]);
