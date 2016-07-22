/**
 * 
 */
function apiBaseService($http, $q) {
    //将API $HTTP 请求包装成服务
    var API_BASE_URL = '/timesloan/api/v1/consumption/';
    var API_BASE_URL_OLD = '/timesloan/api/v1/';
    var DoPost = function(apiPath, postData) {
        $.showBlockLoading('加载中...');
        postData = postData || {};
        var targetUrl = API_BASE_URL + apiPath;
        if (postData.v && postData.v == "1") {
            targetUrl = API_BASE_URL_OLD + apiPath;
        }
        if (postData) {
            postData.appid = ''; //$wd.APPID;
        } else {
            postData = { appid: '' /*$wd.APPID*/ };
        }
        return $http({
            method: 'POST',
            url: targetUrl,
            data: postData,
            headers: { "Content-Type": "application/json;charset=UTF-8" }
        });
    };

    var DoGet = function(apiPath, params) {
        $.showBlockLoading('加载中...');
        params = params || {};
        var targetUrl = API_BASE_URL + apiPath;
        if (params.v && params.v == "1") {
            targetUrl = API_BASE_URL_OLD + apiPath;
        }
        targetUrl += (targetUrl.indexOf('?') >= 0 ? '&' : '?') + 't=' + Date.now();
        if (params) {
            params.appid = ''; //$wd.APPID;
        } else {
            params = {
                appid: '' // $wd.APPID
            }
        }
        return $http({
            method: 'GET',
            url: targetUrl,
            params: params
        });
    };

    var DoPut = function(apiPath, putData) {
        $.showBlockLoading('加载中...');
        putData = putData || {};
        putData.appid = '';
        var targetUrl = API_BASE_URL + apiPath;
        if (putData.v && putData.v == "1") {
            targetUrl = API_BASE_URL_OLD + apiPath;
        }
        return $http({
            method: 'PUT',
            url: targetUrl,
            data: putData
        });
    };

    var DoDelete = function(apiPath, params) {
        $.showBlockLoading('加载中...');
        params = params || {};
        var targetUrl = API_BASE_URL + apiPath;
        if (params.v && params.v == "1") {
            targetUrl = API_BASE_URL_OLD + apiPath;
        }
        targetUrl += (targetUrl.indexOf('?') >= 0 ? '&' : '?') + 't=' + Date.now();
        if (params) {
            params.appid = ''; //$wd.APPID;
        } else {
            params = {
                appid: '' // $wd.APPID
            }
        }
        return $http({
            method: 'DELETE',
            url: targetUrl,
            params: params
        });
    };
    //服务器非正常响应数据
    var handleError = function(response) {
            //应用程序级错误
            var errorMessage = "发生未知错误";
            if (angular.isObject(response.data)) {
                if (response.data.status) {
                    if (response.data.status == 500) {
                        errorMessag = "连接服务器发生错误,请重新登陆后再试";
                    }
                    if (response.data.status == 408) {
                        errorMessag = "服务器请求超时";
                    }
                } else {
                    errorMessage = response.data.message;
                }
            } else if (response.status && response.status == 500) {
                errorMessage = "连接服务器发生错误";
            } else {
                errorMessage = response.statusText;
            }
            $.hideBlockLoading();
            return ($q.reject(errorMessage));
        }
        //服务端正常响应,返回期望的数据
    var handleSuccess = function(response) {
        var returnObj = response.data;
        //业务级错误
        if (response.data.errorCode != 2000) {
            returnObj = ($q.reject(response.data));
        }
        $.hideBlockLoading();
        return returnObj;
    }

    var handleError_old = function(response) {
        var result = response.data;

        if (response.status == 408 && !result.errorMessage) {
            result.errorMessage = "服务器请求超时";
        }
        if (response.status == 500 && !result.errorMessage) {
            result.errorMessage = "连接服务器发生错误,请重新登陆后再试"
        }
        $.hideBlockLoading();
        return ($q.reject(result));
    }

    var handleSuccess_old = function(response) {
        var returnObj = { result: response.data };
        $.hideBlockLoading();
        return returnObj;
    }


    return {
        Post: function(apiPath, postData) {
            return DoPost(apiPath, postData);
        },
        Get: function(apiPath, params) {
            return DoGet(apiPath, params);
        },
        Put: function(apiPath, params) {
            return DoPut(apiPath, params);
        },
        Delete: function(apiPath, params) {
            return DoDelete(apiPath, params);
        },
        handleSuccess: function(response) {
            return handleSuccess(response);
        },
        handleError: function(response) {
            return handleError(response);
        },
        handleErrorOld: function(response) {
            return handleError_old(response);
        },
        handleSuccessOld: function(response) {
            return handleSuccess_old(response);
        }
    }
}

export default [{
    name: 'apiBaseService',
    dependences: ['$http', '$q'],
    fn: apiBaseService
}];
